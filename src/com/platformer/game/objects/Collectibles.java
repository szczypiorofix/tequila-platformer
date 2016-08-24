package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.ObjectsHandler;

public class Collectibles extends GameObject{



private ObjectsHandler objectsHandler;
private float x, y;
private float width, height;
private float velX, velY;
private boolean action, visible;
private int direction;
private int type;
private int show; // 0 - empty, 1 - show
private Random random;

	
public Collectibles(float x, float y, ObjectsHandler objectsHandler)
{
	super();
	this.x = x;
	this.y = y;
	this.objectsHandler = objectsHandler;
	velX = 0;
	velY = 0;
	width = 50;
	height = 50;
	action = false;
	visible = true;
	direction = 0;
	random = new Random();
	show = random.nextInt(2);

	type = random.nextInt(Textures.getInstance().collectible.length);
}
	


@Override
public void render(Graphics g) {
	g.drawImage(Textures.getInstance().collectible[type], (int) (x), (int) (y), null);
}

@Override
public void tick(LinkedList<GameObject> object) {
	if (show == 0) this.objectsHandler.getCollectibles_List().remove(this);
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