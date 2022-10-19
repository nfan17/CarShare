
import java.util.ArrayList;

/**
 * Class to create stations 
 */
public class Station {
    // INSTANCE VARIABLES
    private ArrayList<Passenger> people = new ArrayList<>();
    private ArrayList<Request> requests = new ArrayList<>();
    private ArrayList<String> amenities = new ArrayList<>();
    private int stationNumber;

    // CONSTRUCTOR
    /**
     * Constructs new station
     * @param number station number
     */
    public Station(int number) 
    {
        stationNumber = number;
    }

    // METHODS
    /**
     * Gets station number
     * @return station number
     */
    public int getNumber() 
    {
        return stationNumber;
    }
    /**
     * Generates requests array list
     */
    public void generateRequests()
    {
        for (int i = 0; i < people.size(); i++)
        {
            Request newReq = new Request(people.get(i), people.get(i).getDesiredCar(), 
                        people.get(i).getDestination(), people.get(i).getVipStatus());
            requests.add(i, newReq);
        }
    }

    /**
     * Gets requests as array list
     * @return requests as array list
     */
    public ArrayList<Request> getRequests()
    {
        return requests;
    }

    /**
     * Gets request at index
     * @param index the index
     * @return the request
     */
    public Request getRequest(int index)
    {
        return requests.get(index);
    }


    /**
     * Sets amenities
     * @param outOfService unavailable amenities 
     */
    public void setAmenities(int outOfService)
    {
        if (outOfService != 1)
        {
            amenities.add("Coffee");
        }
        if (outOfService != 2)
        {
            amenities.add("Donuts");
        }
        if (outOfService != 3)
        {
            amenities.add("Pretzels");
        }
        if (outOfService != 4)
        {
            amenities.add("Wifi");
        }
    }

    /**
     * Gets amenities as a string
     * @return amenities
     */
    public String getAmenities()
    {
        return amenities.toString();
    }

    /**
     * Adds passenger
     * @param pass passenger
     */
    public void add(Passenger pass) 
    {
        people.add(pass);
    }

    /**
     * Checks if there are people waiting
     * @return true if people waiting, false if none
     */
    public boolean hasPeopleWaiting() 
    {
        return people.size() > 0;
        //if arraylist size > 0 people in it, if not no ppl
    } 
    
    /**
     * Gets passenger at index 
     * @param index index
     * @return passenger at index
     */
    public Passenger get(int index) 
    {
        return people.get(index);
    }
    
    /**
     * Removes passenger at index
     * @param index index
     * @return passenger at index
     */
    public Passenger remove(int index) 
    {
        requests.remove(index);
        return people.remove(index);
    }
    
    /**
     * Returns number of people waiting
     * @return number of people waiting
     */
    public int numPeopleWaiting() 
    {
        return people.size();
    }
    
    /**
     * Converts station's people array to string
     */
    public String toString() 
    {
        return people.toString();
    }
}
