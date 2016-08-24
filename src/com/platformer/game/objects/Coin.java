package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Animation;
import com.platformer.game.graphics.Textures;

public class Coin extends GameObject{


private Animation coinRotating;
private float x, y;
private float width, height;
private float velX, velY;
private boolean action, visible;
private int direction;


public Coin(float x, float y) {
	super();
	this.x = x;
	this.y = y;
	velX = 0;
	velY = 0;
	width = 32;
	height = 32;
	action = false;
	visible = true;
	direction = 0;
	
	coinRotating = new Animation(5, Textures.getInstance().coinAnim[0], Textures.getInstance().coinAnim[1], Textures.getInstance().coinAnim[2], Textures.getInstance().coinAnim[3], Textures.getInstance().coinAnim[4], Textures.getInstance().coinAnim[5]
			, Textures.getInstance().coinAnim[6], Textures.getInstance().coinAnim[7], Textures.getInstance().coinAnim[8], Textures.getInstance().coinAnim[9], Textures.getInstance().coinAnim[10], Textures.getInstance().coinAnim[11], Textures.getInstance().coinAnim[12], Textures.getInstance().coinAnim[13]
			, Textures.getInstance().coinAnim[14], Textures.getInstance().coinAnim[15], Textures.getInstance().coinAnim[16], Textures.getInstance().coinAnim[17], Textures.getInstance().coinAnim[18], Textures.getInstance().coinAnim[19], Textures.getInstance().coinAnim[20]
			, Textures.getInstance().coinAnim[21], Textures.getInstance().coinAnim[22], Textures.getInstance().coinAnim[23], Textures.getInstance().coinAnim[24], Textures.getInstance().coinAnim[25], Textures.getInstance().coinAnim[26], Textures.getInstance().coinAnim[27], Textures.getInstance().coinAnim[28]
			, Textures.getInstance().coinAnim[29], Textures.getInstance().coinAnim[30], Textures.getInstance().coinAnim[31], Textures.getInstance().coinAnim[32], Textures.getInstance().coinAnim[33], Textures.getInstance().coinAnim[34], Textures.getInstance().coinAnim[35], Textures.getInstance().coinAnim[36]
			, Textures.getInstance().coinAnim[37], Textures.getInstance().coinAnim[38], Textures.getInstance().coinAnim[39], Textures.getInstance().coinAnim[40], Textures.getInstance().coinAnim[41], Textures.getInstance().coinAnim[42], Textures.getInstance().coinAnim[43], Textures.getInstance().coinAnim[44]
			, Textures.getInstance().coinAnim[45], Textures.getInstance().coinAnim[46], Textures.getInstance().coinAnim[47], Textures.getInstance().coinAnim[48], Textures.getInstance().coinAnim[49], Textures.getInstance().coinAnim[50], Textures.getInstance().coinAnim[51], Textures.getInstance().coinAnim[52]
			, Textures.getInstance().coinAnim[53], Textures.getInstance().coinAnim[54], Textures.getInstance().coinAnim[55], Textures.getInstance().coinAnim[56], Textures.getInstance().coinAnim[57], Textures.getInstance().coinAnim[58], Textures.getInstance().coinAnim[59]);
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