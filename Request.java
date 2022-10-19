

/**
 * Class to create requests
 */
public class Request {

    // INSTANCE VARIABLES
    private boolean vip;
    private Passenger passenger;
    private int destination;
    private int carType;

    // CONSTRUCTOR
    /**
     * Constructs new request
     * @param pass passenger
     * @param dest destination
     * @param car car type (1-4)
     * @param VIP VIP
     */
    public Request(Passenger pass, int dest, int car, boolean VIP)
    {
        passenger = pass;
        destination = dest;
        carType = car;
        vip = VIP;
    }

    /**
     * Gets car type
     * @return Car type
     */
    public int getCarType()
    {
        return carType;
    }

    /**
     * Gets VIP status
     * @return True if VIP false if not
     */
    public boolean getVipStatus()
    {
        return vip;
    }

    /**
     * Gets destination
     * @return The passenger's destination
     */
    public int getDestination()
    {
        return destination;
    }

    /**
     * Gets passenger
     * @return The passenger making the request
     */
    public Passenger getPassenger()
    {
        return passenger;
    }

}
