
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.String.valueOf;

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
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    static boolean isOpposite(String input) {
        String[][] opposite = {{"-EVEN", " EVEN", " DUCK", " SUNNY", " SAD", " EVEN", " ODD", " BUZZ", " DUCK", " PALINDROMIC",
                " GAPFUL", " SPY", " SQUARE", " SUNNY", " JUMPING", " HAPPY", " SAD"},
                {"-ODD", " ODD", " SPY", " SQUARE", " HAPPY", "-EVEN", "-ODD", "-BUZZ", "-DUCK", "-PALINDROMIC", "-GAPFUL",
                        "-SPY", "-SQUARE", "-SUNNY", "-JUMPING", "-HAPPY", "-SAD"}};
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
        String properties = " EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD, " +
                "-EVEN, -ODD, -BUZZ, -DUCK, -PALINDROMIC, -GAPFUL, -SPY, -SQUARE, -SUNNY, -JUMPING, -HAPPY, -SAD";
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
            if (input.contains("-SQUARE") && isSquare(n)) continue;
            if (input.contains("-SUNNY") && isSunny(n)) continue;
            if (input.contains("-SPY") && isSpy(n)) continue;
            if (input.contains("-GAPFUL") && isGapful(n)) continue;
            if (input.contains("-PALINDROMIC") && isPalindromic(n)) continue;
            if (input.contains("-DUCK") && isDuck(n)) continue;
            if (input.contains("-BUZZ") && isBuzz(n)) continue;
            if (input.contains("-EVEN") && isEven(n)) continue;
            if (input.contains("-ODD") && isOdd(n)) continue;
            if (input.contains("-JUMPING") && isJumping(n)) continue;
            if (input.contains("-HAPPY") && isHappy(n)) continue;
            if (input.contains("-SAD") && isSad(n)) continue;
            if (input.contains(" SQUARE") && !isSquare(n)) continue;
            if (input.contains(" SUNNY") && !isSunny(n)) continue;
            if (input.contains(" SPY") && !isSpy(n)) continue;
            if (input.contains(" GAPFUL") && !isGapful(n)) continue;
            if (input.contains(" PALINDROMIC") && !isPalindromic(n)) continue;
            if (input.contains(" DUCK") && !isDuck(n)) continue;
            if (input.contains(" BUZZ") && !isBuzz(n)) continue;
            if (input.contains(" EVEN") && !isEven(n)) continue;
            if (input.contains(" ODD") && !isOdd(n)) continue;
            if (input.contains(" JUMPING") && !isJumping(n)) continue;
            if (input.contains(" HAPPY") && !isHappy(n)) continue;
            if (input.contains(" SAD") && !isSad(n)) continue;
            results(n, 1);
            i++;
        }
    }

    static void results(long n1, int n2) {
        long n = n1;
        for (int i = 0; i < n2; i++, n++) {
            StringBuilder output = new StringBuilder(valueOf(n));
            output.append(" is ");
            output.append(isEven(n) ? "even, " : "");
            output.append(isOdd(n) ? "odd, " : "");
            output.append(isBuzz(n) ? "buzz, " : "");
            output.append(isDuck(n) ? "duck, " : "");
            output.append(isPalindromic(n) ? "palindromic, " : "");
            output.append(isGapful(n) ? "gapful, " : "");
            output.append(isSpy(n) ? "spy, " : "");
            output.append(isSquare(n) ? "square, " : "");
            output.append(isSunny(n) ? "sunny, " : "");
            output.append(isJumping(n) ? "jumping, " : "");
            output.append(isHappy(n) ? "happy, " : "");
            output.append(isSad(n) ? "sad, " : "");
            output.delete(output.length() - 2, output.length());
            System.out.println(output);
        }
    }

    static void results(long n) {
        NumberFormat format = NumberFormat.getInstance(new Locale("en", "US"));
        System.out.println("Properties of " + format.format(n));
        System.out.println("        buzz: " + isBuzz(n));
        System.out.println("        duck: " + isDuck(n));
        System.out.println(" palindromic: " + isPalindromic(n));
        System.out.println("      gapful: " + isGapful(n));
        System.out.println("         spy: " + isSpy(n));
        System.out.println("      square: " + isSquare(n));
        System.out.println("       sunny: " + isSunny(n));
        System.out.println("     jumping: " + isJumping(n));
        System.out.println("        even: " + isEven(n));
        System.out.println("         odd: " + isOdd(n));
        System.out.println("       happy: " + isHappy(n));
        System.out.println("         sad: " + isSad(n));
    }

    static boolean isBuzz(long n) {
        return (n % 10 == 7 || n % 7 == 0);
    }

    static boolean isDuck(long n) {
        return String.valueOf(n).contains("0");
    }

    static boolean isPalindromic(long n) {
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

    static boolean isGapful(long n) {
        String[] digits = valueOf(n).split("");
        return n  > 99 && n % Integer.parseInt(digits[0] + digits[digits.length - 1]) == 0;
    }

    static boolean isEven(long n) {
        return n % 2 == 0;
    }

    static boolean isOdd(long n) {
        return n % 2 != 0;
    }

    static boolean isSpy(long n) {
        String[] digits = String.valueOf(n).split("");
        int Sum = 0;
        int Prod = 1;
        for (String digit : digits) {
            Sum += Integer.parseInt(digit);
            Prod *= Integer.parseInt(digit);
        }
        return Sum == Prod;
    }

    static boolean isSunny(long n) {
        return (n + 1) % Math.sqrt(n + 1) == 0;
    }

    static boolean isSquare(long n) {
        return n % Math.sqrt(n) == 0;
    }

    static boolean isJumping(long n) {
        String[] digits = String.valueOf(n).split("");
        for (int i = 0; i < digits.length - 1; i++) {
            if (Math.abs(Long.parseLong(digits[i]) - Long.parseLong(digits[i + 1])) != 1) {
                return false;
            }
        }
        return true;
    }

    static boolean isHappy(long n) {
        int sum = 0;
        long number = n;
        do {
            String[] digits = String.valueOf(number).split("");
            for (String digit : digits) {
                sum += Integer.parseInt(digit) * Integer.parseInt(digit);
            }
            if (sum == 1) return true;
            number = sum;
            sum = 0;
        } while (!(number == n || number == 4));
        return false;
    }

    static boolean isSad(long n) {
        int sum = 0;
        long number = n;
        do {
            String[] digits = String.valueOf(number).split("");
            for (String digit : digits) {
                sum += Integer.parseInt(digit) * Integer.parseInt(digit);
            }
            if (sum == 1) return false;
            number = sum;
            sum = 0;
        } while (!(number == n || number == 4));
        return true;
    }
}