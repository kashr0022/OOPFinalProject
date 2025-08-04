/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.util.List;

/**
 *
 * @author franm
 */
public interface DaoInterface {
    
    /**
     * 
     */
    String getAllQuery = "SELECT * FROM ?"; //? because we'll need to swap it out depending on the table
    
    /**
     * 
     */
    String getByIdQuery = "SELECT * FROM ? WHERE ? = ?";
    
    /**
     * 
     */
    String addItemQuery = "INSERT INTO ? VALUES (FirstName, LastName, Email, Role)"; //TEMPORARY HARDCODE, CHANGE THIS
    
    /**
     * 
     */
    String updateItemQuery = "";
    
    /**
     * 
     */
    String deleteItemQuery = "";
    
    /**
     * 
     * @param item
     * @return 
     */
    List<Item> getAllItems(Item item);
    
    /**
     * 
     * @param id 
     */
    void getItemById(int id);
    
    /**
     * 
     * @param i 
     */
    void addItem(Item i);
    
    /**
     * 
     * @param i 
     */
    void updateItem(Item i);
    
    /**
     * 
     * @param i 
     */
    void deleteItem(Item i);
}
