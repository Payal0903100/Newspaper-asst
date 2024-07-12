package billboard;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class billboardViewcontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton radiopaid;

    @FXML
    private RadioButton radiopending;

    @FXML
    private TableView<billboardbean> tabledata;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txttotalamount;
    PreparedStatement pst;
    Connection con;

    @FXML
    void dobillhistory(ActionEvent event) {
        
        	tabledata.getColumns().clear();
        	TableColumn<billboardbean,String> mn=new TableColumn<billboardbean,String>("Mobile no.");
        	mn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        	mn.setMinWidth(100);
        	tabledata.getColumns().add(mn);
        	
        	TableColumn<billboardbean,String> bf=new TableColumn<billboardbean,String>("Bill From");
        	bf.setCellValueFactory(new PropertyValueFactory<>("datefrom"));
        	bf.setMinWidth(100);
        	tabledata.getColumns().add(bf);
        	
        	TableColumn<billboardbean,String> dt=new TableColumn<billboardbean,String>("Bill to");
        	dt.setCellValueFactory(new PropertyValueFactory<>("dateto"));
        	dt.setMinWidth(100);
        	tabledata.getColumns().add(dt);
        	
        	TableColumn<billboardbean,String> a=new TableColumn<billboardbean,String>("Amount");
        	a.setCellValueFactory(new PropertyValueFactory<>("bill"));
        	a.setMinWidth(100);
        	tabledata.getColumns().add(a);
        	tabledata.setItems(FetchDetails());
        	
        }

      
        ObservableList<billboardbean> FetchDetails() 
        {
        	String m=txtmobile.getText();
        	String s;
        	float total=0;
        	ObservableList<billboardbean>ary=FXCollections.observableArrayList();
        	try {
        		pst = con.prepareStatement("select * from bills where mobile=?");
        		pst.setString(1, m);
        		ResultSet table=pst.executeQuery();
        		while(table.next()) {
    	    		String mno=table.getString("mobile");
    	    		String df = String.valueOf(table.getDate("datefrom").toLocalDate());
    	    		String dt= String.valueOf(table.getDate("dateto").toLocalDate());
    	    		String a=table.getString("bill");
    	    		total+=Float.parseFloat(a);
    	    		billboardbean ref=new billboardbean( mno,df,dt,a);
    	    		
    	    		ary.add(ref);
        		}
        		txttotalamount.setText(String.valueOf(total));
        	}
        	catch(Exception ex) { ex.printStackTrace(); }
        		return ary;
        		
        
    }

    @FXML
    void doexport(ActionEvent event) {

    }

    @FXML
    void dosearch(ActionEvent event) {
    	tabledata.getColumns().clear();
    	
     	TableColumn<billboardbean, String> mn=new TableColumn<billboardbean, String>("mobile no");
    	mn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mn.setMinWidth(50);
    	tabledata.getColumns().add(mn);
    	TableColumn<billboardbean, String> df=new TableColumn<billboardbean, String>("bill from");
    	df.setCellValueFactory(new PropertyValueFactory<>("datefrom"));
    	df.setMinWidth(50);
    	tabledata.getColumns().add(df);
    		TableColumn<billboardbean, String> dt=new TableColumn<billboardbean, String>("bill to");
    	dt.setCellValueFactory(new PropertyValueFactory<>("dateto"));
    	dt.setMinWidth(50);
    	tabledata.getColumns().add(dt);
    	TableColumn<billboardbean, String> amt=new TableColumn<billboardbean, String>("amount");
        amt .setCellValueFactory(new PropertyValueFactory<>("bill"));
    	amt.setMinWidth(50);
    	tabledata.getColumns().add(amt);
    	tabledata.setItems(SearchDetails());
	  	
    }
    ObservableList<billboardbean> SearchDetails()
    
    {
String mn=txtmobile.getText();
String s;
if(radiopending.isSelected())
{
	s="0";
}
	else
		s="1";
	float total=0;
	 
	    
	    {
	    	ObservableList<billboardbean>	ary=FXCollections.observableArrayList();
	    	try {
	    	   	
	    		pst = con.prepareStatement("select * from bills where billstatus=?");
	    		
	    		pst.setString(1, s);
	    		ResultSet table=pst.executeQuery();
	    	
	    		while(table.next()) {
		    		String mno=table.getString("mobile");
		    		String df =String.valueOf( table.getDate("datefrom").toLocalDate());
		    		String dt =String.valueOf( table.getDate("dateto").toLocalDate());
		    		
		    
		    		float Amt=table.getFloat("bill");
		    		String amt=Float.toString(Amt);
		    		
		    		total+=Float.parseFloat(amt);
		    		
		    		billboardbean ref=new billboardbean(mno,df,dt,amt);
		    		ary.add(ref);
	    		}
	    		 txttotalamount.setText(String.valueOf(total));
	    	
	    	}
	    	catch(Exception ex) 
	    	{
	    		ex.printStackTrace();
	    		}
	    	return ary;
	    }
	    }
	   
	    @FXML
	    void initialize() {
	    	con = mysqlconnector.doConnect();
	    	if(con==null) { System.out.println("Invalid Connection");
	    	}
	    	else { System.out.println("Connected");	}
	    }
	}


    	
     
