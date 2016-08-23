package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Animation;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainClass;

public class SpikeBlock extends GameObject{

private Textures tex = MainClass.getTexturesInstance();
private float x, y;
private float width, height;
private float velX, velY;
private int direction;
private boolean action, visible;
private Animation spikeAnimation;
private int animationTempo;


	
public SpikeBlock(float x, float y)
{
	super();
	this.x = x;
	this.y = y;
	width = 50;
	height = 70;
	velX = 0;
	velY = 0;
	direction = 1;
	action = false;
	visible = true;
	animationTempo = 10;
	spikeAnimation = new Animation(animationTempo, tex.spikeBlock1, tex.spikeBlock1, tex.spikeBlock1, tex.spikeBlock1, tex.spikeBlock1, tex.spikeBlock1, tex.spikeBlock2
			, tex.spikeBlock3, tex.spikeBlock4, tex.spikeBlock4, tex.spikeBlock4, tex.spikeBlock4, tex.spikeBlock4, tex.spikeBlock4, tex.spikeBlock3, tex.spikeBlock2);
}

@Override
public void render(Graphics g) {
	spikeAnimation.drawAnimation(g, (int) x, (int) y-20, false);
	//Graphics2D g2d = (Graphics2D) g;
	//g2d.draw(getBounds());
}

@Override
public void tick(LinkedList<GameObject> object) {
	spikeAnimation.runAnimation();

	if (spikeAnimation.getKlatkaAnimacji() > 8 && spikeAnimation.getKlatkaAnimacji() < 15) action = false;
	else action = true;
	
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, (int )width, (int) height-30);
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