package com.gba.view;
import java.sql.*;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.gba.controller.GroceryDAOImp;
import com.gba.db.GroceryDb;
import com.gba.model.Grocery;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JScrollPane;
import java.text.SimpleDateFormat;  
import java.util.Date;

public class GroceryFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtgname;
	private JTextField txtprice;
	private JTextField txtqty;
	private JTable table;
	private JTextField tot_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroceryFrame frame = new GroceryFrame();
					frame.setVisible(true);
					frame.setTitle("Grocery Billing Application");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    public void Load()
    {
        GroceryDAOImp dao = new GroceryDAOImp();
        List<Grocery> list = dao.list();
        DefaultTableModel DFT = (DefaultTableModel) table.getModel();
        DFT.setRowCount(0);
        for(Grocery gy: list)
        {
        	int s_no = gy.getS_no();
        	String g_name = gy.getG_name();
        	int g_price = gy.getG_price();
        	int g_quantity = gy.getG_quantity();
        	int g_tot = gy.getG_tot();
        	
            DFT.addRow(new Object[]{s_no,g_name,g_price,g_quantity,g_tot});
        }     
  
    }
	/**
	 * Create the frame.
	 */
	public GroceryFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(0, 10, 846, 81);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Grocery Billing Application\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(281, 20, 303, 40);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(30, 158, 389, 267);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(40, 28, 136, 29);
		panel_1.add(lblNewLabel_1);
		
		txtgname = new JTextField();
		txtgname.setBounds(111, 34, 185, 22);
		panel_1.add(txtgname);
		txtgname.setColumns(10);
		
		JLabel sone = new JLabel("Price");
		sone.setFont(new Font("Tahoma", Font.BOLD, 16));
		sone.setBounds(40, 94, 136, 29);
		panel_1.add(sone);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(111, 100, 185, 22);
		panel_1.add(txtprice);
		
		JLabel lblNewLabel_1_2 = new JLabel("Quantity");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(26, 169, 136, 29);
		panel_1.add(lblNewLabel_1_2);
		
		txtqty = new JTextField();
		txtqty.setColumns(10);
		txtqty.setBounds(111, 175, 185, 22);
		panel_1.add(txtqty);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setBounds(142, 220, 96, 37);
		panel_1.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		        Grocery gy = new Grocery();
		        String g_name = txtgname.getText();
		        int g_price = Integer.parseInt(txtprice.getText());
		        int g_quantity = Integer.parseInt(txtqty.getText());
		        int g_tot = g_quantity*g_price;
		        
		        gy.setG_name(g_name);
		        gy.setG_price(g_price);
		        gy.setG_quantity(g_quantity);
		        gy.setG_tot(g_tot);
		        
		        GroceryDAOImp dao = new GroceryDAOImp();
		        dao.save(gy);
		        Load();
		        txtgname.setText("");
		        txtprice.setText("");
		        txtqty.setText(""); 
		        txtgname.requestFocus();
			}
		});
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1.setBounds(447, 158, 389, 267);
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 369, 247);
		panel_1_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SI", "NAME", "PRICE", "QUANTITY", "TOT.PRICE"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Object.class, Integer.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JButton txttot = new JButton("TOTAL");
		txttot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroceryDAOImp dao = new GroceryDAOImp();
				tot_txt.setText(dao.total());
				
			}
			}
		);
		txttot.setBounds(492, 435, 87, 36);
		contentPane.add(txttot);
		txttot.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton txtclr_1 = new JButton("CLEAR");
		txtclr_1.setBounds(734, 112, 102, 36);
		contentPane.add(txtclr_1);
		txtclr_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroceryDAOImp dao = new GroceryDAOImp();
				dao.clear();
				Load();
				tot_txt.setText("");
			}
		});
		txtclr_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		tot_txt = new JTextField();
		tot_txt.setFont(new Font("Tahoma", Font.BOLD, 16));
		tot_txt.setBounds(621, 439, 135, 32);
		contentPane.add(tot_txt);
		tot_txt.setColumns(10);
		
		JButton txtprnt = new JButton("PRINT");
		txtprnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroceryDAOImp dao = new GroceryDAOImp();
			    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			    Date date = new Date();
			    
				String path = "E:\\";
				com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
				try{
					PdfWriter.getInstance(doc, new FileOutputStream(path+""+"Grocery Biller"+".pdf"));
					doc.open();
					Paragraph paragraph1 = new Paragraph("									Grocery Billing Apllication\n\n");
					doc.add(paragraph1);
					Paragraph paragraphn = new Paragraph("									Date: "+formatter.format(date)+"\n\n");
					doc.add(paragraphn);
					PdfPTable tb1 = new PdfPTable(5);
					tb1.addCell("id");
					tb1.addCell("Name");
					tb1.addCell("Price");
					tb1.addCell("Quantity");
					tb1.addCell("Subtotal");
					for(int i=0;i<table.getRowCount();i++) {
						String n = table.getValueAt(i, 0).toString();
						String p = table.getValueAt(i, 1).toString();
						String r = table.getValueAt(i, 2).toString();
						String s = table.getValueAt(i, 3).toString();
						String d = table.getValueAt(i, 4).toString();
						tb1.addCell(n);
						tb1.addCell(p);
						tb1.addCell(r);
						tb1.addCell(s);
						tb1.addCell(d);
					}
					doc.add(tb1);
					Paragraph paragraph2 = new Paragraph("\nTotal Amount: Rs."+dao.total()+"\nThank You For Visiting Come Again!!!"+"\n\n\nA Project By"+"\nArulkarthick T"+"\nKailaash V"+"\nStephen A");
					doc.add(paragraph2);
					JOptionPane.showMessageDialog(null, "Printed");
					
					
					
				}
				catch(Exception e1) {
					e1.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Error");
				}
				doc.close();
				
				
			}
		});
		txtprnt.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtprnt.setBounds(596, 112, 102, 36);
		contentPane.add(txtprnt);
	}
}
