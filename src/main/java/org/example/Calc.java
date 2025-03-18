package org.example;

public class Calc {

    public static int run(String exp) {

        exp = exp.replace("- ", "+ -");

        String[] bits = exp.split(" \\+ ");

        int sum = 0;

        for (String bit : bits) {
            sum += Integer.parseInt(bit);
        }

        return sum;
    }
}