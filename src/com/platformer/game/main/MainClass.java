package com.platformer.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

import com.platformer.game.graphics.BufferedImageLoader;
import com.platformer.game.objects.Collectibles;
import com.platformer.game.sounds.Music;
import com.platformer.game.sounds.SoundsLoader;



/** G³ówna klasa uruchamiaj¹ca grê. To tutaj zainicjowane s¹ pocz¹tkowe warunki gry, ³adowane tekstury czy inne pliki
 * do pamiêci. W MainClass inicjowane jest podstawowe okno gry. Tutaj równie znajduje siê game loop oraz przygotowywane
 * do u¿ytku s¹ klasy obiekty klas HallOfFame oraz Achievements.
 * @author Piotrek
 *
 */
public class MainClass implements Runnable {


	
public static final File achievementsFile = new File("achievements.dat");
public static final File hallOfFameFile = new File("halloffame.dat");
public static final File collectiblesFile = new File("collectibles.dat");
public static final File gamepadConfigFile = new File("input.cfg");
public static final File gameConfigFile = new File("tequila.cfg");
public static final Color fontColor = new Color(60, 0, 140);
public static final float GAME_VER = 0.64f;
public static final int BUILD = 27;

public static Font verdana14Font = new Font("Verdana", Font.BOLD, 14);
public static Font verdana18Font = new Font("Verdana", Font.BOLD, 18);
public static Font arial14Font = new Font("Arial", Font.BOLD, 14);
public static Font smokunFont;
public static Font texasFont;
public static Music music;
public static NetworkConnector nc = new NetworkConnector();
public static SoundsLoader jumpSound, powerUpSound, coinSound, hitSound, cactusShotSound, springJumpSound,crateHitSound, screenShotSound, menuSound1, menuSound2;
public static int WIDTH = 0;
public static int HEIGHT = 0;
public static boolean fpsCap;
public static Languages language;
public enum Languages { polish, english };

private final static Logger LOGGER = Logger.getLogger(MainClass.class.getName());

private final InputStream SMOKUN_FONT = getClass().getResourceAsStream("/Smokum-Regular.ttf");
private final InputStream TEXAS_FONT = getClass().getResourceAsStream("/Cowboy_Hippie_Pro.otf");
private final double amountOfTicks = 60.0;

private static boolean DEBUG_MODE;

private Thread gameThread;
private Thread musicThread;
private Achievements achievements = null;
private HallOfFame hallOfFame = null;
private GameWindow gameWindow;
private MainScreen mainScreen;
private ObjectInputStream ois = null;
private ObjectOutputStream oos = null;
private GameState gameState;
private JWindow window;
private BufferedImageLoader splashScreenLoader = new BufferedImageLoader();
private BufferedImage splashScreen;
private FileHandler fileHandler = null;
private HashMap<Integer, Boolean> achievementsList;
private ArrayList<HallOfFamePlayer> hallOfFameList = new ArrayList<HallOfFamePlayer>(30);
private Properties prop = new Properties();
private InputStream propStream;

private String languageProp;
private int[] collectiblesList = new int[Collectibles.MAX_COLLECTIBLES];
private int fps_count, ticks_count;
private boolean gamepadConfigFileEnabled = false;
private boolean gameThreadRunning;
private boolean musicThreadRunning;



public MainClass()
{
	// LOGGER
	if (DEBUG_MODE)
	{
		try {
			fileHandler = new FileHandler("platformer.log", false);
		} catch (SecurityException e1) {
			e1.printStackTrace();
			System.exit(-1);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}
		
		//LOGGER.setUseParentHandlers(false); // WYŒWIETLANIE LOGÓW W KONSOLI
		
		fileHandler.setFormatter(new SimpleFormatter());
		fileHandler.setLevel(Level.INFO);
		LOGGER.addHandler(fileHandler);
		logging(false, Level.INFO, "Uruchomienie gry w trybie DEBUG. Logger za³adowany.");
	}
	
	loadProperties();
	
	splashScreen();	
	gameInit();
}


public void loadProperties()
{	
	if(gameConfigFile.exists() && !gameConfigFile.isDirectory())
	{
		language = Languages.polish;
		
		try {
			propStream = new FileInputStream(MainClass.gameConfigFile);
			prop.load(propStream);
			languageProp = prop.getProperty("Language");
			if (languageProp.equalsIgnoreCase("polish")) language = Languages.polish;
			if (languageProp.equalsIgnoreCase("english")) language = Languages.english;

			MainClass.logging(false, Level.INFO, "Plik ustawieñ gry prawid³owo odczytany.");

		} catch (IOException ex) {
			ex.printStackTrace();
			MainClass.logging(false, Level.WARNING,  "B³¹d odczytu pliku ustawieñ gry");
			MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(ex));
		} finally {
			if (propStream != null) {
				try {
					propStream.close();
				} catch (IOException e) {
					MainClass.logging(false, Level.WARNING,  "Plik ustawieñ gry zosta³ nieprawid³owo zamkniêty!");
					MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(e));
				}
			}
		}
	}
	else {
		saveOptions();
	}	
}

