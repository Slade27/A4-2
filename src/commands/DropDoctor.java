//Shea Slade
//sds266
//11235049

package commands;

import commands.CommandStatus;
import containers.DoctorMapAccess;
import containers.PatientMapAccess;
import entities.Doctor;
import entities.Patient;

import java.util.Scanner;

public class DropDoctor extends CommandStatus
{
    /**
     * Takes in Doctor namd and type and adds the doctor to the dictionary
     * @param healthNum = The health number of the Patient
     * @param name = The doctor to be dropped
     */
    public static void dropAssociation(int healthNum,String name)
    {
        CommandStatus err = new CommandStatus();

        Patient p = (Patient) PatientMapAccess.dictionary().get(healthNum);
        if (p == null){
            err.errorMessage = "There is no patient with health number \"\n" + healthNum;
            err.getErrorMessage();}

        Doctor d = (Doctor) DoctorMapAccess.dictionary().get(name);
        if (d == null){
            err.errorMessage = "There is no doctor with name \"\n" + name;
            err.getErrorMessage();}


        int pHealthNumber = p.getHealthNumber();
        if (!d.hasPatient(pHealthNumber)){
            err.errorMessage = "This doctor is not associated with this patient.";
            err.getErrorMessage();}

        if (!p.hasDoctor(name))
        {
            err.errorMessage = "This doctor and this patient are incorrectly associated. The doctor has the patient, \"\n" + "but the patient does not have the doctor";
        }

        p.removeDoctor(name);
        d.removePatient(healthNum);

        err.wasSuccessful(); // Set to True
        err.getErrorMessage();
    }
}
