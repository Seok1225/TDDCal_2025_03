package org.example;

public class Calc {

    public static int run(String exp) {

        exp = exp.replace("- ", "+ -");

        String[] bits = exp.split(" \\+ ");

        int sum = 0;

        for (String bit : bits) {
            if (bit.contains("*")) {
                String[] factors = bit.split(" \\* ");
                int product = 1;
                for (String factor : factors) {
                    product *= Integer.parseInt(factor);
                }
                sum += product;
            } else {
                sum += Integer.parseInt(bit);
            }
        }

        return sum;
    }
}