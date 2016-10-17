package com.platformer.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.logging.Level;

import com.platformer.game.graphics.Animation;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.Achievements;
import com.platformer.game.main.MainClass;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectsHandler;

public class PlayerObject extends GameObject{

	
private ObjectsHandler objectsHandler;

private ObjectOutputStream oos;
private Animation playerRunRight, playerRunLeft, playerIdleRight, playerIdleLeft, playerJumpRight, playerJumpLeft, playerFallingRight, playerFallingLeft;

private int MAX_HEALTH = 5;


private static final float NORMAL_GRAVITY = 0.5f;
private final float MAX_SPEED = 13f;
private final int HIT_COOLDOWN = 60*2;
private final int TEQUILA_COOLDOWN = 60*5;
private final int TACO_COOLDOWN = 60*8;
private final int IMMORTALITY_COOLDOWN = 60*15;
private final int DUST_TIME = 20;
private float width, height;
private float velX = 0;
private float velY = 0;
private float gravity;
private boolean onGround = false;
private boolean jumping = false;
private boolean visible;
private boolean action;
private float x, y;
private int direction;
private int health;

private int immortality_time = IMMORTALITY_COOLDOWN;
private boolean immortality = false;

private int hit_time = HIT_COOLDOWN;
private boolean hit_by_enemy = false;

private int tequila_time = TEQUILA_COOLDOWN;
private boolean tequila_powerUp = false;

private int taco_time = TACO_COOLDOWN;
private boolean taco_powerUp = false;

private int dust_time = DUST_TIME;
private boolean isDust = false;

private int dustX, dustY;
private int cactus_wake;
private int dustFrame;
private boolean finishLevel = false;
private int maxCoins;
private int maxPowerups;
private int powerups;
private boolean noHarm;
private Achievements achievements;
private int[] collectiblesList;




public PlayerObject(float x, float y, ObjectsHandler objectsHandler, Achievements achievements, int[] collectiblesList) {
	super();
	this.x = x;
	this.y = y;
	this.achievements = achievements;
	this.collectiblesList = collectiblesList;
	velX = 0;
	velY = 0;
	width = 110;
	height = 120;
	this.objectsHandler = objectsHandler;
	action = false;
	noHarm = true;
	visible = true;
	direction = 1;
	dustX = 0;
	dustY = 0;
	dustFrame = 0;
	maxCoins = 0;
	powerups = 0;
	health = MAX_HEALTH;
	gravity = NORMAL_GRAVITY;
	cactus_wake = 0;
	
	
	playerRunRight = new Animation(3, Textures.getInstance().playerRunR[0], Textures.getInstance().playerRunR[1], Textures.getInstance().playerRunR[2]
			, Textures.getInstance().playerRunR[3], Textures.getInstance().playerRunR[4], Textures.getInstance().playerRunR[5], Textures.getInstance().playerRunR[6]
			, Textures.getInstance().playerRunR[7], Textures.getInstance().playerRunR[8], Textures.getInstance().playerRunR[9]);
	
	playerRunLeft = new Animation(3, Textures.getInstance().playerRunL[0], Textures.getInstance().playerRunL[1], Textures.getInstance().playerRunL[2]
			, Textures.getInstance().playerRunL[3], Textures.getInstance().playerRunL[4], Textures.getInstance().playerRunL[5], Textures.getInstance().playerRunL[6]
			, Textures.getInstance().playerRunL[7], Textures.getInstance().playerRunL[8], Textures.getInstance().playerRunL[9]);
	
	playerIdleRight = new Animation(3, Textures.getInstance().playerIdleR[0], Textures.getInstance().playerIdleR[1], Textures.getInstance().playerIdleR[2]
			, Textures.getInstance().playerIdleR[3], Textures.getInstance().playerIdleR[4], Textures.getInstance().playerIdleR[5], Textures.getInstance().playerIdleR[6]
			, Textures.getInstance().playerIdleR[7], Textures.getInstance().playerIdleR[8], Textures.getInstance().playerIdleR[9]);
	
	playerIdleLeft = new Animation(3, Textures.getInstance().playerIdleL[0], Textures.getInstance().playerIdleL[1], Textures.getInstance().playerIdleL[2]
			, Textures.getInstance().playerIdleL[3], Textures.getInstance().playerIdleL[4], Textures.getInstance().playerIdleL[5], Textures.getInstance().playerIdleL[6]
			, Textures.getInstance().playerIdleL[7], Textures.getInstance().playerIdleL[8], Textures.getInstance().playerIdleL[9]);
	
	playerJumpRight = new Animation(10, Textures.getInstance().playerJumpR[0], Textures.getInstance().playerJumpR[1], Textures.getInstance().playerJumpR[2]
			, Textures.getInstance().playerJumpR[3], Textures.getInstance().playerJumpR[4], Textures.getInstance().playerJumpR[4], Textures.getInstance().playerJumpR[4]);
	
	playerJumpLeft = new Animation(10, Textures.getInstance().playerJumpL[0], Textures.getInstance().playerJumpL[1], Textures.getInstance().playerJumpL[2]
			, Textures.getInstance().playerJumpL[3], Textures.getInstance().playerJumpL[4], Textures.getInstance().playerJumpL[4], Textures.getInstance().playerJumpL[4]);
	
	playerFallingRight = new Animation(10, Textures.getInstance().playerJumpR[3], Textures.getInstance().playerJumpR[3], Textures.getInstance().playerJumpR[3]
			, Textures.getInstance().playerJumpR[3], Textures.getInstance().playerJumpR[4], Textures.getInstance().playerJumpR[4], Textures.getInstance().playerJumpR[4]);
	
	playerFallingLeft = new Animation(10, Textures.getInstance().playerJumpL[3], Textures.getInstance().playerJumpL[3], Textures.getInstance().playerJumpL[3]
			, Textures.getInstance().playerJumpL[3], Textures.getInstance().playerJumpL[4], Textures.getInstance().playerJumpL[4], Textures.getInstance().playerJumpL[4]);
}


@Override
public void tick(LinkedList<GameObject> object) {
	
	if (!finishLevel)
	{
		if ((MainScreen.PLAYER_LEFT) || (MainScreen.PLAYER_RIGHT))
			{
				if ((MainScreen.PLAYER_LEFT)) {
					if (velX > -5) velX -=0.5f;
					direction = -1;
				}
				if (MainScreen.PLAYER_RIGHT) {
					if (velX < 5) velX +=0.5f;
					direction = 1;
				}
			}
			else {
				velX *= 0.80f;
				if (velX < 0.15 && velX > -0.15) velX = 0;
			}
		
		if ((MainScreen.PLAYER_JUMP) && (!jumping) && (onGround)) {
			
			MainClass.jumpSound.play();
			velY = -12;
			jumping = true;
		}
	}
	
	if (!onGround) velY += gravity;
	if (velY > MAX_SPEED) velY = MAX_SPEED;

	if (velY != 0) jumping = true;
	
	x += velX;
	y += velY;
	
	// GRANICE PORUSZANIA SIÊ PO POZIOMIE
	if (x < 10) x = 10;
	if (x > 232 * 64) x = 232*64;
	
	//ŒMIERÆ PRZY SPADNIÊCIU W DÓ£
	if (y > 1130) health = 0;
	
	onGround = false;
	
	gravity = NORMAL_GRAVITY;
	if (tequila_powerUp)
	{
		gravity = 0.2f;
		
		if (tequila_time > 0) tequila_time--;
		else {
			tequila_time = TEQUILA_COOLDOWN;
			tequila_powerUp = false;
		}
	}
	
	if (isDust)
	{
		if (dust_time > 0) {
			dust_time--;
			if (dust_time > 15) dustFrame = 0;
			if (dust_time > 8 && dust_time <= 15) dustFrame = 1;
			if (dust_time <= 8) dustFrame = 2;
			}
		else {
			dust_time = DUST_TIME;
			isDust = false;
		}
	}
	
	if (taco_powerUp)
	{
		gravity = 0.9f;
		
		if (taco_time > 0) taco_time--;
		else {
			taco_time = TACO_COOLDOWN;
			taco_powerUp = false;
		}
	}
	
	if (immortality)
	{	
		if (immortality_time > 0) immortality_time--;
		else {
			immortality_time = IMMORTALITY_COOLDOWN;
			immortality = false;
		}
	}
	
	if (hit_by_enemy)
	{
		if (hit_time > 0) hit_time--;
		else {
			hit_time = HIT_COOLDOWN;
			hit_by_enemy = false;
		}
	}
	
	if (!finishLevel) MainScreen.time_bonus -= 0.02f;
	
	playerRunRight.runAnimation();
	playerRunLeft.runAnimation();
	playerIdleRight.runAnimation();
	playerIdleLeft.runAnimation();
	playerJumpRight.runAnimation();
	playerJumpLeft.runAnimation();
	playerFallingRight.runAnimation();
	playerFallingLeft.runAnimation();
	
	collisions();
}


private void collisions()
{
		/// BLOCK LIST
		for (int i = 0; i < objectsHandler.getBlock_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getBlock_List().get(i);
			if (tempObject.isVisible())
			{
				if (getBoundsTop().intersects(tempObject.getBounds()))
				{
					if (y > (tempObject.getBounds().y - tempObject.getHeight())) {
						y = tempObject.getY() + 40;
						velY = 0;	
					}
				}
					
				if (getBounds().intersects(tempObject.getBounds()))
				{			
					if (velY > 10) {
						dustX = (int) x-20;
						dustY = (int) y+75;
						isDust = true;
					}
					y = tempObject.getY() - 103;
					jumping = false;
					velY = 0;
					onGround = true;
				}
					
				if (getBoundsRight().intersects(tempObject.getBounds())) x = tempObject.getX() -75;
				
				if (getBoundsLeft().intersects(tempObject.getBounds())) x = tempObject.getX() + 55;
			}
		}
		
		/// MOVING BLOCK Y LIST
		for (int i = 0; i < objectsHandler.getMovingBlockY_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getMovingBlockY_List().get(i);
			if (tempObject.isVisible())
			{
				if (getBoundsTop().intersects(tempObject.getBounds()))
				{
					if (y > (tempObject.getBounds().y - tempObject.getHeight())) {				
						y = tempObject.getY() + 41;
						velY = 0;	
					}
				}
					
				if (getBounds().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY() -103;
					jumping = false;
					velY = 0;
					onGround = true;
					if (velY == 0) y += tempObject.getVelY();
				}
					
				if (getBoundsRight().intersects(tempObject.getBounds())) x = tempObject.getX() - 75;
					
				if (getBoundsLeft().intersects(tempObject.getBounds())) x = tempObject.getX() + 79;
			}
		}
		
		/// MOVING BLOCK X LIST
		for (int i = 0; i < objectsHandler.getMovingBlockX_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getMovingBlockX_List().get(i);
			if (tempObject.isVisible())
			{
				if (getBoundsTop().intersects(tempObject.getBounds()))
				{
					if (y > (tempObject.getBounds().y - tempObject.getHeight())) {				
						y = tempObject.getY() + 41;
						velY = 0;
						x += tempObject.getVelX();
					}
				}
						
				if (getBounds().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY() -103;
					jumping = false;
					velY = 0;
					onGround = true;
					if (velX == 0) x += tempObject.getVelX();
				}
							
				if (getBoundsRight().intersects(tempObject.getBounds())) x = tempObject.getX() - 75;
					
				if (getBoundsLeft().intersects(tempObject.getBounds())) x = tempObject.getX() + 79;
			}
		}
		
		/// LEVEL END LIST
		for (int i = 0; i < objectsHandler.getLevelEnd_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getLevelEnd_List().get(i);
			if (tempObject.isVisible())
			{
				if (getBounds().intersects(tempObject.getBounds()))
				{
					if (MainScreen.COINS == maxCoins) achievements.addFindAllCoinsCount();
					if (powerups == maxPowerups) achievements.addFindAllPowerupsCount();
					if (noHarm) achievements.addNoHarmCount();
					
					if (MainScreen.LEVEL == 1) achievements.addComplete1LevelCount();
					if (MainScreen.LEVEL == 2) achievements.addComplete2LevelCount();
					if (MainScreen.LEVEL == 3) achievements.addComplete3LevelCount();
					if (MainScreen.LEVEL == 4) achievements.addComplete4LevelCount();
					if (MainScreen.LEVEL == 5) achievements.addComplete5LevelCount();
					if (MainScreen.LEVEL == 6) achievements.addComplete6LevelCount();
					if (MainScreen.LEVEL == 7) achievements.addComplete7LevelCount();
					if (MainScreen.LEVEL == 8) achievements.addComplete8LevelCount();
					if (MainScreen.LEVEL == 9) achievements.addComplete9LevelCount();
					if (MainScreen.LEVEL == 10) achievements.addComplete10LevelCount();
					velX = 0f;
					velY = 0f;
					finishLevel = true;
				}				
			}
		}

		/// COIN LIST
		for (int i = 0; i < objectsHandler.getCoin_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getCoin_List().get(i);
			if (tempObject.isVisible())
			{
				if (getWholeBounds().intersects(tempObject.getBounds()))
				{
					objectsHandler.getCoin_List().remove(tempObject);
					MainScreen.COINS++;
					MainScreen.SCORE += 20;
					if (!achievements.isCoinCount20Complete()) achievements.addCoin20Count();

					if (!achievements.isCoinCount50Complete()) achievements.addCoin50Count();
					
					if (!achievements.isCoinCount100Complete()) achievements.addCoin100Count();
					
					if (!achievements.isCoinCount150Complete()) achievements.addCoin150Count();
					
					MainClass.coinSound.play();
				}
			}
		}
		
		/// TEQUILA LIST
		for (int i = 0; i < objectsHandler.getTequila_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getTequila_List().get(i);
			if (tempObject.isVisible())
			{
				if (getWholeBounds().intersects(tempObject.getBounds()) && (!isTequila_powerUp()))
				{
					objectsHandler.getTequila_List().remove(tempObject);
					MainScreen.SCORE += 35;
					MainClass.powerUpSound.play();
					achievements.addPowerup3Count();
					tequila_powerUp = true;
					powerups++;
					taco_powerUp = false;
					taco_time = TACO_COOLDOWN;
				}
			}
		}
		
		
		/// TACO LIST
		for (int i = 0; i < objectsHandler.getTaco_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getTaco_List().get(i);
			if (tempObject.isVisible())
			{
				if (getWholeBounds().intersects(tempObject.getBounds()) && (health < MAX_HEALTH))
				{
					objectsHandler.getTaco_List().remove(tempObject);
					MainScreen.SCORE += 35;
					MainClass.powerUpSound.play();
					achievements.addPowerup3Count();
					taco_powerUp = true;
					powerups++;
					tequila_powerUp = false;
					tequila_time = TEQUILA_COOLDOWN;
					health++;
				}
			}
		}
			
		/// DART LIST
		for (int i = 0; i < objectsHandler.getDart_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getDart_List().get(i);
			if (tempObject.isVisible())
			{
				if (getWholeBounds().intersects(tempObject.getBounds()) && !hit_by_enemy && !immortality)
				{
					if (tempObject.getX() > x) x -= 10;
					else x += 10;
					health--;
					noHarm = false;
					MainScreen.SCORE -=40;
					MainClass.hitSound.play();
					hit_by_enemy = true;
					objectsHandler.getDart_List().remove(tempObject);
				}
				
				if (tempObject.getVelX() == 0) {
					objectsHandler.getDart_List().remove(tempObject);
				}
			}
		}
		
		
		/// ANGRY CACTUS LIST
		for (int i = 0; i < objectsHandler.getAngryCactus_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getAngryCactus_List().get(i);
			if (tempObject.isVisible())
			{
				if (!getWholeBounds().intersects(tempObject.getBounds())) cactus_wake = 0;
				if (getWholeBounds().intersects(tempObject.getBounds()))
				{
					tempObject.setVelX(1);
					if (tempObject.getX() > x) tempObject.setDirection(-1);
					else tempObject.setDirection(1);
					
					cactus_wake++;
					
					if (!tempObject.isAction()) {
						if (cactus_wake > 20) {
							objectsHandler.getDart_List().add(new Dart((int) tempObject.getX(), (int) tempObject.getY(), tempObject.getDirection(), objectsHandler));
							MainClass.cactusShotSound.play();
							cactus_wake = 0;
						}
						tempObject.setAction(true);
					}
				} else {
					tempObject.setVelX(0);
				}
			}
		}

		/// MOVING CRATE LIST
		for (int i = 0; i < objectsHandler.getMovingCrate_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getMovingCrate_List().get(i);
			tempObject.setVelX(0);
			if (tempObject.isVisible())
			{
				if (getBounds().intersects(tempObject.getBounds()))
				{			
					y = tempObject.getY() - 119;
					jumping = false;
					velY = 0;
					onGround = true;
				}
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - 75;
					tempObject.setVelX(2);
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 68;
					tempObject.setVelX(-2);
				}
			}
		}

