package org.example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Calc {

    public static int run(String exp) {
        // 가장 바깥쪽 괄호를 제거
        exp = stripOuterBrackets(exp.trim());

        // 단일 숫자라면 그대로 반환
        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }

        // 음수 처리: "-"로 시작하는 경우
        if (exp.startsWith("-")) {
            return -run(exp.substring(1));
        }

        // 괄호 연산 먼저 수행
        if (exp.contains("(")) {
            int openIdx = exp.indexOf("(");
            int closeIdx = findMatchingBracket(exp, openIdx);

            String beforeBracket = exp.substring(0, openIdx).trim();
            String insideBracket = exp.substring(openIdx + 1, closeIdx).trim();
            String afterBracket = exp.substring(closeIdx + 1).trim();

            int result = run(insideBracket);

            if (!beforeBracket.isEmpty()) {
                return run(beforeBracket + " " + result);
            }
            if (!afterBracket.isEmpty()) {
                return run(result + " " + afterBracket);
            }

            return result;
        }

        // 연산자 우선순위 적용 (곱셈을 먼저 계산)
        return evaluateExpression(exp);
    }

    private static int evaluateExpression(String exp) {
        // 수식을 공백 단위로 나누기
        String[] tokens = exp.split(" ");
        Queue<String> queue = new LinkedList<>();

        // 곱셈 먼저 처리
        int result = Integer.parseInt(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            int next = Integer.parseInt(tokens[i + 1]);

            if (operator.equals("*")) {
                result *= next;
            } else {
                queue.add(String.valueOf(result));
                queue.add(operator);
                result = next;
            }
        }
        queue.add(String.valueOf(result));

        // 덧셈과 뺄셈 처리
        int finalResult = Integer.parseInt(queue.poll());
        while (!queue.isEmpty()) {
            String operator = queue.poll();
            int next = Integer.parseInt(queue.poll());

            if (operator.equals("+")) {
                finalResult += next;
            } else if (operator.equals("-")) {
                finalResult -= next;
            }
        }

        return finalResult;
    }

    // 가장 바깥쪽 괄호를 제거하는 메서드
    private static String stripOuterBrackets(String exp) {
        while (exp.startsWith("(") && exp.endsWith(")")) {
            exp = exp.substring(1, exp.length() - 1).trim();
        }
        return exp;
    }

    // 괄호의 짝을 찾아 반환하는 메서드
    private static int findMatchingBracket(String exp, int openIdx) {
        int count = 0;
        for (int i = openIdx; i < exp.length(); i++) {
            if (exp.charAt(i) == '(') count++;
            if (exp.charAt(i) == ')') count--;
            if (count == 0) return i;
        }
        return -1;
    }
}