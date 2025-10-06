import java.util.Date;

/**
 * The VolunteerRecord class tracks a user's submitted volunteer hours for a specific event.
 * @author Brandon Roos
 */
public class VolunteerRecord {
    /** A unique ID for the record. */
    private String recordID;
    /** The ID of the volunteer. */
    private String userID;
    /** The ID of the event. */
    private String eventID;
    /** The number of hours submitted. */
    private double hoursSubmitted;
    /** The date the hours were submitted. */
    private Date submissionDate;
    /** The approval status of the record. */
    private Status status;
    /** The ID of the user who approved or rejected the record. */
    private String approverID;

    /**
     * Enum to define the approval status of a volunteer record.
     */
    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    /**
     * Constructs a new VolunteerRecord object.
     *
     * @param recordID a unique ID for the record.
     * @param userID the ID of the volunteer.
     * @param eventID the ID of the event.
     * @param hoursSubmitted the number of hours submitted.
     */
    public VolunteerRecord(String recordID, String userID, String eventID, double hoursSubmitted) {
        this.recordID = recordID;
        this.userID = userID;
        this.eventID = eventID;
        this.hoursSubmitted = hoursSubmitted;
        this.submissionDate = new Date();
        this.status = Status.PENDING;
        this.approverID = null;
    }

    /**
     * Submits the hours to the system.
     */
    public void submitHours() {
        System.out.println("Record " + this.recordID + " submitted. Status: " + this.status);
    }

    /**
     * Returns the current approval status of the record.
     *
     * @return the status (Pending, Approved, or Rejected).
     */
    public Status getApprovalStatus() {
        return this.status;
    }

    /**
     * Updates the status of the record and sets the approver's ID.
     *
     * @param newStatus the new status to set.
     * @param approverID the ID of the user who approved or rejected the record.
     */
    public void updateStatus(Status newStatus, String approverID) {
        this.status = newStatus;
        this.approverID = approverID;
        System.out.println("Record " + this.recordID + " status updated to " + this.status);
    }

    /**
     * Gets the unique ID of the record.
     * @return The record ID.
     */
    public String getRecordID() { return recordID; }
    /**
     * Gets the ID of the volunteer.
     * @return The user ID.
     */
    public String getUserID() { return userID; }
    /**
     * Gets the ID of the event.
     * @return The event ID.
     */
    public String getEventID() { return eventID; }
    /**
     * Gets the number of hours submitted.
     * @return The hours submitted.
     */
    public double getHoursSubmitted() { return hoursSubmitted; }
    /**
     * Gets the date of submission.
     * @return The submission date.
     */
    public Date getSubmissionDate() { return submissionDate; }
    /**
     * Gets the ID of the approver.
     * @return The approver's ID.
     */
    public String getApproverID() { return approverID; }
}