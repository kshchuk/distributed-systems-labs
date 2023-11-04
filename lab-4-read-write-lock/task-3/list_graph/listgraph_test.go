package list_graph

import (
	"math"
	"task-3/priority_queue"
	"testing"
)

func TestGraph(t *testing.T) {
	g := &Graph{}

	// Test AddEdge
	g.AddEdge(0, 1, 5.0)
	g.AddEdge(1, 2, 3.0)
	g.AddEdge(2, 0, -2.0)

	// Test Bellman-Ford
	dist, prev, hasNegativeCycle := g.BellmanFord(0)
	if hasNegativeCycle {
		t.Error("Expected no negative weight cycle")
	}
	expectedDist := []float64{0.0, 5.0, 8.0}
	if !floatSliceEqual(dist, expectedDist) {
		t.Errorf("Bellman-Ford: Expected distances %v, got %v", expectedDist, dist)
	}
	expectedPrev := []int{-1, 0, 1}
	if !intSliceEqual(prev, expectedPrev) {
		t.Errorf("Bellman-Ford: Expected previous vertices %v, got %v", expectedPrev, prev)
	}

	// Test Dijkstra
	dist, prev = g.Dijkstra(0, priority_queue.NewFibHeap())
	expectedDist = []float64{0.0, 5.0, 8.0}
	if !floatSliceEqual(dist, expectedDist) {
		t.Errorf("Dijkstra: Expected distances %v, got %v", expectedDist, dist)
	}
	expectedPrev = []int{-1, 0, 1}
	if !intSliceEqual(prev, expectedPrev) {
		t.Errorf("Dijkstra: Expected previous vertices %v, got %v", expectedPrev, prev)
	}
}

func floatSliceEqual(a, b []float64) bool {
	if len(a) != len(b) {
		return false
	}
	for i, v := range a {
		if math.Abs(v-b[i]) > 1e-9 {
			return false
		}
	}
	return true
}

func intSliceEqual(a, b []int) bool {
	if len(a) != len(b) {
		return false
	}
	for i, v := range a {
		if v != b[i] {
			return false
		}
	}
	return true
}
