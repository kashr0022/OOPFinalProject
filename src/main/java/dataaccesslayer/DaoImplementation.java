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
                PreparedStatement ps = con.prepareStatement(getAllQuery)) {
           ps.setString(1, item.getTableName());
           //Have to create 2nd try w/ resources, to auto-close rs in case of problem
           try (ResultSet rs = ps.executeQuery()) {
               
           }
           
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
    public void addItem(Item item) {
        
        try (Connection con = DataSource.getConnection(); 
                PreparedStatement ps = con.prepareStatement(addItemQuery)) {
           ps.setString(1, item.getTableName());
           //Have to create 2nd try w/ resources, to auto-close rs in case of problem
           ps.executeQuery(addItemQuery);
           
        }
        catch (SQLException e) {
            
        }
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
