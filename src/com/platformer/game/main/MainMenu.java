package com.platformer.game.main;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import com.platformer.game.graphics.BufferedImageLoader;
import com.platformer.game.sounds.Music;
import com.platformer.game.sounds.SoundsLoader;

public class MainMenu {

private JFrame mainMenuFrame;
private Buttons[] buttons = new Buttons[4];
private final JPanel centralPanel = new JPanel(null)
{
	private static final long serialVersionUID = -3068810529307571296L;

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(new BufferedImageLoader().loadImage("/BG.png"), 0, 0, 500, 500, null);
	}
};
private MainMenuListener mainMenuListener = new MainMenuListener();
private MenuMouseListener menuMouseListener = new MenuMouseListener();
private MainClass mainClass;
private BufferedImageLoader loader = new BufferedImageLoader();
private LineBorder yellowBorder = new LineBorder(Color.YELLOW, 2, true);
private LineBorder blueBorder = new LineBorder(Color.BLUE, 2, true);
private SoundsLoader sounds;
private int selected = 0;
private MenuKeyListener keyListener = new MenuKeyListener();


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

	buttons[0] = new Buttons("PLAY GAME");
	buttons[0].setBounds(160, 100, 170, 50);
	
	buttons[1] = new Buttons("HOW TO PLAY");
	buttons[1].setBounds(160, 180, 170, 50);
	
	buttons[2] = new Buttons("CREDITS");
	buttons[2].setBounds(160, 260, 170, 50);
	
	buttons[3] = new Buttons("EXIT");
	buttons[3].setBounds(160, 340, 170, 50);
	
	mainMenuFrame.addKeyListener(keyListener);

	centralPanel.setSize(500, 500);
	mainMenuFrame.add(centralPanel);
	

	buttons[0].setActionCommand("START");
	buttons[1].setActionCommand("HOWTO");
	buttons[2].setActionCommand("CREDITS");
	buttons[3].setActionCommand("EXIT");
	
	sounds = new SoundsLoader();
	selected = 0;
	buttons[selected].setBorder(blueBorder);
	new Music();
}

public void showMenu(boolean b)
{
	mainMenuFrame.setVisible(b);
}

public class MenuMouseListener implements MouseListener
{

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getComponent() == buttons[0])
		{
			if (selected != 0)
			{
				deselectMenu(selected);
				selected = 0;
				selectMenu(selected);	
			}
		}
		if (e.getComponent() == buttons[1])
		{
			if (selected != 1)
			{
				deselectMenu(selected);
				selected = 1;
				selectMenu(selected);	
			}
		}
		if (e.getComponent() == buttons[2])
		{
			if (selected != 2)
			{
				deselectMenu(selected);
				selected = 2;
				selectMenu(selected);	
			}
		}
		if (e.getComponent() == buttons[3])
		{
			if (selected != 3)
			{
				deselectMenu(selected);
				selected = 3;
				selectMenu(selected);	
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {}
}

private void selectMenu(int s)
{
	buttons[s].setBorder(blueBorder);
	sounds.playMenuSound1();
}

private void deselectMenu(int s)
{
	buttons[s].setBorder(yellowBorder);
}

private void exitGame()
{
	sounds.playMenuSound2();
	try {
		Thread.sleep(70);
	} catch (InterruptedException e1) {
		e1.printStackTrace();
	}
	System.exit(0);
}

public class MenuKeyListener implements KeyListener
{
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if (selected == 0)
			{
				sounds.playMenuSound2();
				showMenu(false);
				mainClass.gameStart();
			}
			if (selected == 3)
			{
				exitGame();
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) exitGame();
		
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			if (selected > 0)
			{
				deselectMenu(selected);
				selected--;
				selectMenu(selected);	
			}
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if (selected < 3)
			{
				deselectMenu(selected);
				selected ++;
				selectMenu(selected);	
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}

public class Buttons extends JButton
{

private static final long serialVersionUID = 1670542167015159494L;

public Buttons(String title)
{
	super(title);
	setOpaque(false);
	setFocusable(false);
	setContentAreaFilled(false);
	setFont(MainClass.texasFont.deriveFont(12f));
	addActionListener(mainMenuListener);
	addMouseListener(menuMouseListener);
	setBorder(yellowBorder);
	centralPanel.add(this);
}
}

public class MainMenuListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("START"))
		{
			sounds.playMenuSound2();
			showMenu(false);
			mainClass.gameStart();
		}
		
		if (e.getActionCommand().equalsIgnoreCase("EXIT"))
		{
			exitGame();
		}
	}	
}
}