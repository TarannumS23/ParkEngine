/*
    ParkingLot Automation System

    Step 1 : Create requred enums
    Step 2 : Vehicle Hierarchy creation 
    Step 3 : VehicleFactory creation (Factory Pattern)
    Step 4 : ParkingSpot hierarchy
    Step 5 : ParkingObserver
    Step 6 : ParkingFloor class
    Step 7 : ParkingDisplayBoard (Observer Pattern)
    Step 8 : ParkingStrategy class (Strategy Pattern)
    Step 9 : PricingStrategy class (Strategy Pattern)
    Step 10 : PaymentStrategy class
    Step 11 : ParkingTicket class 
    Step 12 : EntryGate class
    Step 13 : ExitGate class
    Step 14 : ParkingLot class (Singleton Pattern)
    Step 15 : Main class (Controller)
*/

import java.util.*;
import java.time.Duration;             // this will provide difference between two dates
import java.time.LocalDateTime;

///////////////////////////////////////////////////////////////
///  Step 1 : Create Enums
///  It is used to create fixed constants which are required
///  througout the project
///////////////////////////////////////////////////////////////

// Represents the different types of vehicles supported by the project
enum VehicleType
{
    BIKE,
    CAR,
    TRUCK
}

// Represents different types of parking spots 
enum SpotType
{
    BIKE,
    CAR,
    TRUCK
}

// Represents the current state of parking ticket
enum TicketStatus
{
    ACTIVE,
    CLOSED
}

///////////////////////////////////////////////////////////////
///  Step 2 : Create Vehicle Class Hierarchy
///  It is used to create multiple types of classes which 
///  represents the types of vehicles
///  Concepts : Abstraction, Inheritance, Polyorphism, Encapsulation
///////////////////////////////////////////////////////////////

// class which represents a generic vehicle type
abstract class Vehicle
{
    // Abstracted (Hidden) characteristics of class

    private String vehicleNumber;

    private VehicleType vehicleType;

    // Parametrised constructor
    public Vehicle(String vehicleNumber, VehicleType vehicleType)     // this constructor will gets called when any derived class will call
    {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType; 
    }

    // Concrete getter method
    public VehicleType getVehicleType()      // getter method is conpulsory coz the characteristrics are private
    {
        return this.vehicleType;
    }

    // Concrete getter method
    public String getVehicleNumber()
    {
        return this.vehicleNumber;
    }

    // Every concrete class will provide its own defination
    public abstract void display();
    
}

// Class which represents the vehicle type as Bike
class Bike extends Vehicle
{
    public Bike(String vehicleNumber)
    {
        // Calls Vehicle class constructor
        super(vehicleNumber, VehicleType.BIKE);
    }

    // Method overriding
    @Override 
    public void display()
    {
        System.out.println("Bike : "+getVehicleNumber());
    }
}

// Class which represents the vehicle type as Car
class Car extends Vehicle
{
    public Car(String vehicleNumber)
    {
        // Calls Vehicle class constructor
        super(vehicleNumber, VehicleType.CAR);
    }

    // Method overriding
    @Override 
    public void display()
    {
        System.out.println("Car : "+getVehicleNumber());
    }
}

// Class which represents the vehicle type as Truck
class Truck extends Vehicle
{
    public Truck(String vehicleNumber)
    {
        // Calls Vehicle class constructor
        super(vehicleNumber, VehicleType.TRUCK);
    }

    // Method overriding
    @Override 
    public void display()
    {
        System.out.println("Truck : "+getVehicleNumber());
    }
}

///////////////////////////////////////////////////////////////
///  Step 3 : Create VehicleFactory class
///  It is used to centralised the creation of vehicle objects
///  Concepts : Factory Design Pattern
///////////////////////////////////////////////////////////////

class VehicleFactory
{
    // Creates and return the desired class object

    public static Vehicle createVehicle(VehicleType type, String number)             // static method so no need to create object
    {
        switch(type)
        {
            case BIKE:
                return new Bike(number);

            case CAR:
                return new Car(number);

            case TRUCK:
                return new Truck(number);

            default:
                throw new IllegalArgumentException("Invalid Vehicle type");
        }
    }
}

