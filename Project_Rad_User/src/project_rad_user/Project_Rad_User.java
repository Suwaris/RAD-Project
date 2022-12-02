
package project_rad_user;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

    
   public class Project_Rad_User extends Application {
    
   @Override
    public void start(Stage stage) throws Exception {
        
        //FXMLLoader loader = new FXMLLoader(Project_Rad_User.class.getResource("UserFXML.fxml"));
       // Parent root = loader.load();
        Parent root = FXMLLoader.load(getClass().getResource("UserFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

