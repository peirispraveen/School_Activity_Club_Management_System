# Source Code Directory

This directory contains the main source code for the School Activity Club Management System.

## 📁 Structure

```
src/
├── main/
│   ├── java/
│   │   ├── com/example/
│   │   │   ├── sacms/              # Main application package
│   │   │   ├── implementation/     # Club management package
│   │   │   ├── EventScheduling/   # Event scheduling package
│   │   │   └── demo/              # Attendance & reporting package
│   │   └── module-info.java       # Java module descriptor
│   └── resources/
│       ├── com/example/           # FXML layouts and CSS
│       ├── Images/                 # Application images
│       ├── Fonts/                  # Custom fonts
│       └── Styles/                 # CSS stylesheets
└── School_Activity_Club_Management_System.iml
```

## 📦 Package Descriptions

### `com.example.sacms`
**Main Application Package**

Core application functionality including:
- **UserRegApplication** - Application entry point and JavaFX initialization
- **DBConnect** - Database connection management and authentication
- **Student** - Student entity class with attributes and methods
- **Advisor** - Advisor entity class
- **Member** - Club membership management
- **JoinClub** - Student club joining functionality
- **AddMember** - Adding members to clubs
- **ReportGen** - Report generation utilities
- **Regex** - Input validation using regular expressions
- **DateOfBirth** - Date handling and validation
- **ViewStudents** - Student viewing functionality
- **ViewAdvisors** - Advisor viewing functionality
- **EventController** - Event management controller
- **ClubController** - Club management controller
- **UserRegMainController** - User registration main controller
- **Driver** - Database driver initialization

### `com.example.implementation`
**Club Management Package**

Club creation and management functionality:
- **CreateClub** - Club creation with validation
- **Club** - Club entity class
- **ClubAdvisor** - Club advisor entity
- **ClubMember** - Club membership entity
- **DeleteClub** - Club deletion functionality
- **UpdateMembers1/UpdateMembers2** - Member update functionality
- **UpdateProfile1/UpdateProfile2** - Profile update functionality
- **Updating** - General update operations
- **ViewClub** - Club viewing functionality
- **ClubApplication** - Main club application class
- **ClubValidator** - Club validation logic
- **ClubValidation** - Additional validation utilities
- **GenerateReport** - Club report generation
- **Storage** - Data storage and retrieval utilities
- **DBConnection** - Database connection utilities

### `com.example.EventScheduling`
**Event Scheduling Package**

Event, activity, and meeting scheduling:
- **EventParent** - Abstract parent class for events
- **Event** - Event entity class (extends EventParent)
- **Activity** - Activity entity class (extends EventParent)
- **Meeting** - Meeting entity class (extends EventParent)
- **EventValidator** - Event validation using threads
- **EventValidation** - Additional validation utilities
- **EventView** - Event display and viewing
- **PostponeEvent** - Event postponement functionality

### `com.example.demo`
**Attendance Tracking & Reporting Package**

Attendance management and reporting:
- **Submit** - Attendance submission
- **Submit_Teacher** - Teacher attendance submission
- **Check** - Attendance checking
- **Check_Teacher** - Teacher attendance checking
- **Update** - Attendance updates
- **Update_Teacher** - Teacher attendance updates
- **Delete** - Attendance deletion
- **Chart** - Data visualization (student)
- **Chart_Teacher** - Data visualization (teacher)
- **Export_Student** - Student data export to Excel
- **Export_Teacher** - Teacher data export to Excel
- **Att_Validate** - Attendance validation
- **validations** - General validation utilities
- **HelloApplication** - Demo application entry point
- **HelloController** - Demo controller

## 🎨 Resources Directory

### FXML Layouts
Located in `resources/com/example/`:
- User registration forms
- Club management interfaces
- Event scheduling forms
- Attendance tracking screens
- Report generation interfaces

### Images
Located in `resources/Images/`:
- Login screen images
- Application logos
- UI graphics

### Styles
Located in `resources/Styles/`:
- **Buttons.css** - Button styling
- **MainPage.css** - Main page styling
- **event-scheduling.css** - Event scheduling interface styling

### Fonts
Located in `resources/Fonts/`:
- Custom fonts for UI enhancement

## 🔧 Module System

The project uses Java's module system (`module-info.java`):
- Defines module dependencies
- Exports required packages
- Requires JavaFX modules
- Requires database connectivity modules

## 🏗️ Architecture Patterns

### MVC Pattern
- **Model:** Entity classes (Student, Advisor, Club, Event)
- **View:** FXML layouts
- **Controller:** Controller classes (UserRegMainController, ClubController, EventController)

### Inheritance Hierarchy
```
EventParent (Abstract)
    ├── Event
    ├── Activity
    └── Meeting
```

### Design Patterns
- **Singleton:** Database connections
- **Factory:** Object creation
- **Observer:** Event handling
- **Strategy:** Validation strategies


## 📚 Key Implementation Details

### Database Connectivity
- JDBC connection pooling
- Prepared statements for security
- Transaction management
- Connection cleanup

### Exception Handling
- SQLException handling
- IOException handling
- Custom exception classes
- User-friendly error messages

### Data Validation
- Input validation using regex
- Business rule validation
- Database constraint validation
- Concurrent validation using threads

---

**Note:** This directory contains the complete source code for the SACMS application. All packages follow Object-Oriented principles and Java best practices.
