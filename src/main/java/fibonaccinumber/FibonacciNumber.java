package main.java.fibonaccinumber;

import java.util.Scanner;

public class FibonacciNumber {

    public static void main(String[] args) {
        System.out.print("Ingrese número para calcular la sucesión de Fibonacci: ");
        int numberIn = new Scanner(System.in).nextInt();
        printFibonacciNumber(0, 1, numberIn);
    }

    static void printFibonacciNumber(int firstNumber, int secondNumber, int numberMax) {
        if (numberMax == 0) System.out.println(firstNumber);
        else if (secondNumber <= numberMax) {
            if (firstNumber == 0) {
                System.out.println(firstNumber);
            }
            System.out.println(secondNumber);
            printFibonacciNumber(secondNumber, firstNumber + secondNumber, numberMax);
        }
    }
}