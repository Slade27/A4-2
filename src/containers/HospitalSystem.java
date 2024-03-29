//Shea Slade
//sds266
//11235049

package containers;

import commands.*;
import entities.Doctor;
import entities.Patient;
import commands.NewDoctor;
import userInterfaces.AbstractConsoleIO;
import userInterfaces.AbstractDialogIO;
import userInterfaces.InputOutputInterface;

import java.util.TreeMap;
import java.util.Scanner;
import java.util.Collection;

/**
 * A simple hospital system with only one ward.  Patients and doctors can be created,
 * and patients assigned to a doctor and/or placed in a bed of the ward.
 */
public class HospitalSystem
{
    /**
     * The keyed dictionary of all patients.
     */
    private TreeMap<Integer, Patient> patients;

    /**
     * The keyed dictionary of all doctors.
     */
    private TreeMap<String, Doctor> doctors;

    /**
     * The ward to be handled.
     */
    private Ward ward;

    /**
     * Initialize an instance of the hospital ward
     * relies on user-input to get the relevent information
     */

    private InputOutputInterface ioInterface;
    /**
     * Initialize another I/O option
     */

    public HospitalSystem() {

        patients = PatientMapAccess.dictionary();
        doctors = DoctorMapAccess.dictionary();

        AbstractDialogIO box = new AbstractDialogIO() {
            @Override
            public void outputString(String outString) {

            }
        };

        String[] s = {"Console", "Dialogue"};
        int choice = box.readChoice(s);

        //System.out.println("You picked "+ choice);

        if (choice == 0)
        {
            System.out.println("Console");

            ioInterface = new AbstractConsoleIO() {
                @Override
                public void outputString(String outString) {

                }
            };
        }
        else
            {
                System.out.println("Dialogue");
                ioInterface = new AbstractDialogIO() {
                    @Override
                    public void outputString(String outString) {

                    }
                };
            }


        // get the ward information
        //Scanner consoleIn = new Scanner(System.in);

        System.out.println("Initializing the system...");
        System.out.println("Getting Ward information...");
        //System.out.print("Enter the name of the Ward: ");
        String name = ioInterface.readInt("Enter the Name of the ward");
        //String name = consoleIn.nextLine();
        System.out.print("Enter the integer label of the first bed: ");
        int firstBedNum = ioInterface.readString("Enter the integer label of the first bed: ");
        //int firstBedNum = consoleIn.nextInt();
        //consoleIn.nextLine();

        //System.out.print("Enter the integer label of the last bed: ");
        int lastBedNum = ioInterface.readString("Enter the integer label of the last bed: ");
        //int lastBedNum = consoleIn.nextInt();
        //consoleIn.nextLine();
        //ward = new Ward(name, firstBedNum, lastBedNum);
        ward = WardAccess.ward(name,firstBedNum,lastBedNum);
    }

    /**
     * Read the information for a new patient and then add the patient
     * to the dictionary of all patients.
     */
    public void addPatient()
    {
        //Scanner consoleIn = new Scanner(System.in);

        System.out.println("Getting entities.Patient information...");
        //System.out.print("Enter the name of the patient: ");

        String name = ioInterface.readInt("Enter the name of the patient: ");
        //String name = consoleIn.nextLine();


        //System.out.print("Enter the health number of the patient: ");
        int healthNum = ioInterface.readString("Enter the health number of the patient: ");
        //int healthNum = consoleIn.nextInt();
        //consoleIn.nextLine();  // discard the remainder of the line
        if (patients.containsKey(healthNum))
        {
            throw new RuntimeException("entities.Patient with the health number " + healthNum + " already exsists");
        }
        else
        {
            Patient p = new Patient(name, healthNum);
            Patient result = patients.put(healthNum, p);

            // checking to make sure the the key was unique
            if (result != null)
            {
                patients.put(healthNum, result);  // put the original patient back
                throw new RuntimeException("Health number in the patient dictionary even "
                        + "though containsKey failed.  Number " + healthNum + " not entered.");
            }
        }
    }

    /**
     * Read the information for a new doctor and then add the doctor
     * to the dictionary of all doctors.
     */
    public void addDoctor()
    {
        //Scanner consoleIn = new Scanner(System.in);

        System.out.println("Getting entities.Patient information...");
        //System.out.print("Enter the name of the doctor: ");
        String name = ioInterface.readInt("Enter the name of the doctor: ");
        //String name = consoleIn.nextLine();

        System.out.print("Is the doctor a surgeon? (yes or no)");
        String response = ioInterface.readInt("Is the doctor a surgeon? (yes or no)");
        //String response = consoleIn.nextLine();
        NewDoctor.addDoctor(name,response);

    }

    /**
     * Assign a doctor to take care of a patient.
     */
    public void assignDoctorToPatient()
    {
        //Scanner consoleIn = new Scanner(System.in);

        System.out.println("Assigning a new entities.Doctor-entities.Patient Association...");
        System.out.println("Getting entities.Patient information...");
        //System.out.print("Enter the health number of the patient: ");

        int healthNumber = ioInterface.readString("Enter the health number of the patient: ");
        //int healthNumber = consoleIn.nextInt();
        //consoleIn.nextLine();  // discard the remainder of the line


        System.out.println("Getting entities.Doctor information...");
        //System.out.print("Enter the name of the doctor: ");

        String name = ioInterface.readInt("Enter the name of the doctor: ");
        //String name = consoleIn.nextLine();

        AssignDoctor.assignDoctorToPatient(healthNumber, name);
    }

