package adminlogin;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class adminloginViewcontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgadmin;

    @FXML
    private PasswordField passadmin;
    @FXML
    private Label lblinfo;

    @FXML
    void dologin(ActionEvent event) {
    	if(!passadmin.getText().equals("1234567"))
    	{
    		lblinfo.setText("Incorrect passcode");
    	}
    	else
    	{
    		lblinfo.setText("confirmed");
    		try
    		{
    			Parent root=FXMLLoader.load(getClass().getResource("/Admindesk/AdmindeskView.fxml")); 
    			Scene scene = new Scene(root);
    			Stage stage=new Stage();
    			stage.setScene(scene);
    			stage.show();
        		
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	
    	
    	
    	
    }

    

    @FXML
    void initialize() {
        assert imgadmin != null : "fx:id=\"imgadmin\" was not injected: check your FXML file 'adminloginView.fxml'.";
        assert passadmin != null : "fx:id=\"passadmin\" was not injected: check your FXML file 'adminloginView.fxml'.";

    }

}
