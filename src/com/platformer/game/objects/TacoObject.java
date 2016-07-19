package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class TacoObject extends GameObject{

Textures tex = MainScreen.getInstance();
	
public TacoObject(ObjectId id, float x, float y) {
	super(id, x, y, 43, 48);
}

@Override
public void render(Graphics g) {
	g.drawImage(tex.tacoImage, (int) x, (int) y, null);
}

@Override
public void tick(ArrayList<GameObject> object) {
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, 48, 43);
}	
}