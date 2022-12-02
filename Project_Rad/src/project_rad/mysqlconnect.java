/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_rad;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Anjalee
 */
public class mysqlconnect {
    Connection con = null;
    
    public static Connection ConnectDb(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/RAD_Project", "root","");
            //JOptionPane.showMessageDialog(null, "Connection Successful !"); 
            return con;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e); 
            return null;
            
        }
    }
    
    public static ObservableList<ItemTable> getDataItemTable(){
        
        Connection con = ConnectDb();
        ObservableList<ItemTable> list = FXCollections.observableArrayList();
                
        try {
            PreparedStatement pst = (PreparedStatement) con.prepareStatement("select * from item_details");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                list.add(new ItemTable(rs.getString("item_code"),rs.getString("item_name"),rs.getString("address"),rs.getString("availability")));

                
            }
        }catch(Exception e){
            
        }
        
        return list;
    }
}
