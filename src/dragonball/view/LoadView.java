package dragonball.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoadView extends JFrame {
	private transient JFileChooser fc;
	
	
	public JFileChooser getFc() {
		return fc;
	}


	public void setFc(JFileChooser fc) {
		this.fc = fc;
	}
	
	public LoadView()
	{
		fc = new JFileChooser();
		setTitle("Load your saved game");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		add(fc);
		pack();
		setResizable(false);
	}

	
	public static void main(String[] args) {
		LoadView m = new LoadView();
	}
}
