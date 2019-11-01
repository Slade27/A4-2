package containers;

import entities.Doctor;

import java.util.TreeMap;

public class DoctorMapAccess
{

    /** The dictionary for doctors. */
    private static TreeMap dictionary = new TreeMap<String, Doctor>();

    /**
         * Private constructor to ensure that no instance of this class
         is created.
         */
    private DoctorMapAccess()
        {
        }

        /**
         * Return the dictionary that maps names to Doctors
         *
         * @return the dictionary that maps names to Doctors
         */
        public static TreeMap dictionary()
        {
            if (dictionary == null)
            {
                dictionary = new TreeMap<String,Doctor>();
            }
            System.out.println("HEY YOU ARE DOING IT RIGHT");
            return dictionary;
        }

}
