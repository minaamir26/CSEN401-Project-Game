package dragonball.tournament.model.character.fighter;

import java.util.ArrayList;

import dragonball.tournament.model.attack.SuperAttack;
import dragonball.tournament.model.attack.UltimateAttack;

public class Namekian extends PlayableFighter {
	public Namekian(String name) {
		super(name, 1350, 0, 50, 3, 5);
	}

	public Namekian(String name, int level, int xp, int targetXp, int maxHealthPoints, int blastDamage,
			int physicalDamage, int abilityPoints, int maxKi, int maxStamina, ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) {
		super(name, level, xp, targetXp, maxHealthPoints, blastDamage, physicalDamage, abilityPoints, maxKi, maxStamina,
				superAttacks, ultimateAttacks);
	}

	@Override
	public void onAttackerTurn() {
		super.onAttackerTurn();
		setHealthPoints(getHealthPoints() + 50);
	}

	@Override
	public void onDefenderTurn() {
		super.onDefenderTurn();
		setHealthPoints(getHealthPoints() + 50);
	}
}
