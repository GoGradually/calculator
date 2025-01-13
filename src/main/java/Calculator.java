import java.io.StringReader;
import java.util.Scanner;

public class Calculator {
    int add(int i, int j) {
        return i + j;
    }

    int subtract(int i, int j) {
        return i - j;
    }

    int multiply(int i, int j) {
        return i * j;
    }

    int divide(int i, int j) {
        return i / j;
    }

    int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        String[] split = s.split(" ");
        for (int i = 0; i < split.length; i++) {
            if (split[i].isEmpty()) {
                split[i] = "0";
            }
        }
        if (split.length % 2 == 0) {
            throw new IllegalArgumentException("Invalid expression");
        }
        split[0] = transform(split[0]);
        for (int i = 1; i < split.length; i += 2) {
            split[i+1] = transform(split[i+1]);
            split[i+1] = switch (split[i]) {
                case "+" -> String.valueOf(add(Integer.parseInt(split[i - 1]), Integer.parseInt(split[i + 1])));
                case "-" -> String.valueOf(subtract(Integer.parseInt(split[i - 1]), Integer.parseInt(split[i + 1])));
                case "*" -> String.valueOf(multiply(Integer.parseInt(split[i - 1]), Integer.parseInt(split[i + 1])));
                case "/" -> String.valueOf(divide(Integer.parseInt(split[i - 1]), Integer.parseInt(split[i + 1])));
                default -> throw new IllegalArgumentException("Invalid expression");
            };
        }
        return Integer.parseInt(split[split.length-1]);
    }

    private String transform(String s) {
        String[] split = s.split("[,:]");
        int sum = 0;
        for (int i = 0; i < split.length; i++) {
            sum += Integer.parseInt(split[i]);
        }
        return String.valueOf(sum);
    }

    public static void main(String[] args) {
        Calculator cal = new Calculator();
        System.out.println(cal.add(3, 4));
        System.out.println(cal.subtract(5, 4));
        System.out.println(cal.multiply(2, 6));
        System.out.println(cal.divide(8, 4));
    }

}