import java.util.TreeMap;

public class DoctorMapAccess
{
        /**
         * Private constructor to ensure that no instance of this class
         is created.
         */
    private DoctorMapAccess()
        {
        }

        /** The dictionary for doctors. */
        private static TreeMap<String, Doctor> doctors = new TreeMap<String, Doctor>();

        /**
         * Return the dictionary that maps names to Doctors
         *
         * @return the dictionary that maps names to Doctors
         */
        public static TreeMap<String, Doctor> doctors()
        {
            if (doctors == null)
            {
                doctors = new TreeMap<String, Doctor>();
            }
            return doctors;
        }

}
