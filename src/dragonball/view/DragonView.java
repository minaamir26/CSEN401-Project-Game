package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import dragonball.controller.Control;

public class DragonView extends JFrame {

	private JPanel panel;
	private JLabel welcomeLabel;
	private JLabel chooseWish;
	private JComboBox<String> options;
	private JButton done;
	private ActionListener listener;

	public ActionListener getListener() {
		return listener;
	}

	public void setListener(ActionListener listener) {
		this.listener = listener;
	}

	public DragonView(Control c) {
		super();
		listener = c;
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit ?", "WARNING !",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (n == JOptionPane.YES_OPTION)
					System.exit(1);
			}

		});

		setTitle("Dragon Ball Adventures \"The Dragon!\"");
		setIconImage((new ImageIcon("dragon ball.png")).getImage());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(1366, 720);
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(500, 500));
		setResizable(false);
		JLabel x = new JLabel(new ImageIcon("dragon.jpg"));
		setContentPane(x);
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setVisible(true);
		getContentPane().setLayout(new BorderLayout());
		welcomeLabel = new JLabel(
				"Congratulations!! You have collected 7 dragon balls and now you can choose a wish from the dragon!");
		welcomeLabel.setFont(new Font("Calibri(Body)", Font.BOLD, 20));
		welcomeLabel.setForeground(Color.RED);
		welcomeLabel.setBounds(200, 50, 1000, 30);
		welcomeLabel.setOpaque(false);
		chooseWish = new JLabel("Choose a wish from the following !");
		chooseWish.setFont(new Font("Calibri(Body)", Font.BOLD, 12));
		chooseWish.setForeground(Color.WHITE);
		chooseWish.setOpaque(false);
		chooseWish.setBounds(100, 420, 200, 30);
		options = new JComboBox<String>();
		// for (int i = 0; i < 2 ; i++) {
		// options.addItem(((Control)
		// listener).getDragon().getWishes()[i].toString());
		// }
		options.addItem("Senzu Beans");
		options.addItem("Ability Points");
		options.addItem("Unlock new random super attack");
		options.addItem("Unlock new random ultimate attack");
		// options.addItem("Ability Points");
		// options.addItem("Unlock a new super attack");
		// options.addItem("Unlock a new ultimate attack");
		options.setBounds(100, 460, 240, 30);
		done = new JButton("Done!");
		done.setBounds(1250, 640, 80, 30);
		panelAdd();
		add(panel, BorderLayout.CENTER);
		setVisible(true);
		validate();
	}

	public void panelAdd() {
		panel.add(welcomeLabel);
		panel.add(chooseWish);
		panel.add(options);
		panel.add(done);
	}

	//
	// public static void main(String[] args) {
	// new DragonView();
	// }

	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}

	public void setWelcomeLabel(JLabel welcomeLabel) {
		this.welcomeLabel = welcomeLabel;
	}

	public JLabel getChooseWish() {
		return chooseWish;
	}

	public void setChooseWish(JLabel chooseWish) {
		this.chooseWish = chooseWish;
	}

	public JComboBox<String> getOptions() {
		return options;
	}

	public void setOptions(JComboBox<String> options) {
		this.options = options;
	}

	public JButton getDone() {
		return done;
	}

	public void setDone(JButton done) {
		this.done = done;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

}
