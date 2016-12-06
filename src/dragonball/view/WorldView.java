package dragonball.view;

import java.awt.*;

import javax.swing.*;

import sun.awt.IconInfo;

import java.awt.event.*;
import java.util.ArrayList;

import dragonball.controller.*;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.world.World;

public class WorldView extends JFrame {

	private JPanel grid;
	private JPanel control1;
	private JPanel control2;
	private JLabel[][] gridSlotts;
	private JLabel player;
	private JLabel senzuBeans;
	private JLabel dragonBalls;
	private JLabel activeFighter;
	private JLabel level;
	private JLabel swithchFighter;
	private JLabel assigAttackLabel;
	private JComboBox<String> fighters;
	private JButton switchButton;
	private JComboBox<String> superAttacks;
	private JComboBox<String> ultimateAttacks;
	private JComboBox<String> oldSuper;
	private JComboBox<String> oldUltimate;
	private JButton assignSuperAttack;
	private JButton assignUltimateAttack;
	private JLabel createFighter;
	private JComboBox<String> race;
	private JButton create;
	private JTextField fighterName;
	private JLabel enterName;
	private JButton upgrade;
	private JButton save;
	private JButton moveUp;
	private JButton moveDown;
	private JButton moveRight;
	private JButton moveLeft;
	private ActionListener listener;
	private KeyListener listenerer;
	private JLabel xp;
	private JLabel targetXp;

	public WorldView() {
		super();
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
		setTitle("Dragon Ball Adventures!");
		setIconImage((new ImageIcon("dragon ball.png")).getImage());
		setSize(1366, 720);
		// setSize(1000,700);
		setResizable(false);
		setMinimumSize(new Dimension(500, 500));
		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(new BorderLayout());
		grid = new JPanel();
		grid.setLayout(new GridLayout(10, 10));
		grid.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		control1 = new JPanel();
		control2 = new JPanel();
		control2.setLayout(new GridLayout(0, 1));
		player = new JLabel("Player name : osama");
		senzuBeans = new JLabel("Current senzu beans : 0");
		dragonBalls = new JLabel("current dragon balls : 0");
		activeFighter = new JLabel("current ative fighter: Goku");
		level = new JLabel("current level : 1");
		// addKeyListener((KeyListener) listener);
		swithchFighter = new JLabel("Switch the active fighter!");
		assigAttackLabel = new JLabel(
				"Assign a new attack for the active fighter");
		gridSlotts = new JLabel[10][10];
		fighters = new JComboBox<String>();

		// fighters.addItem(((Control) listener).getGame().getPlayer()
		// .getActiveFighter().getName());
		//
		// for (int i = 0; i < ((Control) listener).getGame().getPlayer()
		// .getFighters().size() - 1; i++) {
		// if (!(((Control) listener).getGame().getPlayer().getFighters()
		// .get(i).equals(((Control) listener).getGame().getPlayer()
		// .getActiveFighter()))) {
		// fighters.addItem(((Control) listener).getGame().getPlayer()
		// .getFighters().get(i).getName());
		//
		// }
		// }
		switchButton = new JButton("Switch");
		superAttacks = new JComboBox<String>();
		oldSuper = new JComboBox<String>();
		oldSuper.addItem("Choose an old super attack to replace!");
		oldUltimate = new JComboBox<String>();
		oldUltimate.addItem("Choose an old ultimate attack to replace!");
		ultimateAttacks = new JComboBox<String>();
		assignSuperAttack = new JButton("Assign Super Attack!");
		assignUltimateAttack = new JButton("Assign Ultimate Attack!");
		createFighter = new JLabel("Create a new active fighter");
		race = new JComboBox<String>();
		race.addItem("Majin");
		race.addItem("Frieza");
		race.addItem("Saiyan");
		race.addItem("Namekian");
		race.addItem("Earthling");
		create = new JButton("Create!");
		xp = new JLabel();
		targetXp = new JLabel();
		fighterName = new JTextField();
		enterName = new JLabel("Enter the fighter's name!");
		upgrade = new JButton("Upgrade!");
		save = new JButton("Save!");
		moveUp = new JButton("Move up!");
		moveDown = new JButton("Move down!");
		moveRight = new JButton("Move right!");
		moveLeft = new JButton("Move left!");
		// JLabel a = new JLabel();
		// a.setOpaque(false);
		// control1.add(a);
		// JLabel b = new JLabel();
		// b.setOpaque(false);
		// control1.add(b);
		// JLabel c = new JLabel();
		// c.setOpaque(false);
		// control1.add(c);
		// c.setLayout(new BorderLayout());
		// c.add(new JButton("move up!"), BorderLayout.CENTER);
		// c.add(new JButton("move down!"), BorderLayout.SOUTH);
		// c.add(new JButton("move up!"), BorderLayout.EAST);
		// c.add(new JButton("move up!"), BorderLayout.WEST);
		// c.setSize(new Dimension(getWidth(), 200));
		// b.add(new JComboBox<String>());
		// grid.setBackground(Color.blue);
		// control1.setBackground(Color.GREEN);
		gridy();
		controly1();
		controly2();
		grid.setVisible(true);
		control1.setVisible(true);
		control2.setVisible(true);
		grid.setOpaque(false);
		control1.setOpaque(false);
		control2.setOpaque(false);

		// l.
		add(grid, BorderLayout.CENTER);
		// l.
		add(control2, BorderLayout.EAST);
		add(control1, BorderLayout.SOUTH);

		setVisible(false);
		validate();
	}

