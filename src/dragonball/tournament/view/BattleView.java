package dragonball.tournament.view;

import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import dragonball.tournament.controller.TournamentControl;
import dragonball.tournament.model.attack.MaximumCharge;
import dragonball.tournament.model.attack.SuperAttack;
import dragonball.tournament.model.attack.SuperSaiyan;
import dragonball.tournament.model.attack.UltimateAttack;
import dragonball.tournament.model.character.fighter.*;
import dragonball.tournament.model.exceptions.MissingFieldException;
import dragonball.tournament.model.exceptions.UnknownAttackTypeException;

public class BattleView extends JFrame {
	TournamentControl listener;
	JPanel fighterOnePanel ;
	JPanel fighterTwoPanel ;
	JPanel playerOnePanel;
	JPanel playerTwoPanel;
	JPanel actionPanel;
	JPanel logPanel;
	JButton attack;
	//JButton use;
	JButton block;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	JLabel label_3;
	JLabel label_4;
	JLabel label_5;
	JLabel label_6;
	JLabel label_7;
	JLabel gif;
	JLabel photoLabel;
	JTextArea log;
	JLabel photoLabel2;

	JComboBox combo1;
	JComboBox combo2;

	public JPanel getLogPanel() {
		return logPanel;
	}

	public void setLogPanel(JPanel logPanel) {
		this.logPanel = logPanel;
	}

	public JTextArea getLog() {
		return log;
	}

	public void setLog(JTextArea log) {
		this.log = log;
	}

	public JButton getBlock() {
		return block;
	}

	public JComboBox getCombo1() {
		return combo1;
	}

	public JComboBox getCombo2() {
		return combo2;
	}

	public JButton getAttack() {
		return attack;
	}

	public TournamentControl getListener() {
		return listener;
	}

	public void setListener(TournamentControl listener) {
		this.listener = listener;
	}

