import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 * A graphical user interface for generating reports.
 * This class displays all data from the database in a tabbed table format.
 * @author Brandon Roos
 */
public class ReportGUI extends JFrame {
    private VolunteerDatabase dbManager;
    private JTabbedPane tabbedPane;

    public ReportGUI(VolunteerDatabase dbManager) {
        this.dbManager = dbManager;
        setTitle("Volunteer Hub Reports");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        add(tabbedPane);

        // Add tabs for different reports
        setupUserReportTab();
        setupOrganizationReportTab();
        setupEventReportTab();
        setupVolunteerRecordReportTab();

        setVisible(true);
    }

    private void setupUserReportTab() {
        String[] columnNames = {"User ID", "First Name", "Last Name", "Email", "Role", "Total Hours"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tabbedPane.addTab("Users", scrollPane);

        try {
            List<User> users = dbManager.getAllUsers();
            for (User user : users) {
                Object[] rowData = {user.getUserID(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole().name(), user.getTotalHours()};
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading users: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupOrganizationReportTab() {
        String[] columnNames = {"Org ID", "Name", "Contact Person", "Email", "Phone"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tabbedPane.addTab("Organizations", scrollPane);

        try {
            List<Organization> orgs = dbManager.getAllOrganizations();
            for (Organization org : orgs) {
                Object[] rowData = {org.getOrgID(), org.getOrgName(), org.getContactPersonName(), org.getContactEmail(), org.getContactPhone()};
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading organizations: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupEventReportTab() {
        String[] columnNames = {"Event ID", "Title", "Date", "Location", "Max Volunteers", "Current Volunteers"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tabbedPane.addTab("Events", scrollPane);

        try {
            List<Event> events = dbManager.getAllEvents();
            for (Event event : events) {
                Object[] rowData = {event.getEventID(), event.getTitle(), event.getDate(), event.getLocation().formatAddress(), event.getMaxVolunteers(), event.getCurrentVolunteers()};
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading events: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupVolunteerRecordReportTab() {
        String[] columnNames = {"Record ID", "User ID", "Event ID", "Hours Submitted", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tabbedPane.addTab("Volunteer Records", scrollPane);

        try {
            List<VolunteerRecord> records = dbManager.getAllVolunteerRecords();
            for (VolunteerRecord record : records) {
                Object[] rowData = {record.getRecordID(), record.getUserID(), record.getEventID(), record.getHoursSubmitted(), record.getApprovalStatus().name()};
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading records: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}