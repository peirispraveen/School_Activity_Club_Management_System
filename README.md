# School Activity Club Management System (SACMS)

## 📋 Project Overview

A comprehensive **Java-based desktop application** for managing school activity clubs, built using Object-Oriented Programming principles and JavaFX. The system facilitates club creation, event scheduling, student registration, attendance tracking, and reporting for extracurricular activities.


## 🎯 Project Objectives

The SACMS system aims to:
- Enable club advisors to create and manage club profiles
- Allow club advisors to schedule events, meetings, and activities
- Facilitate student registration and club membership
- Provide attendance tracking mechanisms for club events
- Generate comprehensive reports on club activities

## ✨ Key Features

### Core Functionality
- ✅ **Club Creation** - Advisors can create and manage club profiles
- ✅ **Event Scheduling** - Schedule events, meetings, and activities
- ✅ **User Registration** - Students and advisors can register with the system
- ✅ **Club Membership** - Students can join multiple clubs
- ✅ **Attendance Tracking** - Track attendance at club events
- ✅ **Reporting** - Generate reports on membership, attendance, and activities
- ✅ **Data Export** - Export reports to Excel format

### Technical Features
- **Database Integration** - MySQL database for persistent storage
- **JavaFX GUI** - Modern graphical user interface
- **Data Serialization** - Backup data storage using serialization
- **Input Validation** - Comprehensive validation for all inputs
- **Exception Handling** - Robust error handling throughout
- **Unit Testing** - JUnit test suite for validation

## 🛠️ Technology Stack

- **Language:** Java 17
- **Framework:** JavaFX 20.0.2
- **Build Tool:** Maven
- **Database:** MySQL 8.0
- **Testing:** JUnit 5.9.2
- **Libraries:**
  - Apache POI 5.2.4 (Excel export)
  - MySQL Connector/J 8.0.33
  - Ikonli (Icon library)
  - Log4j 2.20.0 (Logging)

## 📁 Repository Structure

```
School_Activity_Club_Management_System/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/example/
│   │   │   │   ├── sacms/              # Main application & user registration
│   │   │   │   ├── implementation/     # Club creation & management
│   │   │   │   ├── EventScheduling/   # Event scheduling module
│   │   │   │   └── demo/              # Attendance tracking & reporting
│   │   │   └── module-info.java
│   │   └── resources/
│   │       ├── com/example/           # FXML layouts & CSS
│   │       ├── Images/                # Application images
│   │       ├── Fonts/                 # Custom fonts
│   │       └── Styles/                # CSS stylesheets
├── Test/                              # Unit tests
│   └── com/example/sacms/
├── lib/                               # External libraries
├── target/                            # Build output
├── pom.xml                            # Maven configuration
├── mysql-connector-j-8.2.0.jar       # MySQL driver
```

## 🏗️ System Architecture

### Package Structure

#### `com.example.sacms`
Main application package containing:
- **UserRegApplication** - Application entry point
- **DBConnect** - Database connection and authentication
- **Student** - Student entity class
- **Advisor** - Advisor entity class
- **Member** - Club membership management
- **JoinClub** - Student club joining functionality
- **ReportGen** - Report generation utilities

#### `com.example.implementation`
Club management package containing:
- **CreateClub** - Club creation functionality
- **Club** - Club entity class
- **ClubAdvisor** - Club advisor management
- **ClubMember** - Club membership handling
- **DeleteClub** - Club deletion
- **UpdateMembers** - Member management
- **GenerateReport** - Club reporting

#### `com.example.EventScheduling`
Event scheduling package containing:
- **Event** - Event entity class
- **Activity** - Activity entity class
- **Meeting** - Meeting entity class
- **EventParent** - Abstract parent class
- **EventValidator** - Event validation
- **EventView** - Event display
- **PostponeEvent** - Event postponement

#### `com.example.demo`
Attendance and reporting package containing:
- **Submit** - Attendance submission
- **Check** - Attendance checking
- **Chart** - Data visualization
- **Export_Student** - Student data export
- **Export_Teacher** - Teacher data export
- **Update** - Data updates


## 📊 System Modules

### 1. User Registration Module
- Student registration
- Advisor registration
- User authentication
- Profile management

### 2. Club Management Module
- Club creation with validation
- Club profile management
- Advisor assignment
- Member management
- Club deletion

### 3. Event Scheduling Module
- Event creation (Events, Activities, Meetings)
- Event validation
- Event postponement
- Event viewing
- Concurrent validation using threads

### 4. Attendance Tracking Module
- Attendance submission
- Attendance checking
- Attendance validation
- Attendance reports

### 5. Reporting Module
- Membership reports
- Attendance reports
- Club activity reports
- Excel export functionality
- Data visualization (charts)

## 🔍 Key Classes

### Entity Classes
- **Student** - Represents student users
- **Advisor** - Represents club advisors
- **Club** - Represents activity clubs
- **Member** - Represents club membership
- **Event** - Represents scheduled events
- **Activity** - Represents club activities
- **Meeting** - Represents club meetings

### Controller Classes
- **UserRegMainController** - User registration controller
- **ClubController** - Club management controller
- **EventController** - Event scheduling controller

### Utility Classes
- **DBConnect** - Database connection management
- **Storage** - Data storage utilities
- **Regex** - Input validation using regular expressions
- **DateOfBirth** - Date handling utilities
- **ReportGen** - Report generation


## 📈 OOP Concepts Implemented

- **Encapsulation** - Private attributes with getters/setters
- **Inheritance** - EventParent → Event, Activity, Meeting
- **Polymorphism** - Abstract methods, method overriding
- **Abstraction** - Abstract classes and interfaces
- **Exception Handling** - Try-catch blocks, custom exceptions
- **Concurrent Programming** - Thread usage in event validation


---

**Note:** This is a group project developed for academic purposes. The system demonstrates practical application of Object-Oriented Programming principles, UML design, database connectivity, and GUI development in Java.
