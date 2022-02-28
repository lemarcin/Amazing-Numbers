
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!\n");
        instructions();
        execute();
    }

    static void execute() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("\nEnter a request: ");
            String input = scanner.nextLine();
            String input1 = null;
            String input2 = null;
            long n1;
            int n2;
            if (input.matches("[0-9]+ \\S*")) {
                if (input.matches("[0-9]+ [0-9]+")) {
                    input1 = input.split(" ")[0];
                    input2 = input.split(" ")[1];
                    n1 = Long.parseLong(input1);
                    n2 = Integer.parseInt(input2);

                    results(n1, n2);
                } else {
                    System.out.println("\nThe second parameter should be a natural number.");
                }


            } else if (input.matches("[0-9]+")) {
                n1 = Long.parseLong(input);
                if (n1 == 0) {
                    break;
                }
                results(n1, input);
            } else if (input.length() == 0) {
                instructions();
            }else {
                System.out.println("\nThe first parameter should be a natural number or zero.");
            }
        }
        while (true);
        System.out.println("\nGoodbye!");
    }

    static void instructions() {
        System.out.println("\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    static void results(long n1, int n2) {
        long n = n1;
        for (int i = 0; i < n2; i++, n++) {
            int j = 0;
            System.out.print(n + " is ");
            if (buzz(n)) {
                System.out.print("buzz");
                j++;
            }
            if (duck(n)) {
                System.out.print(j++ > 0 ? ", duck": "duck");
            }
            if (palindromic(n)) {
                System.out.print(j++ > 0 ? ", palindromic": "palindromic");
            }
            if (gapful(n)) {
                System.out.print(j++ > 0 ? ", gapful": "gapful");
            }
            if (even(n)) {
                System.out.print(j++ > 0 ? ", even": "even");
            }
            if (odd(n)) {
                System.out.print(j > 0 ? ", odd": "odd");
            }
            System.out.println("");
        }
    }

    static void results(long n, String input) {
        NumberFormat format = NumberFormat.getInstance(new Locale("en", "US"));
        System.out.println("Properties of " + format.format(n));
        System.out.println("        buzz: " + buzz(n));
        System.out.println("        duck: " + duck(n));
        System.out.println(" palindromic: " + palindromic(n));
        System.out.println("      gapful: " + gapful(n));
        System.out.println("        even: " + even(n));
        System.out.println("         odd: " + odd(n));
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

    static boolean palindromic(long n) {
        String str = Long.toString(n);
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    static boolean gapful(long n) {
        if (n > 99) {
            long firstDigit = 0;
            long number = n;
            while(number != 0) {
                firstDigit = number % 10;
                number /= 10;
            }
            return n % (firstDigit * 10 + n % 10) == 0;
        }
            return false;
    }

    static boolean even(long n) {
        return n % 2 == 0;
    }

    static boolean odd(long n) {
        return n % 2 != 0;
    }
}
