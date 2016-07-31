package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class SceneryObject extends GameObject{

private static final int SCENERY_WIDTH = 45;
private static final int SCENERY_HEIGHT = 45;
private Textures tex = MainScreen.getInstance();
private float x, y;
private int type;



public SceneryObject(ObjectId id, float x, float y, int type) {
	super(id, x, y, SCENERY_WIDTH, SCENERY_HEIGHT, 0f, 0f, 0);
	this.x = x;
	this.y = y;
	this.type = type;
}

@Override
public void render(Graphics g) {
	if (type == 16) g.drawImage(tex.sceneryObjects[0], (int)x-tex.sceneryObjects[0].getWidth() /2+30, (int)y-tex.sceneryObjects[0].getHeight()/2+8, null);
	if (type == 17) g.drawImage(tex.sceneryObjects[1], (int)x-tex.sceneryObjects[1].getWidth() /2+30, (int)y-tex.sceneryObjects[1].getHeight()/2+15, null);
	if (type == 18) g.drawImage(tex.sceneryObjects[2], (int)x-tex.sceneryObjects[2].getWidth() /2+30, (int)y-tex.sceneryObjects[2].getHeight()/2-5, null);
	if (type == 19) g.drawImage(tex.sceneryObjects[3], (int)x-tex.sceneryObjects[3].getWidth() /2+30, (int)y-tex.sceneryObjects[3].getHeight()/2+27, null);
	if (type == 20) g.drawImage(tex.sceneryObjects[4], (int)x-tex.sceneryObjects[4].getWidth() /2+30, (int)y-tex.sceneryObjects[4].getHeight()/2 +2, null);
	if (type == 21) g.drawImage(tex.sceneryObjects[5], (int)x-tex.sceneryObjects[5].getWidth() /2+27, (int)y-tex.sceneryObjects[5].getHeight()/2+26, null);
	if (type == 22) g.drawImage(tex.sceneryObjects[6], (int)x-tex.sceneryObjects[6].getWidth() /2+30, (int)y-tex.sceneryObjects[6].getHeight()/2+30, null);
	if (type == 23) g.drawImage(tex.sceneryObjects[7], (int)x-tex.sceneryObjects[7].getWidth() /2+30, (int)y-tex.sceneryObjects[7].getHeight()/2+5, null);
	if (type == 24) g.drawImage(tex.sceneryObjects[8], (int)x-tex.sceneryObjects[8].getWidth() /2+30, (int)y-tex.sceneryObjects[8].getHeight()/2+5, null);
	if (type == 25) g.drawImage(tex.sceneryObjects[9], (int)x-tex.sceneryObjects[9].getWidth() /2+30, (int)y-tex.sceneryObjects[9].getHeight()/2+27, null);
	if (type == 26) g.drawImage(tex.sceneryObjects[10], (int)x-tex.sceneryObjects[10].getWidth() /2+30, (int)y-tex.sceneryObjects[10].getHeight()/2+13, null);
	if (type == 27) g.drawImage(tex.sceneryObjects[11], (int)x-tex.sceneryObjects[11].getWidth() /2+30, (int)y-tex.sceneryObjects[11].getHeight()/2 -80, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, SCENERY_WIDTH, SCENERY_HEIGHT);
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