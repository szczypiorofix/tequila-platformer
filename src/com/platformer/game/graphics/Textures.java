package com.platformer.game.graphics;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;


/** Klasa, kt�rej obiekt przechowuje wszystkie grafiki w grze (Design Patterns - Singleton).
 * @author Piotrek
 *
 */
public final class Textures {

/** Podstawowa i jedyna instancja klasy Textures.
 * 
 */
private static Textures instance = null;


public BufferedImage[] block = new BufferedImage[12];
public BufferedImage[] playerRunR = new BufferedImage[10];
public BufferedImage[] playerRunL = new BufferedImage[10];
public BufferedImage[] playerIdleR = new BufferedImage[10];
public BufferedImage[] playerIdleL = new BufferedImage[10];
public BufferedImage[] playerJumpR = new BufferedImage[5];
public BufferedImage[] playerJumpL = new BufferedImage[5];
public BufferedImage[] playerDeadR = new BufferedImage[7];
public BufferedImage[] playerDeadL = new BufferedImage[7];

public BufferedImage backGroundMountains, bg_gory, bg_niebo;
public BufferedImage screenShotImage;
public BufferedImage bgMenuImage;
public BufferedImage sun;
public BufferedImage achievementBg;
public BufferedImage menuBg;

public BufferedImage[] sceneryObjects = new BufferedImage[12];
public BufferedImage[] blocks = new BufferedImage[16];
public BufferedImage movingBlockX, movingBlockY;
public BufferedImage waterDeep, water1, water2, water3, water4, water5;
public BufferedImage levelend;
private BufferedImage coinImage;
public SpriteSheet coinSheet;  // http://opengameart.org/content/coins-asset
public BufferedImage[] coinAnim = new BufferedImage[61];
public BufferedImage[] beeR = new BufferedImage[5];
public BufferedImage[] beeL = new BufferedImage[5];
public BufferedImage heart;
public BufferedImage tequilaImage;
public BufferedImage tacoImage;
public BufferedImage angryCactusR, angryCactusL, angryCactus0;
public BufferedImage dartR, dartL;
public BufferedImage movingCrate;
public BufferedImage buttonBlock, buttonBlockOn;
public BufferedImage pushingMovingBlockXOff, pushingMovingBlockXOn, pushingMovingBlockYOff, pushingMovingBlockYOn;
public BufferedImage spikeBlock1, spikeBlock2, spikeBlock3, spikeBlock4;
public BufferedImage tumbleweed, tumbleweed1, tumbleweed2, tumbleweed3, tumbleweed4, tumbleweed5, tumbleweed6, tumbleweed7;
public BufferedImage springBlock, springBlock2;
public BufferedImage fallingBlock;
public BufferedImage clouds1, clouds2, clouds3;
public BufferedImage[] collectible = new BufferedImage[7];
public BufferedImage handMenuItemFrame;
public BufferedImage mainMenuButton, mainMenuButtonSelected;
public BufferedImage planeR;    // http://opengameart.org/content/red-biplane
public BufferedImage smiglo[] = new BufferedImage[4];
public BufferedImage flaga;
public BufferedImage literaA, literaE, literaF, literaI, literaL, literaM, literaO, literaP, literaQ, literaR, literaT, literaU;
public BufferedImage ptakiR1, ptakiR2;
public BufferedImage[] dust = new BufferedImage[3];
public BufferedImage websiteButton;
public BufferedImage handMenu;
public BufferedImage polishFlagImage, englishFlagImage;
public BufferedImage gameUIbg;
public BufferedImage immortalIcon;
public BufferedImage text_bg;
public BufferedImage intro_game_logo;
public BufferedImage intro_game_text1, intro_game_text2, intro_game_text3;
public BufferedImage lockedLevelPL, lockedLevelENG;
public BufferedImage koza;
public BufferedImage[] balony = new BufferedImage[6];


// ACHIEVMENTS
public final BufferedImage blank;
public final BufferedImage jump10Image;
public final BufferedImage jump25Image;
public final BufferedImage jump50Image;
public final BufferedImage coin20Image;
public final BufferedImage coin50Image;
public final BufferedImage coin100Image;
public final BufferedImage coin150Image;
public final BufferedImage powerup3Image;
public final BufferedImage complete1LevelImage;
public final BufferedImage complete2LevelImage;
public final BufferedImage complete3LevelImage;
public final BufferedImage complete4LevelImage;
public final BufferedImage complete5LevelImage;
public final BufferedImage complete6LevelImage;
public final BufferedImage complete7LevelImage;
public final BufferedImage complete8LevelImage;
public final BufferedImage complete9LevelImage;
public final BufferedImage complete10LevelImage;
public final BufferedImage completeGameImage;
public final BufferedImage findAllCoinsImage;
public final BufferedImage findAllPowerupsImage;
public final BufferedImage noHarmImage;
public final BufferedImage megaJumpImage;
public final BufferedImage sprinterImage;


public BufferedImage howToPlayImagePL, howToPlayImageEN, hallOfFameImage, creditsImagePL, creditsImageEN, achievementsMenuBGImage, collectiblesImage;



private Textures()
{	
	BufferedImageLoader loader = new BufferedImageLoader();
		
	// http://charas-project.net/charas2/  - GENERATOR !!!
	
	// http://www.piskelapp.com/  - spritesheet creator
	
	sceneryObjects[0] = loader.loadImage("/Bush (1).png");   // TILES http://www.gameart2d.com/free-desert-platformer-tileset.html
	sceneryObjects[1] = loader.loadImage("/Bush (2).png");
	sceneryObjects[2] = loader.loadImage("/Cactus (1).png");
	sceneryObjects[3] = loader.loadImage("/Cactus (2).png");
	sceneryObjects[4] = loader.loadImage("/Cactus (3).png");
	sceneryObjects[5] = loader.loadImage("/Grass (1).png");
	sceneryObjects[6] = loader.loadImage("/Grass (2).png");
	sceneryObjects[7] = loader.loadImage("/Sign.png");
	sceneryObjects[8] = loader.loadImage("/SignArrow.png");
	sceneryObjects[9] = loader.loadImage("/Skeleton.png");
	sceneryObjects[10] = loader.loadImage("/Stone.png");
	//sceneryObjects[12] = loader.loadImage("/StoneBlock.png");
	sceneryObjects[11] = loader.loadImage("/Tree.png");
	
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
	
	backGroundMountains = loader.loadImage("/BG.png");  // http://opengameart.org/content/generic-platformer-tileset-16x16-background
	sun = loader.loadImage("/sun.png");
	achievementBg = loader.loadImage("/achievementBg.png");
	menuBg = loader.loadImage("/gameMenuBackground.png");
	
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
	
	water1 = loader.loadImage("/16_1.png");
	water2 = loader.loadImage("/16_2.png");
	water3 = loader.loadImage("/16_3.png");
	water4 = loader.loadImage("/16_4.png");
	water5 = loader.loadImage("/16_5.png");
	
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
	
	tequilaImage = loader.loadImage("/TequilaBottle.png");
	
	tacoImage = loader.loadImage("/taco.png");	
	
	angryCactusR = loader.loadImage("/BadCactusR.png");
	angryCactusL = loader.loadImage("/BadCactusL.png");
	angryCactus0 = loader.loadImage("/BadCactus0.png");
	
	dartR = loader.loadImage("/dartRight.png");
	dartL = loader.loadImage("/dartLeft.png");
	
	movingCrate = loader.loadImage("/MovingCrate.png");
	
	buttonBlock = loader.loadImage("/ButtonBlock.png");
	buttonBlockOn = loader.loadImage("/ButtonBlockOn.png");
	pushingMovingBlockXOff = loader.loadImage("/PushingMovingBlockX Off.png");
	pushingMovingBlockXOn = loader.loadImage("/PushingMovingBlockX On.png");
	pushingMovingBlockYOff = loader.loadImage("/PushingMovingBlockY Off.png");
	pushingMovingBlockYOn = loader.loadImage("/PushingMovingBlockY On.png");
	
	spikeBlock1 = loader.loadImage("/Spike1.png");
	spikeBlock2 = loader.loadImage("/Spike2.png");
	spikeBlock3 = loader.loadImage("/Spike3.png");
	spikeBlock4 = loader.loadImage("/Spike4.png");
	
	tumbleweed = loader.loadImage("/tumbleweed.png");
	double rotationRequired = Math.toRadians (45);
	double locationX = tumbleweed.getWidth() / 2;
	double locationY = tumbleweed.getHeight() / 2;
	AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	
	tumbleweed1 = op.filter(tumbleweed, null);
	rotationRequired = Math.toRadians (90);
	tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
	op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	tumbleweed2 = op.filter(tumbleweed, null);
	rotationRequired = Math.toRadians (135);
	tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
	op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	tumbleweed3 = op.filter(tumbleweed, null);
	rotationRequired = Math.toRadians (180);
	tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
	op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	tumbleweed4 = op.filter(tumbleweed, null);
	rotationRequired = Math.toRadians (225);
	tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
	op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	tumbleweed5 = op.filter(tumbleweed, null);
	rotationRequired = Math.toRadians (270);
	tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
	op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	tumbleweed6 = op.filter(tumbleweed, null);
	rotationRequired = Math.toRadians (315);
	tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
	op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	tumbleweed7 = op.filter(tumbleweed, null);
	
	springBlock = loader.loadImage("/SpringBlock.png");
	springBlock2 = loader.loadImage("/SpringBlock2.png");
	
	fallingBlock = loader.loadImage("/FallingBlock.png");
	
	clouds1 = loader.loadImage("/clouds1.png");
	clouds2 = loader.loadImage("/clouds2.png");
	clouds3 = loader.loadImage("/clouds3.png");
	
	collectible[0] = loader.loadImage("/collectibles0.png");
	collectible[1] = loader.loadImage("/collectibles1.png");
	collectible[2] = loader.loadImage("/collectibles2.png");
	collectible[3] = loader.loadImage("/collectibles3.png");
	collectible[4] = loader.loadImage("/collectibles4.png");
	collectible[5] = loader.loadImage("/collectibles5.png");
	collectible[6] = loader.loadImage("/collectibles6.png");
	
	handMenuItemFrame = loader.loadImage("/handMenuItemFrame.png");
	
	mainMenuButton = loader.loadImage("/mainMenuButton.png");
	mainMenuButtonSelected = loader.loadImage("/mainMenuButtonSelected.png");
	
	howToPlayImagePL = loader.loadImage("/JakGrac.png");
	howToPlayImageEN = loader.loadImage("/HowToPlay.png");
	hallOfFameImage = loader.loadImage("/hallOfFameMenuBG.png");
	creditsImagePL = loader.loadImage("/OGrze.png");
	creditsImageEN = loader.loadImage("/AboutGame.png");
	achievementsMenuBGImage = loader.loadImage("/AchievementsMenuBG.png");
	collectiblesImage = loader.loadImage("/CollectiblesMenuBG.png");
	
	bg_gory = loader.loadImage("/BG_gory.png");
	bg_niebo = loader.loadImage("/BG_niebo.png");
	
	planeR = loader.loadImage("/planeR.png");
	
	smiglo[0] = loader.loadImage("/smiglo1.png");
	smiglo[1] = loader.loadImage("/smiglo2.png");
	smiglo[2] = loader.loadImage("/smiglo3.png");
	smiglo[3] = loader.loadImage("/smiglo4.png");
	
	flaga = loader.loadImage("/flaga.png");
	
	literaA = loader.loadImage("/literaA.png");
	literaE = loader.loadImage("/literaE.png");
	literaF = loader.loadImage("/literaF.png");
	literaI = loader.loadImage("/literaI.png");
	literaL = loader.loadImage("/literaL.png");
	literaM = loader.loadImage("/literaM.png");
	literaO = loader.loadImage("/literaO.png");
	literaP = loader.loadImage("/literaP.png");
	literaQ = loader.loadImage("/literaQ.png");
	literaR = loader.loadImage("/literaR.png");
	literaT = loader.loadImage("/literaT.png");
	literaU = loader.loadImage("/literaU.png");
	
	ptakiR1 = loader.loadImage("/ptakiR1.png");
	ptakiR2 = loader.loadImage("/ptakiR2.png");
	
	websiteButton = loader.loadImage("/websiteButton.png");
	
	handMenu = loader.loadImage("/handMenu.png");
	
	dust[0] = loader.loadImage("/dust1.png");
	dust[1] = loader.loadImage("/dust2.png");
	dust[2] = loader.loadImage("/dust3.png");
	
	polishFlagImage = loader.loadImage("/polishFlag.png");
	englishFlagImage = loader.loadImage("/englishFlag.png");
	gameUIbg = loader.loadImage("/gameUIbg.png");
	immortalIcon = loader.loadImage("/immortalIcon.png");
	text_bg = loader.loadImage("/text_bg.png");
	
	intro_game_logo = loader.loadImage("/intro_game_logo.png");
	intro_game_text1 = loader.loadImage("/intro_game_text1.png");
	intro_game_text2 = loader.loadImage("/intro_game_text2.png");
	intro_game_text3 = loader.loadImage("/intro_game_text3.png");
	
	lockedLevelPL = loader.loadImage("/lockedLevelPL.png");
	lockedLevelENG = loader.loadImage("/lockedLevelENG.png");
	
	koza = loader.loadImage("/koza.png");
	
	balony[0] = loader.loadImage("/baloon_blue.png");
	balony[1] = loader.loadImage("/baloon_darkblue.png");
	balony[2] = loader.loadImage("/baloon_green.png");
	balony[3] = loader.loadImage("/baloon_pink.png");
	balony[4] = loader.loadImage("/baloon_red.png");
	balony[5] = loader.loadImage("/baloon_yellow.png");
	
	blank = loader.loadImage("/Ablank.png");
	jump10Image = loader.loadImage("/A10jumps.png");
	jump25Image = loader.loadImage("/A25jumps.png");
	jump50Image = loader.loadImage("/A50jumps.png");
	coin20Image = loader.loadImage("/A20coins.png");
	coin50Image = loader.loadImage("/A50coins.png");
	coin100Image = loader.loadImage("/A100coins.png");
	coin150Image = loader.loadImage("/A150coins.png");
	powerup3Image = loader.loadImage("/A3powerups.png");
	complete1LevelImage = loader.loadImage("/Acomplete1Level.png");
	complete2LevelImage = loader.loadImage("/Acomplete2Level.png");
	complete3LevelImage = loader.loadImage("/Acomplete3Level.png");
	complete4LevelImage = loader.loadImage("/Acomplete4Level.png");
	complete5LevelImage = loader.loadImage("/Acomplete5Level.png");
	complete6LevelImage = loader.loadImage("/Acomplete6Level.png");
	complete7LevelImage = loader.loadImage("/Acomplete7Level.png");
	complete8LevelImage = loader.loadImage("/Acomplete8Level.png");
	complete9LevelImage = loader.loadImage("/Acomplete9Level.png");
	complete10LevelImage = loader.loadImage("/Acomplete10Level.png");
	completeGameImage = loader.loadImage("/AcompleteGame.png");
	findAllCoinsImage = loader.loadImage("/AfindAllCoins.png");
	findAllPowerupsImage = loader.loadImage("/AfindAllPowerup.png");
	noHarmImage = loader.loadImage("/AnoHarm.png");
	megaJumpImage = loader.loadImage("/AMegaJump.png");
	sprinterImage = loader.loadImage("/Asprinter.png");
}




/** Metoda zwracaj� podstawow� i jedyn� instancj� klasy Textures (zgodna z wzorcem Singleton).
 * @return instance - jedyna instancja obiektu klasy Textures.
 */
public static final Textures getInstance()
{
	if (instance == null) instance = new Textures();
	return instance;
}
}