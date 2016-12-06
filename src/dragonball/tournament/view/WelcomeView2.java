package dragonball.tournament.view;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;

public class WelcomeView2 extends JFrame {
	
	ArrayList<JComboBox> combos;
	ArrayList<JTextArea> areas;
	JButton button;
	
	public ArrayList<JComboBox> getCombos() {
		return combos;
	}

	public void setCombos(ArrayList<JComboBox> combos) {
		this.combos = combos;
	}

	public ArrayList<JTextArea> getAreas() {
		return areas;
	}

	public void setAreas(ArrayList<JTextArea> areas) {
		this.areas = areas;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public WelcomeView2(int n){
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
		
		setTitle("Choose the player names");
		setSize(640,420);
		setContentPane(new JLabel(new ImageIcon("tour.jpg")));

		combos = new ArrayList<JComboBox>();
		areas = new ArrayList<JTextArea>();
		button = new JButton("Enter !");
		setLayout(new FlowLayout());
		for(int i=0;i<n;i++)
		{
			JComboBox combo  = new JComboBox();
			combo.addItem("Saiyan");
			combo.addItem("Majin");
			combo.addItem("Namekian");
			combo.addItem("Earthling");
			combo.addItem("Frieza");
			JTextArea area = new JTextArea(2,10);
			combos.add(combo);
			areas.add(area);
			add(area);
			add(combo);
		}
		add(button);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		WelcomeView2 w = new WelcomeView2(1);
		w.setVisible(true);
	}

}
