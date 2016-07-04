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


public void createLevel()
{
	addObject(new Block(ObjectId.Block, 310, MainClass.HEIGHT - 260));
	
	for (int mid = 190; mid < MainClass.WIDTH - 250; mid += 30)
		addObject(new Block(ObjectId.Block, mid, MainClass.HEIGHT - 230));
	
	for (int mid = 150; mid < MainClass.WIDTH - 180; mid += 30)
		addObject(new Block(ObjectId.Block, mid, MainClass.HEIGHT - 140));
	
	for (int south = 0; south < MainClass.WIDTH; south += 30)
		addObject(new Block(ObjectId.Block, south, MainClass.HEIGHT - 30));
}
}