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
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.platformer.game.graphics.BufferedImageLoader;
import com.platformer.game.sounds.SoundsLoader;

public class MainMenu {

	
private JFrame mainMenuFrame;
private Buttons[] buttons = new Buttons[7];
private BufferedImage bg_image = new BufferedImageLoader().loadImage("/BG.png");
private final JPanel centralPanel = new JPanel(null)
{
	private static final long serialVersionUID = -3068810529307571296L;

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(new Color(100,225,250));
		g.drawImage(bg_image.getSubimage(0, bg_image.getHeight() - 600, 500, 600), 0, 0, null);
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
private AchievementsWindow achievementsWindow;
private CollectiblesWindow collectiblesWindow;
private HallOfFame hallOfFame;
private Achievements achievements;

public MainMenu(MainClass mainClass, HallOfFame hallOfFame, Achievements achievements)
{
	this.mainClass = mainClass;
	this.hallOfFame = hallOfFame;
	this.achievements = achievements;
	
	mainMenuFrame = new JFrame("TEQUILA PLATFORMER");
	mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainMenuFrame.setSize(500, 600);
	mainMenuFrame.setResizable(false);
	mainMenuFrame.setLocationRelativeTo(null);
	mainMenuFrame.setLayout(null);
	mainMenuFrame.setIconImage(loader.loadImage("/programIcon.png"));

	buttons[0] = new Buttons("GRAJ W GRÊ");
	buttons[0].setBounds(100, 20, 290, 60);
	
	buttons[1] = new Buttons("JAK GRAÆ");
	buttons[1].setBounds(100, 100, 290, 60);
	
	buttons[2] = new Buttons("NAJLEPSZE WYNIKI");
	buttons[2].setBounds(100, 180, 290, 60);
	
	buttons[3] = new Buttons("OSI¥GNIÊCIA");
	buttons[3].setBounds(100, 260, 290, 60);
	
	buttons[4] = new Buttons("ZNAJDKI");
	buttons[4].setBounds(100, 340, 290, 60);
	
	buttons[5] = new Buttons("O GRZE");
	buttons[5].setBounds(100, 420, 290, 60);
	
	buttons[6] = new Buttons("ZAKOÑCZ");
	buttons[6].setBounds(100, 500, 290, 60);
	
	mainMenuFrame.addKeyListener(keyListener);

	centralPanel.setSize(500, 600);
	mainMenuFrame.add(centralPanel);
	

	buttons[0].setActionCommand("START");
	buttons[1].setActionCommand("HOWTO");
	buttons[2].setActionCommand("HALLOFFAME");
	buttons[3].setActionCommand("ACHIEVEMENTS");
	buttons[4].setActionCommand("COLLECTIBLES");
	buttons[5].setActionCommand("CREDITS");
	buttons[6].setActionCommand("EXIT");
	
	menuSound1 = new SoundsLoader("/menusound1.wav");
	menuSound2 = new SoundsLoader("/menusound2.wav");
	menuSound1.setVolume(-25f);
	menuSound2.setVolume(-25f);
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
		if (e.getComponent() == buttons[5])
		{
			if (selected != 5)
			{
				deselectMenu(selected);
				selected = 5;
				selectMenu(selected);	
			}
		}
		if (e.getComponent() == buttons[6])
		{
			if (selected != 6)
			{
				deselectMenu(selected);
				selected = 6;
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
	hallOfFameWindow = new HallOfFameWindow(mainMenuFrame, hallOfFame);
	hallOfFameWindow.setVisible(true);
}

private void showAchievementsWindow()
{
	achievementsWindow = new AchievementsWindow(mainMenuFrame, achievements);
	achievementsWindow.setVisible(true);
}

private void showCollectiblesWindow()
{
	collectiblesWindow = new CollectiblesWindow(mainMenuFrame);
	collectiblesWindow.setVisible(true);
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
				//mainClass.gameStart();
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
				showAchievementsWindow();
			}
			if (selected == 4)
			{
				showCollectiblesWindow();
			}
			if (selected == 5)
			{
				showCreditsWindow();
			}
			if (selected == 6)
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
			//mainClass.gameStart();
		}
		
		if (e.getActionCommand().equalsIgnoreCase("EXIT"))
		{
			exitGame();
		}
		
		if (e.getActionCommand().equalsIgnoreCase("HALLOFFAME"))
		{
			showHallOfFameWindow();
		}
		
		if (e.getActionCommand().equalsIgnoreCase("ACHIEVEMENTS"))
		{
			showAchievementsWindow();
		}
		
		if (e.getActionCommand().equalsIgnoreCase("COLLECTIBLES"))
		{
			showCollectiblesWindow();
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