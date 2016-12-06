package dragonball.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.player.Player;

public class WelcomeView2 extends JFrame {

	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JLabel text;
	private JLabel text2;
	private JLabel text3;
	private JLabel text4;
	private JLabel text5;
	private JTextArea area;
	private JTextArea area2;
	private JButton enter;
	private JButton load;
	private JComboBox combo;

	public JTextArea getArea2() {
		return area2;
	}

	public JComboBox getCombo() {
		return combo;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public JPanel getPanel3() {
		return panel3;
	}

	public JPanel getPanel4() {
		return panel4;
	}

	public JPanel getPanel5() {
		return panel5;
	}

	public JLabel getText() {
		return text;
	}

	public JLabel getText2() {
		return text2;
	}

	public JLabel getText3() {
		return text3;
	}

	public JLabel getText4() {
		return text4;
	}

	public JTextArea getArea() {
		return area;
	}

	public JButton getEnter() {
		return enter;
	}

	public JButton getLoad() {
		return load;
	}

	public WelcomeView2() {
		setTitle("Dragon Ball Z Adventures!!");
		setIconImage((new ImageIcon("dragon ball.png")).getImage());
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();

		JLabel space1 = new JLabel("                                      ");
		space1.setFont(space1.getFont().deriveFont(104.0f));
		JLabel space2 = new JLabel("                                      ");
		space2.setFont(space1.getFont().deriveFont(4.0f));
		JLabel space3 = new JLabel("            ");
		space3.setFont(space1.getFont().deriveFont(100.0f));
		JLabel space4 = new JLabel("      ");
		space4.setFont(space1.getFont().deriveFont(56.0f));
		JLabel space5 = new JLabel("      ");
		space5.setFont(space1.getFont().deriveFont(56.0f));
		JLabel space6 = new JLabel(
				"                                                                               ");
		space6.setFont(space1.getFont().deriveFont(20.0f));
		JLabel space7 = new JLabel("   ");
		space7.setFont(space7.getFont().deriveFont(50.0f));

		setContentPane(new JLabel(new ImageIcon("background.jpg")));
		// setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setLayout(new FlowLayout());
		// setBounds(50, 50, 800, 300); //x,y,width,height

		text = new JLabel("Welcome to Dragon Ball Z !");
		text.setFont(text.getFont().deriveFont(44.0f));
		text.setForeground(Color.blue);
		// text.setOpaque(false);

		text2 = new JLabel("Please Enter your name here");
		text2.setFont(text.getFont().deriveFont(38.0f));
		text2.setForeground(Color.red);

		text3 = new JLabel("Or load a saved Game");
		text3.setFont(text.getFont().deriveFont(38.0f));

		text4 = new JLabel("Choose your fighter type");

		text5 = new JLabel("Please Enter you Fighter's name");
		text5.setFont(text.getFont().deriveFont(30.0f));

		area = new JTextArea(2, 10);
		area.setFont(text.getFont().deriveFont(16.0f));
		area.setForeground(Color.black);
		area.setLineWrap(true);
		JScrollPane scrollArea = new JScrollPane(area);

		area2 = new JTextArea(2, 10);
		area2.setFont(text.getFont().deriveFont(16.0f));
		area2.setForeground(Color.black);
		area.setLineWrap(true);
		JScrollPane scrollArea2 = new JScrollPane(area2);

		enter = new JButton("Enter");
		enter.setFont(text.getFont().deriveFont(44.0f));
		enter.setSize(2, 10);

		load = new JButton("Load");
		load.setFont(text.getFont().deriveFont(44.0f));

		panel1.add(text);
		add(panel1);
		add(space1);

		panel2.add(text2);

		panel3.add(text3);

		panel5.add(text5);

		String[] list = { "Majin", "Frieza", "Saiyan", "Namekian", "Earthling" };
		panel4.add(text4);
		combo = new JComboBox<String>(list);
		panel4.add(combo);

		// panel1.setOpaque(false);
		// panel2.setOpaque(false);
		// panel3.setOpaque(false);
		// panel4.setOpaque(false);
		// panel5.setOpaque(false);

		add(panel2);
		add(space4);
		add(scrollArea);
		add(space5);
		add(panel4);
		add(panel5);
		add(scrollArea2);
		add(space6);
		add(enter);
		add(space2);
		add(panel3);
		add(load);
		pack();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit ?", "WARNING !",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (n == JOptionPane.YES_OPTION)
					System.exit(1);
			}

		});

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		WelcomeView2 m = new WelcomeView2();
		System.out.println(m.enter.getText());
	}

}
