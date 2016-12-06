package dragonball.model.world;

import java.util.EventListener;

import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.exceptions.NotEnoughKiException;

public interface WorldListener extends EventListener {
	void onFoeEncountered(NonPlayableFighter foe) throws NotEnoughKiException;

	void onCollectibleFound(Collectible collectible);
}
