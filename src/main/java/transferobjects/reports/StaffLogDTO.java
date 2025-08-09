
package transferobjects.reports;

import java.time.LocalDateTime;

/**
 * DTO representing a staff log entry, records staff work session timings and optional notes.
 * 
 * @author Khairunnisa Ashri
 */
public class StaffLogDTO {
    private int logNo;
    private int staffID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String notes;
    
    public StaffLogDTO() {
    }

    /**
     * @return the logNo
     */
    public int getLogNo() {
        return logNo;
    }

    /**
     * @param logNo the logNo to set
     */
    public void setLogNo(int logNo) {
        this.logNo = logNo;
    }

    /**
     * @return the staffID
     */
    public int getStaffID() {
        return staffID;
    }

    /**
     * @param staffID the staffID to set
     */
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    /**
     * @return the startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    
}
