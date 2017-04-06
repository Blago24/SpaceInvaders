package Screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserName {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserName window = new UserName();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserName() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 125, 380, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewHighscoreAchieved = new JLabel("New highscore achieved! ");
		lblNewHighscoreAchieved.setForeground(new Color(255, 69, 0));
		lblNewHighscoreAchieved.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblNewHighscoreAchieved.setBounds(79, 23, 293, 26);
		frame.getContentPane().add(lblNewHighscoreAchieved);
		
		JLabel lblNewLabel = new JLabel("Enter your name below:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setForeground(new Color(255, 69, 0));
		lblNewLabel.setBounds(12, 89, 252, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnAddPlayer = new JButton("Add player");
		btnAddPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameOverScreen gs = new GameOverScreen(null);
				gs.pushToGameOverScreen(textField.getText());
			}
		});
		btnAddPlayer.setBounds(295, 215, 97, 25);
		frame.getContentPane().add(btnAddPlayer);
	}
}