    /**
     * Assign a patient to a specific bed.
     */
    public void assignBed()
    {
        //Scanner consoleIn = new Scanner(System.in);

        System.out.println("Assigning a entities.Patient to a Bed...");
        System.out.println("Getting entities.Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = ioInterface.readString("Enter the health number of the patient: ");

        //int healthNumber = consoleIn.nextInt();
        //consoleIn.nextLine();  // discard the remainder of the line

        Patient p = patients.get(healthNumber);
        if (p == null){
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);}

        if (p.getBedLabel() != -1)
            throw new RuntimeException(" entities.Patient " + p
                    + " is already in a bed so cannot be assigned a new bed");

        //System.out.print("Enter the bed number for the patient: ");
        //int bedNum = consoleIn.nextInt();
        int bedNum = ioInterface.readString("Enter the bed number for the patient: ");
        //consoleIn.nextLine();  // discard the remainder of the line

        if (bedNum < ward.getMinBedLabel() || bedNum > ward.getMaxBedLabel())
            throw new RuntimeException("Bed label " + bedNum + " is not valid, as "
                    + "the value must be between " + ward.getMinBedLabel()
                    + " and " + ward.getMaxBedLabel());

        p.setBedLabel(bedNum);
        ward.assignPatientToBed(p, bedNum);
    }

    /**
     * Drop the association between a doctor and a patient.
     */
    public void dropAssociation()
    {
        //Scanner consoleIn = new Scanner(System.in);

        System.out.println("Dropping a new entities.Doctor-entities.Patient Association...");
        System.out.println("Getting entities.Patient information...");
        //System.out.print("Enter the health number of the patient: ");
        int healthNumber = ioInterface.readString("Enter the health number of the patient: ");
        //int healthNumber = consoleIn.nextInt();
        //consoleIn.nextLine();  // discard the remainder of the line

        System.out.println("Getting entities.Doctor information...");
        System.out.print("Enter the name of the doctor: ");
        String name = ioInterface.readInt("Enter the name of the doctor: ");
        //String name = consoleIn.nextLine();

        DropDoctor.dropAssociation(healthNumber,name);
    }

    /**
     * Displays the system state
     */
    public void systemState()
    {
        System.out.println(this.toString());
    }

    /**
     * Return a string representation of the containers.HospitalSystem
     * @return a string representation of the containers.HospitalSystem
     */
    public String toString() {
        String result = "\nThe patients in the system are \n";
        Collection<Patient> patientsColl = patients.values();
        for (Patient p: patientsColl)
            result = result + p;
        result = result + "\nThe doctors in the system are \n";
        Collection<Doctor> doctorsColl = doctors.values();
        for (Doctor d: doctorsColl)
            result = result + d;
        result = result + "\nThe ward is " + ward;
        return result;
    }

    /**
     * Display the empty beds for the ward.
     * Method is just a stub, needs to be implemented
     */
    public void displayEmptyBeds()
    {
        System.out.println("Finding empty beds...");
        EmptyBeds.displayEmptyBeds(ward);
    }




    /**
     * Release a patient from the ward.
     * Method is just a stub, needs to be implemented
     */
    public void releasePatient()
    {
        //Scanner In = new Scanner(System.in);

        System.out.print("Enter the name of the patient: ");
        String name = ioInterface.readInt("Enter the name of the patient");
        //String name = In.nextLine();

        //System.out.print("Enter the health number of the patient: ");
        int healthNum = ioInterface.readString("Enter the Health umber of the patient");
        //int healthNum = In.nextInt();
        //In.nextLine();  // discard the remainder of the line

        Patient p = patients.get(healthNum);

        if (!patients.containsKey(healthNum))
        {
            throw new RuntimeException("entities.Patient with the health number " + healthNum + " does not exist");
        }
        else
        {
            //System.out.println("Does patient have doctors? 1=yes/0=no ");
            //int doc = In.nextInt();
            int doc = ioInterface.readString("Does patient have doctors? 1=yes/0=no ");
            if (doc == 1){ dropAssociation(); }
            patients.remove(healthNum);
            int bed = p.getBedLabel();
            ward.freeBed(bed);


            System.out.println("\n Removed patient " + name);
            System.out.println("Free'd bed"+ bed);
            System.out.println("Dropped any doctor patient associations \n");
            // checking to make sure the the key was unique
        }
    }

    /**
     * Run the hospital system.
     * @param args not used
     */
    public static void main(String[] args)
    {


        String[] options = {"Pick an Option","quit","New patient","New doctor",
                "Assign doctor to patient","Empty beds","Assign to bed",
                "Release patient","Drop doctor patient","System state"};

        int task = -1;
        int input;

        HospitalSystem sys = new HospitalSystem();
        input = sys.ioInterface.readString("Enter an Integer");
        System.out.println( "Here is the input --" + (input));

        try{
            while(task != 1) {

                task = sys.ioInterface.readChoice(options);
                System.out.println("This is the task" + task);

                if(task == 0)
                    sys.systemState();
                else if (task == 1)
                    sys.systemState();
                else if (task == 2)
                    sys.addPatient();
                else if (task == 3)
                    sys.addDoctor();
                else if (task == 4)
                    sys.assignDoctorToPatient();
                else if (task == 5)
                    sys.displayEmptyBeds();
                else if (task == 6)
                    sys.assignBed();
                else if (task == 7)
                    sys.releasePatient();
                else if (task == 8)
                    sys.dropAssociation();
                else if (task == 9)
                    sys.systemState();
                else
                    System.out.println("Invalid option, try again.");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}