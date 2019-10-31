package commands;

import commands.CommandStatus;
import containers.DoctorMapAccess;
import containers.PatientMapAccess;
import entities.Doctor;
import entities.Patient;

import java.util.Scanner;

public class DropDoctor extends CommandStatus
{
    public static void dropAssociation(int healthNum,String name)
    {
        Patient p = (Patient) PatientMapAccess.dictionary().get(healthNum);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNum);

        Doctor d = (Doctor) DoctorMapAccess.dictionary().get(name);
        if (d == null)
            throw new RuntimeException("There is no doctor with name " + name);

        int pHealthNumber = p.getHealthNumber();
        if (!d.hasPatient(pHealthNumber))
            throw new RuntimeException("This doctor is not associated with this patient.");
        if (!p.hasDoctor(name))
            throw new RuntimeException("This doctor and this patient are incorrectly "
                    + "associated.  The doctor has the patient, "
                    + "but the patient does not have the doctor");

        p.removeDoctor(name);
        d.removePatient(healthNum);
    }
}
