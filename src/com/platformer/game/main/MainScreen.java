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
import com.platformer.game.graphics.BufferedImageLoader;
import com.platformer.game.graphics.Textures;
import com.platformer.game.input.InputManager;
import com.platformer.game.input.Joystick;
import com.platformer.game.objects.PlayerObject;
import com.platformer.game.sounds.SoundsLoader;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Event;



public class MainScreen extends Canvas{

private static final long serialVersionUID = -5788122194224852624L;

public static int LEVEL = 1;
public static int COINS = 0;
public static int SCORE = 0;
public static boolean pauseGame = false;
public static int minutes = 0, seconds = 0;
public static float milis = 0f;
private long milisMax = 0;
public static final float MAX_TIME_BONUS = 1500f;
public static float time_bonus = MAX_TIME_BONUS;
public static float TOTAL_SCORE = 0f;
public static boolean PLAYER_LEFT = false, PLAYER_RIGHT = false, PLAYER_JUMP = false, START_EXIT = false;

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
private static Textures tex = MainClass.getTexturesInstance();
private BufferedImage backGroundMountains;
private BufferedImage screenShotImage;
private BufferedImage sun;
private BufferedImage achievementBg;
private BufferedImage menuBg;
private boolean gamepadEnabled = false;
private boolean makeScreenShot = false;
private boolean showMessage = false;
private boolean saveAchievementsToFile;
private int messageCount = 0;
private int msgY;
private final int MESSAGE_TIME = 300;
private Properties prop = new Properties();
private InputStream propInput = null;
private String leftProp, leftValueProp, rightProp, rightValueProp, jumpProp, jumpValueProp, startProp, startValueProp;
private String time;
private File screenShotFile;
private SoundsLoader screenShotSound;
private String playerName;
private HallOfFame hallOfFame;
private Achievements achievements;
private GameState gameState;


//private Animation playerDeadR, playerDeadL;


public MainScreen(GameState gameState, GameWindow gameWindow, boolean gamepadEnabled, HallOfFame hallOfFame, Achievements achievements)
{
	super();
	this.setFocusable(false);
	this.gameState = gameState;
	this.gameWindow = gameWindow;
	this.hallOfFame = hallOfFame;
	this.achievements = achievements;
	this.gamepadEnabled = gamepadEnabled;
	
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
		}
		else this.gamepadEnabled = false;
	}
	
	BufferedImageLoader loader = new BufferedImageLoader();
	
	backGroundMountains = loader.loadImage("/BG.png");  // http://opengameart.org/content/generic-platformer-tileset-16x16-background
	sun = loader.loadImage("/sun.png");
	achievementBg = loader.loadImage("/achievementBg.png");
	menuBg = loader.loadImage("/gameMenuBackground.png");
	
	this.gameWindow.add(this);
	key = new InputManager();
	
	this.gameWindow.addKeyListener(key);
	this.gameWindow.addMouseListener(key);
	this.gameWindow.addMouseMotionListener(key);
	this.gameWindow.addMouseWheelListener(key);
	
	cam = new Camera(0,0);
	screenShotSound = new SoundsLoader("/screenShotSound.wav");
	
	playerName = "";
	
	msgY = 0;
	saveAchievementsToFile = false;
	//playerDeadR = new Animation(4, tex.playerDeadR[0], tex.playerDeadR[1], tex.playerDeadR[2], tex.playerDeadR[3], tex.playerDeadR[4], tex.playerDeadR[5], tex.playerDeadR[6]);
	//playerDeadL = new Animation(4, tex.playerDeadL[0], tex.playerDeadL[1], tex.playerDeadL[2], tex.playerDeadL[3], tex.playerDeadL[4], tex.playerDeadL[5], tex.playerDeadL[6]);
}


