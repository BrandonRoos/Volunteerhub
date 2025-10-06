import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 * The AdminGUI class provides a graphical user interface for administrators
 * to manage users within the Volunteer Hub application. It displays user data in a table
 * and includes buttons to add, edit, and remove users.
 * @author Brandon Roos
 */
public class AdminGUI extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;
    private VolunteerDatabase dbManager;

    /**
     * Constructs the AdminGUI window.
     * @param dbManager The database manager instance to interact with the database.
     */
    public AdminGUI(VolunteerDatabase dbManager) {
        this.dbManager = dbManager;
        setTitle("Admin Panel - Manage Users");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Create the table to display users
        String[] columnNames = {"User ID", "First Name", "Last Name", "Email", "Role"};
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add User");
        JButton editButton = new JButton("Edit User");
        JButton removeButton = new JButton("Remove User");

        // Add action listeners
        addButton.addActionListener(e -> {
            new NewUserDialog(this, dbManager);
            refreshTable();
        });

        editButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String userID = (String) userTable.getValueAt(selectedRow, 0);
                try {
                    User userToEdit = dbManager.getUserByID(userID);
                    if (userToEdit != null) {
                        new EditUserDialog(this, dbManager, userToEdit);
                        refreshTable();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error fetching user data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a user to edit.", "No User Selected", JOptionPane.WARNING_MESSAGE);
            }
        });

        removeButton.addActionListener(e -> {
                    int selectedRow = userTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String userID = (String) userTable.getValueAt(selectedRow, 0);
                        try {
                            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this user?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                dbManager.deleteUser(userID);
                                JOptionPane.showMessageDialog(this, "User " + userID + " removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                refreshTable();
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(this, "Error removing user: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please select a user to remove.", "No User Selected", JOptionPane.WARNING_MESSAGE);
                    }
                }
        );

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshTable();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        try {
            List<User> userList = dbManager.getAllUsers();
            for (User user : userList) {
                Object[] rowData = {user.getUserID(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole().name()};
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading users from database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}