import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;

/*
Form for login page GUI
 */
public class LoginGUI{

	private JFrame loginPanel;
    private JTextField textUserField;
    private JPasswordField passwordField;

    // database url, username, and password
    String url = "jdbc:mysql://localhost:3306/introsweproject";
    String SQLusername = "root";
    String SQLpassword = "";
    
    /**
	 * Create the application. And Default Constructor
     * @throws SQLException 
     * @throws ClassNotFoundException 
	 */
    public LoginGUI() throws ClassNotFoundException, SQLException {
    	initialize();
    }
    
    /**
	 * Initialize the contents of the frame.
     * @throws SQLException 
     * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, SQLException {
    	
        // setting up the GUI
        loginPanel = new JFrame();
		loginPanel.setBounds(100, 100, 508, 475);
		loginPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginPanel.getContentPane().setLayout(null);
		loginPanel.setTitle("Backlog Tracker");
        
        // text field creation
        textUserField = new JTextField();
        textUserField.setColumns(10);
        textUserField.setBounds(139, 181, 196, 29);
        loginPanel.getContentPane().add(textUserField);
        
        // password field creation, should prevent onlookers from identifying characters on screen
        passwordField = new JPasswordField();
        passwordField.setBounds(139, 221, 196, 30);
        loginPanel.getContentPane().add(passwordField);
        loginPanel.setVisible(true);
        
        
        /**
         * Label creation section
         */
        JLabel duplicateUserWarning = new JLabel("Username is already in use.");
        duplicateUserWarning.setHorizontalAlignment(SwingConstants.CENTER);
        duplicateUserWarning.setBounds(139, 261, 196, 14);
        loginPanel.getContentPane().add(duplicateUserWarning);
        duplicateUserWarning.setVisible(false);
        
        JLabel incorrectLabel = new JLabel("Username or Password is not recognized.");
        incorrectLabel.setForeground(Color.RED);
        incorrectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        incorrectLabel.setBounds(93, 261, 289, 14);
        loginPanel.getContentPane().add(incorrectLabel);
        incorrectLabel.setVisible(false);
        
        JLabel lblTitleLabel = new JLabel("Backlog Tracker");
        lblTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblTitleLabel.setBounds(82, 11, 345, 72);
        loginPanel.getContentPane().add(lblTitleLabel);
        
        JLabel lblWelcomeLabel = new JLabel("Welcome Gamer!");
        lblWelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblWelcomeLabel.setBounds(82, 78, 311, 72);
        loginPanel.getContentPane().add(lblWelcomeLabel);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(16, 184, 113, 26);
        loginPanel.getContentPane().add(lblUsername);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(16, 224, 113, 26);
        loginPanel.getContentPane().add(lblPassword);
        
        
        
        /**
         * Signup button and method
         */
        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		// username and password fields taken from text fields
                String username = textUserField.getText();
                char[]  password = passwordField.getPassword();
                String strPassword = "";
                // changing password to String for now, not sure how to deal with a char[] for the sql database
                for (char c : password) {
                    strPassword += c;
                }
                
                // before user might attempt another sing up, will remove label stating there is duplicate user
                duplicateUserWarning.setVisible(false);
                incorrectLabel.setVisible(false);

                // creating connection
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, SQLusername, SQLpassword);

                    Statement statement = connection.createStatement();

                    // sql insert
                    String sqlInsert = "insert into users_table values ('" + username + "', '"
                            + strPassword + "')";
                    System.out.println("sqlInsert: " + sqlInsert);
                    int countInserted = statement.executeUpdate(sqlInsert); // executing the statement created above
                    System.out.println("Data has been inserted into the database");

                    // closing connection
                    connection.close();
                }
                
                // catch statement for specifically duplicates in the usernames since they are considered the primary keys
                catch(SQLIntegrityConstraintViolationException s) {
                	System.out.println(s);
                    duplicateUserWarning.setVisible(true);
                }

                catch (Exception error) {
                    System.out.println(error);
                    
                }
                
                // section for creating a table associated with the user
                try {
                	// establish connection once again
                	Connection connection = DriverManager.getConnection(url, SQLusername, SQLpassword);
                	
                	// create a prepared statement to make a table using the username as the identifier
                	PreparedStatement createTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "+
                			username+"(currently_playing VARCHAR(100), backlogged_games VARCHAR(100), completed_games VARCHAR(100))");
                	
                	// execute statement to create etable
                	createTable.executeUpdate();
                	
                	// closing connection
                    connection.close();
                	
                }catch(Exception err) {
                	System.out.println(err);
                }
                
        	}
        });
        btnSignUp.setBounds(139, 286, 89, 23);
        loginPanel.getContentPane().add(btnSignUp);
        
        
        
        /**
         * Login button and method
         */
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		// username and password fields taken from text fields
                String username = textUserField.getText();
                char[]  password = passwordField.getPassword();
                String strPassword = "";
                
                // changing password to String for now, not sure how to deal with a char[] for the sql database
                for (char c : password) {
                    strPassword += c;
                }
                
                // removes the password fail label before user attempts the try logging in again
                incorrectLabel.setVisible(false);
                duplicateUserWarning.setVisible(false);
                
                // sql connections
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, SQLusername, SQLpassword);

                    // strings for searching database
                    String query = "select * from users_table where username='" + username + "'";
                    // System.out.println(query); // for debugging
                    // creating and executing statement to get the data
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(query);

                    // checking if password matches to log user in
                    if (result.next()) { // moving pointer up
                        if (result.getString("password").equals(strPassword)) {
                            System.out.println("The passwords match :thumbsup:");
                            loginPanel.setVisible(false); // closing out of login panel
                            
                         // creating class object for other panel
                            MainPageGUI mainPage = new MainPageGUI(username);
                            mainPage.setVisible(true);
                        }
                        else {
                            System.out.println("The passwords don't match L");
                            incorrectLabel.setVisible(true);
                        }
                    } // end if
                    incorrectLabel.setVisible(true); // sets label to true in case none of the previous if/else catches
                    								 //   still does not catch it in the else currently for some reason,
                    								 //   we could get rid of the else
                }
                catch (Exception error) {
                    System.out.println(error);
                }
        	}
        });
        btnLogin.setBounds(246, 286, 89, 23);
        loginPanel.getContentPane().add(btnLogin);
        
        
        
        
    }

    
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.loginPanel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
