package dragonball.tournament.view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class FirstView extends JFrame{
	
	private JButton tournament;
	private JButton gameMode;
	
	
	
	public JButton getTournament() {
		return tournament;
	}

	public void setTournament(JButton tournament) {
		this.tournament = tournament;
	}

	public JButton getGameMode() {
		return gameMode;
	}

	public void setGameMode(JButton gameMode) {
		this.gameMode = gameMode;
	}

	public FirstView()
	{
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit ?", "WARNING !",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (n == JOptionPane.YES_OPTION)
					System.exit(1);
			}

		});

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	
		JLabel x = new JLabel(new ImageIcon("back2.jpg"));
		setContentPane(x);
		
		tournament = new JButton("Go to Tourament Mode!");
		gameMode = new JButton("Go to World Mode!");
		setLayout(new FlowLayout());
		add(tournament);
		add(gameMode);
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new FirstView();
	}
	
}
