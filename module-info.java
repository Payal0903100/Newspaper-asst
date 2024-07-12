import java.sql.SQLException;

import papermaster.mysqlconnector;

module NewsPaperJavaProject {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens papermaster to javafx.graphics, javafx.fxml;
	opens hawkermanager to javafx.graphics, javafx.fxml;
	opens customermanager to javafx.graphics, javafx.fxml;
	opens billgenerator to javafx.graphics, javafx.fxml;
	opens showallhawkers to javafx.graphics, javafx.fxml,javafx.base;
	opens customerpanel to javafx.graphics, javafx.fxml,javafx.base;
	opens billcollector to javafx.graphics, javafx.fxml;
	opens billboard to javafx.graphics, javafx.fxml,javafx.base;
    

	opens areasdetail to javafx.graphics, javafx.fxml,javafx.base;
	opens Admindesk to javafx.graphics, javafx.fxml;
	opens adminlogin to javafx.graphics, javafx.fxml;

	

	}
