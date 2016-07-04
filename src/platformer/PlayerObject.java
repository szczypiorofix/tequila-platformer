package platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class PlayerObject extends GameObject{

private float width = 30, height = 30;
private ObjectsHandler objectsHandler;
protected boolean onGround;
protected boolean jumpAgain;
protected float speedX = 0, speedY = 0;
protected float gravity = 0.5f;


public PlayerObject(ObjectId id, float x, float y, ObjectsHandler objectsHandler) {
	super(id, x, y);
	this.objectsHandler = objectsHandler;
	onGround = false;
	jumpAgain = false;
}


@Override
public void tick(LinkedList<GameObject> object) {
	
	//collisions(object);
}


public void collisions(LinkedList<GameObject> object)
{
	for (int i = 0; i < objectsHandler.object.size(); i++)
	{
		GameObject tempObject = objectsHandler.object.get(i);
		if (tempObject.getId() == ObjectId.Block)
		{
			if (getBounds().intersects(tempObject.getBounds()))
			{
				if (y > (tempObject.getBounds().height +10)) {
					
					y = tempObject.getBounds().y - height;

					
				}
			}
		}
	}
}

@Override
public void render(Graphics g) {
	g.setColor(Color.RED);
	g.fillRect((int) x, (int) y, (int) width , (int)height );
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, (int) width, (int) height);
}

public float getWidth() {
	return width;
}

public void setWidth(float width) {
	this.width = width;
}

public float getHeight() {
	return height;
}

public void setHeight(float height) {
	this.height = height;
}

public float getSpeedX() {
	return speedX;
}

public void setSpeedX(float speedX) {
	this.speedX = speedX;
}

public float getSpeedY() {
	return speedY;
}


public void setSpeedY(float speedY) {
	this.speedY = speedY;
}

public float getGravity() {
	return gravity;
}

public void setGravity(float gravity) {
	this.gravity = gravity;
}

public boolean isOnGround() {
	return onGround;
}

public void setOnGround(boolean onGround) {
	this.onGround = onGround;
}

public boolean isJumpAgain() {
	return jumpAgain;
}

public void setJumpAgain(boolean jumpAgain) {
	this.jumpAgain = jumpAgain;
}

}