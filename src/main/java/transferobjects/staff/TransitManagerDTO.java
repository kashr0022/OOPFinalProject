package transferobjects.staff;

import model.Role;
import model.Staff;

/**
 * DTO representing a Transit Manager. This class extends Staff and assigns the role of TRANSIT_MANAGER
 * 
 * @author Khairunnisa Ashri
 */
public class TransitManagerDTO extends Staff {
    
    /**
     * Constructs a TransitManagerDTO and sets the role to TRANSIT_MANAGER
     */
    public TransitManagerDTO() {
        this.setRole(Role.TRANSIT_MANAGER);
    }
    
    /**
     * Indicates whether this staff member has special permissions
     * 
     * @return true indicating special permissions are granted
     */
    @Override
    public boolean hasSpecialPerm() {
        return true;
    }
}
