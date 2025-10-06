import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 * A JDialog class for logging in.
 * This pop-out window provides fields for a user to log in and track their volunteer hours.
 * @author Brandon Roos
 */
public class LoginDialog extends JDialog {
    private JTextField phoneNumberField;
    private JComboBox<String> eventComboBox;
    private JRadioButton inRadioButton;
    private JRadioButton outRadioButton;
    private VolunteerDatabase dbManager;

    /**
     * Constructs a new LoginDialog.
     *
     * @param parent The parent JFrame from which this dialog is displayed.
     * @param dbManager The database manager instance.
     */
    public LoginDialog(JFrame parent, VolunteerDatabase dbManager) {
        super(parent, "Login", true);
        this.dbManager = dbManager;
        setSize(400, 300);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Phone Number field
        formPanel.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField(15);
        formPanel.add(phoneNumberField);

        // Event dropdown
        formPanel.add(new JLabel("Event:"));
        eventComboBox = new JComboBox<>();
        populateEventComboBox();
        formPanel.add(eventComboBox);

        // In/Out radio buttons
        formPanel.add(new JLabel("Time:"));
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inRadioButton = new JRadioButton("In");
        outRadioButton = new JRadioButton("Out");
        ButtonGroup timeGroup = new ButtonGroup();
        timeGroup.add(inRadioButton);
        timeGroup.add(outRadioButton);
        radioPanel.add(inRadioButton);
        radioPanel.add(outRadioButton);
        formPanel.add(radioPanel);

        add(formPanel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> processLogin());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void populateEventComboBox() {
        try {
            List<Event> events = dbManager.getAllEvents();
            for (Event event : events) {
                eventComboBox.addItem(event.getTitle());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading events: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void processLogin() {
        String phoneNumber = phoneNumberField.getText();
        String selectedEventTitle = (String) eventComboBox.getSelectedItem();
        boolean isIn = inRadioButton.isSelected();

        // This is where you would implement the logic to find the user by phone number
        // and update their volunteer record with the time and event.
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Selected Event: " + selectedEventTitle);
        System.out.println("Time: " + (isIn ? "In" : "Out"));

        // Placeholder for database logic:
        // 1. Find user by phone number
        // 2. Find event by title
        // 3. Create or update a VolunteerRecord in the database

        JOptionPane.showMessageDialog(this, "Login information processed.", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}