		/// BEE LIST
		for (int i = 0; i < objectsHandler.getBee_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getBee_List().get(i);
			if (tempObject.isVisible())
			{
				if (getWholeBounds().intersects(tempObject.getBounds()) && !hit_by_enemy && !immortality)
				{
					if (tempObject.getX() > x) x -= 25;
					else x += 25;
					health--;
					noHarm = false;
					MainScreen.SCORE -=40;
					MainClass.hitSound.play();
					hit_by_enemy = true;
				}
			}
		}
		
		/// PUSHING MOVING BLOCK X LIST
		for (int i = 0; i < objectsHandler.getPushingMovingBlockX_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getPushingMovingBlockX_List().get(i);
			if (tempObject.isVisible())
			{
				if (getBoundsTop().intersects(tempObject.getBounds()))
				{
					if (y > (tempObject.getBounds().y - tempObject.getHeight())) {				
						y = tempObject.getY() + 41;
						velY = 0;
						x += tempObject.getVelX();
					}
				}
						
				if (getBounds().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY() -103;
					jumping = false;
					velY = 0;
					onGround = true;
					if (velX == 0) x += tempObject.getVelX();
				}
							
				if (getBoundsRight().intersects(tempObject.getBounds())) x = tempObject.getX() - 75;
					
				if (getBoundsLeft().intersects(tempObject.getBounds())) x = tempObject.getX() + 79;
			}
		}
		
