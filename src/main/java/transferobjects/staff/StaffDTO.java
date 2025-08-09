package transferobjects.staff;

/**
 * DTO of Staff records from the PTFMS db. Contains private fields for each
 * characteristic, public getters and setters for data access and modification. enforces separation of
 * application layers. Used to pass information from presentation to business to data
 * 
 * @author Lily S.
 */
public class StaffDTO {

    private int staffId;
    private String firstName;
    private String lastName;
    private String email;
    private String Role;

    /**
     * Getter for the staff ID field
     * @return int, numerical staff ID
     */
    public int getStaffId() {
        return staffId;
    }

    /**
     * Setter for the staff ID field
     * @param staffId, numerical Staff ID
     */
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    /**
     * Getter for the staff's first name
     * @return String, staff's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the staff's first name
     * @param firstName String, staff's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the staff's last name
     * @return String, staff's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the staff's last name
     * @param lastName String, staff's first name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for the staff's email address
     * @return String, staff's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for the staff's email address
     * @param email, String, staff's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for the staff's role (Operator/Transit Manager)
     * @return String, staff's role
     */
    public String getRole() {
        return Role;
    }

    /**
     * Setter for staff's role (Operator/Transit Manager)
     * @param role, staff's role
     */
    public void setRole(String role) {
        Role = role;
    }
}
