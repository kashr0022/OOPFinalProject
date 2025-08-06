package transferobjects.staff;

import model.Role;
import model.Staff;

/**
 * Data Transfer Object representing a Transit Manager.
 * This class extends Staff and assigns the role of TRANSIT_MANAGER.
 * It indicates that a Transit Manager has special permissions.
 * 
 * @author Khairunnisa Ashri
 */
public class TransitManagerDTO extends Staff {
    
    /**
     * Constructs a TransitManagerDTO and sets the role to TRANSIT_MANAGER.
     */
    public TransitManagerDTO() {
        this.setRole(Role.TRANSIT_MANAGER);
    }
    
    /**
     * Indicates whether this staff member has special permissions.
     * For Transit Manager, this returns true.
     * 
     * @return true indicating special permissions are granted
     */
    @Override
    public boolean hasSpecialPerm() {
        return true;
    }
}
