package com.platformer.game.main;

import com.platformer.game.objects.GameObject;

public class Camera {

private float x, y;


public Camera(float x, float y)
{
	this.x = x;
	this.y = y;
}

public void tick(GameObject player)
{
	if ((player.getX() > MainClass.WIDTH/2) && (player.getX() < 226*64))
		x = -player.getX() + MainClass.WIDTH / 2;
	if (player.getY() < 915) y = -player.getY()/4 - 320;
}

public float getX() {
	return x;
}

public void setX(float x) {
	this.x = x;
}

public float getY() {
	return y;
}

public void setY(float y) {
	this.y = y;
}
}