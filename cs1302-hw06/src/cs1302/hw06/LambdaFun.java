package cs1302.hw06;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Function;

/**
 * This class contains methods related to {@code cs1302-hw06}.
 */
public class LambdaFun {

    /** Standard Input scanner. */
    private static Scanner input = new Scanner(System.in);

    /**
     * Main entry-point into the application.
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {

        String[] myStrings = new String[] {
            "CSCI",        "1302",    "is", "an", "awesome", "course!",
            "Lambda", "expressions", "scare", "me",     "but",       "I",
            "will",   "persevere"
        };

        Email[] inbox = new Email[] {
            //  Email(recipient, sender, sendDate, contents)
            new Email("bjb211@uga.edu", "yellowjacket@gatech.edu",
                      LocalDate.of(2019, 2, 4), "Go GA Tech!"),
            new Email("bjb211@uga.edu", "mom@aol.com",
                      LocalDate.of(2019, 2, 5), "Have a good day!"),
            new Email("bjb211@uga.edu", "steve@anyotherschool.edu",
                      LocalDate.of(2019, 2, 6), "I wish I would've chosen UGA"),
            new Email("bjb211@uga.edu", "student1@uga.edu",
                      LocalDate.of(2019, 2, 7), "Thanks for teaching us!"),
            new Email("bjb211@uga.edu", "yellowjacket@gatech.edu",
                      LocalDate.of(2019, 2, 8), "Go GA Tech!")
        };

        // Method 1 Output Original
        Predicate<String> containsA = (String str) -> str.contains("a");
        LambdaFun.<String>printlnMatches(myStrings, containsA);
        // Method 1Output 1:
        Predicate<String> printAll = (String str) -> str.length() != 0;
        LambdaFun.<String>printlnMatches(myStrings, printAll);
        // Method 1 Output 2:
        Predicate<String> shorterThan6 = (String str) -> str.length() < 6;
        LambdaFun.<String>printlnMatches(myStrings, shorterThan6);
        // Method 1 Output 3:
        Predicate<String> containsTwoAs = (String str) -> {
            int counter = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == 'a') {
                    counter++;
                    if (counter >= 2) {
                        return true;
                    } // if
                } // if
            } // for
            return false;
        };
        LambdaFun.<String>printlnMatches(myStrings, containsTwoAs);
        // Method 2 Output:
        Predicate<Email> doesntIncludeGaTech = (Email e) -> {
            if (e.getSender().contains("gatech.edu")) {
                return false;
            }
            return true;
        };
        Function<Email, String> onlyContent = (Email e) -> {
            return "Your email says: " + e.getContents();
        };
        LambdaFun.<Email>printlnMappedMatches(inbox, doesntIncludeGaTech, onlyContent);
    } // main

    /**
     * Prints the elements of the array that pass the test specified by the given predicate.
     * More formally, this method prints all elements {@code element} in the array referred to by
     * {@code array} such that {@code condition.test(element)}. Each element will be printed on
     * its own line.
     *
     * @param <T> the type of the array elements
     * @param array the specified array
     * @param condition the specified predicate
     * @throws NullPointerException if the specified predicate is {@code null}
     */
    private static <T> void printlnMatches(T[] array, Predicate<T> condition) {
        for (int i = 0; i < array.length; i++) {
            if (condition.<T>test(array[i])) {
                System.out.println(array[i]);
            } // if
        } // for
    } // printlnMatches

    /**
     * Prints the elements of the array that pass the test specified by the given predicate
     * using a string mapper. More formally, this method prints the string-mapped elements
     * {@code mapper.apply(element)} in the array referred to by {@code array} for each
     * {@code element} such that {@code condition.test(element)}. Each string-mapped element
     * will be printed on its own line.
     *
     * @param <T> the type of the array elements
     * @param array the specified array
     * @param condition the specified predicate
     * @param mapper the specified string mapper
     * @throws NullPointerException if the specified predicate or string mapper is {@code null}
     */
    private static <T> void printlnMappedMatches(T[] array, Predicate<T> condition,
                                                 Function<T, String> mapper) {
        for (int i = 0; i < array.length; i++) {
            if (condition.<T>test(array[i])) {
                System.out.println(mapper.<T>apply(array[i]));
            } // if
        } // for
    } // printlnMappedMatches

} // LambdaFun