		/// PUSHING MOVING BLOCK Y LIST
		for (int i = 0; i < objectsHandler.getPushingMovingBlockY_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getPushingMovingBlockY_List().get(i);
			if (tempObject.isVisible())
			{
				if (getBoundsTop().intersects(tempObject.getBounds()))
				{
					if (y > (tempObject.getBounds().y - tempObject.getHeight())) {				
						y = tempObject.getY() + 41;
						velY = 0;	
					}
				}
					
				if (getBounds().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY() -103;
					jumping = false;
					velY = 0;
					onGround = true;
					if (velY == 0) y += tempObject.getVelY();
				}
					
				if (getBoundsRight().intersects(tempObject.getBounds())) x = tempObject.getX() - 75;
					
				if (getBoundsLeft().intersects(tempObject.getBounds())) x = tempObject.getX() + 79;
			}
		}
		
		/// BUTTON BLOCK LIST
		for (int i = 0; i < objectsHandler.getButtonBlock_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getButtonBlock_List().get(i);
			if (tempObject.isVisible())
			{
				if (getBoundsTop().intersects(tempObject.getBounds()))
				{
					if (y > (tempObject.getBounds().y - tempObject.getHeight())) {
						
						y = tempObject.getY() + 49;
						velY = 0;	
					}
				}
					
				if (getBounds().intersects(tempObject.getBounds()))
				{			
					y = tempObject.getY() - 103;
					jumping = false;
					velY = 0;
					onGround = true;
					tempObject.setAction(true);
				}

				if (getBoundsRight().intersects(tempObject.getBounds())) x = tempObject.getX() -75;
				
				if (getBoundsLeft().intersects(tempObject.getBounds())) x = tempObject.getX() + 55;
			}
		}
		
