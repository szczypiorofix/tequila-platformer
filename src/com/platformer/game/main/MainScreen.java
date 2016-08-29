package com.platformer.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Desktop;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
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
private boolean exit = false;
private Camera cam;
private HUD hud;

/** Przyjmuje warto�� true je�li plik konfiguracyjny gamepada zosta� utworzony ORAZ gdy gamepad zosta� pod��czony do portu USB konputera.
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

/** Obiekt enumu GameState prezentuj�cy aktualny "stan gry". W zale�no�ci od tego stanu jest wy�wietlane odpowienie menu gry lub sama gra.
 *  @see GameState
 */
private GameState gameState;

private int selectedMainMenuButton;
private final int MAX_MAIN_MENU_BUTTONS = 7;
private MenuButton[] mainMenuButtons;
private int selectedMenuButton;
private final int MAX_MENU_BUTTONS = 3;
private MenuButton[] menuButtons;

/** Zmienna pomocna przy naliczaniu przesuwania si� t�a w menu g��wnym.
 * 
 */
private float bg_move = 0f;

/** Zmienna pomocna przy naliczaniu ruchu s�o�ca po okr�gu w menu g��wnym.
 * 
 */
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
		"       BEDE GRAU W GRE !       ",
		"   LEELOO DALLAS MULTIPASS     ",
		"        NASI TU BYLI !         ",
		"         I'LL BE BACK !         ",
		"   CIEMNO��, WIDZ� CIEMNO��     ",
		"        MY LITTLE PONY...       ",
		"  TO JEST KURA PANIE GENRALE !  ",
		"          IT JUST WORKS !       ",
		"COME WITH ME IF YOU WANT TO LIVE",
		"   BOO BARDZO LUBI TAKIE LASY  ",
		"IT'S NOT A BUG, IT'S A FEATURE !",
		"      MARCELLO M�WI: CZE�� !    "
};
private Random random;

/** Przyjmuje warto�� true je�li w system obs�uguje polecenia domy�lne takie jak OPEN, EDIT, MAIL, PRINT oraz BROWSE.
 * 
 */
private boolean isDesktopSupported;

private Desktop desktop;
private float[] falujaceLitery = new float[16];
private boolean[] literyUp = new boolean[16];
private Animation ptaki1RAnim;
private float ptaki1, ptaki2, ptaki3;
private float chmury1, chmury2, chmury3;
private float scrollScreenY;



/** Podstawowy konstruktor klasy MainScreen.
 * @param gameState - stan gry.
 * @param gameWindow - obiekt klasy GameWindow czyli okno gry.
 * @param gamepadFileEnabled - true je�li plik konfiguracyjny gamepada zosta� utworzony.
 * @param hallOfFame - obiekt klasy HallOfFame czyli Najlepsze Wyniki.
 * @param achievements - obiekt klasy Achievements czyli Osi�gni�cia.
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
	
	isDesktopSupported = Desktop.isDesktopSupported();
	
	scrollScreenY = 0;
	
	random = new Random();
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
	mainMenuButtons[1] = new MenuButton("JAK GRA�", 650, 180, 310, 50);
	mainMenuButtons[2] = new MenuButton("NAJLEPSZE WYNIKI", 650, 240, 310, 50);
	mainMenuButtons[3] = new MenuButton("OSI�GNI�CIA", 650, 300, 310, 50);
	mainMenuButtons[4] = new MenuButton("ZNAJD�KI", 650, 360, 310, 50);
	mainMenuButtons[5] = new MenuButton("O GRZE", 650, 420, 310, 50);
	mainMenuButtons[6] = new MenuButton("ZAKO�CZ", 650, 480, 310, 50);
	
	menuButtons = new MenuButton[MAX_MENU_BUTTONS];
	selectedMenuButton = 0;	
	menuButtons[0] = new MenuButton("WZN�W GR�", 360, 190);
	menuButtons[1] = new MenuButton("MENU G��WNE", 360, 270);
	menuButtons[2] = new MenuButton("ZAKO�CZ GR�", 360, 350);
		
	smigloAnim = new Animation(1, Textures.getInstance().smiglo[3], Textures.getInstance().smiglo[2], Textures.getInstance().smiglo[1], Textures.getInstance().smiglo[0]);
	ptaki1RAnim = new Animation(10, Textures.getInstance().ptakiR1, Textures.getInstance().ptakiR2);
	
	ptaki1 = -500f;
	ptaki2 = -460f;
	ptaki3 = -420f;
	
	chmury1 = 200;
	chmury2 = 1300;
	chmury3 = 800;
	
	for (int i = 0; i < falujaceLitery.length; i++) falujaceLitery[i] = random.nextInt(falujaceLitery.length);
}


/** Metoda powoduj�ca odtwarzanie muzyki nr.1 - 'Western.mp3"
 * 
 */
