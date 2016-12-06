package dragonball.tournament.model.character.fighter;

import java.util.ArrayList;

import dragonball.tournament.model.attack.SuperAttack;
import dragonball.tournament.model.attack.UltimateAttack;

public class Frieza extends PlayableFighter {
	public Frieza(String name) {
		super(name, 1100, 75, 75, 4, 4);
	}

	public Frieza(String name, int level, int xp, int targetXp, int maxHealthPoints, int blastDamage,
			int physicalDamage, int abilityPoints, int maxKi, int maxStamina, ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) {
		super(name, level, xp, targetXp, maxHealthPoints, blastDamage, physicalDamage, abilityPoints, maxKi, maxStamina,
				superAttacks, ultimateAttacks);
	}
}
