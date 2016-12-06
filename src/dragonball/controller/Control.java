package dragonball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.view.BattleView;
import dragonball.view.DragonView;
import dragonball.view.LoadView;
import dragonball.view.SaveView;
import dragonball.view.UpgradeView;
import dragonball.view.WelcomeView;
import dragonball.view.WelcomeView2;
import dragonball.view.WorldView;

@SuppressWarnings("serial")
public class Control implements GameListener, ActionListener, KeyListener {
	private Game game;
	private Battle battle;
	private WelcomeView welcomeView;
	private LoadView loadView;
	private BattleView battleView;
	private WorldView worldView;
	private SaveView saveView;
	private DragonView dragonView;
	private Dragon dragon;
	private UpgradeView upgradeView;

	private static int meLevel;

	public static int getMeLevel() {
		return meLevel;
	}

	public Control() throws MissingFieldException, UnknownAttackTypeException {
		game = new Game();
		// game.getPlayer().setDragonBalls(6);
		// game.getPlayer().getUltimateAttacks().add(new SuperSaiyan());

		game.setListener(this);
		welcomeView = new WelcomeView();
		welcomeView.getEnter().addActionListener(this);
		welcomeView.getLoad().addActionListener(this);
		generateNewWorld();
		// for (int i = 0; i < game.getWorld().getMap().length; i++) {
		// for (int k = 0; k < game.getWorld().getMap().length; k++) {
		//
		// System.out.print(game.getWorld().getMap()[i][k].toString());
		// }
		// System.out.println();
		// }
	}

	public void generateNewWorld() {
		worldView = new WorldView();
		worldView.getMoveUp().addActionListener(this);
		worldView.addKeyListener(this);
		worldView.getMoveRight().addActionListener(this);
		worldView.getMoveDown().addActionListener(this);
		worldView.getMoveLeft().addActionListener(this);
		worldView.getSwitchButton().addActionListener(this);
		worldView.getAssignUltimateAttack().addActionListener(this);
		worldView.getAssignSuperAttack().addActionListener(this);
		worldView.getCreate().addActionListener(this);
		worldView.getSave().addActionListener(this);
		worldView.getUpgrade().addActionListener(this);
		worldView.setListener(this);
	}

	public WorldView getWorldView() {
		return worldView;
	}

	public void setWorldView(WorldView worldView) {
		this.worldView = worldView;
	}

	public static void setMeLevel(int l) {
		meLevel = l;
	}

	public Battle getBattle() {
		return battle;
	}

	public Game getGame() {
		return game;
	}

	public WelcomeView getWelcomeView() {
		return welcomeView;
	}

	public LoadView getLoadView() {
		return loadView;
	}

	public BattleView getBattleView() {
		return battleView;
	}

