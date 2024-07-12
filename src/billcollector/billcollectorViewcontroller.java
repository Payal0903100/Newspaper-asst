package billcollector;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import papermaster.mysqlconnector;

public class billcollectorViewcontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dateend;
    @FXML
    private ToggleGroup togbill;


    @FXML
    private DatePicker datestart;

    @FXML
    private Label lblamount;

    @FXML
    private Label lblend;

    @FXML
    private Label lblinfo;

    @FXML
    private Label lblno;

    @FXML
    private Label lbtstart;

    @FXML
    private TextField txtamt;

    @FXML
    private TextField txtno;
    PreparedStatement pst;
    Connection con;
    @FXML
    void dobilldetails(ActionEvent event)  throws SQLException {
    	pst=con.prepareStatement("select *from bills where mobile=?");
    	pst.setString(1,txtno.getText());
    	ResultSet table=pst.executeQuery();
    	while(table.next())
    	{
    		String bill=table.getString("bill");
    		txtamt.setText(bill);
    		java.sql.Date dt=table.getDate("datefrom");
    		LocalDate ld=dt.toLocalDate();
    		datestart.setValue(ld);
    		java.sql.Date dt1=table.getDate("dateto");
    		LocalDate ld1=dt1.toLocalDate();
    		dateend.setValue(ld1);
    		
    	}

   

    }


    @FXML
    void dodone(ActionEvent event) {
    	   {
    	    	lblinfo.setText("Payment Done");
    	    	try
    	    	{
    	    		pst=con.prepareStatement("update bills set billstatus=? where mobile=?");
    	    		pst.setInt(1, 1);
    	    		pst.setString(2, txtno.getText());
    	    		pst.executeUpdate();
    	    	}
    	    	catch(SQLException e)
    	    	{
    	    		e.printStackTrace();
    	    	}
    	    }

    }

    @FXML
    void initialize() {
    	con=mysqlconnector.doConnect();
	if(con==null)
		System.out.println("connection problem");
	else
		System.out.println("connectedddd");
	
    
    

    

    }

}

