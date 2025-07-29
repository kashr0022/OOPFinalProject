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
public class OperatorDTO extends Staff {
    
     public OperatorDTO() {
        this.setRole(Role.OPERATOR);
    }
    
    @Override
    public boolean hasSpecialPerm(){
        return false;
    }
}
