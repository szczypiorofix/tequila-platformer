package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;


public class Block extends GameObject{

private static final int BLOCK_WIDTH = 50;
private static final int BLOCK_HEIGHT = 50;
private Textures tex = MainScreen.getInstance();
private float x, y;
private int type;

public Block(ObjectId id, float x, float y, int type) {
	super(id, x, y, BLOCK_WIDTH, BLOCK_HEIGHT, 0f, 0f, 1);
	this.x = x;
	this.y = y;
	this.type = type;
}

@Override
public void render(Graphics g) {
	if (type == 0) g.drawImage(tex.blocks[0], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 1) g.drawImage(tex.blocks[1], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 2) g.drawImage(tex.blocks[2], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 3) g.drawImage(tex.blocks[3], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 4) g.drawImage(tex.blocks[4], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 5) g.drawImage(tex.blocks[5], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 6) g.drawImage(tex.blocks[6], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 7) g.drawImage(tex.blocks[7], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 8) g.drawImage(tex.blocks[8], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 9) g.drawImage(tex.blocks[9], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 10) g.drawImage(tex.blocks[10], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 11) g.drawImage(tex.blocks[11], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 12) g.drawImage(tex.blocks[12], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 13) g.drawImage(tex.blocks[13], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 14) g.drawImage(tex.blocks[14], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
	if (type == 15) g.drawImage(tex.blocks[15], (int)x, (int)y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, BLOCK_WIDTH, BLOCK_HEIGHT);
}

public static int getBlockkWidth()
{
	return BLOCK_WIDTH;
}

public static int getBlockHeight()
{
	return BLOCK_HEIGHT;
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