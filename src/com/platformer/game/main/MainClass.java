package com.platformer.game.main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.platformer.game.sounds.MusicState;
import com.platformer.game.sounds.SoundsLoader;



/** G³ówna klasa uruchamiaj¹ca grê. To tutaj zainicjowane s¹ pocz¹tkowe warunki gry, ³adowane tekstury czy inne pliki
 * do pamiêci. W MainClass inicjowane jest podstawowe okno gry. Tutaj równie znajduje siê game loop oraz przygotowywane
 * do u¿ytku s¹ klasy obiekty klas HallOfFame oraz Achievements.
 * @author Piotrek
 *
 */
public class MainClass implements Runnable {


/** Obiekt HashMap<Integer, Boolean> przechowuj¹cy spis achievementów.
 * 
 */
private HashMap<Integer, Boolean> achievementsList;

/** Obiekt klasy ArrayList<HallOfFamePlayer> z zapisanymi Najlepszymi Osi¹gniêciami.
 * 
 */
private ArrayList<HallOfFamePlayer> hallOfFameList = new ArrayList<HallOfFamePlayer>(10);

/** Plik z zapisanymi Osi¹gniêcami.
 * 
 */
public static final File achievementsFile = new File("achievements.dat");

/** Plik z zapisanymi Najlepszymi Wynikami.
 * 
 */
public static final File hallOfFameFile = new File("halloffame.dat");

/** Plik konfiguracyjny do obs³ugi pada/gamepada.
 * 
 */
public static final File gamepadConfigFile = new File("input.cfg");

/** Czcionka Smokun Font.
 * 
 */
public static Font smokunFont;

/** Czcionka Texas Font.
 * 
 */
public static Font texasFont;

/** Szerokoœæ g³ównego okna gry.
 * 
 */
public static int WIDTH = 0;

/** Wysokoœæ g³ównego okna gry.
 * 
 */
public static int HEIGHT = 0;

/** Oiekt klasy Achievements.
 * @see Achievements
 */
private Achievements achievements = null;
private HallOfFame hallOfFame = null;
public static SoundsLoader jumpSound, powerUpSound, coinSound, hitSound, cactusShotSound, springJumpSound,crateHitSound, screenShotSound, menuSound1, menuSound2;
private int fps_count = 0, ticks_count = 0;
public static double amountOfTicks = 60.0;
private boolean gamepadEnabled = false;
private GameWindow gameWindow;
private MainScreen mainScreen;
private ObjectInputStream ois = null;
private ObjectOutputStream oos = null;
private boolean gameRunning;
private final InputStream SMOKUN_FONT = getClass().getResourceAsStream("/Smokum-Regular.ttf");
private final InputStream TEXAS_FONT = getClass().getResourceAsStream("/Cowboy_Hippie_Pro.otf");
private Thread gameThread;
private GameState gameState;
private MusicState musicState;




/** Konstruktor klasy g³ównej gry.
 * 
 */
public MainClass()
{
	gameInit();
}


/** Inicjuje pocz¹tekowe warunki i stany gry, np. gameWindow, MainScreen (Canvas). W tej metodzie uruchamiany jest w¹tek
 * renderowania i update'owania gry.
 * 
 */
private void gameInit()
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
	prepareHallOfFame();
	
	//mainMenu = new MainMenu(this, hallOfFame, achievements);
	
	//mainMenu.showMenu(true);
	
	musicState = MusicState.play;
	
	jumpSound = new SoundsLoader("/jump.wav");
	powerUpSound = new SoundsLoader("/powerup.wav");
	coinSound = new SoundsLoader("/coin10.wav");
	hitSound = new SoundsLoader("/hit.wav");
	cactusShotSound = new SoundsLoader("/cactusShot.wav");
	springJumpSound = new SoundsLoader("/SpringJump.wav");
	crateHitSound = new SoundsLoader("/crateHit.wav");
	screenShotSound = new SoundsLoader("/screenShotSound.wav");
	menuSound1 = new SoundsLoader("/menusound1.wav");
	menuSound2 = new SoundsLoader("/menusound2.wav");
	
	jumpSound.setVolume(-15f);
	powerUpSound.setVolume(-20f);
	coinSound.setVolume(-15f);
	hitSound.setVolume(-15f);
	cactusShotSound.setVolume(-15f);
	springJumpSound.setVolume(-15f);
	crateHitSound.setVolume(-15f);

	gameWindow = new GameWindow();
	gameState = GameState.MainMenu;
	mainScreen = new MainScreen(gameState, gameWindow, gamepadEnabled, hallOfFame, achievements);
	gameWindow.setVisible(true);
	WIDTH = mainScreen.getWidth();
	HEIGHT = mainScreen.getHeight();
		
	gameThreadStart();
}


/** Metoda uruchamiaj¹ca w¹tek renderowania i obliczania logic gry.
 * 
 */
