package dragonball.model.cell;

import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.exceptions.NotEnoughKiException;

public class FoeCell extends Cell {
	private NonPlayableFighter foe;

	public FoeCell(NonPlayableFighter foe) {
		this.foe = foe;
	}

	public NonPlayableFighter getFoe() {
		return foe;
	}

	@Override
	public void onStep() throws NotEnoughKiException {
		notifyOnFoeEncountered(foe);
	}

	@Override
	public String toString() {
		if (foe.isStrong()) {
			return "[b]";
		} else {
			return "[w]";
		}
	}
}
