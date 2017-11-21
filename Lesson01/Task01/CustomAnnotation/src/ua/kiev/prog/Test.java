package ua.kiev.prog;


public class Test {

    @Sum(a=5, b=3)
    public static void printSum(int a, int b) {
        int sum = a + b;
        System.out.print("a+b=" + sum);
    }
}
