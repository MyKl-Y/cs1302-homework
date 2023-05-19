package cs1302.example;

import java.util.Scanner;
import cs1302.utility.MyMethods;


/**
 * The {@code Hello} holds the main class which will promp the user for their name and greets
 * them, then it prompts the user for two integers and prints the greater of the two using
 * the MyMethods class and returnGreater method.
 */

public class Hello {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = keyboard.nextLine();

        System.out.println("Hello, " + name);

        System.out.println("Enter one integer");
        int num1 = keyboard.nextInt();

        System.out.println("Enter second integer");
        int num2 = keyboard.nextInt();

        System.out.println(MyMethods.returnGreater(num1, num2) + " is the largest number");
    } // main
} // Hello
