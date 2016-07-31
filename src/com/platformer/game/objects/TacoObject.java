package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class TacoObject extends GameObject{

private Textures tex = MainScreen.getInstance();
private static final int TACO_WIDTH = 43;
private static final int TACO_HEIGHT = 48;
private float x, y;

	
public TacoObject(ObjectId id, float x, float y) {
	super(id, x, y, TACO_WIDTH, TACO_HEIGHT, 0f, 0f, 0);
	this.x = x;
	this.y = y;
}

@Override
public void render(Graphics g) {
	g.drawImage(tex.tacoImage, (int) x, (int) y, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, TACO_WIDTH, TACO_HEIGHT);
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