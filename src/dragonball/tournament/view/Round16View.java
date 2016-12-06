package dragonball.tournament.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;

import dragonball.tournament.controller.TournamentControl;

@SuppressWarnings("serial")
public class Round16View extends JFrame {
	
	JPanel panel1;
	JPanel panel2;
	ArrayList<JLabel> labels ;
	TournamentControl listener;
	JButton button;
	ArrayList<JLabel> winners;
	
	
	public JPanel getPanel2() {
		return panel2;
	}

	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	public ArrayList<JLabel> getWinners() {
		return winners;
	}

	public void setWinners(ArrayList<JLabel> winners) {
		this.winners = winners;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel) {
		this.panel1 = panel;
	}

	public ArrayList<JLabel> getLabels() {
		return labels;
	}

	public void setLabels(ArrayList<JLabel> labels) {
		this.labels = labels;
	}

	public TournamentControl getListener() {
		return listener;
	}

	public void setListener(TournamentControl listener) {
		this.listener = listener;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public Round16View(TournamentControl t)
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
		setResizable(false);
		winners = new ArrayList<JLabel>();
		listener=t;
		setContentPane(new JLabel(new ImageIcon("tour.jpg")));
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
		labels = new ArrayList<JLabel>();
		button = new JButton("Advance to the next Battle");
		String s="";
		if(listener.getTournament().getRound16().getRound() == 2 )
			s="THE FINAL !!";
		else
			s="This is the draw of round "+listener.getTournament().getRound16().getRound()+ "!";
		JLabel title = new JLabel(s);
		title.setFont(new Font("Calibri(Body)", Font.BOLD, 30));
		title.setForeground(Color.white);

		panel1.add(title);
		for(int i=0;i<listener.getTournament().getRound16().getRound()/2;i++)
		{
			JLabel label = new JLabel("" +listener.getTournament().getRound16().getPlayer1()[i].getName()
					+" vs " + listener.getTournament().getRound16().getPlayer2()[i].getName());
			label.setFont(new Font("Calibri(Body)", Font.BOLD, 21));
			label.setForeground(Color.white);
			labels.add(label);
			panel1.add(label);
		}
		JLabel title2 = new JLabel("Winners");
		title2.setFont(new Font("Calibri(Body)", Font.BOLD, 40));
		title2.setForeground(Color.red);

		panel2.add(title2);
		for(int i=0;i<listener.getTournament().getRound16().getRound()/2;i++)
		{
			JLabel label = new JLabel("" + (((listener.getTournament().getRound16().getWinners()[i])==null)? "Not played yet!" : 
				listener.getTournament().getRound16().getWinners()[i]));
			label.setFont(new Font("Calibri(Body)", Font.BOLD, 21));
			label.setForeground(Color.red);
			winners.add(label);
			panel2.add(label);
		}
		
		panel1.add(new JLabel("    "));
		panel1.add(new JLabel("    "));
		panel1.add(new JLabel("    "));
		panel1.add(new JLabel("    "));
		panel1.add(new JLabel("    "));
		panel1.add(new JLabel("    "));
		
		panel2.add(new JLabel("    "));
		panel2.add(new JLabel("    "));
		panel2.add(new JLabel("    "));
		panel2.add(new JLabel("    "));
		panel2.add(new JLabel("    "));
		panel2.add(button);
		
		add(panel1);
		add(panel2);
		pack();
		setVisible(false);
	}
	
	public void update()
	{
		for(int i=0;i<listener.getTournament().getRound16().getRound()/2;i++)
		{
			winners.get(i).setText("" + (((listener.getTournament().getRound16().getWinners()[i])==null)? "Not played yet!" : 
				(listener.getTournament().getRound16().getWinners()[i]).getName()));
		}
		pack();
	}
	
}
