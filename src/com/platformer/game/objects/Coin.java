package com.platformer.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.platformer.game.graphics.Animation;
import com.platformer.game.graphics.Textures;
import com.platformer.game.main.MainScreen;
import com.platformer.game.main.ObjectId;

public class Coin extends GameObject{

Textures tex = MainScreen.getInstance();
private Animation coinRotating;


public Coin(ObjectId id, float x, float y) {
	super(id, x, y, 32, 32);
	coinRotating = new Animation(5, tex.coinAnim[0], tex.coinAnim[1], tex.coinAnim[2], tex.coinAnim[3], tex.coinAnim[4], tex.coinAnim[5]
			, tex.coinAnim[6], tex.coinAnim[7], tex.coinAnim[8], tex.coinAnim[9], tex.coinAnim[10], tex.coinAnim[11], tex.coinAnim[12], tex.coinAnim[13]
			, tex.coinAnim[14], tex.coinAnim[15], tex.coinAnim[16], tex.coinAnim[17], tex.coinAnim[18], tex.coinAnim[19], tex.coinAnim[20]
			, tex.coinAnim[21], tex.coinAnim[22], tex.coinAnim[23], tex.coinAnim[24], tex.coinAnim[25], tex.coinAnim[26], tex.coinAnim[27], tex.coinAnim[28]
			, tex.coinAnim[29], tex.coinAnim[30], tex.coinAnim[31], tex.coinAnim[32], tex.coinAnim[33], tex.coinAnim[34], tex.coinAnim[35], tex.coinAnim[36]
			, tex.coinAnim[37], tex.coinAnim[38], tex.coinAnim[39], tex.coinAnim[40], tex.coinAnim[41], tex.coinAnim[42], tex.coinAnim[43], tex.coinAnim[44]
			, tex.coinAnim[45], tex.coinAnim[46], tex.coinAnim[47], tex.coinAnim[48], tex.coinAnim[49], tex.coinAnim[50], tex.coinAnim[51], tex.coinAnim[52]
			, tex.coinAnim[53], tex.coinAnim[54], tex.coinAnim[55], tex.coinAnim[56], tex.coinAnim[57], tex.coinAnim[58], tex.coinAnim[59]);
}

@Override
public void render(Graphics g) {	
	coinRotating.drawAnimation(g, (int) x, (int) y);
}

@Override
public void tick(ArrayList<GameObject> object) {
	coinRotating.runAnimation();
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, 32, 32);
}
}