package platformer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;



public class MainScreen extends Canvas{

private static final long serialVersionUID = -5788122194224852624L;

private GameWindow gameWindow;
private BufferStrategy bs;
private Graphics g;
private ObjectsHandler objectsHandler;
private PlayerObject player;
private InputManager key;
private boolean exit = false;
protected static boolean KEY_LEFT = false, KEY_RIGHT = false, KEY_UP = false, KEY_CTRL = false, KEY_SHIFT = false, KEY_DOWN = false;
private Camera cam;
static Textures tex;
private BufferedImage level1image, backGroundMountains, grass;




public MainScreen(GameWindow gameWindow)
{
	super();
	
	this.gameWindow = gameWindow;
	this.gameWindow.blank();
	
	BufferedImageLoader loader = new BufferedImageLoader();
	
	tex = new Textures();
	
	level1image = loader.loadImage("/level1_image.png"); // LEVEL 1 IMAGE
	//clouds = loader.loadImage("/clouds.png"); // CLOUD IMAGE  // http://opengameart.org/content/generic-platformer-tileset-16x16-background
	backGroundMountains = loader.loadImage("/gory.png");  // http://opengameart.org/content/generic-platformer-tileset-16x16-background
	grass = loader.loadImage("/grass1.png"); // http://opengameart.org/content/grass-2-0
	
	gameWindow.add(this);
	key = new InputManager();
	gameWindow.addKeyListener(key);
	cam = new Camera(0,0);
	new Music();
}

public static Textures getInstance()
{
	return tex;
}

public void tick()
{
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

public void addElements()
{
	objectsHandler = new ObjectsHandler();
	loadImageLevel(level1image);
}


private void loadImageLevel(BufferedImage image)
{
	int w = level1image.getWidth();
	int h = level1image.getHeight();
	
	for (int yy = 0; yy < h; yy++)
		for (int xx = 0; xx < w; xx++)
		{
			int pixel = image.getRGB(xx, yy);
			int red = (pixel >> 16) & 0xff;
			int green = (pixel >> 8) & 0xff;
			int blue = (pixel) & 0xff;
			
			if (red == 255 && green == 255 && blue == 255) objectsHandler.addObject(new Block(ObjectId.Block, xx*45, yy*MainClass.HEIGHT /22, 1));
			if (red == 200 && green == 200 && blue == 200) objectsHandler.addObject(new Block(ObjectId.Block, xx*45, yy*MainClass.HEIGHT /22, 4));
			
			if (red == 0 && green == 0 && blue == 255) {
				player = new PlayerObject(ObjectId.Player, xx*45, yy*MainClass.HEIGHT /22, objectsHandler); /// TRZEBA PAMI�TA� O DODANIU PLAYERA !!! INACZEJ GRA WYRZUCA B�AD W KLASIE CAMERA !!!
				objectsHandler.addObject(player);
			}
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
	
	/////////////////// DRAW HERE ////////////////////////////
	
	g.setColor(Color.BLACK);
	g.fillRect(0,0,getWidth(), getHeight());
	
	g.drawImage(backGroundMountains, (int) (0 - player.getLevel1X()), (int) (cam.getY()/1.3) + (MainClass.HEIGHT / 2), 3800, MainClass.HEIGHT, this);
	
	
	//if ((player.getX() < cam.getX()))
	//{
		g.drawImage(grass, (int) (0 - player.getLevel2X())+150, (int) (cam.getY()+(MainClass.HEIGHT * 1.35)), (int) (grass.getWidth()* 1.3), grass.getHeight(), this);	
		g.drawImage(grass, (int) (0 - player.getLevel2X()+150+grass.getWidth() * 1.3), (int) (cam.getY()+(MainClass.HEIGHT * 1.35)), (int) (grass.getWidth()* 1.3), grass.getHeight(), this);
		//g.drawImage(grass, (int) (0 - player.getLevel2X()+150+grass.getWidth() * 2.6), (int) (cam.getY()+(MainClass.HEIGHT * 1.35)), (int) (grass.getWidth()* 1.3), grass.getHeight(), this);
	//}
		
	
	
	g.setColor(Color.YELLOW);
	g.drawString("X:"+player.getX() +" Y:"+player.getY(), MainClass.WIDTH - 130, 40);
	g.drawString("canX:"+cam.getX() +" camY:"+cam.getY(), MainClass.WIDTH - 130, 60);
	g.drawString("Jumping: "+player.jumping, MainClass.WIDTH - 130, 80);
	g.drawString("OnGround: "+player.onGround, MainClass.WIDTH - 130, 100);
	g.drawString("velY: "+player.velY, MainClass.WIDTH - 130, 120);
	g.drawString("velX: "+player.velX, MainClass.WIDTH - 130, 140);
	g.drawString("Gravity: "+player.gravity, MainClass.WIDTH - 130, 160);
	g.drawString("HEALTH: "+player.getHealth(), MainClass.WIDTH - 130, 180);
	
	g.drawString("FPS: "+fps_count +" TICKS: "+ ticks_count, 10, 20);
	g.drawString("KEY: "+key.getKey(), 10, 40);
	g.drawString("VIEWPORT: "+MainClass.WIDTH+"x" +MainClass.HEIGHT, 10, 60);
	
	g2d.translate(cam.getX(), cam.getY());  // CAM BEGINNING
	
	objectsHandler.render(g);
	
	g2d.translate(-cam.getX(), -cam.getY()); // CAM ENGING
	
	//////////////////////////////////////////////////////////
		
	g.dispose();
	bs.show();
}


public boolean isExit()
{
	return exit;
}

}