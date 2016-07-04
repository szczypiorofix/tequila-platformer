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
	
	if (player.isOnGround())
	{
		player.setSpeedX(player.getSpeedX() * 0.9f);
		
		if ((key.isKeyDown(KeyEvent.VK_UP)) && (player.getY() > 20))
		{
			if (player.isJumpAgain())
			{
				player.setSpeedY(-10);
				
				// https://wiki.allegro.cc/index.php?title=How_to_implement_jumping_in_platformers
				// http://jsfiddle.net/LyM87/2176/
			}
		}
		
		
	}
	
	
	if ((key.isKeyDown(KeyEvent.VK_RIGHT)) && (player.getX() < MainClass.WIDTH - 50))
	{
		player.setSpeedX(6);
	}
	
	if ((key.isKeyDown(KeyEvent.VK_LEFT)) && (player.getX() > 15))
	{
		player.setSpeedX(-6);
	}
	
	
	
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
	//g.drawString("speedX:"+player.getSpeedX(), MainClass.WIDTH - 100, 60);
	//g.drawString("speedY:"+player.getSpeedY(), MainClass.WIDTH - 100, 80);
	//g.drawString("Falling: "+player.falling, MainClass.WIDTH - 100, 100);
	//g.drawString("Jumping: "+player.jumping, MainClass.WIDTH - 100, 120);
	
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