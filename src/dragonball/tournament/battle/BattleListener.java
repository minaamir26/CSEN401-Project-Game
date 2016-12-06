package dragonball.tournament.battle;

import java.util.EventListener;

import dragonball.tournament.model.exceptions.NotEnoughKiException;

public interface BattleListener extends EventListener {
	void onBattleEvent(BattleEvent e) throws NotEnoughKiException;
}
