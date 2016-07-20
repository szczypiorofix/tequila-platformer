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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import com.platformer.game.graphics.BufferedImageLoader;
import com.platformer.game.graphics.Textures;
import com.platformer.game.input.InputManager;
import com.platformer.game.input.Joystick;
import com.platformer.game.objects.PlayerObject;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Event;



public class MainScreen extends Canvas{

private static final long serialVersionUID = -5788122194224852624L;
private GameWindow gameWindow;
public static int LEVEL = 1;
public static int COINS = 0;
private BufferStrategy bs;
private Graphics g;
private ObjectsHandler objectsHandler;
private PlayerObject player;
private InputManager key;
private Joystick joystick;
private Controller myGamepad;
private Component[] gamepadComponents;
private boolean exit = false;
public static boolean KEY_LEFT = false, KEY_RIGHT = false, KEY_UP = false, KEY_DOWN = false, KEY_PAUSE = false;
public static boolean GAMEPAD_LEFT = false, GAMEPAD_RIGHT = false, GAMEPAD_UP = false;
private Camera cam;
static Textures tex;
private BufferedImage backGroundMountains;
private boolean gamepadEnabled = false;
private Properties prop = new Properties();
private InputStream propInput = null;
private String leftProp, leftValueProp, rightProp, rightValueProp, jumpProp, jumpValueProp, startProp, startValueProp;
public static boolean pauseGame = false;
private int minutes = 0, seconds = 0;
private float milis = 0f;
private String time;



public MainScreen(GameWindow gameWindow, boolean gamepadEnabled)
{
	super();
	this.setFocusable(false);
	this.gameWindow = gameWindow;
	
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
	tex = new Textures();
	backGroundMountains = loader.loadImage("/BG.png");  // http://opengameart.org/content/generic-platformer-tileset-16x16-background
	this.gameWindow.add(this);
	key = new InputManager();
	this.gameWindow.addKeyListener(key);
	cam = new Camera(0,0);
	//new Music();
}


public void addElements()
{
	objectsHandler = new ObjectsHandler(cam);
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
				if (Float.toString(value).equals(jumpValueProp)) GAMEPAD_UP = true;
    				else GAMEPAD_UP = false;
    			}
			
			if (comp.getName().equals(leftProp))
			{
				if (Float.toString(value).equals(leftValueProp))
				{
					GAMEPAD_LEFT = true;
				}
				else GAMEPAD_LEFT = false;
			}
			
			if (comp.getName().equals(rightProp))
			{
				if (Float.toString(value).equals(rightValueProp))
				{
					GAMEPAD_RIGHT = true;
				}
				else GAMEPAD_RIGHT = false;
			}

		
		}
	}
    
    // KEYBOARD
    //key.update();

  	KEY_UP = false;
  	KEY_LEFT = false;
  	KEY_RIGHT = false;
  	KEY_DOWN = false;
    
    if ((key.isKeyDown(KeyEvent.VK_LEFT)) || (key.isKeyDown(KeyEvent.VK_A))) KEY_LEFT = true;
	if (((key.isKeyDown(KeyEvent.VK_RIGHT)) || (key.isKeyDown(KeyEvent.VK_D)))) KEY_RIGHT = true;
	if ((key.isKeyDown(KeyEvent.VK_UP)) || (key.isKeyDown(KeyEvent.VK_W))) KEY_UP = true;
	if ((key.isKeyDown(KeyEvent.VK_DOWN)) || (key.isKeyDown(KeyEvent.VK_S))) KEY_DOWN = true;
	if (key.isKeyDown(KeyEvent.VK_ESCAPE)) exit=true;
	if (key.isKeyDown(KeyEvent.VK_SPACE)) pauseGame = !pauseGame;
	
	
	if (!pauseGame) {
		objectsHandler.tick();
		cam.tick(player);
		
		milis += (1000/60);
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
	
	if (player.getHealth() < 1)
	{
		JOptionPane.showMessageDialog(null, "YOU DIED !!!");
		System.exit(0);
	}
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
	Graphics2D g2d = (Graphics2D) g;
	RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	  
	/////////////////// DRAW HERE ////////////////////////////
	
    g2d.setRenderingHints(rh);
	g.setColor(new Color(184, 220, 254));
	g.fillRect(0,0,getWidth(), getHeight());
	
	g2d.setFont(MainClass.texasFont.deriveFont(18f));
	g2d.setColor(Color.BLUE);
	
	g.drawImage(backGroundMountains, (int) (0 - player.getLevel1X()), (int) (cam.getY()/1.33) + (MainClass.HEIGHT / 2), MainClass.WIDTH, (int) (MainClass.HEIGHT*1.2), null);
	g.drawImage(backGroundMountains, (int) (MainClass.WIDTH - player.getLevel1X()), (int) (cam.getY()/1.33) + (MainClass.HEIGHT / 2), MainClass.WIDTH, (int) (MainClass.HEIGHT*1.2), null);
	g.drawImage(backGroundMountains, (int) ((MainClass.WIDTH *2)- player.getLevel1X()), (int) (cam.getY()/1.33) + (MainClass.HEIGHT / 2), MainClass.WIDTH, (int) (MainClass.HEIGHT*1.2), null);	
	g.drawImage(backGroundMountains, (int) ((MainClass.WIDTH *3)- player.getLevel1X()), (int) (cam.getY()/1.33) + (MainClass.HEIGHT / 2), MainClass.WIDTH, (int) (MainClass.HEIGHT*1.2), null);
	
	g2d.drawString("LEVEL "+LEVEL, 845, 40);
	g2d.drawString("COINS: "+COINS, 10, 30);
	
	g2d.setFont(new Font("Verdana", 1, 12));
	g2d.drawString("FPS: "+fps_count +" TICKS: "+ ticks_count, MainClass.WIDTH - 150, 60);
	g2d.drawString("TIME: "+time, MainClass.WIDTH - 150, 80);
	
	if (player.isTequila_powerUp()) g2d.drawImage(tex.tequilaImage, 10, 50, null);
	if (player.isTaco_powerUp()) g2d.drawImage(tex.tacoImage, 10, 50, null);

	for (int i = 0; i < player.getHealth(); i++) g.drawImage(tex.heart, 360+(i*40), 5, 40, 40,null);
	
	
	g2d.translate(cam.getX(), cam.getY());  // CAM BEGINNING
	
	objectsHandler.render(g);
	
	g2d.translate(-cam.getX(), -cam.getY()); // CAM ENGING
	
	//////////////////////////////////////////////////////////
	
	Toolkit.getDefaultToolkit().sync();
	
	g.dispose();
	bs.show();
}


public static Textures getInstance()
{
	return tex;
}


public boolean isExit()
{
	return exit;
}
}