///////////////////////////////////////////////////////////////
///  Step 4 : Create ParkingSpot Heirarchy
///  It is used to create heirarchy of parking spots
///  Concepts : Encapsulation, Abstration, Polymorphism, Inheritance
///////////////////////////////////////////////////////////////

abstract class ParkingSpot
{
    // Unique number for parking spot (Primary Key)
    private int spotNumber;

    // Type of Parking spot
    private SpotType spotType;

    // Indicates whether spot is currently occupied or not
    private boolean occupied;

    // Stores information about the vehicle
    private Vehicle vehicle;

    // Parametrised constructor
    public ParkingSpot(int spotNumber, SpotType spotType)
    {
        this.spotNumber = spotNumber;
        this.spotType = spotType;

        // Initialised with default values
        this.occupied = false;
        this.vehicle = null;
    }

    public int getSpotNumber()
    {
        return this.spotNumber;
    }

    public SpotType getSpotType()
    {
        return this.spotType;
    }

    public boolean isOccupied()
    {
        return this.occupied;
    }

    public Vehicle getVehicle()
    {
        return this.vehicle;
    }

    // It is used to park the vehicle
    public void parkVehicle(Vehicle vehicle)
    {
        if(this.occupied == true)
        {
            throw new RuntimeException("Parking spot is already occupied");
        }
        else
        {
            this.vehicle = vehicle;
            this.occupied = true;
        }
    }

    public Vehicle removeVehicle()
    {
        if(this.occupied == true)
        {
            Vehicle temp = vehicle;  

            this.vehicle = null;
            this.occupied = false;

            return temp;
        }
        else
        {
            throw new RuntimeException("Parking spot is already empty");
        }
    }

    // this method decides whether we can park it in the spot or not
    public abstract boolean canFitVehicle(Vehicle vehicle);

    public void display()
    {
        System.out.println("Spot : "+spotNumber+ "["+ spotType +"]");

        if(this.occupied == true)
        {
            System.out.println("Occupied by : "+vehicle.getVehicleNumber());
        }
        else
        {
            System.out.println("Spot is available");
        }
    }
} // end of ParkingSpot class

class BikeSpot extends ParkingSpot
{
    public BikeSpot(int spotNumber)
    {
        super(spotNumber, SpotType.BIKE);
    }

    @Override 
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.BIKE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

class CarSpot extends ParkingSpot
{
    public CarSpot(int spotNumber)
    {
        super(spotNumber, SpotType.CAR);
    }

    @Override 
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.CAR)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

class TruckSpot extends ParkingSpot
{
    public TruckSpot(int spotNumber)
    {
        super(spotNumber, SpotType.TRUCK);
    }

    @Override 
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.TRUCK)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

///////////////////////////////////////////////////////////////
///  Step 5 : Create ParkingObserver class
///  It is used to automatically update display board when 
///  the parking availability changes.
///  Concepts : Observer Design Pattern
///////////////////////////////////////////////////////////////

interface ParkingObserver
{
    void update();
}

///////////////////////////////////////////////////////////////
///  Step 6 : Create ParkingFloor class
///  It is used to manage parking floor
///  Concepts : Composition, ArrayList, Object Management
///////////////////////////////////////////////////////////////

class ParkingFloor
{
    // Unique floor number
    private int floorNumber;

    // Collection of all parking spots
    private List<ParkingSpot> parkingSpots;

    // Collection of observers registered for the floor
    private List<ParkingObserver> observers;

    public ParkingFloor(int floorNumber)
    {
        this.floorNumber = floorNumber;

        this.parkingSpots = new ArrayList<>();

        this.observers = new ArrayList<>();
    }

    public int getFloorNumber()
    {
        return this.floorNumber;
    }

    public void addParkingSpot(ParkingSpot spot)
    {
        parkingSpots.add(spot);
    }

    public void addObserver(ParkingObserver observer)
    {
        observers.add(observer);
    }

