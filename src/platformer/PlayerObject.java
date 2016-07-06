package platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class PlayerObject extends GameObject{

	
private float width = 30, height = 50;
private ObjectsHandler objectsHandler;
private final float MAX_SPEED = 10f;
protected float velX = 0, velY = 0;
protected float gravity = 0.4f;
protected boolean falling = true;
protected boolean jumping = true;
private SoundsLoader sounds;


public PlayerObject(ObjectId id, float x, float y, ObjectsHandler objectsHandler) {
	super(id, x, y);
	this.objectsHandler = objectsHandler;
	sounds = new SoundsLoader();
}


@Override
public void tick(LinkedList<GameObject> object) {
	
	if ((MainScreen.KEY_LEFT)) velX -= 0.9f;
	
	if (MainScreen.KEY_RIGHT) velX += 0.9f;
	
	if ((MainScreen.KEY_CTRL) || (MainScreen.KEY_SHIFT)) 
		if (MainScreen.KEY_CTRL) gravity = 0.1f;
		else gravity = 0.9f;
	else gravity = 0.4f;
	
	if ((MainScreen.KEY_UP) && (!jumping)) {
		
		sounds.playJumpSound();
		velY = -10;
		jumping = true;
	}
	
	x += velX;
	y += velY;
	
	velX *= 0.8f;
	
	if ((velX < 0.1f) && (velX > -0.1f)) velX = 0.0f;
	
	if (falling || jumping)
	{
		velY += gravity;
		if (velY > MAX_SPEED) velY = MAX_SPEED;
	}
	
	collisions(object);
}


public void collisions(LinkedList<GameObject> object)
{
	for (int i = 0; i < objectsHandler.object.size(); i++)
	{
		GameObject tempObject = objectsHandler.object.get(i);
		
		if (tempObject.getId() == ObjectId.Block)
		{
			// TOP
			if (getBoundsTop().intersects(tempObject.getBounds()))
			{
				if (y > (tempObject.getBounds().y - height)) {
					
					y = tempObject.getY() + Block.brickHeight;
					velY = 0;	
				}
			}
			
			if (getBounds().intersects(tempObject.getBounds()))
			{
				y = tempObject.getY() - height;
				velY = 0;
				jumping = false;
				falling = false;
			}
			else {
				falling = true;
			}
			
			// RIGHT
			if (getBoundsRight().intersects(tempObject.getBounds()))
			{
					x = tempObject.getX() - width;
			}
			
			// LEFT
			if (getBoundsLeft().intersects(tempObject.getBounds()))
			{					
					x = tempObject.getX() + width;
			}
		}
	}
}


public void render(Graphics g) {
	g.setColor(Color.BLUE);
	g.fillRect((int) x, (int) y, (int) width , (int)height );
}


/// COLLISION BOUNDS !!!

@Override
public Rectangle getBounds() {
	return new Rectangle((int) ((int) x + (width/2) - (width /2)/2), (int) ((int) y + (height / 2)), (int) width / 2, (int) height /2);
}

public Rectangle getBoundsTop()
{
	return new Rectangle((int) ((int) x + (width /2) - (width/2)/2), (int)y, (int) width / 2, (int) height /2);
}

public Rectangle getBoundsRight()
{
	return new Rectangle((int) ((int) x+width-5), (int)y+5, (int) 5, (int) height -10);
}

public Rectangle getBoundsLeft()
{
	return new Rectangle((int) x, (int)y+5, (int) 5, (int) height-10);
}
}