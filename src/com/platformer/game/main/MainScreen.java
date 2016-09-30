package com.platformer.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;

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
public class MainScreen extends Canvas {

private static final long serialVersionUID = -5788122194224852624L;


private int fps_count = 0, ticks_count = 0;
public static int LEVEL = 1;
public static int COINS = 0;
public static int SCORE = 0;
public static int minutes = 0, seconds = 0;
public static long millis = 0;
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
public static boolean exit = false;
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



private final int MAX_MAIN_MENU_BUTTONS = 7;
private MenuButton[] mainMenuButtons;
private int selectedMainMenuButton;

private final int MAX_MENU_BUTTONS = 3;
private MenuButton[] menuButtons;
private int selectedMenuButton;

private final int MAX_LEVEL_MENU_BUTTONS = 10;
private MenuButton[] levelMenuButtons;
private int selectedLevelMenuButton;
private int unlockedLevels = 0;

private float bg_move = 0f;
private float circle_move = 0f;
private int plane_move = -600;

private final double orbitRadius1 = 500;
private final double orbitRadius2 = 250;
private double orbitSpeed = Math.PI / 16;
private double radian = 0;
private boolean makeBgImage = false, makeBgImageGrayScale = false;
public BufferedImage backgroundGrayScaleImage;
private Animation smigloAnim;
private String napisNaFladze = "      TEQUILA PLATFORMER      ";
private String[] napisy = new String[] {
		"             HELLO !!!         ",
		"       TEQUILA PLATFORMER      ",
		"    CZY LECI Z NAMI PILOT ?    ",
		"       BEDE GRAU W GRE !       ",
		"   LEELOO DALLAS MULTIPASS     ",
		"        NASI TU BYLI !         ",
		"   NIE LEKCEWAØ ZAKWASZENIA    ",
		"         I'LL BE BACK !         ",
		"   CIEMNOå∆, WIDZ  CIEMNOå∆     ",
		"        MY LITTLE PONY...       ",
		"  TO JEST KURA PANIE GENRALE !  ",
		"          IT JUST WORKS !       ",
		"COME WITH ME IF YOU WANT TO LIVE",
		"   BOO BARDZO LUBI TAKIE LASY  ",
		"IT'S NOT A BUG, IT'S A FEATURE !",
		"      MARCELLO M”WI: CZEå∆ !    "
};
private Random random;

/** Przyjmuje wartoúÊ true jeúli w system obs≥uguje polecenia domyúlne takie jak OPEN, EDIT, MAIL, PRINT oraz BROWSE.
 * 
 */
private boolean isDesktopSupported;
private int[] collectiblesList;
private Desktop desktop;
private float[] falujaceLitery = new float[16];
private boolean[] literyUp = new boolean[16];
private Animation ptaki1RAnim;
private float ptaki1, ptaki2, ptaki3;
private float chmury1, chmury2, chmury3;
private float scrollScreenY;
private Shape defaultClip;
private HandMenuItem[] handMenuItem = new HandMenuItem[7];
private boolean readHoFRecords = false;
private int showInfo = -1;

// INTRO
private String introText1PL = "Dawno dawno temu, w dalekim Meksyku ...";
private BufferedImage intro_logo_image = null;
private int intro_counter = 0;
private float intro_logo_size = 0.1f;
private float intro_font_size1 = 0.1f;
private float intro_font_size2 = 0.1f;
private float intro_font_size3 = 0.1f;
private float intro_font_y1 = 0;
private float intro_font_y2 = 0;
private float intro_font_y3 = 0;

// OUTRO
public static boolean endgame = false;
private int outro_counter = 0;
private String outroText1 = "GRATULACJE !!!";
private String outroText2 = "Pomog≥eú Marcello odnaleüÊ jego";
private String outroText3 = " zaginionπ kozÍ MatyldÍ!";
private String outroText4 = "JESTEå ZWYCI ZC• !!!";
private int[] baloniki = new int[6];

/** Podstawowy konstruktor klasy MainScreen.
 * @param gameState - stan gry.
 * @param gameWindow - obiekt klasy GameWindow czyli okno gry.
 * @param gamepadFileEnabled - true jeúli plik konfiguracyjny gamepada zosta≥ utworzony.
 * @param hallOfFame - obiekt klasy HallOfFame czyli Najlepsze Wyniki.
 * @param achievements - obiekt klasy Achievements czyli OsiπgniÍcia.
 */
public MainScreen(GameState gameState, GameWindow gameWindow, boolean gamepadFileEnabled, HallOfFame hallOfFame, Achievements achievements, int[] collectiblesList)
{
	super();	
	this.setFocusable(false);
	this.gameState = gameState;
	this.gameWindow = gameWindow;
	this.hallOfFame = hallOfFame;
	this.achievements = achievements;
	this.gamepadEnabled = gamepadFileEnabled;
	this.collectiblesList = collectiblesList;
	
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
			MainClass.logging(false, Level.INFO, "Plik ustawieÒ gamepada prawid≥owo odczytany.");

		} catch (IOException ex) {
			MainClass.logging(false, Level.WARNING,  "B≥πd odczytu pliku ustawieÒ gamepada!");
			MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(ex));
		} finally {
			if (propInput != null) {
				try {
					propInput.close();
				} catch (IOException e) {
					MainClass.logging(false, Level.WARNING,  "Plik ustawieÒ gamepada zosta≥ nieprawid≥owo zamkniÍty!");
					MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(e));
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
	
	handMenuItem[0] = new HandMenuItem(Textures.getInstance().collectible[0], 110, 418);
	handMenuItem[1] = new HandMenuItem(Textures.getInstance().collectible[1], 151, 452);
	handMenuItem[2] = new HandMenuItem(Textures.getInstance().collectible[2], 145, 502);
	handMenuItem[3] = new HandMenuItem(Textures.getInstance().collectible[3], 98, 522);
	handMenuItem[4] = new HandMenuItem(Textures.getInstance().collectible[4], 56, 491);
	handMenuItem[5] = new HandMenuItem(Textures.getInstance().collectible[5], 63, 440);
	handMenuItem[6] = new HandMenuItem(Textures.getInstance().collectible[6], 105, 470);
		
	playerName = "";
		
	isDesktopSupported = Desktop.isDesktopSupported();
	
	scrollScreenY = 0;
	
	random = new Random();
	bg_move = 0f;
	msgY = 0;
	saveAchievementsToFile = false;
	
	hud = new HUD();
	
	objectsHandler = new ObjectsHandler(cam, achievements, collectiblesList);
	objectsHandler.loadLevel(1);
	player = objectsHandler.getPlayer();
	
	mainMenuButtons = new MenuButton[MAX_MAIN_MENU_BUTTONS];
		
	selectedMainMenuButton = 0;
	if (MainClass.language == MainClass.Languages.polish)
	{
		mainMenuButtons[0] = new MenuButton(TextResources.NEW_GAME_PL, 650, 120, 310, 50);
		mainMenuButtons[1] = new MenuButton(TextResources.HOW_TO_PLAY_PL, 650, 180, 310, 50);
		mainMenuButtons[2] = new MenuButton(TextResources.HALL_OF_FAME_PL, 650, 240, 310, 50);
		mainMenuButtons[3] = new MenuButton(TextResources.ACHIEVEMENTS_PL, 650, 300, 310, 50);
		mainMenuButtons[4] = new MenuButton(TextResources.COLLECTIBLES_PL, 650, 360, 310, 50);
		mainMenuButtons[5] = new MenuButton(TextResources.CREDITS_PL, 650, 420, 310, 50);
		mainMenuButtons[6] = new MenuButton(TextResources.EXIT_PL, 650, 480, 310, 50);		
	}
	if (MainClass.language == MainClass.Languages.english)
	{
		mainMenuButtons[0] = new MenuButton(TextResources.NEW_GAME_ENG, 650, 120, 310, 50);
		mainMenuButtons[1] = new MenuButton(TextResources.HOW_TO_PLAY_ENG, 650, 180, 310, 50);
		mainMenuButtons[2] = new MenuButton(TextResources.HALL_OF_FAME_ENG, 650, 240, 310, 50);
		mainMenuButtons[3] = new MenuButton(TextResources.ACHIEVEMENTS_ENG, 650, 300, 310, 50);
		mainMenuButtons[4] = new MenuButton(TextResources.COLLECTIBLES_ENG, 650, 360, 310, 50);
		mainMenuButtons[5] = new MenuButton(TextResources.CREDITS_ENG, 650, 420, 310, 50);
		mainMenuButtons[6] = new MenuButton(TextResources.EXIT_ENG, 650, 480, 310, 50);		
	}
	
	menuButtons = new MenuButton[MAX_MENU_BUTTONS];
	selectedMenuButton = 0;
	
	if (MainClass.language == MainClass.Languages.polish)
	{
		menuButtons[0] = new MenuButton(TextResources.RESUME_GAME_PL, 360, 190);
		menuButtons[1] = new MenuButton(TextResources.MAIN_MENU_PL, 360, 270);
		menuButtons[2] = new MenuButton(TextResources.EXIT_PL, 360, 350);		
	}
	if (MainClass.language == MainClass.Languages.english)
	{
		menuButtons[0] = new MenuButton(TextResources.RESUME_GAME_ENG, 360, 190);
		menuButtons[1] = new MenuButton(TextResources.MAIN_MENU_ENG, 360, 270);
		menuButtons[2] = new MenuButton(TextResources.EXIT_ENG, 360, 350);		
	}
	

	
	levelMenuButtons = new MenuButton[MAX_LEVEL_MENU_BUTTONS];
	selectedLevelMenuButton = 0;
	
	for (int i = 0; i < MAX_LEVEL_MENU_BUTTONS; i++)
	{
		if (MainClass.language == MainClass.Languages.polish)
		{
			levelMenuButtons[i] = new MenuButton("      " +TextResources.LEVEL_PL +" " +(i+1), 360, 25+(i*55), 300, 45);
		}
		if (MainClass.language == MainClass.Languages.english)
		{
			levelMenuButtons[i] = new MenuButton("      " +TextResources.LEVEL_ENG +" " +(i+1), 360, 25+(i*55), 300, 45);
		}
		levelMenuButtons[i].setLocked(true);
	}
	levelMenuButtons[0].setLocked(false);
	unlockedLevels = 1;
	
	
	smigloAnim = new Animation(1, Textures.getInstance().smiglo[3], Textures.getInstance().smiglo[2], Textures.getInstance().smiglo[1], Textures.getInstance().smiglo[0]);
	ptaki1RAnim = new Animation(10, Textures.getInstance().ptakiR1, Textures.getInstance().ptakiR2);
	
	ptaki1 = -500f;
	ptaki2 = -460f;
	ptaki3 = -420f;
	
	chmury1 = 200;
	chmury2 = 1300;
	chmury3 = 800;
	
	for (int i = 0; i < falujaceLitery.length; i++) falujaceLitery[i] = random.nextInt(falujaceLitery.length);
	
	this.addMouseListener(new MyMouseListener());
	this.addMouseMotionListener(new MyMouseListener());
	this.addMouseWheelListener(new MyMouseListener());
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


/** Otwiera podanπ stronÍ internetowπ w domyúlnej przeglπdarce systemu.
 * @param website - Adres strony internetowej do otwarcia.
 */
private void openWebsite(String website)
{
	if (isDesktopSupported) {
		desktop = Desktop.getDesktop();

		if (desktop.isSupported(Desktop.Action.BROWSE)) {
			URI url = null;
			MainClass.logging(false, Level.INFO, "Polecenie desktopowe BROWSE jest dostÍpne.");
			try {
				url = new URI(website);
			} catch (URISyntaxException e1) {
				MainClass.logging(false, Level.WARNING,  "B≥πd wywo≥ania (URL) otwierania strony domowej gry.");
				MainClass.logging(false, Level.WARNING, MainClass.getStackTrace(e1));
			}
			try {
				desktop.browse(url);
			} catch (IOException e) {
				MainClass.logging(false, Level.WARNING, "B≥πd otwierania (browse) strony domowej gry.");
				MainClass.logging(false,  Level.WARNING, MainClass.getStackTrace(e));
			}			
		}
	}
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
	
	// RESTART AFTEER DEATH
	if (gameState == GameState.Death && key.isKeyPressedOnce(KeyEvent.VK_SPACE))
	{
		MainClass.menuSound2.play();
		gameState = GameState.Game;
		objectsHandler.clearLevel();
		objectsHandler.resetLevelStatistics();
		System.gc(); // GARBAGE COLLECTOR
		MainClass.logging(false, Level.INFO, "Poziom gry zosta≥ zrestartowany.");
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
		circle_move  = 0.04f;
		radian += orbitSpeed * circle_move;
		
		// PLAIN MOVE
		smigloAnim.runAnimation();
		
		// PTAKI
		ptaki1RAnim.runAnimation();
		ptaki1 += 1.8f;
		ptaki2 += 1.8f;
		ptaki3 += 1.8f;
		
		if (ptaki1 > 1300) {
			ptaki1 = -500f;
			ptaki2 = -460f;
			ptaki3 = -420f;
		}
		
		// CHMURY
		chmury1 -= 0.5f;
		chmury2 -= 0.5f;
		chmury3 -= 0.5f;
		
		if (chmury1 < -300) chmury1 = 1100 + random.nextInt(200);
		if (chmury2 < -300) chmury2 = 1100 + random.nextInt(200);
		if (chmury3 < -300) chmury3 = 1100 + random.nextInt(200);
		
		//chmury1 = 200;
		//chmury2 = 1300;
		//chmury3 = 800;
		
		plane_move += 1;
		if (plane_move > 1300) {
			napisNaFladze = napisy[random.nextInt(napisy.length)];
			plane_move = -500;
		}
	}
	
	// INTRO
	if (gameState == GameState.Intro)
	{
		if (intro_counter == 10) {
			intro_font_y1 = MainClass.HEIGHT;
			intro_font_y2 = MainClass.HEIGHT;
			intro_font_y3 = MainClass.HEIGHT;
		}
		intro_counter ++;
		if (intro_counter > 300 && intro_counter < 1250)
		{
			if (intro_logo_size < 1.7) intro_logo_size += 0.002f;
			if (intro_logo_size > 1.4) intro_logo_size *= 1.005;
		}
		if (intro_counter > 800 && intro_counter < 1500)
		{
			intro_font_size1 += 0.0002f;
			intro_font_y1 -= 0.6f;
		}
		
		if (intro_counter > 1000 && intro_counter < 1500)
		{
			intro_font_size2 += 0.0002f;
			intro_font_y2 -= 0.6f;
		}
		
		if (intro_counter > 1300 && intro_counter < 1900)
		{
			intro_font_size3 += 0.0002f;
			intro_font_y3 -= 0.6f;
		}

		if (intro_counter > 1950 )
		{
			playMusic2();
			objectsHandler.clearLevel();
			objectsHandler.resetLevelStatistics();
			System.gc();
			LEVEL = 1;
			objectsHandler.loadLevel(LEVEL);
			cam.setX(0);
			player = objectsHandler.getPlayer();
			achievements.restartLevel();
			MainClass.logging(false, Level.INFO, "RozpoczÍto poziom "+selectedLevelMenuButton);
			gameState = GameState.Game;
		}
	}
	
	
	// OB£UGA MENU G£”WNEGO
	if (gameState == GameState.MainMenu)
	{
		
		for (int i = 0; i < MAX_MAIN_MENU_BUTTONS; i++)
		{
			if (i != selectedMainMenuButton) mainMenuButtons[i].setSelected(false);
		}
		
		
		// OBS£UGA FALUJACYCH LITER
		
		for (int i = 0; i < falujaceLitery.length; i++)
		{
			
			if (!literyUp[i] && falujaceLitery[i] > -10) falujaceLitery[i] -= 0.6f;
			if (literyUp[i] && falujaceLitery[i] < 0) falujaceLitery[i] += 0.6f;
			
			if (falujaceLitery[i] >= 0) literyUp[i] = false;
			if (falujaceLitery[i] <= -10) literyUp[i] = true;
		}
		

		if (key.isKeyPressedOnce(KeyEvent.VK_DOWN) || key.isKeyPressedOnce(KeyEvent.VK_S))
		{
			if (selectedMainMenuButton < MAX_MAIN_MENU_BUTTONS)
			{
				MainClass.menuSound1.play();
				if (selectedMainMenuButton == MAX_MAIN_MENU_BUTTONS-1) selectedMainMenuButton = 0; 
				else selectedMainMenuButton++;				
			}
		}
		
		if (key.isKeyPressedOnce(KeyEvent.VK_UP) || key.isKeyPressedOnce(KeyEvent.VK_W))
		{
			if (selectedMainMenuButton >= 0)
			{
				MainClass.menuSound1.play();	
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
			case 0: intro_counter = 0;
					intro_logo_size = 0.1f;
					intro_font_size1 = 0.1f;
					intro_font_size2 = 0.1f;
					intro_font_size3 = 0.1f;
					intro_font_y1 = 0;
					intro_font_y2 = 0;
					intro_font_y3 = 0;
					gameState = GameState.LevelChoose;
					break;				
			case 1: gameState = GameState.JakGrac;
					break;
			case 2: scrollScreenY = 0;
					readHoFRecords = true;
					gameState = GameState.NajlepszeWyniki;
					break;
			case 3: scrollScreenY = 0;
					gameState = GameState.Osiagniecia;
					break;
			case 4: gameState = GameState.Znajdzki;
					break;
			case 5: gameState = GameState.OGrze;
					break;
			case 6: gameState = GameState.Zakoncz;
					break;
			}
			key.update();
		}
	}
	
	
	// INTRO DO GRY
	if (gameState == GameState.Intro)
	{
		if (key.isKeyPressedOnce(KeyEvent.VK_ESCAPE)) gameState = GameState.MainMenu;
		if (key.isKeyPressedOnce(KeyEvent.VK_ENTER))
		{
			objectsHandler.clearLevel();
			objectsHandler.resetLevelStatistics();
			System.gc();
			LEVEL = 1;
			objectsHandler.loadLevel(LEVEL);
			cam.setX(0);
			player = objectsHandler.getPlayer();
			achievements.restartLevel();
			MainClass.logging(false, Level.INFO, "RozpoczÍto poziom "+selectedLevelMenuButton);
			playMusic1();
			gameState = GameState.Game;
		}
	}
	
	// WYWO£ANIE OUTRO
	if (gameState == GameState.Game && endgame)
	{
		for (int i = 0; i < baloniki.length; i++)
		{
			baloniki[i] = MainClass.HEIGHT + random.nextInt(150) + 1;
		}
		outro_counter = 0;
		gameState = GameState.Outro;
	}
	
	if (gameState == GameState.Outro)
	{
		outro_counter++;
		
		for (int i = 0; i < baloniki.length; i++)
		{
			baloniki[i]--;
		}
		
		if (key.isKeyPressedOnce(KeyEvent.VK_ENTER))
		{
			objectsHandler.clearLevel();
			objectsHandler.resetLevelStatistics();
			endgame = false;
			player = objectsHandler.getPlayer();
			achievements.restartLevel();
			System.gc();
			gameState = GameState.MainMenu;
		}
	}
	
	// SKIP LEVEL
	if (gameState == GameState.Game)
	{
		if (key.isKeyPressedOnce(KeyEvent.VK_F1))
		{
			gameState = GameState.Game;
			MainScreen.millis = 0;
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
	}
	
	
	// MENU WYBORU POZIOM”W
	if (gameState == GameState.LevelChoose)
	{
		for (int i = 0; i < MAX_LEVEL_MENU_BUTTONS; i++)
		{
			if (i != selectedLevelMenuButton) levelMenuButtons[i].setSelected(false);
		}
		
		if (achievements.isComplete1LevelComplete()) {
			levelMenuButtons[1].setLocked(false);
			unlockedLevels = 2;
		}
		if (achievements.isComplete2LevelComplete()) {
			levelMenuButtons[2].setLocked(false);
			unlockedLevels = 3;
		}
		if (achievements.isComplete3LevelComplete()) {
			levelMenuButtons[3].setLocked(false);
			unlockedLevels = 4;
		}
		if (achievements.isComplete4LevelComplete()) {
			levelMenuButtons[4].setLocked(false);
			unlockedLevels = 5;
		}
		if (achievements.isComplete5LevelComplete()) {
			levelMenuButtons[5].setLocked(false);
			unlockedLevels = 6;
		}
		if (achievements.isComplete6LevelComplete()) {
			levelMenuButtons[6].setLocked(false);
			unlockedLevels = 7;
		}
		if (achievements.isComplete7LevelComplete()) {
			levelMenuButtons[7].setLocked(false);
			unlockedLevels = 8;
		}
		if (achievements.isComplete8LevelComplete()) {
			levelMenuButtons[8].setLocked(false);
			unlockedLevels = 9;
		}
		if (achievements.isComplete9LevelComplete()) {
			levelMenuButtons[9].setLocked(false);
			unlockedLevels = 10;
		}

		
		if (key.isKeyPressedOnce(KeyEvent.VK_DOWN) || key.isKeyPressedOnce(KeyEvent.VK_S))
		{
			if (selectedLevelMenuButton < unlockedLevels)
			{
				MainClass.menuSound1.play();
				levelMenuButtons[selectedLevelMenuButton].setSelected(false);
				if (selectedLevelMenuButton == unlockedLevels-1) selectedLevelMenuButton = 0; 
				else selectedLevelMenuButton++;				
			}
		}
		
		if (key.isKeyPressedOnce(KeyEvent.VK_UP) || key.isKeyPressedOnce(KeyEvent.VK_W))
		{
			if (selectedLevelMenuButton >= 0)
			{
				MainClass.menuSound1.play();
				levelMenuButtons[selectedLevelMenuButton].setSelected(false);
				if (selectedLevelMenuButton == 0) selectedLevelMenuButton = unlockedLevels-1;
				else selectedLevelMenuButton--;
			}
		}
		
		if (key.isKeyPressedOnce(KeyEvent.VK_ESCAPE))
		{
			gameState = GameState.MainMenu;
		}
		
		if (key.isKeyPressedOnce(KeyEvent.VK_ENTER))
		{
			MainClass.menuSound2.play();		
			objectsHandler.clearLevel();
			objectsHandler.resetLevelStatistics();
			System.gc();
			LEVEL = selectedLevelMenuButton+1;
			if (LEVEL == 1) {
				gameState = GameState.Intro;
			}
			else {
				objectsHandler.loadLevel(LEVEL);
				cam.setX(0);
				player = objectsHandler.getPlayer();
				achievements.restartLevel();
				MainClass.logging(false, Level.INFO, "RozpoczÍto poziom "+selectedLevelMenuButton);
				playMusic1();
				gameState = GameState.Game;				
			}
		}
	}

	
	
	// OBS£UGA MENU W TRAKCIE GRY
	if (gameState == GameState.Menu)
	{
		
		for (int i = 0; i < MAX_MENU_BUTTONS; i++)
		{
			if (i != selectedMenuButton) menuButtons[i].setSelected(false);
		}
		
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
			case 1: objectsHandler.clearLevel();
					objectsHandler.resetLevelStatistics();
					System.gc();
					objectsHandler.loadLevel(1);
					cam.setX(0);
					player = objectsHandler.getPlayer();
					achievements.restartLevel();
					MainClass.logging(false, Level.INFO, "PowrÛt do menu g≥Ûwnego gry.");
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
		
		if (key.isKeyPressed(KeyEvent.VK_DOWN) && scrollScreenY < ((hallOfFame.getHallOfFameList().size() - 9) * 60))
		{
			scrollScreenY += 5;
		}
		
		if (key.isKeyPressed(KeyEvent.VK_UP) && scrollScreenY > 0)
		{
			scrollScreenY -= 5;
		}
		
		if (key.isKeyPressedOnce(KeyEvent.VK_ESCAPE))
		{
			MainClass.menuSound2.play();
			gameState = GameState.MainMenu;
		}
	}


	// MENU OSIAGNIECIA
	if (gameState == GameState.Osiagniecia)
	{
		
		if (key.isKeyPressed(KeyEvent.VK_DOWN) && scrollScreenY < ((Achievements.maxAchievements - 9) * 60))
		{
			scrollScreenY += 5;
		}
		
		if (key.isKeyPressed(KeyEvent.VK_UP) && scrollScreenY > 0)
		{
			scrollScreenY -= 5;
		}
		
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
	if (player.isLevelFinished()) {
		
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
			MainClass.logging(false, Level.INFO, "Zapisano kolejnego gracza.");
			MainClass.nc.addAnotherPlayerToHoF(new HallOfFamePlayer(playerName, SCORE, millis, LEVEL));
		}
	}
	
	// GRA DZIA£A
	if (gameState == GameState.Game && !player.isLevelFinished() && player.getHealth() > 0) {
		objectsHandler.tick();
		cam.tick(player);
		timeTick();
	}

	
	// ENTER TO NEW LEVEL
	if (gameState == GameState.NextLevel && player.isLevelFinished() && key.isKeyPressedOnce(KeyEvent.VK_ENTER)) // ENTER PO WPISANIU IMIENIA PRZENOSI NA NOWY POZIOM
	{
		gameState = GameState.Game;
		MainScreen.millis = 0;
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
	millis += (1000/60);
	time = new SimpleDateFormat("mm:ss:SSS").format(new Date(millis));
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
		    MainClass.logging(false, Level.INFO, "Plik osiπgniÍÊ " +MainClass.achievementsFile.getName() +" zosta≥ poprawnie zapisany.");
			}
			catch (IOException ioe)
			{
				MainClass.logging(false, Level.WARNING, "B≥πd zapisu plikÛw osiπgniÍÊ " +MainClass.achievementsFile.getName());
				MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(ioe));
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


private static BufferedImage toBufferedImage(Image img)
{
    if (img instanceof BufferedImage)
    {
        return (BufferedImage) img;
    }

    // Create a buffered image with transparency
    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    // Draw the image on to the buffered image
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();

    // Return the buffered image
    return bimage;
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
		
		g2d.drawImage(Textures.getInstance().sun, (int) (430+ orbitRadius1 * Math.cos(radian)), (int) (260 + orbitRadius2 * Math.sin(radian)), this);
		
		g2d.drawImage(Textures.getInstance().bg_gory, (int) (bg_move), (int) (MainClass.HEIGHT - 305), null);
		g2d.drawImage(Textures.getInstance().bg_gory, (int) (bg_move+1000), (int) (MainClass.HEIGHT - 305), null);
		
		
		// CHMURY
		
		if (chmury1 > -200 && chmury1 < 1000) g2d.drawImage(Textures.getInstance().clouds1, (int) (chmury1), 70, null);
		if (chmury3 > -200 && chmury3 < 1000) g2d.drawImage(Textures.getInstance().clouds3, (int) (chmury3), 110, null);
		if (chmury2 > -200 && chmury2 < 1000) g2d.drawImage(Textures.getInstance().clouds2, (int) (chmury2), 150, null);
		
		
		// PTAKI
		if (ptaki1 > -220 && ptaki1 < 890)
		{
			ptaki1RAnim.drawAnimation(g2d, (int) (100+ptaki1), 80, false);
			ptaki1RAnim.drawAnimation(g2d, (int) (100+ptaki2), 100, false);
			ptaki1RAnim.drawAnimation(g2d, (int) (100+ptaki3), 90, false);			
		}
		
		
		//plane_move = 300;
		if (plane_move > -170 && plane_move < 1300)
		{
			// SAMOLOT
			g2d.drawImage(Textures.getInstance().planeR, plane_move, 15, null);
			smigloAnim.drawAnimation(g2d, plane_move+155, 35, false);
			
			g2d.setColor(Color.BLACK);
			g2d.drawLine(plane_move+3, 78, plane_move-30, 78);
			g2d.drawImage(Textures.getInstance().flaga, plane_move-302, 65, null);
			g2d.setFont(MainClass.smokunFont.deriveFont(Font.PLAIN, 19f));
			g2d.drawString(napisNaFladze, plane_move - 285, 85);
		}
		
		
		for (int i = 0; i < MAX_MAIN_MENU_BUTTONS; i++) 
		{
			mainMenuButtons[i].render(g2d);
		}
		mainMenuButtons[selectedMainMenuButton].setSelected(true);
	}
	
	
	// FALUJACE LITERY
	if (gameState == GameState.MainMenu)
	{		
		g2d.drawImage(Textures.getInstance().literaT, 95, (int) (MainClass.HEIGHT - 100 + falujaceLitery[0]), null);
		g2d.drawImage(Textures.getInstance().literaE, 125, (int) (MainClass.HEIGHT - 100 + falujaceLitery[1]), null);
		g2d.drawImage(Textures.getInstance().literaQ, 155, (int) (MainClass.HEIGHT - 100 + falujaceLitery[2]), null);
		g2d.drawImage(Textures.getInstance().literaI, 185, (int) (MainClass.HEIGHT - 100 + falujaceLitery[3]), null);
		g2d.drawImage(Textures.getInstance().literaL, 205, (int) (MainClass.HEIGHT - 100 + falujaceLitery[4]), null);
		g2d.drawImage(Textures.getInstance().literaA, 230, (int) (MainClass.HEIGHT - 100 + falujaceLitery[5]), null);
		
		g2d.drawImage(Textures.getInstance().literaP, 285, (int) (MainClass.HEIGHT - 100 + falujaceLitery[6]), null);
		g2d.drawImage(Textures.getInstance().literaL, 315, (int) (MainClass.HEIGHT - 100 + falujaceLitery[7]), null);
		g2d.drawImage(Textures.getInstance().literaA, 340, (int) (MainClass.HEIGHT - 100 + falujaceLitery[8]), null);
		g2d.drawImage(Textures.getInstance().literaT, 370, (int) (MainClass.HEIGHT - 100 + falujaceLitery[9]), null);
		g2d.drawImage(Textures.getInstance().literaF, 400, (int) (MainClass.HEIGHT - 100 + falujaceLitery[10]), null);
		g2d.drawImage(Textures.getInstance().literaO, 430, (int) (MainClass.HEIGHT - 100 + falujaceLitery[11]), null);
		g2d.drawImage(Textures.getInstance().literaR, 460, (int) (MainClass.HEIGHT - 100 + falujaceLitery[12]), null);
		g2d.drawImage(Textures.getInstance().literaM, 490, (int) (MainClass.HEIGHT - 100 + falujaceLitery[13]), null);
		g2d.drawImage(Textures.getInstance().literaE, 530, (int) (MainClass.HEIGHT - 100 + falujaceLitery[14]), null);
		g2d.drawImage(Textures.getInstance().literaR, 560, (int) (MainClass.HEIGHT - 100 + falujaceLitery[15]), null);
		
		if (isDesktopSupported) g2d.drawImage(Textures.getInstance().websiteButton, 330, (int) (MainClass.HEIGHT - 25), null);
		
		if (MainClass.language == MainClass.Languages.polish) g2d.drawImage(Textures.getInstance().polishFlagImage, MainClass.WIDTH - 55, MainClass.HEIGHT - 32, null);
		if (MainClass.language == MainClass.Languages.english) g2d.drawImage(Textures.getInstance().englishFlagImage, MainClass.WIDTH - 55, MainClass.HEIGHT - 32, null);
	}
	
	// FPS CAP WSZ DZIE OPR”CZ GRY
	if (gameState == GameState.Game || gameState == GameState.Intro || gameState == GameState.Outro) MainClass.fpsCap = false;
	else MainClass.fpsCap = true;
	
		
	if ((!player.isLevelFinished()) && (gameState==GameState.Game || gameState==GameState.Death || gameState==GameState.Menu || gameState==GameState.NextLevel || gameState == GameState.Outro))
	{ 
		////// CAM MOVING HERE
		g2d.translate(cam.getX(), cam.getY());  // CAM BEGINNING
			objectsHandler.render(g);
		g2d.translate(-cam.getX(), -cam.getY()); // CAM ENDING
	}
	
	
	// INTRO
	if (gameState == GameState.Intro)
	{
		g2d.drawImage(Textures.getInstance().backGroundMountains, 0, 0, MainClass.WIDTH, MainClass.HEIGHT, null);
		
		g2d.setColor(MainClass.fontColor);
		
		if (intro_counter > 50 && intro_counter < 250)
		{
			g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 34f));
			g2d.drawString(introText1PL, 200, 100);	
		}
		
		if (intro_counter > 300 && intro_counter < 1250)
		{
			double ratio = (double) Textures.getInstance().intro_game_logo.getWidth() / Textures.getInstance().intro_game_logo.getHeight();
			
			intro_logo_image = toBufferedImage(Textures.getInstance().intro_game_logo.getScaledInstance((int) (Textures.getInstance().intro_game_logo.getWidth() / (ratio * intro_logo_size)),
					(int) (Textures.getInstance().intro_game_logo.getHeight() / (ratio * intro_logo_size)), Image.SCALE_DEFAULT));
					
			g2d.drawImage(intro_logo_image, (int) ((MainClass.WIDTH / 2) - (intro_logo_image.getWidth() / 2)), (int) ((MainClass.HEIGHT / 2) - (intro_logo_image.getHeight() /2)), null);
		}
		
		if (intro_counter > 800 && intro_counter < 1300)
		{
			double ratio1 = (double) Textures.getInstance().intro_game_text1.getWidth() / Textures.getInstance().intro_game_text1.getHeight();
			
			BufferedImage text1image = toBufferedImage(Textures.getInstance().intro_game_text1.getScaledInstance((int) (Textures.getInstance().intro_game_text1.getWidth() / (ratio1 * intro_font_size1)),
					(int) (Textures.getInstance().intro_game_text1.getHeight() / (ratio1 * intro_font_size1)), Image.SCALE_DEFAULT));
			
			g2d.drawImage(text1image, (int) ((MainClass.WIDTH / 2) - (text1image.getWidth() / 2)), (int) (intro_font_y1), null);
		}
		
		
		if (intro_counter > 900 && intro_counter < 1500)
		{
			double ratio2 = (double) Textures.getInstance().intro_game_text2.getWidth() / Textures.getInstance().intro_game_text2.getHeight();
			
			BufferedImage text2image = toBufferedImage(Textures.getInstance().intro_game_text2.getScaledInstance((int) (Textures.getInstance().intro_game_text2.getWidth() / (ratio2 * intro_font_size2)),
					(int) (Textures.getInstance().intro_game_text2.getHeight() / (ratio2 * intro_font_size2)), Image.SCALE_DEFAULT));	
			g2d.drawImage(text2image, (int) ((MainClass.WIDTH / 2) - (text2image.getWidth() / 2)), (int) (intro_font_y2), null);
		}
		
		if (intro_counter > 1000 && intro_counter < 1900)
		{
			double ratio3 = (double) Textures.getInstance().intro_game_text3.getWidth() / Textures.getInstance().intro_game_text3.getHeight();
			
			BufferedImage text3image = toBufferedImage(Textures.getInstance().intro_game_text3.getScaledInstance((int) (Textures.getInstance().intro_game_text3.getWidth() / (ratio3 * intro_font_size3)),
					(int) (Textures.getInstance().intro_game_text3.getHeight() / (ratio3 * intro_font_size3)), Image.SCALE_DEFAULT));	
			g2d.drawImage(text3image, (int) ((MainClass.WIDTH / 2) - (text3image.getWidth() / 2)), (int) (intro_font_y3), null);
		}
		
		//g2d.drawString(intro_counter+"", 100, 100);
	}

	
	// OUTRO
	if (endgame && gameState == GameState.Outro)
	{
		g2d.drawImage(Textures.getInstance().backGroundMountains, 0, 0, MainClass.WIDTH, MainClass.HEIGHT, null);
		for (int i = 0; i < baloniki.length; i++)
		{
			g2d.drawImage(Textures.getInstance().balony[i], 50 + (i* 10), baloniki[i], null);
		}
		
		g2d.setColor(MainClass.fontColor);
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 36f));
		
		if (outro_counter > 100) g2d.drawString(outroText1, 370, 240);
		if (outro_counter > 190) g2d.drawString(outroText2, 330, 290);
		if (outro_counter > 220) g2d.drawString(outroText3, 330, 340);
		if (outro_counter > 350) g2d.drawString(outroText4, 350, 390);
		
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 24f));
		if (outro_counter > 500) g2d.drawString("Naciúnij ENTER aby wrÛciÊ na ranczo...", 300, 580);
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
	if (player.isImmortality()) {
		g2d.drawImage(Textures.getInstance().immortalIcon, -10, 210, null);
		g2d.setColor(Color.ORANGE);
		g2d.fillRect(90, 295 - (player.getImmortality_time()/14), 10, (player.getImmortality_time()/14));
	}

	
	// ACHIEVEMENTS
	if (showMessage) showMessage(g2d, achievements.getAchievementTextShort(), achievements.getAchievementImage(), achievements.getAchievementCount());
	else saveAchievementsToFile = false;
		
	// KONIEC POZIOMU
	if (player.isLevelFinished())
	{
		gameState = GameState.NextLevel;
		if (millis < (1000 * 60)) achievements.addSprinterCount();
		TOTAL_SCORE = SCORE + (int) time_bonus;
	}
		
	// HAND MENU
	if (gameState == GameState.Game)
	{
		g2d.drawImage(Textures.getInstance().handMenu, 40, MainClass.HEIGHT - 200, null);
		
		for (int i = 0; i < handMenuItem.length; i++) {
			
			if (collectiblesList[i] >= 5) handMenuItem[i].setActive(true);
			else handMenuItem[i].setActive(false);
			
			handMenuItem[i].drawItem(g2d);
			g2d.drawString(collectiblesList[i] +"", (int) handMenuItem[i].getX() + 16, (int) handMenuItem[i].getY() + 22);
		}
		
		if (showInfo > -1) {
			
			String info = "";
			switch (showInfo)
			{
				case 0: if (MainClass.language == MainClass.Languages.polish) info = "      Ma≥a regeneracja zdrowia";
						if (MainClass.language == MainClass.Languages.english) info = "     Small health regeneration";
						break;
				case 1: if (MainClass.language == MainClass.Languages.polish) info = "      ZwiÍkszenie zdrowia o 1";
						if (MainClass.language == MainClass.Languages.english) info = "    Increasing max health by 1";
						break;
				case 2: if (MainClass.language == MainClass.Languages.polish) info = "     Regeneracja ca≥ego zdrowia";
						if (MainClass.language == MainClass.Languages.english) info = "     Full health regeneration";
						break;
				case 3: if (MainClass.language == MainClass.Languages.polish) info = "       Tymczasowa nieúmiertelnoúÊ";
						if (MainClass.language == MainClass.Languages.english) info = "        Temporary immortality";
						break;
				case 4: if (MainClass.language == MainClass.Languages.polish) info = "                UsuÒ pszczo≥y";
						if (MainClass.language == MainClass.Languages.english) info = "               Remove bees";
						break;
				case 5: if (MainClass.language == MainClass.Languages.polish) info = "                UsuÒ kaktusy";
						if (MainClass.language == MainClass.Languages.english) info = "               Remove cactuses";
						break;
				case 6: if (MainClass.language == MainClass.Languages.polish) info = "UsuniÍcie wszystkich przeciwnikÛw";
						if (MainClass.language == MainClass.Languages.english) info = "       Remove all enemies";
						break;
			}
			g2d.setColor(new Color(100, 100, 255));
			g2d.drawImage(Textures.getInstance().text_bg, 230, MainClass.HEIGHT - 55, null);
			g2d.setColor(Color.BLUE);
			g2d.drawString(info+"", 250, MainClass.HEIGHT - 30);
		}
	}

	// HUD gry
	if (gameState==GameState.Game || gameState==GameState.Death || gameState==GameState.Menu || gameState==GameState.NextLevel || gameState == GameState.Outro)
	{
		// PLAYER HEALTH
		for (int i = 0; i < player.getHealth(); i++) g.drawImage(Textures.getInstance().heart, 360+(i*40), 5, 40, 40,null);
		
		
		g2d.drawImage(Textures.getInstance().gameUIbg, 0, 0, null);
		g2d.drawImage(Textures.getInstance().gameUIbg, 770, 0, null);
		
		
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 38f));
		g2d.setColor(Color.BLUE);
		
		if (MainClass.language == MainClass.Languages.polish) g2d.drawString(TextResources.LEVEL_PL +": " +MainScreen.LEVEL, 800, 40);
		if (MainClass.language == MainClass.Languages.english) g2d.drawString(TextResources.LEVEL_ENG +": " +MainScreen.LEVEL, 800, 40);
		
		if (MainClass.language == MainClass.Languages.polish) g2d.drawString(TextResources.COINS_PL +": " +MainScreen.COINS, 10, 40);
		if (MainClass.language == MainClass.Languages.english) g2d.drawString(TextResources.COINS_ENG +": " +MainScreen.COINS, 10, 40);
		
		if (MainClass.language == MainClass.Languages.polish) g2d.drawString(TextResources.SCORE_PL +": "  +MainScreen.SCORE, 10, 80);
		if (MainClass.language == MainClass.Languages.english) g2d.drawString(TextResources.SCORE_ENG +": " +MainScreen.SCORE, 10, 80);
		
		g2d.setFont(MainClass.verdana14Font);
		if (MainClass.language == MainClass.Languages.polish) g2d.drawString(TextResources.TIME_PL +": " +MainScreen.time, MainClass.WIDTH - 200, 70);
		if (MainClass.language == MainClass.Languages.english) g2d.drawString(TextResources.TIME_ENG +": " +MainScreen.time, MainClass.WIDTH - 200, 70);
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
	
	// LEVEL CHOOSE
	if (gameState == GameState.LevelChoose)
	{
		g2d.drawImage(Textures.getInstance().backGroundMountains, 0, 0, MainClass.WIDTH, MainClass.HEIGHT, null);
		//g2d.drawImage(Textures.getInstance().menuBg, 320, 140, null);
		for (int i = 0; i < MAX_LEVEL_MENU_BUTTONS; i++) 
		{
			levelMenuButtons[i].render(g2d);
		}
		levelMenuButtons[selectedLevelMenuButton].setSelected(true);
	}
	
	
	if (gameState == GameState.Game) makeBgImage = false;
	
	hud.showGameHud(g2d, gameState, achievements, hallOfFame, this.fps_count, this.ticks_count);
	
	
	if (gameState == GameState.Znajdzki)
	{
		g2d.drawImage(Textures.getInstance().collectiblesImage, 90, 0, null);
		g2d.setFont(MainClass.verdana18Font);
		g2d.setColor(MainClass.fontColor);
		
		
		for (int i = 0; i < Textures.getInstance().collectible.length; i++)
		{
			g2d.drawImage(Textures.getInstance().collectible[i], 130, 70 + (i * 70), null);
			g2d.drawString("("+collectiblesList[i]+")", 180, 95 + (i * 70));
			switch (i)
			{
			case 0: g2d.drawString("regeneracja 1 punktu zdrowia", 250, 95 + (i * 70));
					break;
			case 1: g2d.drawString("zwiÍkszenie maksymalnego zdrowia o 1", 250, 95 + (i * 70));
					break;
			case 2: g2d.drawString("regeneracja ca≥ego zdrowia", 250, 95 + (i * 70));
					break;
			case 3: g2d.drawString("tymczasowa nieúmiertelnoúÊ postaci", 250, 95 + (i * 70));
					break;
			case 4: g2d.drawString("usuwa wszystkie pszczo≥y na danym poziomie", 250, 95 + (i * 70));
					break;
			case 5: g2d.drawString("usuwa wszystkie kaktusy na danym poziomie", 250, 95 + (i * 70));
					break;
			case 6: g2d.drawString("usuwa wszystkich przeciwnikÛw na danym poziomie", 250, 95 + (i * 70));
					break;
			}
		}
		
	}
	
	
	if (gameState == GameState.Osiagniecia)
	{
		g2d.drawImage(Textures.getInstance().achievementsMenuBGImage, 85, 0, null);
		g2d.setFont(MainClass.verdana14Font);
		g2d.setColor(Color.BLACK);
		g2d.setColor(MainClass.fontColor);
		if (MainClass.language == MainClass.Languages.polish) g2d.drawString(TextResources.achievements_PL +": " +achievements.getAchievementsUnlocked()+"/" +Achievements.maxAchievements,380, 34);
		if (MainClass.language == MainClass.Languages.english) g2d.drawString(TextResources.achievements_ENG +": " +achievements.getAchievementsUnlocked()+"/" +Achievements.maxAchievements,380, 34);

		
		defaultClip = g2d.getClip();
		
		g2d.setClip(new Rectangle(110, 40, (int) Textures.getInstance().achievementsMenuBGImage.getWidth()-50, (int) Textures.getInstance().achievementsMenuBGImage.getHeight()-56));
		
		g2d.translate(0, -scrollScreenY); // CAM BEGINNING
		
		for (int i = 0; i < Achievements.maxAchievements; i++)
		{

			if (achievements.getAchievementsList().get(i))
			{
				switch (i)
				{
				case 0:	g2d.drawString(achievements.getJump10TextShort() +"  " +achievements.getJump10Text(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().jump10Image, 110, 40 + (i*60), null);
						break;
				case 1: g2d.drawString(achievements.getJump25TextShort() +"  " +achievements.getJump25Text(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().jump25Image, 110, 40 + (i*60), null);					
						break;
				case 2:	g2d.drawString(achievements.getJump50TextShort() +"  " +achievements.getJump50Text(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().jump50Image, 110, 40 + (i*60), null);
						break;
				case 3:	g2d.drawString(achievements.getCoin20TextShort() +"  " +achievements.getCoin20Text(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().coin20Image, 110, 40 + (i*60), null);
						break;
				case 4:	g2d.drawString(achievements.getCoin50TextShort() +"  " +achievements.getCoin50Text(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().coin50Image, 110, 40 + (i*60), null);
						break;
				case 5:	g2d.drawString(achievements.getCoin100TextShort() +"  " +achievements.getCoin100Text(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().coin100Image, 110, 40 + (i*60), null);
						break;
				case 6:	g2d.drawString(achievements.getCoin150TextShort() +"  " +achievements.getCoin150Text(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().coin150Image, 110, 40 + (i*60), null);
						break;
				case 7:	g2d.drawString(achievements.getPowerup3TextShort() +"  " +achievements.getPowerup3Text(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().powerup3Image, 110, 40 + (i*60), null);
						break;
				case 8: g2d.drawString(achievements.getComplete1LevelTextShort() +"  " +achievements.getComplete1LevelText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().complete1LevelImage, 110, 40 + (i*60), null);
						break;
				case 9: g2d.drawString(achievements.getComplete2LevelTextShort() +"  " +achievements.getComplete2LevelText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().complete2LevelImage, 110, 40 + (i*60), null);
						break;
				case 10:g2d.drawString(achievements.getComplete3LevelTextShort() +"  " +achievements.getComplete3LevelText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().complete3LevelImage, 110, 40 + (i*60), null);
						break;
				case 11:g2d.drawString(achievements.getComplete4LevelTextShort() +"  " +achievements.getComplete4LevelText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().complete4LevelImage, 110, 40 + (i*60), null);
						break;
				case 12:g2d.drawString(achievements.getComplete5LevelTextShort() +"  " +achievements.getComplete5LevelText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().complete5LevelImage, 110, 40 + (i*60), null);
						break;
				case 13:g2d.drawString(achievements.getComplete6LevelTextShort() +"  " +achievements.getComplete6LevelText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().complete6LevelImage, 110, 40 + (i*60), null);
						break;
				case 14:g2d.drawString(achievements.getComplete7LevelTextShort() +"  " +achievements.getComplete7LevelText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().complete7LevelImage, 110, 40 + (i*60), null);
						break;
				case 15:g2d.drawString(achievements.getComplete8LevelTextShort() +"  " +achievements.getComplete8LevelText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().complete8LevelImage, 110, 40 + (i*60), null);
						break;
				case 16:g2d.drawString(achievements.getComplete9LevelTextShort() +"  " +achievements.getComplete9LevelText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().complete9LevelImage, 110, 40 + (i*60), null);
						break;
				case 17:g2d.drawString(achievements.getComplete10LevelTextShort() +"  " +achievements.getComplete10LevelText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().complete10LevelImage, 110, 40 + (i*60), null);
						break;
				case 18:g2d.drawString(achievements.getFindAllCoinsTextShort() +"  " +achievements.getFindAllCoinsText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().findAllCoinsImage, 110, 40 + (i*60), null);
						break;
				case 19:g2d.drawString(achievements.getFindAllPowerupsTextShort() +"  " +achievements.getFindAllPowerupsText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().findAllPowerupsImage, 110, 40 + (i*60), null);
						break;
				case 20:g2d.drawString(achievements.getNoHarmTextShort() +"  " +achievements.getNoHarmText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().noHarmImage, 110, 40 + (i*60), null);
						break;
				case 21:g2d.drawString(achievements.getMegaJumpTextShort() +". " +achievements.getMegaJumpText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().megaJumpImage, 110, 40 + (i*60), null);
						break;
				case 22:g2d.drawString(achievements.getSprinterTextShort() +". " +achievements.getSprinterText(), 175, 70 + (i*60));
						g2d.drawImage(Textures.getInstance().sprinterImage, 110, 40 + (i*60), null);
						break;
				}				
			}
			else
			{
				if (MainClass.language == MainClass.Languages.polish) g2d.drawString(TextResources.HIDDEN_PL, 175, 70 + (i*60));
				if (MainClass.language == MainClass.Languages.english) g2d.drawString(TextResources.HIDDEN_ENG, 175, 70 + (i*60));
				
				g2d.drawImage(Textures.getInstance().blank, 110, 40 + (i*60), null);
			}
		}
		g2d.setClip(defaultClip);
	}
	
	
	if (gameState == GameState.NajlepszeWyniki)
	{
		
		if (readHoFRecords)
		{
			hallOfFame.setHallOfFame(MainClass.nc.getHOFRecordsFromServer());
			readHoFRecords = false;
		}
		
		g2d.setColor(MainClass.fontColor);
		g2d.drawImage(Textures.getInstance().hallOfFameImage, 100, 0, null);
		
		g2d.setFont(MainClass.verdana18Font);
		
		if (MainClass.language == MainClass.Languages.polish) g2d.drawString(TextResources.HALL_OF_FAME_COLUMNS_PL, 140, 45);
		if (MainClass.language == MainClass.Languages.english) g2d.drawString(TextResources.HALL_OF_FAME_COLUMNS_ENG, 140, 45);
		
		g2d.drawLine(140, 51, 820, 51);
		g2d.drawLine(140, 52, 820, 52);
		g2d.drawLine(140, 53, 820, 53);
		
		// CONNECTION ERROR ?
		if (!NetworkConnector.connected) {
			if (MainClass.language == MainClass.Languages.polish) g2d.drawString(TextResources.CONNECTION_ERROR_PL, 300, 300);
			if (MainClass.language == MainClass.Languages.english) g2d.drawString(TextResources.CONNECTION_ERROR_ENG, 300, 300);
		}
		
		
		defaultClip = g2d.getClip();
		
		g2d.setClip(new Rectangle(110, 56, (int) Textures.getInstance().hallOfFameImage.getWidth(), (int) Textures.getInstance().hallOfFameImage.getHeight()-77));
		
		g2d.translate(0, -scrollScreenY);
				
		// CLIPPING DO DANEJ WIELKOåCI TAKIEJ JAK OBRAZEK T£A DL TEGO OKNA !
		
		for (int i = 0; i < hallOfFame.getHallOfFameList().size(); i++)
		{
		 	g2d.drawString(i+1+"", 175, 80 + (i*60));
		 	g2d.drawString(hallOfFame.getHallOfFameList().get(i).getLevel()+"", 280, 80 + (i*60));
		 	g2d.drawString(hallOfFame.getHallOfFameList().get(i).getName()+"", 360, 80+(i*60));
		 	g2d.drawString(hallOfFame.getHallOfFameList().get(i).getTimeFromMilis(hallOfFame.getHallOfFameList().get(i).getMilis()), 570, 80 + (i*60));
		 	g2d.drawString(hallOfFame.getHallOfFameList().get(i).getScore()+"", 755, 80 + (i*60));
		}
		
		g2d.setClip(defaultClip);
	}
		
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
			MainClass.logging(false, Level.WARNING, "B≥πd zapisu zrzutu ekranu" +screenShotFile.getName());
			MainClass.logging(false, Level.WARNING, MainClass.getStackTrace(e));
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



/** MÛj w≥asny prywatny MouseListener
 * @author Piotrek
 *
 */
private class MyMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener
{
	@Override
	public void mouseReleased(MouseEvent me) {
		
		if (gameState == GameState.MainMenu)
		{
			
			if (me.getX() >= MainClass.WIDTH - 55 && me.getX() <= Textures.getInstance().polishFlagImage.getWidth() + MainClass.WIDTH - 55
					&& me.getY() >= MainClass.HEIGHT - 32  && me.getY() <= MainClass.HEIGHT - 32 + Textures.getInstance().polishFlagImage.getHeight())
			{
				if (MainClass.language == MainClass.Languages.polish) {
					MainClass.language = MainClass.Languages.english;

					mainMenuButtons[0].setName(TextResources.NEW_GAME_ENG);
					mainMenuButtons[1].setName(TextResources.HOW_TO_PLAY_ENG);
					mainMenuButtons[2].setName(TextResources.HALL_OF_FAME_ENG);
					mainMenuButtons[3].setName(TextResources.ACHIEVEMENTS_ENG);
					mainMenuButtons[4].setName(TextResources.COLLECTIBLES_ENG);
					mainMenuButtons[5].setName(TextResources.CREDITS_ENG);
					mainMenuButtons[6].setName(TextResources.EXIT_ENG);
					
					menuButtons[0].setName(TextResources.RESUME_GAME_ENG);
					menuButtons[1].setName(TextResources.MAIN_MENU_ENG);
					menuButtons[2].setName(TextResources.EXIT_ENG);
					
					levelMenuButtons[0].setName("      " +TextResources.LEVEL_ENG +" 1");
					levelMenuButtons[1].setName("      " +TextResources.LEVEL_ENG +" 2");
					levelMenuButtons[2].setName("      " +TextResources.LEVEL_ENG +" 3");
					levelMenuButtons[3].setName("      " +TextResources.LEVEL_ENG +" 4");
					levelMenuButtons[4].setName("      " +TextResources.LEVEL_ENG +" 5");
					levelMenuButtons[5].setName("      " +TextResources.LEVEL_ENG +" 6");
					levelMenuButtons[6].setName("      " +TextResources.LEVEL_ENG +" 7");
					levelMenuButtons[7].setName("      " +TextResources.LEVEL_ENG +" 8");
					levelMenuButtons[8].setName("      " +TextResources.LEVEL_ENG +" 9");
					levelMenuButtons[9].setName("      " +TextResources.LEVEL_ENG +" 10");
					
					return;
				}
				if (MainClass.language == MainClass.Languages.english) {
					MainClass.language = MainClass.Languages.polish;
					
					mainMenuButtons[0].setName(TextResources.NEW_GAME_PL);
					mainMenuButtons[1].setName(TextResources.HOW_TO_PLAY_PL);
					mainMenuButtons[2].setName(TextResources.HALL_OF_FAME_PL);
					mainMenuButtons[3].setName(TextResources.ACHIEVEMENTS_PL);
					mainMenuButtons[4].setName(TextResources.COLLECTIBLES_PL);
					mainMenuButtons[5].setName(TextResources.CREDITS_PL);
					mainMenuButtons[6].setName(TextResources.EXIT_PL);

					menuButtons[0].setName(TextResources.RESUME_GAME_PL);
					menuButtons[1].setName(TextResources.MAIN_MENU_PL);
					menuButtons[2].setName(TextResources.EXIT_PL);		
					
					levelMenuButtons[0].setName("      " +TextResources.LEVEL_PL +" 1");
					levelMenuButtons[1].setName("      " +TextResources.LEVEL_PL +" 2");
					levelMenuButtons[2].setName("      " +TextResources.LEVEL_PL +" 3");
					levelMenuButtons[3].setName("      " +TextResources.LEVEL_PL +" 4");
					levelMenuButtons[4].setName("      " +TextResources.LEVEL_PL +" 5");
					levelMenuButtons[5].setName("      " +TextResources.LEVEL_PL +" 6");
					levelMenuButtons[6].setName("      " +TextResources.LEVEL_PL +" 7");
					levelMenuButtons[7].setName("      " +TextResources.LEVEL_PL +" 8");
					levelMenuButtons[8].setName("      " +TextResources.LEVEL_PL +" 9");
					levelMenuButtons[9].setName("      " +TextResources.LEVEL_PL +" 10");
					
					return;
				}	
			}
		}
		
		if (gameState == GameState.Game)
		{
			for (int i = 0; i < handMenuItem.length; i++)
			{
				if (me.getX() >= handMenuItem[i].getX()+5 && me.getX() <= handMenuItem[i].getX() + handMenuItem[i].getWidth()-5
						&& me.getY() >= handMenuItem[i].getY()+5 && me.getY() <= handMenuItem[i].getY() + handMenuItem[i].getHeight()-5
						&& handMenuItem[i].isActive())  // MUST BE ACTIVE !!!
				{
					/// AKTYWACJA DANEJ MOCY nr. i
					
					if (i == 0 && player.getHealth() < player.getMaxHealth())    /// 1 MOC - ODNAWIA ZDROWIE O +1 O ILE NIE WI CEJ NIØ MAX HEALTH
					{
						player.setHealth(player.getHealth() + 1);
						
						collectiblesList[i] -= 5;
						if (collectiblesList[i] < 0) collectiblesList[i] = 0;	
					}
					
					if (i == 1)    /// 2 MOC - ZWI KSZA MAKSYMALNE ZDROWIE O 1 I UZUPE£NIA BRAKUJACE O JEDEN JESLI POTRZEBA
					{
						player.setMaxHealth(player.getMaxHealth() + 1);
						player.setHealth(player.getHealth() + 1);
						
						collectiblesList[i] -= 5;
						if (collectiblesList[i] < 0) collectiblesList[i] = 0;	
					}
					
					if (i == 2)    /// 3 MOC - ODNAWIA CA£E ZDROWIE POSTACI
					{
						player.setHealth(player.getMaxHealth());
						
						collectiblesList[i] -= 5;
						if (collectiblesList[i] < 0) collectiblesList[i] = 0;	
					}
					
					if (i == 3)    /// 4 MOC - CZASOWA NIEåMIERTELNOåC POSTACI
					{
						
						player.setImmortality(true);
						collectiblesList[i] -= 5;
						if (collectiblesList[i] < 0) collectiblesList[i] = 0;	
					}

					if (i == 4)    /// 5 MOC - USUWA WSZYSTKIE PSZCZO£Y Z PLANSZY
					{
						objectsHandler.getBee_List().clear();
						
						collectiblesList[i] -= 5;
						if (collectiblesList[i] < 0) collectiblesList[i] = 0;	
					}

					if (i == 5)    /// 6 MOC - USUWA KAKTUSY Z PLANSZY
					{
						objectsHandler.getAngryCactus_List().clear();
						
						collectiblesList[i] -= 5;
						if (collectiblesList[i] < 0) collectiblesList[i] = 0;	
					}
					
					if (i == 6)    /// 7 MOC - USUWA WSZYSTKICH PRZECIWNIK”W Z PLANSZY !
					{
						objectsHandler.getBee_List().clear();
						objectsHandler.getTumbleweed_List().clear();
						objectsHandler.getAngryCactus_List().clear();
						
						collectiblesList[i] -= 5;
						if (collectiblesList[i] < 0) collectiblesList[i] = 0;	
					}
				}
			}
		}
		
		if (gameState == GameState.MainMenu)
		{
			
			if (isDesktopSupported && me.getX() >= 330 && me.getX() <= Textures.getInstance().websiteButton.getWidth()+330
				&& me.getY() >= MainClass.HEIGHT - 25  && me.getY() <= MainClass.HEIGHT - 25 + Textures.getInstance().websiteButton.getHeight())
				openWebsite("https://wroblewskipiotr.pl/tequila/");
			{
			for (int i = 0; i < MAX_MAIN_MENU_BUTTONS; i++)
			{
				if (me.getX() >= mainMenuButtons[i].getX() && me.getX() <= (mainMenuButtons[i].getX() + mainMenuButtons[i].getWidth())
						&& me.getY() >= mainMenuButtons[i].getY() && me.getY() <= (mainMenuButtons[i].getY() + mainMenuButtons[i].getHeight()))
						{
							MainClass.menuSound2.play();
							switch (i)
							{
							case 0: intro_counter = 0;
									intro_logo_size = 0.1f;
									intro_font_size1 = 0.1f;
									intro_font_size2 = 0.1f;
									intro_font_size3 = 0.1f;
									intro_font_y1 = 0;
									intro_font_y2 = 0;
									intro_font_y3 = 0;
									//gameState = GameState.Intro;		
									gameState = GameState.LevelChoose;
									break;				
							case 1: gameState = GameState.JakGrac;
									break;
							case 2: scrollScreenY = 0;
									readHoFRecords = true;
									gameState = GameState.NajlepszeWyniki;
									break;
							case 3: scrollScreenY = 0;
									gameState = GameState.Osiagniecia;
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
			}
		}
		
		
		if (gameState == GameState.LevelChoose)
		{

			for (int i = 0; i < unlockedLevels; i++)
			{
				if (me.getX() >= levelMenuButtons[i].getX() && me.getX() <= (levelMenuButtons[i].getX() + levelMenuButtons[i].getWidth())
						&& me.getY() >= levelMenuButtons[i].getY() && me.getY() <= (levelMenuButtons[i].getY() + levelMenuButtons[i].getHeight()))
						{
							MainClass.menuSound2.play();		
							objectsHandler.clearLevel();
							objectsHandler.resetLevelStatistics();
							System.gc();
							LEVEL = selectedLevelMenuButton+1;
							if (LEVEL == 1) {
								gameState = GameState.Intro;
							}
							else {
								objectsHandler.loadLevel(LEVEL);
								cam.setX(0);
								player = objectsHandler.getPlayer();
								achievements.restartLevel();
								MainClass.logging(false, Level.INFO, "RozpoczÍto poziom "+selectedLevelMenuButton);
								playMusic1();
								gameState = GameState.Game;				
							}
						}
			}
		}
		
		
		if (gameState == GameState.Menu)
		{
			for (int i = 0; i < MAX_MENU_BUTTONS; i++)
			{
				if (me.getX() >= menuButtons[i].getX() && me.getX() <= (menuButtons[i].getX() + menuButtons[i].getWidth())
						&& me.getY() >= menuButtons[i].getY() && me.getY() <= (menuButtons[i].getY() + menuButtons[i].getHeight())) {
							MainClass.menuSound2.play();
							switch (selectedMenuButton)
							{
							case 0: gameState = GameState.Game;
									break;				
							case 1: 
									objectsHandler.clearLevel();
									objectsHandler.resetLevelStatistics();
									System.gc(); // GARBAGE COLLECTOR
									objectsHandler.loadLevel(1);
									cam.setX(0);
									player = objectsHandler.getPlayer();
									achievements.restartLevel();
									MainClass.logging(false, Level.INFO, "PowrÛt do menu g≥Ûwnego gry.");
									playMusic1();
									gameState = GameState.MainMenu;
									break;
							case 2: gameState = GameState.Zakoncz;
									break;
							}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent me) {}

	@Override
	public void mouseExited(MouseEvent me) {}

	@Override
	public void mouseClicked(MouseEvent me) {}

	@Override
	public void mousePressed(MouseEvent me) {}

	@Override
	public void mouseDragged(MouseEvent me) {}

	@Override
	public void mouseMoved(MouseEvent me) {
		
		if (gameState == GameState.Game)
		{
			showInfo = -1;
			for (int i = 0; i < handMenuItem.length; i++)
			{
				if (me.getX() >= handMenuItem[i].getX()+5 && me.getX() <= handMenuItem[i].getX() + handMenuItem[i].getWidth()-5
						&& me.getY() >= handMenuItem[i].getY()+5 && me.getY() <= handMenuItem[i].getY() + handMenuItem[i].getHeight()-5)
				{
					showInfo = i;
				}
			}
		}
		
		if (gameState == GameState.Menu)
		{
			for (int i = 0; i < MAX_MENU_BUTTONS; i++)
			{
				if (me.getX() >= menuButtons[i].getX() && me.getX() <= (menuButtons[i].getX() + menuButtons[i].getWidth())
						&& me.getY() >= menuButtons[i].getY() && me.getY() <= (menuButtons[i].getY() + menuButtons[i].getHeight()))
						{
							if (selectedMenuButton != i) MainClass.menuSound1.play();
							selectedMenuButton = i;
						}
			}			
		}
		
		if (gameState == GameState.MainMenu)
		{
			for (int i = 0; i < MAX_MAIN_MENU_BUTTONS; i++)
			{
				if (me.getX() >= mainMenuButtons[i].getX() && me.getX() <= (mainMenuButtons[i].getX() + mainMenuButtons[i].getWidth())
						&& me.getY() >= mainMenuButtons[i].getY() && me.getY() <= (mainMenuButtons[i].getY() + mainMenuButtons[i].getHeight()))
						{
							if (selectedMainMenuButton != i) MainClass.menuSound1.play();
							selectedMainMenuButton = i;
						}
			}
		}
		
		if (gameState == GameState.LevelChoose)
		{
			for (int i = 0; i < unlockedLevels; i++)
			{
				if (me.getX() >= levelMenuButtons[i].getX() && me.getX() <= (levelMenuButtons[i].getX() + levelMenuButtons[i].getWidth())
						&& me.getY() >= levelMenuButtons[i].getY() && me.getY() <= (levelMenuButtons[i].getY() + levelMenuButtons[i].getHeight()))
						{
							if (selectedLevelMenuButton != i) MainClass.menuSound1.play();
							selectedLevelMenuButton = i;
						}
			}
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent mwe) {
				
		if (gameState == GameState.Menu)
		{
			if (mwe.getWheelRotation() == -1)
			{
				if (selectedMenuButton == 0) selectedMenuButton = MAX_MENU_BUTTONS-1;
				else selectedMenuButton--;				
			}
			
			if (mwe.getWheelRotation() == 1)
			{
				if (selectedMenuButton == MAX_MENU_BUTTONS-1) selectedMenuButton = 0; 
				else selectedMenuButton++;		
			}
		}
		
		if (gameState == GameState.MainMenu)
		{
			if (mwe.getWheelRotation() == -1)
			{
				if (selectedMainMenuButton == 0) selectedMainMenuButton = MAX_MAIN_MENU_BUTTONS-1;
				else selectedMainMenuButton--;				
			}
			
			if (mwe.getWheelRotation() == 1)
			{
				if (selectedMainMenuButton == MAX_MAIN_MENU_BUTTONS-1) selectedMainMenuButton = 0; 
				else selectedMainMenuButton++;		
			}
		}
		
		if (gameState == GameState.Osiagniecia)
		{
			if (mwe.getWheelRotation() == 1 && scrollScreenY < ((Achievements.maxAchievements - 9) * 60)) scrollScreenY += 20;
			if (mwe.getWheelRotation() == -1 && scrollScreenY > 0) scrollScreenY -= 20;
		}
		
		if (gameState == GameState.NajlepszeWyniki)
		{
			if (mwe.getWheelRotation() == 1 && scrollScreenY < ((hallOfFame.getHallOfFameList().size() - 9) * 60)) scrollScreenY += 20;
			if (mwe.getWheelRotation() == -1 && scrollScreenY > 0) scrollScreenY -= 20;
		}
	}
}
}