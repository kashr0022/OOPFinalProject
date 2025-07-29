/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author A
 */
public abstract class Vehicles {

    private int vehicleID;
    private VehicleType vehicleType;
    private String consumptionRate;

    /**
     * @return the vehicleID
     */
    public int getVehicleID() {
        return vehicleID;
    }

    /**
     * @param vehicleID the vehicleID to set
     */
    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * @return the vehicleType
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     * @param vehicleType the vehicleType to set
     */
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * @return the consumptionRate
     */
    public String getConsumptionRate() {
        return consumptionRate;
    }

    /**
     * @param consumptionRate the consumptionRate to set
     */
    public void setConsumptionRate(String consumptionRate) {
        this.consumptionRate = consumptionRate;
    }

}
