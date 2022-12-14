package numbers;

import java.util.Arrays;

public class Number {
    public enum Property {
        BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY, JUMPING, HAPPY, SAD

    }
    public static boolean isEven(long number) {
        return number%2 == 0;
    }
    public static boolean isBuzz(long number) {
        return number%7 == 0 || number%10 == 7;
    }
    public static boolean isDuck(long number) {
        while (number != 0) {
            if (number%10 == 0) {
                return true;
            }
            number /= 10;
        }
        return false;
    }

    public static boolean isPalindromic(long number) {
        long remainder;
        long summary = 0;
        long temporary;

        temporary = number;
        while (number > 0) {
            remainder = number % 10;
            summary = summary * 10 + remainder;
            number /= 10;
        }

        return temporary == summary;
    }
    public static boolean isGapful(long number) {
        if (number < 100) {
            return false;
        }
        long last = number % 10;
        long first = number;
        while (first > 9) {
            first /= 10;
        }
        return number % (first*10+last) == 0;
    }
    public static boolean isSpy(long number) {
        int sumOfDigits = 0;
        int productOfDigits = 1;
        while (number > 0) {
            long remainder = number%10;
            number /= 10;
            sumOfDigits += remainder;
            productOfDigits *= remainder;
        }
        return sumOfDigits == productOfDigits;
    }
    public static boolean isSunny(long number) {
        return isSquare(number+1);
    }
    public static boolean isSquare(long number) {
        return Math.sqrt(number)%1.0 == 0;
    }
    public static boolean isJumping(long number) {
        long last = 0;
        while (number > 0) {
           last = number % 10;
           number /= 10;
           if ((Math.abs(number % 10 - last) == 0 || Math.abs(number % 10 - last) > 1)  && number != 0) {
               return false;
           }
        }
        return true;
    }
    public static boolean isHappy(long number) {
        long newNumber = 0;
        long[] numbers = {number};
        while (number > 0) {
            long last = number % 10;
            number /= 10;
            newNumber += last * last;
        }
         if (newNumber == 1) {
            return true;
        } else if (newNumber == numbers[0]) {
             return false;
         } else {
            return isHappy(newNumber, numbers);
        }
    }
    public static boolean isHappy(long number, long[] numbers) {
        long newNumber = 0;
        while (number > 0) {
            long last = number % 10;
            number /= 10;
            newNumber += last * last;
        }
        if (checkIfArrayContains(numbers,newNumber)) {
            return false;
        } else if (newNumber == 1) {
            return true;
        } else {
            long[] newNumbers = new long[numbers.length+1];
            System.arraycopy(numbers, 0, newNumbers, 0, numbers.length);
            newNumbers[numbers.length] = newNumber;
            return isHappy(newNumber, newNumbers);
        }
    }
    public static boolean containTypes(long number, String str) {
        Property property = Property.valueOf(str.toUpperCase());
        switch(property) {
            case BUZZ:
                if(Number.isBuzz(number)) return true;
                break;
            case DUCK: if (Number.isDuck(number)) return true;
                break;
            case PALINDROMIC: if (Number.isPalindromic(number)) return true;
                break;
            case GAPFUL: if (Number.isGapful(number)) return true;
                break;
            case SPY: if (Number.isSpy(number)) return true;
                break;
            case EVEN: if (Number.isEven(number)) return true;
                break;
            case ODD: if (!Number.isEven(number)) return true;
                break;
            case SUNNY: if (Number.isSunny(number)) return true;
                break;
            case SQUARE: if (Number.isSquare(number)) return true;
                break;
            case JUMPING: if (Number.isJumping(number)) return true;
                break;
            case HAPPY: if (Number.isHappy(number)) return true;
                break;
            case SAD: if (!Number.isHappy(number)) return true;
                break;
            default:
                return false;
        }
        return false;
    }

    public static String[] getWrongParameters(String[] str) {
        String[] str2 = new String[str.length];
        int count = 0;
        for (String s : str) {
            boolean isCorrect = false;
            for (Property p : Property.values()) {
                if (s.equals(p.toString()) || s.equals("-" + p.toString())) {
                    isCorrect = true;
                }
            }
            if (!isCorrect) {
                str2[count] = s;
                count++;
            }
        }
        return str2;
    }
    public static String[] checkCollision(String[] str) {
        for (String s1 : str) {
            for (String s2 : str) {
                if (areNotColliding(s1,s2) != null) {
                    return areNotColliding(s1,s2);
                }
            }
        }
        return null;
    }
    public static String[] areNotColliding(String str, String str2) {
        if ((str.equals("EVEN") && str2.equals("ODD")) ||
                (str.equals("ODD") && str2.equals("EVEN"))) {
                    return new String[]{"EVEN","ODD"};
        }
        if ((str.equals("SUNNY") && str2.equals("SQUARE")) ||
                (str.equals("SQUARE") && str2.equals("SUNNY"))) {
            return new String[]{"SQUARE","SUNNY"};
        }
        if ((str.equals("DUCK") && str2.equals("SPY")) ||
                (str.equals("SPY") && str2.equals("DUCK"))) {
            return new String[]{"DUCK","SPY"};
        }
        if ((str.equals("HAPPY") && str2.equals("SAD")) ||
                (str.equals("SAD") && str2.equals("HAPPY"))) {
            return new String[]{"HAPPY","SAD"};
        }
        if ((str.equals("-EVEN") && str2.equals("-ODD")) ||
                (str.equals("-ODD") && str2.equals("-EVEN"))) {
            return new String[]{"-EVEN","-ODD"};
        }
        if ((str.equals("-SUNNY") && str2.equals("-SQUARE")) ||
                (str.equals("-SQUARE") && str2.equals("-SUNNY"))) {
            return new String[]{"-SQUARE","-SUNNY"};
        }
        if ((str.equals("-DUCK") && str2.equals("-SPY")) ||
                (str.equals("-SPY") && str2.equals("-DUCK"))) {
            return new String[]{"-DUCK","-SPY"};
        }
        if ((str.equals("-HAPPY") && str2.equals("-SAD")) ||
                (str.equals("-SAD") && str2.equals("-HAPPY"))) {
            return new String[]{"-HAPPY","-SAD"};
        }
        if (str.equals("-" + str2) || str2.equals("-" + str)){
            return new String[]{str,str2};
        }
        return null;
    }
    public static void showAvailableProperties() {
        System.out.println("Available properties: " + Arrays.asList(Property.values()));
    }
    public static boolean checkIfArrayContains(long[] numbers, long newNumber) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == newNumber) {
                return true;
            }
        }
        return false;
    }
}