		/// SPIKE BLOCK LIST
		for (int i = 0; i < objectsHandler.getSpikeBlock_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getSpikeBlock_List().get(i);
			if (tempObject.isVisible())
			{
				if (getBoundsTop().intersects(tempObject.getBounds()))
				{
					if (y > (tempObject.getBounds().y - tempObject.getHeight())) {
							
						y = tempObject.getY() + 49;
						velY = 0;	
					}
				}
							
				if (getBounds().intersects(tempObject.getBounds()))
				{			
					y = tempObject.getY() - 103;
					jumping = false;
					velY = 0;
					onGround = true;
					if (!hit_by_enemy && tempObject.isAction() && !immortality)
					{
						y -= 90;
						health--;
						noHarm = false;
						MainScreen.SCORE -=40;
						MainClass.hitSound.play();
						hit_by_enemy = true;	
					}
				}

				if (getBoundsRight().intersects(tempObject.getBounds())) x = tempObject.getX() -75;
					
				if (getBoundsLeft().intersects(tempObject.getBounds())) x = tempObject.getX() + 55;
			}
		}
		
		/// TUMBLEWEED LIST
		for (int i = 0; i < objectsHandler.getTumbleweed_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getTumbleweed_List().get(i);
			if (tempObject.isVisible())
			{
				if (getWholeBounds().intersects(tempObject.getBounds()) && !hit_by_enemy && !immortality)
				{
					if (tempObject.getX() > x) x -= 25;
					else x += 25;
					health--;
					noHarm = false;
					MainScreen.SCORE -=40;
					MainClass.hitSound.play();
					hit_by_enemy = true;
				}
			}
		}
		
