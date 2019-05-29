package main

import (
	"time"
	"fmt"
	"os"
	"bufio"
)

type Fibonacci struct {
	Number1 float64
}

func main() {
	serviceName := ServiceName{"localhost", "1234"}
	ClientProxy := serviceName.Lookup("lookup", &Lookup{"FibonacciRecursive"})

	fmt.Println()

	var textos string
	start := time.Now()
	for j := 0; j < req; j ++ {
		ClientProxyCall("Mult", ClientProxy, &Fibonacci{5})
		time.Sleep(1 * time.Millisecond)
	}
	elapsed := time.Since(start)
	textos = append(textos, elapsed.String())

	escreverTexto(textos, "teste1.txt")
}

// Funcao que escreve um texto no arquivo e retorna um erro caso tenha algum problema
func escreverTexto(linhas string, caminhoDoArquivo string) error {
	// Cria o arquivo de texto
	arquivo, err := os.OpenFile(caminhoDoArquivo, os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
	// Caso tenha encontrado algum erro retornar ele
	if err != nil {
		return err
	}
	// Garante que o arquivo sera fechado apos o uso
	defer arquivo.Close()

	// Cria um escritor responsavel por escrever cada linha do slice no arquivo de texto
	escritor := bufio.NewWriter(arquivo)
	for _, linha := range linhas {
		fmt.Fprintln(escritor, linha)
	}

	// Caso a funcao flush retorne um erro ele sera retornado aqui tambem
	return escritor.Flush()
}
