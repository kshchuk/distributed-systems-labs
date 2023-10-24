package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

type Stock struct {
	name  string
	price int
}

func (s *Stock) increasePrice() {
	s.price++
}

func (s *Stock) decreasePrice() {
	s.price--
}

type Broker struct {
	stock     *Stock
	semaphore chan bool
	random    *rand.Rand
}

func (b *Broker) buyStock() {
	price := b.stock.price
	time.Sleep(time.Second)
	fmt.Printf("Куплено акції %s за ціною %d\n", b.stock.name, price)
	b.stock.increasePrice()
}

func (b *Broker) sellStock() {
	price := b.stock.price
	time.Sleep(time.Second)
	fmt.Printf("Продано акції %s за ціною %d\n", b.stock.name, price)
	b.stock.decreasePrice()
}

func (b *Broker) run(wg *sync.WaitGroup, barrier chan bool) {
	defer wg.Done()

	b.semaphore <- true
	if b.random.Intn(2) == 0 {
		b.buyStock()
	} else {
		b.sellStock()
	}
	<-b.semaphore

	barrier <- true
}

type Exchange struct {
	brokers       []*Broker
	exchangeIndex int
	THRESHOLD     int
}

func (e *Exchange) calculateExchangeIndex(barrier chan bool) {
	sum := 0
	for _, broker := range e.brokers {
		sum += broker.stock.price
	}
	e.exchangeIndex = sum / len(e.brokers)
	fmt.Println("Індекс біржі:", e.exchangeIndex)

	if e.exchangeIndex > e.THRESHOLD {
		close(barrier)
	}
}

func (e *Exchange) startTrading() {
	barrier := make(chan bool)

	for {
		semaphore := make(chan bool, len(e.brokers))

		for _, broker := range e.brokers {
			broker.semaphore = semaphore
		}

		wg := &sync.WaitGroup{}
		wg.Add(len(e.brokers))

		for _, broker := range e.brokers {
			go broker.run(wg, barrier)
		}

		wg.Wait()
		close(semaphore)
		
		go func() {
			for range barrier {
				e.calculateExchangeIndex(barrier)
				if e.exchangeIndex <= e.THRESHOLD {
					break
				}
			}
		}()
	}
	fmt.Println("Торги призупинено, індекс біржі:", e.exchangeIndex)
}

func main() {
	rand.Seed(time.Now().UnixNano())

	names := []string{"Google", "Apple", "Microsoft", "Amazon", "Facebook", "Tesla", "Intel", "Oracle", "IBM", "HP"}
	prices := []int{100, 200, 300, 400, 500, 600, 700, 800, 900, 1000}

	var stocks []*Stock
	for i := range names {
		stocks = append(stocks, &Stock{name: names[i], price: prices[i]})
	}

	var brokers []*Broker
	for _, stock := range stocks {
		brokers = append(brokers, &Broker{stock: stock, random: rand.New(rand.NewSource(time.Now().UnixNano()))})
	}

	exchange := &Exchange{brokers: brokers, THRESHOLD: 500}
	exchange.startTrading()
}