		/// SPRING BLOCK LIST
		for (int i = 0; i < objectsHandler.getSpringBlock_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getSpringBlock_List().get(i);
			if (tempObject.isVisible())
			{
				if (getBoundsTop().intersects(tempObject.getBounds()))
				{
					if (y > (tempObject.getBounds().y - tempObject.getHeight())) {
							
						y = tempObject.getY() + 49;
						velY = 0;	
					}
				}
									
				if (getBounds().intersects(tempObject.getBounds()))
				{			
					y = tempObject.getY() - 107;
					if (jumping) {
						velY = -18;
						if (tequila_powerUp) achievements.addMegaJumpCount();
						MainClass.springJumpSound.play();
						tempObject.setAction(true);
					}
					onGround = true;
				}

				if (getBoundsRight().intersects(tempObject.getBounds())) x = tempObject.getX() -75;
							
				if (getBoundsLeft().intersects(tempObject.getBounds())) x = tempObject.getX() + 55;
			}
		}
		
		/// FALLING BLOCK LIST
		for (int i = 0; i < objectsHandler.getFallingBlock_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getFallingBlock_List().get(i);
			if (tempObject.isVisible())
			{
				if (getBoundsTop().intersects(tempObject.getBounds()))
				{
					if (y > (tempObject.getBounds().y - tempObject.getHeight())) {
						
						y = tempObject.getY() + 20;
						velY = 0;	
					}
				}
							
				if (getBounds().intersects(tempObject.getBounds()))
				{			
					y = tempObject.getY() - 103;
					jumping = false;
					velY = 0;
					onGround = true;
					tempObject.setAction(true);
				}
							
				if (getBoundsRight().intersects(tempObject.getBounds())) x = tempObject.getX() -75;
					
				if (getBoundsLeft().intersects(tempObject.getBounds())) x = tempObject.getX() + 75;	
			}
		}
		
