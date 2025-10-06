//This was for testing do not run again or emails won't work

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.UUID;
//
///**
// * A utility class to test the Apache Derby database connection and basic table operations.
// * @author Brandon Roos
// */
//public class TestDerbyDB {
//    /** The JDBC driver class name. */
//    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
//    /** The JDBC URL to connect to the embedded Derby database. */
//    private static final String JDBC_URL = "jdbc:derby:volunteerdb;create=true";
//
//    /**
//     * The main method to run the database test.
//     * It initializes the database, creates a test table, inserts a test user, and shuts down the connection.
//     * @param args Command-line arguments (not used).
//     */
//    public static void main(String[] args) {
//        System.out.println("Attempting to initialize and test the database...");
//        try {
//            Class.forName(DRIVER);
//            System.out.println("Apache Derby driver loaded successfully.");
//            createUsersTable();
//            insertTestUser();
//            System.out.println("Database test complete. Check your project folder for a 'volunteerdb' directory.");
//        } catch (ClassNotFoundException e) {
//            System.err.println("Error: Apache Derby driver not found in classpath.");
//        } catch (SQLException e) {
//            System.err.println("Database error occurred: " + e.getMessage());
//        } finally {
//            shutdown();
//        }
//    }
//
//    private static void createUsersTable() throws SQLException {
//        try (Connection conn = getConnection();
//             Statement stmt = conn.createStatement()) {
//
//            stmt.execute("CREATE TABLE users (" +
//                    "userID VARCHAR(50) PRIMARY KEY," +
//                    "firstName VARCHAR(100)," +
//                    "lastName VARCHAR(100))");
//            System.out.println("Users table created successfully.");
//        } catch (SQLException e) {
//            if (e.getSQLState().equals("X0Y32")) {
//                System.out.println("Users table already exists, skipping creation.");
//            } else {
//                throw e;
//            }
//        }
//    }
//
//    private static void insertTestUser() throws SQLException {
//        String uniqueUserID = "testUser-" + UUID.randomUUID().toString();
//        String firstName = "John";
//        String lastName = "Doe";
//
//        String sql = "INSERT INTO users (userID, firstName, lastName) VALUES (?, ?, ?)";
//        try (Connection conn = getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, uniqueUserID);
//            pstmt.setString(2, firstName);
//            pstmt.setString(3, lastName);
//
//            int rowsAffected = pstmt.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Test user " + firstName + " inserted successfully with ID: " + uniqueUserID);
//            }
//        }
//    }
//
//    private static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(JDBC_URL);
//    }
//
//    private static void shutdown() {
//        try {
//            DriverManager.getConnection("jdbc:derby:volunteerdb;shutdown=true");
//        } catch (SQLException e) {
//            if (e.getSQLState().equals("XJ015")) {
//                System.out.println("Database shutdown successfully.");
//            }
//        }
//    }
//}