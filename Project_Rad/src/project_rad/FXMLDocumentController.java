/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_rad;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Anjalee
 */
public class FXMLDocumentController implements Initializable {
    
     @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    Connection  con;
    PreparedStatement pst;
    ResultSet rs;
        

    @FXML
    private Button login;

    @FXML
    void login(ActionEvent event) throws IOException {
        

        String uname = username.getText();
        String pass = password.getText();
        
        if(uname.equals("") && pass.equals(""))
        {
            JOptionPane.showMessageDialog(null, "User Name or Password is Empty !");

        }   
        else
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/RAD_Project", "root","");
                
                pst = con.prepareStatement("select * from admin_details where username=? and password=?");
                
                pst.setString(1,uname);
                pst.setString(2, pass); 
                
                rs = pst.executeQuery();
                
                if(rs.next())
                {
                    Parent LoginPage = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                   
                   
                    Scene LoginPageScene = new Scene(LoginPage);
                    Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    
                    appStage.setScene(LoginPageScene);
                    appStage.show();
                    JOptionPane.showMessageDialog(null, "Login Successful !"); 
                                             
                }
                
                else
                {
                    JOptionPane.showMessageDialog(null, "Login Failed !");
                    username.setText("");
                    password.setText("");
                    username.requestFocus();;

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private static class loader {

        private static AdminController getController() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public loader() {
        }
    }
    
}
