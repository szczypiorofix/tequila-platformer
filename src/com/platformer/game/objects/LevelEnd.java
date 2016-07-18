package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class LevelEnd extends GameObject {

Textures tex = MainScreen.getInstance();
	
public LevelEnd(ObjectId id, float x, float y) {
	super(id, x, y, 45, 45);
}

@Override
public void render(Graphics g) {	
	g.drawImage(tex.levelend, (int)x-tex.levelend.getWidth() /2 +23, (int)y-tex.levelend.getHeight()/2+18, null);
}

@Override
public void tick(ArrayList<GameObject> object) {	
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, 45, 45);
}
}