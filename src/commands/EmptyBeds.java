package commands;

import commands.CommandStatus;
import containers.Ward;

public class EmptyBeds extends CommandStatus
{
    public static void displayEmptyBeds(Ward ward)
    {
        System.out.println("Finding empty beds...");
        System.out.println(ward.availableBeds());
        System.out.println("\n");
    }
}