    private void notifyObservers()
    {
        for(ParkingObserver observer : observers)
        {
            observer.update();
        }
    }

    // Method is going to search parking spot for specific type of vehicle
    public ParkingSpot findAvailableSpot(Vehicle vehicle)      // vehicle type
    {
        for(ParkingSpot spot : parkingSpots)
        {
            if(!spot.isOccupied() && spot.canFitVehicle(vehicle))
            {
                return spot;
            }
        }

        return  null;
    }

    // Called when new vehicle gets parked
    public void occupySpot(ParkingSpot spot, Vehicle vehicle)
    {
        // Allocate spot for the vehicle 
        spot.parkVehicle(vehicle);

        // Notify all observers about the availability of spots
        notifyObservers();
    }

    public void releaseSpot(ParkingSpot spot)
    {
        // Release the already allocated spot
        spot.removeVehicle();

        // Notify all observers about the availability of spots
        notifyObservers();
    }

    public int getAvailableCount(SpotType type)
    {
        int count = 0;

        for(ParkingSpot spot : parkingSpots)
        {
            if(spot.getSpotType() == type && !spot.isOccupied())
            {
                count++;
            }
        }

        return count;
    }

    // Display all parking spots on specific floor
    
    public void displayFloor()
    {
        System.out.println();

        System.out.println("Floor : "+floorNumber);

        for(ParkingSpot spot : parkingSpots)
        {
            spot.display();
        }
    }
    
}

///////////////////////////////////////////////////////////////
///  Step 7 : Create ParkingDisplayBoard class
///  It is used to create a class which displays the parking 
///  status 
///  Subject  -> ParkingFloor
///  Observer -> ParingDisplayBoard
///  Concepts : Observer Design Pattern
///////////////////////////////////////////////////////////////

class ParkingDisplayBoard implements ParkingObserver
{
    // Floor whose availability is diplayed by this board
    private ParkingFloor floor;

    // Constructor
    public ParkingDisplayBoard(ParkingFloor floor)
    {
        this.floor = floor;
    }

    // Automatically called whenever floor availability changes
    @Override 
    public void update()
    {
        System.out.println();
        System.out.println("-------------- Display Board --------------");

        System.out.println("Floor : "+floor.getFloorNumber());

        System.out.println("Available Bike Spots : "+floor.getAvailableCount(SpotType.BIKE));

        System.out.println("Available Car Spots : "+floor.getAvailableCount(SpotType.CAR));

        System.out.println("Available Truck Spots : "+floor.getAvailableCount(SpotType.TRUCK));

        System.out.println("-------------------------------------------");
        System.out.println();
    }
}


///////////////////////////////////////////////////////////////
///  Step 8 : Create ParkingStrategy class
///  It is used to create a class Parkingstrategy which is 
//   responsible to decide the parking spot selection
///  Concepts : Strategy Design Pattern
///////////////////////////////////////////////////////////////

// Defines a common concepts for parking spot selection algorithm
interface ParkingStrategy
{
    ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle);
    
}

// Selects the first available parking spot
class FirstAvailableParkingStrategy implements ParkingStrategy
{
    @Override 
    public ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle)
    {
        // Iterate over all available floors
        for(ParkingFloor floor : floors)
        {
            ParkingSpot spot = floor.findAvailableSpot(vehicle);

            if(spot != null)
            {
                return spot;
            }
        }

        return null;
    }
}

///////////////////////////////////////////////////////////////
///  Step 9 : Create PricingStrategy class

///  It is used to create a class PricingStrategy 
///  It keeps the pricing algorithm independent of exit logic

///  Concepts : Strategy Design Pattern
///////////////////////////////////////////////////////////////

interface PricingStrategy 
{
    double calculatePrice(Vehicle vehicle, long hours);
}

class NormalPricingStrategy implements PricingStrategy
{
    @Override 
    public double calculatePrice(Vehicle vehicle, long hours)
    {
        if(hours <= 0)
        {
            hours = 1;
        }

        switch(vehicle.getVehicleType())
        {
            case BIKE:
                return hours * 20;

            case CAR:
                return hours * 50;

            case TRUCK:
                return hours * 100;

            default:
                return 0;
        }
    }
}

