package dragonball.tournament.rounds;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dragonball.tournament.*;
import dragonball.tournament.battle.*;
import dragonball.tournament.model.character.fighter.Fighter;
import dragonball.tournament.model.character.fighter.NonPlayableFighter;
import dragonball.tournament.model.exceptions.NotEnoughKiException;

public class Round implements BattleListener {
	Fighter[] player1;
	Fighter[] player2;
	Fighter[] winners;
	Tournament listener;
	static int current=0;
	private int round;
	
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Round(Tournament t , int round)
	{
		current = 0;
		this.round=round;
		listener =t;
		player1 = new Fighter[round/2];
		player2 = new Fighter[round/2];
		winners = new Fighter[round/2];
		//ArrayList<Attack> attacks = listener.getAttacks();
		//ArrayList<NonPlayableFighter> foes = listener.getFoes();
		//ArrayList<Fighter> players = listener.getPlayers();

		ArrayList<Fighter> all = listener.getPlayers();
		for(int i=0;i<round/2;i++)
		{
			int index1 = (int)(Math.random()*all.size());
			player1[i] = all.get(index1);
			all.remove(index1);
			int index2 = (int)(Math.random()*all.size());
			player2[i] = all.get(index2);
			all.remove(index2);
		}
	}

	public Fighter[] getPlayer1() {
		return player1;
	}

	public void setPlayer1(Fighter[] player1) {
		this.player1 = player1;
	}

	public Fighter[] getPlayer2() {
		return player2;
	}

	public void setPlayer2(Fighter[] player2) {
		this.player2 = player2;
	}

	public Fighter[] getWinners() {
		return winners;
	}

	public void setWinners(Fighter[] winners) {
		this.winners = winners;
	}

	public Tournament getListener() {
		return listener;
	}

	public void setListener(Tournament listener) {
		this.listener = listener;
	}

	public static int getCurrent() {
		return current;
	}

	public static void setCurrent(int current) {
		Round.current = current;
	}

	@Override
	public void onBattleEvent(BattleEvent e) throws NotEnoughKiException {
		/*Battle b = (Battle)(e.getSource());
		if (e.getType() == BattleEventType.ENDED) {
			//If I won
			if (e.getWinner() == b.getMe()) {
				
			
			} else if (e.getWinner() == foe) {
				
				} catch (Exception exp) {
					// do nothing
				}
			}

		}*/
		notifyOnBattleEvent(e);
		
	}

	private void notifyOnBattleEvent(BattleEvent e) throws NotEnoughKiException {
		listener.onBattleEvent(e);
		
	}
	
	
	public boolean checkPlayerWinner()
	{
		for(int i=0;i<winners.length;i++)
		{
			if ( !(winners[i] instanceof NonPlayableFighter))
				return true;
		}
		return false;
	}
	
	

}
