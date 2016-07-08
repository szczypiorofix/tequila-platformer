package platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class PlayerObject extends GameObject{

	
private static float playerWidth = 35, playerHeight = 60;
private ObjectsHandler objectsHandler;
private final float MAX_SPEED = 10f;
protected float velX = 0, velY = 0;
protected float gravity = 0.5f;
protected boolean onGround = false;
protected boolean jumping = true;
private SoundsLoader sounds;
Textures tex = MainScreen.getInstance();
private float level1X = 0f, level1Y = 0f;
private float level2X = 0f, level2Y = 0f;
private int turn = 1;

private Animation playerWalkRight, playerWalkLeft;


public PlayerObject(ObjectId id, float x, float y, ObjectsHandler objectsHandler) {
	super(id, x, y, playerWidth, playerHeight);
	this.objectsHandler = objectsHandler;
	sounds = new SoundsLoader();
	level1X = x;
	level1Y = y;
	level2X = x;
	level2Y = y;
	playerWalkRight = new Animation(3, tex.player[0], tex.player[1], tex.player[2]);
	playerWalkLeft = new Animation(3, tex.player[3], tex.player[4], tex.player[5]);
}


@Override
public void tick(LinkedList<GameObject> object) {
		
	velX = 0;
	
	level1X = x /4;   // PARALLAX LEVEL 1 !!!
	level1Y = 0f;
	level2X = x / 2;   // PARALLAX LEVEL 2 !!!
	level2Y = 0f;
	
	if (MainScreen.KEY_LEFT) {
		velX = -5;
		turn = -1;
	}
	
	if (MainScreen.KEY_RIGHT) {
		velX = 5;
		turn = 1;
	}
		
	if ((MainScreen.KEY_CTRL) || (MainScreen.KEY_SHIFT)) 
		if (MainScreen.KEY_CTRL) gravity = 0.1f;
		else gravity = 0.9f;
	else gravity = 0.5f;
	
	if ((MainScreen.KEY_UP) && (!jumping) && (onGround)) {
		
		sounds.playJumpSound();
		velY = -10;
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
					
					y = tempObject.getY() + Block.brickHeight;
					velY = 0;	
				}
			}
			
			if (getBounds().intersects(tempObject.getBounds()))
			{
				y = tempObject.getY() - Block.brickHeight - 24;
				jumping = false;
				velY = 0;
				onGround = true;
			}
			
			if (getBoundsRight().intersects(tempObject.getBounds()))
			{
					x = tempObject.getX() - width;
			}
			
			// LEFT
			if (getBoundsLeft().intersects(tempObject.getBounds()))
			{					
					x = tempObject.getX() + width-4;
			}
			
		}
	}
}


public void render(Graphics g) {
	g.setColor(Color.BLUE);
	
	if (velX > 0) playerWalkRight.drawAnimation(g, (int) x, (int) y-5, (int) playerWidth+10, (int) playerHeight);
	else if (velX < 0) playerWalkLeft.drawAnimation(g, (int) x, (int) y-5, (int) playerWidth+10, (int) playerHeight);
	else {
		if (turn == 1) g.drawImage(tex.player[1], (int) x, (int) y-5, (int) playerWidth+10, (int) playerHeight, null);
		else g.drawImage(tex.player[4], (int) x, (int) y-5, (int) playerWidth+10, (int) playerHeight, null);
	}
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
	return new Rectangle((int) ((int) x + (playerWidth/2) - (playerWidth /2)/2), (int) ((int) y + (playerHeight / 2)), (int) playerWidth / 2, (int) playerHeight /2);
}

public Rectangle getBoundsTop()
{
	return new Rectangle((int) ((int) x + (playerWidth /2) - (playerWidth/2)/2), (int)y, (int) playerWidth / 2, (int) playerHeight /2);
}

public Rectangle getBoundsRight()
{
	return new Rectangle((int) ((int) x+playerWidth-10), (int)y+10, (int) 10, (int) playerHeight -20);
}

public Rectangle getBoundsLeft()
{
	return new Rectangle((int) x, (int)y+10, (int) 10, (int) playerHeight-20);
}
}