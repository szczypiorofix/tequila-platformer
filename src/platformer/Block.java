package platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Block extends GameObject{

private final int brickWidth = 30;
private final int brickHeight = 30;
	
	
public Block(ObjectId id, float x, float y) {
	super(id, x, y);
}

@Override
public void render(Graphics g) {
	g.setColor(Color.YELLOW);
	g.drawRect((int) x, (int) y, brickWidth, brickHeight);
}

@Override
public void tick(LinkedList<GameObject> object) {
}

@Override
public Rectangle getBounds() {
	return new Rectangle((int) x, (int) y, brickWidth, brickHeight);
}
}