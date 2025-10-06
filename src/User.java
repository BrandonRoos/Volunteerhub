import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * The User class represents a volunteer in the Volunteer Hub application.
 * It encapsulates a user's personal information, role, and service hours.
 * @author Brandon Roos
 */
public class User {
    /** The unique ID of the user. */
    private String userID;
    /** The first name of the user. */
    private String firstName;
    /** The last name of the user. */
    private String lastName;
    /** The email address of the user. */
    private String email;
    /** The password for the user's account. */
    private String password;
    /** The role of the user (e.g., Volunteer, OrganizationRepresentative, Admin). */
    private Role role;
    /** The total number of service hours accumulated by the user. */
    private double totalHours;
    /** A map to store service hours by academic semester. */
    private Map<String, Double> academicSemesterHours;

    /**
     * Enum to define the possible roles for a user.
     */
    public enum Role {
        VOLUNTEER,
        ORGANIZATION_REPRESENTATIVE,
        ADMIN
    }

    /**
     * Constructs a new User object.
     *
     * @param userID the unique ID of the user.
     * @param firstName the first name of the user.
     * @param lastName the last name of the user.
     * @param email the email address of the user.
     * @param password the password for the user's account.
     * @param role the role of the user (Volunteer, OrganizationRepresentative, or Admin).
     */
    public User(String userID, String firstName, String lastName, String email, String password, Role role) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.totalHours = 0.0;
        this.academicSemesterHours = new HashMap<>();
    }

    /**
     * Allows a user to register for the platform.
     */
    public void register() {
        // Implementation for user registration (e.g., saving to database)
        System.out.println("User " + this.firstName + " " + this.lastName + " has registered.");
    }

    /**
     * Allows a user to log in to the platform.
     *
     * @return true if login is successful, false otherwise.
     */
    public boolean login() {
        // Implementation for user login
        return true;
    }

    /**
     * Displays the user's profile information.
     */
    public void viewProfile() {
        System.out.println("User Profile for " + this.firstName + ":");
        System.out.println("------------------------------------");
        System.out.println("User ID: " + this.userID);
        System.out.println("Name: " + this.firstName + " " + this.lastName);
        System.out.println("Email: " + this.email);
        System.out.println("Role: " + this.role);
        System.out.println("Total Hours: " + this.totalHours);
        System.out.println("Academic Semester Hours: " + this.academicSemesterHours);
    }

    /**
     * Allows a user to edit their profile information.
     */
    public void editProfile() {
        // Implementation for editing user profile
        System.out.println("User profile for " + this.firstName + " has been updated.");
    }

    /**
     * Submits a volunteer record for approval.
     *
     * @param record the VolunteerRecord to submit.
     */
    public void submitHours(VolunteerRecord record) {
        // Implementation to submit hours and update the user's records
        System.out.println("Volunteer hours for event " + record.getEventID() + " submitted by " + this.firstName + ".");
    }

    /**
     * Views notifications for the user.
     */
    public void viewNotifications() {
        // Implementation to fetch and display notifications
        System.out.println("Viewing notifications for " + this.firstName + "...");
    }

    /**
     * Gets the unique ID of the user.
     * @return The user ID.
     */
    public String getUserID() { return userID; }
    /**
     * Gets the first name of the user.
     * @return The user's first name.
     */
    public String getFirstName() { return firstName; }
    /**
     * Gets the last name of the user.
     * @return The user's last name.
     */
    public String getLastName() { return lastName; }
    /**
     * Gets the email address of the user.
     * @return The user's email.
     */
    public String getEmail() { return email; }
    /**
     * Gets the password of the user.
     * @return The user's password.
     */
    public String getPassword() { return password; }
    /**
     * Gets the role of the user.
     * @return The user's role.
     */
    public Role getRole() { return role; }
    /**
     * Gets the total hours accumulated by the user.
     * @return The total hours.
     */
    public double getTotalHours() { return totalHours; }
    /**
     * Gets the map of academic semester hours.
     * @return The academic semester hours map.
     */
    public Map<String, Double> getAcademicSemesterHours() { return academicSemesterHours; }

    /**
     * Sets the first name of the user.
     * @param firstName The new first name.
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }
    /**
     * Sets the last name of the user.
     * @param lastName The new last name.
     */
    public void setLastName(String lastName) { this.lastName = lastName; }
    /**
     * Sets the email address of the user.
     * @param email The new email address.
     */
    public void setEmail(String email) { this.email = email; }
    /**
     * Sets the password of the user.
     * @param password The new password.
     */
    public void setPassword(String password) { this.password = password; }
    /**
     * Sets the role of the user.
     * @param role The new role.
     */
    public void setRole(Role role) { this.role = role; }
    /**
     * Adds hours to the user's total hours.
     * @param hours The hours to add.
     */
    public void addHours(double hours) { this.totalHours += hours; }
    /**
     * Adds hours to a specific academic semester.
     * @param semester The semester to add hours to.
     * @param hours The hours to add.
     */
    public void addAcademicHours(String semester, double hours) {
        this.academicSemesterHours.merge(semester, hours, Double::sum);
    }
}