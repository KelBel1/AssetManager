package edu.seminolestate.term;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class AssetDBApplication extends JFrame {
	private JTextArea textArea;
    private JButton buttonAdd = new JButton("Add Asset");
    private JButton buttonShowAll = new JButton("Show All Assets");
    private JButton buttonEdit = new JButton("Edit an Asset");
    private JButton buttonDelete = new JButton("Delete an Asset");
  
   
    
    /**
     * Define the dimensions of the GUI window
     */
    public AssetDBApplication() {
        super("Asset Viewer");
         
        textArea = new JTextArea(50, 10);
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new Deida_CustomOutputStream(textArea));
        
        System.setOut(printStream);
        System.setErr(printStream);
 
        // create the GUI
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        
        add(buttonAdd, constraints);
        constraints.gridx = 1;
        add(buttonShowAll, constraints);
        constraints.gridx = 2;
        add(buttonEdit, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(buttonDelete, constraints);
       
         
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
         
        add(new JScrollPane(textArea), constraints);
        
        buttonAdd.addActionListener(new ActionListener() {
            /**
             *Listens for input to the PRINT button. Text Analyzer runs when pressed.
             *
             */
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                	textArea.setText("");
                	add();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
        
        buttonShowAll.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent evt) {
                try {
                	textArea.setText("");
                	showAll();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
        
        buttonEdit.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent evt) {
                try {
                	textArea.setText("");
                	edit();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
        
        buttonDelete.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent evt) {
                try {
                	textArea.setText("");
                	delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 320);
        setLocationRelativeTo(null);
    }
    
    private void add() throws IOException {
    	//Initialize variables
    	String response;
    	PreparedStatement insertAsset;
    	
    	String dateAssigned;
    	String purchaseDate;
    	String brand;
    	String model;
    	String series;
    	String serviceTag;
    	String serialNum;
    	String cost;
    	
    	//Input dialog boxes to save responses to variables
    	response = JOptionPane.showInputDialog("Type a Date Assigned");
    	dateAssigned = response;
    	response = JOptionPane.showInputDialog("Type a Purchase Date");
    	purchaseDate = response;
    	response = JOptionPane.showInputDialog("Type a Brand");
    	brand = response;
    	response = JOptionPane.showInputDialog("Type a Model");
    	model = response;
    	response = JOptionPane.showInputDialog("Type a Series");
    	series = response;
    	response = JOptionPane.showInputDialog("Type a Service Tag");
    	serviceTag = response;
    	response = JOptionPane.showInputDialog("Type a Serial Number");
    	serialNum = response;
    	response = JOptionPane.showInputDialog("Type a Cost");
    	cost = response;
    	
    	
    	//create SQL statement for INSERT
    	String sqlStatement = "INSERT INTO Assets (Brand, Model, Series, ServiceTag, SerialNum,"
    						+ "PurchaseDate, DateAssigned, Cost) " 
    						+ "VALUE (" + brand + "," + model + "," + series + "," +
    						serviceTag + "," + serialNum + "," + purchaseDate + "," + 
    						dateAssigned + "," + cost +")"; // create SQL query with response data
    	
    	
    	 try{
    		Connection conn = getConnection(); // open DB connection
    		
    		insertAsset = conn.prepareStatement(sqlStatement); // set PreparedStatement value
    		
    		
    		insertAsset.executeUpdate(); // execute PreparedStatement
    		conn.commit(); // commit changes to DB
    		conn.close(); // close DB connection 
    	}catch(Exception e){
    		e.printStackTrace();
    		textArea.setText("Could not add Asset.");
    	}finally{
    		textArea.setText("Added Asset."); 	// Confirmation message to textArea on success/error
    	}
    	
    	
    	
    	
    
    	
    }
    
    private void showAll() throws IOException {
    	//The logic to show all assets in the database goes here
    	String displayText = "";
    	try{
    		Connection conn = getConnection(); // open db connection
    		
    		//select statement for all assets in a prepared statement
    		PreparedStatement statement = conn.prepareStatement("SELECT * FROM assets"); 
    		
    		ResultSet result = statement.executeQuery(); //execute SELECT statement
    		
    		ArrayList<String> array = new ArrayList<String>(); //array to store individual records
    		while(result.next()){
    			
    			//recursively show all in textArea
    			displayText = result.getString("AssetID") + " " + result.getString("brand") + 
    					result.getString("Model") + " " + result.getString("Series") + 
    					result.getString("ServiceTag") + " " + result.getString("SerialNum") + 
    					result.getString("PurchaseDate") + " " + result.getString("DateAssigned") + 
    					result.getString("Cost");
    			array.add(displayText);
    		}
    		//Brand, Model, Series, ServiceTag, SerialNum," + "PurchaseDate, DateAssigned, Cost
    		textArea.setText(array.toString());
    		conn.close(); //close db connection
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	
    	
    	
    }
    
    private void edit() throws IOException {
    	//The logic for viewing one asset in the database goes here
    	
    	//display input dialog
    	//display scrollframe 
    	//Show all assets in scrollframe
    	//select asset by service tag/serial id in input dialog
    	//change employee/ location prompt for both? or prompt for all 
    	//create preparedStatement
    	//commit changes
    	//close db connection
    	
    	
    }

    private void delete()throws Exception {
		//The logic to delete an asset in the database goes here
    	String response;
    	String assetID;
    	//select by AssetID 
    	
    	response = JOptionPane.showInputDialog("Enter asset ID of asset to be deleted.");
    	assetID = response;
    	try{
    		Connection conn = getConnection(); //open DB connection
    		//prepare DELETE statement
    		PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM assets WHERE AssetID='"+ assetID + "';");
    		
    		if(assetID == null){
    			conn.close(); //close DB connection if assetID response is null
    			textArea.setText("No assetID was entered.");
    		}else{
    			deleteStatement.executeUpdate(); //execute PreparedStatement
        		conn.commit(); //commit changes to DB
    		}
    		
    		conn.close(); //close DB connection 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
	public static Connection getConnection() throws Exception {
		
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/AssetDB";
			String username = "root";
			String password = "root";
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("==CONNECTION SUCCESS==");
			return con;
		}
		
		catch(Exception e) {
			System.out.println("Something went wrong.");
		}
		
		return null;
	}
    
	/**
	 * The Main method which opens the GUI in a new window.
	 * @param args s
	 * @throws IOException s
	 */
	public static void main(String[] args) throws IOException {
		
		//TODO run SQL scripts to insert mock data into tables for user
		
		SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new AssetDBApplication().setVisible(true);
            }
        });
	}
}
