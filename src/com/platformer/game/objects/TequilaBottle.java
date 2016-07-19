package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class TequilaBottle extends GameObject{

	
Textures tex = MainScreen.getInstance();

	
public TequilaBottle(ObjectId id, float x, float y) {
		super(id, x, y, 32, 60);
}

@Override
public void render(Graphics g) {
	g.drawImage(tex.tequilaImage.getScaledInstance(32, 60, 0), (int) x, (int) y-40, null);
}

@Override
public void tick(ArrayList<GameObject> object) {
	
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y-40, 32, 60);
}
}