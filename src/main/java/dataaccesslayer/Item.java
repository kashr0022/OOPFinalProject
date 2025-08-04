/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

/**
 *
 * @author franm
 */
public class Item {
    
    String tableName;
    
    /**
     * Converts item name to a String containing "ClassName@HashCode", and chops off the HashCode
     * This is done as the item name corresponds to a table name
     * @param item
     * @return 
     */
    public String toString(Item item) { //used in daoimpl
        
        return item.toString().substring(0, item.toString().indexOf('@'));
    }
    
    /**
     * 
     * @param item
     * @return 
     */
    public String getTableName() { //used to get the required table name
        return tableName;
    }
}
