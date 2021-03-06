package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainClass;
import com.platformer.game.main.ObjectsHandler;

public class Dart extends GameObject{


private ObjectsHandler objectsHandler;

private float width ;
private float height;
private float x, y;
private int direction;
private float velX, velY;
private boolean action, visible;
private int sx;
private int howLondToRun = 85;



public Dart(float x, float y, int direction, ObjectsHandler objectsHandler) {
	super();
	this.x = x;
	this.y = y;
	this.objectsHandler = objectsHandler;

	velX = 0;
	velY = 0;
	sx = 0;
	width = 32;
	height = 12;
	action = false;
	visible = true;
	if (direction == 1) velX = 7;
	else velX = -7;
	this.direction = direction;
}


@Override
public void render(Graphics g) {
	
	if (direction == 1) g.drawImage(Textures.getInstance().dartR, (int) x, (int) y, null);
	else g.drawImage(Textures.getInstance().dartL, (int) x, (int) y, null);
}

@Override
public void tick(LinkedList<GameObject> object) {

	x += velX;
	if (direction == -1)
	{
		sx--;
		if (sx < (howLondToRun * -1)) velX = 0;	
	}
	if (direction == 1)
	{
		sx++;
		if (sx > howLondToRun) velX = 0;	
	}
	
	for (int i = 0; i < objectsHandler.getMovingCrate_List().size(); i++)
	{
		GameObject tempObject = objectsHandler.getMovingCrate_List().get(i);
		if (tempObject.isVisible() && getBounds().intersects(tempObject.getBounds()))
		{
			objectsHandler.getDart_List().remove(this);
			MainClass.crateHitSound.play();
		}
	}
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x+5, (int) y+2, (int) width-10, (int) height-4);
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
public float getX() {
	return x;
}

@Override
public float getY() {
	return y;
}

@Override
public float getVelX()
{
	return velX;
}

@Override
public float getVelY()
{
	return velY;
}

@Override
public void setVelX(float velX) {
	this.velX = velX;
}

@Override
public void setVelY(float velY) {
	this.velY = velY;
}

@Override
public void setX(float x) {
	this.x = x;
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
public boolean isAction() {
	return action;
}

@Override
public void setAction(boolean action) {
	this.action = action;
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