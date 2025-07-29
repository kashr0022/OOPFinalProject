/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobjects.staff;

import model.Role;
import model.Staff;

/**
 *
 * @author khair
 */
public class TransitManagerDTO extends Staff {
    
    public TransitManagerDTO() {
        this.setRole(Role.TRANSIT_MANAGER);
    }
    
    @Override
    public boolean hasSpecialPerm() {
        return true;
    }
}
