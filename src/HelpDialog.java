import javax.swing.*;
import java.awt.*;

/**
 * A JDialog class to display help information.
 * This pop-out window shows a contact phone number and email address.
 * @author Brandon Roos
 */
public class HelpDialog extends JDialog {

    /**
     * Constructs a new HelpDialog.
     *
     * @param parent The parent JFrame from which this dialog is displayed.
     */
    public HelpDialog(JFrame parent) {
        super(parent, "Help", true);
        setSize(300, 150);
        setLayout(new GridLayout(3, 1, 10, 10));

        // Create labels for the help information
        JLabel titleLabel = new JLabel("Contact Support", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel phoneLabel = new JLabel("Phone: (757)123-4567", SwingConstants.CENTER);
        JLabel emailLabel = new JLabel("Email: help@volunteer.com", SwingConstants.CENTER);

        // Add the labels to the dialog
        add(titleLabel);
        add(phoneLabel);
        add(emailLabel);

        // Center the dialog relative to the parent frame and make it visible
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}