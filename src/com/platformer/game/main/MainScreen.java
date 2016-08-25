package com.platformer.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.imageio.ImageIO;

import com.platformer.game.graphics.Animation;
import com.platformer.game.graphics.Textures;
import com.platformer.game.input.InputManager;
import com.platformer.game.input.Joystick;
import com.platformer.game.objects.PlayerObject;
import com.platformer.game.sounds.Music;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Event;



/** Podstawowa klasa ekranu gry.
 * 
 * @author Piotrek
 *
 */
public class MainScreen extends Canvas{

private static final long serialVersionUID = -5788122194224852624L;


private int fps_count = 0, ticks_count = 0;
public static int LEVEL = 1;
public static int COINS = 0;
public static int SCORE = 0;
public static int minutes = 0, seconds = 0;
public static float milis = 0f;
private long milisMax = 0;
public static final float MAX_TIME_BONUS = 1500f;
public static float time_bonus = MAX_TIME_BONUS;
public static float TOTAL_SCORE = 0f;
public static boolean PLAYER_LEFT = false, PLAYER_RIGHT = false, PLAYER_JUMP = false, START_EXIT = false;
protected static String time;
protected static String playerName;

private GameWindow gameWindow;
private BufferStrategy bs;
private Graphics g;
private Graphics2D g2d;
private ObjectsHandler objectsHandler;
private ObjectOutputStream oos;
private PlayerObject player;
private InputManager key;
private Joystick joystick;
private Controller myGamepad;
private Component[] gamepadComponents;
private boolean exit = false;
private Camera cam;
private HUD hud;

/** Przyjmuje wartoúÊ true jeúli plik konfiguracyjny gamepada zosta≥ utworzony ORAZ gdy gamepad zosta≥ pod≥πczony do portu USB konputera.
 * 
 */
private boolean gamepadEnabled = false;

private boolean makeScreenShot = false;
private boolean showMessage = false;
private boolean saveAchievementsToFile;
private int messageCount = 0;
private int msgY;
private final int MESSAGE_TIME = 300;
private Properties prop = new Properties();
private InputStream propInput = null;
private String leftProp, leftValueProp, rightProp, rightValueProp, jumpProp, jumpValueProp, startProp, startValueProp, upProp, upValueProp, downProp, downValueProp;
private File screenShotFile;
private HallOfFame hallOfFame;
private Achievements achievements;

/** Obiekt enumu GameState prezentujπcy aktualny "stan gry". W zaleønoúci od tego stanu jest wyúwietlane odpowienie menu gry lub sama gra.
 *  @see GameState
 */
private GameState gameState;

private int selectedMainMenuButton;
private final int MAX_MAIN_MENU_BUTTONS = 7;
private MenuButton[] mainMenuButtons;
private int selectedMenuButton;
private final int MAX_MENU_BUTTONS = 3;
private MenuButton[] menuButtons;

/** Zmienna pomocna przy naliczaniu przesuwania siÍ t≥a w menu g≥Ûwnym.
 * 
 */
private float bg_move = 0f;

/** Zmienna pomocna przy naliczaniu ruchu s≥oÒca po okrÍgu w menu g≥Ûwnym.
 * 
 */
private float circle_move = 0f;

private int plane_move = -600;

private double orbitRadius = 250;
private double orbitSpeed = Math.PI / 16;
private double radian = 0;
private boolean makeBgImage = false, makeBgImageGrayScale = false;
public BufferedImage backgroundGrayScaleImage;
private Animation smigloAnim;




/** Podstawowy konstruktor klasy MainScreen.
 * @param gameState - stan gry.
 * @param gameWindow - obiekt klasy GameWindow czyli okno gry.
 * @param gamepadFileEnabled - true jeúli plik konfiguracyjny gamepada zosta≥ utworzony.
 * @param hallOfFame - obiekt klasy HallOfFame czyli Najlepsze Wyniki.
 * @param achievements - obiekt klasy Achievements czyli OsiπgniÍcia.
 */
public MainScreen(GameState gameState, GameWindow gameWindow, boolean gamepadFileEnabled, HallOfFame hallOfFame, Achievements achievements)
{
	super();	
	this.setFocusable(false);
	this.gameState = gameState;
	this.gameWindow = gameWindow;
	this.hallOfFame = hallOfFame;
	this.achievements = achievements;
	this.gamepadEnabled = gamepadFileEnabled;
	
	if (this.gamepadEnabled) 
	{
		
		try {
			propInput = new FileInputStream(MainClass.gamepadConfigFile);

			prop.load(propInput);

			leftProp = prop.getProperty("Left");
			leftValueProp = prop.getProperty("value_left");
			rightProp = prop.getProperty("Right");
			rightValueProp = prop.getProperty("value_right");
			jumpProp = prop.getProperty("Jump");
			jumpValueProp = prop.getProperty("value_jump");
			startProp = prop.getProperty("Start");
			startValueProp = prop.getProperty("value_start");
			upProp = prop.getProperty("Up");
			upValueProp = prop.getProperty("value_up");
			downProp = prop.getProperty("Down");
			downValueProp = prop.getProperty("value_down");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (propInput != null) {
				try {
					propInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		joystick = new Joystick();
		if (joystick.isGamepadFound()) 
		{
			myGamepad = joystick.getMyFirstGamepad();
			System.out.println("Input - " +myGamepad.getName());
			gamepadComponents = myGamepad.getComponents();
		
			for (int i = 0; i < gamepadComponents.length; i++)
				System.out.println(gamepadComponents[i].getName());
			
			System.out.println();
			
			System.out.println(leftProp);
			System.out.println(rightProp);
			System.out.println(upProp);
			System.out.println(downProp);
			System.out.println(startProp);
		}
		else this.gamepadEnabled = false;
	}
		
	this.gameWindow.add(this);
	key = new InputManager();
	
	this.gameWindow.addKeyListener(key);
	this.gameWindow.addMouseListener(key);
	this.gameWindow.addMouseMotionListener(key);
	this.gameWindow.addMouseWheelListener(key);
	
	cam = new Camera(0,0);
	
	playerName = "";
	
	bg_move = 0f;
	msgY = 0;
	saveAchievementsToFile = false;
	
	hud = new HUD();
	
	objectsHandler = new ObjectsHandler(cam, achievements);
	objectsHandler.loadLevel(1);
	player = objectsHandler.getPlayer();
	
	mainMenuButtons = new MenuButton[MAX_MAIN_MENU_BUTTONS];
		
	selectedMainMenuButton = 0;
	mainMenuButtons[0] = new MenuButton("NOWA GRA", 650, 120, 310, 50);
	mainMenuButtons[1] = new MenuButton("JAK GRA∆", 650, 180, 310, 50);
	mainMenuButtons[2] = new MenuButton("NAJLEPSZE WYNIKI", 650, 240, 310, 50);
	mainMenuButtons[3] = new MenuButton("OSI•GNI CIA", 650, 300, 310, 50);
	mainMenuButtons[4] = new MenuButton("ZNAJDèKI", 650, 360, 310, 50);
	mainMenuButtons[5] = new MenuButton("O GRZE", 650, 420, 310, 50);
	mainMenuButtons[6] = new MenuButton("ZAKO—CZ", 650, 480, 310, 50);
	
	menuButtons = new MenuButton[MAX_MENU_BUTTONS];
	selectedMenuButton = 0;	
	menuButtons[0] = new MenuButton("WZN”W GR ", 360, 190);
	menuButtons[1] = new MenuButton("MENU G£”WNE", 360, 270);
	menuButtons[2] = new MenuButton("ZAKO—CZ GR ", 360, 350);
	
	smigloAnim = new Animation(1, Textures.getInstance().smiglo[3], Textures.getInstance().smiglo[2], Textures.getInstance().smiglo[1], Textures.getInstance().smiglo[0]);
}


/** Metoda powodujπca odtwarzanie muzyki nr.1 - 'Western.mp3"
 * 
 */
public void playMusic1()
{
	MainClass.music.stop();
	MainClass.music.restart(Music.WESTERN);
	MainClass.music.setPlaying(true);		
}

/** Metoda powodujπca odtwarzanie muzyki nr. 2 - "Mirage.mp3".
 * 
 */
public void playMusic2()
{
	MainClass.music.stop();
	MainClass.music.restart(Music.MIRAGE);
	MainClass.music.setPlaying(true);
}

/** Metoda zatrzymujπca odtwarzanie muzyki.
 * 
 */
public void stopMusic()
{
	MainClass.music.stop();
	MainClass.music.setPlaying(false); // KONIECZNE !!
}


public BufferedImage makeGrayScale(BufferedImage input)
{
	BufferedImage newImage = copyImage(input);
	int bgWidth = newImage.getWidth();
    int bgHeight = newImage.getHeight();
    
    for(int i=0; i<bgWidth; i++) {
    
       for(int j=0; j<bgHeight; j++) {
       
          Color c = new Color(newImage.getRGB(i, j));
          int red = (int)(c.getRed() * 0.299);
          int green = (int)(c.getGreen() * 0.587);
          int blue = (int)(c.getBlue() *0.114);
          Color newColor = new Color(red+green+blue,
          red+green+blue,red+green+blue);
          newImage.setRGB(i, j,newColor.getRGB());
       }
    }
    return newImage;
}

static BufferedImage copyImage(BufferedImage bi)
{
	ColorModel cm = bi.getColorModel();
	boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
	WritableRaster raster = bi.copyData(null);
	return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
}


/** Metoda w ktÛrej zawarty jest ca≥y game logic.
 * 
 */
public void tick()
{
	// GAMEPAD
	if (this.gamepadEnabled)
	{
		myGamepad.poll();    
		net.java.games.input.EventQueue queue = myGamepad.getEventQueue();
		Event event = new Event();
    
		while(queue.getNextEvent(event))
		{
			Component comp = event.getComponent();
			float value = event.getValue();          	
    	
			if (comp.getName().equals(startProp))
			{
				if (Float.toString(value).equals(startValueProp) && gameState == GameState.MainMenu) exit = true;
			}
			
			if (comp.getName().equals(jumpProp) && gameState == GameState.Game)
    			{
				if (Float.toString(value).equals(jumpValueProp)) PLAYER_JUMP = true;
    				else PLAYER_JUMP = false;
    			}
			
			if (comp.getName().equals(leftProp) && gameState == GameState.Game)
			{
				if (Float.toString(value).equals(leftValueProp))
				{
					PLAYER_LEFT = true;
				}
				else PLAYER_LEFT = false;
			}
			
			if (comp.getName().equals(rightProp) && gameState == GameState.Game)
			{
				if (Float.toString(value).equals(rightValueProp))
				{
					PLAYER_RIGHT = true;
				}
				else PLAYER_RIGHT = false;
			}
			
			// MAIN MENU
			if (comp.getName().equals(upProp) && gameState == GameState.MainMenu)
			{
				if (Float.toString(value).equals(upValueProp))
				{
					if (selectedMainMenuButton >= 0)
					{
						MainClass.menuSound1.play();
						mainMenuButtons[selectedMainMenuButton].setSelected(false);
						if (selectedMainMenuButton == 0) selectedMainMenuButton = MAX_MAIN_MENU_BUTTONS-1;
						else selectedMainMenuButton--;
					}
				}
				//else PLAYER_RIGHT = false;
			}
			
			if (comp.getName().equals(downProp) && gameState == GameState.MainMenu)
			{
				if (Float.toString(value).equals(downValueProp))
				{
					if (selectedMainMenuButton < MAX_MAIN_MENU_BUTTONS)
					{
						MainClass.menuSound1.play();
						mainMenuButtons[selectedMainMenuButton].setSelected(false);
						if (selectedMainMenuButton == MAX_MAIN_MENU_BUTTONS-1) selectedMainMenuButton = 0; 
						else selectedMainMenuButton++;				
					}
				}
				//else PLAYER_RIGHT = false;
			}
		}
	}
    
    // KEYBOARD
	key.update();

	PLAYER_JUMP = false;
  	PLAYER_LEFT = false;
  	PLAYER_RIGHT = false;
    
    if ((key.isKeyPressed(KeyEvent.VK_LEFT)) || (key.isKeyPressed(KeyEvent.VK_A))) PLAYER_LEFT = true;
	if (((key.isKeyPressed(KeyEvent.VK_RIGHT)) || (key.isKeyPressed(KeyEvent.VK_D)))) PLAYER_RIGHT = true;
	if ((key.isKeyPressedOnce(KeyEvent.VK_UP)) || (key.isKeyPressedOnce(KeyEvent.VK_W))) {
		PLAYER_JUMP = true;
		
		if (player.isOnGround()) {
			if (!achievements.isJumpCount10Complete()) achievements.addJump10Count();
			if (achievements.isJumpCount10Complete() && !achievements.isJumpCount50Complete()) achievements.addJump25Count();
			if (achievements.isJumpCount25Complete()) achievements.addJump50Count();
		}
		
	}
	
	if (key.isKeyPressedOnce(KeyEvent.VK_ESCAPE)) // POKAØ / UKRYJ MENU
	{
		if (gameState == GameState.Menu)
		{
			MainClass.menuSound2.play();
			gameState = GameState.Game;
			return;
		}
		
		if (gameState == GameState.Game)
		{
			MainClass.menuSound2.play();
			gameState = GameState.Menu;
			return;
		}
	}
	
	
	if (key.isKeyPressedOnce(KeyEvent.VK_CONTROL)) {
		player.setHealth(5);
		//System.out.println(Thread.activeCount());
		int active = Thread.activeCount();
        System.out.println("currently active threads: " + active);
        Thread all[] = new Thread[active];
        Thread.enumerate(all);

        for (int i = 0; i < active; i++) {
           System.out.println(i + ": " + all[i] +" " +all[i].getState());
        }
	}
	
	if (showMessage)
	{
		if (messageCount > 0) messageCount--;
		else {
			messageCount = MESSAGE_TIME;
			showMessage = false;
		}
	}
	
	if (key.isKeyPressedOnce(KeyEvent.VK_F12))
	{
		// SCREENSHOT !!!
		makeScreenShot = true;
	}
	
	
	// ZABAWA BULLET TIME
	//if (key.isKeyPressedOnce(KeyEvent.VK_1) && MainClass.amountOfTicks > 30) MainClass.amountOfTicks -= 10;
	//if (key.isKeyPressedOnce(KeyEvent.VK_2) && MainClass.amountOfTicks < 60) MainClass.amountOfTicks += 10;
	
	
	// RESTART AFTEER DEATH
	if (gameState == GameState.Death && key.isKeyPressedOnce(KeyEvent.VK_SPACE))
	{
		MainClass.menuSound2.play();
		gameState = GameState.Game;
		objectsHandler.clearLevel();
		objectsHandler.resetLevelStatistics();
		objectsHandler.loadLevel(LEVEL);
		cam.setX(0);
		player = objectsHandler.getPlayer();
		achievements.restartLevel();
		
	}
	
	
	// PRZESUWANIE T£A W MENU G£”WNYM
	if (gameState == GameState.MainMenu || gameState == GameState.JakGrac || gameState == GameState.NajlepszeWyniki || gameState == GameState.NajlepszeWyniki
			|| gameState == GameState.Osiagniecia || gameState == GameState.OGrze || gameState == GameState.Znajdzki)
	{
		bg_move -= 1;
		if (bg_move < -1000) bg_move = 0;

		// MOVE SUN IN A CIRCLE
		circle_move  = 0.05f;
		radian += orbitSpeed * circle_move;
		
		// PLAIN MOVE
		smigloAnim.runAnimation();
		plane_move += 4;
		if (plane_move > 2200) plane_move = -800;
	}
	
	// OB£UGA MENU G£”WNEGO
	if (gameState == GameState.MainMenu)
	{
		// KEYBOARD
		
		if (key.isKeyPressedOnce(KeyEvent.VK_DOWN) || key.isKeyPressedOnce(KeyEvent.VK_S))
		{
			if (selectedMainMenuButton < MAX_MAIN_MENU_BUTTONS)
			{
				MainClass.menuSound1.play();
				mainMenuButtons[selectedMainMenuButton].setSelected(false);
				if (selectedMainMenuButton == MAX_MAIN_MENU_BUTTONS-1) selectedMainMenuButton = 0; 
				else selectedMainMenuButton++;				
			}
		}
		
		if (key.isKeyPressedOnce(KeyEvent.VK_UP) || key.isKeyPressedOnce(KeyEvent.VK_W))
		{
			if (selectedMainMenuButton >= 0)
			{
				MainClass.menuSound1.play();
				mainMenuButtons[selectedMainMenuButton].setSelected(false);
				if (selectedMainMenuButton == 0) selectedMainMenuButton = MAX_MAIN_MENU_BUTTONS-1;
				else selectedMainMenuButton--;
			}
		}
		
		if (key.isKeyPressedOnce(KeyEvent.VK_ESCAPE))
		{
			MainClass.menuSound2.play();
			gameState = GameState.Zakoncz;// ZAMKNI CIE GRY KLAWISZEM ESC.
		}
		
		if (key.isKeyPressedOnce(KeyEvent.VK_ENTER))
		{
			MainClass.menuSound2.play();
			switch (selectedMainMenuButton)
			{
			case 0: {
						playMusic2();
						gameState = GameState.Game;
						break;				
					}
			case 1: gameState = GameState.JakGrac;
					break;
			case 2: gameState = GameState.NajlepszeWyniki;
					break;
			case 3: gameState = GameState.Osiagniecia;
					break;
			case 4: gameState = GameState.Znajdzki;
					break;
			case 5: gameState = GameState.OGrze;
					break;
			case 6: gameState = GameState.Zakoncz;
					break;
			}
		}
	}
	
	// OBS£UGA MENU W TRAKCIE GRY
	if (gameState == GameState.Menu)
	{
		if (key.isKeyPressedOnce(KeyEvent.VK_DOWN) || key.isKeyPressedOnce(KeyEvent.VK_S))
		{
			if (selectedMenuButton < MAX_MENU_BUTTONS)
			{
				MainClass.menuSound1.play();
				menuButtons[selectedMenuButton].setSelected(false);
				if (selectedMenuButton == MAX_MENU_BUTTONS-1) selectedMenuButton = 0; 
				else selectedMenuButton++;				
			}
		}
		
		if (key.isKeyPressedOnce(KeyEvent.VK_UP) || key.isKeyPressedOnce(KeyEvent.VK_W))
		{
			if (selectedMenuButton >= 0)
			{
				MainClass.menuSound1.play();
				menuButtons[selectedMenuButton].setSelected(false);
				if (selectedMenuButton == 0) selectedMenuButton = MAX_MENU_BUTTONS-1;
				else selectedMenuButton--;
			}
		}
			
		if (key.isKeyPressedOnce(KeyEvent.VK_ENTER))
		{
			MainClass.menuSound2.play();
			switch (selectedMenuButton)
			{
			case 0: gameState = GameState.Game;
					break;				
			case 1: 
					objectsHandler.clearLevel();
					objectsHandler.resetLevelStatistics();
					objectsHandler.loadLevel(1);
					cam.setX(0);
					player = objectsHandler.getPlayer();
					achievements.restartLevel();
					playMusic1();
					gameState = GameState.MainMenu;
					break;
			case 2: gameState = GameState.Zakoncz;
					break;
			}
		}
	}

	// MENU JAK GRA∆	
	if (gameState == GameState.JakGrac)
	{
		if (key.isKeyPressedOnce(KeyEvent.VK_ESCAPE))
		{
			MainClass.menuSound2.play();
			gameState = GameState.MainMenu;
		}
	}
	

	// MENU NAJLEPSZE WYNIKI	
	if (gameState == GameState.NajlepszeWyniki)
	{
		if (key.isKeyPressedOnce(KeyEvent.VK_ESCAPE))
		{
			MainClass.menuSound2.play();
			gameState = GameState.MainMenu;
		}
	}


	// MENU OSIAGNIECIA
	if (gameState == GameState.Osiagniecia)
	{
		if (key.isKeyPressedOnce(KeyEvent.VK_ESCAPE))
		{
			MainClass.menuSound2.play();
			gameState = GameState.MainMenu;
		}
	}

	
	// MENU ZNAJDèKI
	if (gameState == GameState.Znajdzki)
	{
		if (key.isKeyPressedOnce(KeyEvent.VK_ESCAPE))
		{
			MainClass.menuSound2.play();
			gameState = GameState.MainMenu;
		}
	}
	
	
	// MENU O GRZE
	if (gameState == GameState.OGrze)
	{
		if (key.isKeyPressedOnce(KeyEvent.VK_ESCAPE))
		{
			MainClass.menuSound2.play();
			gameState = GameState.MainMenu;
		}
	}
	
	
	// PO WPISANIU IMIENIA DO LISTY NAJLEPSZYCH - ENTER!
	if (player.isFinishLevel()) {
		
		if (key.isAnyKeyPressedOnce())
		{
			if (((Character.isAlphabetic(key.getKey()) || (key.getKey() == '1' || key.getKey() == '2' || key.getKey() == '3' || key.getKey() == '4')
					|| key.getKey() == '5' || key.getKey() == '6' || key.getKey() == '7' || key.getKey() == '8' || key.getKey() == '9' || key.getKey() == '0'
					|| key.getKey() == ' '))
					&& (playerName.length() < 20))  playerName += key.getKey();
			else
				if (key.isKeyPressed(KeyEvent.VK_BACK_SPACE) && playerName.length() > 0) {
					playerName = playerName.substring(0, playerName.length()-1);
				}
		}
		
		if (key.isKeyPressedOnce(KeyEvent.VK_ENTER)) {
			writeScore(playerName, SCORE, milisMax);
		}
	}
	
	// GRA DZIA£A
	if (gameState == GameState.Game && !player.isFinishLevel() && player.getHealth() > 0) {
		objectsHandler.tick();
		cam.tick(player);
		timeTick();
	}

	
	
	// ENTER TO NEW LEVEL
	if (gameState == GameState.NextLevel && player.isFinishLevel() && key.isKeyPressedOnce(KeyEvent.VK_ENTER)) // ENTER PO WPISANIU IMIENIA PRZENOSI NA NOWY POZIOM
	{
		gameState = GameState.Game;
		MainScreen.milis = 0f;
		milisMax = 0;
		MainScreen.minutes = 0;
		MainScreen.seconds = 0;
		MainScreen.COINS = 0;
		MainScreen.SCORE = 0;
		MainScreen.time_bonus = MainScreen.MAX_TIME_BONUS;
		MainScreen.TOTAL_SCORE = 0;
				
		objectsHandler.switchLevel();
		player = objectsHandler.getPlayer();
		achievements.restartLevel();
	}
	
	if (achievements.isShowAchievement())
	{
		if (achievements.getAchievementCount() > 0) {
			achievements.setAchievementCount(achievements.getAchievementCount() -1);
			showMessage = true;
		}
		else {
			achievements.setAchievementCount(achievements.getShowAchievementCooldown());
			achievements.setShowAchievement(false);			
		}
	}
	if (player.getHealth() <= 0) gameState = GameState.Death;
}


/** Metoda obliczajπ aktualny czas gry na danym poziomie.
 * 
 */
public void timeTick()
{
	milis += (1000/60);
	milisMax += (1000/60);
	if (milis >999)
	{
		seconds++;
		milis = 0;
	}
	if (seconds > 59)
	{
		minutes++;
		seconds = 0;
	}
	if (minutes < 10) time = "0"+minutes;
	else time = minutes+"";
	if (seconds < 10) time += ":0" +seconds +":" + (int) milis;
	else time += ":"+seconds +":" + (int) milis;	
}


/** Metoda dodajπca nowegy wpis na liúcie Najlepsze Wyniki (Hall of Fame)
 * oraz zapisujπca tÍ listÍ do pliku.
 * @param name ImiÍ/ksywka obecnego gracza.
 * @param score Wynik obecnego gracza.
 * @param milis Czas gry obecnego gracza w milisekundach
 */
private void writeScore(String name, int score, long milis)
{
	hallOfFame.getHallOfFameList().add(new HallOfFamePlayer(name, score, milis));
	hallOfFame.writeScoreToFile();
}

/** Metoda wyúwietlajπca osiπgniÍcie na ekranie, w≥πcznie z "najazdem" i "odjazdem" ramki z tekstem u gÛry ekranu.
 * @param g2d Graphics2D.
 * @param msg TreúÊ osiπgniÍcia.
 * @param achievementImage Ikonka osiπgniÍcia.
 * @param counter Licznik, opisujπcy jednostki czasu przez jaki wiadomoúÊ jest wyúwietlana.
 */
private void showMessage(Graphics2D g2d, String msg, BufferedImage achievementImage, int counter)
{
	
	if (!saveAchievementsToFile)
	{
		if(MainClass.achievementsFile.exists() && !MainClass.achievementsFile.isDirectory())
		{
			try {
			oos = new ObjectOutputStream(new FileOutputStream((MainClass.achievementsFile)));
		    oos.writeObject(achievements.getAchievementsList());
		    oos.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
				System.exit(-1);
			}
			saveAchievementsToFile = true;
		}
		else saveAchievementsToFile = true;
	}
	
	g2d.setColor(Color.RED);
	g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 34f));
	
	if (msgY < 100 && counter < achievements.getShowAchievementCooldown()) msgY++;
	if (counter == achievements.getShowAchievementCooldown())
		if (msgY > -20) msgY--;
	
	g2d.drawImage(Textures.getInstance().achievementBg, 280, msgY-40, null);
	g2d.drawImage(achievementImage, 295, msgY - 35, null);
	g2d.drawString(msg, 355, msgY);
}


/** Podstawowa metoda rysowania na ekranie w trakcie trwania gry.
 * @param fps_count Licznik FPSÛw.
 * @param ticks_count Licznik update'Ûw (game logic).
 */
public void render(int fps_count, int ticks_count)
{
	
	// POLECENIE EXIT
	if (gameState == GameState.Zakoncz)
	{
		exit = true;
	}
	
	this.fps_count = fps_count;
	this.ticks_count = ticks_count;
	
	bs = this.getBufferStrategy();
	
	if (bs == null)
	{
		this.createBufferStrategy(3);
		return;
	}
	
	g = bs.getDrawGraphics();

	if (makeScreenShot)
	{
		Textures.getInstance().screenShotImage = new BufferedImage(MainClass.WIDTH, MainClass.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = Textures.getInstance().screenShotImage.createGraphics();
	}
	
	// Tworzenie obrazu t≥a gry w grayScale;
	if ((gameState == GameState.Menu || gameState == GameState.Death) && !makeBgImage)
	{
		makeBgImage = true;
		Textures.getInstance().bgMenuImage = new BufferedImage(MainClass.WIDTH, MainClass.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = Textures.getInstance().bgMenuImage.createGraphics();
		backgroundGrayScaleImage = makeGrayScale(Textures.getInstance().bgMenuImage);
		makeBgImageGrayScale = false;
	}
	
	/////////////////// DRAW HERE ////////////////////////////
	
	g2d = (Graphics2D) g;
	RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	
	g2d.setRenderingHints(rh);
		
	// MOUNTAING & PARALLAX
	if (gameState == GameState.Game || gameState == GameState.Death || gameState == GameState.Menu || gameState == GameState.NextLevel)
	{
		// BACKGROUND - GÛry i s≥oÒce
		g.setColor(new Color(184, 220, 254));
		g.fillRect(0,0,getWidth(), getHeight());
		if (cam.getX() < 10  && cam.getX() > -6950) g2d.drawImage(Textures.getInstance().backGroundMountains, (int) (cam.getX()*0.143), (int) (cam.getY()/1.33) + (MainClass.HEIGHT / 2), MainClass.WIDTH, (int) (MainClass.HEIGHT*1.2), null);
		if (cam.getX() < -15  && cam.getX() > -14000) g2d.drawImage(Textures.getInstance().backGroundMountains, (int) (cam.getX()*0.143) + 1000, (int) (cam.getY()/1.33) + (MainClass.HEIGHT / 2), MainClass.WIDTH, (int) (MainClass.HEIGHT*1.2), null);
		if (cam.getX() < -7000  && cam.getX() > -14500) g2d.drawImage(Textures.getInstance().backGroundMountains, (int) (cam.getX()*0.143) + 2000, (int) (cam.getY()/1.33) + (MainClass.HEIGHT / 2), MainClass.WIDTH, (int) (MainClass.HEIGHT*1.2), null);		
		g2d.drawImage(Textures.getInstance().sun, 210, (int) (cam.getY()/1.33) + 420, null);
	}
	
	if (gameState == GameState.MainMenu || gameState == GameState.JakGrac || gameState == GameState.NajlepszeWyniki || gameState == GameState.NajlepszeWyniki
			|| gameState == GameState.Osiagniecia || gameState == GameState.OGrze || gameState == GameState.Znajdzki)
	{
		g.setColor(new Color(184, 220, 254));
		g.fillRect(0,0,getWidth(), getHeight());
		
		g2d.drawImage(Textures.getInstance().bg_niebo, (int) (bg_move), (int) (0), null);
		g2d.drawImage(Textures.getInstance().bg_niebo, (int) (bg_move+1000), (int) (0), null);
		
		g2d.drawImage(Textures.getInstance().sun, (int) (450+ orbitRadius * Math.cos(radian)), (int) (220 + orbitRadius * Math.sin(radian)), this);
		
		g2d.drawImage(Textures.getInstance().bg_gory, (int) (bg_move), (int) (MainClass.HEIGHT - 305), null);
		g2d.drawImage(Textures.getInstance().bg_gory, (int) (bg_move+1000), (int) (MainClass.HEIGHT - 305), null);
		
		// SAMOLOT
		g2d.drawImage(Textures.getInstance().planeR, plane_move, 15, null);
		smigloAnim.drawAnimation(g2d, plane_move+155, 35, false);
		
		//plane_move = 200;
		
		g2d.setColor(Color.BLACK);
		g2d.drawLine(plane_move+3, 78, plane_move-30, 78);
		g2d.drawImage(Textures.getInstance().flaga, plane_move-220, 65, null);
		
		for (int i = 0; i < MAX_MAIN_MENU_BUTTONS; i++) 
		{
			mainMenuButtons[i].render(g2d);
		}
		mainMenuButtons[selectedMainMenuButton].setSelected(true);
	}
	
	
		
	if ((!player.isFinishLevel()) && gameState==GameState.Game || gameState==GameState.Death || gameState==GameState.Menu || gameState==GameState.NextLevel)
	{ 
		////// CAM MOVING HERE

		g2d.translate(cam.getX(), cam.getY());  // CAM BEGINNING
			objectsHandler.render(g);
		g2d.translate(-cam.getX(), -cam.getY()); // CAM ENDING
	}
		
	
	// POWERUUPS
	if (player.isTequila_powerUp()) {
		g2d.drawImage(Textures.getInstance().tequilaImage, 10, 100, null);
		g2d.setColor(Color.ORANGE);
		g2d.fillRect(60, 180 - (int)(player.getTequila_time()/3.5), 10, (int) (player.getTequila_time()/3.5));
	}
	if (player.isTaco_powerUp()) {
		g2d.drawImage(Textures.getInstance().tacoImage, 10, 110, null);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(70, 160 - (player.getTaco_time()/8), 10, (player.getTaco_time()/8));
	}

	
	// ACHIEVEMENTS
	if (showMessage) showMessage(g2d, achievements.getAchievementTextShort(), achievements.getAchievementImage(), achievements.getAchievementCount());
	else saveAchievementsToFile = false;
		
	// KONIEC POZIOMU
	if (player.isFinishLevel())
	{
		gameState = GameState.NextLevel;
		TOTAL_SCORE = SCORE + (int) time_bonus;
	}
	

	// HUD gry
	if (gameState==GameState.Game || gameState==GameState.Death || gameState==GameState.Menu || gameState==GameState.NextLevel)
	{
		// PLAYER HEALTH
		for (int i = 0; i < player.getHealth(); i++) g.drawImage(Textures.getInstance().heart, 360+(i*40), 5, 40, 40,null);	
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 38f));
		g2d.setColor(Color.BLUE);
		g2d.drawString("POZIOM "+MainScreen.LEVEL, 845, 40);
		g2d.drawString("MONETY: "+MainScreen.COINS, 10, 40);
		g2d.drawString("WYNIK: "+MainScreen.SCORE, 10, 80);	
		g2d.setFont(new Font("Verdana", 1, 12));
		g2d.drawString("CZAS: "+MainScreen.time, MainClass.WIDTH - 150, 70);
	}
	
	// ZROBIENIE SZAREGO EKRANU
	if ((gameState == GameState.Menu || gameState == GameState.Death) && makeBgImage)
	{
		if (!makeBgImageGrayScale) {
			backgroundGrayScaleImage = makeGrayScale(Textures.getInstance().bgMenuImage);
			makeBgImageGrayScale = true;
		}
		g2d.drawImage(backgroundGrayScaleImage, 0, 0, null);
	}
	
	if (gameState == GameState.Menu)
	{
		g2d.drawImage(Textures.getInstance().menuBg, 320, 140, null);
		for (int i = 0; i < MAX_MENU_BUTTONS; i++) 
		{
			menuButtons[i].render(g2d);
		}
		menuButtons[selectedMenuButton].setSelected(true);
	}
	
	if (gameState == GameState.Game) makeBgImage = false;
	
	hud.showGameHud(g2d, gameState, this.fps_count, this.ticks_count);
	
	if (makeScreenShot)	makeScreenShot();
	
	//////////////////////////////////////////////////////////
	
	Toolkit.getDefaultToolkit().sync();
	
	g.dispose();
	bs.show();
}

/** Metoda zapisujπca zrzut ekranu do pliku .png. 
 * 
 */
public void makeScreenShot()
{
	MainClass.screenShotSound.play();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
	Calendar cal = Calendar.getInstance();
			
	screenShotFile = new File("screenshot "+dateFormat.format(cal.getTime())+".png");
			
	try {
		ImageIO.write(Textures.getInstance().screenShotImage, "png", screenShotFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	makeScreenShot = false;
}

/** Metoda zwracajπca pole isExit, ktÛre mÛwi, czy zosta≥o wykonane rzπdanie zakmniÍcia gry.
 * @return isExit
 */
public boolean isExit()
{
	return exit;
}
}