public void saveOptions()
{
	prop.put("Language", language.toString());

	try {
		FileOutputStream fileOut = new FileOutputStream(gameConfigFile);
		prop.store(fileOut, "Game options");
		fileOut.close();
	}
	catch (Exception ex)
	{
		ex.printStackTrace();
	}
}

public void splashScreen()
{
	window = new JWindow();
	if (language == Languages.polish) splashScreen = splashScreenLoader.loadImage("/splashScreenPl.png");
	if (language == Languages.english) splashScreen = splashScreenLoader.loadImage("/splashScreenEng.png");
	
	JLabel label = new JLabel(new ImageIcon(splashScreen));
	window.getContentPane().add(label);
	window.setSize(splashScreen.getWidth(), splashScreen.getHeight());
	label.setBackground(Color.BLACK);
	window.setLocationRelativeTo(null);
	window.setVisible(true);
	logging(false, Level.INFO, "Uruchomienie ekranu splashScreen.");
}

/** Inicjuje pocz¹tekowe warunki i stany gry, np. gameWindow, MainScreen (Canvas). W tej metodzie uruchamiany jest w¹tek
 * renderowania i update'owania gry.
 * 
 */
private void gameInit()
{
	if(gamepadConfigFile.exists() && !gamepadConfigFile.isDirectory()) gamepadConfigFileEnabled = true;
	
	try {
		texasFont = Font.createFont(Font.TRUETYPE_FONT, TEXAS_FONT);
		logging(false, Level.INFO, "Czcionka Textas Font za³adowana.");
		smokunFont = Font.createFont(Font.TRUETYPE_FONT, SMOKUN_FONT);
		logging(false, Level.INFO, "Czcionka Smokun Font Font za³adowana.");
	}
	catch (FontFormatException | IOException e)
	{
		String message = getStackTrace(e);
		logging(false, Level.WARNING, "B³¹d ³adowania czcionki! ");
		logging(true, Level.WARNING, message);
	}
	
	prepareCollectibles();
	prepareAchievements();
	prepareHallOfFame();
	
	music = new Music();
	
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
	
	gameWindow = new GameWindow(this);
	gameState = GameState.MainMenu;
	mainScreen = new MainScreen(gameState, gameWindow, gamepadConfigFileEnabled, hallOfFame, achievements, collectiblesList);
	gameWindow.setVisible(true);
	logging(false, Level.INFO, "Okno gry zainicjowane.");
	WIDTH = mainScreen.getWidth();
	HEIGHT = mainScreen.getHeight();
	
	musicThreadStart();
	gameThreadStart();
	window.dispose();
}


public synchronized void musicThreadStart()
{
	if (musicThreadRunning) return;
	musicThreadRunning = true;
	musicThread = new Thread(new MusicThread());
	musicThread.start();
}


