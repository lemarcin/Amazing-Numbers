
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
            long n1 = 0;
            int n2 = 0;
            System.out.print("\nEnter a request: ");
            String input = scanner.nextLine().toUpperCase();
            System.out.println();
            if (input.equals("0")) {
                break;
            } else if (input.length() == 0) {
                instructions();
                continue;
            } else if (input.split(" ")[0].matches("[1-9][0-9]*")) {
                n1 = Long.parseLong(input.split(" ")[0]);
            } else {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }
            if (input.split(" ").length == 1) {
                results(n1);
                continue;
            }
            if (input.split(" ").length > 1) {
                if (input.split(" ")[1].matches("[1-9][0-9]*")) {
                    n2 = Integer.parseInt(input.split(" ")[1]);
                } else {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
            }
            if (input.split(" ").length == 2) {
                results(n1, n2);
                continue;
            }
            if (input.split(" ").length > 2) {
                if (isWrong(input)) {
                    continue;
                }
            }
            if (input.split(" ").length > 3) {
                if (isOpposite(input)) {
                    continue;
                }
            }
            if (input.split(" ").length > 2) {
                results(n1, n2, input);
            }
        } while (true);
        System.out.println("Goodbye!");
    }

    static void instructions() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- two natural numbers and two properties to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    static boolean isOpposite(String input) {
        String[][] opposite = {{"EVEN", "DUCK", "SUNNY"}, {"ODD", "SPY", "SQUARE"}};
        for (int i = 0; i < opposite[0].length; i++) {
            if (input.contains(opposite[0][i]) && input.contains(opposite[1][i])) {
                System.out.println("The request contains mutually exclusive properties: [" +
                        opposite[0][i] + ", " + opposite[1][i] + "]\n" +
                        "There are no numbers with these properties.");
                return true;
            }
        }
        return false;
    }

    static boolean isWrong(String input) {
        String properties = "EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY";
        StringBuilder output = new StringBuilder("");
        int result = 0;
        for (int i = 2; i < input.split(" ").length; i++) {
            if (!properties.contains(input.split(" ")[i])) {
                result += 1;
                output.append(input.split(" ")[i] + ", ");
            }
        }
        if (result == 1) {
            output.delete(output.length() - 2, output.length());
            System.out.println("The property [" + output + "] is wrong.\n" +
                    "Available properties: " + properties);
        } else if (result > 1) {
            output.delete(output.length() - 2, output.length());
            System.out.println("The properties [" + output + "] are wrong.\n" +
                    "Available properties: " + properties);
        }
        return result > 0;
    }

    static void results(long n1, int n2, String input) {
        long n = n1;
        for (int i = 0; i < n2; n++) {
            if (input.contains("SQUARE")) {
                if (!square(n)) continue;
            }
            if (input.contains("SUNNY")) {
                if (!sunny(n)) continue;
            }
            if (input.contains("SPY")) {
                if (!spy(n)) continue;
            }
            if (input.contains("GAPFUL")) {
                if (!gapful(n)) continue;
            }
            if (input.contains("PALINDROMIC")) {
                if (!palindromic(n)) continue;
            }
            if (input.contains("DUCK")) {
                if (!duck(n)) continue;
            }
            if (input.contains("BUZZ")) {
                if (!buzz(n)) continue;
            }
            if (input.contains("EVEN")) {
                if (!even(n)) continue;
            }
            if (input.contains("ODD")) {
                if (!odd(n)) continue;
            }
            results(n, 1);
            i++;
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
            if (square(n)) {
                System.out.print(j++ > 0 ? ", square": "square");
            }
            if (sunny(n)) {
                System.out.print(j++ > 0 ? ", sunny": "sunny");
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
        System.out.println("      square: " + square(n));
        System.out.println("       sunny: " + sunny(n));
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

    static boolean sunny(long n) {
        int root = (int) Math.sqrt(n + 1);
        return (long) root * root == n + 1;
    }

    static boolean square(long n) {
        int root = (int) Math.sqrt(n);
        return (long) root * root == n;
    }
}