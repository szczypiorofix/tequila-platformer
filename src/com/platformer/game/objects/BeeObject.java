package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Animation;
import com.platformer.game.graphics.Textures;


public class BeeObject extends GameObject{

private Animation beeMovingR, beeMovingL;
private int direction;
private int startPos = 0;
private float x, y;
private float velX;
private float velY;
private float width, height;
private boolean action, visible;


public BeeObject(float x, float y) {
	super();
	this.x = x;
	this.y = y;
	velX = 0;
	velY = 0;
	direction = 1;
	width = 32;
	height = 32;
	action = false;
	visible = true;
	startPos = (int) x;
	beeMovingR = new Animation(3, Textures.getInstance().beeR[0], Textures.getInstance().beeR[1], Textures.getInstance().beeR[2]
			, Textures.getInstance().beeR[3], Textures.getInstance().beeR[4]);
	beeMovingL = new Animation(3, Textures.getInstance().beeL[0], Textures.getInstance().beeL[1], Textures.getInstance().beeL[2]
			, Textures.getInstance().beeL[3], Textures.getInstance().beeL[4]);
}

@Override
public void render(Graphics g) {
	if (direction == 1) beeMovingR.drawAnimation(g, (int) x, (int) y-20, false);
	else beeMovingL.drawAnimation(g, (int) x, (int) y-20, false);
}

@Override
public void tick(LinkedList<GameObject> object) {
	
	if ((int) x > startPos+240) direction = -1;
	if ((int) x < startPos) direction = 1;
	
	if (direction == 1) x+= 1.3;
	if (direction == -1) x -= 1.3;
	
	beeMovingR.runAnimation();
	beeMovingL.runAnimation();
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y-8, (int) width, (int) height-15);
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
public float getVelX() {
	return velX;
}

@Override
public float getVelY() {
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