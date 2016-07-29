package com.platformer.game.graphics;

import java.awt.image.BufferedImage;

public class Textures {

public BufferedImage[] block = new BufferedImage[12];
public BufferedImage[] playerRunR = new BufferedImage[10];
public BufferedImage[] playerRunL = new BufferedImage[10];
public BufferedImage[] playerIdleR = new BufferedImage[10];
public BufferedImage[] playerIdleL = new BufferedImage[10];
public BufferedImage[] playerJumpR = new BufferedImage[5];
public BufferedImage[] playerJumpL = new BufferedImage[5];
public BufferedImage[] playerDeadR = new BufferedImage[7];
public BufferedImage[] playerDeadL = new BufferedImage[7];

public BufferedImage[] sceneryObjects = new BufferedImage[14];
public BufferedImage[] blocks = new BufferedImage[16];
public BufferedImage movingBlockX, movingBlockY;
public BufferedImage water, waterDeep;
public BufferedImage levelend;
private BufferedImage coinImage;
public SpriteSheet coinSheet;  // http://opengameart.org/content/coins-asset
public BufferedImage[] coinAnim = new BufferedImage[61];
public BufferedImage[] beeR = new BufferedImage[5];
public BufferedImage[] beeL = new BufferedImage[5];
public BufferedImage heart;
public BufferedImage tequilaImage;
public BufferedImage tacoImage;
public BufferedImage dartThrowerR, dartThrowerL;
public BufferedImage dartR, dartL;
public BufferedImage movingCrate;



public Textures()
{
	BufferedImageLoader loader = new BufferedImageLoader();
		
	// http://charas-project.net/charas2/  - GENERATOR !!!
	
	// http://www.piskelapp.com/  - spritesheet creator
	
	sceneryObjects[0] = loader.loadImage("/Bush (1).png");   // TILES http://www.gameart2d.com/free-desert-platformer-tileset.html
	sceneryObjects[1] = loader.loadImage("/Bush (2).png");
	sceneryObjects[2] = loader.loadImage("/Cactus (1).png");
	sceneryObjects[3] = loader.loadImage("/Cactus (2).png");
	sceneryObjects[4] = loader.loadImage("/Cactus (3).png");
	sceneryObjects[5] = loader.loadImage("/Crate.png");
	sceneryObjects[6] = loader.loadImage("/Grass (1).png");
	sceneryObjects[7] = loader.loadImage("/Grass (2).png");
	sceneryObjects[8] = loader.loadImage("/Sign.png");
	sceneryObjects[9] = loader.loadImage("/SignArrow.png");
	sceneryObjects[10] = loader.loadImage("/Skeleton.png");
	sceneryObjects[11] = loader.loadImage("/Stone.png");
	sceneryObjects[12] = loader.loadImage("/StoneBlock.png");
	sceneryObjects[13] = loader.loadImage("/Tree.png");
	
	playerRunR[0] = loader.loadImage("/Run00R.png"); // http://www.gameart2d.com/temple-run---free-sprites.html
	playerRunR[1] = loader.loadImage("/Run01R.png"); // RUN RIGHT
	playerRunR[2] = loader.loadImage("/Run02R.png");
	playerRunR[3] = loader.loadImage("/Run03R.png");
	playerRunR[4] = loader.loadImage("/Run04R.png");
	playerRunR[5] = loader.loadImage("/Run05R.png");
	playerRunR[6] = loader.loadImage("/Run06R.png");
	playerRunR[7] = loader.loadImage("/Run07R.png");
	playerRunR[8] = loader.loadImage("/Run08R.png");
	playerRunR[9] = loader.loadImage("/Run09R.png");
	
	playerRunL[0] = loader.loadImage("/Run00L.png"); // RUN LEFT
	playerRunL[1] = loader.loadImage("/Run01L.png");
	playerRunL[2] = loader.loadImage("/Run02L.png");
	playerRunL[3] = loader.loadImage("/Run03L.png");
	playerRunL[4] = loader.loadImage("/Run04L.png");
	playerRunL[5] = loader.loadImage("/Run05L.png");
	playerRunL[6] = loader.loadImage("/Run06L.png");
	playerRunL[7] = loader.loadImage("/Run07L.png");
	playerRunL[8] = loader.loadImage("/Run08L.png");
	playerRunL[9] = loader.loadImage("/Run09L.png");
	
	playerIdleR[0] = loader.loadImage("/Idle00R.png"); // IDLE RIGHT
	playerIdleR[1] = loader.loadImage("/Idle01R.png");
	playerIdleR[2] = loader.loadImage("/Idle02R.png");
	playerIdleR[3] = loader.loadImage("/Idle03R.png");
	playerIdleR[4] = loader.loadImage("/Idle04R.png");
	playerIdleR[5] = loader.loadImage("/Idle05R.png");
	playerIdleR[6] = loader.loadImage("/Idle06R.png");
	playerIdleR[7] = loader.loadImage("/Idle07R.png");
	playerIdleR[8] = loader.loadImage("/Idle08R.png");
	playerIdleR[9] = loader.loadImage("/Idle09R.png");
	
	playerIdleL[0] = loader.loadImage("/Idle00L.png"); // IDLE RIGHT
	playerIdleL[1] = loader.loadImage("/Idle01L.png");
	playerIdleL[2] = loader.loadImage("/Idle02L.png");
	playerIdleL[3] = loader.loadImage("/Idle03L.png");
	playerIdleL[4] = loader.loadImage("/Idle04L.png");
	playerIdleL[5] = loader.loadImage("/Idle05L.png");
	playerIdleL[6] = loader.loadImage("/Idle06L.png");
	playerIdleL[7] = loader.loadImage("/Idle07L.png");
	playerIdleL[8] = loader.loadImage("/Idle08L.png");
	playerIdleL[9] = loader.loadImage("/Idle09L.png");
	
	playerJumpR[0] = loader.loadImage("/Jump00R.png"); // JUMP RIGHT
	playerJumpR[1] = loader.loadImage("/Jump01R.png");
	playerJumpR[2] = loader.loadImage("/Jump02R.png");
	playerJumpR[3] = loader.loadImage("/Jump03R.png");
	playerJumpR[4] = loader.loadImage("/Jump04R.png");
	
	playerJumpL[0] = loader.loadImage("/Jump00L.png"); // JUMP LEFT
	playerJumpL[1] = loader.loadImage("/Jump01L.png");
	playerJumpL[2] = loader.loadImage("/Jump02L.png");
	playerJumpL[3] = loader.loadImage("/Jump03L.png");
	playerJumpL[4] = loader.loadImage("/Jump04L.png");
	
	playerDeadR[0] = loader.loadImage("/Dead00R.png");  // PLAYER DEAD RIGHT
	playerDeadR[1] = loader.loadImage("/Dead01R.png");
	playerDeadR[2] = loader.loadImage("/Dead02R.png");
	playerDeadR[3] = loader.loadImage("/Dead03R.png");
	playerDeadR[4] = loader.loadImage("/Dead04R.png");
	playerDeadR[5] = loader.loadImage("/Dead05R.png");
	playerDeadR[6] = loader.loadImage("/Dead06R.png");
	
	playerDeadL[0] = loader.loadImage("/Dead00L.png");  // PLAYER DEAD LEFT
	playerDeadL[1] = loader.loadImage("/Dead01L.png");
	playerDeadL[2] = loader.loadImage("/Dead02L.png");
	playerDeadL[3] = loader.loadImage("/Dead03L.png");
	playerDeadL[4] = loader.loadImage("/Dead04L.png");
	playerDeadL[5] = loader.loadImage("/Dead05L.png");
	playerDeadL[6] = loader.loadImage("/Dead06L.png");
	
	blocks[0] = loader.loadImage("/00.png");  // BLOCKS http://www.gameart2d.com/free-desert-platformer-tileset.html
	blocks[1] = loader.loadImage("/01.png");
	blocks[2] = loader.loadImage("/02.png");
	blocks[3] = loader.loadImage("/03.png");
	blocks[4] = loader.loadImage("/04.png");
	blocks[5] = loader.loadImage("/05.png");
	blocks[6] = loader.loadImage("/06.png");
	blocks[7] = loader.loadImage("/07.png");
	blocks[8] = loader.loadImage("/08.png");
	blocks[9] = loader.loadImage("/09.png");
	blocks[10] = loader.loadImage("/10.png");
	blocks[11] = loader.loadImage("/11.png");
	blocks[12] = loader.loadImage("/12.png");
	blocks[13] = loader.loadImage("/13.png");
	blocks[14] = loader.loadImage("/14.png");
	blocks[15] = loader.loadImage("/15.png");
	
	levelend = loader.loadImage("/level_end.png");
	
	movingBlockX = loader.loadImage("/MovingBlockX.png");
	movingBlockY = loader.loadImage("/MovingBlockY.png");
	
	water = loader.loadImage("/16.png");
	waterDeep = loader.loadImage("/17.png");
	
	coinImage = loader.loadImage("/coin32.png");
	coinSheet = new SpriteSheet(coinImage);
	
	for (int i = 1; i < 61; i++) coinAnim[i-1] = coinSheet.grabImage(i, 1, 32, 32);
	
	beeR[0] = loader.loadImage("/BeeR01.png");
	beeR[1] = loader.loadImage("/BeeR02.png");
	beeR[2] = loader.loadImage("/BeeR03.png");
	beeR[3] = loader.loadImage("/BeeR04.png");
	beeR[4] = loader.loadImage("/BeeR05.png");
	
	beeL[0] = loader.loadImage("/BeeL01.png");
	beeL[1] = loader.loadImage("/BeeL02.png");
	beeL[2] = loader.loadImage("/BeeL03.png");
	beeL[3] = loader.loadImage("/BeeL04.png");
	beeL[4] = loader.loadImage("/BeeL05.png");
	
	heart = loader.loadImage("/heart.png");
	
	tequilaImage = loader.loadImage("/tequila_bottle.png");
	
	tacoImage = loader.loadImage("/taco.png");	
	
	dartThrowerR = loader.loadImage("/BadCactusR.png");
	dartThrowerL = loader.loadImage("/BadCactusL.png");
	
	dartR = loader.loadImage("/dartRight.png");
	dartL = loader.loadImage("/dartLeft.png");
	
	movingCrate = loader.loadImage("/MovingCrate.png");
}
}