class WeekendPricingStrategy implements PricingStrategy
{
    @Override 
    public double calculatePrice(Vehicle vehicle, long hours)
    {
        if(hours <= 0)
        {
            hours = 1;
        }

        switch(vehicle.getVehicleType())
        {
            case BIKE:
                return hours * 40;

            case CAR:
                return hours * 100;

            case TRUCK:
                return hours * 200;

            default:
                return 0;
        }
    }
}

///////////////////////////////////////////////////////////////
///  Step 10 : Create PaymentStrategy class

///  It is used to create a class PaymentStrategy 
///  It supports different types of payment methods

///  Concepts : Strategy Design Pattern
///////////////////////////////////////////////////////////////

// Common contract for all payment methods
interface PaymentStrategy
{
    void pay(double amount);
}

class UPIPayment implements PaymentStrategy
{
    @Override 
    public void pay(double amount)
    {
        System.out.println("UPI Payment Successfull : Rs. "+amount);
    }
}

class CardPayment implements PaymentStrategy
{
    @Override 
    public void pay(double amount)
    {
        System.out.println("Card Payment Successfull : Rs. "+amount);
    }
}

class CashPayment implements PaymentStrategy
{
    @Override 
    public void pay(double amount)
    {
        System.out.println("Cash Payment Successfull : Rs. "+amount);
    }
}

///////////////////////////////////////////////////////////////
///  Step 11 : Create ParkingTicket class

///  It is used to represent one complete parking transaction 

///////////////////////////////////////////////////////////////

class ParkingTicket
{
    // Used for generating unique tickets
    private static int counter = 1000;

    // Ticket number for unique ticket
    private int ticketNumber;

    // Vehicle associated with that ticket
    private Vehicle vehicle;

    // Floor on which the vehicle is parked
    private ParkingFloor floor;

    // Actual spot on which the vehicle is parked
    private ParkingSpot spot;

    // Time at which vehicle arrives
    private LocalDateTime entryTime;

    // Time at which vehicle exited from parking floor
    private LocalDateTime exitTime;

    // It maintains the status of the ticket
    private TicketStatus status;

    // parametrized constructor
    public ParkingTicket(
                            Vehicle vehicle,
                            ParkingFloor floor,
                            ParkingSpot spot
                        )
    {
        this.ticketNumber = ++counter;
        this.vehicle = vehicle;
        this.floor = floor;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
        this.status = TicketStatus.ACTIVE;
    }

    // Getter method for ticket number
    public int getTicketNumber()
    {
        return this.ticketNumber;
    }

    // Getter method for vehicle
    public Vehicle getVehicle()
    {
        return this.vehicle;
    }

    // Getter method for floor
    public ParkingFloor getFloor()
    {
        return this.floor;
    }

    // Getter method for spot
    public ParkingSpot getSpot()
    {
        return this.spot;
    }

    // Getter method for entry time
    public LocalDateTime getEntryTime()
    {
        return this.entryTime;
    }

    // Getter method for exit time
    public LocalDateTime getExitTime()
    {
        return this.exitTime;
    }

    // Getter method for status
    public TicketStatus getStatus()
    {
        return this.status;
    }

    // Method gets called when vehicle is going out
    public void closeTicket()
    {
        this.exitTime = LocalDateTime.now();

        this.status = TicketStatus.CLOSED;
    }

    // Calculate the total number of hours the vehicle is parked
    public long calculateHours()
    {
        LocalDateTime endtime;

        if(exitTime == null)
        {
            endtime = LocalDateTime.now();
        }
        else
        {
            endtime = exitTime;
        }

        // Calculate the actual time 
        long minutes = Duration.between(entryTime, endtime).toMinutes();

        // Converts minutes to hours 
        long hours = minutes / 60;

        if(minutes % 60 != 0)
        {
            hours++;
        }

        if(hours == 0)
        {
            hours = 1;
        }

        return hours;
    }

