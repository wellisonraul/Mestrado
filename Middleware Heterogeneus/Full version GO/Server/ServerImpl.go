package main

// Types needed to integrate application
type Fibonacci struct{}

// Implementation of the Fibonacci function
func (c Fibonacci) FibonacciRecursive(Number float64) float64 {
	if Number <= 1 {
		return Number
	} else {
		return c.FibonacciRecursive(Number-1) + c.FibonacciRecursive(Number-2)
	}
}