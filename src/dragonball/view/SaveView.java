package dragonball.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SaveView extends JFrame {
	private transient JFileChooser fc;


	public JFileChooser getFc() {
		return fc;
	}


	public void setFc(JFileChooser fc) {
		this.fc = fc;
	}


	public SaveView()
	{
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES  );
		
		setTitle("Save your game");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		add(fc);
		pack();
		setResizable(false);
		setVisible(true);
	}


	public static void main(String[] args) {
		new SaveView();
	}
}