public void addElements()
{
	objectsHandler = new ObjectsHandler(cam, achievements);
	objectsHandler.loadLevel(LEVEL);
	player = objectsHandler.getPlayer();
	
	// BUILD JAVA WEB APP QUICKLY AHHAHAHAHAH
	// https://www.javacodegeeks.com/2016/07/build-java-web-app-quickly-java-servlet-jsp-tags-stormpath.html
	
}


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
				if (Float.toString(value).equals(startValueProp)) exit = true;
			}
			
			if (comp.getName().equals(jumpProp))
    			{
				if (Float.toString(value).equals(jumpValueProp)) PLAYER_JUMP = true;
    				else PLAYER_JUMP = false;
    			}
			
			if (comp.getName().equals(leftProp))
			{
				if (Float.toString(value).equals(leftValueProp))
				{
					PLAYER_LEFT = true;
				}
				else PLAYER_LEFT = false;
			}
			
			if (comp.getName().equals(rightProp))
			{
				if (Float.toString(value).equals(rightValueProp))
				{
					PLAYER_RIGHT = true;
				}
				else PLAYER_RIGHT = false;
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
	//if (key.isKeyPressedOnce(KeyEvent.VK_ESCAPE)) exit=true;
	if (key.isKeyPressedOnce(KeyEvent.VK_ESCAPE)) pauseGame = !pauseGame;
	if (key.isKeyPressedOnce(KeyEvent.VK_CONTROL)) {
		player.setHealth(5);
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
	if (player.getHealth() <= 0 && key.isKeyPressedOnce(KeyEvent.VK_SPACE))
	{
		pauseGame = false;
		objectsHandler.clearLevel();
		objectsHandler.resetLevel();
		objectsHandler.loadLevel(LEVEL);
		cam.setX(0);
		player = objectsHandler.getPlayer();
		achievements.restartLevel();
	}

	// PO WPISANIU IMIENIA DO LISTY NAJLEPSZYCH - ENTER!
	if (!pauseGame && player.isFinishLevel()) {
		
		
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
			pauseGame = true;	
		}
	}
	
	// GRA DZIA�A
	if (!pauseGame && !player.isFinishLevel() && player.getHealth() > 0) {
		objectsHandler.tick();
		cam.tick(player);
		timeTick();
	}
	
	// ENTER TO NEW LEVEL
	if (pauseGame && player.isFinishLevel())
	{
		pauseGame = false;
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
	
}

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

private void writeScore(String name, int score, long milis)
{
	hallOfFame.getHallOfFameList().add(new HallOfFamePlayer(name, score, milis));
	
	//System.out.println(hallOfFame.getHallOfFameList().get(hallOfFame.getHallOfFameList().size()-1).getTimeFromMilis(milis));
	
	if(MainClass.hallOfFameFile.exists() && !MainClass.hallOfFameFile.isDirectory())
	{
		try {
		oos = new ObjectOutputStream(new FileOutputStream((MainClass.hallOfFameFile)));
	    oos.writeObject(hallOfFame.getHallOfFameList());
	    oos.close();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(-1);
		}
	}
}

private void showMessage(Graphics2D g2d, String msg, BufferedImage achievementImage, int counter)
{
	
	if (!saveAchievementsToFile)
	{
		if(MainClass.achievementsFile.exists() && !MainClass.achievementsFile.isDirectory())
		{
			//System.out.println("Zapis achievement�w do pliku");
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
		else
			//System.out.println("Brak pliku!!!");
		saveAchievementsToFile = true;
	}
	
	g2d.setColor(Color.RED);
	g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 34f));
	
	if (msgY < 100 && counter < achievements.getShowAchievementCooldown()) msgY++;
	if (counter == achievements.getShowAchievementCooldown())
		if (msgY > -20) msgY--;
	
	g2d.drawImage(achievementBg, 280, msgY-40, null);
	g2d.drawImage(achievementImage, 295, msgY - 35, null);
	g2d.drawString(msg, 355, msgY);
}


