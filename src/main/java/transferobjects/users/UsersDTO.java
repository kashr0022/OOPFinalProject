package transferobjects.users;

/**
 * DTO of User records from the PTFMS db. Contains private fields for each
 * characteristic, public getters and setters for data access & modification. enforces separation of
 * application layers. Used to pass information from presentation to business to data
 * 
 * @author Lily S.
 */
public class UsersDTO {
    private String username;
    private String password;
    private String role;

    /**
     * Getter for user's username
     * @return String, user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for user's username
     * @param username, String, user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for user's password
     * @return String, user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for user's password
     * @param password, String, user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for user's role (Operator/Transit Manager)
     * @return String, user's role
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter for user's role (Operator/Transit Manager)
     * @param role, String, user's role
     */
    public void setRole(String role) {
        this.role = role;
    }

}
