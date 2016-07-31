package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class WaterObject extends GameObject{

	
protected static final int WATER_BLOCK_WIDTH = 50;
protected static final int WATER_BLOCK_HEIGHT = 50;
private Textures tex = MainScreen.getInstance();
private int type;
private float x, y;

public WaterObject(ObjectId id, float x, float y, int type) {
	super(id, x, y, WATER_BLOCK_WIDTH, WATER_BLOCK_HEIGHT, 0, 0, 0);
	this.x = x;
	this.y = y;
	this.type = type;
}

@Override
public void render(Graphics g) {
	if (type == 0) g.drawImage(tex.water, (int)x, (int)y, WATER_BLOCK_WIDTH, WATER_BLOCK_HEIGHT, null);
	else g.drawImage(tex.waterDeep, (int)x, (int)y, WATER_BLOCK_WIDTH, WATER_BLOCK_HEIGHT, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, WATER_BLOCK_WIDTH, WATER_BLOCK_HEIGHT);
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
	return 0;
}

@Override
public float getVelY() {
	return 0;
}

@Override
public void setVelX(float velX) {	
}

@Override
public void setVelY(float velY) {	
}

@Override
public void setShooting(boolean shooting) {	
}

@Override
public boolean isShooting() {
	return false;
}
}