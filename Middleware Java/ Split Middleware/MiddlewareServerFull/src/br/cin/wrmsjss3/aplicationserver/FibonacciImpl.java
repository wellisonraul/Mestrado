package br.cin.wrmsjss3.aplicationserver;

public class FibonacciImpl implements IFibonacci {

    @Override
    public long recursiveFibonacci(long numberFibonacci) {
        long returnFibonacci = calculatorFibonacci(numberFibonacci);
        return returnFibonacci;
    }

    public long calculatorFibonacci(long numberFibonacci) {
        if (numberFibonacci == 1 || numberFibonacci == 2) return 1;
        else
            return calculatorFibonacci(numberFibonacci - 1)
                    + calculatorFibonacci(numberFibonacci - 2);
    }
}
