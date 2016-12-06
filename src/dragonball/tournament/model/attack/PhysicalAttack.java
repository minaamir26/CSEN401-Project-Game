package dragonball.tournament.model.attack;

import dragonball.tournament.battle.BattleOpponent;
import dragonball.tournament.model.character.fighter.Fighter;
import dragonball.tournament.model.exceptions.NotEnoughKiException;

public class PhysicalAttack extends Attack {
	public PhysicalAttack() {
		super("Physical Attack", 50);
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		return getDamage() + ((Fighter) attacker).getPhysicalDamage();
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException {
		super.onUse(attacker, defender, defenderBlocking);

		// increment ki by 1
		Fighter attackerFighter = (Fighter) attacker;
		attackerFighter.setKi(attackerFighter.getKi() + 1);
	}
}