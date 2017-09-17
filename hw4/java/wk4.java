// Carrie Ravotta, cravott1@jhu.edu
// September 2017
// JHU EN.605.481 Principles of Enterprise Web Dev
// Homework #4

public class wk4 {
    public static void main(String[] args) {
        int total = 0;
        int one = 0;
        int two = 0;

        one = Integer.parseInt(args[0]);
        two = Integer.parseInt(args[1]);

        total = addNumbers(one, two);

        System.out.println("The total of your input ( " + one + " + " + two + " ) is: " + numberFormat(total));
    }

    // add two integers
    public static int addNumbers(int one, int two) {
        return one + two;
    }

    // print number
    // if number is negative return parenthesis rather than negative sign
    // if positive just return as string
    public static String numberFormat(int n) {
        if (n < 0) {
            return "(" + Math.abs(n) + ")";
        }

        return n + "";
    }
}
