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
protected static boolean KEY_LEFT = false, KEY_RIGHT = false, KEY_UP = false, KEY_CTRL = false, KEY_SHIFT = false;
private Camera cam;

private BufferedImage level1image, tileSetImage, clouds, backGroundMountains;

private BufferedImage grassGroundL, grassGroundM, grassGroundR, middleGroundL, middleGroundM, middleGroundR, bottomGroundL, bottomGroundM, bottomGroundR; //1st Tile
private BufferedImage smallGrassGroundL, smallGrassGroundM, smallGrassGroundR;
private BufferedImage deepGroundL, deepGroundM, deepGroundR, leftDeepGround, rightDeepGround, deepGroundGrass;
private BufferedImage smallGrassGround1Top, smallGround1Middle, smallGround1Bottom;
private BufferedImage smallGrassGroundAlone;


public MainScreen(GameWindow gameWindow)
{
	super();
	this.gameWindow = gameWindow;
	this.gameWindow.blank();
	
	BufferedImageLoader loader = new BufferedImageLoader();
	level1image = loader.loadImage("/level1_image.png"); // LEVEL 1 IMAGE
	tileSetImage = loader.loadImage("/generic_platformer_tiles.png"); // TILE SHEET: http://opengameart.org/content/generic-platformer-tileset-16x16-background
	clouds = loader.loadImage("/clouds.png"); // CLOUD IMAGE
	backGroundMountains = loader.loadImage("/backGroundMountains.png");

	grassGroundL = tileSetImage.getSubimage(0, 0, 16, 16);
	grassGroundM = tileSetImage.getSubimage(16, 0, 16, 16);
	grassGroundR = tileSetImage.getSubimage(32, 0, 16, 16);
	middleGroundL = tileSetImage.getSubimage(0, 16, 16, 16);
	middleGroundM = tileSetImage.getSubimage(16, 16, 16, 16);
	middleGroundR = tileSetImage.getSubimage(32, 16, 16, 16);
	bottomGroundL = tileSetImage.getSubimage(0, 32, 16, 16);
	bottomGroundM = tileSetImage.getSubimage(16, 32, 16, 16);
	bottomGroundR = tileSetImage.getSubimage(32, 32, 16, 16);
	
	smallGrassGroundL = tileSetImage.getSubimage(0, 48, 16, 16);
	smallGrassGroundM = tileSetImage.getSubimage(16, 48, 16, 16);
	smallGrassGroundR = tileSetImage.getSubimage(32, 48, 16, 16);
	
	deepGroundL = tileSetImage.getSubimage(48, 0, 16, 16);
	deepGroundM = tileSetImage.getSubimage(64, 0, 16, 16);
	deepGroundR = tileSetImage.getSubimage(80, 0, 16, 16);
	leftDeepGround = tileSetImage.getSubimage(48, 16, 16, 16);
	rightDeepGround = tileSetImage.getSubimage(80, 16, 16, 16);
	deepGroundGrass = tileSetImage.getSubimage(64, 32, 16, 16);
	
	smallGrassGround1Top = tileSetImage.getSubimage(48, 48, 16, 16);
	smallGround1Middle = tileSetImage.getSubimage(48, 64, 16, 16);
	smallGround1Bottom = tileSetImage.getSubimage(48, 80, 16, 16);
	
	smallGrassGroundAlone = tileSetImage.getSubimage(64, 48, 16, 16);
	
	gameWindow.add(this);
	key = new InputManager();
	gameWindow.addKeyListener(key);
	cam = new Camera(0,0);
}


public void tick()
{
	//key.update();

	KEY_UP = false;
	KEY_LEFT = false;
	KEY_RIGHT = false;
	KEY_CTRL = false;
	KEY_SHIFT = false;
	
	if ((key.isKeyDown(KeyEvent.VK_LEFT)) || (key.isKeyDown(KeyEvent.VK_A))) KEY_LEFT = true;
	if (((key.isKeyDown(KeyEvent.VK_RIGHT)) || (key.isKeyDown(KeyEvent.VK_D)))) KEY_RIGHT = true;
	if ((key.isKeyDown(KeyEvent.VK_UP)) || (key.isKeyDown(KeyEvent.VK_W))) KEY_UP = true;
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
			
			if (red == 255 && green == 255 && blue == 255) objectsHandler.addObject(new Block(ObjectId.Block, xx*32, yy*32, middleGroundM));

			
			if (red == 0 && green == 0 && blue == 255) {
				player = new PlayerObject(ObjectId.Player, xx*32, yy*32, objectsHandler); /// TRZEBA PAMIÊTAÆ O DODANIU PLAYERA !!! INACZEJ GRA WYRZUCA B£AD W KLASIE CAMERA !!!
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
	
	g.setColor(new Color(110, 180, 224));
	g.fillRect(0,0,getWidth(), getHeight());
	
	g.drawImage(clouds, 0, 0, 800, 600, this); // CLOUDS
	g.drawImage(backGroundMountains, 0, 200, 800, 600, this);
	
	g.setColor(Color.BLACK);
	g.drawString("Jumping: "+player.jumping, MainClass.WIDTH - 100, 60);
	g.drawString("Falling: "+player.falling, MainClass.WIDTH - 100, 80);
	g.drawString("velY: "+player.velY, MainClass.WIDTH - 100, 100);
	g.drawString("velX: "+player.velX, MainClass.WIDTH - 100, 120);
	g.drawString("Gravity: "+player.gravity, MainClass.WIDTH - 100, 140);
	g.drawString("FPS: "+fps_count +" TICKS: "+ ticks_count, 10, 10);
	g.drawString("KEY: "+key.getKey(), 10, 20);
	g.drawString("X:"+player.getX() +" Y:"+player.getY(), MainClass.WIDTH - 100, 40);
	
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