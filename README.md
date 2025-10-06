# Volunteer Hub

A Java-based volunteer management system that helps organizations coordinate volunteer opportunities and track service hours. Built with Java Swing for the GUI and Apache Derby for the embedded database.

## Author

Brandon Roos

## Overview

Volunteer Hub is a comprehensive application designed to connect volunteers with organizations, manage volunteer events, track service hours, and generate reports. The system supports multiple user roles including volunteers, organization representatives, and administrators.

## Features

- **User Management**: Register new users, edit profiles, and manage user roles (Volunteer, Organization Representative, Admin)
- **Event Management**: Organizations can create and manage volunteer opportunities with details like date, time, location, and required skills
- **Hour Tracking**: Volunteers can log in/out of events and submit hours for approval
- **Administrative Panel**: Admins can add, edit, and remove users from the system
- **Reporting**: Generate comprehensive reports on users, organizations, events, and volunteer records
- **Notifications**: System for sending reminders, approvals, and new opportunity alerts
- **Academic Semester Tracking**: Track volunteer hours by academic semester for educational requirements

## Technologies Used

- **Language**: Java
- **GUI Framework**: Java Swing
- **Database**: Apache Derby (embedded)
- **JDBC**: For database connectivity

## Project Structure

```
src/
├── Address.java                 # Physical address representation
├── AdminGUI.java               # Admin panel for user management
├── EditUserDialog.java         # Dialog for editing user information
├── Event.java                  # Volunteer event/opportunity model
├── HelpDialog.java             # Help and contact information dialog
├── LoginDialog.java            # User login and time tracking dialog
├── Main.java                   # Application entry point
├── NewUserDialog.java          # New user registration dialog
├── Notification.java           # Notification system model
├── Organization.java           # Organization model
├── Report.java                 # Report generation utilities
├── ReportGUI.java             # Report viewing interface
├── User.java                   # User model with roles
├── VolunteerDatabase.java     # Database manager (CRUD operations)
├── VolunteerHubGUI.java       # Main application window
└── VolunteerRecord.java       # Volunteer hour tracking model
```

## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Derby library (included in classpath)

### Setup

1. Clone or download the project files
2. Ensure Apache Derby JAR files are in your classpath
3. Compile all Java files:
   ```bash
   javac src/*.java
   ```
4. Run the application:
   ```bash
   java -cp src Main
   ```

## Usage

### First Launch

On first launch, the application will:
- Initialize the Apache Derby database (`volunteerdb` directory)
- Create necessary tables (users, organizations, events, records)
- Populate the database with sample data including 50 random users and 2 sample organizations with events

### Main Window

The main window provides five buttons:

- **New user**: Register a new user account
- **Login user**: Log volunteer hours for an event
- **Help**: Display contact support information
- **Admin**: Access the administrative panel for user management
- **Reports**: View comprehensive reports on all system data

### User Roles

- **VOLUNTEER**: Can register for events, submit hours, and view their profile
- **ORGANIZATION_REPRESENTATIVE**: Can create events and approve volunteer hours
- **ADMIN**: Full system access including user management

## Database Schema

### Users Table
- userID, firstName, lastName, email, password, role, totalHours, academicSemesterHours

### Organizations Table
- orgID, orgName, contactPersonName, contactEmail, contactPhone, password, description, address fields

### Events Table
- eventID, title, description, date, startTime, endTime, location fields, maxVolunteers, currentVolunteers, requiredSkills, orgID, contactInfo

### Records Table
- recordID, userID, eventID, hoursSubmitted, submissionDate, status, approverID

## Sample Data

The application includes sample organizations:
- **Liberty University**: Elementary tutoring event
- **Red Cross**: Community blood drive event

Contact information:
- Phone: (757)123-4567
- Email: help@volunteer.com

## Key Classes

### Main.java
Entry point that initializes the database and launches the GUI on the Event Dispatch Thread.

### VolunteerDatabase.java
Manages all database operations including connection management, table creation, and CRUD operations for users, organizations, events, and records.

### User.java
Represents a volunteer with support for multiple roles and academic semester hour tracking.

### Event.java
Represents a volunteer opportunity with capacity management and volunteer registration.

### Organization.java
Represents organizations that post events and approve volunteer hours.

## Future Enhancements

- User authentication system
- Email notifications
- PDF and CSV report export functionality
- Event search and filtering
- Skill-based volunteer matching
- Mobile application support

## License

Educational project - all rights reserved.

## Contact

For support or questions, contact: help@volunteer.com
