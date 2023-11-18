import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainPageGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPageGUI frame = new MainPageGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPageGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// database url, username, and password
	    String url = "jdbc:mysql://localhost:3306/introsweproject";
	    String SQLusername = "root";
	    String SQLpassword = "";
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollCurrent = new JScrollPane();
		scrollCurrent.setBounds(167, 111, 161, 299);
		contentPane.add(scrollCurrent);
		
		JScrollPane scrollBacklog = new JScrollPane();
		scrollBacklog.setBounds(338, 111, 161, 299);
		contentPane.add(scrollBacklog);
		
		JScrollPane scrollCompleted = new JScrollPane();
		scrollCompleted.setBounds(510, 111, 161, 299);
		contentPane.add(scrollCompleted);
		
		JLabel lblHeader = new JLabel("User's Backlog Status");
		lblHeader.setBounds(10, 11, 147, 14);
		contentPane.add(lblHeader);
		
		JLabel lblCurrent = new JLabel("Currently Playing");
		lblCurrent.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrent.setBounds(167, 86, 161, 14);
		contentPane.add(lblCurrent);
		
		JLabel lblGameBacklog = new JLabel("Game Backlog");
		lblGameBacklog.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameBacklog.setBounds(338, 86, 161, 14);
		contentPane.add(lblGameBacklog);
		
		JLabel lblCompletedGames = new JLabel("Completed Games");
		lblCompletedGames.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompletedGames.setBounds(510, 86, 161, 14);
		contentPane.add(lblCompletedGames);
		
		
		
		
		
		JButton btnAddGame = new JButton("Add Game");
		btnAddGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddGame.setBounds(10, 111, 147, 23);
		contentPane.add(btnAddGame);
		
		
		
		JButton btnDeleteEntry = new JButton("Delete Entry");
		btnDeleteEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDeleteEntry.setBounds(10, 387, 147, 23);
		contentPane.add(btnDeleteEntry);
		
		
		
		JButton btnEditEntry = new JButton("Edit Entry");
		btnEditEntry.setBounds(10, 248, 147, 23);
		contentPane.add(btnEditEntry);
		
		
	}

}
