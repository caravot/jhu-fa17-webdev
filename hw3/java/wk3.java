// Carrie Ravotta, cravott1@jhu.edu
// September 2017
// JHU EN.605.481 Principles of Enterprise Web Dev
// Homework #3
// 1. Create a method (in an arbitrary class of your choosing) that takes integers as arguments (not prompt the user,
// these are arguments to your program) and returns the product of the two integers.
//
// 2. The method will have an "int" return type.
//
// 3. In order to show your method works, you should have a "main" method in the class that requires the users
// provide two arguments after the command, and then converts those two arguments to integers.
//
// 4. You will then pass those ints to your method, get the result, and then print the result back out to the
// console. If the number is negative, print out a negative amount as a number in parenthesis () rather than have a negative sign.

public class wk3 {
    public static void main(String[] args) {
        int total = 0;
        int one = 0;
        int two = 0;

        one = Integer.parseInt(args[0]);
        two = Integer.parseInt(args[1]);

        total = addNumbers(one, two);

        System.out.println("The total of your input is: " + numberFormat(total));
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
