package dragonball.tournament.model.attack;

import dragonball.tournament.battle.BattleOpponent;
import dragonball.tournament.model.character.fighter.Fighter;

public class MaximumCharge extends SuperAttack {
	public MaximumCharge() {
		super("Maximum Charge", 0);
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		return 0;
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) {
		Fighter attackerFighter = (Fighter) attacker;
		attackerFighter.setKi(attackerFighter.getKi() + 3);
	}
}
