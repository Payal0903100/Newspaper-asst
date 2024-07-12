package customermanager;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import papermaster.mysqlconnector;

public class customermanagerViewcontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private Label lbladdress;

    @FXML
    private Label lblarea;

    @FXML
    private Label lbldate;

    @FXML
    private Label lblemail;

    @FXML
    private Label lblhawker;

    @FXML
    private Label lblmobile;

    @FXML
    private Label lblname;

    @FXML
    private Label lblpaper;

    @FXML
    private Laxbel lblselpaper;

    @FXML
    private Label lbltitle;

    @FXML
    private ListView<String> list1;

    @FXML
    private ListView<String> list2;

    @FXML
    private ListView<String> list3;

    @FXML
    private ListView<String> list4;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtdate;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txthawker;

    @FXML
    private TextField txtmobile;
    @FXML
    private Label lblinfo;
    @FXML
    private DatePicker datedos;


    @FXML
    private TextField txtname;
    PreparedStatement pst;
    Connection con1;

    @FXML
    void doput(MouseEvent event) {
    String selecteditem=list1.getSelectionModel().getSelectedItem();
    	int i=list1.getSelectionModel().getSelectedIndex();
    	list2.getSelectionModel().clearAndSelect(i);  
    String selectedrate=(String) list2.getSelectionModel().getSelectedItem();
    list3.getItems().add(selecteditem);
    list4.getItems().add(selectedrate);
    
    }    @FXML
    void docombo(ActionEvent event) throws SQLException {
    	String allo=comboarea.getSelectionModel().getSelectedItem();
    	pst=con1.prepareStatement("select hname from hawkers where alloareas like ?");
    	pst.setString(1,"%" +allo+"%");
    	ResultSet rs=pst.executeQuery();
    	while (rs.next())
    	{
    		String name=rs.getString("hname");
    		txthawker.setText(name);
    		System.out.println("hname:"+name);
    	}
    	
    	
    	

    }
    
    @FXML
    void dofetch(ActionEvent event) throws SQLException {	
    	
    		String mobile=txtmobile.getText();
    	
    
		pst=con1.prepareStatement("select * from current where mobile=?");
		
		pst.setString(1,mobile);
		
		
	ResultSet table=pst.executeQuery(); //array of objects
	while(table.next())
	{

		String cname=table.getString("cname");
		

		String spapers=table.getString("spapers");
		String sprices=table.getString("sprices");
		

		String area=table.getString("area");

		String hawker= table.getString("hawker");
		String email=table.getString("email");
		String address=table.getString("address");
		java.sql.Date dt = table.getDate("dos");
		LocalDate ld = dt.toLocalDate();
		datedos.setValue(ld);
	    
	    		
		txtname.setText(cname);
		txthawker.setText(hawker);
		txtemail.setText(email);
		txtaddress.setText(address);
//		System.out.println(area);
		comboarea.getSelectionModel().select(area);
		String ary[]=spapers.split(",");
		for (String s:ary)
		{
			System.out.println(s);
			list3.getItems().add(s);
		}
		String aryprice[]=sprices.split(",");
		for (String s:aryprice)
		{
			list4.getItems().add(s);
		}
	}

	}
    
	
	
    
    


    @FXML
    void dosubscribe(ActionEvent event) {
    
	
	LocalDate ld=datedos.getValue();
    java.sql.Date dt=java.sql.Date.valueOf(ld);
  ObservableList<String> aryselpaper=list3.getItems();
 
 String selpapers="";
 for(String string:aryselpaper)
 {
	 selpapers+=string+",";
 }
	 
	 
	 
 ObservableList<String> aryselprice=list4.getItems();
 String selprices="";
 for(String string:aryselprice)
 {
	 selprices+=string+",";
 }
 if(selpapers.endsWith(",")) {
	 selpapers= selpapers.substring(0,selpapers.length()-1);
 } 
	 if(selprices.endsWith(",")) {
		 selprices= selprices.substring(0,selprices.length()-1);
	 }
 
    try {
    	pst=con1.prepareStatement("insert into current values(?,?,?,?,?,?,?,?,?)");
    	pst.setString(1,txtmobile.getText());
    	pst.setString(2, txtname.getText());
    	pst.setString(3, selpapers);
    	pst.setString(4, selprices);
    	pst.setString(5, comboarea.getSelectionModel().getSelectedItem());
    	pst.setString(6, txthawker.getText());
    	pst.setString(7,txtemail.getText());
    	pst.setString(8, txtaddress.getText());
    	pst.setDate(9, dt);
    	pst.executeUpdate();
    	lblinfo.setText("subscribed....");
    	
    }
	catch (SQLException e) 
	{
	  e.printStackTrace();
	}
    }
    


    


    @FXML
    void dounsubscribe(ActionEvent event) throws SQLException {
    	pst=con1.prepareStatement("delete from current where mobile=?");
    	pst.setString(1,txtmobile.getText());
    	pst.executeUpdate();
    	lblinfo.setText("unsubscribed......");
    }

    @FXML
    void doupdate(ActionEvent event) {
    	LocalDate ld=datedos.getValue();
    	java.sql.Date dt=java.sql.Date.valueOf(ld);
    	ObservableList<String> aryselpaper=list3.getItems();
    
    	 
    	 String selpapers="";
    	 for(String string:aryselpaper)
    	 {
    		 selpapers+=string+",";
    	 }
    		 if(selpapers.endsWith(",")) {
    			 selpapers= selpapers.substring(0,selpapers.length()-1);
    		 }
    		 
    	 
    ObservableList<String> aryselprice=list4.getItems();
    	 
    	 String selprices="";
    	 for(String string:aryselprice)
    	 {
    		 selprices+=string+",";
    	 }
    		 
    		 if(selprices.endsWith(",")) {
    			 selprices= selprices.substring(0,selprices.length()-1);
    		 }
    	 


    	
    	


	
	try {
			pst=con1.prepareStatement("update current set cname=?,spapers=?,sprices=?,area=?,hawker=?,email=?,address=?,dos=? where mobile=?");
			
			pst.setString(1, txtname.getText());
	    
	    	pst.setString(2, selpapers);
	    	pst.setString(3, selprices);
	    	pst.setString(4, comboarea.getSelectionModel().getSelectedItem());
	    	pst.setString(5, txthawker.getText());
	    	pst.setString(6,txtemail.getText());
	    	pst.setString(7, txtaddress.getText());
	    	pst.setDate(8, dt);
	    	pst.setString(9, txtmobile.getText());
	    	pst.executeUpdate();
	    	lblinfo.setText("updated....");
	    	
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
    	pst=con1.prepareStatement("select *from data1");
    	ResultSet table=pst.executeQuery();
    	while(table.next())
    	{
    		String p=table.getString("paper");
    		list1.getItems().add(p);
    		float pprice=table.getFloat("price");
    		list2.getItems().add(String.valueOf(pprice));
    	}
    		pst=con1.prepareStatement("select *from hawkers");
        	ResultSet table1=pst.executeQuery();
        	pst=con1.prepareStatement("select distinct Areas from areas");
    		ResultSet table3=pst.executeQuery();
    		while(table3.next())
    		{
    			String A=table3.getString("Areas");
    			comboarea.getItems().add(A);
    		}
        	
			
    
    	
    }
}

