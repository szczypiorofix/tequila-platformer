package com.platformer.game.main;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
private MenuMouseListener menuMouseListener = new MenuMouseListener();
private MainClass mainClass;
private BufferedImageLoader loader = new BufferedImageLoader();
private LineBorder yellowBorder = new LineBorder(Color.YELLOW, 2, true);
private LineBorder blueBorder = new LineBorder(Color.BLUE, 2, true);



public MainMenu(MainClass mainClass)
{
	this.mainClass = mainClass;
	mainMenuFrame = new JFrame("TEQUILA PLATFORMER");
	mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainMenuFrame.setSize(500, 500);
	mainMenuFrame.setResizable(false);
	mainMenuFrame.setLocationRelativeTo(null);
	mainMenuFrame.setLayout(null);
	mainMenuFrame.setIconImage(loader.loadImage("/programIcon.png"));

	startButton = new JButton("Start Game");
	exitButton = new JButton("Exit Game");
	startButton.setBounds(160, 100, 170, 60);
	exitButton.setBounds(160, 200, 170, 60);

	startButton.setOpaque(false);
	startButton.setContentAreaFilled(false);
	startButton.setFocusable(false);
	startButton.setBorder(yellowBorder);
	exitButton.setOpaque(false);
	exitButton.setContentAreaFilled(false);
	exitButton.setFocusable(false);
	exitButton.setBorder(yellowBorder);

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
	startButton.addMouseListener(menuMouseListener);
	startButton.setActionCommand("START");
	exitButton.addActionListener(mainMenuListener);
	exitButton.addMouseListener(menuMouseListener);
	exitButton.setActionCommand("EXIT");
	
	new Music();
}

public void showMenu(boolean b)
{
	mainMenuFrame.setVisible(b);
}

public class MenuMouseListener implements MouseListener
{

	@Override
	public void mouseClicked(MouseEvent e) {		
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getComponent() == startButton)
		{
			startButton.setBorder(blueBorder);
		}
		if (e.getComponent() == exitButton)
		{
			exitButton.setBorder(blueBorder);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getComponent() == startButton)
		{
			startButton.setBorder(yellowBorder);
		}
		if (e.getComponent() == exitButton)
		{
			exitButton.setBorder(yellowBorder);
		}
	}
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