
import java.util.Random;
import java.util.ArrayList;
import java.time.LocalTime;

// Name: nfan17
// Output: Simulation results: Revenue per Mile, total miles, total revenue, passengers remaining
// Processing: Uses car, station, passenger, and request objects to create car sharing simulation
// Last Changed: 8/4/2022

/**
 * Car Sharing Simulation 
 */
public class Simulation
{
   private Random generator;
   private ArrayList<Car> cars = new ArrayList<>();
   private double faresCollected = 0.0;
   private int milesDriven = 0;
   private Station[] stations;
   private int numPeople;

   /**
    * Number of stations
    */
   public static final int STATIONS = 20;

   final String CITY = "Pasadena";
   final int ZIPCODE = 91107;

   final int MORN_BUSY_HOUR = 7;
   final int MORN_BUSY_HOUR_END = 11;
   final int EVE_BUSY_HOUR = 15;
   final int EVE_BUSY_HOUR_END = 20;

   private static int passengerNum = 1;
   
   /**
    * Construct a simulation using a given generator.
    * @param generator the Random generator to use in this simulation.
    */
   public Simulation(Random generator)
   {
      this.generator = generator;
   }
   /**
    * Load a scenario into the simulator.
    * @param numStations - the number of stations to add.
    */
   public void loadRandomScenario(int numStations)
   {  
      stations = new Station[numStations];
      for (int i = 0; i < stations.length; i++) 
      {
         int busyHour = 1;
         if ((getHour() > MORN_BUSY_HOUR && getHour() < MORN_BUSY_HOUR_END ||
         getHour() > EVE_BUSY_HOUR && getHour() < EVE_BUSY_HOUR_END))
         {
            busyHour = 5;
         }
         int numPass = 10 + generator.nextInt(0, 20) * 3;
         numPeople += numPass * busyHour;
         stations[i] = new Station(i + 1);
         // 2/5 chance for all, 3/5 for missing one amenity
         int amenities = generator.nextInt(0, 6); 
         stations[i].setAmenities(amenities);
         int j = 0;
         while (j < numPass * busyHour)
         {
            int passDest;
            do
            {
               passDest = 1 + generator.nextInt(stations.length);
            }
            while (passDest == i); 
            String passName = "P#" + i + "-" + passengerNum;
            int passId = generator.nextInt(1000, 9999) * 10 + passengerNum;
            String address = "abc" + passengerNum;
            int cellNum = generator.nextInt(0000000, 10000000);
            int carType = generator.nextInt(1, 5);
            int vipStatus = generator.nextInt(1, 11);
            passengerNum++;
            Passenger pass = new Passenger(passId, passDest, i, LocalTime.now(), passName, 
                                 passName, address, CITY, ZIPCODE, cellNum, carType, vipStatus);
            stations[i].add(pass);
            j++;
         }
         stations[i].generateRequests();
         for (int k = 0; k < numPass; k++) 
         {
            int carDest;
            int carType = generator.nextInt(1, 5);
            do
            {
               carDest = 1 + generator.nextInt(stations.length);
            }
            while (carDest == i + 1); 
            Car car = new Car(i, carDest, carType);
            cars.add(car);
         }
      }
   }
   /**
    * Calcuate the revenue per mile.
    * @return Revenue per mile 
    */
   public double getRevenuePerMile()
   {
      // Complete this method.
      return faresCollected / milesDriven;
   }
   /**
    * Gets current hour
    * @return hour as int
    */
   public int getHour()
   {
      String hour = LocalTime.now().toString().substring(0, 2);
      return Integer.parseInt(hour);
   }
   /**
    * Return if more people than cars
    * @return true if more people than cars, false if not
    */
   public boolean busyHour()
   {
      return numPeople > cars.size();
   }
   /**
    * Gets array list of cars as string
    * @return array list of cars
    */
   public String getCars()
   {
      return cars.toString();
   }
   
   // Accessor methods
   /**
    * Adds a car to car array list
    * @param car car to add
    */
   public void addCar(Car car) { cars.add(car); }
   /**
    * Gets total fares collected
    * @return fares collected
    */
   public double getFares() { return faresCollected; }
   /**
    * Gets total miles driven
    * @return miles driven
    */
   public int getMilesDriven() { return milesDriven; }

   /**
    * Gets number of passengers
    * @return number of passengers
    */
   public int getNumPassengers() 
   { 
      int passRemain = 0;
      for (int i = 0; i < stations.length - 1; i++)
      {
         passRemain += stations[i].numPeopleWaiting();
      }
      return passRemain;
   }

   /**
    * Gets number of cars
    * @return number of cars
    */
   public int getNumCars()
   {
      return cars.size();
   }

   /**
    * Get beginning number of passengers and cars
    * @return a string showing passengers and cars
    */
   public String getInitialState()
   {
      return "NUM PASSENGERS: " + getNumPassengers()
               + " NUM CARS: " + getNumCars();
   }
   /**
    * Get Revenue per Mile, Total Mileage, Total Fares, Passengers Remaining
    * @return a string showing RPM, total mileage, total fares, passengers
    */
   public String getFinalState()
   {
      return "AFTER SIMULATION:\nRPM: " + getRevenuePerMile() + 
               ", MILES: " + getMilesDriven() +
               ", FARES: "  + getFares() + 
               ", PASSENGERS REMAINING: " + getNumPassengers();
   }
   
   /**
    * Drive all the cars to their destination.
    */
   public void driveAllCars()
   {
      while (cars.size() > 0)
      {
         for (int i = cars.size() - 1; i >= 0; i--)
         {
            Car car = cars.get(i);
            loadPassengers(stations[car.getLocation() - 1], car);
            car.drive(busyHour());
            car.dropOff();
            if (car.outOfGas())
            {
               faresCollected += car.getRevenue();
               milesDriven += car.getMilesDriven();
               cars.remove(i);
            }
         }
      }
   }
   
   /**
    * Gets priority request from list 
    * @param station the station
    * @param car the car
    */
   public int getPriorityRequest(Station station, Car car)
   {
      int points = 0;
      int maxPoints = 0;
      int maxIndex = 0;
      for (int i = station.getRequests().size() - 1; i >= 0; i--)
      {
         if (car.getType() == station.getRequest(i).getCarType())
         {
            points += 3;
         }
         if (car.getDestination() - station.getRequest(i).getDestination() > 4 ||
         car.getDestination() - station.getRequest(i).getDestination() < -4)
         {
            points += 1;
         }
         if (station.getRequest(i).getVipStatus())
         {
            points += 5;
         }
         if (points > maxPoints)
         {
            maxIndex = i;
            maxPoints = points;
         }
      }
      return maxIndex;
   }

   /**
    * Pick up passengers from a station.
    * @param station the station to load passengers from.
    * @param car the car that we want to load the passengers into.
    */
   public void loadPassengers(Station station, Car car)
   {
      if (car.hasRoom() && station.hasPeopleWaiting())
      {
         car.add(station.remove(getPriorityRequest(station, car)));
      }
   }
}