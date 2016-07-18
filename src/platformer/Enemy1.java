package platformer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Enemy1 extends GameObject{

Textures tex = MainScreen.getInstance();
private Animation enemy1MovingR, enemy1MovingL;
private int moving = 1;
private int startPos = 0;

public Enemy1(ObjectId id, float x, float y) {
	super(id, x, y, 128, 128);
	startPos = (int) x;
	enemy1MovingR = new Animation(10, tex.enemy1AnimR[0], tex.enemy1AnimR[1], tex.enemy1AnimR[2], tex.enemy1AnimR[3]);
	enemy1MovingL = new Animation(10, tex.enemy1AnimL[0], tex.enemy1AnimL[1], tex.enemy1AnimL[2], tex.enemy1AnimL[3]);
}

@Override
public void render(Graphics g) {
	if (moving == 1) enemy1MovingR.drawAnimation(g, (int) x, (int) y-14);
	else enemy1MovingL.drawAnimation(g, (int) x, (int) y-14);
}

@Override
public void tick(ArrayList<GameObject> object) {
	if (moving == 1) x++;
	if (moving == -1) x --;
	
	if (((int) x) > startPos+250) moving = -1;
	if ((int) x < startPos) moving = 1;
	
	if (moving == 1) enemy1MovingR.runAnimation();
	else enemy1MovingL.runAnimation();
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, 64, 64);
}
}