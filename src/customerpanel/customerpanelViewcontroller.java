package customerpanel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import customerpanelbean.customerpanelbean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import papermaster.mysqlconnector;
import showallhawkers.showallhawkerbean;

public class customerpanelViewcontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private ComboBox<String> combopaper;

    @FXML
    private Label lblarea;

    @FXML
    private Label lblpaper;

    @FXML
    private TableView<customerpanelbean> tabledata;
 
    @FXML
    void doexport(ActionEvent event) {

    }

    @FXML
    void dofetch(ActionEvent event) 
    	 {
    	    	TableColumn<customerpanelbean, String> name=new TableColumn<customerpanelbean,String>("Customer name");
    	    	name.setCellValueFactory(new PropertyValueFactory<>("cname"));
    	    	name.setMinWidth(50);
    	    	
    	    	TableColumn<customerpanelbean, String> mobile=new TableColumn<customerpanelbean, String>("Customer Mobile");
    	    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	    	mobile.setMinWidth(50);
    	    	
    	    	TableColumn<customerpanelbean, String> area=new TableColumn<customerpanelbean, String>("Areas");
    	    	area.setCellValueFactory(new PropertyValueFactory<>("area"));
    	    	area.setMinWidth(50);
    	    	
    	    	tabledata.getColumns().clear();
    	    	tabledata.getColumns().addAll(new ArrayList<>(Arrays.asList(name,mobile,area)));
    	    	tabledata.setItems(FetchAll());
    	    	
    	    }
    	    
    	    ObservableList<customerpanelbean> FetchAll()
    	    {
    	    	ObservableList<customerpanelbean> ary=FXCollections.observableArrayList();
    	    	try
    	    	{
    	    		pst=con.prepareStatement("select * from current where spapers like ?");
    	    		pst.setString(1, "%"+combopaper.getSelectionModel().getSelectedItem()+"%");
    	    		ResultSet table=pst.executeQuery();
    	    		while(table.next())
    	    		{  			
    	    			String name=table.getString("cname");
    	    			String mobile=table.getString("mobile");
    	    			String area=table.getString("area");
    	    			
    	    			customerpanelbean ref=new customerpanelbean(name,mobile,area);
    	    			ary.add(ref);
    	    		}
    	    	}
    	    	catch(Exception exp)
    	    	{
    	    		exp.printStackTrace();
    	    	}
    	    	return ary;
    	    }
    	    
    	    PreparedStatement pst;
    	    Connection con;
    	    

    @FXML
    void dofilter(ActionEvent event)  	
    {
    	TableColumn<customerpanelbean, String> name=new TableColumn<customerpanelbean, String>("Customer name");
    	name.setCellValueFactory(new PropertyValueFactory<>("cname"));
    	name.setMinWidth(50);
    	
    	TableColumn<customerpanelbean, String> spapers=new TableColumn<customerpanelbean, String>("Selected Papers");
    	spapers.setCellValueFactory(new PropertyValueFactory<>("spapers"));
    	spapers.setMinWidth(50);
    	
    	tabledata.getColumns().clear();
    	tabledata.getColumns().addAll(new ArrayList<>(Arrays.asList(name,spapers)));
    	tabledata.setItems(FilterAll());
    }

    ObservableList<customerpanelbean> FilterAll()
    {
    	ObservableList<customerpanelbean> ary=FXCollections.observableArrayList();
    	try
    	{
    		pst=con.prepareStatement("select * from current where area=?");
    		pst.setString(1, comboarea.getSelectionModel().getSelectedItem());
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			String name=table.getString("cname");
    			String spapers=table.getString("spapers");
    			
    			customerpanelbean ref=new customerpanelbean(name,spapers);
    			ary.add(ref);
    		}
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    	return ary;

    }
    void doFillCombo()
    {
        	try
        	{
        		pst=con.prepareStatement("select distinct area from current");
        		ResultSet table=pst.executeQuery();
        		while(table.next())
        		{
        			String area=table.getString("area");
        			//System.out.println(area);
        			comboarea.getItems().add(area);
        		}
        	}
        	catch(Exception exp)
        	{
        		System.out.println(exp);
        	}
        }
        
        void doFillPaper()
        {
        	try
        	{
        		pst=con.prepareStatement("select distinct paper from data1");
        		ResultSet table=pst.executeQuery();
        		while(table.next())
        		{
        			String paper=table.getString("paper");
        		    //System.out.println(paper);
        			combopaper.getItems().add(paper);
        		}
        	}
        	catch(Exception exp)
        	{
        		System.out.println(exp);
        	}
        }

    @FXML
    void initialize() {
    	con=mysqlconnector.doConnect();
    	if(con==null)
    		System.out.println("connection problem");
    	else
    		System.out.println("connectedddd");
    	doFillCombo();
    	doFillPaper();

    }

}
