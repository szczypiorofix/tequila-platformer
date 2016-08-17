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
import java.util.HashMap;

import com.platformer.game.sounds.Music;



public class MainClass implements Runnable {


public static HashMap<Integer, Boolean> ac;
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
private boolean running;
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

	/**
	
	double nextTime = (double)System.nanoTime() / 1000000000.0;
    double maxTimeDiff = 0.5;
    int skippedFrames = 1;
    int maxSkippedFrames = 5;
    double delta = (1.0f / 60.0f);
    
    while(running)
    {
        double currTime = (double)System.nanoTime() / 1000000000.0;
        if((currTime - nextTime) > maxTimeDiff) nextTime = currTime;
        if(currTime >= nextTime)
        {
           nextTime += delta;
           
           mainScreen.tick();
           if (mainScreen.isExit()) gameWindow.showWindow(false);  // PROGRAM EXIT
                      
           if((currTime < nextTime) || (skippedFrames > maxSkippedFrames))
           {
        	   mainScreen.render(60, (int) (delta * 3600));
           
        	   skippedFrames = 1;
           }
           else
           {
               skippedFrames++;
           }
        }
        else
        {
            int sleepTime = (int)(1000.0 * (nextTime - currTime));
            if(sleepTime > 0)
            {
                try
                {
                    Thread.sleep(sleepTime);
                }
                catch(InterruptedException e)
                {
                	e.printStackTrace();
                	System.exit(0);
                }
            }
        }
    }
	**/
    
	boolean fpsCap = false;

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
			if (fpsCap) mainScreen.render(60, ticks_count);
			updates++;
			delta--;
		}
		
		if (!fpsCap) mainScreen.render(fps_count, ticks_count);
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

@SuppressWarnings("unchecked")
private void prepareAchievements()
{
	
	ac = new HashMap<Integer, Boolean>(Achievements.maxAchievements);
	for (int i = 0; i < Achievements.maxAchievements; i++) ac.put(i, false);
	
	if (!MainClass.achievementsFile.exists() && !MainClass.achievementsFile.isDirectory())
	{
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
	ac = (HashMap<Integer, Boolean>) ois.readObject();
	ois.close();
	}
	catch (IOException | ClassNotFoundException e)
	{
		e.printStackTrace();
		System.exit(-1);
	}

	achievements = new Achievements();
	achievements.setJumpCount10Complete(ac.get(0));
	achievements.setJumpCount25Complete(ac.get(1));
	achievements.setJumpCount50Complete(ac.get(2));
	achievements.setCoinCount20Complete(ac.get(3));
	achievements.setCoinCount50Complete(ac.get(4));
	achievements.setCoinCount100Complete(ac.get(5));
	achievements.setCoinCount150Complete(ac.get(6));
	achievements.setPowerupCount3Complete(ac.get(7));
	achievements.setComplete1LevelComplete(ac.get(8));
	achievements.setComplete2LevelComplete(ac.get(9));
	achievements.setComplete3LevelComplete(ac.get(10));
	achievements.setComplete4LevelComplete(ac.get(11));
	achievements.setComplete5LevelComplete(ac.get(12));
	achievements.setFindAllCoinsComplete(ac.get(13));
	achievements.setFindAllPowerupsComplete(ac.get(14));
	achievements.setNoHarmComplete(ac.get(15));
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