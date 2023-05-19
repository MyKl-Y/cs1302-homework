package cs1302.utility;

/**
 * The {@code MyMethods} holds the method that would be used in main to find the greater of
 * two ints.
 */


public class MyMethods {

/**
 * This method takes two ints as it's @param, a and b, and @return the greater of the
 * two.
 *
 * @param a
 * @param b
 * @return the greater
 */
    public static int returnGreater(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        } // if-else
    } // returnGreater
} // MyMethods
