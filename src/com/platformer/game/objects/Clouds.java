package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.Camera;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class Clouds extends GameObject{

private Textures tex = MainScreen.getInstance();
private float x, y;
private float velX, velY;
private float width, height;
private ObjectId id;
private boolean action;
private int direction;
private int type;
private Camera cam;
private float originX;


public Clouds(ObjectId id, float x, float y, Camera cam, int type) {
	super();
	this.x = x;
	this.y = y;
	this.id = id;
	originX = this.x;
	this.type = type;
	this.cam = cam;
	width = 50;
	height = 50;
	action = false;
	direction = 1;
	velX = 0;
	velY = 0;
}
	
@Override
public void render(Graphics g) {
	if (type == 1) g.drawImage(tex.clouds1, (int) x, (int) y, null);
	if (type == 2) g.drawImage(tex.clouds2, (int) x, (int) y, null);
	if (type == 3) g.drawImage(tex.clouds3, (int) x, (int) y, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
	
	velX -= 0.2f;
	x = -cam.getX()+velX+originX;
	
	if (x < (-cam.getX()-140))
	{
		originX += 1500;
		//System.out.println("OK");
	}
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, (int) width, (int) height);
}

@Override
public ObjectId getId() {
	return id;
}

@Override
public void setId(ObjectId id) {
	this.id = id;
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
}