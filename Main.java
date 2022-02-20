import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println("Enter a natural number:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.matches("[1-9][0-9]*")) {
            int n = Integer.parseInt(input);
            if (n % 2 == 0) {
                System.out.println("This number is Even.");
            } else {
                System.out.println("This number is Odd.");
            }
            if (n % 10 == 7 || n % 7 == 0) {
                System.out.println("It is a Buzz number.\nExplanation:");
                if (n % 10 == 7 && n % 7 == 0) {
                    System.out.println(n + " is divisible by 7 and ends with 7.");
                } else if (n % 7 == 0) {
                    System.out.println(n + " is divisible by 7.");
                } else if (n % 10 == 7) {
                    System.out.println(n + " ends with 7.");
                }
            } else {
                System.out.println("It is not a Buzz number.\nExplanation:");
                System.out.println(n + " is neither divisible by 7 nor does it end with 7.");
            }
        } else {
            System.out.println("This number is not natural!");
        }
    }
}
