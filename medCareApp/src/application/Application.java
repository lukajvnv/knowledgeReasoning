package application;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import application.view.MainFrame;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel");
			UIManager.put("Synthetica.tabbedPane.keepOpacity", true);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		MainFrame.getInstance().setVisible(true);
		
		try {
			getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	 public static Connection getConnection() throws Exception{
	  try{
//	   String driver = "com.mysql.jdbc.Driver";
	   String driver = "com.mysql.cj.jdbc.Driver";

	  /* String url = "jdbc:mysql://localhost:3306/medcare_db?useSSL=false&createDatabaseIfNotExist=true";
	   String username = "root";
	   String password = "kimi";*/
	   //Class.forName(driver);
	   
	   Properties p = new Properties();
	   p.load(new FileInputStream("properties.txt"));
	   String url = p.getProperty("url");
	   String username = p.getProperty("username");
	   String password = p.getProperty("password");
	   
	   Connection conn = DriverManager.getConnection(url,username,password);
	   System.out.println("Connected");
	   return conn;
	  } catch(Exception e) {
		  System.out.println(e);
	  }
	  
	  
	  return null;
	 }

}
