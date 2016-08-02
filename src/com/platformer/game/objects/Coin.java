package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Animation;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class Coin extends GameObject{

private Textures tex = MainScreen.getInstance();
private Animation coinRotating;
private float x, y;
private float width, height;
private float velX, velY;
private boolean action;
private int direction;
private ObjectId id;


public Coin(ObjectId id, float x, float y) {
	super();
	this.x = x;
	this.y = y;
	this.id = id;
	velX = 0;
	velY = 0;
	width = 32;
	height = 32;
	action = false;
	direction = 0;
	
	coinRotating = new Animation(5, tex.coinAnim[0], tex.coinAnim[1], tex.coinAnim[2], tex.coinAnim[3], tex.coinAnim[4], tex.coinAnim[5]
			, tex.coinAnim[6], tex.coinAnim[7], tex.coinAnim[8], tex.coinAnim[9], tex.coinAnim[10], tex.coinAnim[11], tex.coinAnim[12], tex.coinAnim[13]
			, tex.coinAnim[14], tex.coinAnim[15], tex.coinAnim[16], tex.coinAnim[17], tex.coinAnim[18], tex.coinAnim[19], tex.coinAnim[20]
			, tex.coinAnim[21], tex.coinAnim[22], tex.coinAnim[23], tex.coinAnim[24], tex.coinAnim[25], tex.coinAnim[26], tex.coinAnim[27], tex.coinAnim[28]
			, tex.coinAnim[29], tex.coinAnim[30], tex.coinAnim[31], tex.coinAnim[32], tex.coinAnim[33], tex.coinAnim[34], tex.coinAnim[35], tex.coinAnim[36]
			, tex.coinAnim[37], tex.coinAnim[38], tex.coinAnim[39], tex.coinAnim[40], tex.coinAnim[41], tex.coinAnim[42], tex.coinAnim[43], tex.coinAnim[44]
			, tex.coinAnim[45], tex.coinAnim[46], tex.coinAnim[47], tex.coinAnim[48], tex.coinAnim[49], tex.coinAnim[50], tex.coinAnim[51], tex.coinAnim[52]
			, tex.coinAnim[53], tex.coinAnim[54], tex.coinAnim[55], tex.coinAnim[56], tex.coinAnim[57], tex.coinAnim[58], tex.coinAnim[59]);
}

@Override
public void render(Graphics g) {	
	coinRotating.drawAnimation(g, (int) x, (int) y, false);
}

@Override
public void tick(LinkedList<GameObject> object) {
	coinRotating.runAnimation();
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