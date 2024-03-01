package com.gba.model;

public class Grocery {
	private int s_no;
	private String g_name;
	private int g_price;
	private int	g_quantity;
	private int g_tot;
	private String g_sum;
	
	public int getS_no() {
		return s_no;
	}
	public String getG_sum() {
		return g_sum;
	}
	public void setG_sum(String g_sum) {
		this.g_sum = g_sum;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public int getG_price() {
		return g_price;
	}
	public void setG_price(int g_price) {
		this.g_price = g_price;
	}
	public int getG_quantity() {
		return g_quantity;
	}
	public void setG_quantity(int g_quantity) {
		this.g_quantity = g_quantity;
	}
	public int getG_tot() {
		return g_tot;
	}
	public void setG_tot(int g_tot) {
		this.g_tot = g_tot;
	}
	
	
}