		/// COLLECTIBLES LIST
		for (int i = 0; i < objectsHandler.getCollectibles_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getCollectibles_List().get(i);
			if (tempObject.isVisible())
			{
				if (getWholeBounds().intersects(tempObject.getBounds()))
				{
					
					collectiblesList[(int) tempObject.getVelX()] ++;
					
					try {
						oos = new ObjectOutputStream(new FileOutputStream((MainClass.collectiblesFile)));
					    oos.writeObject(collectiblesList);
					    oos.close();
					    MainClass.logging(false, Level.INFO, "Plik Collectibles " +MainClass.collectiblesFile.getName() +" zosta³ poprawnie zapisany.");
						}
						catch (IOException ioe)
						{
							MainClass.logging(false, Level.WARNING, "B³¹d zapisu plików Collectibles " +MainClass.collectiblesFile.getName());
							MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(ioe));
						}
					
					objectsHandler.getCollectibles_List().remove(tempObject);
					MainScreen.SCORE += 50;
					MainClass.powerUpSound.play();
				}
			}
		}
		
		/// GOAT LIST
		for (int i = 0; i < objectsHandler.getGoat_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getGoat_List().get(i);
			if (tempObject.isVisible())
			{
				if (getWholeBounds().intersects(tempObject.getBounds()))
				{
					MainScreen.endgame = true;
				}
			}
		}
}



