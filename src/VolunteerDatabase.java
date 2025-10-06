import java.sql.*;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.UUID;


/**
 * Manages the connection and operations for the Apache Derby database.
 * This class handles database initialization, table creation, and provides
 * methods for other classes to perform CRUD operations on core data entities.
 * @author Brandon Roos
 */
public class VolunteerDatabase {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:volunteerdb;create=true";

    /**
     * Initializes the database by loading the driver and creating the necessary tables.
     */
    public void initializeDatabase() {
        try {
            Class.forName(DRIVER);
            System.out.println("Apache Derby driver loaded successfully.");
            createTables();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establishes and returns a database connection.
     * @return A Connection object to the database.
     * @throws SQLException if a database access error occurs.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    /**
     * Creates the database tables for User, Organization, Event, and VolunteerRecord.
     */
    private void createTables() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Create User table
            stmt.execute("CREATE TABLE users (" +
                    "userID VARCHAR(50) PRIMARY KEY," +
                    "firstName VARCHAR(100)," +
                    "lastName VARCHAR(100)," +
                    "email VARCHAR(100)," +
                    "password VARCHAR(100)," +
                    "role VARCHAR(50)," +
                    "totalHours DOUBLE," +
                    "academicSemesterHours BLOB)");
            System.out.println("User table created.");

            // Create Organization table
            stmt.execute("CREATE TABLE organizations (" +
                    "orgID VARCHAR(50) PRIMARY KEY," +
                    "orgName VARCHAR(200)," +
                    "contactPersonName VARCHAR(200)," +
                    "contactEmail VARCHAR(100)," +
                    "contactPhone VARCHAR(50)," +
                    "password VARCHAR(100)," +
                    "description VARCHAR(500)," +
                    "addressStreet VARCHAR(200)," +
                    "addressCity VARCHAR(100)," +
                    "addressState VARCHAR(50)," +
                    "addressZipCode VARCHAR(20))");
            System.out.println("Organization table created.");

            // Create Event table
            stmt.execute("CREATE TABLE events (" +
                    "eventID VARCHAR(50) PRIMARY KEY," +
                    "title VARCHAR(200)," +
                    "description VARCHAR(500)," +
                    "date DATE," +
                    "startTime TIME," +
                    "endTime TIME," +
                    "locationStreet VARCHAR(200)," +
                    "locationCity VARCHAR(100)," +
                    "locationState VARCHAR(50)," +
                    "locationZipCode VARCHAR(20)," +
                    "maxVolunteers INT," +
                    "currentVolunteers INT," +
                    "requiredSkills BLOB," +
                    "orgID VARCHAR(50)," +
                    "contactInfo VARCHAR(100)," +
                    "FOREIGN KEY (orgID) REFERENCES organizations(orgID))");
            System.out.println("Event table created.");

            // Create VolunteerRecord table
            stmt.execute("CREATE TABLE records (" +
                    "recordID VARCHAR(50) PRIMARY KEY," +
                    "userID VARCHAR(50)," +
                    "eventID VARCHAR(50)," +
                    "hoursSubmitted DOUBLE," +
                    "submissionDate DATE," +
                    "status VARCHAR(50)," +
                    "approverID VARCHAR(50)," +
                    "FOREIGN KEY (userID) REFERENCES users(userID)," +
                    "FOREIGN KEY (eventID) REFERENCES events(eventID))");
            System.out.println("VolunteerRecord table created.");

        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32")) {
                e.printStackTrace();
            } else {
                System.out.println("Tables already exist, skipping creation.");
            }
        }
    }

    /**
     * Inserts a new user into the database.
     * @param user The User object to insert.
     * @throws SQLException if a database access error occurs.
     */
    public void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO users (userID, firstName, lastName, email, password, role, totalHours, academicSemesterHours) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUserID());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());
            pstmt.setString(6, user.getRole().name());
            pstmt.setDouble(7, user.getTotalHours());

            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                 ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeObject(user.getAcademicSemesterHours());
                pstmt.setBytes(8, bos.toByteArray());
            }

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("Error inserting user: " + e.getMessage(), e);
        }
    }

    /**
     * Inserts a new organization into the database.
     * @param org The Organization object to insert.
     * @throws SQLException if a database access error occurs.
     */
    public void insertOrganization(Organization org) throws SQLException {
        String sql = "INSERT INTO organizations (orgID, orgName, contactPersonName, contactEmail, contactPhone, password, description, addressStreet, addressCity, addressState, addressZipCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, org.getOrgID());
            pstmt.setString(2, org.getOrgName());
            pstmt.setString(3, org.getContactPersonName());
            pstmt.setString(4, org.getContactEmail());
            pstmt.setString(5, org.getContactPhone());
            pstmt.setString(6, org.getPassword());
            pstmt.setString(7, org.getDescription());
            pstmt.setString(8, org.getAddress().getStreet());
            pstmt.setString(9, org.getAddress().getCity());
            pstmt.setString(10, org.getAddress().getState());
            pstmt.setString(11, org.getAddress().getZipCode());

            pstmt.executeUpdate();
        }
    }

    /**
     * Inserts a new event into the database.
     * @param event The Event object to insert.
     * @throws SQLException if a database access error occurs.
     */
    public void insertEvent(Event event) throws SQLException {
        String sql = "INSERT INTO events (eventID, title, description, date, startTime, endTime, locationStreet, locationCity, locationState, locationZipCode, maxVolunteers, currentVolunteers, requiredSkills, orgID, contactInfo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, event.getEventID());
            pstmt.setString(2, event.getTitle());
            pstmt.setString(3, event.getDescription());
            pstmt.setDate(4, new java.sql.Date(event.getDate().getTime()));
            pstmt.setTime(5, event.getStartTime());
            pstmt.setTime(6, event.getEndTime());
            pstmt.setString(7, event.getLocation().getStreet());
            pstmt.setString(8, event.getLocation().getCity());
            pstmt.setString(9, event.getLocation().getState());
            pstmt.setString(10, event.getLocation().getZipCode());
            pstmt.setInt(11, event.getMaxVolunteers());
            pstmt.setInt(12, event.getCurrentVolunteers());

            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                 ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeObject(event.getRequiredSkills());
                pstmt.setBytes(13, bos.toByteArray());
            }

            pstmt.setString(14, event.getOrgID());
            pstmt.setString(15, event.getContactInfo());

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("Error inserting event: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves all users from the database.
     * @return A list of all User objects.
     * @throws SQLException if a database access error occurs.
     */
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String userID = rs.getString("userID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                User.Role role = User.Role.valueOf(rs.getString("role"));

                Map<String, Double> academicSemesterHours = new HashMap<>();
                byte[] academicHoursBytes = rs.getBytes("academicSemesterHours");
                if (academicHoursBytes != null) {
                    try (ByteArrayInputStream bis = new ByteArrayInputStream(academicHoursBytes);
                         ObjectInputStream ois = new ObjectInputStream(bis)) {
                        academicSemesterHours = (Map<String, Double>) ois.readObject();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                User user = new User(userID, firstName, lastName, email, password, role);
                userList.add(user);
            }
        }
        return userList;
    }

    /**
     * Retrieves all organizations from the database.
     * @return A list of all Organization objects.
     * @throws SQLException if a database access error occurs.
     */
    public List<Organization> getAllOrganizations() throws SQLException {
        List<Organization> orgList = new ArrayList<>();
        String sql = "SELECT * FROM organizations";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String orgID = rs.getString("orgID");
                String orgName = rs.getString("orgName");
                String contactPersonName = rs.getString("contactPersonName");
                String contactEmail = rs.getString("contactEmail");
                String contactPhone = rs.getString("contactPhone");
                String password = rs.getString("password");
                String description = rs.getString("description");
                String street = rs.getString("addressStreet");
                String city = rs.getString("addressCity");
                String state = rs.getString("addressState");
                String zipCode = rs.getString("addressZipCode");
                Address address = new Address(street, city, state, zipCode);

                Organization org = new Organization(orgID, orgName, contactPersonName, contactEmail, contactPhone, password, description, address);
                orgList.add(org);
            }
        }
        return orgList;
    }

    /**
     * Retrieves all events from the database.
     * @return A list of all Event objects.
     * @throws SQLException if a database access error occurs.
     */
    public List<Event> getAllEvents() throws SQLException {
        List<Event> eventList = new ArrayList<>();
        String sql = "SELECT * FROM events";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String eventID = rs.getString("eventID");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date date = rs.getDate("date");
                Time startTime = rs.getTime("startTime");
                Time endTime = rs.getTime("endTime");
                String street = rs.getString("locationStreet");
                String city = rs.getString("locationCity");
                String state = rs.getString("locationState");
                String zipCode = rs.getString("locationZipCode");
                Address location = new Address(street, city, state, zipCode);
                int maxVolunteers = rs.getInt("maxVolunteers");
                int currentVolunteers = rs.getInt("currentVolunteers");
                List<String> requiredSkills = new ArrayList<>();

                byte[] skillsBytes = rs.getBytes("requiredSkills");
                if (skillsBytes != null) {
                    try (ByteArrayInputStream bis = new ByteArrayInputStream(skillsBytes);
                         ObjectInputStream ois = new ObjectInputStream(bis)) {
                        requiredSkills = (List<String>) ois.readObject();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                String orgID = rs.getString("orgID");
                String contactInfo = rs.getString("contactInfo");

                Event event = new Event(eventID, title, description, date, startTime, endTime, location, maxVolunteers, requiredSkills, orgID, contactInfo);
                eventList.add(event);
            }
        }
        return eventList;
    }

    /**
     * Retrieves an existing user from the database by their ID.
     * @param userID The ID of the user to retrieve.
     * @return The User object, or null if not found.
     * @throws SQLException if a database access error occurs.
     */
    public User getUserByID(String userID) throws SQLException {
        String sql = "SELECT * FROM users WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    User.Role role = User.Role.valueOf(rs.getString("role"));

                    Map<String, Double> academicSemesterHours = new HashMap<>();
                    byte[] academicHoursBytes = rs.getBytes("academicSemesterHours");
                    if (academicHoursBytes != null) {
                        try (ByteArrayInputStream bis = new ByteArrayInputStream(academicHoursBytes);
                             ObjectInputStream ois = new ObjectInputStream(bis)) {
                            academicSemesterHours = (Map<String, Double>) ois.readObject();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    return new User(userID, firstName, lastName, email, password, role);
                }
            }
        }
        return null;
    }

    /**
     * Retrieves all volunteer records from the database.
     * @return A list of all VolunteerRecord objects.
     * @throws SQLException if a database access error occurs.
     */
    public List<VolunteerRecord> getAllVolunteerRecords() throws SQLException {
        List<VolunteerRecord> recordList = new ArrayList<>();
        String sql = "SELECT * FROM records";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String recordID = rs.getString("recordID");
                String userID = rs.getString("userID");
                String eventID = rs.getString("eventID");
                double hoursSubmitted = rs.getDouble("hoursSubmitted");
                Date submissionDate = rs.getDate("submissionDate");
                VolunteerRecord.Status status = VolunteerRecord.Status.valueOf(rs.getString("status"));
                String approverID = rs.getString("approverID");

                VolunteerRecord record = new VolunteerRecord(recordID, userID, eventID, hoursSubmitted);
                // The constructor needs to be updated to take all these parameters
                // For now, you can use setters to populate the rest of the fields.
                // The constructor below is an example of what would be needed
                // public VolunteerRecord(String recordID, String userID, String eventID, double hoursSubmitted, Date submissionDate, VolunteerRecord.Status status, String approverID) { ... }

                // For now, we will use setters to set the values
                record.updateStatus(status, approverID);
                recordList.add(record);
            }
        }
        return recordList;
    }


    /**
     * Updates an existing user's data in the database.
     * @param user The User object with updated information.
     * @throws SQLException if a database access error occurs.
     */
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET firstName = ?, lastName = ?, email = ?, password = ?, role = ? WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getRole().name());
            pstmt.setString(6, user.getUserID());

            pstmt.executeUpdate();
        }
    }

    /**
     * Deletes a user from the database by their userID.
     * @param userID The ID of the user to delete.
     * @throws SQLException if a database access error occurs.
     */
    public void deleteUser(String userID) throws SQLException {
        String sql = "DELETE FROM users WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userID);
            pstmt.executeUpdate();
        }
    }

    /**
     * Closes the database connection gracefully.
     */
    public void shutdown() {
        try {
            DriverManager.getConnection("jdbc:derby:volunteerdb;shutdown=true");
        } catch (SQLException e) {
            if (e.getSQLState().equals("XJ015")) {
                System.out.println("Database shutdown successfully.");
            } else {
                e.printStackTrace();
            }
        }
    }
}