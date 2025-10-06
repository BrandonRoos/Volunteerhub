import javax.swing.*;
import java.awt.*;

/**
 * The main class for the Volunteer Hub graphical user interface (GUI).
 * This class creates the main application window and provides buttons
 * to navigate to other parts of the application.
 * @author Brandon Roos
 */
public class VolunteerHubGUI extends JFrame {

    /** The database manager instance to interact with the database. */
    private VolunteerDatabase dbManager;

    /**
     * Constructs a new VolunteerHubGUI object, setting up the main window and its components.
     * @param dbManager The database manager instance.
     */
    public VolunteerHubGUI(VolunteerDatabase dbManager) {
        this.dbManager = dbManager;

        setTitle("Welcome to Volunteer Hub");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Create and style the buttons
        JButton newUserButton = new JButton("New user");
        JButton loginUserButton = new JButton("Login user");
        JButton helpButton = new JButton("Help");
        JButton adminButton = new JButton("Admin");
        JButton reportButton = new JButton("Reports");

        // Add action listeners to the buttons
        newUserButton.addActionListener(e -> new NewUserDialog(this, dbManager));
        loginUserButton.addActionListener(e -> new LoginDialog(this, dbManager));
        helpButton.addActionListener(e -> new HelpDialog(this));
        adminButton.addActionListener(e -> {
            AdminGUI adminGui = new AdminGUI(dbManager);
            adminGui.setVisible(true);
        });
        reportButton.addActionListener(e -> {
            ReportGUI reportGui = new ReportGUI(dbManager);
            reportGui.setVisible(true);
        });

        // Add the buttons to the frame
        add(newUserButton);
        add(loginUserButton);
        add(helpButton);
        add(adminButton);
        add(reportButton);

        // Make the window visible
        setVisible(true);
    }
}