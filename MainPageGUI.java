import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class MainPageGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable gameListTable;
	private JTextField userEntry;

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public MainPageGUI(String username) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// database url, username, and password
	    String url = "jdbc:mysql://localhost:3306/introsweproject";
	    String SQLusername = "root";
	    String SQLpassword = "";
	    
	    // establish connection to the sql server and tables
	    Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, SQLusername, SQLpassword);

        // initialize layout for the content pane for the jpanel
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// header for the main page, might need an update here to display actual username
		JLabel lblHeader = new JLabel("User's Backlog Status");
		lblHeader.setBounds(10, 11, 147, 14);
		contentPane.add(lblHeader);
		
		// text field for user to use when add/edit/delete entries
		userEntry = new JTextField();
		userEntry.setBounds(10, 114, 147, 20);
		contentPane.add(userEntry);
		userEntry.setColumns(10);
		
		// set up for printing to table the sql data
		JScrollPane currentScroll = new JScrollPane();
		currentScroll.setBounds(177, 111, 494, 297);
		contentPane.add(currentScroll);
		gameListTable = new JTable();
		currentScroll.setViewportView(gameListTable);
		
		// radio buttons for selecting which list to go into? can be deleted or changed to fit the process
		JRadioButton rdbtnCurrentColumn = new JRadioButton("Currently Playing");
		rdbtnCurrentColumn.setBounds(10, 141, 147, 23);
		contentPane.add(rdbtnCurrentColumn);
				
		JRadioButton rdbtnBacklog = new JRadioButton("Backlog");
		rdbtnBacklog.setBounds(10, 167, 147, 23);
		contentPane.add(rdbtnBacklog);
				
		JRadioButton rdbtnCompleted = new JRadioButton("Completed");
		rdbtnCompleted.setBounds(10, 193, 147, 23);
		contentPane.add(rdbtnCompleted);
				
		
		
		// the Add button to add entries to a list
		//checks if there's available cell with null value and edits that entry if possible, otherwise adds extra row
		JButton btnAddGame = new JButton("Add Game");
		btnAddGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					String query = "";
					
					//add value at next null cell so we dont get extra rows
					if (userEntry.getText() != null) {
					    if (rdbtnCurrentColumn.isSelected()) {
					        query = "UPDATE " + username + " SET currently_playing = '" + userEntry.getText() + "' WHERE currently_playing IS NULL LIMIT 1;";
					    } else if (rdbtnBacklog.isSelected()) {
					        query = "UPDATE " + username + " SET backlogged_games = '" + userEntry.getText() + "' WHERE backlogged_games IS NULL LIMIT 1;";
					    } else if (rdbtnCompleted.isSelected()) {
					        query = "UPDATE " + username + " SET completed_games = '" + userEntry.getText() + "' WHERE completed_games IS NULL LIMIT 1;";
					    }
					    
					    //if no null value available to then count number of cells updated
					    PreparedStatement pst = connection.prepareStatement(query);
					    //n cells updated
						int cellsUpdated = pst.executeUpdate();
						 
					    //if no cells were updated (i.e., no NULL values found), then add new row
					    if (cellsUpdated == 0) {
					        if (rdbtnCurrentColumn.isSelected()) {
					            query = "INSERT INTO " + username + " (currently_playing) VALUES ('" + userEntry.getText() + "');";
					        } else if (rdbtnBacklog.isSelected()) {
					            query = "INSERT INTO " + username + " (backlogged_games) VALUES ('" + userEntry.getText() + "');";
					        } else if (rdbtnCompleted.isSelected()) {
					            query = "INSERT INTO " + username + " (completed_games) VALUES ('" + userEntry.getText() + "');";
					        }
					        
					        //exectuion and dialog box
					        pst = connection.prepareStatement(query);
					        pst.execute();
					        JOptionPane.showMessageDialog(null, "Entry Added");
					        pst.close();
					    }
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnAddGame.setBounds(10, 223, 147, 23);
		contentPane.add(btnAddGame);
		
		// DELETE BUTTON WAS WAY HARDER TO DO THAN I THOUGHT, IT MOSTLY WORKS NOW
		// search then delete an entry via name in text field
		JButton btnDeleteEntry = new JButton("Delete Entry");
		btnDeleteEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = ";";
					
					if(userEntry.getText() != null && rdbtnCurrentColumn.isSelected()) {
						query ="update "+username+" set currently_playing = NULL where currently_playing = '"+userEntry.getText()+"';";
					}
					else if(userEntry.getText() != null && rdbtnBacklog.isSelected()) {
						query ="update "+username+" set backlogged_games = NULL where backlogged_games = '"+userEntry.getText()+"';";
					}
					else if(userEntry.getText() != null && rdbtnCompleted.isSelected()) {
						query ="update "+username+" set completed_games = NULL where completed_games = '"+userEntry.getText()+"';";
					}
					
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Entry Deleted");
					
					pst.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnDeleteEntry.setBounds(10, 291, 147, 23);
		contentPane.add(btnDeleteEntry);
		
		
		// search for entry then edit which list its in 
		JButton btnEditEntry = new JButton("Edit Entry");
		btnEditEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditEntry.setBounds(10, 257, 147, 23);
		contentPane.add(btnEditEntry);
		
		// button to load user data from the tables
		JButton btnLoadTable = new JButton("Update Table");
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					// loads data from users table
					String query ="select * from "+username;
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					gameListTable.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLoadTable.setBounds(318, 65, 194, 23);
		contentPane.add(btnLoadTable);
		
		
		
		
	}
}
