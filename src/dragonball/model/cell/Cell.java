package dragonball.model.cell;

import java.io.Serializable;

import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.exceptions.NotEnoughKiException;

public abstract class Cell implements Serializable{
	private CellListener listener;

	public abstract void onStep() throws NotEnoughKiException;

	public void setListener(CellListener listener) {
		this.listener = listener;
	}

	protected void notifyOnFoeEncountered(NonPlayableFighter foe) throws NotEnoughKiException {
		if (listener != null) {
			listener.onFoeEncountered(foe);
		}
	}

	protected void notifyOnCollectibleFound(Collectible collectible) {
		if (listener != null) {
			listener.onCollectibleFound(collectible);
		}
	}

	public CellListener getListener() {
		return listener;
	}

	@Override
	public abstract String toString();
}
