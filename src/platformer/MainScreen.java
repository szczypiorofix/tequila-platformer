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
		
	if ((key.isKeyDown(KeyEvent.VK_RIGHT)) && (player.getX() < MainClass.WIDTH - 50))
	{
		player.setSpeedX(6);
	}
	
	if ((key.isKeyDown(KeyEvent.VK_LEFT)) && (player.getX() > 15))
	{
		player.setSpeedX(-6);
	}
	
	if ((key.isKeyDown(KeyEvent.VK_UP)) && (!player.getJump()) && (player.getY() > 50))
	{
		player.setSpeedY(-10);
		player.setJumping(true);
		player.setJump(true);
	}
	
	if (key.isKeyDown(KeyEvent.VK_ESCAPE)) exit=true;
	
	if ((key.isKeyDown(KeyEvent.VK_UP)) && (player.getSpeedY() > 0)) player.setSpeedY(player.getSpeedY() -1);
	
	if (player.getSpeedX() > 0) player.setSpeedX(player.getSpeedX() -1);
	if (player.getSpeedX() < 0) player.setSpeedX(player.getSpeedX() +1);
	
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
	//g.drawString("KEY: "+keyInput, WIDTH - 100, 10);
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