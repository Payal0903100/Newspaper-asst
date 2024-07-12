package showallhawkers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;


public class showallhawkersViewcontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableView<showallhawkerbean> tablehawker;


    @FXML
    private Label lblresp;

    @FXML
    void dofetch(ActionEvent event) {
    	TableColumn<showallhawkerbean, String> name=new TableColumn<showallhawkerbean, String>("Hawker Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("hname"));
    	
    	TableColumn<showallhawkerbean, String> mobile=new TableColumn<showallhawkerbean, String>("Hawker Mobile No");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(50);
    	
    	TableColumn<showallhawkerbean, String> alloarea=new TableColumn<showallhawkerbean, String>("Allocated Areas");
    	alloarea.setCellValueFactory(new PropertyValueFactory<>("alloareas"));
    	alloarea.setMinWidth(50);
    	
    	TableColumn<showallhawkerbean, String> doj=new TableColumn<showallhawkerbean, String>("Date of joining");
    	doj.setCellValueFactory(new PropertyValueFactory<>("doj"));
    	doj.setMinWidth(50);
    	
    	tablehawker.getColumns().addAll(new ArrayList<>(Arrays.asList(name, mobile, alloarea, doj)));
    	tablehawker.setItems(FetchAllhawkers());
    	
    	lblresp.setText("Records Fetched Successfully...");
    }
    
    Connection con;
    PreparedStatement pst;  
    
    ObservableList<showallhawkerbean> FetchAllhawkers()
    
    {
    	ObservableList<showallhawkerbean>	ary=FXCollections.observableArrayList();
    	try {
    	   	
    		pst = con.prepareStatement("select * from hawkers");
    		ResultSet table=pst.executeQuery();
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String name = table.getString("hname");
	    		String DOJ = String.valueOf(table.getDate("doj").toLocalDate());
	    		String alloarea=table.getString("alloareas");
	    		
	    		showallhawkerbean ref=new showallhawkerbean(name, mno, alloarea, DOJ);
	    		ary.add(ref);
    		}
    	
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return ary;
    }
   
    @FXML
    void initialize() {
    	con = mysqlconnector.doConnect();
    	if(con==null) { System.out.println("Invalid Connection");
    	}
    	else { System.out.println("Connected");	}
    }
}

    
