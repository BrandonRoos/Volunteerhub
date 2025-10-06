import java.util.ArrayList;
import java.util.List;

/**
 * The Organization class represents a group that posts volunteer opportunities.
 * It manages events and the approval of volunteer hours.
 * @author Brandon Roos
 */
public class Organization {
    /** The unique ID of the organization. */
    private String orgID;
    /** The name of the organization. */
    private String orgName;
    /** The name of the contact person for the organization. */
    private String contactPersonName;
    /** The email of the contact person. */
    private String contactEmail;
    /** The phone number of the contact person. */
    private String contactPhone;
    /** The password for the organization's account. */
    private String password;
    /** A description of the organization. */
    private String description;
    /** The physical address of the organization. */
    private Address address;
    /** A list of events posted by the organization. */
    private List<Event> postedEvents;

    /**
     * Constructs a new Organization object.
     *
     * @param orgID the unique ID of the organization.
     * @param orgName the name of the organization.
     * @param contactPersonName the name of the contact person.
     * @param contactEmail the contact person's email.
     * @param contactPhone the contact person's phone number.
     * @param password the password for the organization's account.
     * @param description a description of the organization.
     * @param address the physical address of the organization.
     */
    public Organization(String orgID, String orgName, String contactPersonName, String contactEmail, String contactPhone, String password, String description, Address address) {
        this.orgID = orgID;
        this.orgName = orgName;
        this.contactPersonName = contactPersonName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.password = password;
        this.description = description;
        this.address = address;
        this.postedEvents = new ArrayList<>();
    }

    /**
     * Creates and posts a new event.
     *
     * @param event the Event object to be created and posted.
     */
    public void createEvent(Event event) {
        this.postedEvents.add(event);
        System.out.println("Event '" + event.getTitle() + "' created by " + this.orgName + ".");
    }

    /**
     * Approves a volunteer's submitted hours.
     *
     * @param record the VolunteerRecord to approve.
     */
    public void approveHours(VolunteerRecord record) {
        record.updateStatus(VolunteerRecord.Status.APPROVED, this.orgID);
        System.out.println("Hours for record " + record.getRecordID() + " have been approved by " + this.orgName + ".");
    }

    /**
     * Manages the posted opportunities (e.g., editing, deleting).
     */
    public void manageOpportunities() {
        System.out.println(this.orgName + " is managing its opportunities.");
    }

    /**
     * Views a list of submitted volunteer hours.
     */
    public void viewSubmittedHours() {
        // Implementation to fetch and display submitted hours
        System.out.println("Viewing submitted hours for " + this.orgName + ".");
    }

    /**
     * Gets the unique ID of the organization.
     * @return The organization's ID.
     */
    public String getOrgID() { return orgID; }
    /**
     * Gets the name of the organization.
     * @return The organization's name.
     */
    public String getOrgName() { return orgName; }
    /**
     * Gets the contact person's name.
     * @return The contact person's name.
     */
    public String getContactPersonName() { return contactPersonName; }
    /**
     * Gets the contact person's email.
     * @return The contact person's email.
     */
    public String getContactEmail() { return contactEmail; }
    /**
     * Gets the contact person's phone number.
     * @return The contact person's phone number.
     */
    public String getContactPhone() { return contactPhone; }
    /**
     * Gets the password of the organization.
     * @return The password.
     */
    public String getPassword() { return password; }
    /**
     * Gets the description of the organization.
     * @return The description.
     */
    public String getDescription() { return description; }
    /**
     * Gets the address of the organization.
     * @return The address.
     */
    public Address getAddress() { return address; }
    /**
     * Gets the list of posted events.
     * @return The list of events.
     */
    public List<Event> getPostedEvents() { return postedEvents; }
}