    // It will display complete ticket on screen
    public void displayTicket()
    {
        System.out.println();
        
        System.out.println("-------------------------------------------");
        System.out.println("-------------- Parking Ticket -------------");
        System.out.println("-------------------------------------------");

        System.out.println("Ticket Number : "+this.ticketNumber);

        System.out.println("Vehicle Number : "+this.vehicle.getVehicleNumber());

        System.out.println("Vehicle Type : "+this.vehicle.getVehicleType());

        System.out.println("Floor Number : "+this.floor.getFloorNumber());

        System.out.println("Spot Number : "+this.spot.getSpotNumber());

        System.out.println("Entry Time : "+this.entryTime);

        System.out.println("Ticket Status : "+this.status);

        System.out.println("-------------------------------------------");

        System.out.println();
    }
}

///////////////////////////////////////////////////////////////
///  Step 12 : Create EntryGate class

///  It is used to handle entry of a vehicle and its ticket 
///  generation 

///////////////////////////////////////////////////////////////

class EntryGate
{
    private int gateNumber;

    public EntryGate(int gateNumber)
    {
        this.gateNumber = gateNumber;
    }

    public int getGateNumber()
    {
        return this.gateNumber;
    }

    // It generates the new parking ticket when vehicle enters
    public ParkingTicket generateTicket(Vehicle vehicle, ParkingFloor floor, ParkingSpot spot)
    {
        System.out.println("Vehicle Entering from gate : "+this.gateNumber);

        // New Parking ticket gets generated for the vehicle
        return new ParkingTicket(vehicle, floor, spot);
    }
}

///////////////////////////////////////////////////////////////
///  Step 13 : Create ExitGate class

///  It is used to handle billing and payment during the 
///  vehicle exit

///////////////////////////////////////////////////////////////

class ExitGate
{
    private int gateNumber;

    public ExitGate(int gateNumber)
    {
        this.gateNumber = gateNumber;
    }

    public int getGateNumber()
    {
        return this.gateNumber;
    }

    // This performs complete exit operations
    public void processExit(
                                ParkingTicket ticket, 
                                PricingStrategy pricingStrategy,
                                PaymentStrategy paymentStrategy
                            )
    {
        // Step 1 : Close the ticket and record the exit time
        ticket.closeTicket();

        // Step 2 : Calculate the parking duration 
        long hours = ticket.calculateHours();

        // Step 3 : Calculate the parking charges
        double amount = pricingStrategy.calculatePrice(ticket.getVehicle(), hours);

        System.out.println();

        System.out.println("Vehicle exiting from gate : "+gateNumber);

        System.out.println("Parking Diuration : "+hours);

        System.out.println("Parking Charges : "+amount);

        // Step 4 : Process the payment using selected payment strategy

        paymentStrategy.pay(amount);
    }

}

///////////////////////////////////////////////////////////////
///  Step 14 : Create ParkingLot Class

///  Pattern : Singleton Pattern

///  This class is the main controller of complete parking 
///  System

///////////////////////////////////////////////////////////////

// Singleton class
class ParkingLot
{       
    // instance of class
    
    private static ParkingLot instance;

    // Store the parking Lot name
    
    private String parkingLotName;

    // Store all floors of the parking lot
    
    private List<ParkingFloor> floors;

    // Maps the ticket number with active parking slot
   
    private Map<Integer, ParkingTicket> activeTickets;

    // Maps vehicle number with active tickets 
    // Used for searching vehicle 
    // It prevents duplicate parking 
    
    private Map<String, ParkingTicket> vehicleTicketMap;

    // Algorithm used fro selecting parking spot
    
    private ParkingStrategy parkingStrategy;

    // Algorithm used for calculating parking charges
    
    private PricingStrategy pricingStrategy;

    // Private constructor for singleton class

    private ParkingLot()
    {
        floors = new ArrayList<>();

        activeTickets = new HashMap<>();

        vehicleTicketMap = new HashMap<>();

        // Default parking strategy

        parkingStrategy = new FirstAvailableParkingStrategy();

        // Defalut pricing strategy

        pricingStrategy = new NormalPricingStrategy();

    }

