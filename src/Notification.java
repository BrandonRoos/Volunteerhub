import java.util.Date;

/**
 * The Notification class represents a message sent to a user.
 * @author Brandon Roos
 */
public class Notification {
    /** A unique ID for the notification. */
    private String notificationID;
    /** The ID of the user to whom the notification is sent. */
    private String userID;
    /** The content of the notification message. */
    private String message;
    /** The timestamp when the notification was created. */
    private Date timestamp;
    /** The read status of the notification. */
    private boolean readStatus;
    /** The type of notification (e.g., Reminder, Approval, NewOpportunity). */
    private NotificationType type;

    /**
     * Enum to define the type of a notification.
     */
    public enum NotificationType {
        REMINDER,
        APPROVAL,
        NEW_OPPORTUNITY
    }

    /**
     * Constructs a new Notification object.
     *
     * @param notificationID a unique ID for the notification.
     * @param userID the ID of the user to whom the notification is sent.
     * @param message the content of the notification.
     * @param type the type of notification (Reminder, Approval, or NewOpportunity).
     */
    public Notification(String notificationID, String userID, String message, NotificationType type) {
        this.notificationID = notificationID;
        this.userID = userID;
        this.message = message;
        this.timestamp = new Date();
        this.readStatus = false;
        this.type = type;
    }

    /**
     * Sends the notification.
     */
    public void sendNotification() {
        System.out.println("Sending notification to user " + this.userID + ": " + this.message);
    }

    /**
     * Marks the notification as read.
     */
    public void markAsRead() {
        this.readStatus = true;
        System.out.println("Notification " + this.notificationID + " marked as read.");
    }

    /**
     * Displays the notification.
     */
    public void displayNotification() {
        System.out.println("--- Notification ---");
        System.out.println("To: " + this.userID);
        System.out.println("Message: " + this.message);
        System.out.println("Type: " + this.type);
        System.out.println("Time: " + this.timestamp);
        System.out.println("Status: " + (this.readStatus ? "Read" : "Unread"));
    }

    /**
     * Gets the unique ID of the notification.
     * @return The notification ID.
     */
    public String getNotificationID() { return notificationID; }
    /**
     * Gets the ID of the user.
     * @return The user ID.
     */
    public String getUserID() { return userID; }
    /**
     * Gets the message content.
     * @return The message.
     */
    public String getMessage() { return message; }
    /**
     * Gets the timestamp of the notification.
     * @return The timestamp.
     */
    public Date getTimestamp() { return timestamp; }
    /**
     * Checks if the notification has been read.
     * @return true if read, false otherwise.
     */
    public boolean isReadStatus() { return readStatus; }
    /**
     * Gets the type of the notification.
     * @return The notification type.
     */
    public NotificationType getType() { return type; }
}