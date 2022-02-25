
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter 0 to exit.");
        execute();

    }

    static void execute() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("\nEnter a request: ");
            String input = scanner.nextLine();
            long n;
            if (input.matches("[0-9]*")) {
                n = Long.parseLong(input);
                if (n == 0) {
                    break;
                }
                NumberFormat format = NumberFormat.getInstance(new Locale("en", "US"));
                System.out.println("Properties of " + format.format(n));;
                System.out.println("\n        even: " + even(n));
                System.out.println("         odd: " + odd(n));
                System.out.println("        buzz: " + buzz(n));
                System.out.println("        duck: " + duck(n));
                System.out.println(" palindromic: " + palindromic(input));

            } else {
                System.out.println("\nThe first parameter should be a natural number or zero.");
            }
        } while (true);
        System.out.println("\nGoodbye!");
    }

    static boolean even(long n) {
        return n % 2 == 0;
    }

    static boolean odd(long n) {
        return n % 2 != 0;
    }

    static boolean buzz(long n) {
        return (n % 10 == 7 || n % 7 == 0);
    }

    static boolean duck(long n) {
        long n2 = n;
        while (n2 > 0) {
            if (n2 % 10 == 0)
                return true;
            n2 /= 10;
        }
        return false;
    }

    static boolean palindromic(String input) {
        return input.equals(new StringBuilder(input).reverse().toString());
    }
}