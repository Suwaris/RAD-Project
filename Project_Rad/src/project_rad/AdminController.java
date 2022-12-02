
package project_rad;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Anjalee
 */
public class AdminController implements Initializable {
    
    @FXML
    private TableView<ItemTable> table;

    @FXML
    private TableColumn<ItemTable, String> tcode;

    @FXML
    private TableColumn<ItemTable,String > tname;

    @FXML
    private TableColumn<ItemTable, String> taddress;

    @FXML
    private TableColumn<ItemTable, String> tavailability;
    
    @FXML
    private Button logout;

    @FXML
    private TextField code;

    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField availability;

    ObservableList<ItemTable> listM;
    int  index = -1;
         
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @FXML
    void add(ActionEvent event) {
        con = mysqlconnect.ConnectDb();
        String sql = "insert into item_details(item_code,item_name,address,availability,qty)values(?,?,?,?,?)";
            try{
                pst = con.prepareStatement(sql);
                pst.setString(1,code.getText());
                pst.setString(2,name.getText());
                pst.setString(3,address.getText());
                pst.setString(4,availability.getText());
                pst.execute();
                
                JOptionPane.showMessageDialog(null, "Item added !");
                code.setText("");
                name.setText("");
                address.setText("");
                availability.setText("");
                code.requestFocus();;
                UpdateTable();
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        
        
    }
    
    @FXML
    void getSelected (MouseEvent event){
        index = table.getSelectionModel().getSelectedIndex();
        if(index <=-1){
            
            return;
        }
        code.setText(tcode.getCellData(index).toString());
        name.setText(tname.getCellData(index).toString());
        address.setText(taddress.getCellData(index).toString());
        availability.setText(tavailability.getCellData(index).toString());
    }

    

    @FXML
    void update(ActionEvent event) {
        try{
            con = mysqlconnect.ConnectDb();
            String value1 = code.getText();
            String value2 = name.getText();
            String value3 = address.getText();
            String value4 = availability.getText();
            
            String sql = "update item_details set item_code = '"+value1+"',item_name = '"+value2+"',address = '"+value3+"','"+value4+"' where item_code='"+value1+"' ";
            pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Item Updated !");
            UpdateTable();
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
            
        }   

    }
    
    @FXML
    void delete(ActionEvent event) {
       con = mysqlconnect.ConnectDb();
       String sql = "delete from item_details where item_code = ?";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, code.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Item Deleted !");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }

    }
    
    public void UpdateTable(){
        tcode.setCellValueFactory(new PropertyValueFactory<ItemTable,String>("item_code"));
        tname.setCellValueFactory(new PropertyValueFactory<ItemTable,String>("item_name"));
        taddress.setCellValueFactory(new PropertyValueFactory<ItemTable,String>("address"));
        tavailability.setCellValueFactory(new PropertyValueFactory<ItemTable,String>("availability"));
        
        listM = mysqlconnect.getDataItemTable();
        table.setItems(listM);
    }
    
    Stage stage;
    @FXML
    void logout(ActionEvent event) {
        
        logout.getScene().getWindow().hide();
        

    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
    }    
    
}
