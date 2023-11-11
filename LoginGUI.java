import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public LoginGUI() {
        // setting up the GUI
        setContentPane(loginPanel);
        setTitle("Backlog Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);


        /**
         * login method
         */
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // username and password fields taken from text fields
                String username = userField.getText();
                char[]  password = passField.getPassword();
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
                // creating user class object based with the entered username and password
                User newUser = new User(username, password);
                // need to add stuff to add them to the SQL database here.
            }
        });
    }

    public static void main (String[] args) {
        new LoginGUI();
    }
}
