package papermaster;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;





public class papermasterViewcontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combopaper;

    @FXML
    private ImageView imgpic;

    @FXML
    private Label lblpaper;

    @FXML
    private Label lblpapermaster;

    @FXML
    private Label lblprice;

    @FXML
    private Label lblrecord;

    @FXML
    private TextField textprice;
    PreparedStatement pst;
    Connection con1;

    @FXML
    void doavail(ActionEvent event) {

    	String paper= combopaper.getSelectionModel().getSelectedItem();

        	float price= Float.parseFloat(textprice.getText());
        			
        	try {
    				pst=con1.prepareStatement("insert into data1 values (?,?)");
    			
    				pst.setString(1,paper);
    				pst.setFloat(2, price);
    				pst.execute();
    				lblrecord.setText("Record Saved........");
    				
			} 
    				
    				
    		catch (SQLException e) 
        		{
    			  e.printStackTrace();
    			}
}
        		
        


    

    @FXML
    void doedit(ActionEvent event) {
    	
    	String paper=combopaper.getSelectionModel().getSelectedItem();
    	float price=Float.parseFloat(textprice.getText());
    	try {
    	pst=con1.prepareStatement("update data1 set price=? where paper=?");
    	
    	
    	pst.setFloat(1,price );
    	pst.setString(2, paper);
    	pst.executeUpdate();
    	lblrecord.setText(" Records Updated........");
		
    	}
    	catch (SQLException e) 
		{
		  e.printStackTrace();
		}
    }
    

    
        @FXML
    void dosearch(ActionEvent event) {
        	try
        	{
    
    
		pst=con1.prepareStatement("select * from data1 where paper=?");
		String paper= combopaper.getSelectionModel().getSelectedItem();
		pst.setString(1, paper);
		
		
	ResultSet table=pst.executeQuery(); 
	while(table.next())
	{
		
		float pprice=table.getFloat("price");
	
		textprice.setText(String.valueOf(pprice));
	}	
        	}
	
	catch(Exception exp)
	{
		System.out.println(exp);
	}
}



    

    @FXML
    void dowithdraw(ActionEvent event) {
    	try{
        	String  paper= combopaper.getSelectionModel().getSelectedItem();
        	pst=con1.prepareStatement("delete from data1 where paper=?");
        	
        	pst.setString(1, paper);
    		int count=pst.executeUpdate();
    	if(count!=0)
    		lblrecord.setText(count+ " Records Deleted");
    	else
    		lblrecord.setText("Invalid ID");
    	}
    	catch(Exception exp)
    	{
    		System.out.println(exp.toString());
    	}


}
    void doFillCombo()
    {
    	try{
			pst=con1.prepareStatement("select distinct paper from data1");
		ResultSet table=pst.executeQuery(); 
		while(table.next())
		{
			String paper=table.getString("paper");
			System.out.println(paper);
			combopaper.getItems().add(String.valueOf(paper));
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
    		
    	
        assert combopaper != null : "fx:id=\"combopaper\" was not injected: check your FXML file 'papermasterView.fxml'.";
        assert imgpic != null : "fx:id=\"imgpic\" was not injected: check your FXML file 'papermasterView.fxml'.";
        assert lblpaper != null : "fx:id=\"lblpaper\" was not injected: check your FXML file 'papermasterView.fxml'.";
        assert lblpapermaster != null : "fx:id=\"lblpapermaster\" was not injected: check your FXML file 'papermasterView.fxml'.";
        assert lblprice != null : "fx:id=\"lblprice\" was not injected: check your FXML file 'papermasterView.fxml'.";
        assert textprice != null : "fx:id=\"textprice\" was not injected: check your FXML file 'papermasterView.fxml'.";

    }

}
