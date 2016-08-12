package com.platformer.game.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.platformer.game.sounds.Music;



public class MainClass implements Runnable {

	
public static boolean[] ac;
public static final File achievementsFile = new File("achievements.dat");
public static final File gamepadConfigFile = new File("input.cfg");
public static Font smokunFont;
public static Font texasFont;
public static int WIDTH = 0, HEIGHT = 0;

private static Achievements achievements = null;

private int fps_count = 0, ticks_count = 0;
private boolean gamepadEnabled = false;
private GameWindow gameWindow;
private MainMenu mainMenu;
private MainScreen mainScreen;
private ObjectInputStream ois = null;
private ObjectOutputStream oos = null;
private boolean running = false;
private final InputStream SMOKUN_FONT = getClass().getResourceAsStream("/Smokum-Regular.ttf");  // http://www.1001freefonts.com/la_tequila.font




private final InputStream TEXAS_FONT = getClass().getResourceAsStream("/Cowboy_Hippie_Pro.otf");  // http://www.1001freefonts.com/la_tequila.font

private Thread thread;

public MainClass()
{	
	if(gamepadConfigFile.exists() && !gamepadConfigFile.isDirectory()) gamepadEnabled = true;
	
	try {
		texasFont = Font.createFont(Font.TRUETYPE_FONT, TEXAS_FONT);
		smokunFont = Font.createFont(Font.TRUETYPE_FONT, SMOKUN_FONT);
	}
	catch (FontFormatException | IOException e)
	{
		e.printStackTrace();
		System.exit(-1);
	}
	
	//achievements = new Achievements();
	
	prepareAchievements();
	
	mainMenu = new MainMenu(this);
	mainMenu.showMenu(true);
}


public void gameStart()
{
	gameWindow = new GameWindow();
	mainScreen = new MainScreen(gameWindow, gamepadEnabled);
	
	gameWindow.setVisible(true);
	WIDTH = mainScreen.getWidth();
	HEIGHT = mainScreen.getHeight();
	
	mainScreen.addElements();
	new Music();
	gameThreadStart();
}

public synchronized void gameThreadStart()
{
	if (running) return;
	running = true;
	thread = new Thread(this);
	thread.start();
}

@Override
public void run()
{	
	gameWindow.requestFocus();
	
	// GAME LOOPd

	long lastTime = System.nanoTime();
	double amountOfTicks = 60.0;
	double ns = 1000000000 / amountOfTicks;
	double delta = 0;
	long timer = System.currentTimeMillis();
	int updates = 0;
	int frames = 0;
	
	while(running)
	{	
		long now = System.nanoTime();
		delta += (now - lastTime) / ns;
		lastTime = now;
		
		while(delta >= 1)
		{	
			if (mainScreen.isExit()) gameWindow.showWindow(false);  // PROGRAM EXIT
			mainScreen.tick();
			updates++;
			delta--;
		}
		
		mainScreen.render(fps_count, ticks_count);
		frames++;
		
		if (System.currentTimeMillis() - timer > 1000)
		{
			timer += 1000;
			fps_count = frames;
			ticks_count = updates;
			frames = 0;
			updates = 0;
		}
	}
}

private void prepareAchievements()
{
	
	ac = new boolean[Achievements.maxAchievements];
	
	if(!MainClass.achievementsFile.exists() && !MainClass.achievementsFile.isDirectory())
	{
		System.out.println("Brak pliku: " +MainClass.achievementsFile.getName());
		try {
			oos = new ObjectOutputStream(new FileOutputStream((MainClass.achievementsFile)));
		    oos.writeObject(ac);
		    oos.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
				System.exit(-1);
			}
	}
	
	try {
	ois = new ObjectInputStream(new FileInputStream(MainClass.achievementsFile));
	ac = (boolean[]) ois.readObject();
	ois.close();
	}
	catch (IOException | ClassNotFoundException e)
	{
		e.printStackTrace();
		System.exit(-1);
	}
	
	achievements = new Achievements();
	achievements.setJumpCount10Complete(ac[0]);
	achievements.setJumpCount25Complete(ac[1]);
	achievements.setCoinCount20Complete(ac[2]);
	achievements.setCoinCount50Complete(ac[3]);
	achievements.setPowerupCount3Complete(ac[4]);
}

public static Achievements getAchievementsInstance()
{
	return achievements;
}

public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable()
	{
		@Override
		public void run()
		{
			new MainClass();
		}
	});
}
}