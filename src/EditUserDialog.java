import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * A JDialog class for editing user details.
 * This pop-out window loads a user's data and allows an admin to modify it.
 * @author Brandon Roos
 */
public class EditUserDialog extends JDialog {
    private JTextField firstNameField, lastNameField, emailField, passwordField;
    private JComboBox<User.Role> roleComboBox;
    private VolunteerDatabase dbManager;
    private User userToEdit;

    /**
     * Constructs a new EditUserDialog.
     *
     * @param parent The parent JFrame from which this dialog is displayed.
     * @param dbManager The database manager instance.
     * @param userToEdit The User object to be edited.
     */
    public EditUserDialog(JFrame parent, VolunteerDatabase dbManager, User userToEdit) {
        super(parent, "Edit User", true);
        this.dbManager = dbManager;
        this.userToEdit = userToEdit;
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));
        setLocationRelativeTo(parent);

        firstNameField = new JTextField(userToEdit.getFirstName());
        lastNameField = new JTextField(userToEdit.getLastName());
        emailField = new JTextField(userToEdit.getEmail());
        passwordField = new JPasswordField(userToEdit.getPassword());

        roleComboBox = new JComboBox<>(User.Role.values());
        roleComboBox.setSelectedItem(userToEdit.getRole());

        add(new JLabel("User ID:")); add(new JLabel(userToEdit.getUserID()));
        add(new JLabel("First Name:")); add(firstNameField);
        add(new JLabel("Last Name:")); add(lastNameField);
        add(new JLabel("Email:")); add(emailField);
        add(new JLabel("Role:")); add(roleComboBox);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> saveChanges());

        add(new JLabel()); add(saveButton);
        setVisible(true);
    }

    private void saveChanges() {
        userToEdit.setFirstName(firstNameField.getText());
        userToEdit.setLastName(lastNameField.getText());
        userToEdit.setEmail(emailField.getText());
        userToEdit.setPassword(passwordField.getText());
        userToEdit.setRole((User.Role) roleComboBox.getSelectedItem());

        try {
            dbManager.updateUser(userToEdit);
            JOptionPane.showMessageDialog(this, "User updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating user: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}