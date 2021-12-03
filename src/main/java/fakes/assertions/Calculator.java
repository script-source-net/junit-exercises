package fakes.assertions;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zahl a eingeben: ");
        double a = sc.nextDouble();
        System.out.println("Zahl b eigeben: ");
        double b = sc.nextDouble();
        addition(a,b);
    }
    public static double addition(double a, double b){
        return a + b;
    }

    public static double multiply(double a, double b) {
        try {
            return a * b;
        } catch (ArithmeticException e){
            return 0;
        }
    }
}
