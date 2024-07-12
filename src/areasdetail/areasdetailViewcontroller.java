package areasdetail;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import papermaster.mysqlconnector;

public class areasdetailViewcontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblareas;

    @FXML
    private TextField textareas;
    PreparedStatement pst;
    Connection con1;


    @FXML
    void dosave(ActionEvent event) {
    	try {
			pst=con1.prepareStatement("insert into areas values(?)");
		
			pst.setString(1,textareas.getText());
			
			pst.execute();
			lblareas.setText("Record Saved........");
			
	} 
			
			
	catch (SQLException e) 
		{
		  e.printStackTrace();
		}
}
		

    

    @FXML
    void initialize() throws SQLException {
    	con1=mysqlconnector.doConnect();
    	if(con1==null)
    		System.out.println("connection problem");
    	else
    		System.out.println("connectedddd");


}
}
