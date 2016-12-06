package dragonball.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class CustomPanel extends JPanel {


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("battleBackground.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(myPicture, 0, 0, null);
	}
}


