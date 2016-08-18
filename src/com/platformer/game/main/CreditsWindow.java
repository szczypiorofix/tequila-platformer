package com.platformer.game.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import com.platformer.game.graphics.BufferedImageLoader;

public class CreditsWindow extends JFrame{


private static final long serialVersionUID = 3999992812968059965L;


private JPanel panel;
private JScrollPane scroll;
private BufferedImageLoader loader;
private BufferedImage bg;


public CreditsWindow(JFrame parent)
{
	super("O grze...");
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setSize(390,500);
	setLocationRelativeTo(parent);
	setResizable(false);
	loader = new BufferedImageLoader();
	bg = loader.loadImage("/OGrze.png");
	panel = new JPanel()
	{
		private static final long serialVersionUID = 2311137477487062858L;

		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(bg, 0, 0, null);
			
		}
	};
	panel.setPreferredSize(new Dimension(365, 550));
	scroll = new JScrollPane(panel);
	scroll.getVerticalScrollBar().setUnitIncrement(5);
	add(scroll);
}
	
	
}