package com.gba.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.gba.db.GroceryDb;
import com.gba.model.Grocery;
public class GroceryDAOImp implements GroceryDAO {

	@Override
	public void save(Grocery grocerys) {
		// TODO Auto-generated method stub

        try {
           Connection con =  GroceryDb.getConnection();
           String sql = "INSERT INTO grocery_details(g_name,g_price,g_quantity,g_tot) VALUES (?,?,?,?)";
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1, grocerys.getG_name());
           ps.setInt(2, grocerys.getG_price());
           ps.setInt(3, grocerys.getG_quantity());
           ps.setInt(4, grocerys.getG_tot());
           ps.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Error");
       }
		
	}




	@Override
	public List<Grocery> list() {
		// TODO Auto-generated method stub
        List<Grocery> list = new ArrayList<Grocery>();
      try {
          Connection con = GroceryDb.getConnection();
          String sql = "SELECT * FROM grocery_details ";
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          
          
          
          while(rs.next()){
              Grocery gy = new Grocery();
              gy.setS_no(rs.getInt("s_no"));
              gy.setG_name(rs.getString("g_name"));
              gy.setG_price(rs.getInt("g_price"));
              gy.setG_quantity(rs.getInt("g_quantity"));
              gy.setG_tot(rs.getInt("g_tot"));

              list.add(gy);
          }
          
      } catch (Exception e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(null, "Error");
      }
      return list;
	}




	@Override
	public void clear() {
		// TODO Auto-generated method stub
		try {
	           Connection con =  GroceryDb.getConnection();
	           String sql = "DELETE FROM grocery_details";
	           PreparedStatement ps = con.prepareStatement(sql);
	           ps.executeUpdate();
	           JOptionPane.showMessageDialog(null, "Table Cleared");
	       }
		catch (Exception e) {
	           e.printStackTrace();
	           JOptionPane.showMessageDialog(null, "Error");
	       }
		
	}




	@Override
	public String total() {
		// TODO Auto-generated method stub
		Grocery gy = new Grocery();
		try {
	        Connection con =  GroceryDb.getConnection();
	        String sql = "SELECT SUM(g_tot) FROM grocery_details;";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rsl = ps.executeQuery();
	        if(rsl.next()) {
	     	   String sum = rsl.getString("SUM(g_tot)");
	     	  gy.setG_sum(sum);
	     	        
	        }
	    }
		catch (Exception e1) {
	        e1.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error");
	    }
		return gy.getG_sum();
	}

}
