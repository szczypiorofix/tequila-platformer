package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.ObjectsHandler;

public class Collectibles extends GameObject{


public static final int MAX_COLLECTIBLES = 7;
private ObjectsHandler objectsHandler;
private float x, y;
private float width, height;
private float velX, velY;
private boolean action, visible;
private int direction;
private int show; // 0 - empty, 1 - show
private Random random;

	
public Collectibles(float x, float y, ObjectsHandler objectsHandler)
{
	super();
	this.x = x;
	this.y = y;
	this.objectsHandler = objectsHandler;
	velY = 0;
	width = 50;
	height = 50;
	action = false;
	visible = true;
	direction = 0;
	random = new Random();
	
	show = random.nextInt(2); // 50% szansy na pojawianie siê kryszta³u
	
	int r = random.nextInt(28);
	
	if (r == 0) velX = 6;
	else if (r == 1 || r == 2) velX = 5;
	else if (r == 3 || r == 4 || r == 5) velX = 4;
	else if (r == 6 || r == 7 || r == 8 || r == 9) velX = 3;
	else if (r == 10 || r == 11 || r == 12 || r == 13 || r == 14) velX = 2;
	else if (r == 15 || r == 16 || r == 17 || r == 18 || r == 19 || r == 20) velX = 1;
	else if (r == 21 || r == 22 || r == 23 || r == 24 || r == 25 || r == 26 || r == 27) velX = 0;
}
	


@Override
public void render(Graphics g) {
	g.drawImage(Textures.getInstance().collectible[(int) velX], (int) (x), (int) (y), null);
}

@Override
public void tick(LinkedList<GameObject> object) {
	if (show != 0) this.objectsHandler.getCollectibles_List().remove(this);
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