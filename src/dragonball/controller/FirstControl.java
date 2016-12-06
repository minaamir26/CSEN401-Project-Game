package dragonball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.tournament.controller.TournamentControl;
import dragonball.tournament.view.FirstView;

public class FirstControl implements ActionListener {
	private FirstView firstView;
	
	
	
	public FirstView getFirstView() {
		return firstView;
	}

	public void setFirstView(FirstView firstView) {
		this.firstView = firstView;
	}

	public FirstControl()
	{
		firstView= new FirstView();
		firstView.getTournament().addActionListener(this);
		firstView.getGameMode().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		switch (button.getText()) {
		case "Go to Tourament Mode!":
			firstView.setVisible(false);
			new TournamentControl(this);
			break;
		case  "Go to World Mode!":
			try {
				firstView.setVisible(false);
				new Control();
			} catch (MissingFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnknownAttackTypeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		}
		
	}
	public static void main(String[] args) {
		new FirstControl();
	}
}
