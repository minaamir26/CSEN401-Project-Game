package dragonball.tournament.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import dragonball.tournament.battle.Battle;
import dragonball.tournament.battle.BattleEvent;
import dragonball.tournament.battle.BattleEventType;
import dragonball.tournament.battle.BattleListener;
import dragonball.tournament.model.attack.Attack;
import dragonball.tournament.model.attack.MaximumCharge;
import dragonball.tournament.model.attack.PhysicalAttack;
import dragonball.tournament.model.attack.SuperAttack;
import dragonball.tournament.model.attack.SuperSaiyan;
import dragonball.tournament.model.attack.UltimateAttack;
import dragonball.tournament.model.character.fighter.*;
import dragonball.tournament.model.exceptions.MissingFieldException;
import dragonball.tournament.model.exceptions.NotEnoughKiException;
import dragonball.tournament.model.exceptions.UnknownAttackTypeException;
import dragonball.controller.FirstControl;
import dragonball.tournament.Tournament;
import dragonball.tournament.rounds.Round;
import dragonball.tournament.rounds.Round16Listener;
import dragonball.tournament.view.BattleView;
import dragonball.tournament.view.Round16View;
import dragonball.tournament.view.WelcomeView;
import dragonball.tournament.view.WelcomeView2;

public class TournamentControl implements ActionListener, Round16Listener, BattleListener {

	private WelcomeView welcomeView;
	private WelcomeView2 welcomeView2;
	private Round16View round16View;
	private Tournament tournament;
	private Battle battle;
	private BattleView battleView;
	private FirstControl firstControl;

	public FirstControl getFirstControl() {
		return firstControl;
	}

	public void setFirstControl(FirstControl firstControl) {
		this.firstControl = firstControl;
	}

	public TournamentControl(FirstControl c) {
		firstControl = c;
		welcomeView = new WelcomeView();
		welcomeView.getEnter().addActionListener(this);
	}

	public BattleView getBattleView() {
		return battleView;
	}

	public void setBattleView(BattleView battleView) {
		this.battleView = battleView;
	}

	public WelcomeView getWelcomeView() {
		return welcomeView;
	}

	public void setWelcomeView(WelcomeView welcomeView) {
		this.welcomeView = welcomeView;
	}

	public WelcomeView2 getWelcomeView2() {
		return welcomeView2;
	}

	public void setWelcomeView2(WelcomeView2 welcomeView2) {
		this.welcomeView2 = welcomeView2;
	}

	public Round16View getRound16View() {
		return round16View;
	}

