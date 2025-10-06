import javax.swing.SwingUtilities;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Random;

/**
 * The main entry point for the Volunteer Hub application.
 * This class initializes the database and launches the graphical user interface.
 * @author Brandon Roos
 */
public class Main {
    private static VolunteerDatabase db;

    /**
     * The main method that starts the application.
     * It ensures the GUI is created on the Event Dispatch Thread (EDT) for thread safety.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Create an instance of the database manager
        db = new VolunteerDatabase();

        // Initialize the database
        db.initializeDatabase();

        // Populate the database with sample data if it's empty
        try {
            if (db.getAllUsers().isEmpty()) {
                System.out.println("Populating database with sample data...");
                populateDatabase();
            }
        } catch (SQLException e) {
            System.err.println("Error checking for existing users: " + e.getMessage());
        }

        // Launch the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new VolunteerHubGUI(db);
        });
    }

    /**
     * Populates the database with sample users, organizations, and events.
     */
    private static void populateDatabase() {
        try {
            // Add 50 random users to the database
            addRandomUsers(50);

            // Create and insert sample organizations
            Address luAddress = new Address("1971 University Blvd", "Lynchburg", "VA", "24515");
            Organization luOrg = new Organization("org-1", "Liberty University", "James Dollens", "jdollens@liberty.edu", "555-123-4567", "lu_pass", "University CSER program.", luAddress);
            db.insertOrganization(luOrg);

            Address redCrossAddress = new Address("123 Main St", "Lynchburg", "VA", "24504");
            Organization redCrossOrg = new Organization("org-2", "Red Cross", "Jane Red", "info@redcross.org", "555-987-6543", "rc_pass", "Disaster relief organization.", redCrossAddress);
            db.insertOrganization(redCrossOrg);

            // Create and insert sample events
            List<String> skills1 = new ArrayList<>();
            skills1.add("Teaching");
            Event tutoringEvent = new Event("event-1", "Elementary Tutoring", "Help kids with homework", new Date(), new Time(15, 0, 0), new Time(17, 0, 0), luAddress, 10, skills1, luOrg.getOrgID(), luOrg.getContactEmail());
            db.insertEvent(tutoringEvent);

            List<String> skills2 = new ArrayList<>();
            skills2.add("First Aid");
            Event bloodDriveEvent = new Event("event-2", "Community Blood Drive", "Assist with a local blood drive", new Date(), new Time(9, 0, 0), new Time(14, 0, 0), redCrossAddress, 25, skills2, redCrossOrg.getOrgID(), redCrossOrg.getContactEmail());
            db.insertEvent(bloodDriveEvent);

            System.out.println("Database populated with sample data.");
        } catch (SQLException e) {
            System.err.println("Error populating database: " + e.getMessage());
        }
    }

    /**
     * Adds a specified number of random users to the database.
     * @param count The number of random users to add.
     */
    private static void addRandomUsers(int count) throws SQLException {
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            String userID = "user-" + UUID.randomUUID().toString();
            String firstName = "Random" + i;
            String lastName = "User";
            String email = "random" + i + "@example.com";
            String password = "pass" + i;
            User.Role role = User.Role.VOLUNTEER; // Default role for random users

            User newUser = new User(userID, firstName, lastName, email, password, role);
            db.insertUser(newUser);
        }
    }
}