package dragonball.view;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class WelcomeView extends JFrame {

	private JPanel welcomePanel;
	private JPanel namePanel;
	private JPanel fighterPanel;
	private JPanel fighterPanel0;
	private JTextArea area;
	private JTextArea area2;
	private JButton enter;
	private JButton load;
	private JComboBox combo;

	public JPanel getWelcomePanel() {
		return welcomePanel;
	}

	public void setWelcomePanel(JPanel welcomePanel) {
		this.welcomePanel = welcomePanel;
	}

	public JPanel getNamePanel() {
		return namePanel;
	}

	public void setNamePanel(JPanel namePanel) {
		this.namePanel = namePanel;
	}

	public JPanel getFighterPanel() {
		return fighterPanel;
	}

	public void setFighterPanel(JPanel fighterPanel) {
		this.fighterPanel = fighterPanel;
	}

	public JPanel getFighterPanel0() {
		return fighterPanel0;
	}

	public void setFighterPanel0(JPanel fighterPanel0) {
		this.fighterPanel0 = fighterPanel0;
	}

	public JTextArea getArea() {
		return area;
	}

	public void setArea(JTextArea area) {
		this.area = area;
	}

	public JTextArea getArea2() {
		return area2;
	}

	public void setArea2(JTextArea area2) {
		this.area2 = area2;
	}

	public JButton getEnter() {
		return enter;
	}

	public void setEnter(JButton enter) {
		this.enter = enter;
	}

	public JButton getLoad() {
		return load;
	}

	public void setLoad(JButton load) {
		this.load = load;
	}

	public JComboBox getCombo() {
		return combo;
	}

	public void setCombo(JComboBox combo) {
		this.combo = combo;
	}

	public WelcomeView() {
		setTitle("Dragon Ball Z Adventures");
		setIconImage((new ImageIcon("dragon ball.png")).getImage());
		setContentPane(new JLabel(new ImageIcon("back0.png")));
		welcomePanel = new JPanel();
		namePanel = new JPanel();
		fighterPanel = new JPanel();
		fighterPanel0 = new JPanel();
		area = new JTextArea(2, 10);
		area.setFont(area.getFont().deriveFont(16.0f));
		area.setForeground(Color.black);
		area.setLineWrap(true);
		JScrollPane scrollArea = new JScrollPane(area);

		area2 = new JTextArea(2, 10);
		area2.setFont(area2.getFont().deriveFont(16.0f));
		area2.setForeground(Color.black);
		area.setLineWrap(true);
		JScrollPane scrollArea2 = new JScrollPane(area2);

		enter = new JButton("Enter");
		load = new JButton("Load");
		load.setBounds(420, 525, 80, 30);

		String[] list = { "Majin", "Frieza", "Saiyan", "Namekian", "Earthling" };
		combo = new JComboBox<String>(list);

		welcomePanel.setBounds(170, 50, 404, 140);
		welcomePanel.add(new JLabel(new ImageIcon("welcome.gif")));
		welcomePanel.setOpaque(false);

		namePanel.setBounds(210, 210, 300, 100);
		;
		namePanel.add(scrollArea);
		namePanel.setOpaque(false);

		fighterPanel0.setBounds(200, 300, 330, 200);
		fighterPanel0.add(scrollArea2);
		fighterPanel0.setOpaque(false);

		fighterPanel.setBounds(200, 390, 330, 200);
		fighterPanel.add(combo);
		fighterPanel.add(enter);
		fighterPanel.setOpaque(false);

		// add(welcomePanel);
		add(namePanel);
		add(fighterPanel0);
		add(fighterPanel);
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

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	public static void main(String[] args) {
		new WelcomeView();
	}

}
