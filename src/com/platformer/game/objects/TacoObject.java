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
	
public TacoObject(ObjectId id, float x, float y) {
	super(id, x, y, TACO_WIDTH, TACO_HEIGHT);
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
}