package dragonball.tournament.battle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.tournament.model.attack.Attack;
import dragonball.tournament.model.attack.PhysicalAttack;
import dragonball.tournament.model.character.fighter.Fighter;
import dragonball.tournament.model.character.fighter.PlayableFighter;
import dragonball.tournament.model.character.fighter.Saiyan;
import dragonball.tournament.model.exceptions.NotEnoughKiException;

public class Battle implements Serializable{
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private BattleListener listener;
	private static Attack randomAttack;
	private static String randomm;

	public static String getRandomm() {
		return randomm;
	}

	public static Attack getRandomAttack() {
		return randomAttack;
	}

	public Battle(BattleOpponent me, BattleOpponent foe) {
		this.me = me;
		this.foe = foe;
		this.attacker = me;

		// set current values appropriately
		Fighter meFighter = (Fighter) me;
		System.out.println(meFighter);
		meFighter.setHealthPoints(meFighter.getMaxHealthPoints());
		meFighter.setKi(0);
		meFighter.setStamina(meFighter.getMaxStamina());
		// reset a saiyan's transformation state in case it was transformed in a previous battle
		if (me instanceof Saiyan) {
			Saiyan meSaiyan = (Saiyan) me;
			meSaiyan.setTransformed(false);
		}

		Fighter foeFighter = (Fighter) foe;
		foeFighter.setHealthPoints(foeFighter.getMaxHealthPoints());
		foeFighter.setKi(0);
		foeFighter.setStamina(foeFighter.getMaxStamina());
	}

	public BattleOpponent getMe() {
		return me;
	}

	public BattleOpponent getFoe() {
		return foe;
	}

	public BattleOpponent getAttacker() {
		return attacker;
	}

	public BattleOpponent getDefender() {
		return attacker == me ? foe : me;
	}

	public boolean isMeBlocking() {
		return meBlocking;
	}

	public boolean isFoeBlocking() {
		return foeBlocking;
	}

	public ArrayList<Attack> getAssignedAttacks() {
		Fighter attackerFighter = (Fighter) attacker;

		ArrayList<Attack> attacks = new ArrayList<>();
		// make sure to include the physical attack as well
		attacks.add(new PhysicalAttack());
		attacks.addAll(attackerFighter.getSuperAttacks());
		attacks.addAll(attackerFighter.getUltimateAttacks());
		return attacks;
	}

	public void switchTurn() {
		attacker = getDefender();
	}

	public void play() throws NotEnoughKiException {
		if (new Random().nextInt(100) > 15) {
			ArrayList<Attack> attacks = getAssignedAttacks();
			randomAttack = attacks.get(new Random().nextInt(attacks.size()));
			if(randomAttack!=null)
				randomm = randomAttack.getName();
			attack(randomAttack);
		} else {
			randomm = "Block";
			block();
		}
	}

	public void endTurn() throws NotEnoughKiException {
		// reset block mode
		if (attacker == me && foeBlocking) {
			foeBlocking = false;
		} else if (attacker == foe && meBlocking) {
			meBlocking = false;
		}

		if (((Fighter) me).getHealthPoints() == 0) {
			System.out.println("foe wins");
			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ENDED, foe));
		} 
		else if (((Fighter) foe).getHealthPoints() == 0) {
			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ENDED, me));
		} 
		else 
		{
			switchTurn();

			getAttacker().onDefenderTurn();
			getDefender().onAttackerTurn();
			//System.out.println("new turn");
			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
		}
	}

	public void start() throws NotEnoughKiException {
		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.STARTED));
		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
	}

	// perform an attack and end turn
	public void attack(Attack attack) throws NotEnoughKiException {
		//System.out.println("tamam2");
		randomm =attack.getName();
		attack.onUse(attacker, getDefender(),
				(attacker == me && foeBlocking) || (attacker == foe && meBlocking));

		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ATTACK, attack));

		endTurn();
	}

	// perform a block and end turn
	public void block() throws NotEnoughKiException {
		if (attacker == me) {
			meBlocking = true;
		} else if (attacker == foe) {
			foeBlocking = true;
		}

		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.BLOCK));

		endTurn();
	}

	// use a collectible and end turn


	public void setListener(BattleListener listener) {
		this.listener = listener;
	}

	public void notifyOnBattleEvent(BattleEvent e) throws NotEnoughKiException {
		//System.out.println(listener);
		if (listener != null) {
			listener.onBattleEvent(e);
		}
	}
}
