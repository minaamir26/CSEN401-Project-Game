package dragonball.tournament.model.character.fighter;

import java.util.ArrayList;

import dragonball.tournament.model.attack.SuperAttack;
import dragonball.tournament.model.attack.UltimateAttack;
import dragonball.tournament.model.character.NonPlayableCharacter;

public class NonPlayableFighter extends Fighter implements NonPlayableCharacter {
	private boolean strong;

	public NonPlayableFighter(String name, int level, int maxHealthPoints, int blastDamage, int physicalDamage,
			int maxKi, int maxStamina, boolean strong, ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) {
		super(name, level, maxHealthPoints, blastDamage, physicalDamage, maxKi, maxStamina, superAttacks,
				ultimateAttacks);
		this.strong = strong;
	}

	public boolean isStrong() {
		return strong;
	}
}