	public BattleView(TournamentControl c)
	{
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit ?", "WARNING !",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (n == JOptionPane.YES_OPTION)
					System.exit(1);
			}

		});

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setIconImage((new ImageIcon("dragon ball.png")).getImage());
		logPanel = new JPanel();
		setListener(c);
		setTitle("Battle");
		setContentPane(new JLabel (new ImageIcon("tournament back.png")));
		//setting panels
		playerOnePanel = new JPanel();
		playerOnePanel.setBounds(183, 6, 300, 190);  			 //    x,y,width,height
		playerOnePanel.setLayout(new BoxLayout(playerOnePanel, BoxLayout.PAGE_AXIS));
		playerOnePanel.setOpaque(false);
		
		logPanel.setBounds(310, 6, 400, 210);
		log = new JTextArea(8, 30);
		log.setSize(400, 210);
		log.setText("-->The Battle will start now !");
		log.setEditable(false);
		logPanel.setOpaque(false);
		JScrollPane scrollArea = new JScrollPane(log);
		scrollArea.setSize(400, 210);
		logPanel.add(scrollArea);
		
		playerTwoPanel = new JPanel();
		playerTwoPanel.setBounds(910,10,300,210);
		playerTwoPanel.setOpaque(false);
		playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.PAGE_AXIS));


		fighterOnePanel = new JPanel();
		fighterOnePanel.setBounds(27, 170, 260, 380);
		fighterOnePanel.setOpaque(false);


		fighterTwoPanel = new JPanel();
		fighterTwoPanel.setBounds(700, 190, 260, 350);
		fighterTwoPanel.setOpaque(false);



		label3 = new JLabel(((Fighter)(listener.getBattle().getMe())).getName()+"   " );
		label3.setFont(new Font("Calibri(Body)", Font.BOLD, 21));
		label3.setForeground(Color.RED);
		playerOnePanel.add(label3);
		label7 = new JLabel("" + ((Fighter)(listener.getBattle().getMe())).getLevel());
		label7.setFont(new Font("Calibri(Body)", Font.BOLD, 21));
		label7.setForeground(Color.RED);
		playerOnePanel.add(label7);
		label4 = new JLabel("" + ((Fighter)(listener.getBattle().getMe())).getHealthPoints() +"/" +((Fighter)(listener.getBattle().getMe())).getMaxHealthPoints() );
		label4.setFont(new Font("Calibri(Body)", Font.BOLD, 21));
		label4.setForeground(Color.RED);
		playerOnePanel.add(label4);
		label5 = new JLabel("" + ((Fighter)(listener.getBattle().getMe())).getStamina()+ "/" + ((Fighter)(listener.getBattle().getMe())).getMaxStamina() );
		label5.setFont(new Font("Calibri(Body)", Font.BOLD, 21));
		label5.setForeground(Color.RED);
		playerOnePanel.add(label5);
		label6 = new JLabel("" + ((Fighter)(listener.getBattle().getMe())).getKi() +"/" + ((Fighter)(listener.getBattle().getMe())).getMaxKi());
		label6.setFont(new Font("Calibri(Body)", Font.BOLD, 21));
		label6.setForeground(Color.RED);
		playerOnePanel.add(label6);

		gif = new JLabel(new ImageIcon("giphy.gif"));
		gif.setBounds(500,10,200,200);



		label_3 = new JLabel(((Fighter)(listener.getBattle().getFoe())).getName());
		label_3.setFont(new Font("Calibri(Body)", Font.BOLD, 18));
		label_3.setForeground(Color.RED);
		playerTwoPanel.add(label_3);
		label_7 = new JLabel("" + ((Fighter)(listener.getBattle().getFoe())).getLevel());
		label_7.setFont(new Font("Calibri(Body)", Font.BOLD, 21));
		label_7.setForeground(Color.RED);
		playerTwoPanel.add(label_7);

		label_4 = new JLabel("" + ((Fighter)(listener.getBattle().getFoe())).getHealthPoints() +"/" + ((Fighter)(listener.getBattle().getFoe())).getMaxHealthPoints());
		label_4.setFont(new Font("Calibri(Body)", Font.BOLD, 21));
		label_4.setForeground(Color.RED);
		playerTwoPanel.add(label_4);
		label_5 = new JLabel("" + ((Fighter)(listener.getBattle().getFoe())).getStamina()+ "/" + ((Fighter)(listener.getBattle().getFoe())).getMaxStamina() );
		label_5.setFont(new Font("Calibri(Body)", Font.BOLD, 21));
		label_5.setForeground(Color.RED);
		playerTwoPanel.add(label_5);
		label_6 = new JLabel("" + ((Fighter)(listener.getBattle().getFoe())).getKi() +"/" +((Fighter)(listener.getBattle().getFoe())).getMaxKi() );
		label_6.setFont(new Font("Calibri(Body)", Font.BOLD, 21));
		label_6.setForeground(Color.RED);
		playerTwoPanel.add(label_6);

		if(listener.getBattle().getFoe() instanceof NonPlayableFighter)
			fighterTwoPanel.add(new JLabel(new ImageIcon("" + ((int)(Math.random()*12)) +".png")));
		else
		{
			String photo="";
			if(listener.getBattle().getFoe() instanceof Namekian)
				photo = "Namekian.png";
			else
				if(listener.getBattle().getFoe() instanceof Saiyan)
					if(((Saiyan)(listener.getBattle().getFoe())).isTransformed())
						photo = "Super_Saiyan.png";
					else
						photo = "Saiyan.png";
				else
					if(listener.getBattle().getFoe() instanceof Frieza)
						photo = "Frieza.png";
					else
						if(listener.getBattle().getFoe() instanceof Majin)
							photo = "Majin.png";
						else
							photo = "Earthling.png";
			photoLabel2 = new JLabel(new ImageIcon(photo));
			fighterTwoPanel.add(photoLabel2);

		}
		//((Saiyan)(listener.getBattle().getMe())).setTransformed(true);

		String photo="";
		if(listener.getBattle().getMe() instanceof Namekian)
			photo = "Namekian.png";
		else
			if(listener.getBattle().getMe() instanceof Saiyan)
				if(((Saiyan)(listener.getBattle().getMe())).isTransformed())
					photo = "Super_Saiyan.png";
				else
					photo = "Saiyan.png";
			else
				if(listener.getBattle().getMe() instanceof Frieza)
					photo = "Frieza.png";
				else
					if(listener.getBattle().getMe() instanceof Majin)
						photo = "Majin.png";
					else
						photo = "Earthling.png";
		photoLabel = new JLabel(new ImageIcon(photo));
		fighterOnePanel.add(photoLabel);

		createCascadedWindow();

		attack = new JButton("Attack");
		//attack.setBounds(330, 585, 100, 20);
		//use = new JButton("Use");
		block = new JButton("Block");

		actionPanel.add(attack);
		actionPanel.add(block);
		//actionPanel.add(use);

		add(playerOnePanel);
		add(logPanel);
		add(playerTwoPanel);
		add(fighterOnePanel);
		add(fighterTwoPanel);
		add(actionPanel);
		//add(gif);

		pack();
		setResizable(false);
		setVisible(false);
	}

	public void createCascadedWindow()
	{		
		actionPanel = new JPanel();
		actionPanel.setOpaque(false);
		actionPanel.setBounds(6, 570, 500, 100);
		actionPanel.setLayout(new FlowLayout());
		combo1 = new JComboBox();
		loadCombo1();
		combo1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				loadCombo2((String) combo1.getSelectedItem());
			}

		});

		combo2 = new JComboBox();
		loadCombo2((String) combo1.getSelectedItem());

		actionPanel.add(combo1);
		actionPanel.add(combo2);
		actionPanel.setVisible(true);



	}

	public void loadCombo1() {
		combo1.removeAllItems();
		combo1.addItem("Physical Attack");
		combo1.addItem("Super Attack");
		combo1.addItem("Ultimate Attack");
	}

	public void loadCombo2(String selectionOfCombo1) {
		combo2.removeAllItems();
		if (selectionOfCombo1.equals("Ultimate Attack"))
		{
			ArrayList<UltimateAttack> ultimates = ((Fighter)listener.getBattle().getAttacker()).getUltimateAttacks();
			if(listener.getBattle().getAttacker() instanceof Saiyan)
				ultimates.add(new SuperSaiyan());
			for(int i = 0;i<ultimates.size();i++)
			{
				if(!exist(combo2,ultimates.get(i).getName()))	
					combo2.addItem(ultimates.get(i).getName());
			}
		}
		if (selectionOfCombo1.equals("Super Attack")) 
		{
			ArrayList<SuperAttack> supers = ((Fighter)listener.getBattle().getAttacker()).getSuperAttacks();
			for(int i = 0;i<supers.size();i++)
			{
				if(!exist(combo2,supers.get(i).getName()))	
					combo2.addItem(supers.get(i).getName());
			}
		}
	}

	public boolean exist(JComboBox combo , String name)
	{
		for(int i=0;i<combo.getItemCount();i++)
		{
			if(combo.getItemAt(i).equals(name))
				return true;
		}
		return false;
	}

	public void update()
	{

		label3.setText(((Fighter)(listener.getBattle().getMe())).getName()+"   " );
		label4 .setText("" + ((Fighter)(listener.getBattle().getMe())).getHealthPoints() +"/" +((Fighter)(listener.getBattle().getMe())).getMaxHealthPoints() );
		label5 .setText("" + ((Fighter)(listener.getBattle().getMe())).getStamina()+ "/" + ((Fighter)(listener.getBattle().getMe())).getMaxStamina() );
		label6.setText("" + ((Fighter)(listener.getBattle().getMe())).getKi() +"/" + ((Fighter)(listener.getBattle().getMe())).getMaxKi());
		label_3 .setText(((Fighter)(listener.getBattle().getFoe())).getName());
		label_4 .setText("" + ((Fighter)(listener.getBattle().getFoe())).getHealthPoints() +"/" + ((Fighter)(listener.getBattle().getFoe())).getMaxHealthPoints());
		label_5 .setText("" + ((Fighter)(listener.getBattle().getFoe())).getStamina()+ "/" + ((Fighter)(listener.getBattle().getFoe())).getMaxStamina() );
		label_6 .setText("" + ((Fighter)(listener.getBattle().getFoe())).getKi() +"/" +((Fighter)(listener.getBattle().getFoe())).getMaxKi() );
		label7 = new JLabel("" + ((Fighter)(listener.getBattle().getMe())).getLevel());
		label_7 = new JLabel("" + ((Fighter)(listener.getBattle().getFoe())).getLevel());		
		if(listener.getBattle().getMe()  instanceof Saiyan)
			if(((Saiyan) listener.getBattle().getMe()).isTransformed())
			{
				photoLabel.setIcon(new ImageIcon("Super_Saiyan.png"));
				//fighterOnePanel.add(new JLabel(new ImageIcon("Super_Saiyan.png")));
			}
			else
			{
				fighterOnePanel = new JPanel();
				photoLabel.setIcon(new ImageIcon("Saiyan.png"));
				//fighterOnePanel.add(new JLabel(new ImageIcon("Saiyan.png")));
			}
		
		
		
		if(listener.getBattle().getFoe()  instanceof Saiyan)
			if(((Saiyan) listener.getBattle().getFoe()).isTransformed())
			{
				photoLabel2.setIcon(new ImageIcon("Super_Saiyan.png"));
			}
			else
			{
				fighterTwoPanel = new JPanel();
				photoLabel2.setIcon(new ImageIcon("Saiyan.png"));
			}
		
		validate();
	}

	
}