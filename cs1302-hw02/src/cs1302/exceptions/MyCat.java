package cs1302.exceptions;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * An enhanced version of the simpler version of the Unix <code>cat</code> command.
 */
public class MyCat {

    /**
     * Enhanced version of the application. Zero or multiple command-line arguments are expected.
     * If a filename is given as an argument, then the program should print the contents of that
     * file to standard output. If a single dash (i.e., "-") is given as an argument, then
     * the program should print the contents of standard input. Catches FileNotFoundException
     * for unknown files.
     *
     * @param args  the command-line arguments
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: MyCat [filename]...");
        } // if

        for (int i = 0; i < args.length; i++) {

            String filename = args[i];

            if (filename.equals("-")) {
                Printer.printStdInLines();
            } else {
                try {
                    File file = new File(filename);
                    Printer.printFileLines(file);
                } catch (FileNotFoundException fnfe) {
                    System.err.println("MyCat: " + fnfe.toString());
                }
            } // if

        } // for

    } // main

} // MyCat
