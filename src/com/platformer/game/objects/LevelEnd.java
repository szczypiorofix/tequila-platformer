package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class LevelEnd extends GameObject {

private Textures tex = MainScreen.getInstance();
private float x, y;


public LevelEnd(ObjectId id, float x, float y) {
	super(id, x, y, 45, 45, 0f, 0f, 0);
	this.x = x;
	this.y = y;
}

@Override
public void render(Graphics g) {	
	g.drawImage(tex.levelend, (int)x-tex.levelend.getWidth() /2 +23, (int)y-tex.levelend.getHeight()/2+18, null);
}

@Override
public void tick(LinkedList<GameObject> object) {	
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, 45, 45);
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
}