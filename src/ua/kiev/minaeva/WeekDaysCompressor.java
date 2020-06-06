package ua.kiev.minaeva;

import java.util.Scanner;
import java.util.stream.Collectors;

public class WeekDaysCompressor {

    private static String input;
    private static String result;

    public static void main(String[] args) {
        sortAndUnifyInput();
        compressResult();
        printResult();
    }

    private static void sortAndUnifyInput() {
        Scanner in = new Scanner(System.in);
        String rawInput = in.next();

        if (rawInput.length() == 0) {
            throw new RuntimeException("Empty line");
        }

        input = rawInput.chars()
                .mapToObj(c -> (char) c)
                .sorted()
                .distinct()
                .map(c -> String.valueOf(c))
                .collect(Collectors.joining(""));
    }

    private static void compressResult() {
        result = String.valueOf(input.charAt(0));

        for (int currentStep = 1; currentStep < input.length(); currentStep++) {
            if (isConsequent(currentStep)) {
                result += "-" + input.charAt(currentStep);
                if ((currentStep > 1) && (isSecondConsequent(currentStep))) {
                    result = result.substring(0, result.length() - 3) + input.charAt(currentStep);
                }
            } else {
                result += "," + input.charAt(currentStep);
            }
        }
    }

    private static boolean isConsequent(int i) {
        if (input.charAt(i) - input.charAt(i - 1) == 1) {
            return true;
        }
        return false;
    }

    private static boolean isSecondConsequent(int i) {
        if (input.charAt(i - 1) - input.charAt(i - 2) == 1) {
            return true;
        } else {
            return false;
        }
    }

    private static void printResult() {
        System.out.println(result);
    }

}
