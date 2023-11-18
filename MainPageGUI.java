import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Form for main page GUI
 */
public class MainPageGUI extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JLabel currentLabel;
    private JLabel suggestedLabel;
    private JLabel completedLabel;
    private JTable currPlayTable;
    private JTable suggTable;
    private JTable compTable;

    // database url, username, and password
    String url = "jdbc:mysql://localhost:3306/introsweproject";
    String username = "root";
    String password = "";

    public MainPageGUI() {
        setContentPane(mainPanel);
        setTitle("Backlog Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(false); // set to false until the user logs in

        /**
         * add game button method
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // need access to the sql database to begin working on this
            }
        });

        /**
         * edit entry button method
         */
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // need access to the sql database to begin working on this
            }
        });

        /**
         * delete entry button method
         */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // need access to the sql database to begin working on this
            }
        });
    }
    /*
    Main method for this form, used for debugging purposes
     */
    public static void main (String[] args) {
        new MainPageGUI();
    }
}
