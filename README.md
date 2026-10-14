# ParkEngine — Scalable Parking Allocation System

ParkEngine is a Java-based parking allocation system focused on object-oriented system design, extensible architecture, and design patterns. It explores how modular components interact to support multi-floor parking allocation, vehicle ticketing, pricing, and parking availability updates.

## Design Patterns

- **Singleton:** Centralizes access to the parking lot instance.
- **Factory:** Creates vehicle objects based on vehicle type.
- **Observer:** Updates the parking display when availability changes.
- **Strategy:** Separates parking allocation, pricing, and payment behaviors.

## Core Features

- Supports multiple vehicle types: Bike, Car, and Truck.
- Allocates compatible parking spots across multiple floors.
- Generates parking tickets and tracks vehicle entry and exit.
- Calculates parking duration and parking fees.
- Supports configurable parking allocation and pricing strategies.
- Provides simulated Cash, UPI, and Card payment options.
- Updates parking availability through the Observer pattern.

## Technology Stack

- **Language:** Java
- **Concepts:** Object-Oriented Programming, System Design, Design Patterns
- **Interface:** Console-based application
- **Planned enhancement:** Java Swing graphical user interface

## Architecture

The system separates parking operations from vehicle creation, parking allocation, pricing, payment, and availability notifications. This modular approach makes the design easier to understand and provides opportunities to extend individual behaviors.

## Project Status

The core Java implementation is being organized for publication on GitHub. Documentation, testing, and a Java Swing interface are planned as subsequent development milestones.

## Future Improvements

- Add a graphical interface using Java Swing.
- Expand automated testing and input validation.
- Explore persistence for parking records.
- Investigate concurrency handling and performance under larger workloads.

## Getting Started

Setup and execution instructions will be added alongside the source code as the repository is organized.

---

**Project:** ParkEngine  
**Focus:** Object-Oriented System Design and Design Patterns