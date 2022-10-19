

import java.time.LocalTime;
/**
 * Sublass of person to create passengers
 */
public class Passenger extends Person {
    // FIELDS
    public int destination;
    public int currentLocation;
    public int idNum;
    public LocalTime timeStamp;
    public boolean vip;
    public int desiredCar;


    // CONSTRUCTOR
    /**
     * Constructs new passenger
     * @param ID ID number
     * @param dest destination
     * @param location location
     * @param time time stamp
     * @param fName first name
     * @param lName last name 
     * @param addy address
     * @param cityName name of city
     * @param zip zipcode
     * @param cellNum cell phone number
     * @param carType type of car desired
     * @param vipStatus vip status
     */
    public Passenger(int ID, int dest, int location, LocalTime time, String fName, String lName,
                    String addy, String cityName, int zip, int cellNum, int carType, int vipStatus) 
    { 
        super(fName, lName, addy, cityName, zip, cellNum);
        idNum = ID;
        currentLocation = location + 1;
        destination = dest;
        timeStamp = time;
        desiredCar = carType;
        if (vipStatus < 4)
        {
            vip = true;
        }
        else
        {
            vip = false;
        }
    }

    // METHODS
    /**
     * Gets passenger destination
     * @return destination 
     */
    public int getDestination() 
    {
        return destination;
    }
    /**
     * Gets passenger ID number
     * @return ID number
     */
    public int getIdNum() 
    {
        return idNum;
    }
    /**
     * Gets passenger location
     * @return current location
     */
    public int getCurrentLocation()
    {
        return currentLocation;
    }
    /**
     * Get time stamp
     * @return time stamp
     */
    public LocalTime getTimeStamp()
    {
        return timeStamp;
    }
    /**
     * Gets desired car type
     * @return car type (1-4)
     */
    public int getDesiredCar()
    {
        return desiredCar;
    }
    /**
     * Get VIP status (fill car)
     * @return true if VIP false if not
     */
    public boolean getVipStatus()
    {
        return vip;
    }

    /**
     * Set passenger destination
     * @param dest destination
     */
    public void setDestination(int dest) 
    {
        destination = dest;
    }
    /**
     * Set passenger ID
     * @param ID ID number
     */
    public void setIdNum(int ID) 
    {
        idNum = ID;
    }
    /**
     * Set current location
     * @param location current location
     */
    public void setCurrentLocation(int location)
    {
        currentLocation = location;
    }
    /**
     * Set time stamp
     * @param time time stamp
     */
    public void setTimeStamp(LocalTime time)
    {
        timeStamp = time;
    }
    /**
     * Set desired car type
     * @param carType type of car (1-4)
     */
    public void setDesiredCar(int carType)
    {
        desiredCar = carType;
    }
    /**
     * Sets VIP status
     * @param vipStatus VIP status (1-10)
     */
    public void setVipStatus(int vipStatus)
    {
        vip = (vipStatus < 4) ? true : false;
    }

    /**
     * Converts passenger info to string
     */
    @Override
    public String toString() 
    {
        return super.toString() + ", ID Number = " + idNum + ", Current Location = " + currentLocation
                + ", Destination = " + destination + ", Time Stamp = " + timeStamp;
    }
    
}
