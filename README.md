# ParkEngine — Scalable Parking Allocation System

ParkEngine is a Java-based, console-driven parking allocation system designed to demonstrate object-oriented programming, modular system architecture, and software design patterns.

The application manages vehicle parking across multiple floors, generates parking tickets, calculates parking charges, simulates payment processing, and automatically updates parking availability.

## Design Patterns

- **Singleton Pattern:** Provides centralized access to a single `ParkingLot` instance.
- **Factory Pattern:** Creates Bike, Car, and Truck objects through `VehicleFactory`.
- **Observer Pattern:** Automatically updates parking display boards when parking spots are occupied or released.
- **Strategy Pattern:** Separates parking allocation, pricing calculations, and payment behaviors into interchangeable implementations.

## Core Features

- Supports three vehicle types: Bike, Car, and Truck.
- Manages multiple parking floors and vehicle-specific parking spots.
- Automatically allocates compatible available parking spots.
- Generates unique parking tickets containing vehicle, floor, spot, and entry-time information.
- Tracks active parking tickets and prevents duplicate active parking for the same vehicle number.
- Calculates parking duration and charges based on vehicle type.
- Implements normal and weekend pricing strategies.
- Simulates Cash, UPI, and Card payments.
- Automatically updates parking availability through the Observer pattern.
- Supports searching for parked vehicles.
- Provides an interactive console menu for parking operations.

## Technology Stack

- **Language:** Java
- **Core Concepts:** Object-Oriented Programming, System Design, Design Patterns
- **Data Structures:** ArrayList, HashMap
- **Java APIs:** Collections Framework, LocalDateTime, Duration
- **Interface:** Console-based application
- **Development Tools:** VS Code, Git, GitHub

## Architecture

ParkEngine separates responsibilities across specialized components:

| Component | Responsibility |
|---|---|
| Vehicle and VehicleFactory | Vehicle representation and object creation |
| ParkingSpot | Vehicle-specific parking space management |
| ParkingFloor | Parking spot organization and availability tracking |
| ParkingDisplayBoard | Availability notifications |
| ParkingStrategy | Parking spot selection |
| PricingStrategy | Parking fee calculation |
| PaymentStrategy | Simulated payment processing |
| ParkingTicket | Entry records and parking duration |
| EntryGate and ExitGate | Vehicle entry, exit, and billing |
| ParkingLot | Central parking operations and active ticket management |
| ParkEngine | Interactive console controller |

The modular design allows individual behaviors to be extended without rewriting the entire application.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- Terminal or command prompt

### Clone the Repository

```bash
git clone https://github.com/TarannumS23/ParkEngine.git
cd ParkEngine
```

### Compile

```bash
javac ParkEngine.java
```

### Run

```bash
java ParkEngine
```

### Application Menu

```text
------------ Marvellous ParkEngine ------------
1 : Park Vehicle
2 : Exit Vehicle
3 : Search Vehicle
4 : Display Parking Lot
5 : Exit
```

Follow the on-screen instructions to perform parking operations.

## Testing

The following scenarios have been manually tested:

- Vehicle parking and ticket generation
- Searching for an active parking ticket
- Displaying occupied and available parking spots
- Vehicle exit and simulated UPI payment
- Automatic parking spot release
- Prevention of duplicate active parking
- Searching for a vehicle that is not parked
- Handling invalid menu selections
- Exiting the application

Automated unit testing and additional edge-case validation are planned for future development.

## Project Status

**Current version:** Functional Java console application.

The core implementation and interactive menu are complete. Primary parking operations have been manually tested.

The project currently uses in-memory data structures and simulated payment processing.

## Future Improvements

- Develop a graphical interface using Java Swing.
- Introduce automated unit tests using JUnit.
- Improve input validation and exception handling.
- Add database persistence for parking records.
- Explore multithreading and concurrency handling.
- Evaluate performance with larger parking configurations.

---

**Project:** ParkEngine  
**Focus:** Object-Oriented System Design and Design Patterns  
**Language:** Java  
**Application Type:** Console-Based Parking Management System

---

## Author

**Tarannum Shaikh**

- GitHub: [@TarannumS23](https://github.com/TarannumS23)
- Project: [ParkEngine](https://github.com/TarannumS23/ParkEngine)