public void render(int fps_count, int ticks_count)
{
	bs = this.getBufferStrategy();
	
	if (bs == null)
	{
		this.createBufferStrategy(3);
		return;
	}
	
	g = bs.getDrawGraphics();

	
	if (makeScreenShot)
	{
		screenShotImage = new BufferedImage(MainClass.WIDTH, MainClass.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = screenShotImage.createGraphics();
	}
	
	/////////////////// DRAW HERE ////////////////////////////
	
	g2d = (Graphics2D) g;
	RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	
	g2d.setRenderingHints(rh);
	g.setColor(new Color(184, 220, 254));
	g.fillRect(0,0,getWidth(), getHeight());
	
	
	if (gameState == GameState.Game)
	{
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 38f));
		g2d.setColor(Color.BLUE);
		
		/// MOUNTAING & PARALLAX
		
		if (cam.getX() < 10  && cam.getX() > -6950) g2d.drawImage(backGroundMountains, (int) (cam.getX()*0.143), (int) (cam.getY()/1.33) + (MainClass.HEIGHT / 2), MainClass.WIDTH, (int) (MainClass.HEIGHT*1.2), null);
		if (cam.getX() < -15  && cam.getX() > -14000) g2d.drawImage(backGroundMountains, (int) (cam.getX()*0.143) + 1000, (int) (cam.getY()/1.33) + (MainClass.HEIGHT / 2), MainClass.WIDTH, (int) (MainClass.HEIGHT*1.2), null);
		if (cam.getX() < -7000  && cam.getX() > -14500) g2d.drawImage(backGroundMountains, (int) (cam.getX()*0.143) + 2000, (int) (cam.getY()/1.33) + (MainClass.HEIGHT / 2), MainClass.WIDTH, (int) (MainClass.HEIGHT*1.2), null);		
		
		g2d.drawImage(sun, 210, (int) (cam.getY()/1.33) + 420, null);
		
		////// CAM MOVING HERE
		g2d.translate(cam.getX(), cam.getY());  // CAM BEGINNING
		
		if (!player.isFinishLevel()) objectsHandler.render(g);
		
		g2d.translate(-cam.getX(), -cam.getY()); // CAM ENGING	
		

		g2d.drawString("POZIOM "+LEVEL, 845, 40);
		g2d.drawString("MONETY: "+COINS, 10, 40);
		g2d.drawString("WYNIK: "+SCORE, 10, 80);
		
		g2d.setFont(new Font("Verdana", 1, 12));
		g2d.drawString("FPS: "+fps_count +" TICKS: "+ ticks_count, MainClass.WIDTH - 150, 60);
		g2d.drawString("CZAS: "+time, MainClass.WIDTH - 150, 80);
		

		if (player.isTequila_powerUp()) {
			g2d.drawImage(tex.tequilaImage, 10, 90, null);
			g2d.setColor(Color.ORANGE);
			g2d.fillRect(60, 180 - (int)(player.getTequila_time()/3.5), 10, (int) (player.getTequila_time()/3.5));
		}
		if (player.isTaco_powerUp()) {
			g2d.drawImage(tex.tacoImage, 10, 110, null);
			g2d.setColor(Color.GREEN);
			g2d.fillRect(70, 160 - (player.getTaco_time()/8), 10, (player.getTaco_time()/8));
		}
		
		//g2d.drawString("CAM X "+ cam.getX(), MainClass.WIDTH - 170, 120);
		//g2d.drawString("GROUND "+ player.isOnGround(), MainClass.WIDTH - 170, 120);
		//g2d.drawString("JUMP "+ player.isJumping(), MainClass.WIDTH - 170, 170);
		//g2d.drawString("BONUS CZASOWY "+ (int) time_bonus, MainClass.WIDTH - 170, 120);
		
		if (pauseGame && !player.isFinishLevel())
		{
			gameState = GameState.Menu;
		}
		
		if (showMessage) showMessage(g2d, achievements.getAchievementTextShort(), achievements.getAchievementImage(), achievements.getAchievementCount());
		else saveAchievementsToFile = false;
		
		
		// KONIEC POZIOMU
		if (player.isFinishLevel())
		{
			TOTAL_SCORE = SCORE + (int) time_bonus;
			g2d.setColor(Color.GRAY);
			g2d.fillRect(300, 100, 400, 450);
			g2d.setColor(Color.YELLOW);
			g2d.drawRect(2999, 99, 402, 342);
			g2d.setFont(MainClass.texasFont.deriveFont(Font.BOLD, 54f));
			g2d.drawString("POZIOM "+LEVEL +" UKO�CZONY !!!", 315, 155);
			g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 42f));
			g2d.drawString("TW�J WYNIK: " +SCORE, 375, 240);
			g2d.drawString("CZAS: " +time, 380, 300);
			g2d.drawString("BONUS CZASOWY: " + (int) time_bonus, 340, 370);
			g2d.drawString("WYNIK KO�COWY: " +(int) TOTAL_SCORE, 340, 430);
			g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 26f));
			g2d.setColor(Color.WHITE);
			g2d.drawString("PODAJ SWOJE IMI� I NACI�NIJ ENTER:", 320, 480);
			g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 32f));
			g2d.setColor(Color.CYAN);
			g2d.drawString(playerName , 490-(playerName.length() * 8), 520);
			//key.setKetPressedOnce(KeyEvent.VK_ENTER);
			//pauseGame = false;
		}
		//playerDeadR.drawAnimation(g2d, (int) player.getX(), (int) player.getY(), false);
		if (player.getHealth() <= 0)
		{
			g2d.setColor(Color.RED);
			g2d.setFont(MainClass.texasFont.deriveFont(68f));
			g2d.drawString("NIE �YJESZ ...", 355, 220);
			g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 38f));
			g2d.drawString("SPACJA - RESTART", 340, 320);
			g2d.drawString("ESC - KONIEC", 370, 380);
			//playerDeadR.runAnimation();
			//playerDeadL.runAnimation();
			//playerDeadR.drawAnimation(g, (int) player.getX(), (int) player.getY(), false);
			//pauseGame = true;
		}
		
		for (int i = 0; i < player.getHealth(); i++) g.drawImage(tex.heart, 360+(i*40), 5, 40, 40,null);
		
		
		if (makeScreenShot)
		{
			makeScreenShot();
		}
		
	}
	
	
	if (gameState == GameState.Menu)
	{
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 64f));
		g2d.setColor(Color.BLUE);
		g2d.drawImage(menuBg, 300, 100, null);
		g2d.drawString("WZN�W GR�", 360, 220);
		g2d.drawString("MENU G��WNE", 360, 320);
		g2d.drawString("WYJ�CIE", 360, 420);
	}
	
	
	
	//////////////////////////////////////////////////////////
	
	Toolkit.getDefaultToolkit().sync();
	
	g.dispose();
	bs.show();
}

public void makeScreenShot()
{
	//g.dispose();
	screenShotSound.play();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
	Calendar cal = Calendar.getInstance();
			
	screenShotFile = new File("screenshot "+dateFormat.format(cal.getTime())+".png");
			
	try {
		ImageIO.write(screenShotImage, "png", screenShotFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	makeScreenShot = false;
}

public boolean isExit()
{
	return exit;
}
}