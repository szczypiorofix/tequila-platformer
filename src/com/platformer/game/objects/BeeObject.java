package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Animation;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class BeeObject extends GameObject{

private Textures tex = MainScreen.getInstance();
private Animation beeMovingR, beeMovingL;
private int moving = 1;
private int startPos = 0;
private float x, y;
private float velX = 0;
private float velY = 0;
private static final int BEE_WIDTH = 32, BEE_HEIGHT = 32;

public BeeObject(ObjectId id, float x, float y) {
	super(id, x, y, BEE_WIDTH, BEE_HEIGHT, 0, 0, 1);
	this.x = x;
	this.y = y;
	startPos = (int) x;
	beeMovingR = new Animation(3, tex.beeR[0], tex.beeR[1], tex.beeR[2], tex.beeR[3], tex.beeR[4]);
	beeMovingL = new Animation(3, tex.beeL[0], tex.beeL[1], tex.beeL[2], tex.beeL[3], tex.beeL[4]);
}

@Override
public void render(Graphics g) {
	if (moving == 1) beeMovingR.drawAnimation(g, (int) x, (int) y-20, false);
	else beeMovingL.drawAnimation(g, (int) x, (int) y-20, false);
}

@Override
public void tick(LinkedList<GameObject> object) {
	
	if ((int) x > startPos+240) moving = -1;
	if ((int) x < startPos) moving = 1;
	
	if (moving == 1) x+= 1.3;
	if (moving == -1) x -= 1.3;
	
	beeMovingR.runAnimation();
	beeMovingL.runAnimation();
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x-3, (int) y-18, BEE_WIDTH+10, BEE_HEIGHT);
}

@Override
public float getX()
{
	return x;
}

@Override
public float getY()
{
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
}