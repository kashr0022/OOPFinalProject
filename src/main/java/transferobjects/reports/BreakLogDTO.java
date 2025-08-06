package transferobjects.reports;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing a break log entry for a staff member.
 * It contains details about the break action and the timestamp when it occurred.
 * 
 * @author Khairunnisa Ashri
 */
public class BreakLogDTO {
    private int logID;
    private int staffID;
    private String action;
    private LocalDateTime timestamp;

    /**
     * Gets the unique identifier for this break log entry.
     * 
     * @return the log ID
     */
    public int getLogID() {
        return logID;
    }

    /**
     * Sets the unique identifier for this break log entry.
     * 
     * @param logID the log ID to set
     */
    public void setLogID(int logID) {
        this.logID = logID;
    }

    /**
     * Gets the staff ID associated with this break log.
     * 
     * @return the staff ID
     */
    public int getStaffID() {
        return staffID;
    }

    /**
     * Sets the staff ID associated with this break log.
     * 
     * @param staffID the staff ID to set
     */
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    /**
     * Gets the action description for this break log.
     * 
     * @return the action description (e.g., "Start Break", "End Break")
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action description for this break log.
     * 
     * @param action the action description to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Gets the timestamp when this break action occurred.
     * 
     * @return the timestamp as a {@link LocalDateTime}
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp when this break action occurred.
     * 
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
