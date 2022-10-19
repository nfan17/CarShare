

import java.util.ArrayList;

/**
 * Class to create cars
 */
public class Car {
    // INSTANCE VARIABLES
    //private int idNum; //<-make static variable, next car = id + 1
    private int carId;
    private int destination;
    private int location;
    private int maxPass;
    private int maxMiles;
    private int direction;
    private double fuelEconomy;
    private double carFare;
    private String carType;
    private int carTypeNum;
    private ArrayList<Passenger> passengers = new ArrayList<>();
    private int busyHourCost;
    private boolean vip;

    private double faresCollected = 0.0;
    private int milesDriven = 0;

    // CONSTANTS
    final double VIP_DISCOUNT = 2.0;
    final double FARE_PER_MILE_3SEAT = 1.0;
    final double FARE_PER_MILE_LUX3SEAT = 3.0;
    final double FARE_PER_MILE_5SEAT = 2.0;
    final double FARE_PER_MILE_LUX5SEAT = 4.0;
    final int MAX_PASSENGERS_3SEAT = 3;
    final int MAX_PASSENGERS_5SEAT = 5;
    final int MAX_MILES_3SEAT = 400;
    final int MAX_MILES_5SEAT = 350;
    final double GALLON_GAS_COST = 5.0;
    final int TANK_SIZE = 16;
    final double FUEL_COST_PER_MILE_3SEAT = GALLON_GAS_COST / MAX_MILES_3SEAT / (.9 * TANK_SIZE) ;
    final double FUEL_COST_PER_MILE_5SEAT = GALLON_GAS_COST / MAX_MILES_5SEAT / (.9 * TANK_SIZE);

    final double BUSY_HOUR_CHARGE = 0.5;

    // STATIC VARIABLES
    private static int idNum = 0;

    // CONSTRUCTOR
    /**
     * Constructs new car
     * @param station station number
     * @param dest car destination
     * @param type type of car 
     */
    public Car(int station, int dest, int type) 
    {
        idNum = idNum++;
        carId = idNum;
        location = station + 1;
        destination = dest;
        vip = false;
        carTypeNum = type;
        if (type == 1) // 3 seat
        {
            carFare = FARE_PER_MILE_3SEAT;
            maxPass = MAX_PASSENGERS_3SEAT;
            maxMiles = MAX_MILES_3SEAT;
            fuelEconomy = FUEL_COST_PER_MILE_3SEAT;
            carType = "3 Seat";
        }
        else if (type == 2) // Luxury 3 seat
        {
            carFare = FARE_PER_MILE_LUX3SEAT;
            maxPass = MAX_PASSENGERS_3SEAT;
            maxMiles = MAX_MILES_3SEAT;
            fuelEconomy = FUEL_COST_PER_MILE_3SEAT;
            carType = "Luxury 3 Seat";
        }
        else if (type == 3) // 5 seat
        {
            carFare = FARE_PER_MILE_5SEAT;
            maxPass = MAX_PASSENGERS_5SEAT;
            maxMiles = MAX_MILES_5SEAT;
            fuelEconomy = FUEL_COST_PER_MILE_5SEAT;
            carType = "5 Seat";
        }
        else // Luxury 5 seat
        {
            carFare = FARE_PER_MILE_LUX5SEAT;
            maxPass = MAX_PASSENGERS_5SEAT;
            maxMiles = MAX_MILES_5SEAT;
            fuelEconomy = FUEL_COST_PER_MILE_5SEAT;
            carType = "Luxury 5 Seat";
        }
        if (location < destination) 
        {
            direction = 1;
        }
        else 
        {
            direction = -1;
        }
    }

    // ACCESSORS
    /**
     * Gets location of car
     * @return location
     */
    public int getLocation() 
    {
        return location;
    }
    /**
     * Gets destination of car
     * @return destination
     */
    public int getDestination() 
    {
        return destination;
    }
    /**
     * Returns if car is out of gas
     * @return true if yes, false if not
     */
    public boolean outOfGas() 
    {
        return milesDriven == maxMiles;
    }
    /**
     * Returns if car has room
     * @return true if has room, false if not
     */
    public boolean hasRoom() 
    {
        
        return passengers.size() < maxPass && !vip;
    }
    /**
     * Checks if carrying VIP passenger
     * @return true if yes, false if not
     */
    public boolean vipStatus()
    {
        if (passengers.size() > 0)
        {
            vip = passengers.get(0).getVipStatus();
        }
        return vip;

    }
    /**
     * Returns revenue (fares minus gas)
     * @return revenue
     */
    public double getRevenue() 
    {
        return faresCollected - (fuelEconomy * milesDriven);
    }
    /**
     * Returns miles driven
     * @return miles driven
     */
    public int getMilesDriven() 
    {
        return milesDriven;
    }

    /**
     * Gets max miles the car can drive
     * @return max miles 
     */
    public int getMaxMiles() 
    {
        return maxMiles;
    }
    /**
     * Gets car type
     * @return car type (1-4)
     */
    public int getType()
    {
        return carTypeNum;
    }
    
    // MUTATORS
    /**
     * Drives to next station
     * @param busyHour true if busy hour, false if not
     */
    public void drive(boolean busyHour)
    {
        if (milesDriven < maxMiles) 
        {
            milesDriven++;
            if (busyHour)
            {
                busyHourCost = 1;
            }
            else
            {
                busyHourCost = 0;
            }
            if (vip)
            {
                faresCollected += maxPass * carFare - VIP_DISCOUNT + busyHourCost * BUSY_HOUR_CHARGE;
            }
            else
            {
                faresCollected += passengers.size() * carFare + busyHourCost * BUSY_HOUR_CHARGE; 
            }
            direction += direction;
            if (location == 1 || location == 20)
            {
                direction *= -1;
            }
            /*if (location == 1)
            {
                direction++;
            }
            else if (location == 20)
            {
                direction--;
            }*/
            // location = (location < destination) ? location + 1 : location - 1;
            // in line if statement: if location < destination, then location = location + 1, 
            //          else location = location - 1
            // car can go in diff directions from 1->20 or 20->1
        }
    }

    /**
     * Drop off any passengers who have arrived
     */
    public void dropOff()
    {
        for (int i = passengers.size() - 1; i >= 0; i--) 
        {
            if (passengers.get(i).getDestination() == location)
            {
                passengers.remove(i);
            }
        }
    }

    /**
     * Add a passenger if there is room
     * @param pass the passenger to add
     * @return true if the passenger was successfully added, false if not
     */
    public boolean add(Passenger pass) 
    {
        if (hasRoom())
        {
            passengers.add(pass);
            return true;
        }
        return false;
    }

    /**
     * Converts car info to string
     */
    @Override
    public String toString()
    {
        return "Car[ID = " + carId + ", car type = " + carType + ", location = " + location 
        + ", destination = " + destination + ", passengers = " + passengers + "]";
    }


}
