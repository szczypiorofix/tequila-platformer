package com.platformer.game.main;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.platformer.game.graphics.BufferedImageLoader;
import com.platformer.game.sounds.Music;

public class MainMenu {

private JFrame mainMenuFrame;
private JButton startButton, exitButton;
private JPanel centralPanel;
private MainMenuListener mainMenuListener = new MainMenuListener();
private MainClass mainClass;

public MainMenu(MainClass mainClass)
{
	this.mainClass = mainClass;
	mainMenuFrame = new JFrame("Texas Platformer");
	mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainMenuFrame.setSize(500, 500);
	mainMenuFrame.setResizable(false);
	mainMenuFrame.setLocationRelativeTo(null);
	mainMenuFrame.setLayout(null);
	
	startButton = new JButton("Start Game");
	exitButton = new JButton("Exit Game");
	startButton.setBounds(160, 100, 170, 60);
	exitButton.setBounds(160, 200, 170, 60);

	startButton.setOpaque(false);
	startButton.setContentAreaFilled(false);
	startButton.setFocusable(false);
	startButton.setBorder(new LineBorder(Color.YELLOW, 2, true));
	exitButton.setOpaque(false);
	exitButton.setContentAreaFilled(false);
	exitButton.setFocusable(false);
	exitButton.setBorder(new LineBorder(Color.YELLOW, 2, true));

	startButton.setFont(MainClass.texasFont.deriveFont(12f));
	exitButton.setFont(MainClass.texasFont.deriveFont(12f));
	
	centralPanel = new JPanel(null)
	{
		private static final long serialVersionUID = -3068810529307571296L;

		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(new BufferedImageLoader().loadImage("/BG.png"), 0, 0, 500, 500, null);
		}
	};
	centralPanel.setSize(500, 500);
	centralPanel.add(startButton);
	centralPanel.add(exitButton);
	mainMenuFrame.add(centralPanel);
	
	startButton.addActionListener(mainMenuListener);
	startButton.setActionCommand("START");
	exitButton.addActionListener(mainMenuListener);
	exitButton.setActionCommand("EXIT");
	
	new Music();
}

public void showMenu(boolean b)
{
	mainMenuFrame.setVisible(b);
}

public class MainMenuListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("START"))
		{
			showMenu(false);
			mainClass.gameStart();
		}
		
		if (e.getActionCommand().equalsIgnoreCase("EXIT"))
		{
			System.exit(0);
		}
	}	
}
}