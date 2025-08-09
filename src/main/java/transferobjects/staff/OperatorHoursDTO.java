
package transferobjects.staff;

/**
 * DTO representing the hours worked by an operator, contains the operator's name and the total hours worked.
 * 
 * @author Khairunnisa Ashri
 */
public class OperatorHoursDTO {
    private String operatorName;
    private double hoursWorked;
    
     /**
     * Constructs an OperatorHoursDTO with the specified operator name and hours worked.
     *
     * @param operatorName the name of the operator
     * @param hoursWorked the total hours worked by the operator
     */
    public OperatorHoursDTO(String operatorName, double hoursWorked) {
        this.operatorName = operatorName;
        this.hoursWorked = hoursWorked;
    }

   /**
     * Returns the operator's name
     *
     * @return the operatorName
     */
    public String getOperatorName() {
        return operatorName;
    }
    
     /**
     * Returns the total hours worked by the operator
     *
     * @return the hoursWorked
     */
    public double getHoursWorked() {
        return hoursWorked;
    }
            
}
