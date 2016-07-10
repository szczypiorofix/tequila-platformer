package platformer;

import java.awt.image.BufferedImage;

public class Textures {

SpriteSheet bs;
private BufferedImage block_sheet = null;
public BufferedImage[] block = new BufferedImage[12];
public BufferedImage[] playerRunR = new BufferedImage[8];
public BufferedImage[] playerRunL = new BufferedImage[8];
public BufferedImage[] playerIdleR = new BufferedImage[10];
public BufferedImage[] playerIdleL = new BufferedImage[10];
public BufferedImage[] playerJumpR = new BufferedImage[5];
public BufferedImage[] playerJumpL = new BufferedImage[5];

public Textures()
{
	BufferedImageLoader loader = new BufferedImageLoader();
	
	block_sheet = loader.loadImage("/generic_platformer_tiles.png"); //TILE SHEET: http://opengameart.org/content/generic-platformer-tileset-16x16-background	
	
	// http://charas-project.net/charas2/  - GENERATOR !!!
	
	bs = new SpriteSheet(block_sheet);
	
	playerRunR[0] = loader.loadImage("/Run (1).png"); // http://www.gameart2d.com/the-robot---free-sprites.html
	playerRunR[1] = loader.loadImage("/Run (2).png"); // RUN RIGHT
	playerRunR[2] = loader.loadImage("/Run (3).png");
	playerRunR[3] = loader.loadImage("/Run (4).png");
	playerRunR[4] = loader.loadImage("/Run (5).png");
	playerRunR[5] = loader.loadImage("/Run (6).png");
	playerRunR[6] = loader.loadImage("/Run (7).png");
	playerRunR[7] = loader.loadImage("/Run (8).png");
	
	playerRunL[0] = loader.loadImage("/Run (1)L.png"); // RUN LEFT
	playerRunL[1] = loader.loadImage("/Run (2)L.png");
	playerRunL[2] = loader.loadImage("/Run (3)L.png");
	playerRunL[3] = loader.loadImage("/Run (4)L.png");
	playerRunL[4] = loader.loadImage("/Run (5)L.png");
	playerRunL[5] = loader.loadImage("/Run (6)L.png");
	playerRunL[6] = loader.loadImage("/Run (7)L.png");
	playerRunL[7] = loader.loadImage("/Run (8)L.png");
	
	playerIdleR[0] = loader.loadImage("/Idle (1).png"); // IDLE RIGHT
	playerIdleR[1] = loader.loadImage("/Idle (2).png");
	playerIdleR[2] = loader.loadImage("/Idle (3).png");
	playerIdleR[3] = loader.loadImage("/Idle (4).png");
	playerIdleR[4] = loader.loadImage("/Idle (5).png");
	playerIdleR[5] = loader.loadImage("/Idle (6).png");
	playerIdleR[6] = loader.loadImage("/Idle (7).png");
	playerIdleR[7] = loader.loadImage("/Idle (8).png");
	playerIdleR[8] = loader.loadImage("/Idle (9).png");
	playerIdleR[9] = loader.loadImage("/Idle (10).png");
	
	playerIdleL[0] = loader.loadImage("/Idle (1)L.png"); // IDLE LEFT
	playerIdleL[1] = loader.loadImage("/Idle (2)L.png");
	playerIdleL[2] = loader.loadImage("/Idle (3)L.png");
	playerIdleL[3] = loader.loadImage("/Idle (4)L.png");
	playerIdleL[4] = loader.loadImage("/Idle (5)L.png");
	playerIdleL[5] = loader.loadImage("/Idle (6)L.png");
	playerIdleL[6] = loader.loadImage("/Idle (7)L.png");
	playerIdleL[7] = loader.loadImage("/Idle (8)L.png");
	playerIdleL[8] = loader.loadImage("/Idle (9)L.png");
	playerIdleL[9] = loader.loadImage("/Idle (10)L.png");
	
	playerJumpR[0] = loader.loadImage("/Jump (1).png"); // JUMP RIGHT
	playerJumpR[1] = loader.loadImage("/Jump (2).png");
	playerJumpR[2] = loader.loadImage("/Jump (3).png");
	playerJumpR[3] = loader.loadImage("/Jump (4).png");
	playerJumpR[4] = loader.loadImage("/Jump (5).png");
	
	playerJumpL[0] = loader.loadImage("/Jump (1)L.png"); // JUMP LEFT
	playerJumpL[1] = loader.loadImage("/Jump (2)L.png");
	playerJumpL[2] = loader.loadImage("/Jump (3)L.png");
	playerJumpL[3] = loader.loadImage("/Jump (4)L.png");
	playerJumpL[4] = loader.loadImage("/Jump (5)L.png");
	
	getTextures();
	
}

private void getTextures()
{
	block[0] = bs.grabImage(1, 1, 16, 16);  // First block
	block[1] = bs.grabImage(2, 1, 16, 16); // Second block
	block[2] = bs.grabImage(3, 1, 16, 16);
	block[3] = bs.grabImage(1, 2, 16, 16);
	block[4] = bs.grabImage(2, 2, 16, 16);
	block[5] = bs.grabImage(3, 2, 16, 16);
}


}