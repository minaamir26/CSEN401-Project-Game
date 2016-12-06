package dragonball.tournament.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class WelcomeView extends JFrame {

	JLabel label0;
	JLabel label;
	JComboBox<String> combo;
	JButton enter;
	
	
	public JLabel getLabel0() {
		return label0;
	}

	public void setLabel0(JLabel label0) {
		this.label0 = label0;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JComboBox<String> getCombo() {
		return combo;
	}

	public void setCombo(JComboBox<String> combo) {
		this.combo = combo;
	}

	public JButton getEnter() {
		return enter;
	}

	public void setEnter(JButton enter) {
		this.enter = enter;
	}

	public WelcomeView(){
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
		JLabel x = new JLabel(new ImageIcon("tour.jpg"));
		setContentPane(x);
		
		label0 = new JLabel("Welcome to the tournament mode !");
		label0.setForeground(Color.WHITE);
		label=new JLabel("Choose the number of the player fighters");
		label.setForeground(Color.WHITE);

		combo = new JComboBox();
		enter = new JButton("Enter");
		setLayout(new FlowLayout());
		for(int i=1;i<=16;i++)
		{
			combo.addItem("" + i);
		}
		
		add(label0);
		add(label);
		add(combo);
		add(enter);
		pack();
		setResizable(false);

		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		WelcomeView w = new WelcomeView();
	}
	
}
