package dragonball.tournament.model.attack;

import dragonball.tournament.battle.BattleOpponent;
import dragonball.tournament.model.character.fighter.Saiyan;
import dragonball.tournament.model.exceptions.NotEnoughKiException;

public class SuperSaiyan extends UltimateAttack {
	public SuperSaiyan() {
		super("Super Saiyan", 0);
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		return 0;
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException {
		if (attacker instanceof Saiyan) {
			Saiyan attackerSaiyan = (Saiyan) attacker;

			// only requires 3 ki, without consuming them (override superclass behavior)
			if (attackerSaiyan.getKi() >= 3) {
				attackerSaiyan.setTransformed(true);
			}else{
				throw new NotEnoughKiException(3, attackerSaiyan.getKi());
			}
		} 
	}
}
