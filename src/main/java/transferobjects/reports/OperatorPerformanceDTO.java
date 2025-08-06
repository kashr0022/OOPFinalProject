
package transferobjects.reports;

import model.Role;

/**
 * Data Transfer Object (DTO) representing the performance metrics of an operator.
 * Includes personal details, role, punctuality, work duration, and current status.
 * 
 * @author Khairunnisa Ashri
 */
public class OperatorPerformanceDTO {
    private int staffID;
    private Role role;
    private String firstName;
    private String lastName;
    private double onTimeRate;
    private double avgRouteDuration;
    private double totalHoursWorked;
    private String status;

    
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the onTimeRate
     */
    public double getOnTimeRate() {
        return onTimeRate;
    }

    /**
     * @param onTimeRate the onTimeRate to set
     */
    public void setOnTimeRate(double onTimeRate) {
        this.onTimeRate = onTimeRate;
    }

    /**
     * @return the avgRouteDuration
     */
    public double getAvgRouteDuration() {
        return avgRouteDuration;
    }

    /**
     * @param avgRouteDuration the avgRouteDuration to set
     */
    public void setAvgRouteDuration(double avgRouteDuration) {
        this.avgRouteDuration = avgRouteDuration;
    }

    /**
     * @return the totalHoursWorked
     */
    public double getTotalHoursWorked() {
        return totalHoursWorked;
    }

    /**
     * @param totalHoursWorked the totalHoursWorked to set
     */
    public void setTotalHoursWorked(double totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

   
}