    // Method to return the singleton class object

    public static synchronized ParkingLot getInstance()
    {
        if(instance == null)
        {
            instance = new ParkingLot();
        }

        return instance;
    }

    // used to set name for complete parking lot

    public void setParkingLotName(String parkingLotName)
    {
        this.parkingLotName = parkingLotName;
    }

    // Used to add new parking floor

    public void addFloor(ParkingFloor floor)
    {
        // Insert in arraylist

        floors.add(floor);
    }

    // this method returns list of all floors
    public List<ParkingFloor> getFloors()
    {
        return floors;
    }

    // This method can be used to change the default parking strategy

    public void setParkingStrategy(ParkingStrategy strategy)
    {
        this.parkingStrategy = strategy;
    }

    // This method can be used to change the default pricing strategy

    public void setPricingStrategy(PricingStrategy strategy)
    {
        this.pricingStrategy = strategy;
    }



    public ParkingTicket parkVehicle(
                                        Vehicle vehicle,
                                        EntryGate entryGate
    )
    {
        // Step 1 : Prevent the same vehicle for being parked multiple times

        if(vehicleTicketMap.containsKey(vehicle.getVehicleNumber()))
        {
            System.out.println("This vehicle is already parked");

            throw new RuntimeException("This vehicle is already parked");
        }

        // Step 2 : Find the available spot

        ParkingSpot spot = parkingStrategy.findSpot(floors, vehicle);

        // If there is no empty spot 

        if(spot == null)
        {
            throw new RuntimeException("Parking is full");
        }

        // Step 3 : Identify the exact floor for the vehicle

        ParkingFloor selectedFloor = null;

        for(ParkingFloor floor : floors)
        {
            ParkingSpot temp = floor.findAvailableSpot(vehicle);

            if(temp == spot)
            {
                selectedFloor = floor;
                break;
            }
        }

        if(selectedFloor == null)
        {
            throw new RuntimeException("Unable to identify floor");
        }

        // Step 4 : Occupy the spot

        selectedFloor.occupySpot(spot, vehicle);
        
        // Step 5 : Generate parking ticket from entry gate

        ParkingTicket ticket = entryGate.generateTicket(vehicle, selectedFloor, spot);

        // Step 6 : Store the ticket using ticket number

        activeTickets.put(ticket.getTicketNumber(), ticket);

        // Step 7 : Store the ticket using ticket number

        vehicleTicketMap.put(vehicle.getVehicleNumber(), ticket);

        return ticket;
    }

    public void removeVehicle(
                                int ticketNumber,
                                ExitGate exitGate,
                                PaymentStrategy paymentStrategy
    )
    {
        // Step 1 : Find active ticket using ticket number

        ParkingTicket ticket = activeTickets.get(ticketNumber);

        if(ticket == null)
        {
            throw new RuntimeException("There is no such ticket");
        }

        // Step 2 : Perform billing and payment

        exitGate.processExit(ticket, pricingStrategy, paymentStrategy);

        // Step 3 : Release the occupied spot
        ticket.getFloor().releaseSpot(ticket.getSpot());

        // Step 4 : Remove ticket
        activeTickets.remove(ticketNumber); 

        // Step 5 : Remove vehicle from active vehicle
        vehicleTicketMap.remove(ticket.getVehicle().getVehicleNumber());

        System.out.println("Vehicle removed successfully");
    }

    // Search the specified method
    public ParkingTicket searchVehicle(String vehicleNumber)
    {
        return vehicleTicketMap.get(vehicleNumber);
    }

    // Display complete parking lot information
    public void displayParkingLot()
    {
        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println("----------- Parking Lot Details -----------");
        System.out.println("-------------------------------------------");

        for(ParkingFloor floor : floors)
        {
            floor.displayFloor();
        }
    }
} // End of parkingLot class


class ParkEngine
{
    public static void main(String A[]) 
    {
        
    }
}