	public void gridy() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				JLabel y = new JLabel();
				y.setBorder(BorderFactory.createLineBorder(Color.black));
				// y.setSize(100, 100);
				y.setOpaque(false);
				grid.add(y);
				gridSlotts[i][j] = y;
			}
		}
		ImageIcon bb = new ImageIcon("Untitled.png");
		gridSlotts[9][9].setIcon(bb);
	}

	public void controly1() {
		control1.add(moveUp);
		control1.add(moveDown);
		control1.add(moveRight);
		control1.add(moveLeft);
		control1.add(save);
		control1.add(upgrade);

	}

	public void controly2() {

		control2.add(player);
		control2.add(senzuBeans);
		control2.add(dragonBalls);
		control2.add(activeFighter);
		control2.add(level);
		control2.add(xp);
		control2.add(targetXp);
		control2.add(swithchFighter);
		control2.add(fighters);
		control2.add(switchButton);
		control2.add(assigAttackLabel);
		control2.add(superAttacks);
		control2.add(oldSuper);
		control2.add(assignSuperAttack);
		control2.add(ultimateAttacks);
		control2.add(oldUltimate);
		control2.add(assignUltimateAttack);
		control2.add(createFighter);
		control2.add(enterName);
		control2.add(fighterName);
		control2.add(race);
		control2.add(create);
	}

	public void update() {

		fighters.setFocusable(false);
		switchButton.setFocusable(false);
		superAttacks.setFocusable(false);
		ultimateAttacks.setFocusable(false);
		oldSuper.setFocusable(false);
		oldUltimate.setFocusable(false);
		assignSuperAttack.setFocusable(false);
		assignUltimateAttack.setFocusable(false);
		createFighter.setFocusable(false);
		race.setFocusable(false);
		create.setFocusable(false);
		fighterName.setFocusable(true);
		enterName.setFocusable(false);
		upgrade.setFocusable(false);
		save.setFocusable(false);
		moveUp.setFocusable(false);
		moveDown.setFocusable(false);
		moveRight.setFocusable(false);
		moveLeft.setFocusable(false);

		setFocusable(true);
		xp.setText("Current XP : "+((Control) listener).getGame().getPlayer().getActiveFighter().getXp());
		targetXp.setText("Next level at XP : "+((Control) listener).getGame().getPlayer().getActiveFighter().getTargetXp());
		player.setText("Player's name: "
				+ ((Control) listener).getGame().getPlayer().getName());
		senzuBeans.setText("Senzu Beans : "
				+ (((Control) listener).getGame().getPlayer().getSenzuBeans()));
		dragonBalls
				.setText("Dragon Balls : "
						+ (((Control) listener).getGame().getPlayer()
								.getDragonBalls()));
		activeFighter.setText("Current active fighter : "
				+ (((Control) listener).getGame().getPlayer()
						.getActiveFighter().getName()));
		level.setText("Level : "
				+ (((Control) listener).getGame().getPlayer()
						.getActiveFighter().getLevel()));
		for (int i = 0; i < gridSlotts.length; i++) {
			for (int j = 0; j < gridSlotts.length; j++) {
				if (((Control) listener).getGame().getWorld().getVisited()[i][j]) {
					gridSlotts[i][j].setIcon(new ImageIcon("visited.jpg"));
				} else {
					gridSlotts[i][j].setIcon(new ImageIcon("non_visited.jpg"));
				}
			}
		}
		gridSlotts[0][0].setIcon(new ImageIcon("BOSS.png"));
		ImageIcon x;
		if (((Control) listener).getGame().getPlayer().getActiveFighter() instanceof Saiyan)
			x = new ImageIcon("Saiyan_small.png");
		else if (((Control) listener).getGame().getPlayer().getActiveFighter() instanceof Namekian)
			x = new ImageIcon("Namekian_small.png");
		else if (((Control) listener).getGame().getPlayer().getActiveFighter() instanceof Majin)
			x = new ImageIcon("Majin_small.png");
		else if (((Control) listener).getGame().getPlayer().getActiveFighter() instanceof Frieza)
			x = new ImageIcon("Frieza_small.png");
		else
			x = new ImageIcon("Earthling_small.png");
		gridSlotts[((Control) listener).getGame().getWorld().getPlayerRow()][((Control) listener)
				.getGame().getWorld().getPlayerColumn()].setIcon(x);

		oldSuper.removeAllItems();
		oldSuper.addItem("Choose an old super attack to replace!");
		for (int i = 0; i < ((Control) listener).getGame().getPlayer()
				.getActiveFighter().getSuperAttacks().size(); i++) {
			if (!exist(oldSuper, ((Control) listener).getGame().getPlayer()
					.getActiveFighter().getSuperAttacks().get(i).getName()))
				oldSuper.addItem(((Control) listener).getGame().getPlayer()
						.getActiveFighter().getSuperAttacks().get(i).getName());
		}
		oldUltimate.removeAllItems();
		oldUltimate.addItem("Choose an old ultimate attack to replace!");
		for (int i = 0; i < ((Control) listener).getGame().getPlayer()
				.getActiveFighter().getUltimateAttacks().size(); i++) {
			if (!exist(oldUltimate, ((Control) listener).getGame().getPlayer()
					.getActiveFighter().getUltimateAttacks().get(i).getName()))
				oldUltimate.addItem(((Control) listener).getGame().getPlayer()
						.getActiveFighter().getUltimateAttacks().get(i)
						.getName());
		}
		superAttacks.removeAllItems();
		for (int i = 0; i < ((Control) listener).getGame().getPlayer()
				.getSuperAttacks().size(); i++) {
			if (!exist(superAttacks, ((Control) listener).getGame().getPlayer()
					.getSuperAttacks().get(i).getName()))
				superAttacks.addItem(((Control) listener).getGame().getPlayer()
						.getSuperAttacks().get(i).getName());
		}
		ultimateAttacks.removeAllItems();
		for (int i = 0; i < ((Control) listener).getGame().getPlayer()
				.getUltimateAttacks().size(); i++) {
			if (!exist(ultimateAttacks, ((Control) listener).getGame()
					.getPlayer().getUltimateAttacks().get(i).getName()))
				ultimateAttacks.addItem(((Control) listener).getGame()
						.getPlayer().getUltimateAttacks().get(i).getName());
		}

		fighters.removeAllItems();
		if (!exist(fighters, ((Control) listener).getGame().getPlayer()
				.getActiveFighter().getName())) {
			fighters.addItem(((Control) listener).getGame().getPlayer()
					.getActiveFighter().getName());
		}
		for (int i = 0; i < ((Control) listener).getGame().getPlayer()
				.getFighters().size(); i++) {
			if (!(((Control) listener).getGame().getPlayer().getFighters()
					.get(i).equals(((Control) listener).getGame().getPlayer()
					.getActiveFighter()))) {
				if (!exist(fighters, ((Control) listener).getGame().getPlayer()
						.getFighters().get(i).getName())) {
					fighters.addItem(((Control) listener).getGame().getPlayer()
							.getFighters().get(i).getName());
				}
			}
		}

	}

	public boolean exist(JComboBox combo, String name) {
		for (int i = 0; i < combo.getItemCount(); i++) {
			if (combo.getItemAt(i).equals(name))
				return true;
		}
		return false;
	}

	public JPanel getGrid() {
		return grid;
	}

	public void setGrid(JPanel grid) {
		this.grid = grid;
	}

	public JPanel getcontrol1() {
		return control1;
	}

	public void setcontrol1(JPanel control1) {
		this.control1 = control1;
	}

	public JComboBox<String> getSuperAttacks() {
		return superAttacks;
	}

	public void setSuperAttacks(JComboBox<String> superAttacks) {
		this.superAttacks = superAttacks;
	}

	public JComboBox<String> getOldSuper() {
		return oldSuper;
	}

	public void setOldSuper(JComboBox<String> oldSuper) {
		this.oldSuper = oldSuper;
	}

	public JPanel getControl1() {
		return control1;
	}

	public void setControl1(JPanel control1) {
		this.control1 = control1;
	}

	public JPanel getControl2() {
		return control2;
	}

	public void setControl2(JPanel control2) {
		this.control2 = control2;
	}

	public JLabel[][] getGridSlotts() {
		return gridSlotts;
	}

	public void setGridSlotts(JLabel[][] gridSlotts) {
		this.gridSlotts = gridSlotts;
	}

	public JLabel getPlayer() {
		return player;
	}

	public void setPlayer(JLabel player) {
		this.player = player;
	}

	public JLabel getSenzuBeans() {
		return senzuBeans;
	}

	public void setSenzuBeans(JLabel senzuBeans) {
		this.senzuBeans = senzuBeans;
	}

	public JLabel getDragonBalls() {
		return dragonBalls;
	}

	public void setDragonBalls(JLabel dragonBalls) {
		this.dragonBalls = dragonBalls;
	}

	public JLabel getActiveFighter() {
		return activeFighter;
	}

	public void setActiveFighter(JLabel activeFighter) {
		this.activeFighter = activeFighter;
	}

	public JLabel getLevel() {
		return level;
	}

	public void setLevel(JLabel level) {
		this.level = level;
	}

	public JLabel getSwithchFighter() {
		return swithchFighter;
	}

	public void setSwithchFighter(JLabel swithchFighter) {
		this.swithchFighter = swithchFighter;
	}

	public JLabel getassigAttackLabel() {
		return assigAttackLabel;
	}

	public void setassigAttackLabel(JLabel assigAttackLabel) {
		this.assigAttackLabel = assigAttackLabel;
	}

	public JComboBox<String> getFighters() {
		return fighters;
	}

	public void setFighters(JComboBox<String> fighters) {
		this.fighters = fighters;
	}

	public JButton getSwitchButton() {
		return switchButton;
	}

	public void setSwitchButton(JButton switchButton) {
		this.switchButton = switchButton;
	}

	public JComboBox<String> getsuperAttacks() {
		return superAttacks;
	}

	public void setsuperAttacks(JComboBox<String> superAttacks) {
		this.superAttacks = superAttacks;
	}

	public JButton getAssignSuperAttack() {
		return assignSuperAttack;
	}

	public void setAssignSuperAttack(JButton assignSuperAttack) {
		this.assignSuperAttack = assignSuperAttack;
	}

	public JLabel getCreateFighter() {
		return createFighter;
	}

	public void setCreateFighter(JLabel createFighter) {
		this.createFighter = createFighter;
	}

	public JComboBox<String> getRace() {
		return race;
	}

	public void setRace(JComboBox<String> race) {
		this.race = race;
	}

	public JButton getCreate() {
		return create;
	}

	public void setCreate(JButton create) {
		this.create = create;
	}

	public JTextField getFighterName() {
		return fighterName;
	}

	public void setFighterName(JTextField fighterName) {
		this.fighterName = fighterName;
	}

	public JLabel getEnterName() {
		return enterName;
	}

	public void setEnterName(JLabel enterName) {
		this.enterName = enterName;
	}

	public JButton getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(JButton upgrade) {
		this.upgrade = upgrade;
	}

	public JButton getSave() {
		return save;
	}

	public void setSave(JButton save) {
		this.save = save;
	}

	public JButton getMoveUp() {
		return moveUp;
	}

	public void setMoveUp(JButton moveUp) {
		this.moveUp = moveUp;
	}

	public JButton getMoveDown() {
		return moveDown;
	}

	public void setMoveDown(JButton moveDown) {
		this.moveDown = moveDown;
	}

	public JButton getMoveRight() {
		return moveRight;
	}

	public void setMoveRight(JButton moveRight) {
		this.moveRight = moveRight;
	}

	public JButton getMoveLeft() {
		return moveLeft;
	}

	public void setMoveLeft(JButton moveLeft) {
		this.moveLeft = moveLeft;
	}

	public ActionListener getListener() {
		return listener;
	}

	public void setListener(ActionListener listener) {
		this.listener = listener;
	}

	public JComboBox<String> getoldSuper() {
		return oldSuper;
	}

	public void setoldSuper(JComboBox<String> oldSuper) {
		this.oldSuper = oldSuper;
	}

	public JComboBox<String> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public void setUltimateAttacks(JComboBox<String> ultimateAttacks) {
		this.ultimateAttacks = ultimateAttacks;
	}

	public JComboBox<String> getOldUltimate() {
		return oldUltimate;
	}

	public void setOldUltimate(JComboBox<String> oldUltimate) {
		this.oldUltimate = oldUltimate;
	}

	public JButton getAssignUltimateAttack() {
		return assignUltimateAttack;
	}

	public void setAssignUltimateAttack(JButton assignUltimateAttack) {
		this.assignUltimateAttack = assignUltimateAttack;
	}

	public KeyListener getListenerer() {
		return listenerer;
	}

	public void setListenerer(KeyListener listenerer) {
		this.listenerer = listenerer;
	}

	public JLabel getXp() {
		return xp;
	}

	public void setXp(JLabel xp) {
		this.xp = xp;
	}

	public JLabel getTargetXp() {
		return targetXp;
	}

	public void setTargetXp(JLabel targetXp) {
		this.targetXp = targetXp;
	}

}
