package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class TequilaBottle extends GameObject{

	
private Textures tex = MainScreen.getInstance();
private final static int TEQUILA_WIDTH = 32;
private final static int TEQUILA_HEIGHT = 60;
	
public TequilaBottle(ObjectId id, float x, float y) {
		super(id, x, y, TEQUILA_WIDTH, TEQUILA_HEIGHT);
}

@Override
public void render(Graphics g) {
	g.drawImage(tex.tequilaImage.getScaledInstance(TEQUILA_WIDTH, TEQUILA_HEIGHT, 0), (int) x, (int) y-40, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
	
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y-40, TEQUILA_WIDTH, TEQUILA_HEIGHT);
}
}