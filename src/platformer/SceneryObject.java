package platformer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class SceneryObject extends GameObject{

protected static final int brickWidth = 45;
protected static final int brickHeight = 45;
Textures tex = MainScreen.getInstance();
private int type;



public SceneryObject(ObjectId id, float x, float y, int type) {
	super(id, x, y, brickWidth, brickHeight);
	this.type = type;
}

@Override
public void render(Graphics g) {
	if (type == 0) g.drawImage(tex.sceneryObjects[0], (int)x-tex.sceneryObjects[0].getWidth() /2, (int)y-tex.sceneryObjects[0].getHeight()/2, null);
	if (type == 1) g.drawImage(tex.sceneryObjects[1], (int)x-tex.sceneryObjects[1].getWidth() /2, (int)y-tex.sceneryObjects[1].getHeight()/2+5, null);
	if (type == 2) g.drawImage(tex.sceneryObjects[2], (int)x-tex.sceneryObjects[2].getWidth() /2, (int)y-tex.sceneryObjects[2].getHeight()/2+5, null);
	if (type == 3) g.drawImage(tex.sceneryObjects[3], (int)x-tex.sceneryObjects[3].getWidth() /2, (int)y-tex.sceneryObjects[3].getHeight()/2+20, null);
	if (type == 4) g.drawImage(tex.sceneryObjects[4], (int)x-tex.sceneryObjects[4].getWidth() /2+30, (int)y-tex.sceneryObjects[4].getHeight()/2+5, null);
	if (type == 5) g.drawImage(tex.sceneryObjects[5], (int)x-tex.sceneryObjects[5].getWidth() /2, (int)y-tex.sceneryObjects[5].getHeight()/2+5, null);
	if (type == 6) g.drawImage(tex.sceneryObjects[6], (int)x-tex.sceneryObjects[6].getWidth() /2, (int)y-tex.sceneryObjects[6].getHeight()/2+5, null);
	if (type == 7) g.drawImage(tex.sceneryObjects[7], (int)x-tex.sceneryObjects[7].getWidth() /2, (int)y-tex.sceneryObjects[7].getHeight()/2+5, null);
	if (type == 8) g.drawImage(tex.sceneryObjects[8], (int)x-tex.sceneryObjects[8].getWidth() /2, (int)y-tex.sceneryObjects[8].getHeight()/2+5, null);
	if (type == 9) g.drawImage(tex.sceneryObjects[9], (int)x-tex.sceneryObjects[9].getWidth() /2, (int)y-tex.sceneryObjects[9].getHeight()/2+5, null);
	if (type == 10) g.drawImage(tex.sceneryObjects[10], (int)x-tex.sceneryObjects[10].getWidth() /2, (int)y-tex.sceneryObjects[10].getHeight()/2+5, null);
	if (type == 11) g.drawImage(tex.sceneryObjects[11], (int)x-tex.sceneryObjects[11].getWidth() /2, (int)y-tex.sceneryObjects[11].getHeight()/2+5, null);
	if (type == 12) g.drawImage(tex.sceneryObjects[12], (int)x-tex.sceneryObjects[12].getWidth() /2, (int)y-tex.sceneryObjects[12].getHeight()/2+5, null);
	if (type == 13) g.drawImage(tex.sceneryObjects[13], (int)x-tex.sceneryObjects[13].getWidth() /2, (int)y-tex.sceneryObjects[13].getHeight()/2+5, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, brickWidth, brickHeight);
}
}