	public String doublee(String s) {
		String x = "";
		for (int i = 0; i < s.length(); i++) {
			x = x + s.charAt(i);
			if (s.charAt(i) == '\\')
				x += s.charAt(i);
		}
		return x;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JFileChooser) {
			JFileChooser theFileChooser = (JFileChooser) e.getSource();
			String command = e.getActionCommand();
			if (theFileChooser.getFileSelectionMode() != JFileChooser.FILES_AND_DIRECTORIES) {
				if (command.equals(JFileChooser.APPROVE_SELECTION)) {
					File selectedFile = theFileChooser.getSelectedFile();
					String s = (selectedFile.getParent() + "\\" + selectedFile
							.getName());
					try {
						// game = new Game();
						game.load(s);
						game.setListener(this);
						worldView.update();
						loadView.setVisible(false);
						worldView.setVisible(true);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(welcomeView,
								"Please choose a valid file.", "WARNING !",
								JOptionPane.WARNING_MESSAGE);
					}
				} else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
					loadView.setVisible(false);
					welcomeView.setVisible(true);
				}
			} else {
				// System.out.println(true);
				if (command.equals(JFileChooser.APPROVE_SELECTION)) {

					File f = theFileChooser.getSelectedFile();
					String s = (f.getPath());
					// s = doublee(s);
					try {
						game.save(s);
						saveView.setVisible(false);
						worldView.setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				} else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
					saveView.setVisible(false);
					worldView.setVisible(true);
				}
			}
		} else {
			JButton button = (JButton) e.getSource();
			switch (button.getText()) {

			//
			case "Done!": {
				String ss = "";
				// for (int i = 0; i < 2; i++) {
				// if (dragonView.getOptions().getSelectedItem()
				// .equals(dragon.getWishes()[i].toString())) {
				// ss += dragon.getWishes()[i].toString();
				// game.getPlayer().chooseWish(dragon.getWishes()[i]);
				// }
				// }
				if (dragonView.getOptions().getSelectedItem()
						.equals("Senzu Beans")) {
					ss = "Congratulations You have been granted :"
							+ dragon.getSenzuBeans() + " senzu beans";
					game.getPlayer().chooseWish(dragon.getWishes()[0]);
				}
				if (dragonView.getOptions().getSelectedItem()
						.equals("Ability Points")) {
					ss = "Congratulations You have been granted :"
							+ dragon.getAbilityPoints() + " ability points";
					game.getPlayer().chooseWish(dragon.getWishes()[1]);
				}
				if (dragonView.getOptions().getSelectedItem()
						.equals("Unlock new random super attack")) {
					DragonWish granted = dragon.getWishes()[2];
					ss = "Congratulations You have been granted a new random super attack : "
							+ granted.getSuperAttack().getName();
					game.getPlayer().chooseWish(granted);
				}
				if (dragonView.getOptions().getSelectedItem()
						.equals("Unlock new random ultimate attack")) {
					DragonWish granted = dragon.getWishes()[3];
					ss = "Congratulations You have been granted a new random ultimate attack : "
							+ granted.getUltimateAttack().getName();
					game.getPlayer().chooseWish(granted);
				}

				JOptionPane.showMessageDialog(dragonView, ss, "Notification !",
						JOptionPane.PLAIN_MESSAGE);

				worldView.update();
				dragonView.setVisible(false);
				worldView.setVisible(true);

			}
				break;

			case "Enter": {
				String playerName, race, fighterName;
				playerName = welcomeView.getArea().getText();
				fighterName = welcomeView.getArea2().getText();
				race = (String) (welcomeView.getCombo().getSelectedItem());
				// --------------------------------------------------------------------------------------------------------------------
				// ----------------------------------------------------------------------------------------
				if (playerName.length() == 0 || fighterName.length() == 0) {
					JOptionPane
							.showMessageDialog(
									welcomeView,
									"You should enter the player name and the fighter name.",
									"WARNING !", JOptionPane.WARNING_MESSAGE);
					return;
				}
				game.getPlayer().setName(playerName);
				game.getPlayer().createFighter(race.charAt(0), fighterName);

				worldView.update();

				worldView.getFighters().addItem(
						game.getPlayer().getFighters().get(0).getName());
				worldView.setVisible(true);
				welcomeView.setVisible(false);
			}
				break;

			case "Load": {
				loadView = new LoadView();
				loadView.getFc().addActionListener(this);
				welcomeView.setVisible(false);
				loadView.setVisible(true);
			}
				break;

			case "Attack":
				try {
					String type1 = (String) battleView.getCombo1()
							.getSelectedItem();
					String type2 = (String) battleView.getCombo2()
							.getSelectedItem();

					if (type1.equals("Physical Attack"))
						battle.attack(new PhysicalAttack());
					else {
						if (type1.equals("Super Attack")) {
							SuperAttack attack = (SuperAttack) search(
									game.getAttacks(), type2);
							if (attack == null) {
								JOptionPane.showMessageDialog(battleView,
										"You should choose a valid attack.",
										"WARNING !",
										JOptionPane.WARNING_MESSAGE);
								break;
							}
							battle.attack(attack);
						} else {

							UltimateAttack attack = (UltimateAttack) search2(
									game.getAttacks(), type2);
							if (attack == null) {
								JOptionPane.showMessageDialog(battleView,
										"You should choose a valid attack.",
										"WARNING !",
										JOptionPane.WARNING_MESSAGE);
								break;
							}
							battle.attack(attack);
						}
					}
					// battleView.update();
				} catch (NotEnoughKiException e1) {
					JOptionPane.showMessageDialog(battleView,
							"You should have Ki bars to perform this attack.",
							"WARNING !", JOptionPane.WARNING_MESSAGE);
					break;
				}
				break;
			case "Block": {
				try {
					battle.block();
				} catch (NotEnoughKiException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				break;
			case "Use": {
				try {
					battle.use(game.getPlayer(), Collectible.SENZU_BEAN);
				} catch (NotEnoughSenzuBeansException | NotEnoughKiException e1) {

					JOptionPane.showMessageDialog(battleView,
							"You do not have enough Senzu Beans.", "WARNING !",
							JOptionPane.WARNING_MESSAGE);

				}
			}
				break;
			case "Move up!":
				try {
					// Icon x = (worldView.getGridSlotts()[game.getWorld()
					// .getPlayerRow()][game.getWorld().getPlayerColumn()])
					// .getIcon();
					// worldView.getGridSlotts()[game.getWorld().getPlayerRow()][game
					// .getWorld().getPlayerColumn()].setIcon(null);
					game.getWorld().moveUp();
					// worldView.getGridSlotts()[game.getWorld().getPlayerRow()][game
					// .getWorld().getPlayerColumn()].setIcon(x);
					worldView.getFighterName().setFocusable(false);
					worldView.update();
				} catch (MapIndexOutOfBoundsException n) {
					worldView.getFighterName().setFocusable(false);
				} catch (NotEnoughKiException e1) {
					worldView.getFighterName().setFocusable(false);
				}
				break;
			case "Move down!":
				try {
					// Icon x = (worldView.getGridSlotts()[game.getWorld()
					// .getPlayerRow()][game.getWorld().getPlayerColumn()])
					// .getIcon();
					// worldView.getGridSlotts()[game.getWorld().getPlayerRow()][game
					// .getWorld().getPlayerColumn()].setIcon(null);
					game.getWorld().moveDown();
					// worldView.getGridSlotts()[game.getWorld().getPlayerRow()][game
					// .getWorld().getPlayerColumn()].setIcon(x);
					worldView.getFighterName().setFocusable(false);
					worldView.update();
				} catch (MapIndexOutOfBoundsException n) {
					worldView.getFighterName().setFocusable(false);
				} catch (NotEnoughKiException e1) {
					worldView.getFighterName().setFocusable(false);
				}
				break;
			case "Move right!":
				try {
					// Icon x = (worldView.getGridSlotts()[game.getWorld()
					// .getPlayerRow()][game.getWorld().getPlayerColumn()])
					// .getIcon();
					// worldView.getGridSlotts()[game.getWorld().getPlayerRow()][game
					// .getWorld().getPlayerColumn()].setIcon(null);
					game.getWorld().moveRight();
					// worldView.getGridSlotts()[game.getWorld().getPlayerRow()][game
					// .getWorld().getPlayerColumn()].setIcon(x);
					worldView.getFighterName().setFocusable(false);
					worldView.update();
				} catch (MapIndexOutOfBoundsException n) {
					worldView.getFighterName().setFocusable(false);
				} catch (NotEnoughKiException e1) {
					worldView.getFighterName().setFocusable(false);
				}
				break;
			case "Move left!":
				try {
					// Icon x = (worldView.getGridSlotts()[game.getWorld()
					// .getPlayerRow()][game.getWorld().getPlayerColumn()])
					// .getIcon();
					// worldView.getGridSlotts()[game.getWorld().getPlayerRow()][game
					// .getWorld().getPlayerColumn()].setIcon(null);
					game.getWorld().moveLeft();
					// worldView.getGridSlotts()[game.getWorld().getPlayerRow()][game
					// .getWorld().getPlayerColumn()].setIcon(x);
					worldView.getFighterName().setFocusable(false);
					worldView.update();
				} catch (MapIndexOutOfBoundsException n) {
					worldView.getFighterName().setFocusable(false);

				} catch (NotEnoughKiException e1) {
					// TODO Auto-generated catch block
					worldView.getFighterName().setFocusable(false);
					e1.printStackTrace();
				}
				break;

			case "Switch": {
				PlayableFighter temp = null;
				String temp1 = (String) (worldView.getFighters()
						.getSelectedItem());
				for (int i = 0; i < game.getPlayer().getFighters().size(); i++) {

					if (temp1.equals(game.getPlayer().getFighters().get(i)
							.getName())) {
						temp = game.getPlayer().getFighters().get(i);
						break;
					}
				}
				this.game.getPlayer().setActiveFighter(temp);
				worldView.getFighterName().setFocusable(false);
				worldView.update();
			}
				break;
			case "Assign Super Attack!": {
				try {
					if (worldView.getoldSuper().getSelectedItem()
							.equals("Choose an old super attack to replace!")) {
						for (int i = 0; i < game.getPlayer().getSuperAttacks()
								.size(); i++) {
							if (worldView
									.getSuperAttacks()
									.getSelectedItem()
									.equals(game.getPlayer().getSuperAttacks()
											.get(i).getName())) {
								game.getPlayer().assignAttack(
										game.getPlayer().getActiveFighter(),
										game.getPlayer().getSuperAttacks()
												.get(i), null);
								break;
							}
						}

					} else {
						SuperAttack x = null;
						for (int i = 0; i < game.getPlayer().getActiveFighter()
								.getSuperAttacks().size(); i++) {
							if (worldView
									.getOldSuper()
									.getSelectedItem()
									.equals(game.getPlayer().getActiveFighter()
											.getSuperAttacks().get(i).getName())) {
								x = game.getPlayer().getActiveFighter()
										.getSuperAttacks().get(i);
								break;
							}
						}
						for (int i = 0; i < game.getPlayer().getSuperAttacks()
								.size(); i++) {
							if (worldView
									.getSuperAttacks()
									.getSelectedItem()
									.equals(game.getPlayer().getSuperAttacks()
											.get(i).getName())) {
								game.getPlayer().assignAttack(
										game.getPlayer().getActiveFighter(),
										game.getPlayer().getSuperAttacks()
												.get(i), x);
								break;
							}
						}

					}
					worldView.getFighterName().setFocusable(false);
				} catch (DuplicateAttackException exc) {
					worldView.getFighterName().setFocusable(false);
					JOptionPane
							.showMessageDialog(
									worldView,
									"You already have this attack assigned to the current fighter :)",
									"Notification", JOptionPane.PLAIN_MESSAGE);
				} catch (MaximumAttacksLearnedException e1) {
					worldView.getFighterName().setFocusable(false);
					JOptionPane
							.showMessageDialog(
									worldView,
									"You can not assign this attack, please choose a one to replace it",
									"Notification", JOptionPane.PLAIN_MESSAGE);

				} catch (NotASaiyanException e1) {
					worldView.getFighterName().setFocusable(false);
					JOptionPane
							.showMessageDialog(
									worldView,
									"This attack is only assigned to Saiyan Fighters , you are not a Saiyan!",
									"Notification", JOptionPane.PLAIN_MESSAGE);
				}

				worldView.update();
			}
				break;
			case "Assign Ultimate Attack!": {
				try {
					if (worldView
							.getOldUltimate()
							.getSelectedItem()
							.equals("Choose an old ultimate attack to replace!")) {
						for (int i = 0; i < game.getPlayer()
								.getUltimateAttacks().size(); i++) {
							if (worldView
									.getUltimateAttacks()
									.getSelectedItem()
									.equals(game.getPlayer()
											.getUltimateAttacks().get(i)
											.getName())) {
								game.getPlayer().assignAttack(
										game.getPlayer().getActiveFighter(),
										game.getPlayer().getUltimateAttacks()
												.get(i), null);
								break;
							}
						}

					} else {
						UltimateAttack x = null;
						for (int i = 0; i < game.getPlayer().getActiveFighter()
								.getUltimateAttacks().size(); i++) {
							if (worldView
									.getOldUltimate()
									.getSelectedItem()
									.equals(game.getPlayer().getActiveFighter()
											.getUltimateAttacks().get(i)
											.getName())) {
								x = game.getPlayer().getActiveFighter()
										.getUltimateAttacks().get(i);
								break;
							}
						}
						for (int i = 0; i < game.getPlayer()
								.getUltimateAttacks().size(); i++) {
							if (worldView
									.getUltimateAttacks()
									.getSelectedItem()
									.equals(game.getPlayer()
											.getUltimateAttacks().get(i)
											.getName())) {
								game.getPlayer().assignAttack(
										game.getPlayer().getActiveFighter(),
										game.getPlayer().getUltimateAttacks()
												.get(i), x);
								break;
							}
						}

					}
					worldView.getFighterName().setFocusable(false);
				} catch (DuplicateAttackException exc) {
					worldView.getFighterName().setFocusable(false);
					JOptionPane
							.showMessageDialog(
									worldView,
									"You already have this attack assigned to the current fighter :)",
									"Notification", JOptionPane.PLAIN_MESSAGE);

				} catch (MaximumAttacksLearnedException e1) {
					worldView.getFighterName().setFocusable(false);
					JOptionPane
							.showMessageDialog(
									worldView,
									"You can not assign this attack, please choose a one to replace it",
									"Notification", JOptionPane.PLAIN_MESSAGE);
				} catch (NotASaiyanException e1) {
					worldView.getFighterName().setFocusable(false);
					JOptionPane
							.showMessageDialog(
									worldView,
									"This attack is only assigned to Saiyan Fighters , you are not a Saiyan!",
									"Notification", JOptionPane.PLAIN_MESSAGE);
				}
				worldView.getFighterName().setFocusable(false);
				worldView.update();
			}
				break;
			case "Create!": {

				if ((worldView.getFighterName()).getText().length() != 0) {

					boolean flaga = false;
					for (int i = 0; i < game.getPlayer().getFighters().size(); i++) {
						if (worldView
								.getFighterName()
								.getText()
								.equals(game.getPlayer().getFighters().get(i)
										.getName())) {
							flaga = true;
						}
					}
					if (!flaga) {
						switch (((String) (worldView.getRace()
								.getSelectedItem()))) {
						case "Majin":
							game.getPlayer().createFighter('M',
									worldView.getFighterName().getText());
							worldView.getFighters().addItem(
									worldView.getFighterName().getText());
							break;
						case "Frieza":
							game.getPlayer().createFighter('F',
									worldView.getFighterName().getText());
							worldView.getFighters().addItem(
									worldView.getFighterName().getText());
							break;
						case "Saiyan":
							game.getPlayer().createFighter('S',
									worldView.getFighterName().getText());
							worldView.getFighters().addItem(
									worldView.getFighterName().getText());
							break;
						case "Namekian":
							game.getPlayer().createFighter('N',
									worldView.getFighterName().getText());
							worldView.getFighters().addItem(
									worldView.getFighterName().getText());
							break;
						case "Earthling":
							game.getPlayer().createFighter('E',
									worldView.getFighterName().getText());
							worldView.getFighters().addItem(
									worldView.getFighterName().getText());
							break;
						}
						worldView.getFighterName().setFocusable(false);
						worldView.update();
						worldView.getFighterName().setText("");
						// TODO

					} else {
						worldView.getFighterName().setFocusable(false);
						worldView.update();
						JOptionPane
								.showMessageDialog(
										worldView,
										"You cannot create two fighters with the same name!!",
										"WARNING !", JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					worldView.getFighterName().setFocusable(false);
					worldView.update();
					JOptionPane.showMessageDialog(worldView,
							"You should enter a name :)", "WARNING !",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
				break;

			case "Save!": {
				worldView.getFighterName().setFocusable(false);
				saveView = new SaveView();
				saveView.getFc().addActionListener(this);
				worldView.setVisible(false);
				saveView.setVisible(true);
				worldView.update();
			}
				break;
			case "Upgrade!": {
				worldView.getFighterName().setFocusable(false);
				worldView.setVisible(false);
				upgradeView = new UpgradeView();
				upgradeView.setListener(this);
				upgradeView.getUpgrade().addActionListener(this);
				upgradeView.getExit().addActionListener(this);
				upgradeView.update();
				upgradeView.setVisible(true);
			}
				break;

			case "Upgrade": {
				try {
					switch (((String) (upgradeView.getOptions()
							.getSelectedItem()))) {
					case "Max health Points": {
						game.getPlayer().upgradeFighter(
								game.getPlayer().getActiveFighter(), 'H');
					}
						break;
					case "Blast damage": {
						game.getPlayer().upgradeFighter(
								game.getPlayer().getActiveFighter(), 'B');

					}
						break;
					case "Physical damage": {
						game.getPlayer().upgradeFighter(
								game.getPlayer().getActiveFighter(), 'P');

					}
						break;
					case "Max ki": {
						game.getPlayer().upgradeFighter(
								game.getPlayer().getActiveFighter(), 'K');

					}
						break;
					case "Max stamina": {
						game.getPlayer().upgradeFighter(
								game.getPlayer().getActiveFighter(), 'S');

					}
						break;

					}
					upgradeView.update();
					JOptionPane.showMessageDialog(battleView,
							"WOW !! your fighter has been upgraded !!",
							"WARNING !", JOptionPane.PLAIN_MESSAGE);

				} catch (NotEnoughAbilityPointsException exs) {
					JOptionPane.showMessageDialog(battleView,
							"You do not have enough ability points !!",
							"WARNING !", JOptionPane.PLAIN_MESSAGE);
				}
			}
				break;

			case "Continue to world mode": {
				JOptionPane.showMessageDialog(battleView,
						"You will be now redirected to the world mode !!",
						"WARNING !", JOptionPane.PLAIN_MESSAGE);
				upgradeView.update();
				worldView.update();
				upgradeView.setVisible(false);
				worldView.setVisible(true);
			}
				break;

			}
		}
	}

	public Attack search(ArrayList<Attack> list, String name) {
		try {
			if (name.equals("Maximum Charge"))
				return new MaximumCharge();
			for (int i = 0; i < list.size(); i++) {
				// System.out.println("loooooooooooooooooooooooooooooooooooop" +
				// );
				if (name.equals(list.get(i).getName()))
					return list.get(i);
			}
		} catch (NullPointerException e) {
			/*
			 * JOptionPane.showMessageDialog( battleView,
			 * "Please , choose a valid attack!", "WARNING !",
			 * JOptionPane.PLAIN_MESSAGE);
			 */
		}
		return null;
	}

	public Attack search2(ArrayList<Attack> list, String name) {
		try {
			if (name.equals("Super Saiyan"))
				return new SuperSaiyan();
			for (int i = 0; i < list.size(); i++) {
				if (name.equals(list.get(i).getName()))
					return list.get(i);
			}
		} catch (NullPointerException e) {
			// JOptionPane.showMessageDialog(battleView,
			// "Please, choose a valid attack !", "WARNING !",
			// JOptionPane.PLAIN_MESSAGE);
		}
		return null;
	}

	@Override
	public void onDragonCalled(Dragon dragon) {
		this.setDragon(dragon);
		JOptionPane
				.showMessageDialog(
						worldView,
						"You have collected 7 dragon balls, you will be redirected to the Dragon Mode",
						"Notification", JOptionPane.PLAIN_MESSAGE);
		worldView.setVisible(false);
		dragonView = new DragonView(this);
		dragonView.getDone().addActionListener(this);
		dragonView.setListener(this);
		dragonView.setVisible(true);

	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		if (collectible == Collectible.SENZU_BEAN)
			JOptionPane.showMessageDialog(worldView,
					"You have gained a Senzu Bean", "Notification",
					JOptionPane.PLAIN_MESSAGE);
		else
			JOptionPane.showMessageDialog(worldView,
					"You have gained a Dragon Ball", "Notification",
					JOptionPane.PLAIN_MESSAGE);

	}

	// a7ot addActionListener b3d ma create el view
	@Override
	public void onBattleEvent(BattleEvent e) throws NotEnoughKiException {
		if (e.getType() == BattleEventType.STARTED) {
			battle = (Battle) (e.getSource());
			battleView = new BattleView(this);
			battleView.getAttack().addActionListener(this);
			battleView.getBlock().addActionListener(this);
			battleView.getUse().addActionListener(this);

			JOptionPane.showMessageDialog(worldView,
					"Prepare Yourself for a battle ", "WARNING !",
					JOptionPane.PLAIN_MESSAGE);
			worldView.setVisible(false);
			battleView.setVisible(true);
			battleView.update();
			setMeLevel(((Fighter) battle.getMe()).getLevel());
		}
		if (e.getType() == BattleEventType.ATTACK) {
			battleView.update();
			battleView.getLog().setText(
					battleView.getLog().getText() + "\n-->"
							+ ((Fighter) battle.getAttacker()).getName()
							+ " attacked with " + battle.getRandomm());
		}

		if (e.getType() == BattleEventType.NEW_TURN) {
			battleView.getLog().setText(
					battleView.getLog().getText() + "\n-->It is "
							+ ((Fighter) battle.getAttacker()).getName()
							+ "'s turn !");

			if (battle.getAttacker() == battle.getFoe()) {

				try {
					battle.play();
				} catch (NotEnoughKiException e1) {
					battle.attack(new PhysicalAttack());
				}

				/*
				 * if(!battle.getRandomm().equals("Block"))
				 * JOptionPane.showMessageDialog(battleView,
				 * "Your opponent attacked you with " + battle.getRandomm()
				 * ,"WARNING !" , JOptionPane.PLAIN_MESSAGE); else
				 * JOptionPane.showMessageDialog(battleView,
				 * "Your opponent is blocking your next attack ","WARNING !" ,
				 * JOptionPane.PLAIN_MESSAGE);
				 */

				/*
				 * battleView.getLog().setText(battleView.getLog().getText()+
				 * "\n-->Your opponent attacked you with " +
				 * battle.getRandomm());
				 * battleView.getLog().setText(battleView.getLog().getText()+
				 * "\n-->Your opponent is blocking your next attack ");
				 */
			}
		}

		if (e.getType() == BattleEventType.USE) {
			battleView.update();
			battleView.getLog().setText(
					battleView.getLog().getText() + "\n-->"
							+ ((Fighter) battle.getAttacker()).getName()
							+ " has used a senzu bean !");
		}
		if (e.getType() == BattleEventType.BLOCK) {
			battleView.getLog().setText(
					battleView.getLog().getText() + "\n-->"
							+ ((Fighter) battle.getAttacker()).getName()
							+ " is blocking the next attack!");
		}

		if (e.getType() == BattleEventType.ENDED) {
			battleView.update();
			if (e.getWinner() == battle.getMe()) {
				if (((NonPlayableFighter) battle.getFoe()).isStrong()) {
					String s = "";
					// s += "You won the battle, ";
					s += "\nYour xp is updated, it is now "
							+ game.getPlayer().getActiveFighter().getXp();
					s += "\nYou have unlocked the following attacks : ";
					for (Attack attack : game.getGainedAttacks())
						s += attack.getName() + ",";
					if (getMeLevel() != ((Fighter) battle.getMe()).getLevel()) {
						s += "\nYou have leveled up ! you are now in level "
								+ ((Fighter) battle.getMe()).getLevel();
						s += "\nYour next target xp to level up again is: "
								+ ((PlayableFighter) battle.getMe())
										.getTargetXp();
						s += "\nYou now have "
								+ ((PlayableFighter) battle.getMe())
										.getAbilityPoints() + " ability points";
					}
					JOptionPane.showMessageDialog(battleView,
							"You have beaten the BOSS !! , a new map wll be unlocked"
									+ s, "Congratulations !!",
							JOptionPane.PLAIN_MESSAGE);
					battleView.setVisible(false);
					generateNewWorld();
					worldView.update();
					worldView.setVisible(true);
				} else {
					String s = "";
					s += "You won the battle, ";
					s += "\nYour xp is updated, it is now "
							+ game.getPlayer().getActiveFighter().getXp();
					s += "\nYou have unlocked the following attacks : ";
					for (Attack attack : game.getGainedAttacks())
						s += attack.getName() + ",";
					if (getMeLevel() != ((Fighter) battle.getMe()).getLevel()) {
						s += "\nYou have leveled up ! you are now in level "
								+ ((Fighter) battle.getMe()).getLevel();
						s += "\nYour next target xp to level up again is: "
								+ ((PlayableFighter) battle.getMe())
										.getTargetXp();
						s += "\nYou now have "
								+ ((PlayableFighter) battle.getMe())
										.getAbilityPoints() + " ability points";
					}
					JOptionPane.showMessageDialog(battleView, s,
							"Congratulations !!", JOptionPane.PLAIN_MESSAGE);
					worldView.update();
					battleView.setVisible(false);
					worldView.setVisible(true);
				}
			} else {
				JOptionPane
						.showMessageDialog(
								battleView,
								"You have lost , If you had saved the game before it will be loaded "
										+ "otherwise, a new map will be generated for you !",
								"Sorry", JOptionPane.PLAIN_MESSAGE);
				worldView.update();
				battleView.setVisible(false);
				worldView.setVisible(true);
			}
		}
	}

	public static void main(String[] args) throws MissingFieldException,
			UnknownAttackTypeException {
		Control c = new Control();
	}

	public SaveView getSaveView() {
		return saveView;
	}

	public void setSaveView(SaveView saveView) {
		this.saveView = saveView;
	}

	public Dragon getDragon() {
		return dragon;
	}

	public void setDragon(Dragon dragon) {
		this.dragon = dragon;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP: {
			try {
				worldView.getFighterName().setFocusable(false);
				game.getWorld().moveUp();
				worldView.update();
			} catch (MapIndexOutOfBoundsException n) {

			} catch (NotEnoughKiException e1) {

			}
		}
			break;
		case KeyEvent.VK_DOWN: {
			try {
				game.getWorld().moveDown();
				worldView.update();
			} catch (MapIndexOutOfBoundsException n) {

			} catch (NotEnoughKiException e1) {

			}
		}
			break;
		case KeyEvent.VK_RIGHT: {
			try {
				game.getWorld().moveRight();
				worldView.update();
			} catch (MapIndexOutOfBoundsException n) {

			} catch (NotEnoughKiException e1) {

			}
		}
			break;
		case KeyEvent.VK_LEFT: {
			try {
				game.getWorld().moveLeft();
				worldView.update();
			} catch (MapIndexOutOfBoundsException n) {

			} catch (NotEnoughKiException e1) {

			}
		}
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
