import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.UUID;

/**
 * A JDialog class for new user registration.
 * This class creates a pop-out window with input fields for user details
 * and an OK button to submit the information.
 * @author Brandon Roos
 */
public class NewUserDialog extends JDialog {
    private JTextField firstNameField, lastNameField, emailField, passwordField;
    private JComboBox<User.Role> roleComboBox;
    private VolunteerDatabase dbManager;

    /**
     * Constructs a new NewUserDialog.
     *
     * @param parent The parent JFrame from which this dialog is displayed.
     * @param dbManager The database manager instance.
     */
    public NewUserDialog(JFrame parent, VolunteerDatabase dbManager) {
        super(parent, "Add New User", true);
        this.dbManager = dbManager;
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));
        setLocationRelativeTo(parent);

        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        roleComboBox = new JComboBox<>(User.Role.values());

        add(new JLabel("First Name:")); add(firstNameField);
        add(new JLabel("Last Name:")); add(lastNameField);
        add(new JLabel("Email:")); add(emailField);
        add(new JLabel("Password:")); add(passwordField);
        add(new JLabel("Role:")); add(roleComboBox);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveUser());

        add(new JLabel()); add(saveButton);
        setVisible(true);
    }

    private void saveUser() {
        String userID = "user-" + UUID.randomUUID().toString();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        User.Role role = (User.Role) roleComboBox.getSelectedItem();

        try {
            User newUser = new User(userID, firstName, lastName, email, password, role);
            dbManager.insertUser(newUser);
            JOptionPane.showMessageDialog(this, "User added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saving user: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}