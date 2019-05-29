package main

func main() {
	// Create the connection for the naming service
	serviceName := ServiceName{"localhost", "1234"}
	// Create the parameters of a service for publication
	fibonacciRecursive := Bind{"FibonacciRecursive",
		createProxy(Fibonacci{})}

	// Inserting a service and starting
	serviceName.Bind("bind", &fibonacciRecursive)
}
