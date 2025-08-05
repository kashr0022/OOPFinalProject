/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobjects.staff;

/**
 *
 * @author A
 */
public class OperatorHoursDTO {
    private String operatorName;
    private double hoursWorked;
    
    public OperatorHoursDTO(String operatorName, double hoursWorked) {
        this.operatorName = operatorName;
        this.hoursWorked = hoursWorked;
    }

    public OperatorHoursDTO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String getOperatorName(){
        return operatorName;
    }
    
    public double getHoursWorked() {
        return hoursWorked;
    }
            
}
