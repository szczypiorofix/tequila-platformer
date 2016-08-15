package com.platformer.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Animation;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.Achievements;
import com.platformer.game.main.MainClass;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;
import com.platformer.game.main.ObjectsHandler;
import com.platformer.game.sounds.SoundsLoader;

public class PlayerObject extends GameObject{

	
private ObjectsHandler objectsHandler;
private SoundsLoader jumpSound, powerUpSound, coinSound, hitSound, cactusShotSound, springJumpSound;
private Textures tex = MainScreen.getTexturesInstance();
private Animation playerRunRight, playerRunLeft, playerIdleRight, playerIdleLeft, playerJumpRight, playerJumpLeft, playerFallingRight, playerFallingLeft;
private Achievements achievements = MainClass.getAchievementsInstance();
private static final int MAX_HEALTH = 5;
private static final float NORMAL_GRAVITY = 0.5f;
private final float MAX_SPEED = 13f;
private final int HIT_COOLDOWN = 60*2;
private final int TEQUILA_COOLDOWN = 60*5;
private final int TACO_COOLDOWN = 60*8;
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
private int hit_time = HIT_COOLDOWN;
private boolean hit_by_enemy = false;
private int tequila_time = TEQUILA_COOLDOWN;
private boolean tequila_powerUp = false;
private int taco_time = TACO_COOLDOWN;
private boolean taco_powerUp = false;
private boolean finishLevel = false;
private ObjectId id;





public PlayerObject(ObjectId id, float x, float y, ObjectsHandler objectsHandler) {
	super();
	this.x = x;
	this.y = y;
	velX = 0;
	velY = 0;
	width = 110;
	height = 120;
	this.objectsHandler = objectsHandler;
	action = false;
	visible = true;
	direction = 1;
	this.id = id;
	health = MAX_HEALTH;
	gravity = NORMAL_GRAVITY;
	jumpSound = new SoundsLoader("/jump.wav");
	powerUpSound = new SoundsLoader("/powerup.wav");
	coinSound = new SoundsLoader("/coin10.wav");
	hitSound = new SoundsLoader("/hit.wav");
	cactusShotSound = new SoundsLoader("/cactusShot.wav");
	springJumpSound = new SoundsLoader("/SpringJump.wav");
	jumpSound.setVolume(-15f);
	powerUpSound.setVolume(-20f);
	coinSound.setVolume(-15f);
	hitSound.setVolume(-15f);
	cactusShotSound.setVolume(-15f);
	springJumpSound.setVolume(-15f);
	
	playerRunRight = new Animation(3, tex.playerRunR[0], tex.playerRunR[1], tex.playerRunR[2], tex.playerRunR[3], tex.playerRunR[4], tex.playerRunR[5], tex.playerRunR[6], tex.playerRunR[7], tex.playerRunR[8], tex.playerRunR[9]);
	playerRunLeft = new Animation(3, tex.playerRunL[0], tex.playerRunL[1], tex.playerRunL[2], tex.playerRunL[3], tex.playerRunL[4], tex.playerRunL[5], tex.playerRunL[6], tex.playerRunL[7], tex.playerRunL[8], tex.playerRunL[9]);
	playerIdleRight = new Animation(3, tex.playerIdleR[0], tex.playerIdleR[1], tex.playerIdleR[2], tex.playerIdleR[3], tex.playerIdleR[4], tex.playerIdleR[5], tex.playerIdleR[6], tex.playerIdleR[7], tex.playerIdleR[8], tex.playerIdleR[9]);
	playerIdleLeft = new Animation(3, tex.playerIdleL[0], tex.playerIdleL[1], tex.playerIdleL[2], tex.playerIdleL[3], tex.playerIdleL[4], tex.playerIdleL[5], tex.playerIdleL[6], tex.playerIdleL[7], tex.playerIdleL[8], tex.playerIdleL[9]);
	playerJumpRight = new Animation(10, tex.playerJumpR[0], tex.playerJumpR[1], tex.playerJumpR[2], tex.playerJumpR[3], tex.playerJumpR[4], tex.playerJumpR[4], tex.playerJumpR[4]);
	playerJumpLeft = new Animation(10, tex.playerJumpL[0], tex.playerJumpL[1], tex.playerJumpL[2], tex.playerJumpL[3], tex.playerJumpL[4], tex.playerJumpL[4], tex.playerJumpL[4]);
	playerFallingRight = new Animation(10, tex.playerJumpR[3], tex.playerJumpR[3], tex.playerJumpR[3], tex.playerJumpR[3], tex.playerJumpR[4], tex.playerJumpR[4], tex.playerJumpR[4]);
	playerFallingLeft = new Animation(10, tex.playerJumpL[3], tex.playerJumpL[3], tex.playerJumpL[3], tex.playerJumpL[3], tex.playerJumpL[4], tex.playerJumpL[4], tex.playerJumpL[4]);
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
			
			jumpSound.play();
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
	if (y > 1150) health = 0;
	
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
	
	if (taco_powerUp)
	{
		gravity = 0.9f;
		
		if (taco_time > 0) taco_time--;
		else {
			taco_time = TACO_COOLDOWN;
			taco_powerUp = false;
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
					achievements.addCoin20Count();
					if (achievements.isCoinCount20Complete()) achievements.addCoin50Count();
					coinSound.play();
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
					powerUpSound.play();
					achievements.addPowerup3Count();
					tequila_powerUp = true;
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
					powerUpSound.play();
					achievements.addPowerup3Count();
					taco_powerUp = true;
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
				if (getWholeBounds().intersects(tempObject.getBounds()) && !hit_by_enemy)
				{
					if (tempObject.getX() > x) x -= 10;
					else x += 10;
					health--;
					MainScreen.SCORE -=40;
					hitSound.play();
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
				if (getWholeBounds().intersects(tempObject.getBounds()))
				{
					tempObject.setVelX(1);
					if (tempObject.getX() > x) tempObject.setDirection(-1);
					else tempObject.setDirection(1);
					
					if (!tempObject.isAction()) {
						objectsHandler.getDart_List().add(new Dart(ObjectId.Dart, (int) tempObject.getX(), (int) tempObject.getY(), tempObject.getDirection(), objectsHandler));
						cactusShotSound.play();
						tempObject.setAction(true);
					}
				} else tempObject.setVelX(0);
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
				if (getWholeBounds().intersects(tempObject.getBounds()) && !hit_by_enemy)
				{
					if (tempObject.getX() > x) x -= 25;
					else x += 25;
					health--;
					MainScreen.SCORE -=40;
					hitSound.play();
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
					if (velX == 0 && tempObject.isAction()) x += tempObject.getVelX();
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
					if (!hit_by_enemy && tempObject.isAction())
					{
						y -= 90;
						health--;
						MainScreen.SCORE -=40;
						hitSound.play();
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
				if (getWholeBounds().intersects(tempObject.getBounds()) && !hit_by_enemy)
				{
					if (tempObject.getX() > x) x -= 25;
					else x += 25;
					health--;
					MainScreen.SCORE -=40;
					hitSound.play();
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
						springJumpSound.play();
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
					
				if (getBoundsLeft().intersects(tempObject.getBounds())) x = tempObject.getX() + 55;	
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

public boolean isFinishLevel() {
	return finishLevel;
}

public void setFinishLevel(boolean finishLevel) {
	this.finishLevel = finishLevel;
}

@Override
public ObjectId getId() {
	return id;
}

@Override
public void setId(ObjectId id) {
	this.id = id;
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
}