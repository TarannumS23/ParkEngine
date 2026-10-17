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

class ParkEngine
{
    public static void main(String A[]) 
    {
        
    }
}