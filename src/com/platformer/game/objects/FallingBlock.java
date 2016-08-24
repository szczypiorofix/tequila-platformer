package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.ObjectsHandler;

public class FallingBlock extends GameObject{


	
private float x, y;
private float velX, velY;
private float width, height;
private int direction;
private int startPos;
private boolean action, visible;
private ObjectsHandler objectsHandler;

	
	
public FallingBlock(float x, float y, ObjectsHandler objectsHandler)
{
	super();
	this.x = x;
	this.y = y;
	this.objectsHandler = objectsHandler;
	width = 70;
	height = 30;
	velX = 0;
	velY = 0;
	startPos = (int) y;
	direction = 1;
	action = false;
	visible = true;
}

	
@Override
public void render(Graphics g) {
	g.drawImage(Textures.getInstance().fallingBlock, (int) x, (int) y, null);
	//Graphics2D g2d = (Graphics2D) g;
	//g2d.draw(getBounds());
}

@Override
public void tick(LinkedList<GameObject> object) {

	if (action)	{
		velY = 2f;
		/// BLOCK LIST
		for (int i = 0; i < objectsHandler.getBlock_List().size(); i++)
		{
			GameObject tempObject = objectsHandler.getBlock_List().get(i);
			if (tempObject.isVisible())
			{
				if (getBounds().intersects(tempObject.getBounds()))
				{
					if (y > (tempObject.getBounds().y - tempObject.getHeight())) {
							
						y = tempObject.getY() - height+2;
						velY = 0;	
					}
				}
			}
		}
	}
	else 
		{
			if (y > startPos) velY = -0.75f;
			else velY = 0f;
		}

	y += velY;
	
	action = false;
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, (int) width, (int) height);
}


@Override
public float getX() {
	return x;
}

@Override
public void setX(float x) {
	this.x = x;
}

@Override
public float getY() {
	return y;
}

@Override
public void setY(float y) {
	this.y = y;
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
public boolean isVisible() {
	return visible;
}


@Override
public void setVisible(boolean visible) {
	this.visible = visible;
}
}