package dragonball.model.battle;

import java.util.EventListener;

import dragonball.model.exceptions.NotEnoughKiException;

public interface BattleListener extends EventListener {
	void onBattleEvent(BattleEvent e) throws NotEnoughKiException;
}
