package platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

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
private int health = 100;

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
public void tick(LinkedList<GameObject> object) {
		
	velX = 0;
	
	level1X = x /4;   // PARALLAX LEVEL 1 !!!
	level1Y = 0f;
	level2X = x / 2;   // PARALLAX LEVEL 2 !!!
	level2Y = 0f;
	
	if ((MainScreen.KEY_LEFT) || (MainScreen.GAMEPAD_LEFT)) {
		velX = -5;
		turn = -1;
		//sounds.playFootStepSound();
	}
	
	if ((MainScreen.KEY_RIGHT) || (MainScreen.GAMEPAD_RIGHT)) {
		velX = 5;
		turn = 1;
		//sounds.playFootStepSound();
	}
		
	if ((MainScreen.KEY_CTRL) || (MainScreen.KEY_SHIFT)) 
		if (MainScreen.KEY_CTRL) gravity = 0.1f;
		else gravity = 0.9f;
	else gravity = 0.5f;
	
	if (((MainScreen.KEY_UP || (MainScreen.GAMEPAD_UP))) && (!jumping) && (onGround)) {
		
		
		sounds.playJumpSound();
		velY = -12;
		jumping = true;
	}
		
	if (!onGround) velY += gravity;
	if (velY > MAX_SPEED) velY = MAX_SPEED;

	x += velX;
	y += velY;
	
	onGround = false;
	
	collisions(object);
	
	playerWalkRight.runAnimation();
	playerWalkLeft.runAnimation();
	playerIdleRight.runAnimation();
	playerIdleLeft.runAnimation();
	playerJumpRight.runAnimation();
	playerJumpLeft.runAnimation();
}


public void collisions(LinkedList<GameObject> object)
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
				
				if (velY == MAX_SPEED) health -= 10;
				
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
				objectsHandler.switchLevel();
			}
		}
		else if (tempObject.getId() == ObjectId.Coin)
		{
			if (getWholeBounds().intersects(tempObject.getBounds()))
			{
				objectsHandler.removeObject(tempObject);
				MainScreen.COINS++;
				sounds.playCoinSound();
			}
		}
	}
}


public void render(Graphics g) {
	
	//Graphics2D g2d = (Graphics2D) g;
	//g2d.setColor(Color.BLUE);
	
	
	if ((velX > 0) && (!jumping) && (onGround)) playerWalkRight.drawAnimation(g, (int) x, (int) y+6);
	if ((velX < 0) && (!jumping) && (onGround)) playerWalkLeft.drawAnimation(g, (int) x, (int) y+6);
		
	if (turn == 1 && !jumping && onGround && velX == 0) playerIdleRight.drawAnimation(g, (int) x, (int) y+6);
	if (turn == -1 && !jumping && onGround && velX == 0) playerIdleLeft.drawAnimation(g, (int) x, (int) y+6);
		
	if ((jumping) && (turn == 1)) playerJumpRight.drawAnimation(g, (int) x, ( int )y+6);
	if ((jumping) && (turn == -1)) playerJumpLeft.drawAnimation(g, (int) x, ( int )y+6);
	
	if (!onGround && !jumping && turn == 1) g.drawImage(tex.playerJumpR[1], (int) x, (int) y, null);
	if (!onGround && !jumping && turn == -1) g.drawImage(tex.playerJumpL[1], (int) x, (int) y, null);
	
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
}