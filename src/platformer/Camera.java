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
	//if ((player.getX() > MainClass.WIDTH -300)) 
		x = -player.getX() + MainClass.WIDTH /2; 
				//-300;
	y = -player.getY() + MainClass.HEIGHT/2;
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