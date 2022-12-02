package project_rad_user;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import project_rad_user.itemTable;
import static project_rad_user.mysqlconnect.ConnectDb;


public class UserFXMLController implements Initializable{
    
    @FXML
    private TextField searchBox;

    @FXML
    private TableView<itemTable> tableView;

    @FXML
    private TableColumn<itemTable, String> itemName;

    @FXML
    private TableColumn<itemTable, String> address;
    
    @FXML
    private TableColumn<itemTable, String> availability;
    
    @FXML
    private TextArea textArea;
    
    @FXML
    private TableView<selected_ItemTable> selected_ItemView;
    
    @FXML
    private TableColumn<selected_ItemTable,String> seleced_itemName;

    @FXML
    private TableColumn<selected_ItemTable,String> selected_address;
    
    //display item in table1
    ObservableList<itemTable> listMN = FXCollections.observableArrayList();
    //search item
    ObservableList<itemTable> dataList = FXCollections.observableArrayList();
    
    //print selected item in table2
    ObservableList<itemTable> itm = FXCollections.observableArrayList();
    ObservableList<selected_ItemTable> table2 = FXCollections.observableArrayList();

    int  index = -1;
         
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
         
    public void initialize(URL url, ResourceBundle rb){
        
        try{
            Connection con = mysqlconnect.ConnectDb();
            ResultSet rs = con.createStatement().executeQuery("select * from item_details limit 3");
            
            while(rs.next()){
                listMN.add(new itemTable(rs.getString("item_code"),rs.getString("item_name"),rs.getString("address"),rs.getString("availability")));
            }
            
        }catch(SQLException e){
             Logger.getLogger(UserFXMLController.class.getName()).log(Level.SEVERE, null, e);        
        }
        
        //itemTeable
        itemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        availability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        
        seleced_itemName.setCellValueFactory(new PropertyValueFactory<>("seleced_itemName"));
        selected_address.setCellValueFactory(new PropertyValueFactory<>("selected_address"));
        
        tableView.setItems(listMN);
        
        search_item();
        clikedColumn();
        
        
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //tableView.getSelectionModel().setCellSelectionEnabled(true);
        
        tableView.setOnMouseClicked(e -> {
            clikedColumn();
                    });
    }

    @FXML
    void search_item(){
    
        itemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        availability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        
        dataList =  mysqlconnect.getDataItemTable();
        tableView.setItems(dataList);
        FilteredList<itemTable> search = new FilteredList<>(dataList, b -> true);
        
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
        
        search.setPredicate(item -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            
            String lowerCaseFilter = newValue.toLowerCase();
            
            if (item.getItem_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            }
            else 
                return false; 
        });
    
        });
        
        SortedList<itemTable> sortedData = new SortedList<>(search);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
} 
       
    @FXML
    public void clikedColumn(){
    
        for(itemTable itm : tableView.getSelectionModel().getSelectedItems()){
            table2.add(new selected_ItemTable(itm.getItem_name(), itm.getAddress()));
            selected_ItemView.setItems(table2);
        }    
        
    }

}

