package platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class PlayerObject extends GameObject{

private float width = 30, height = 30;
protected final float MAX_SPEED_X = 5;
protected final float MAX_SPEED_Y = 5;
private final int MAX_SPEED = 20;
private ObjectsHandler objectsHandler;
private boolean jump;
	
	
	
public PlayerObject(ObjectId id, float x, float y, ObjectsHandler objectsHandler) {
	super(id, x, y);
	this.objectsHandler = objectsHandler;
	falling = true;
	jumping = false;
	jump = false;
}



@Override
public String toString() {
	return "PlayerObject [width=" + width + ", height=" + height + ", x=" + x + ", y=" + y + ", id=" + id + ", falling="
			+ falling + ", jumping=" + jumping + ", gravity=" + gravity + ", speedX=" + speedX + ", speedY=" + speedY
			+ "]";
}

@Override
public void tick(LinkedList<GameObject> object) {
	
	x += speedX;
	y += speedY;
	
	if (falling)
	{
		jumping = false;
		speedY += gravity;
		if (speedY > MAX_SPEED) speedY = MAX_SPEED;
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
			if (getBounds().intersects(tempObject.getBounds()))
			{
				if (y > (tempObject.getBounds().height)) {
					
					y = tempObject.getBounds().y - height;
					speedY = 0;
					jumping = false;
					falling = false;
					jump = false;
				}
			}
			else falling = true;
		}
	}
}

@Override
public void render(Graphics g) {
	g.setColor(Color.RED);
	g.fillRect((int) x, (int) y, (int) width , (int)height );
	g.drawString("X:"+x +" Y:"+y, MainClass.WIDTH - 100, 40);
	g.drawString("speedX:"+speedX, MainClass.WIDTH - 100, 60);
	g.drawString("speedY:"+speedY, MainClass.WIDTH - 100, 80);
	g.drawString("Falling: "+falling, MainClass.WIDTH - 100, 100);
	g.drawString("Jumping: "+jumping, MainClass.WIDTH - 100, 120);
	g.drawString("Jump: "+jump, MainClass.WIDTH - 100, 140);
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, (int) width, (int) height);
}

public void setJump(boolean b)
{
	jump = b;
}

public boolean getJump()
{
	return jump;
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

public float getMAX_SPEED_X() {
	return MAX_SPEED_X;
}

public float getMAX_SPEED_Y() {
	return MAX_SPEED_Y;
}

public int getMAX_SPEED() {
	return MAX_SPEED;
}
}