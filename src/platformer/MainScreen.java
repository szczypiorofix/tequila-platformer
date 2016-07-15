package platformer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
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
protected static boolean KEY_LEFT = false, KEY_RIGHT = false, KEY_UP = false, KEY_CTRL = false, KEY_SHIFT = false, KEY_DOWN = false;
protected static boolean GAMEPAD_LEFT = false, GAMEPAD_RIGHT = false, GAMEPAD_UP = false;
private Camera cam;
static Textures tex;
private BufferedImage backGroundMountains;
private boolean gamepadEnabled = false;
private Properties prop = new Properties();
private InputStream propInput = null;
private String leftProp, leftValueProp, rightProp, rightValueProp, jumpProp, jumpValueProp;



public MainScreen(GameWindow gameWindow, boolean gamepadEnabled)
{
	super();
	this.gameWindow = gameWindow;
	this.gamepadEnabled = gamepadEnabled;
	if (this.gamepadEnabled) 
	{
		
		try {
			propInput = new FileInputStream("input.txt");

			prop.load(propInput);

			leftProp = prop.getProperty("Left");
			leftValueProp = prop.getProperty("value_left");
			rightProp = prop.getProperty("Right");
			rightValueProp = prop.getProperty("value_right");
			jumpProp = prop.getProperty("Jump");
			jumpValueProp = prop.getProperty("value_jump");

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
	new Music();
}


public void addElements()
{
	objectsHandler = new ObjectsHandler(cam);
	objectsHandler.loadImageLevel(LEVEL);
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
   		
			if (comp.equals(gamepadComponents[14])) exit=true;
    	
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
  	KEY_CTRL = false;
  	KEY_SHIFT = false;
  	KEY_DOWN = false;
    
    if ((key.isKeyDown(KeyEvent.VK_LEFT)) || (key.isKeyDown(KeyEvent.VK_A))) KEY_LEFT = true;
	if (((key.isKeyDown(KeyEvent.VK_RIGHT)) || (key.isKeyDown(KeyEvent.VK_D)))) KEY_RIGHT = true;
	if ((key.isKeyDown(KeyEvent.VK_UP)) || (key.isKeyDown(KeyEvent.VK_W)) || (key.isKeyDown(KeyEvent.VK_SPACE))) KEY_UP = true;
	if ((key.isKeyDown(KeyEvent.VK_DOWN)) || (key.isKeyDown(KeyEvent.VK_S))) KEY_DOWN = true;
	if (key.isKeyDown(KeyEvent.VK_CONTROL)) KEY_CTRL = true;
	if (key.isKeyDown(KeyEvent.VK_SHIFT)) KEY_SHIFT = true;
	if (key.isKeyDown(KeyEvent.VK_ESCAPE)) exit=true;
	
	objectsHandler.tick();
	cam.tick(player);
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
	
	/////////////////// DRAW HERE ////////////////////////////
	
	g.setColor(new Color(184, 220, 254));
	g.fillRect(0,0,getWidth(), getHeight());
	
	g.drawImage(backGroundMountains, (int) (0 - player.getLevel1X()), (int) (cam.getY()/1.3) + (MainClass.HEIGHT / 2), MainClass.WIDTH, (int) (MainClass.HEIGHT*1.2), null);
	g.drawImage(backGroundMountains, (int) (MainClass.WIDTH - player.getLevel1X()), (int) (cam.getY()/1.3) + (MainClass.HEIGHT / 2), MainClass.WIDTH, (int) (MainClass.HEIGHT*1.2), null);
	g.drawImage(backGroundMountains, (int) ((MainClass.WIDTH *2)- player.getLevel1X()), (int) (cam.getY()/1.3) + (MainClass.HEIGHT / 2), MainClass.WIDTH, (int) (MainClass.HEIGHT*1.2), null);	
	
	g.setColor(Color.BLACK);
	g.fillRect(5, 5, 150, 120);
	g.fillRect(MainClass.WIDTH-140, 10, 130, 210);
	g.setColor(Color.YELLOW);
	g.drawString("X:"+player.getX() +" Y:"+player.getY(), MainClass.WIDTH - 130, 40);
	g.drawString("canX:"+cam.getX(), MainClass.WIDTH - 130, 60);
	g.drawString("canY:"+cam.getY(), MainClass.WIDTH - 130, 80);
	g.drawString("Jumping: "+player.jumping, MainClass.WIDTH - 130, 100);
	g.drawString("OnGround: "+player.onGround, MainClass.WIDTH - 130, 120);
	g.drawString("velY: "+player.velY, MainClass.WIDTH - 130, 140);
	g.drawString("velX: "+player.velX, MainClass.WIDTH - 130, 160);
	g.drawString("Gravity: "+player.gravity, MainClass.WIDTH - 130, 180);
	g.drawString("HEALTH: "+player.getHealth(), MainClass.WIDTH - 130, 200);
	
	g.drawString("FPS: "+fps_count +" TICKS: "+ ticks_count, 10, 20);
	g.drawString("LEVEL: "+LEVEL, 10, 40);
	g.drawString("KEY: "+key.getKey(), 10, 60);
	g.drawString("VIEWPORT: "+MainClass.WIDTH+"x" +MainClass.HEIGHT, 10, 80);
	g.drawString("GAMEPAD: "+this.gamepadEnabled, 10, 100);
	g.drawString("COINS: "+COINS, 10, 120);
	
	
	g2d.translate(cam.getX(), cam.getY());  // CAM BEGINNING
	
	objectsHandler.render(g);
	
	g2d.translate(-cam.getX(), -cam.getY()); // CAM ENGING
	
	//////////////////////////////////////////////////////////
		
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