//Shea Slade
//sds266
//11235049

package userInterfaces;

import javax.swing.*;
import java.util.Scanner;

public abstract class AbstractConsoleIO implements InputOutputInterface
{
    /**
     *
     * @param options an array with the options that are presented to the user
     * @return an int for selection
     */
    public int readChoice(String[] options)
    {
        System.out.println("\n Select option \n");
        for(int i=1; i < options.length; i++)
        {
            System.out.println(i+ ": " + options[i]);
        }
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }

    /**
     *
     * @param prompt a prompt for the user to enter a number
     * @return an int for selection
     */
    public int readString(String prompt)
    {
        Scanner s = new Scanner(System.in);
        System.out.println(prompt);

        return s.nextInt();
    }

    /**
     *
     * @param prompt A string instructing user to enter information
     * @return a String of information
     */
    public String readInt(String prompt)
    {
        Scanner s = new Scanner(System.in);
        System.out.println(prompt);

        return s.next();
    }


}
