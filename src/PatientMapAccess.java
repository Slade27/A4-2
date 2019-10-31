import java.util.TreeMap;

public class PatientMapAccess
{
    /**
     * Private constructor to ensure that no instance of this class
     is created.
     */
    private PatientMapAccess()
    {
    }

    /** The dictionary for doctors. */
    private static TreeMap<Integer, Patient> patients = new TreeMap<Integer, Patient>();

    /**
     * Return the dictionary that maps names to customers
     *
     * @return the dictionary that maps names to customers
     */
    public static TreeMap<Integer, Patient> patients()
    {
        if (patients == null)
        {
            patients = new TreeMap<Integer, Patient>();
        }
        return patients;
    }

}
