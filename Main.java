
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println("Enter a natural number:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int n;
        if (input.matches("[1-9][0-9]*")) {
            n = Integer.parseInt(input);
            System.out.println("Properties of " + n);
            System.out.println("        even: " + even(n));
            System.out.println("         odd: " + odd(n));
            System.out.println("        buzz: " + buzz(n));
            System.out.println("        duck: " + duck(n));

        } else {
            System.out.println("This number is not natural!");
        }
    }

    static boolean even(int n) {
        return n % 2 == 0;
    }

    static boolean odd(int n) {
        return n % 2 != 0;
    }

    static boolean buzz(int n) {
        return (n % 10 == 7 || n % 7 == 0);
    }

    static boolean duck(int n) {
        for (int rest = n; rest > 0; rest /= 10) {
            int digit = rest % 10;
            if (digit == 0) {
                return true;
            }
        }
        return false;
    }
}
