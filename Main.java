
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        instructions();
        execute();
    }

    static void execute() {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        do {
            long n1 = 0;
            int n2 = 0;
            System.out.print("\nEnter a request: ");
            String input = scanner.nextLine();
            System.out.println();
            switch (input.split(" ").length) {
                case 1:
                    if (input.length() == 0) {
                        instructions();
                        break;
                    } else if (input.matches("[0-9]+")) {
                        if (input.equals("0")) {
                            run = false;
                            break;
                        }
                        results(Long.parseLong(input));
                    } else {
                        System.out.println("The first parameter should be a natural number or zero.");
                    }
                    break;
                case 2:
                    if (input.split(" ")[0].matches("[1-9][0-9]*")) {
                        n1 = Long.parseLong(input.split(" ")[0]);
                    } else {
                        System.out.println("The first parameter should be a natural number or zero.");
                    }
                    if (input.split(" ")[1].matches("[1-9][0-9]*")) {
                        n2 = Integer.parseInt(input.split(" ")[1]);
                        results(n1, n2);
                    } else {
                        System.out.println("The second parameter should be a natural number.");
                    }
                    break;
                case 3:
                    if (input.split(" ")[0].matches("[1-9][0-9]*")) {
                        n1 = Long.parseLong(input.split(" ")[0]);
                    } else {
                        System.out.println("The first parameter should be a natural number or zero.");
                    }
                    if (input.split(" ")[1].matches("[1-9][0-9]*")) {
                        n2 = Integer.parseInt(input.split(" ")[1]);
                    } else {
                        System.out.println("The second parameter should be a natural number.");
                    }
                    String input3 = input.split(" ")[2].toUpperCase();
                    switch (input3) {
                        case "BUZZ":
                        case "DUCK":
                        case "PALINDROMIC":
                        case "GAPFUL":
                        case "EVEN":
                        case "ODD":
                        case "SPY":
                            results(n1, n2, input3);
                            break;
                        default:
                            System.out.println("The property [" + input3 + "] is wrong.\n" +
                                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY]");
                    }
                    break;
                default:
                    break;
            };
        } while (run);
        System.out.println("Goodbye!");
    }

    static void instructions() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    static void results(long n1, int n2, String input3) {
        long n = n1;
        for (int i = 0; i < n2; n++) {
            switch (input3) {
                case "BUZZ":
                    if (buzz(n)) {
                        results(n, 1);
                        i++;
                    }
                    break;
                case "DUCK":
                    if (duck(n)) {
                        results(n, 1);
                        i++;
                    }
                    break;
                case "PALINDROMIC":
                    if (palindromic(n)) {
                        results(n, 1);
                        i++;
                    }
                    break;
                case "GAPFUL":
                    if (gapful(n)) {
                        results(n, 1);
                        i++;
                    }
                    break;
                case "EVEN":
                    if (even(n)) {
                        results(n, 1);
                        i++;
                    }
                    break;
                case "ODD":
                    if (odd(n)) {
                        results(n, 1);
                        i++;
                    }
                    break;
                case "SPY":
                    if (spy(n)) {
                        results(n, 1);
                        i++;
                    }
                    break;
            }
        }
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
            if (spy(n)) {
                System.out.print(j++ > 0 ? ", spy": "spy");
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

    static void results(long n) {
        NumberFormat format = NumberFormat.getInstance(new Locale("en", "US"));
        System.out.println("Properties of " + format.format(n));
        System.out.println("        buzz: " + buzz(n));
        System.out.println("        duck: " + duck(n));
        System.out.println(" palindromic: " + palindromic(n));
        System.out.println("      gapful: " + gapful(n));
        System.out.println("         spy: " + spy(n));
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

    static boolean spy(long n) {
        String str = Long.toString(n);
        long result1 = 0;
        long result2 = 1;
        for (int i = 0; i < str.length(); i++) {
            result1 += Character.getNumericValue(str.charAt(i));
            result2 *= Character.getNumericValue(str.charAt(i));
        }
        return result1 == result2;
    }
}