package Admindesk;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class AdmindeskViewcontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void dobill(ActionEvent event) {try{
		Parent root=FXMLLoader.load(getClass().getResource("/billboard/billboardView.fxml")); 
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

    

    @FXML
    void dobillcollector(ActionEvent event) {try{
		Parent root=FXMLLoader.load(getClass().getResource("/billcollector/billcollectorView.fxml")); 
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

    

    @FXML
    void dobillgenerator(ActionEvent event) {try{
		Parent root=FXMLLoader.load(getClass().getResource("/billgenerator/billgeneratorView.fxml")); 
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


    @FXML
    void docustomermanager(ActionEvent event) {try{
		Parent root=FXMLLoader.load(getClass().getResource("/customermanager/customermanagerView.fxml")); 
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

    

    @FXML
    void dodisplayhawkers(ActionEvent event) {try{
		Parent root=FXMLLoader.load(getClass().getResource("/showallhawkers/showallhawkersView.fxml")); 
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

    

    @FXML
    void dohawkermanager(ActionEvent event) {try{
		Parent root=FXMLLoader.load(getClass().getResource("/hawkermanager/hawkermanagerView.fxml")); 
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

    

    @FXML
    void domeet(ActionEvent event) {

    }

    

    @FXML
    void dopapermaster(ActionEvent event) {
     	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/papermaster/papermasterView.fxml")); 
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
    @FXML
    void docustomerpanel(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/customerpanel/customerpanelView.fxml")); 
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
    

    	
    Connection con1;


    @FXML
    void initialize() throws SQLException {
    	con1=mysqlconnector.doConnect();
    	if(con1==null)
    		System.out.println("connection problem");
    	else
    		System.out.println("connectedddd"); {

    }

    }
}
