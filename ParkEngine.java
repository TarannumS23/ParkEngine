/*
    ParkingLot Automation System

    Step 1 : Create requred enums                         [DONE]
    Step 2 : Vehicle Hierarchy creation                   [DONE]
    Step 3 : VehicleFactory creation (Factory Pattern)    [IN PROGRESS]
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
        if (vehicleNumber == null || vehicleNumber.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Vehicle number cannot be empty");
        }

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

class ParkEngine
{
    public static void main(String A[]) 
    {
      
    }
}