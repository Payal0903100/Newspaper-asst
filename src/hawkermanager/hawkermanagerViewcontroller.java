package hawkermanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import papermaster.mysqlconnector;

public class hawkermanagerViewcontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private ComboBox<String> comboname;

    @FXML
    private ImageView imgcard;

    @FXML
    private ImageView imgman;

    @FXML
    private Label lbladdress;

    @FXML
    private Label lblalloc;

    @FXML
    private Label lblarea;

    @FXML
    private Label lblmobile;

    @FXML
    private Label lblname;
    @FXML
    private Label lblinfo;


    @FXML
    private Label lbltitle;
    @FXML
    private Label lblpath;


    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtalloc;

    @FXML
    private TextField txtno;
    

    
    Connection con1;
    PreparedStatement pst;
    @FXML
    void dosearch(ActionEvent event) {
    	try{
			pst=con1.prepareStatement("select * from hawkers where hname=?");
			String hname= comboname.getSelectionModel().getSelectedItem();
			pst.setString(1, hname);
			
			
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{

			String mobile=table.getString("mobile");

			String address=table.getString("address");
			

			String alloareas=table.getString("alloareas");

			String picPath= table.getString("picpath");
			
			txtno.setText(mobile);
			txtaddress.setText(String.valueOf(address));
			txtalloc.setText(alloareas);
			lblpath.setText(picPath);
						
			
			imgcard.setImage(new Image(new FileInputStream(picPath)));
			
			
			
			
			
			
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
		
    }
    @FXML
    void doselarea(ActionEvent event) {
    	String s=comboarea.getSelectionModel().getSelectedItem();
    	String sarea=txtalloc.getText();
    	if(sarea.equals(""))
    		sarea=sarea+s;
    	else
    	sarea=sarea+","+s;
    	txtalloc.setText(sarea);
    	

    }


    @FXML
    void doenroll(ActionEvent event) {
    	  {
    	    	String hname=comboname.getSelectionModel().getSelectedItem();
    	    
    	    
    	    														
    	    	try {
    					pst=con1.prepareStatement("insert into hawkers values(?,?,?,?,?,CURRENT_DATE())");
    					pst.setString(1, hname);
    					pst.setString(2, txtno.getText());
    					pst.setString(3, txtaddress.getText());
    					pst.setString(4, txtalloc.getText());
    					pst.setString(5, lblpath.getText());
    					pst.execute();
    					lblinfo.setText("record enrolled...");
    					
    				} 
    	    	catch (SQLException e) 
    	    		{
    				  e.printStackTrace();
    				}
    	    		
    	    }

    }

    @FXML
    void dofire(ActionEvent event) {
    	try{
        	String hname= comboname.getSelectionModel().getSelectedItem();
        	pst=con1.prepareStatement("delete from hawkers where hname=?");
        	pst.setString(1, hname);
        		int count=pst.executeUpdate();
        	if(count!=0)
        		lblinfo.setText(count+ " Records Deleted");
        	else
        		lblinfo.setText("Invalid ID");
        	}
        	catch(Exception exp)
        	{
        		System.out.println(exp.toString());
        	}



    }

    @FXML
    void donew(ActionEvent event) {
    	comboname.setValue(null);
    	txtno.clear();
    	txtaddress.clear();
    	txtalloc.clear();
    	lblpath.setText("");
    	
    	

    }
   

    @FXML
    void doupdate(ActionEvent event) {
    	String hname= comboname.getSelectionModel().getSelectedItem();
    	
    		
    	try {
				pst=con1.prepareStatement("update hawkers set mobile=?,address=?,alloareas=?,picpath=? where hname=?");
				
				pst.setString(1, txtno.getText());
				pst.setString(2,txtaddress.getText());
				pst.setString(3,txtalloc.getText());
				pst.setString(4, lblpath.getText());
				pst.setString(5, hname);
				
				pst.executeUpdate();
				lblinfo.setText("record updated.....");
				
				
			} 
    	catch (SQLException e) 
    		{
			  e.printStackTrace();
			}

    }

    @FXML
    void doupload(ActionEvent event) throws FileNotFoundException {

    	FileChooser fileChooser = new FileChooser();
    	 fileChooser.setTitle("Open Resource File");
    	 fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif") );
    	 File selectedFile = fileChooser.showOpenDialog(null);
    	 
    	 if (selectedFile != null) {
    	    lblpath.setText(selectedFile.getPath());
    	    Image img=new Image(selectedFile.toURI().toString());
    	    System.out.println(selectedFile.toURI().toString());
    	    imgcard.setImage(new Image(new FileInputStream(selectedFile)));
    	    
    	 }
    	 else
    	 {
    		 lblpath.setText("nopic.jpg");
    	 }
    }
    void doFillCombo()
    {
    	try{
			pst=con1.prepareStatement("select distinct hname from hawkers");
		ResultSet table=pst.executeQuery(); 
		while(table.next())
		{
			String hname=table.getString("hname");
			System.out.println(hname);
			comboname.getItems().add(String.valueOf(hname));
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
    		
        assert comboarea != null : "fx:id=\"comboarea\" was not injected: check your FXML file 'hawkermanagerView.fxml'.";
        assert comboname != null : "fx:id=\"comboname\" was not injected: check your FXML file 'hawkermanagerView.fxml'.";
        assert imgcard != null : "fx:id=\"imgcard\" was not injected: check your FXML file 'hawkermanagerView.fxml'.";
        assert imgman != null : "fx:id=\"imgman\" was not injected: check your FXML file 'hawkermanagerView.fxml'.";
        assert lbladdress != null : "fx:id=\"lbladdress\" was not injected: check your FXML file 'hawkermanagerView.fxml'.";
        assert lblalloc != null : "fx:id=\"lblalloc\" was not injected: check your FXML file 'hawkermanagerView.fxml'.";
        assert lblarea != null : "fx:id=\"lblarea\" was not injected: check your FXML file 'hawkermanagerView.fxml'.";
        assert lblmobile != null : "fx:id=\"lblmobile\" was not injected: check your FXML file 'hawkermanagerView.fxml'.";
        assert lblname != null : "fx:id=\"lblname\" was not injected: check your FXML file 'hawkermanagerView.fxml'.";
        assert lbltitle != null : "fx:id=\"lbltitle\" was not injected: check your FXML file 'hawkermanagerView.fxml'.";
        assert txtaddress != null : "fx:id=\"txtaddress\" was not injected: check your FXML file 'hawkermanagerView.fxml'.";
        assert txtalloc != null : "fx:id=\"txtalloc\" was not injected: check your FXML file 'hawkermanagerView.fxml'.";
        assert txtno != null : "fx:id=\"txtno\" was not injected: check your FXML file 'hawkermanagerView.fxml'.";

    }

}
