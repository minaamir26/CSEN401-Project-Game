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

public class UpgradeView extends JFrame {

	private JPanel firstPanel;
	private JLabel firstLabel;
	private JLabel fighterName;
	private JLabel maxHealthPoints;
	private JLabel physicalDamage;
	private JLabel blastDamage;
	private JLabel maxKi;
	private JLabel maxStamina;
	private JLabel abilityPoints;
	private JLabel chooseLabel;
	private JComboBox<String> options;
	private JButton upgrade;
	private JButton exit;
	private ActionListener listener;

	public JLabel getFighterName() {
		return fighterName;
	}

	public void setFighterName(JLabel fighterName) {
		this.fighterName = fighterName;
	}

	public ActionListener getListener() {
		return listener;
	}

	public void setListener(ActionListener listener) {
		this.listener = listener;
	}

	public UpgradeView() {
		super();
		setTitle("Dragon Ball Adventures \"Upgrade The Active Fighter!\"");
		setIconImage((new ImageIcon("dragon ball.png")).getImage());
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
		setSize(1366, 720);
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(500, 500));
		setContentPane(new JLabel(new ImageIcon("DG6.jpg")));
		setResizable(false);
		firstPanel = new JPanel();
		firstPanel.setLayout(null);
		firstPanel.setOpaque(false);
		firstPanel.setVisible(true);
		getContentPane().setLayout(new BorderLayout());
		firstLabel = new JLabel(
				"The Current Active Fighter's Characterictics!!");
		firstLabel.setBounds(150, 100, 400, 30);
		firstLabel.setFont(new Font("Calibri(Body)", Font.BOLD, 18));
		firstLabel.setForeground(Color.black);
		firstLabel.setOpaque(false);
		fighterName = new JLabel("Name : ");
		fighterName.setBounds(150, 140, 250, 30);
		fighterName.setFont(new Font("Calibri(Body)", Font.BOLD, 18));
		fighterName.setForeground(Color.black);
		fighterName.setOpaque(false);
		maxHealthPoints = new JLabel("Max Health Points : ");
		maxHealthPoints.setBounds(150, 180, 250, 30);
		maxHealthPoints.setFont(new Font("Calibri(Body)", Font.BOLD, 18));
		maxHealthPoints.setForeground(Color.black);
		maxHealthPoints.setOpaque(false);
		physicalDamage = new JLabel("Physical Damage : ");
		physicalDamage.setBounds(150, 220, 250, 30);
		physicalDamage.setFont(new Font("Calibri(Body)", Font.BOLD, 18));
		physicalDamage.setForeground(Color.black);
		physicalDamage.setOpaque(false);
		blastDamage = new JLabel("Blast Damage : ");
		blastDamage.setBounds(150, 260, 250, 30);
		blastDamage.setFont(new Font("Calibri(Body)", Font.BOLD, 18));
		blastDamage.setForeground(Color.black);
		blastDamage.setOpaque(false);
		maxKi = new JLabel("Max Ki : ");
		maxKi.setBounds(150, 300, 250, 30);
		maxKi.setFont(new Font("Calibri(Body)", Font.BOLD, 18));
		maxKi.setForeground(Color.black);
		maxKi.setOpaque(false);
		maxStamina = new JLabel("Max Stamina : ");
		maxStamina.setBounds(150, 340, 250, 30);
		maxStamina.setFont(new Font("Calibri(Body)", Font.BOLD, 18));
		maxStamina.setForeground(Color.black);
		maxStamina.setOpaque(false);
		abilityPoints = new JLabel("Ability Points : ");
		abilityPoints.setBounds(150, 380, 250, 30);
		abilityPoints.setFont(new Font("Calibri(Body)", Font.BOLD, 18));
		abilityPoints.setForeground(Color.black);
		abilityPoints.setOpaque(false);
		chooseLabel = new JLabel("Choose something to upgrade!");
		chooseLabel.setBounds(150, 420, 300, 30);
		chooseLabel.setFont(new Font("Calibri(Body)", Font.BOLD, 18));
		chooseLabel.setForeground(Color.black);
		chooseLabel.setOpaque(false);
		options = new JComboBox<String>();
		options.addItem("Max health Points");
		options.addItem("Blast damage");
		options.addItem("Physical damage");
		options.addItem("Max ki");
		options.addItem("Max stamina");
		options.setBounds(500, 420, 130, 30);
		upgrade = new JButton("Upgrade");
		upgrade.setBounds(500, 550, 100, 30);
		exit = new JButton("Continue to world mode");
		exit.setBounds(650, 550, 200, 30);
		panelAdd();
		setVisible(true);
		validate();
	}

	public void panelAdd() {
		firstPanel.add(firstLabel);
		firstPanel.add(fighterName);
		firstPanel.add(maxHealthPoints);
		firstPanel.add(physicalDamage);
		firstPanel.add(blastDamage);
		firstPanel.add(maxKi);
		firstPanel.add(maxStamina);
		firstPanel.add(abilityPoints);
		firstPanel.add(chooseLabel);
		firstPanel.add(options);
		firstPanel.add(upgrade);
		firstPanel.add(exit);
		add(firstPanel, BorderLayout.CENTER);
	}

	public void update() {
		// do not forget to update here ===> ;)
		fighterName.setText("Name : "
				+ ((Control) listener).getGame().getPlayer().getActiveFighter()
						.getName());
		maxHealthPoints.setText("Max Health Points : "
				+ ((Control) listener).getGame().getPlayer().getActiveFighter()
						.getMaxHealthPoints());
		physicalDamage.setText("Physical Damage : "
				+ ((Control) listener).getGame().getPlayer().getActiveFighter()
						.getPhysicalDamage());
		blastDamage.setText("Blast Damage : "
				+ ((Control) listener).getGame().getPlayer().getActiveFighter()
						.getBlastDamage());
		maxKi.setText("Max Ki : "
				+ ((Control) listener).getGame().getPlayer().getActiveFighter()
						.getMaxKi());
		maxStamina.setText("Max Stamina : "
				+ ((Control) listener).getGame().getPlayer().getActiveFighter()
						.getMaxStamina());
		abilityPoints.setText("Ability Points : "
				+ ((Control) listener).getGame().getPlayer().getActiveFighter()
						.getAbilityPoints());
	}

	public static void main(String[] args) {
		new UpgradeView();
	}

	public JLabel getFirstLabel() {
		return firstLabel;
	}

	public void setFirstLabel(JLabel firstLabel) {
		this.firstLabel = firstLabel;
	}

	public JLabel getfighterName() {
		return fighterName;
	}

	public void setfighterName(JLabel fighterName) {
		this.fighterName = fighterName;
	}

	public JLabel getMaxHealthPoints() {
		return maxHealthPoints;
	}

	public void setMaxHealthPoints(JLabel maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
	}

	public JLabel getPhysicalDamage() {
		return physicalDamage;
	}

	public void setPhysicalDamage(JLabel physicalDamage) {
		this.physicalDamage = physicalDamage;
	}

	public JLabel getBlastDamage() {
		return blastDamage;
	}

	public void setBlastDamage(JLabel blastDamage) {
		this.blastDamage = blastDamage;
	}

	public JLabel getMaxKi() {
		return maxKi;
	}

	public void setMaxKi(JLabel maxKi) {
		this.maxKi = maxKi;
	}

	public JLabel getMaxStamina() {
		return maxStamina;
	}

	public void setMaxStamina(JLabel maxStamina) {
		this.maxStamina = maxStamina;
	}

	public JLabel getAbilityPoints() {
		return abilityPoints;
	}

	public void setAbilityPoints(JLabel abilityPoints) {
		this.abilityPoints = abilityPoints;
	}

	public JLabel getChooseLabel() {
		return chooseLabel;
	}

	public void setChooseLabel(JLabel chooseLabel) {
		this.chooseLabel = chooseLabel;
	}

	public JComboBox<String> getOptions() {
		return options;
	}

	public void setOptions(JComboBox<String> options) {
		this.options = options;
	}

	public JButton getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(JButton upgrade) {
		this.upgrade = upgrade;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

	public JPanel getFirstPanel() {
		return firstPanel;
	}

	public void setFirstPanel(JPanel firstPanel) {
		this.firstPanel = firstPanel;
	}

}