public void playMusic1()
{
	MainClass.music.stop();
	MainClass.music.restart(Music.WESTERN);
	MainClass.music.setPlaying(true);		
}

/** Metoda powoduj�ca odtwarzanie muzyki nr. 2 - "Mirage.mp3".
 * 
 */
public void playMusic2()
{
	MainClass.music.stop();
	MainClass.music.restart(Music.MIRAGE);
	MainClass.music.setPlaying(true);
}

/** Metoda zatrzymuj�ca odtwarzanie muzyki.
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


/** Otwiera podan� stron� internetow� w domy�lnej przegl�darce systemu.
 * @param website - Adres strony internetowej do otwarcia.
 */
private void openWebsite(String website)
{
	if (isDesktopSupported) {
		desktop = Desktop.getDesktop();

		if (desktop.isSupported(Desktop.Action.BROWSE)) {
			URI url = null;
			try {
				url = new URI(website);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
			try {
				desktop.browse(url);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
}


/** Metoda w kt�rej zawarty jest ca�y game logic.
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
	
	if (key.isKeyPressedOnce(KeyEvent.VK_ESCAPE)) // POKA� / UKRYJ MENU
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
	
	
	// PRZESUWANIE T�A W MENU G��WNYM
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
	
	// OB�UGA MENU G��WNEGO
	if (gameState == GameState.MainMenu)
	{
		
		// OBS�UGA FALUJACYCH LITER
		
		for (int i = 0; i < falujaceLitery.length; i++)
		{
			
			if (!literyUp[i] && falujaceLitery[i] > -10) falujaceLitery[i] -= 0.6f;
			if (literyUp[i] && falujaceLitery[i] < 0) falujaceLitery[i] += 0.6f;
			
			if (falujaceLitery[i] >= 0) literyUp[i] = false;
			if (falujaceLitery[i] <= -10) literyUp[i] = true;
		}
		
		
		if (key.isKeyPressedOnce(KeyEvent.VK_F1)) {
			// OTWIERANIE STRONY DOMOWEJ GRY
			if (isDesktopSupported) openWebsite("www.tequilaplatformer.cba.pl");
		}

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
			gameState = GameState.Zakoncz;// ZAMKNI�CIE GRY KLAWISZEM ESC.
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
			case 2: scrollScreenY = 0;
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
	
	// OBS�UGA MENU W TRAKCIE GRY
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

	// MENU JAK GRA�	
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

	
	// MENU ZNAJD�KI
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
			writeScore(playerName, SCORE, millis);
		}
	}
	
	// GRA DZIA�A
	if (gameState == GameState.Game && !player.isFinishLevel() && player.getHealth() > 0) {
		objectsHandler.tick();
		cam.tick(player);
		timeTick();
	}

	
	
	// ENTER TO NEW LEVEL
	if (gameState == GameState.NextLevel && player.isFinishLevel() && key.isKeyPressedOnce(KeyEvent.VK_ENTER)) // ENTER PO WPISANIU IMIENIA PRZENOSI NA NOWY POZIOM
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


/** Metoda obliczaj� aktualny czas gry na danym poziomie.
 * 
 */
public void timeTick()
{
	millis += (1000/60);
	time = new SimpleDateFormat("mm:ss:SSS").format(new Date(millis));
}


public static class CompareScore implements Comparator<HallOfFamePlayer>
{
	@Override
	public int compare(HallOfFamePlayer p1, HallOfFamePlayer p2) {
		return p2.getScore() - p1.getScore();
	}
}


/** Metoda dodaj�ca nowegy wpis na li�cie Najlepsze Wyniki (Hall of Fame)
 * oraz zapisuj�ca t� list� do pliku.
 * @param name Imi�/ksywka obecnego gracza.
 * @param score Wynik obecnego gracza.
 * @param millis Czas gry obecnego gracza w milisekundach
 */
private void writeScore(String name, int score, long millis)
{
	hallOfFame.getHallOfFameList().add(new HallOfFamePlayer(name, score, millis, LEVEL));
	
	Collections.sort(hallOfFame.getHallOfFameList(), new CompareScore());
	
	ArrayList<HallOfFamePlayer> t = new ArrayList<HallOfFamePlayer>(10);
	
	
	/// SORTOWANIE W KOLEJNO�CI ODWROTNEJ CZYLI OD NAJWI�KSZEGO DO NAJMNIEJSZEGO
	for (int i = 0; i < 20; i++)
	{
		t.add(hallOfFame.getHallOfFameList().get(i));
	}
	
	hallOfFame.getHallOfFameList().clear();
	
	for (int i = 0; i < 20; i++)
	{
		hallOfFame.getHallOfFameList().add(t.get(i));
	}
	
	hallOfFame.writeScoreToFile();
}

/** Metoda wy�wietlaj�ca osi�gni�cie na ekranie, w��cznie z "najazdem" i "odjazdem" ramki z tekstem u g�ry ekranu.
 * @param g2d Graphics2D.
 * @param msg Tre�� osi�gni�cia.
 * @param achievementImage Ikonka osi�gni�cia.
 * @param counter Licznik, opisuj�cy jednostki czasu przez jaki wiadomo�� jest wy�wietlana.
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
 * @param fps_count Licznik FPS�w.
 * @param ticks_count Licznik update'�w (game logic).
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
	
	// Tworzenie obrazu t�a gry w grayScale;
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
		// BACKGROUND - G�ry i s�o�ce
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
	
	if (gameState == GameState.MainMenu && isDesktopSupported)
	{
		if (isDesktopSupported) {
			g2d.setFont(MainClass.arialFont);
			g2d.drawString("F1 - strona domowa gry.", 400, MainClass.HEIGHT - 10);			
		}
		
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
	}
	
	// FPS CAP WSZ�DZIE OPR�CZ GRY
	if (gameState == GameState.Game) MainClass.fpsCap = false;
	else MainClass.fpsCap = true;
	
		
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
		g2d.setFont(MainClass.verdana14Font);
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
	
	hud.showGameHud(g2d, gameState, achievements, hallOfFame, this.fps_count, this.ticks_count);
	
	
	if (gameState == GameState.Osiagniecia)
	{
		g2d.setColor(MainClass.fontColor);
		g2d.drawImage(Textures.getInstance().achievementsMenuBGImage, 85, 0, null);
		g2d.setFont(MainClass.verdana14Font);
		g2d.drawString(achievements.getAchievementsUnlocked()+"/" +Achievements.maxAchievements,100, 10);
		
		
		g2d.translate(0, -scrollScreenY); // CAM BEGINNING
	
		
		for (int i = 0; i < Achievements.maxAchievements; i++)
		{

			if (achievements.getAchievementsList().get(i))
			{
				switch (i)
				{
				case 0:	if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getJump10TextShort() +"  " +achievements.getJump10Text(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().jump10Image, 110, 30 + (i*60), null);
						}
						break;
				case 1: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getJump25TextShort() +"  " +achievements.getJump25Text(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().jump25Image, 110, 30 + (i*60), null);					
						}
						break;
				case 2:	if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getJump50TextShort() +"  " +achievements.getJump50Text(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().jump50Image, 110, 30 + (i*60), null);
						}
						break;
				case 3:	if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getCoin20TextShort() +"  " +achievements.getCoin20Text(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().coin20Image, 110, 30 + (i*60), null);
						}
						break;
				case 4:	if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getCoin50TextShort() +"  " +achievements.getCoin50Text(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().coin50Image, 110, 30 + (i*60), null);
						}
						break;
				case 5:	if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getCoin100TextShort() +"  " +achievements.getCoin100Text(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().coin100Image, 110, 30 + (i*60), null);
						}
						break;
				case 6:	if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getCoin150TextShort() +"  " +achievements.getCoin150Text(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().coin150Image, 110, 30 + (i*60), null);
						}
						break;
				case 7:	if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getPowerup3TextShort() +"  " +achievements.getPowerup3Text(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().powerup3Image, 110, 30 + (i*60), null);
						}
						break;
				case 8: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getComplete1LevelTextShort() +"  " +achievements.getComplete1LevelText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().complete1LevelImage, 110, 30 + (i*60), null);
						}
						break;
				case 9: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getComplete2LevelTextShort() +"  " +achievements.getComplete2LevelText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().complete2LevelImage, 110, 30 + (i*60), null);
						}
						break;
				case 10: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getComplete3LevelTextShort() +"  " +achievements.getComplete3LevelText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().complete3LevelImage, 110, 30 + (i*60), null);
						}
						break;
				case 11: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getComplete4LevelTextShort() +"  " +achievements.getComplete4LevelText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().complete4LevelImage, 110, 30 + (i*60), null);
						}
						break;
				case 12: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getComplete5LevelTextShort() +"  " +achievements.getComplete5LevelText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().complete5LevelImage, 110, 30 + (i*60), null);
						}
						break;
				case 13: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getComplete6LevelTextShort() +"  " +achievements.getComplete6LevelText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().complete6LevelImage, 110, 30 + (i*60), null);
						}
						break;
				case 14: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getComplete7LevelTextShort() +"  " +achievements.getComplete7LevelText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().complete7LevelImage, 110, 30 + (i*60), null);
						}
						break;
				case 15: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getComplete8LevelTextShort() +"  " +achievements.getComplete8LevelText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().complete8LevelImage, 110, 30 + (i*60), null);
						}
						break;
				case 16: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getComplete9LevelTextShort() +"  " +achievements.getComplete9LevelText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().complete9LevelImage, 110, 30 + (i*60), null);
						}
						break;
				case 17: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getComplete10LevelTextShort() +"  " +achievements.getComplete10LevelText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().complete10LevelImage, 110, 30 + (i*60), null);
						}
						break;
				case 18: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getFindAllCoinsTextShort() +"  " +achievements.getFindAllCoinsText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().findAllCoinsImage, 110, 30 + (i*60), null);
						}
						break;
				case 19: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getFindAllPowerupsTextShort() +"  " +achievements.getFindAllPowerupsText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().findAllPowerupsImage, 110, 30 + (i*60), null);
						}
						break;
				case 20: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getNoHarmTextShort() +"  " +achievements.getNoHarmText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().noHarmImage, 110, 30 + (i*60), null);
						}
						break;
				case 21: if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
						{
							g2d.drawString(achievements.getMegaJumpTextShort() +". " +achievements.getMegaJumpText(), 170, 60 + (i*60));
							g2d.drawImage(Textures.getInstance().megaJumpImage, 110, 30 + (i*60), null);
						}
						break;
				}				
			}
			else {
				if (scrollScreenY < (20 +(i*60)) && scrollScreenY > (-490 + (i*60)))
				{
					g2d.drawString(" < UKRYTY >", 170, 60 + (i*60));
					g2d.drawImage(Textures.getInstance().blank, 110, 30 + (i*60), null);
				}
			}
		}
		//g2d.translate(0, scrollY1); // CAM ENDING
	}
	
	
	if (gameState == GameState.NajlepszeWyniki)
	{
		g2d.setColor(MainClass.fontColor);
		g2d.drawImage(Textures.getInstance().hallOfFameImage, 100, 0, null);
		
		g2d.setFont(MainClass.verdana18Font);
		g2d.drawString("MIEJSCE    POZIOM         IMI�                         CZAS                 WYNIK", 140, 45);
		
		g2d.drawLine(140, 51, 820, 51);
		g2d.drawLine(140, 52, 820, 52);
		g2d.drawLine(140, 53, 820, 53);
		
		g2d.translate(0, -scrollScreenY);
		
		
		// TODO https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html#setClip(int,%20int,%20int,%20int)
		
		
		for (int i = 0; i < hallOfFame.getHallOfFameList().size(); i++)
		{
			 if (scrollScreenY < (10 +(i*60)) && scrollScreenY > (-490 + (i*60)))
			 {
				 	g2d.drawString(i+1+"", 175, 80 + (i*60));
				 	g2d.drawString(hallOfFame.getHallOfFameList().get(i).getLevel()+"", 280, 80 + (i*60));
				 	g2d.drawString(hallOfFame.getHallOfFameList().get(i).getName()+"", 360, 80+(i*60));
				 	g2d.drawString(hallOfFame.getHallOfFameList().get(i).getTimeFromMilis(hallOfFame.getHallOfFameList().get(i).getMilis()), 570, 80 + (i*60));
				 	g2d.drawString(hallOfFame.getHallOfFameList().get(i).getScore()+"", 755, 80 + (i*60));
			 }
		}
	}
	
	
	
	if (makeScreenShot)	makeScreenShot();
	
	//////////////////////////////////////////////////////////
	
	Toolkit.getDefaultToolkit().sync();
	
	g.dispose();
	bs.show();
}

/** Metoda zapisuj�ca zrzut ekranu do pliku .png. 
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

/** Metoda zwracaj�ca pole isExit, kt�re m�wi, czy zosta�o wykonane rz�danie zakmni�cia gry.
 * @return isExit
 */
public boolean isExit()
{
	return exit;
}
}