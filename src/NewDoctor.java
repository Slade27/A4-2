import commands.CommandStatus;

import java.util.Scanner;

public class NewDoctor extends CommandStatus
{








    public void addDoctor()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Getting Patient information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();
        if (doctors.containsKey(name))
            throw new RuntimeException("Doctor not added as there already "
                    + "is a doctor with the name " + name);

        System.out.print("Is the doctor a surgeon? (yes or no)");
        String response = consoleIn.nextLine();
        Doctor d;
        if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
            d = new Surgeon(name);
        else
            d = new Doctor(name);

        // check to make sure the doctor name doesn't already exsist
        Doctor sameNumberDoctor = doctors.put(name, d);
        if (sameNumberDoctor != null)
        {
            doctors.put(name, sameNumberDoctor); // put the original doctor back
            throw new RuntimeException("Name in the doctor dictionary even though "
                    + "containsKey failed.  Name "  + name + " not entered.");
        }
    }




}