/** Metoda uruchamiaj¹ca w¹tek renderowania i obliczania logic gry.
 * 
 */
public synchronized void gameThreadStart()
{
	if (gameThreadRunning) return;
	gameThreadRunning = true;
	gameThread = new Thread(this);
	gameThread.start();
}


@Override
public void run()
{	
	gameWindow.requestFocus();
	
	gameState = GameState.Game;
	logging(false, Level.INFO, "W¹tek gry uruchomiony.");
	
	// GAME LOOP
	long lastTime = System.nanoTime();
	
	double delta = 0;
	long timer = System.currentTimeMillis();
	int updates = 0;
	int frames = 0;
	
	while(gameThreadRunning)
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
				if (fpsCap) mainScreen.render(60, ticks_count);
			}
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
		if (fpsCap)
		{
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				logging(false, Level.WARNING, "B³¹d game loop w momencie Thread.sleep(5); ! ");
				logging(true, Level.WARNING, getStackTrace(e));
			}
		}
	}
}

private void prepareHallOfFame()
{
	hallOfFameList = nc.getHOFRecordsFromServer();
	
	hallOfFame = new HallOfFame(hallOfFameList);
}



/**
@SuppressWarnings("unchecked")
private void prepareHallOfFame()
{	
	if (!MainClass.hallOfFameFile.exists() && !MainClass.hallOfFameFile.isDirectory())
	{
		// POCZATKOWA INICJALIZACJA TABLICY Z HALL OF FAME;
		hallOfFameList = new ArrayList<HallOfFamePlayer>(20);
		for (int i = 0; i < 20; i++) hallOfFameList.add(new HallOfFamePlayer(" < empty > ", 0, 0, 0));
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(MainClass.hallOfFameFile));
			oos.writeObject(hallOfFameList);
			oos.flush();
			oos.close();
			logging(false, "Plik "+MainClass.hallOfFameFile.getName() +" poprawnie utworzony a nowe dane poprawnie zapisane");
		}
		catch (IOException ioe)
		{
			String message = getStackTrace(ioe);
			logging(true, "B³¹d zapisu do pliku "+MainClass.hallOfFameFile.getName(), message);
		}
	}
	
	try {
	ois = new ObjectInputStream(new FileInputStream(MainClass.hallOfFameFile));
	hallOfFameList = (ArrayList<HallOfFamePlayer>) ois.readObject();
	ois.close();
	logging(false, "Dane HallOfFame z pliku "+MainClass.hallOfFameFile.getName() +" poprawnie za³adowane");
	}
	catch (IOException | ClassNotFoundException e)
	{
		String message = getStackTrace(e);
		logging(true, "B³¹d odczytu z pliku " +MainClass.hallOfFameFile.getName(), message);
	}
	hallOfFame = new HallOfFame(hallOfFameList);
}
**/

