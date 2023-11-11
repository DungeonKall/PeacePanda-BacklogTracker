import javax.swing.*;

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

    }

    public static void main (String[] args) {
        new LoginGUI();
    }
}
