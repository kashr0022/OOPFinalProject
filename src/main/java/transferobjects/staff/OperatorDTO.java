/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobjects.staff;

import model.Role;
import model.Staff;

/**
 * Data Transfer Object (DTO) representing an Operator staff member.
 * This class extends the Staff base class and sets the role to OPERATOR.
 * 
 * @author Khairunnisa Ashri
 */
public class OperatorDTO extends Staff {

    /**
     * Constructs a new OperatorDTO and sets the role to OPERATOR.
     */
    public OperatorDTO() {
        this.setRole(Role.OPERATOR);
    }
    
    /**
     * Indicates whether the operator has special permissions.
     * Operators do not have special permissions.
     * 
     * @return false always, as operators lack special permissions
     */
    @Override
    public boolean hasSpecialPerm(){
        return false;
    }
}
