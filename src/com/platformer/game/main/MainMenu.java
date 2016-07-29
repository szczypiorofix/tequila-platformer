package com.platformer.game.main;


import java.awt.Color;
import java.awt.Font;
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
import com.platformer.game.sounds.SoundsLoader;

public class MainMenu {

	
private JFrame mainMenuFrame;
private Buttons[] buttons = new Buttons[5];
private final JPanel centralPanel = new JPanel(null)
{
	private static final long serialVersionUID = -3068810529307571296L;

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(new Color(100,225,250));
		g.fillRect(0, 0, 500, 500);
		//g.drawImage(new BufferedImageLoader().loadImage("/BG.png"), 0, 0, 500, 500, null);
	}
};
private MainMenuListener mainMenuListener = new MainMenuListener();
private MenuMouseListener menuMouseListener = new MenuMouseListener();
private MainClass mainClass;
private BufferedImageLoader loader = new BufferedImageLoader();
private LineBorder yellowBorder = new LineBorder(Color.YELLOW, 3, true);
private LineBorder blueBorder = new LineBorder(Color.BLUE, 3, true);
private SoundsLoader menuSound1, menuSound2;
private int selected = 0;
private MenuKeyListener keyListener = new MenuKeyListener();
private CreditsWindow creditsWindow;
private HowToPlayWindow howToPlayWindow;
private HallOfFameWindow hallOfFameWindow;



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

	buttons[0] = new Buttons("GRAJ W GR�");
	buttons[0].setBounds(100, 60, 290, 60);
	
	buttons[1] = new Buttons("JAK GRA�");
	buttons[1].setBounds(100, 140, 290, 60);
	
	buttons[2] = new Buttons("NAJLEPSZE WYNIKI");
	buttons[2].setBounds(100, 220, 290, 60);
	
	buttons[3] = new Buttons("O GRZE");
	buttons[3].setBounds(100, 300, 290, 60);
	
	buttons[4] = new Buttons("ZAKO�CZ");
	buttons[4].setBounds(100, 380, 290, 60);
	
	mainMenuFrame.addKeyListener(keyListener);

	centralPanel.setSize(500, 500);
	mainMenuFrame.add(centralPanel);
	

	buttons[0].setActionCommand("START");
	//buttons[1].setActionCommand("HOWTO");
	//buttons[2].setActionCommand("HALLOFFAME");
	//buttons[3].setActionCommand("CREDITS");
	buttons[4].setActionCommand("EXIT");
	
	menuSound1 = new SoundsLoader("/menusound1.wav");
	menuSound2 = new SoundsLoader("/menusound2.wav");
	selected = 0;
	buttons[selected].setBorder(blueBorder);
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
		if (e.getComponent() == buttons[4])
		{
			if (selected != 4)
			{
				deselectMenu(selected);
				selected = 4;
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
	menuSound1.play();
}

private void deselectMenu(int s)
{
	buttons[s].setBorder(yellowBorder);
}

private void exitGame()
{
	menuSound2.play();
	try {
		Thread.sleep(70);
	} catch (InterruptedException e1) {
		e1.printStackTrace();
	}
	System.exit(0);
}

private void showCreditsWindow()
{
	creditsWindow = new CreditsWindow(mainMenuFrame);
	creditsWindow.setVisible(true);
}

private void showHowToPlayWindow()
{
	howToPlayWindow = new HowToPlayWindow(mainMenuFrame);
	howToPlayWindow.setVisible(true);
}

private void showHallOfFameWindow()
{
	hallOfFameWindow = new HallOfFameWindow(mainMenuFrame);
	hallOfFameWindow.setVisible(true);
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
				menuSound2.play();
				showMenu(false);
				mainClass.gameStart();
			}
			if (selected == 1)
			{
				showHowToPlayWindow();
			}
			if (selected == 2)
			{
				showHallOfFameWindow();
			}
			if (selected == 3)
			{
				showCreditsWindow();
			}
			if (selected == 4)
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
			if (selected < buttons.length-1)
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
	setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 34f));
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
			menuSound2.play();
			showMenu(false);
			mainClass.gameStart();
		}
		
		if (e.getActionCommand().equalsIgnoreCase("EXIT"))
		{
			exitGame();
		}
		
		if (e.getActionCommand().equalsIgnoreCase("HALLOFFAME"))
		{
			showHallOfFameWindow();
		}
		
		if (e.getActionCommand().equalsIgnoreCase("HOWTO"))
		{
			showHowToPlayWindow();
		}
		
		if (e.getActionCommand().equalsIgnoreCase("CREDITS"))
		{
			showCreditsWindow();
		}
	}	
}
}