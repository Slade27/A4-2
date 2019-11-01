//Shea Slade
//sds266
//11235049

package userInterfaces;

import containers.HospitalSystem;

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
        for(int i=1; i < options.length; i++)
        {
            System.out.println(i+ ": " + options[i]);
        }
        System.out.println("Select option \n"+options);
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }
//Make read_choice read from a string[] and print to console
}
