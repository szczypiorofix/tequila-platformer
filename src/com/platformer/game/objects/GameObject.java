package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.main.ObjectId;

public abstract class GameObject {

private float x,y;
private ObjectId id;
private float width, height;
private float velX, velY;
private int direction;
private boolean shooting;

/**
 * @param id ObjectId
 * @param x Object x
 * @param y Object y
 * @param width Object width
 * @param height Object height
 * @param velX Velocity X
 * @param velY Velocity Y
 * @param dir Direction (1 - right, -1 - left
 */
public GameObject(ObjectId id, float x, float y, float width, float height, float velX, float velY, int direction) {
	super();
	this.id = id;
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.velX = velX;
	this.velY = velY;
	this.direction = direction;
}

public abstract void render(Graphics g);
public abstract void tick(LinkedList<GameObject> object);
public abstract Rectangle getBounds();

public abstract float getX();
public abstract float getY();
public abstract float getVelX();
public abstract float getVelY();
public abstract void setVelX(float velX);
public abstract void setVelY(float velY);


public void setX(float x) {
	this.x = x;
}

public void setY(float y) {
	this.y = y;
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

public ObjectId getId() {
	return id;
}

public void setId(ObjectId id) {
	this.id = id;
}

public int getDirection()
{
	return direction;
}

public void setDirection(int direction)
{
	this.direction = direction;
}

public boolean isShooting() {
	return shooting;
}

public void setShooting(boolean shooting) {
	this.shooting = shooting;
}
}