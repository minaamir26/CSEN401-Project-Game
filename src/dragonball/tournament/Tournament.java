package dragonball.tournament;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import dragonball.tournament.battle.BattleEvent;
import dragonball.tournament.controller.TournamentControl;
import dragonball.tournament.model.attack.Attack;
import dragonball.tournament.model.attack.MaximumCharge;
import dragonball.tournament.model.attack.SuperAttack;
import dragonball.tournament.model.attack.SuperSaiyan;
import dragonball.tournament.model.attack.UltimateAttack;
import dragonball.tournament.model.character.fighter.Fighter;
import dragonball.tournament.model.character.fighter.NonPlayableFighter;
import dragonball.tournament.model.exceptions.MissingFieldException;
import dragonball.tournament.model.exceptions.NotEnoughKiException;
import dragonball.tournament.model.exceptions.UnknownAttackTypeException;
import dragonball.tournament.rounds.Round;

public class Tournament {
	
	private ArrayList<NonPlayableFighter> foes;
	private ArrayList<Attack> attacks;
	private ArrayList<Fighter> players;
	private Round round16;
	TournamentControl listener;

	public TournamentControl getListener() {
		return listener;
	}


	public void setListener(TournamentControl listener) {
		this.listener = listener;
	}


	public Tournament (ArrayList<Fighter> arr) throws MissingFieldException, UnknownAttackTypeException
	{
		players = arr;
		int remaining = 16-arr.size();
		foes = new ArrayList<NonPlayableFighter>();
		attacks = new ArrayList<Attack>();
		try {
			loadAttacks("Database-Attacks.csv");
		} catch (dragonball.tournament.model.exceptions.InvalidFormatException e) {

			loadAttacks("Database-Attacks-aux.csv");
		}
		
		try {
			loadFoes("Database-Foes-Range1.csv");
		} catch (MissingFieldException e) {
			loadFoes("Database-Foes-aux.csv");
		}
		
		for(int i=0;i<remaining;i++)
		{
			int x= (int)(Math.random()*foes.size());
			arr.add(foes.get(x));
		}
		round16 = new Round(this , 16);
	}
	
	
	public ArrayList<NonPlayableFighter> getFoes() {
		return foes;
	}


	public void setFoes(ArrayList<NonPlayableFighter> foes) {
		this.foes = foes;
	}


	public ArrayList<Attack> getAttacks() {
		return attacks;
	}


	public void setAttacks(ArrayList<Attack> attacks) {
		this.attacks = attacks;
	}


	public Round getRound16() {
		return round16;
	}


	public void setRound16(Round round16) {
		this.round16 = round16;
	}



	private String[][] loadCSV(String filePath) {
		ArrayList<String[]> lines = new ArrayList<>();

		BufferedReader reader = null;
		String line = null;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			while ((line = reader.readLine()) != null) {
				lines.add(line.split(","));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return lines.toArray(new String[][] {});
	}

	public void loadFoes(String filePath) throws MissingFieldException {
		String[][] lines = loadCSV(filePath);

		for (int i = 0; i < lines.length; i += 3) {

			// if number of fields is less than expected.
			if (lines[i].length < 8)
				throw new MissingFieldException("File: " + filePath
						+ ", Line: " + (i + 1) + ", Expected: 8, Missing: "
						+ (8 - lines[i].length), filePath, i + 1,
						8 - lines[i].length);

			String name = lines[i][0];
			int level = Integer.parseInt(lines[i][1]);
			int maxHealthPoints = Integer.parseInt(lines[i][2]);
			int blastDamage = Integer.parseInt(lines[i][3]);
			int physicalDamage = Integer.parseInt(lines[i][4]);
			int maxKi = Integer.parseInt(lines[i][5]);
			int maxStamina = Integer.parseInt(lines[i][6]);
			boolean strong = Boolean.parseBoolean(lines[i][7]);
			ArrayList<SuperAttack> superAttacks = new ArrayList<>();
			ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<>();

			for (int j = 0; j < lines[i + 1].length; j++) {
				String attackName = lines[i + 1][j];
				for (Attack attack : attacks) {
					if (attack instanceof SuperAttack
							&& attack.getName().equalsIgnoreCase(attackName)) {
						superAttacks.add((SuperAttack) attack);
						break;
					}
				}
			}

			for (int j = 0; j < lines[i + 2].length; j++) {
				String attackName = lines[i + 2][j];
				for (Attack attack : attacks) {
					if (attack instanceof UltimateAttack
							&& attack.getName().equalsIgnoreCase(attackName)) {
						ultimateAttacks.add((UltimateAttack) attack);
						break;
					}
				}
			}

			NonPlayableFighter foe = new NonPlayableFighter(name, level,
					maxHealthPoints, blastDamage, physicalDamage, maxKi,
					maxStamina, strong, superAttacks, ultimateAttacks);
			if (strong) {
				foes.add(foe);
			} else {
				foes.add(foe);
			}
		}
	}

	public void loadAttacks(String filePath) throws MissingFieldException,
	UnknownAttackTypeException {
		String[][] lines = loadCSV(filePath);

		for (int i = 0; i < lines.length; i++) {

			// if number of fields is less than expected.
			if (lines[i].length < 3)
				throw new MissingFieldException("File: " + filePath
						+ ", Line: " + (i + 1) + ", Expected: 3, Missing: "
						+ (3 - lines[i].length), filePath, i + 1,
						3 - lines[i].length);

			Attack attack = null;
			String attackType = lines[i][0];
			String name = lines[i][1];
			int damage = Integer.parseInt(lines[i][2]);

			if (attackType.equalsIgnoreCase("SA")) {
				attack = new SuperAttack(name, damage);
			} else if (attackType.equalsIgnoreCase("UA")) {
				attack = new UltimateAttack(name, damage);
			} else if (attackType.equalsIgnoreCase("MC")) {
				attack = new MaximumCharge();
			} else if (attackType.equalsIgnoreCase("SS")) {
				attack = new SuperSaiyan();
			} else {
				// If the attack type given is not one of the four defined types.
				throw new UnknownAttackTypeException("File: " + filePath
						+ ", Line: " + (i + 1) + ", Attack Type: " + attackType
						+ " is undefined.", filePath, i + 1, attackType);
			}

			if (attack != null) {
				attacks.add(attack);
			}
		}
	}

	
	public static void main(String[] args) throws MissingFieldException, UnknownAttackTypeException {
		Tournament t = new Tournament(new ArrayList<Fighter>());
		System.out.println(t.foes.size());
	}


	public ArrayList<Fighter> getPlayers() {
		return players;
	}


	public void setPlayers(ArrayList<Fighter> players) {
		this.players = players;
	}
	public void onBattleEvent(BattleEvent e) throws NotEnoughKiException {
		/*Battle b = (Battle)(e.getSource());
		if (e.getType() == BattleEventType.ENDED) {
			//If I won
			if (e.getWinner() == b.getMe()) {
				
			
			} else if (e.getWinner() == foe) {
				
				} catch (Exception exp) {
					// do nothing
				}
			}

		}*/
		notifyOnBattleEvent(e);
		
	}


	private void notifyOnBattleEvent(BattleEvent e) throws NotEnoughKiException {
		listener.onBattleEvent(e);
		
	}

}
