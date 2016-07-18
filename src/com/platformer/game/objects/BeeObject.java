package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.platformer.game.graphics.Animation;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class BeeObject extends GameObject{

Textures tex = MainScreen.getInstance();
private Animation beeMovingR, beeMovingL;
private int moving = 1;
private int startPos = 0;

public BeeObject(ObjectId id, float x, float y) {
	super(id, x, y, 32, 32);
	startPos = (int) x;
	beeMovingR = new Animation(3, tex.beeR[0], tex.beeR[1], tex.beeR[2], tex.beeR[3], tex.beeR[4]);
	beeMovingL = new Animation(3, tex.beeL[0], tex.beeL[1], tex.beeL[2], tex.beeL[3], tex.beeL[4]);
}

@Override
public void render(Graphics g) {
	if (moving == 1) beeMovingR.drawAnimation(g, (int) x, (int) y-20);
	else beeMovingL.drawAnimation(g, (int) x, (int) y-20);
}

@Override
public void tick(ArrayList<GameObject> object) {
	
	if ((int) x > startPos+240) moving = -1;
	if ((int) x < startPos) moving = 1;
	
	if (moving == 1) x+= 1;
	if (moving == -1) x -= 1;
	
	beeMovingR.runAnimation();
	beeMovingL.runAnimation();
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, 32, 32);
}
}