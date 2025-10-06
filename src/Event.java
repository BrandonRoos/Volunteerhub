import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.sql.Time;

/**
 * The Event class holds all information about a specific volunteer opportunity.
 * @author Brandon Roos
 */
public class Event {
    /** The unique ID for the event. */
    private String eventID;
    /** The title of the event. */
    private String title;
    /** A brief description of the event. */
    private String description;
    /** The date of the event. */
    private Date date;
    /** The start time of the event. */
    private Time startTime;
    /** The end time of the event. */
    private Time endTime;
    /** The location of the event. */
    private Address location;
    /** The maximum number of volunteers for the event. */
    private int maxVolunteers;
    /** The current number of volunteers registered for the event. */
    private int currentVolunteers;
    /** A list of required skills for the event. */
    private List<String> requiredSkills;
    /** The ID of the organization posting the event. */
    private String orgID;
    /** Contact information for the event. */
    private String contactInfo;
    /** A list of volunteers currently registered for the event. */
    private List<User> registeredVolunteers;

    /**
     * Constructs a new Event object.
     *
     * @param eventID a unique ID for the event.
     * @param title the title of the event.
     * @param description a brief description of the event.
     * @param date the date of the event.
     * @param startTime the start time of the event.
     * @param endTime the end time of the event.
     * @param location the location of the event.
     * @param maxVolunteers the maximum number of volunteers for the event.
     * @param requiredSkills a list of required skills for the event.
     * @param orgID the ID of the organization posting the event.
     * @param contactInfo contact information for the event.
     */
    public Event(String eventID, String title, String description, Date date, Time startTime, Time endTime, Address location, int maxVolunteers, List<String> requiredSkills, String orgID, String contactInfo) {
        this.eventID = eventID;
        this.title = title;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.maxVolunteers = maxVolunteers;
        this.currentVolunteers = 0;
        this.requiredSkills = requiredSkills;
        this.orgID = orgID;
        this.contactInfo = contactInfo;
        this.registeredVolunteers = new ArrayList<>();
    }

    /**
     * Adds a volunteer to the event if there are available slots.
     *
     * @param volunteer the User object to add.
     */
    public void addVolunteer(User volunteer) {
        if (!isFull()) {
            this.registeredVolunteers.add(volunteer);
            this.currentVolunteers++;
            System.out.println(volunteer.getFirstName() + " has been added to the event: " + this.title);
        } else {
            System.out.println("The event is full. Cannot add " + volunteer.getFirstName() + ".");
        }
    }

    /**
     * Removes a volunteer from the event.
     *
     * @param volunteer the User object to remove.
     */
    public void removeVolunteer(User volunteer) {
        if (this.registeredVolunteers.remove(volunteer)) {
            this.currentVolunteers--;
            System.out.println(volunteer.getFirstName() + " has been removed from the event: " + this.title);
        } else {
            System.out.println(volunteer.getFirstName() + " was not registered for this event.");
        }
    }

    /**
     * Returns the details of the event.
     *
     * @return a formatted string of event details.
     */
    public String getEventDetails() {
        return "Event Title: " + this.title +
                "\nDescription: " + this.description +
                "\nDate: " + this.date +
                "\nLocation: " + this.location.formatAddress() +
                "\nRemaining Slots: " + getRemainingSlots();
    }

    /**
     * Checks if the event is at its maximum volunteer capacity.
     *
     * @return true if the event is full, false otherwise.
     */
    public boolean isFull() {
        return this.currentVolunteers >= this.maxVolunteers;
    }

    /**
     * Returns the number of remaining volunteer slots.
     *
     * @return the number of available slots.
     */
    public int getRemainingSlots() {
        return this.maxVolunteers - this.currentVolunteers;
    }

    /**
     * Gets the unique ID for the event.
     * @return The event ID.
     */
    public String getEventID() { return eventID; }
    /**
     * Gets the title of the event.
     * @return The event title.
     */
    public String getTitle() { return title; }
    /**
     * Gets the description of the event.
     * @return The event description.
     */
    public String getDescription() { return description; }
    /**
     * Gets the date of the event.
     * @return The event date.
     */
    public Date getDate() { return date; }
    /**
     * Gets the start time of the event.
     * @return The event start time.
     */
    public Time getStartTime() { return startTime; }
    /**
     * Gets the end time of the event.
     * @return The event end time.
     */
    public Time getEndTime() { return endTime; }
    /**
     * Gets the location of the event.
     * @return The event location.
     */
    public Address getLocation() { return location; }
    /**
     * Gets the maximum number of volunteers for the event.
     * @return The maximum number of volunteers.
     */
    public int getMaxVolunteers() { return maxVolunteers; }
    /**
     * Gets the current number of volunteers.
     * @return The current number of volunteers.
     */
    public int getCurrentVolunteers() { return currentVolunteers; }
    /**
     * Gets the list of required skills.
     * @return The list of skills.
     */
    public List<String> getRequiredSkills() { return requiredSkills; }
    /**
     * Gets the ID of the organization that posted the event.
     * @return The organization ID.
     */
    public String getOrgID() { return orgID; }
    /**
     * Gets the contact information for the event.
     * @return The contact information.
     */
    public String getContactInfo() { return contactInfo; }
}