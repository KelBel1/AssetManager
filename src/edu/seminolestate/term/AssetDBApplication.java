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
    private JButton buttonView = new JButton("View an Asset");
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
        add(buttonView, constraints);
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
        
        buttonView.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent evt) {
                try {
                	textArea.setText("");
                	view();
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
    	String response;
    	String dateAssigned;
    	String purshaseDate;
    	String brand;
    	String model;
    	String series;
    	String serviceTag;
    	String serialNum;
    	String assetType;
    	String Cost;
    	response = JOptionPane.showInputDialog("Type a response");
    	textArea.setText(response); // testing data save
    	
    	// Set variable fields with showInputDialog responses
    	
    	// Create Prepared Statement
    	
    	// getConnection()
    	
    	// Send SQL INSERT statement
    	
    	// Confirmation message to textArea on success/error
    	
    }
    
    private void showAll() throws IOException {
    	//The logic to show all assets in the database goes here
    }
    
    private void view() throws IOException {
    	//The logic for viewing one asset in the database goes here
    }

    private void delete()throws Exception {
		//The logic to delete an asset in the database goes here
    }
    
	public static Connection getConnection() throws Exception {
		
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/AssetDB";
			String username = "root";
			String password = "password";
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
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AssetDBApplication().setVisible(true);
            }
        });
	}
}
