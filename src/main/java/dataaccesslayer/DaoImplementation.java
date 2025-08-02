/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author franm
 */
public class DaoImplementation implements DaoInterface {
    
    
    
    /**
     * 
     * @return 
     */
    @Override
    public List<Item> getAllItems(Item item) {

        try (Connection con = DataSource.getConnection(); 
                PreparedStatement ps = con.prepareStatement(getAllQuery); ResultSet rs = null) {
           ps.setString(1, item.toString().substring(0, item.toString().indexOf('@')));
           //Above code converts item name to a String with ClassName@HashCode, and chops off the HashCode
           //This is done as the item name corresponds to a table name
        }
        catch (SQLException e) {
            
        }
        return null;
        
    }
    
    /**
     * 
     * @param id 
     */
    @Override
    public void getItemById(int id) {
        
    }
    
    /**
     * 
     * @param i 
     */
    @Override
    public void addItem(Item i) {
        
    }
    
    /**
     * 
     * @param i 
     */
    @Override
    public void updateItem(Item i) {
        
    }
    
    /**
     * 
     * @param i 
     */
    @Override
    public void deleteItem(Item i) {
        
    }
}
