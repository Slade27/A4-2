package containers;
import entities.Patient;
import java.util.TreeMap;

public class PatientMapAccess
{
    /** The dictionary for doctors. */
    private static TreeMap<Integer,Patient> dictionary;

    /**
     * Private constructor to ensure that no instance of this class
     is created.
     */
    private PatientMapAccess()
    {
    }

    /**
     * Return the dictionary that maps names to = Patients
     *
     * @return the dictionary that maps names to Patients
     */
    public static TreeMap dictionary()
    {
        if (dictionary == null)
        {
            dictionary = new TreeMap<Integer, Patient>();
        }
        return dictionary;
    }

}
