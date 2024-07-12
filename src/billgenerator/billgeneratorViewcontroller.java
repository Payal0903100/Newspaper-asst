package billgenerator;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import java.time.LocalDate;
import java.time.Period;

import javafx.scene.control.TextField;
import papermaster.mysqlconnector;

public class billgeneratorViewcontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combomob;

    @FXML
    private Label lblbill;

    @FXML
    private Label lbllast;

    @FXML
    private Label lblmissdays;

    @FXML
    private Label lblmob;

    @FXML
    private Label lblpapers;

    @FXML
    private Label lblprices;

    @FXML
    private Label lblupto;

    @FXML
    private TextField txtbill;

    @FXML
    private TextField txtinfo;

    @FXML
    private TextField txtmissdays;

    @FXML
    private TextField txtpapers;
    @FXML
    private TextField txtprices;
    @FXML
    private TextField texttotal;
    @FXML
    private DatePicker datestart;

    @FXML
    private DatePicker dateupto;




    
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
    Connection con1;
   
    float sum=0;


    @FXML
    void dogenerateandsavebill(ActionEvent event) 
    {
    	
    	LocalDate ld=datestart.getValue();
        java.sql.Date dt=java.sql.Date.valueOf(ld);
        LocalDate ld1=dateupto.getValue();
        java.sql.Date dt1=java.sql.Date.valueOf(ld1);
        Period p=Period.between(ld, ld1);
        int diff=p.getDays();
        int missing=Integer.valueOf(txtmissdays.getText());
        float bill=sum*diff-(missing*sum);
        txtbill.setText(String.valueOf(bill));
        
        try {
        	pst=con1.prepareStatement("insert into bills values(?,?,?,?,?)");
        	pst.setString(1,combomob.getSelectionModel().getSelectedItem());
        	pst.setDate(2,dt);
        	pst.setDate(3,dt1);
        	pst.setFloat(4, bill);
        	pst.setInt(5, 0);
        	pst.executeUpdate();
        	txtinfo.setText("subscribed....");
        	
        }
    	catch (SQLException e) 
    	{
    	  e.printStackTrace();
    	}
        }
  @FXML
    void dofetch(ActionEvent event)
    {
	  
	   LocalDate ld=null;
 try{
    	String mobile=combomob.getSelectionModel().getSelectedItem();
		pst=con1.prepareStatement("select * from current where mobile=?");
		pst2=con1.prepareStatement("select max(dateto) from bills where mobile=?");
		pst.setString(1, mobile);
	
		ResultSet table=pst.executeQuery(); 
		while(table.next())
		{
			int st=table.getInt("status");
			String spapers=table.getString("spapers");
        String sprices=table.getString("sprices");
		txtpapers.setText(spapers);
			txtprices.setText(sprices);
		if(st==0)
		{
	java.sql.Date dt=table.getDate("dos");
		ld=dt.toLocalDate();
		 pst1=con1.prepareStatement("update current set status=1 where mobile=?");
		 pst1.setString(1, mobile);
		 pst1.executeUpdate();
		}
		else
		{
			pst2.setString(1, mobile);
			ResultSet table1=pst2.executeQuery();
			while(table1.next())
			{
				java.sql.Date dt=table1.getDate("max(dateto)");
				  ld=dt.toLocalDate();
			}
		}
			datestart.setValue(ld);
		String aryp[]=sprices.split(",");
		for(String s:aryp)
		{
			sum+=Float.valueOf(s);
		}
		texttotal.setText(String.valueOf(sum));
		}

    
 }
 
    catch(Exception exp)
	{
		System.out.println(exp);
	}
	
}
    
    void doFillCombo()
    {
    	try{
			pst=con1.prepareStatement("select distinct mobile from current");
		ResultSet table=pst.executeQuery(); 
		while(table.next())
		{
			String mobile=table.getString("mobile");
			System.out.println(mobile);
			combomob.getItems().add(mobile);
		}
	
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    }

    @FXML
    void initialize() {
    	con1=mysqlconnector.doConnect();
    	if(con1==null)
    		System.out.println("connection problem");
    	else
    		System.out.println("connectedddd");
    	doFillCombo();
        
        

}
    
}