public synchronized void gameThreadStart()
{
	if (gameRunning) return;
	gameRunning = true;
	gameThread = new Thread(this);
	gameThread.start();
}


@Override
public void run()
{	
	gameWindow.requestFocus();
	
	gameState = GameState.Game;
	
	// GAME LOOPd
    
	boolean fpsCap = false;

	long lastTime = System.nanoTime();
	amountOfTicks = 60.0;
	
	double delta = 0;
	long timer = System.currentTimeMillis();
	int updates = 0;
	int frames = 0;
	
	while(gameRunning)
	{	
		double ns = 1000000000 / amountOfTicks;
		long now = System.nanoTime();
		delta += (now - lastTime) / ns;
		lastTime = now;
		
		while(delta >= 1)
		{	
			if (mainScreen.isExit()) gameWindow.showWindow(false);  // PROGRAM EXIT
			
			if (gameState == GameState.Game)
			{
				mainScreen.tick();
				
				if (!jumpSound.isPlaying()) jumpSound.stop();
				if (!powerUpSound.isPlaying()) powerUpSound.stop();
				if (!coinSound.isPlaying()) coinSound.stop();
				if (!hitSound.isPlaying()) hitSound.stop();
				if (!cactusShotSound.isPlaying()) cactusShotSound.stop();
				if (!springJumpSound.isPlaying()) springJumpSound.stop();
				if (!crateHitSound.isPlaying()) crateHitSound.stop();
				
				if (fpsCap) mainScreen.render(60, ticks_count);
			}
			updates++;
			delta--;
		}
		
		if (gameState == GameState.Game)
		{
			if (!fpsCap) mainScreen.render(fps_count, ticks_count);
		}
		else if (gameState == GameState.Menu)
		{
			
		}
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
private void prepareHallOfFame()
{	
	if (!MainClass.hallOfFameFile.exists() && !MainClass.hallOfFameFile.isDirectory())
	{
		// POCZATKOWA INICJALIZACJA TABLICY Z HALL OF FAME;
		hallOfFameList = new ArrayList<HallOfFamePlayer>(10);
		for (int i = 0; i < 10; i++) hallOfFameList.add(new HallOfFamePlayer("<empty>", 0, 0));
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(MainClass.hallOfFameFile));
			oos.writeObject(hallOfFameList);
			oos.flush();
			oos.close();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(-1);
		}
	}
	
	try {
	ois = new ObjectInputStream(new FileInputStream(MainClass.hallOfFameFile));
	hallOfFameList = (ArrayList<HallOfFamePlayer>) ois.readObject();
	ois.close();
	}
	catch (IOException | ClassNotFoundException 
			e)
	{
		e.printStackTrace();
		System.exit(-1);
	}
	hallOfFame = new HallOfFame(hallOfFameList);
}

@SuppressWarnings("unchecked")
private void prepareAchievements()
{
	
	achievementsList = new HashMap<Integer, Boolean>(Achievements.maxAchievements);
	for (int i = 0; i < Achievements.maxAchievements; i++) achievementsList.put(i, false);
	
	if (!MainClass.achievementsFile.exists() && !MainClass.achievementsFile.isDirectory())
	{
		// POCZATKOWA INICJALIZACJA HASHMAPY Z ACHIEVEMENTAMI;
		try {
				oos = new ObjectOutputStream(new FileOutputStream((MainClass.achievementsFile)));
				oos.writeObject(achievementsList);
				oos.flush();
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
	achievementsList = (HashMap<Integer, Boolean>) ois.readObject();
	ois.close();
	}
	catch (IOException | ClassNotFoundException e)
	{
		e.printStackTrace();
		System.exit(-1);
	}

	achievements = new Achievements(achievementsList);
	achievements.setJumpCount10Complete(achievementsList.get(0));
	achievements.setJumpCount25Complete(achievementsList.get(1));
	achievements.setJumpCount50Complete(achievementsList.get(2));
	achievements.setCoinCount20Complete(achievementsList.get(3));
	achievements.setCoinCount50Complete(achievementsList.get(4));
	achievements.setCoinCount100Complete(achievementsList.get(5));
	achievements.setCoinCount150Complete(achievementsList.get(6));
	achievements.setPowerupCount3Complete(achievementsList.get(7));
	achievements.setComplete1LevelComplete(achievementsList.get(8));
	achievements.setComplete2LevelComplete(achievementsList.get(9));
	achievements.setComplete3LevelComplete(achievementsList.get(10));
	achievements.setComplete4LevelComplete(achievementsList.get(11));
	achievements.setComplete5LevelComplete(achievementsList.get(12));
	achievements.setFindAllCoinsComplete(achievementsList.get(13));
	achievements.setFindAllPowerupsComplete(achievementsList.get(14));
	achievements.setNoHarmComplete(achievementsList.get(15));
}


public static void main(String[] args) {
	new MainClass();
}
}