	public void setRound16View(Round16View round16View) {
		this.round16View = round16View;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		switch (button.getText()) {
		case "Enter":
			int x = Integer.parseInt((String) welcomeView.getCombo().getSelectedItem());
			welcomeView2 = new WelcomeView2(x);
			welcomeView2.getButton().addActionListener(this);
			welcomeView.setVisible(false);
			welcomeView2.setVisible(true);
			break;

		case "Enter !": {
			// System.out.println("nooo");
			ArrayList<Fighter> arr = new ArrayList<Fighter>();
			for (int i = 0; i < Integer.parseInt((String) (welcomeView.getCombo().getSelectedItem())); i++) {
				if (welcomeView2.getAreas().get(i).getText().length() == 0) {
					JOptionPane.showMessageDialog(welcomeView2, "You should fill all the fields", "Warning !!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				String s = (String) welcomeView2.getCombos().get(i).getSelectedItem();
				if (s.equals("Saiyan"))
					arr.add(new Saiyan(welcomeView2.getAreas().get(i).getText()));
				if (s.equals("Namekian"))
					arr.add(new Namekian(welcomeView2.getAreas().get(i).getText()));
				if (s.equals("Earthling"))
					arr.add(new Earthling(welcomeView2.getAreas().get(i).getText()));
				if (s.equals("Frieza"))
					arr.add(new Frieza(welcomeView2.getAreas().get(i).getText()));
				if (s.equals("Majin"))
					arr.add(new Majin(welcomeView2.getAreas().get(i).getText()));

			}
			try {
				tournament = new Tournament(arr);
			} catch (MissingFieldException e1) {
				e1.printStackTrace();
			} catch (UnknownAttackTypeException e1) {
				e1.printStackTrace();
			}
			round16View = new Round16View(this);
			round16View.getButton().addActionListener(this);

			round16View.setVisible(true);
			welcomeView.setVisible(false);
			welcomeView2.setVisible(false);
		}
		break;
		case "Advance to the next Battle": {
			makeNewBattle();
		}
		break;

		case "Attack":
			try {
				String type1 = (String) battleView.getCombo1().getSelectedItem();
				String type2 = (String) battleView.getCombo2().getSelectedItem();

				// System.out.println(type1 + " " + type2);
				if (type1.equals("Physical Attack")) {
					System.out.println("tamam 1");
					battle.attack(new PhysicalAttack());
				} else {
					if (type1.equals("Super Attack")) {
						SuperAttack attack = (SuperAttack) search(tournament.getAttacks(), type2);
						if (attack == null) {
							JOptionPane.showMessageDialog(battleView, "You should choose a valid attack.", "WARNING !",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
						battle.attack(attack);
					} else {

						UltimateAttack attack = (UltimateAttack) search2(tournament.getAttacks(), type2);
						if (attack == null) {
							JOptionPane.showMessageDialog(battleView, "You should choose a valid attack.", "WARNING !",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
						battle.attack(attack);
					}
				}
			} catch (NotEnoughKiException e1) {
				JOptionPane.showMessageDialog(battleView, "You should have Ki bars to perform this attack.",
						"WARNING !", JOptionPane.WARNING_MESSAGE);
				break;
			}
			break;
		case "Block": {
			try {
				battle.block();
			} catch (NotEnoughKiException e1) {
			}
		}
		break;

		}
	}

	public void makeNewBattle() {
		while (tournament.getRound16().getCurrent() < getTournament().getRound16().getRound() / 2
				&& tournament.getRound16().getPlayer1()[tournament.getRound16()
				                                        .getCurrent()] instanceof NonPlayableFighter
				&& tournament.getRound16().getPlayer2()[tournament.getRound16()
				                                        .getCurrent()] instanceof NonPlayableFighter) {

			if (tournament.getRound16().getPlayer1()[tournament.getRound16().getCurrent()]
					.getLevel() > tournament.getRound16().getPlayer2()[tournament.getRound16().getCurrent()]
							.getLevel()) {
				tournament.getRound16().getWinners()[tournament.getRound16().getCurrent()] = tournament.getRound16()
						.getPlayer1()[tournament.getRound16().getCurrent()];
			} else if (tournament.getRound16().getPlayer1()[tournament.getRound16().getCurrent()]
					.getLevel() < tournament.getRound16().getPlayer2()[tournament.getRound16().getCurrent()]
							.getLevel()) {
				tournament.getRound16().getWinners()[tournament.getRound16().getCurrent()] = tournament.getRound16()
						.getPlayer2()[tournament.getRound16().getCurrent()];
			} else {
				int winner = (int) (Math.random() * 2);
				if (winner == 1)
					tournament.getRound16().getWinners()[tournament.getRound16().getCurrent()] = tournament.getRound16()
					.getPlayer1()[tournament.getRound16().getCurrent()];
				else
					tournament.getRound16().getWinners()[tournament.getRound16().getCurrent()] = tournament.getRound16()
					.getPlayer2()[tournament.getRound16().getCurrent()];
			}
			tournament.getRound16().setCurrent(tournament.getRound16().getCurrent() + 1);

		}
		if (tournament.getRound16().getCurrent() == getTournament().getRound16().getRound() / 2) {
			round16View.update();
			if (tournament.getRound16().checkPlayerWinner()) {
				if (getTournament().getRound16().getRound() == 2) {
					JOptionPane.showMessageDialog(getRound16View(), "Congratulations !!  You WON the tournament ! ",
							"Wohooo !", JOptionPane.PLAIN_MESSAGE);
					round16View.setVisible(false);
					getFirstControl().getFirstView().setVisible(true);
				} else {
					String s = " ";
					if (getTournament().getRound16().getRound() / 2 == 2)
						s += "FINAL !!";
					else
						s += "Round " + getTournament().getRound16().getRound() / 2;
					JOptionPane.showMessageDialog(battleView,
							"Round " + getTournament().getRound16().getRound()
							+ " has been finished , you will be redirected to" + s,
							"Notification !", JOptionPane.PLAIN_MESSAGE);

					// create next round
					tournament.setPlayers(addAll(tournament.getPlayers(), tournament.getRound16().getWinners()));
					System.out.println(tournament.getPlayers());
					tournament.setRound16(new Round(tournament, getTournament().getRound16().getRound() / 2));
					round16View.setVisible(false);
					round16View = new Round16View(this);
					round16View.getButton().addActionListener(this);
					round16View.setVisible(true);
				}

			} else {
				JOptionPane.showMessageDialog(battleView, "Sorry , none of your players has won any battle ", "Sorry !",
						JOptionPane.PLAIN_MESSAGE);
				Fighter finalWinner= null ;
				try
				{
					if(tournament.getRound16().getCurrent() !=0)
					{
						int index = (int)(Math.random()*tournament.getRound16().getCurrent());
						finalWinner = tournament.getRound16().getWinners()[index];
					}
					else
					{
						int index = (int)(Math.random()*tournament.getRound16().getPlayer1().length);
						finalWinner = tournament.getRound16().getPlayer1()[index];
					}
					String ss ="The winner of the battle is " + finalWinner.getName();


					JOptionPane.showMessageDialog(battleView, ss, "The Winner",
							JOptionPane.PLAIN_MESSAGE);
				}catch(Exception e)
				{

				}
				round16View.setVisible(false);
				getFirstControl().getFirstView().setVisible(true);
			}
		} else {
			if (!(tournament.getRound16().getPlayer1()[tournament.getRound16()
			                                           .getCurrent()] instanceof NonPlayableFighter))
				battle = new Battle(tournament.getRound16().getPlayer1()[tournament.getRound16().getCurrent()],
						tournament.getRound16().getPlayer2()[tournament.getRound16().getCurrent()]);
			else
				battle = new Battle(tournament.getRound16().getPlayer2()[tournament.getRound16().getCurrent()],
						tournament.getRound16().getPlayer1()[tournament.getRound16().getCurrent()]);
			battle.setListener(this);
			battleView = new BattleView(this);
			battleView.setListener(this);
			battleView.getAttack().addActionListener(this);
			battleView.getBlock().addActionListener(this);
			round16View.setVisible(false);
			battleView.setVisible(true);
		}
	}

	public ArrayList<Fighter> addAll(ArrayList<Fighter> arrayList, Fighter[] arr) {
		arrayList = new ArrayList<Fighter>();
		for (int i = 0; i < arr.length; i++)
			arrayList.add(arr[i]);
		return arrayList;
	}

	public Attack search(ArrayList<Attack> list, String name) {
		try {
			if (name.equals("Maximum Charge"))
				return new MaximumCharge();
			for (int i = 0; i < list.size(); i++) {
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
			/*
			 * JOptionPane.showMessageDialog( battleView,
			 * "Please, choose a valid attack !", "WARNING !",
			 * JOptionPane.PLAIN_MESSAGE);
			 */
		}
		return null;
	}

	@Override
	public void onBattleEvent(BattleEvent e) throws NotEnoughKiException {

		if (e.getType() == BattleEventType.ATTACK) {
			battleView.update();
			battleView.getLog().setText(battleView.getLog().getText() + "\n-->"
					+ ((Fighter) battle.getAttacker()).getName() + " attacked with " + battle.getRandomm());
		}
		if (e.getType() == BattleEventType.NEW_TURN) {
			battleView.getLog().setText(battleView.getLog().getText() + "\n-->It is "
					+ ((Fighter) battle.getAttacker()).getName() + "'s turn !");
			System.out.println("new turn");
			if (battle.getAttacker() instanceof NonPlayableFighter) {
				try {
					battle.play();
				} catch (NotEnoughKiException e1) {
					battle.attack(new PhysicalAttack());
				}
			} else {

				// combo2 = new JComboBox();
				// battleView.loadCombo2((String)
				// battleView.getCombo1().getSelectedItem());
				// battleView.loadCombo1();
				battleView.loadCombo2((String) battleView.getCombo1().getSelectedItem());

			}
		}

		if (e.getType() == BattleEventType.BLOCK) {
			battleView.getLog().setText(battleView.getLog().getText() + "\n-->"
					+ ((Fighter) battle.getAttacker()).getName() + " is blocking the next attack!");
		}

		if (e.getType() == BattleEventType.ENDED) {
			Fighter winner = (Fighter) e.getWinner();

			JOptionPane.showMessageDialog(battleView, winner.getName() + " has won the game !", "Battle Ended !",
					JOptionPane.PLAIN_MESSAGE);

			if (!(e.getWinner() instanceof NonPlayableFighter)) {
				Fighter loser = (Fighter) ((e.getWinner() == battle.getMe()) ? battle.getFoe() : battle.getMe());

				for (SuperAttack superAttack : ((Fighter) loser).getSuperAttacks()) {
					if (!winner.getSuperAttacks().contains(superAttack)) {
						winner.getSuperAttacks().add(superAttack);
					}
				}
				for (UltimateAttack ultimateAttack : ((Fighter) loser).getUltimateAttacks()) {
					if (!winner.getUltimateAttacks().contains(ultimateAttack)) {
						winner.getUltimateAttacks().add(ultimateAttack);
					}
				}
			}
			tournament.getRound16().getWinners()[tournament.getRound16().getCurrent()] = (Fighter) e.getWinner();
			tournament.getRound16().setCurrent(tournament.getRound16().getCurrent() + 1);
			round16View.update();
			battleView.setVisible(false);
			round16View.setVisible(true);
		}

	}

	public Battle getBattle() {
		return battle;
	}

	public void setBattle(Battle battle) {
		this.battle = battle;
	}

}
