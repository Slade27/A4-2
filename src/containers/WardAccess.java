package containers;

public class WardAccess
{
    /** Created ward **/
    private static Ward ward;

    /**
     * Private constructor to ensure that no instance of this class
     is created.
     */
    private WardAccess()
    {
    }

    /**
     * Return the containers.Ward created
     *
     * @return the ward created
     */
    public static Ward ward(String name,int minBedLabel ,int  maxBedLabel)
    {
        if (ward == null)
        {
            ward = new Ward(name, minBedLabel, maxBedLabel);
        }
        return ward;
    }

}
