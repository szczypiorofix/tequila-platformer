package platformer;

import java.awt.Graphics;
import java.util.LinkedList;

public class ObjectsHandler {


public LinkedList<GameObject> object = new LinkedList<GameObject>();
private GameObject tempObject;


public void tick()
{
	for (int i = 0; i < object.size(); i++)
	{
		tempObject = object.get(i);
		tempObject.tick(object);
	}
}


public void render(Graphics g)
{
	for (int i = 0; i < object.size(); i++)
	{
		tempObject = object.get(i);
		tempObject.render(g);
	}
}

public void addObject(GameObject object)
{
	this.object.add(object);
}


public void removeObject(GameObject object)
{
	this.object.remove(object);
}


/** Przyk³adowy poziom 1;
 * 
 */
public void createLevel()
{
	addObject(new Block(ObjectId.Block, 60, MainClass.HEIGHT - 60));
	addObject(new Block(ObjectId.Block, 270, MainClass.HEIGHT - 200));
	addObject(new Block(ObjectId.Block, 300, MainClass.HEIGHT - 200));
	addObject(new Block(ObjectId.Block, 330, MainClass.HEIGHT - 200));
	addObject(new Block(ObjectId.Block, 360, MainClass.HEIGHT - 200));
	addObject(new Block(ObjectId.Block, 390, MainClass.HEIGHT - 200));
	addObject(new Block(ObjectId.Block, 300, MainClass.HEIGHT - 230));
	addObject(new Block(ObjectId.Block, 330, MainClass.HEIGHT - 230));
	addObject(new Block(ObjectId.Block, 360, MainClass.HEIGHT - 230));
	addObject(new Block(ObjectId.Block, 330, MainClass.HEIGHT - 260));
	
	for (int mid = 180; mid < MainClass.WIDTH - 220; mid += 30)
		addObject(new Block(ObjectId.Block, mid, MainClass.HEIGHT - 170));
	
	for (int south = 0; south < MainClass.WIDTH; south += 30)
		addObject(new Block(ObjectId.Block, south, MainClass.HEIGHT - 30));
}
}