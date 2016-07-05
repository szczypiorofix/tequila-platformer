package platformer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class Block extends GameObject{

protected static final int brickWidth = 32;
protected static final int brickHeight = 32;
private BufferedImage image = null;


public Block(ObjectId id, float x, float y, BufferedImage image) {
	super(id, x, y);
	this.image = image;
}

@Override
public void render(Graphics g) {
	//g.setColor(Color.YELLOW);
	//g.drawRect((int) x, (int) y, brickWidth, brickHeight);
	g.drawImage(image, (int)x, (int) y, brickWidth, brickHeight, null);
}

@Override
public void tick(LinkedList<GameObject> object) {
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, brickWidth, brickHeight);
}
}