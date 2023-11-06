package org.example.barrier;

public class MyCyclicBarrier {
    private final int finalThreadCount;
    private volatile int currentThreadCount;

    public MyCyclicBarrier(int finalThreadCount) {
        this.finalThreadCount = finalThreadCount;
        this.currentThreadCount = 0;
    }

    public synchronized void await() throws InterruptedException{
        currentThreadCount++;
        if(currentThreadCount < finalThreadCount){
            this.wait();
        }
        currentThreadCount = 0;
        notifyAll();
    }

    public synchronized Boolean isBroken(){
        return currentThreadCount == finalThreadCount - 1;
    }

    public synchronized void reset(){
        currentThreadCount = 0;
    }

    public synchronized int getParties(){
        return finalThreadCount;
    }

    public synchronized int getNumberWaiting(){
        return currentThreadCount;
    }
}
