package com.gba.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class GroceryDb {
	static Connection con;
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/grocery_billing_application";
    static String uname = "root";
    static String pass = "root";
  
    
    public static Connection getConnection() throws Exception{
        if(con == null){
            Class.forName(driver);
            con = DriverManager.getConnection(url,uname, pass);
        }
        return con;
    }
}
