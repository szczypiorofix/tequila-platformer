package platformer;

public class Camera {

private float x, y;



public Camera(float x, float y)
{
	this.x = x;
	this.y = y;
}



public void tick(GameObject player)
{
	
	//if (x > -player.getX()+(MainClass.WIDTH /2))
		x = -player.getX() + MainClass.WIDTH / 2;
	
	y = -player.getY()/4 - 320;
	//y = -500;
	//y = -MainClass.WIDTH+250;
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