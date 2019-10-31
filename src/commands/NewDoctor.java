package commands;

import commands.CommandStatus;
import containers.DoctorMapAccess;
import entities.Doctor;
import entities.Surgeon;

import java.util.Scanner;

public class NewDoctor extends CommandStatus
{
    public void addDoctor()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Getting entities.Patient information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();
        if(DoctorMapAccess.dictionary().containsKey(name))
        //if (doctors.containsKey(name))
            throw new RuntimeException("entities.Doctor not added as there already "
                    + "is a doctor with the name " + name);

        System.out.print("Is the doctor a surgeon? (yes or no)");
        String response = consoleIn.nextLine();
        Doctor d;
        if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
            d = new Surgeon(name);
        else
            d = new Doctor(name);

        // check to make sure the doctor name doesn't already exsist
        Doctor sameNumberDoctor = (Doctor) DoctorMapAccess.dictionary().put(name, d);
        //Doctor sameNumberDoctor = doctors.put(name, d);
        if (sameNumberDoctor != null)
        {
            DoctorMapAccess.dictionary().put(name, sameNumberDoctor);
            //doctors.put(name, sameNumberDoctor); // put the original doctor back
            throw new RuntimeException("Name in the doctor dictionary even though "
                    + "containsKey failed.  Name "  + name + " not entered.");
        }
    }
}

