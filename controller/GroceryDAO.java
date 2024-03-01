package com.gba.controller;

import java.util.List;

import com.gba.model.Grocery;

public interface GroceryDAO {
	
	public void save(Grocery grocerys);
    public void clear();
    public String total();
    public List<Grocery> list();
	
}
