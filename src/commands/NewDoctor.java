package commands;

import commands.CommandStatus;
import containers.DoctorMapAccess;
import entities.Doctor;
import entities.Surgeon;

import java.util.Scanner;

public class NewDoctor extends CommandStatus
{
    /**
     * Takes in Doctor namd and type and adds the doctor to the dictionary
     * @param name = The name of the Doctor
     * @param response = the type of doctor
     */
    public static void addDoctor(String name, String response)
    {
        CommandStatus err = new CommandStatus();

        if(DoctorMapAccess.dictionary().containsKey(name))
        {
            err.errorMessage = "entities.Doctor not added as there already "
                    + "is a doctor with the name " + name;
            err.getErrorMessage();
        }

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

