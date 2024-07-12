package billgenerator;

import java.sql.Connection;

import java.sql.DriverManager;

public class mysqlconnector
{
	static Connection con1;

	public static Connection doConnect()
	{
	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con1=DriverManager.getConnection("jdbc:mysql://localhost/newspaperagency","root","payalp09");
			System.out.println("Connected....");
		}
		catch(Exception exp)
		{
			System.out.println(exp);
		}
		return con1;
	}
}
	