@Override
public void render(Graphics g) {
	
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(Color.BLUE);
		
	if (health > 0) {
		if ((velX > 0.1f) && (!jumping) && (onGround)) playerRunRight.drawAnimation(g, (int) x, (int) y+6, hit_by_enemy);
		if ((velX < -0.1f) && (!jumping) && (onGround)) playerRunLeft.drawAnimation(g, (int) x, (int) y+6, hit_by_enemy);
			
		if (direction == 1 && !jumping && onGround && velX == 0) playerIdleRight.drawAnimation(g, (int) x, (int) y+6, hit_by_enemy);
		if (direction == -1 && !jumping && onGround && velX == 0) playerIdleLeft.drawAnimation(g, (int) x, (int) y+6, hit_by_enemy);
			
		if ((jumping) && (direction == 1)) playerJumpRight.drawAnimation(g, (int) x, ( int )y+6, hit_by_enemy);
		if ((jumping) && (direction == -1)) playerJumpLeft.drawAnimation(g, (int) x, ( int )y+6, hit_by_enemy);
		
		if (!onGround && !jumping && direction == 1) playerFallingRight.drawAnimation(g, (int) x, ( int )y+6, hit_by_enemy);
		if (!onGround && !jumping && direction == -1) playerFallingLeft.drawAnimation(g, (int) x, ( int )y+6, hit_by_enemy);
	}
	
	if (isDust)	g2d.drawImage(Textures.getInstance().dust[dustFrame], dustX, dustY, null);
		
	//g2d.draw(getBounds());
	//g2d.draw(getBoundsTop());
	//g2d.draw(getBoundsLeft());
	//g2d.draw(getBoundsRight());
	//g2d.draw(getWholeBounds());
}

/// COLLISION BOUNDS !!!

@Override
public Rectangle getBounds() {
	return new Rectangle((int) (x +13), (int) (y+93), 40, 14);
}

private Rectangle getBoundsTop() {
	return new Rectangle((int) x +10, (int) y +10, (int) 50, (int) 10);
}

private Rectangle getBoundsRight() {
	return new Rectangle((int) ((int) x+width-25)-20, (int)y+20, 10, (int) height -48);
}

private Rectangle getBoundsLeft() {
	return new Rectangle((int) x-5, (int)y+20, 10, (int) height -48);
}

private Rectangle getWholeBounds() {
	return new Rectangle((int) x, (int) y+10, (int) 65, (int) 95);
}


//OTHER

@Override
public float getX()
{
	return x;
}

@Override
public void setX(float x)
{
	this.x = x;
}

@Override
public float getY()
{
	return y;
}

@Override
public void setY(float y)
{
	this.y = y;
}

public int getHealth() {
	return health;
}

public void setHealth(int health) {
	this.health = health;
}

@Override
public float getVelX() {
	return velX;
}

@Override
public void setVelX(float velX) {
	this.velX = velX;
}

@Override
public float getVelY() {
	return velY;
}

@Override
public void setVelY(float velY) {
	this.velY = velY;
}

public boolean isOnGround() {
	return onGround;
}

public boolean isJumping() {
	return jumping;
}

public float getGravity() {
	return gravity;
}

public int getTequila_time() {
	return tequila_time;
}

public boolean isTequila_powerUp() {
	return tequila_powerUp;
}

public int getTaco_time() {
	return taco_time;
}

public boolean isTaco_powerUp() {
	return taco_powerUp;
}

public boolean isLevelFinished() {
	return finishLevel;
}

public void setFinishLevel(boolean finishLevel) {
	this.finishLevel = finishLevel;
}

@Override
public boolean isAction() {
	return action;
}

@Override
public void setAction(boolean action) {
	this.action = action;
}

@Override
public int getDirection() {
	return direction;
}

@Override
public void setDirection(int direction) {
	this.direction = direction;
}

@Override
public float getWidth() {
	return width;
}

@Override
public void setWidth(float width) {
	this.width = width;
}

@Override
public float getHeight() {
	return height;
}

@Override
public void setHeight(float height) {	
	this.height = height;
}

@Override
public boolean isVisible() {
	return visible;
}

@Override
public void setVisible(boolean visible) {
	this.visible = visible;
}


public boolean isImmortality()
{
	return immortality;
}

public void setImmortality(boolean immortality)
{
	this.immortality = immortality;
}

public int getImmortalityCooldown()
{
	return IMMORTALITY_COOLDOWN;
}

public int getImmortality_time() {
	return immortality_time;
}

public void setImmortality_time(int immortality_time) {
	this.immortality_time = immortality_time;
}

public void setMaxCoins(int maxCoins)
{
	this.maxCoins = maxCoins;
}

public int getMaxHealth()
{
	return MAX_HEALTH;
}

public void setMaxHealth(int maxHealth)
{
	MAX_HEALTH = maxHealth;
}

public void setMaxPowerups(int maxPowerups)
{
	this.maxPowerups = maxPowerups;
}

public int getMaxPowerups()
{
	return maxPowerups;
}
}