public void prepareCollectibles()
{	
	if (!MainClass.collectiblesFile.exists() && !MainClass.collectiblesFile.isDirectory())
	{
		for (int i = 0; i < collectiblesList.length; i++)
		{
			collectiblesList[i] = 0;
		}
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream((MainClass.collectiblesFile)));
			oos.writeObject(collectiblesList);
			oos.flush();
			oos.close();
			logging(false, Level.INFO, "Plik "+MainClass.collectiblesFile.getName() +" poprawnie utworzony a nowe dane poprawnie zapisane");
		}
		catch (IOException ioe)
		{
			logging(false, Level.WARNING, "B³¹d zapisu do pliku "+MainClass.collectiblesFile.getName());
			logging(true, Level.WARNING, getStackTrace(ioe));
		}
	}
		
	try {
		ois = new ObjectInputStream(new FileInputStream(MainClass.collectiblesFile));
		collectiblesList = (int[]) ois.readObject();
		ois.close();
		logging(false, Level.INFO, "Dane Collectibles z pliku "+MainClass.collectiblesFile.getName() +" poprawnie za³adowane");
		}
		catch (IOException | ClassNotFoundException e)
		{
			logging(false, Level.WARNING, "B³¹d odczytu pliku "+MainClass.collectiblesFile.getName());
			logging(true, Level.WARNING, getStackTrace(e));
		}
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
				logging(false, Level.INFO, "Plik "+MainClass.achievementsFile.getName() +" poprawnie utworzony a nowe dane poprawnie zapisane");
			}
			catch (IOException ioe)
			{
				String message = getStackTrace(ioe);
				logging(true, Level.WARNING, "B³¹d zapisu do pliku "+MainClass.achievementsFile.getName());
				logging(true, Level.WARNING, message);
			}
	}
	
	try {
	ois = new ObjectInputStream(new FileInputStream(MainClass.achievementsFile));
	achievementsList = (HashMap<Integer, Boolean>) ois.readObject();
	ois.close();
	logging(false, Level.INFO, "Dane Achievements z pliku "+MainClass.achievementsFile.getName() +" poprawnie za³adowane");
	}
	catch (IOException | ClassNotFoundException e)
	{
		logging(false, Level.WARNING, "B³¹d odczytu pliku "+MainClass.achievementsFile.getName());
		logging(true, Level.WARNING, getStackTrace(e));
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
	achievements.setComplete6LevelComplete(achievementsList.get(13));
	achievements.setComplete7LevelComplete(achievementsList.get(14));
	achievements.setComplete8LevelComplete(achievementsList.get(15));
	achievements.setComplete9LevelComplete(achievementsList.get(16));
	achievements.setComplete10LevelComplete(achievementsList.get(17));
	achievements.setFindAllCoinsComplete(achievementsList.get(18));
	achievements.setFindAllPowerupsComplete(achievementsList.get(19));
	achievements.setNoHarmComplete(achievementsList.get(20));
	achievements.setMegaJumpComplete(achievementsList.get(21));
	achievements.setSprinterComplete(achievementsList.get(22));
}


/**  Metoda wrzucaj¹ca do loga treœæ wyj¹tku/informacji.
 * Dzia³a tylko jeœli DEBUG_MODE = true;
 * @param critical True jeœli b³¹d jest krytyczny i nale¿y przerwaæ dzia³anie programu.
 * @param level Poziom informacji (z regu³y INFO lub WARNING)
 * @param msg Treœæ wiadomoœci do loga.
 */
public static void logging(boolean critical, Level level, String msg)
{
	if (DEBUG_MODE)
	{
		LOGGER.log(level, msg);
		if (critical) {
			LOGGER.log(Level.WARNING, "Zamykanie programu z b³êdem.");
			System.exit(-1);
		}
	}
}


/** Metoda zwracaj¹ca tekst PrintStackTrace w postaci String.
 * @param throwable  Wyj¹tek.
 * @return (String) tekst PrintStackTrace.
 */
public static String getStackTrace(final Throwable throwable) {
    final StringWriter sw = new StringWriter();
    final PrintWriter pw = new PrintWriter(sw, true);
    throwable.printStackTrace(pw);
    return sw.getBuffer().toString();
}



// URUCHAMIANIE Z "-DEBUG" W£ACZA DEBUG-MODE.
public static void main(String[] args)
{
	if (args.length > 0)
	if (args[0].equalsIgnoreCase("-debug")) DEBUG_MODE = true;
	else DEBUG_MODE = false;
	
	new MainClass();
}


/** Prywatna klasa w¹tku odtwarzaj¹cego muzykê z pliku mp3.
 * @author Piotrek
 *
 */
private class MusicThread implements Runnable
{

	@Override
	public void run() {

		MainClass.logging(false, Level.INFO, "W¹tek muzyczny uruchomiony.");
		
		music.setPlaying(true);
		music.restart(Music.WESTERN);
		
		while (musicThreadRunning)
		{	
			if (music.isPlaying()) {
				music.play();
				music.restart(music.getSong());
			}
		}
	}
}
}