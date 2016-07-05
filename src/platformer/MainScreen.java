package platformer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class MainScreen extends Canvas{

private static final long serialVersionUID = -5788122194224852624L;

private GameWindow gameWindow;
private BufferStrategy bs;
private Graphics g;
private ObjectsHandler objectsHandler;
private PlayerObject player;
private InputManager key;
private boolean exit = false;
protected static boolean KEY_LEFT = false, KEY_RIGHT = false, KEY_UP = false, KEY_CTRL = false, KEY_SHIFT = false;

public MainScreen(GameWindow gameWindow)
{
	super();
	this.gameWindow = gameWindow;
	this.gameWindow.blank();
	gameWindow.add(this);
	key = new InputManager();
	gameWindow.addKeyListener(key);
}


public void tick()
{
	//key.update();

	KEY_UP = false;
	KEY_LEFT = false;
	KEY_RIGHT = false;
	KEY_CTRL = false;
	KEY_SHIFT = false;
	
	if (key.isKeyDown(KeyEvent.VK_LEFT)) KEY_LEFT = true;
	if (key.isKeyDown(KeyEvent.VK_RIGHT)) KEY_RIGHT = true;
	if (key.isKeyDown(KeyEvent.VK_UP)) KEY_UP = true;
	if (key.isKeyDown(KeyEvent.VK_CONTROL)) KEY_CTRL = true;
	if (key.isKeyDown(KeyEvent.VK_SHIFT)) KEY_SHIFT = true;
	if (key.isKeyDown(KeyEvent.VK_ESCAPE)) exit=true;
	
	objectsHandler.tick();
}

public void addElements()
{
	objectsHandler = new ObjectsHandler();
	player = new PlayerObject(ObjectId.Player, 100, 100, objectsHandler);
	objectsHandler.addObject(player);
	objectsHandler.createLevel();
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
	
	/////////////////// DRAW HERE ////////////////////////////
	
	g.setColor(Color.BLACK);
	g.fillRect(0,0,getWidth(), getHeight());

	g.setColor(Color.WHITE);
	g.drawString("FPS: "+fps_count +" TICKS: "+ ticks_count, 10, 10);
	g.drawString("KEY: "+key.getKey(), 10, 20);
	g.drawString("X:"+player.getX() +" Y:"+player.getY(), MainClass.WIDTH - 100, 40);
	
	objectsHandler.render(g);
	
	//////////////////////////////////////////////////////////
		
	g.dispose();
	bs.show();
}


public boolean isExit()
{
	return exit;
}

}