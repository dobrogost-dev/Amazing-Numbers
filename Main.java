package numbers;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!\n");
        showInfo();
        while (process());
        goodbye();
    }
    public static void showPriorities(long number) {
        System.out.print("\n");
        System.out.printf("Properties of %d\n",number);
        System.out.println("\t\teven: " + Number.isEven(number));
        System.out.println("\t\t odd: " + !Number.isEven(number));
        System.out.println("\t\tbuzz: " + Number.isBuzz(number));
        System.out.println("\t\tduck: " + Number.isDuck(number));
        System.out.println("\tpalindromic: " + Number.isPalindromic(number));
        System.out.println("\t\tgapful: " + Number.isGapful(number));
        System.out.println("\t\tspy: " + Number.isSpy(number));
        System.out.println("\t\tsquare: " + Number.isSquare(number));
        System.out.println("\t\tsunny: " + Number.isSunny(number));
        System.out.println("\t\tjumping: " + Number.isJumping(number));
        System.out.println("\t\thappy: " + Number.isHappy(number));
        System.out.println("\t\tsad: " + !Number.isHappy(number));

        System.out.print("\n");
    }
    public static void showInfo() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }
    public static boolean process() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter a request: ");
        String[] nums = scanner.nextLine().toUpperCase().split(" ");
        long number = Long.parseLong(nums[0]);
        if (number == 0) {
            return false;
        } else if (number < 0) {
            System.out.println("The first parameter should be a natural number or zero.\n");
        }
        switch (nums.length) {
            case 1:
                showPriorities(number);
                break;
            case 2:
                processNumber(number,Integer.parseInt(nums[1]));
                break;
            default:
                processNumber(number,Integer.parseInt(nums[1]), Arrays.copyOfRange(nums,2,nums.length));
                break;
        }
        return true;
    }
    public static String checkNumber(long number) {
        StringBuilder str = new StringBuilder("");
        if (Number.isBuzz(number)) {
            str.append("buzz, ");
        }
        if (Number.isDuck(number)) {
            str.append("duck, ");
        }
        if (Number.isPalindromic(number)) {
            str.append("palindromic, ");
        }
        if (Number.isGapful(number)) {
            str.append("gapful, ");
        }
        if (Number.isSpy(number)) {
            str.append("spy, ");
        }
        if (Number.isJumping(number)) {
            str.append("jumping, ");
        }
        if (Number.isSquare(number)) {
            str.append("square, ");
        } else if (Number.isSunny(number)) {
            str.append("sunny, ");
        }
        if (Number.isHappy(number)) {
            str.append("happy, ");
        } else {
            str.append("sad, ");
        }
        if (Number.isEven(number)) {
            str.append("even, ");
        } else {
            str.append("odd, ");
        }
        return str.toString();
    }
    public static void processNumber(long number, int n) {
        if (n <= 0) {
            System.out.println("The second parameter should be a natural number");
        } else {
            for (long i = number; i < number+n; i++) {
                System.out.println(i + " is " + checkNumber(i));
            }
        }
    }
    public static void processNumber(long number, int n, String[] str) {

        if (n <= 0) {
            System.out.println("The second parameter should be a natural number");
            return;
        }
        String[] strWrong = removeAllEmpty(Number.getWrongParameters(str));
        System.out.println(String.join(",", strWrong));
        if (strWrong.length > 0) {
            if (strWrong.length == 1) {
                System.out.println("The property [" + strWrong[0] + "] is wrong.");
            } else {
                System.out.println("The properties [" + String.join(",", strWrong) + "] are wrong.");
            }
            Number.showAvailableProperties();
            return;
        }
        String[] collidingStr = Number.checkCollision(str);
        if (collidingStr != null) {
            System.out.println("The request contains mutually exclusive properties: [" + String.join(",", collidingStr) + "]\n" +
                    "There are no numbers with these properties.");
            return;
        }
        for (long i = number; n > 0; i++) {
            boolean isCorrect = true;
            for (String s : str) {
                if (s.charAt(0) == '-') {
                    if (Number.containTypes(i,s.substring(1))) {
                        isCorrect = false;
                        break;
                    }
                } else if (Number.containTypes(i,s)) {
                } else {
                    isCorrect = false;
                    break;
                }
            }
            if (isCorrect) {
                System.out.println(i + " is " + checkNumber(i));
                n--;
            }
        }
    }
    public static String[] removeAllEmpty(String[] arr) {
        String[] result = Arrays.stream(arr)
                .filter(Objects::nonNull)
                .toArray(String[]::new);
        return result;
    }
    public static void goodbye() {
        System.out.println("Goodbye!");
    }
}
