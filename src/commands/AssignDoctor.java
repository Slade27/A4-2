package commands;

import containers.DoctorMapAccess;
import containers.PatientMapAccess;
import commands.CommandStatus;
import entities.Doctor;
import entities.Patient;

import java.util.Scanner;

public class AssignDoctor extends CommandStatus
{

 Patient p = PatientMapAccess.dictionary().get(healthnum);

}
