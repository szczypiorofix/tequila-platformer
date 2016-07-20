package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import com.platformer.game.graphics.Animation;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;
import com.platformer.game.main.ObjectsHandler;
import com.platformer.game.sounds.SoundsLoader;

public class PlayerObject extends GameObject{

	
private static float playerWidth = 110, playerHeight = 120;
private ObjectsHandler objectsHandler;
private final float MAX_SPEED = 20f;
protected float velX = 0, velY = 0;
protected float gravity = 0.5f;
protected boolean onGround = false;
protected boolean jumping = false;
private SoundsLoader sounds;
Textures tex = MainScreen.getInstance();
private float level1X = 0f, level1Y = 0f;
private float level2X = 0f, level2Y = 0f;
private int turn = 1;
private int health = 5;
private final int HIT_COOLDOWN = 60*2;
private int hit_time = HIT_COOLDOWN;
private boolean hit_by_enemy = false;
private final int TEQUILA_COOLDOWN = 60*5;
private int tequila_time = TEQUILA_COOLDOWN;
private boolean tequila_powerUp = false;
private final int TACO_COOLDOWN = 60*10;
private int taco_time = TEQUILA_COOLDOWN;
private boolean taco_powerUp = false;
private final int FINISH_LEVEL_COOLDOWN = 400;
private int finish_level_time = FINISH_LEVEL_COOLDOWN;
private boolean finishLevel = false;

private Animation playerWalkRight, playerWalkLeft, playerIdleRight, playerIdleLeft, playerJumpRight, playerJumpLeft;


public PlayerObject(ObjectId id, float x, float y, ObjectsHandler objectsHandler) {
	super(id, x, y, playerWidth, playerHeight);
	this.objectsHandler = objectsHandler;
	sounds = new SoundsLoader();
	level1X = x;
	level1Y = y;
	level2X = x;
	level2Y = y;
	
	playerWalkRight = new Animation(3, tex.playerRunR[0], tex.playerRunR[1], tex.playerRunR[2], tex.playerRunR[3], tex.playerRunR[4], tex.playerRunR[5], tex.playerRunR[6], tex.playerRunR[7], tex.playerRunR[8], tex.playerRunR[9]);
	playerWalkLeft = new Animation(3, tex.playerRunL[0], tex.playerRunL[1], tex.playerRunL[2], tex.playerRunL[3], tex.playerRunL[4], tex.playerRunL[5], tex.playerRunL[6], tex.playerRunL[7], tex.playerRunL[8], tex.playerRunL[9]);
	playerIdleRight = new Animation(3, tex.playerIdleR[0], tex.playerIdleR[1], tex.playerIdleR[2], tex.playerIdleR[3], tex.playerIdleR[4], tex.playerIdleR[5], tex.playerIdleR[6], tex.playerIdleR[7], tex.playerIdleR[8], tex.playerIdleR[9]);
	playerIdleLeft = new Animation(3, tex.playerIdleL[0], tex.playerIdleL[1], tex.playerIdleL[2], tex.playerIdleL[3], tex.playerIdleL[4], tex.playerIdleL[5], tex.playerIdleL[6], tex.playerIdleL[7], tex.playerIdleL[8], tex.playerIdleL[9]);
	playerJumpRight = new Animation(10, tex.playerJumpR[0], tex.playerJumpR[1], tex.playerJumpR[2], tex.playerJumpR[3], tex.playerJumpR[4], tex.playerJumpR[4], tex.playerJumpR[4]);
	playerJumpLeft = new Animation(10, tex.playerJumpL[0], tex.playerJumpL[1], tex.playerJumpL[2], tex.playerJumpL[3], tex.playerJumpL[4], tex.playerJumpL[4], tex.playerJumpL[4]);
}


@Override
public void tick(ArrayList<GameObject> object) {
		
	velX = 0;
	
	level1X = x /4;   // PARALLAX LEVEL 1 !!!
	level1Y = 0f;
	level2X = x / 2;   // PARALLAX LEVEL 2 !!!
	level2Y = 0f;
	
	if (!finishLevel)
	{
		if ((MainScreen.KEY_LEFT) || (MainScreen.GAMEPAD_LEFT)) {
			velX = -5;
			turn = -1;
		}
		
		if ((MainScreen.KEY_RIGHT) || (MainScreen.GAMEPAD_RIGHT)) {
			velX = 5;
			turn = 1;
		}
		
		if (((MainScreen.KEY_UP || (MainScreen.GAMEPAD_UP))) && (!jumping) && (onGround)) {
			
			
			sounds.playJumpSound();
			velY = -12;
			jumping = true;
		}
	}
	
		
	if (!onGround) velY += gravity;
	if (velY > MAX_SPEED) velY = MAX_SPEED;

	x += velX;
	y += velY;
	
	onGround = false;
	
	gravity = 0.5f;
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
	
	if (finishLevel)
	{
		if (finish_level_time > 0) finish_level_time--;
		else {
			finish_level_time = FINISH_LEVEL_COOLDOWN;
			finishLevel = false;
			MainScreen.milis = 0f;
			MainScreen.minutes = 0;
			MainScreen.seconds = 0;
			MainScreen.COINS = 0;
			MainScreen.SCORE = 0;
			objectsHandler.switchLevel();
		}
	}
	
	collisions(object);
	
	playerWalkRight.runAnimation();
	playerWalkLeft.runAnimation();
	playerIdleRight.runAnimation();
	playerIdleLeft.runAnimation();
	playerJumpRight.runAnimation();
	playerJumpLeft.runAnimation();
	
	//f_playerWalkRight.runAnimation();
	//f_playerWalkLeft.runAnimation();
	//f_playerIdleRight.runAnimation();
	//f_playerIdleLeft.runAnimation();
	//f_playerJumpRight.runAnimation();
	//f_playerJumpLeft.runAnimation();
}


public void collisions(ArrayList<GameObject> object)
{
	for (int i = 0; i < objectsHandler.object.size(); i++)
	{
		GameObject tempObject = objectsHandler.object.get(i);
		
		if (tempObject.getId() == ObjectId.Block)
		{		
			
			if (getBoundsTop().intersects(tempObject.getBounds()))
			{
				if (y > (tempObject.getBounds().y - height)) {
					
					y = tempObject.getY() + Block.brickHeight-10;
					velY = 0;	
				}
			}
			
			if (getBounds().intersects(tempObject.getBounds()))
			{
				
				//if (velY == MAX_SPEED) health--;   // FALL DAMAGE
				
				y = tempObject.getY() - Block.brickHeight - 53;
				jumping = false;
				velY = 0;
				onGround = true;
			}
			
			if (getBoundsRight().intersects(tempObject.getBounds()))
			{
				x = tempObject.getX() - width+32;
			}
			
			if (getBoundsLeft().intersects(tempObject.getBounds()))
			{					
				x = tempObject.getX() + width-55;
			}
		}
		else if (tempObject.getId() == ObjectId.LevelEnd)
		{
			if (getBounds().intersects(tempObject.getBounds()))
			{
				finishLevel = true;
			}
		}
		else if (tempObject.getId() == ObjectId.Coin)
		{
			if (getWholeBounds().intersects(tempObject.getBounds()))
			{
				objectsHandler.removeObject(tempObject);
				MainScreen.COINS++;
				MainScreen.SCORE += 10;
				sounds.playCoinSound();
			}
		}
		else if (tempObject.getId() == ObjectId.BeeEnemy)
		{
			if (getWholeBounds().intersects(tempObject.getBounds()) && !hit_by_enemy)
			{
				if (tempObject.getX() > x) x -= 80;
				else x += 80;
				health--;
				MainScreen.SCORE -=20;
				sounds.playHitSound();
				hit_by_enemy = true;
			}
		}
		else if (tempObject.getId() == ObjectId.Tequila)
		{
			if (getWholeBounds().intersects(tempObject.getBounds()) && (!isTequila_powerUp()))
			{
				objectsHandler.removeObject(tempObject);
				MainScreen.SCORE += 15;
				sounds.playDrinkSound();
				sounds.playPowerUpSound();
				tequila_powerUp = true;
				taco_powerUp = false;
			}
		}
		else if (tempObject.getId() == ObjectId.Taco)
		{
			if (getWholeBounds().intersects(tempObject.getBounds()) && (health < 5))
			{
				objectsHandler.removeObject(tempObject);
				MainScreen.SCORE += 15;
				sounds.playPowerUpSound();
				taco_powerUp = true;
				tequila_powerUp = false;
				health++;
			}
		}
	}
}


public void render(Graphics g) {
	
	//Graphics2D g2d = (Graphics2D) g;
	//g2d.setColor(Color.BLUE);
	
	if (!hit_by_enemy)
	{
		if ((velX > 0) && (!jumping) && (onGround)) playerWalkRight.drawAnimation(g, (int) x, (int) y+6);
		if ((velX < 0) && (!jumping) && (onGround)) playerWalkLeft.drawAnimation(g, (int) x, (int) y+6);
			
		if (turn == 1 && !jumping && onGround && velX == 0) playerIdleRight.drawAnimation(g, (int) x, (int) y+6);
		if (turn == -1 && !jumping && onGround && velX == 0) playerIdleLeft.drawAnimation(g, (int) x, (int) y+6);
			
		if ((jumping) && (turn == 1)) playerJumpRight.drawAnimation(g, (int) x, ( int )y+6);
		if ((jumping) && (turn == -1)) playerJumpLeft.drawAnimation(g, (int) x, ( int )y+6);
		
		if (!onGround && !jumping && turn == 1) g.drawImage(tex.playerJumpR[1], (int) x, (int) y, null);
		if (!onGround && !jumping && turn == -1) g.drawImage(tex.playerJumpL[1], (int) x, (int) y, null);
	
	}
	else {

		if ((velX > 0) && (!jumping) && (onGround)) playerWalkRight.drawAnimation(g, (int) x, (int) y+6);
		if ((velX < 0) && (!jumping) && (onGround)) playerWalkLeft.drawAnimation(g, (int) x, (int) y+6);
			
		if (turn == 1 && !jumping && onGround && velX == 0) playerIdleRight.drawAnimation(g, (int) x, (int) y+6);
		if (turn == -1 && !jumping && onGround && velX == 0) playerIdleLeft.drawAnimation(g, (int) x, (int) y+6);
			
		if ((jumping) && (turn == 1)) playerJumpRight.drawAnimation(g, (int) x, ( int )y+6);
		if ((jumping) && (turn == -1)) playerJumpLeft.drawAnimation(g, (int) x, ( int )y+6);
		
		if (!onGround && !jumping && turn == 1) g.drawImage(tex.playerJumpR[1], (int) x, (int) y, null);
		if (!onGround && !jumping && turn == -1) g.drawImage(tex.playerJumpL[1], (int) x, (int) y, null);
		
	}
	//g2d.draw(getBounds());
	//g2d.draw(getBoundsTop());
	//g2d.draw(getBoundsLeft());
	//g2d.draw(getBoundsRight());
	//g2d.draw(getWholeBounds());
}

// PARALLAX !
public float getLevel1X()
{
	return level1X;
}

public float getLevel1Y()
{
	return level1Y;
}

public float getLevel2X()
{
	return level2X;
}

public float getLevel2Y()
{
	return level2Y;
}


/// COLLISION BOUNDS !!!

@Override
public Rectangle getBounds() {
	return new Rectangle((int) ((int) x + (playerWidth/2) - (playerWidth /2)/2)-18, (int) ((int) y + (playerHeight / 2))+35, (int) playerWidth / 2 - 5, (int) 10);
}

public Rectangle getBoundsTop()
{
	return new Rectangle((int) ((int) x + (playerWidth /2) - (playerWidth/2)/2)-20, (int) y +10, (int) playerWidth / 2, (int) playerHeight /2-50);
}

public Rectangle getBoundsRight()
{
	return new Rectangle((int) ((int) x+playerWidth-25)-20, (int)y+20, 10, (int) playerHeight -45);
}

public Rectangle getBoundsLeft()
{
	return new Rectangle((int) x-5, (int)y+20, 10, (int) playerHeight -45);
}

public Rectangle getWholeBounds()
{
	return new Rectangle((int) x, (int) y+10, (int) playerWidth-45, (int) playerHeight-20);
}

//OTHER

public int getHealth() {
	return health;
}

public void setHealth(int health) {
	this.health = health;
}

public float getVelX() {
	return velX;
}

public void setVelX(float velX) {
	this.velX = velX;
}

public float getVelY() {
	return velY;
}

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

}