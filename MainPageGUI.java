import javax.swing.*;
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

    public MainPageGUI() {
        setContentPane(mainPanel);
        setTitle("Backlog Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /*
    Main method for this form, used for debugging purposes
     */
    public static void main (String[] args) {
        new MainPageGUI();
    }
}
