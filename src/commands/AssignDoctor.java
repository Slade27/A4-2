package commands;

import containers.DoctorMapAccess;
import containers.PatientMapAccess;
import commands.CommandStatus;
import entities.Doctor;
import entities.Patient;

import java.util.Scanner;

public class AssignDoctor extends CommandStatus
{
    public static void assignDoctorToPatient(int healthNumber, String name)
    {
        Patient p = (Patient) PatientMapAccess.dictionary().get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        Doctor d = (Doctor) DoctorMapAccess.dictionary().get(name);
        if (d == null)
            throw new RuntimeException("There is no doctor with name " + name);
        else
        {
            p.addDoctor(d);
            d.addPatient(p);
        }
    }
}
