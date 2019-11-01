//Shea Slade
//sds266
//11235049
package commands;

import commands.CommandStatus;
import containers.Ward;

public class EmptyBeds extends CommandStatus
{
    /**
     * Displays the empty beds of the inputted ward
     * @param ward = The name used WArd
     */
    public static void displayEmptyBeds(Ward ward)
    {
        System.out.println("Finding empty beds...");
        System.out.println(ward.availableBeds());
        System.out.println("\n");
    }
}
