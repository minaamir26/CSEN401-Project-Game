package dragonball.tournament.rounds;

import dragonball.tournament.battle.BattleEvent;
import dragonball.tournament.model.exceptions.NotEnoughKiException;

public interface Round16Listener {
	void onBattleEvent(BattleEvent e) throws NotEnoughKiException;
}
