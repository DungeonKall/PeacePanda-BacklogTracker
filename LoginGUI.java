import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.Integer.parseInt;

/*
Form for login page GUI
 */
public class LoginGUI extends JFrame {

    private JPanel loginPanel;
    private JLabel titleLabel;
    private JLabel welcomeLabel;
    private JTextField userField;
    private JPasswordField passField;
    private JLabel passLabel;
    private JLabel userLabel;
    private JButton signupButton;
    private JButton loginButton;
    private JLabel incorrectLabel;

    // database url, username, and password
    String url = "jdbc:mysql://localhost:3306/introsweproject";
    String SQLusername = "root";
    String SQLpassword = "";

    public LoginGUI() {
        // setting up the GUI
        setContentPane(loginPanel);
        setTitle("Backlog Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        // creating class object for other panel
        MainPageGUI mainPage = new MainPageGUI();

        /**
         * login method
         */
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // username and password fields taken from text fields
                String username = userField.getText();
                char[]  password = passField.getPassword();
                String strPassword = "";

                // changing password to String for now, not sure how to deal with a char[] for the sql database
                for (char c : password) {
                    strPassword += c;
                }

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
                            setVisible(false); // closing out of login panel
                            mainPage.setVisible(true);
                        }
                        else {
                            System.out.println("The passwords don't match L");
                            incorrectLabel.setVisible(true);
                        }
                    } // end if
                    incorrectLabel.setVisible(true); // sets label to false in case none of the previous if/else catches
                }
                catch (Exception error) {
                    System.out.println(error);
                }
            }
        });

        /**
         * sign up method
         */
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // username and password fields taken from text fields
                String username = userField.getText();
                char[]  password = passField.getPassword();
                String strPassword = "";
                // changing password to String for now, not sure how to deal with a char[] for the sql database
                for (char c : password) {
                    strPassword += c;
                }

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

                // NEED TO ADD SOMETHING TO CATCH IF THERE IS A DUPLICATE ENTRY

                catch (Exception error) {
                    System.out.println(error);
                }
                // creating user class object based with the entered username and password
                //User newUser = new User(username, password);
            }
        });
    }

    public static void main (String[] args) {
        new LoginGUI();
    }
}
