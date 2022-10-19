import java.util.Random;

// Name: nfan17
// Description: Car Share System
// Input: N/A
// Output: Simulation results: Revenue per Mile, total miles, total revenue, passengers remaining
// Processing: Uses car, station, passenger, and request objects to create car sharing simulation
// Last Changed: 8/4/2022

    /**
     * Tester class to verify that simulation works
     */
    public class Test 
    {
        public static void main(String[] args)
    {
        Random rand = new Random();
        Simulation carShare = new Simulation(rand);
        carShare.loadRandomScenario(20);
        System.out.println(carShare.getInitialState());
        carShare.driveAllCars();
        System.out.println(carShare.getFinalState());
        
    }
}

/*
Sample output: (busy hour)
NUM PASSENGERS: 3080 NUM CARS: 64
AFTER SIMULATION:
RPM: 11.162610133425519, MILES: 241500, FARES: 2695770.3472222625, PASSENGERS REMAINING: 436
 */