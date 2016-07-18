package platformer;

import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ObjectsHandler {


public ArrayList<GameObject> object = new ArrayList<GameObject>();
public final int ROWS = 200, COLS = 40;
private GameObject tempObject;
private Camera cam;
private ObjectInputStream ois;
private int[][] tileValues;
private PlayerObject player;
private InputStream in = null;
private PlayerObject tempPlayer;


public ObjectsHandler(Camera cam)
{
	this.cam = cam;
}

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
		if (tempObject.getId() != ObjectId.Player) {
				tempObject.render(g);    // PLAYER NA PIERWSZYM MIEJSCU
		}
		else {
			tempPlayer = (PlayerObject) tempObject;
		}
	}
	tempPlayer.render(g);
}

private void clearLevel()
{
	object.clear();
}

public void addObject(GameObject object)
{
	this.object.add(object);
}


public void removeObject(GameObject object)
{
	this.object.remove(object);
}

public PlayerObject getPlayer()
{
	return player;
}

public void switchLevel()
{
	clearLevel();
	cam.setX(0);
	
	switch(MainScreen.LEVEL)
	{
	case 1:
		loadImageLevel(2);
		break;
	case 2:
		loadImageLevel(3);
		break;
	}
	MainScreen.LEVEL++;
}

public void loadImageLevel(int level)
{
	tileValues = new int[40][100];
	
	try {
		if (level == 1) in = getClass().getResourceAsStream("/level1.lvl");
		if (level == 2) in = getClass().getResourceAsStream("/level2.lvl");
		if (level == 3) in = getClass().getResourceAsStream("/level3.lvl");
		
		ois = new ObjectInputStream(in);
		tileValues = (int[][]) (ois.readObject());
		ois.close();
	}
	catch (IOException | ClassNotFoundException ioe)
	{
		ioe.printStackTrace();
		System.exit(0);
	}
	
	for (int xx = 0; xx < COLS; xx++)
		for (int yy = 0; yy < ROWS; yy++)
		{
			
			if (tileValues[xx][yy] != -1)
			{
				if (tileValues[xx][yy] < 16)
					addObject(new Block(ObjectId.Block, yy*50, (int) ((xx*50) - MainClass.HEIGHT + (MainClass.HEIGHT*0.7)), tileValues[xx][yy]));
				if (tileValues[xx][yy] >= 16 && tileValues[xx][yy] < 30)
					addObject(new SceneryObject(ObjectId.Scenery, yy*50, (int) ((xx*50) - MainClass.HEIGHT+ (MainClass.HEIGHT *0.7)), tileValues[xx][yy]));
				if (tileValues[xx][yy] == 30)
				{
					 /// TRZEBA PAMIÊTAÆ O DODANIU PLAYERA !!! INACZEJ GRA WYRZUCA B£AD W KLASIE CAMERA !!!
					if (player == null) {
						player = new PlayerObject(ObjectId.Player, yy*50, (int) ((xx*50) - MainClass.HEIGHT + (MainClass.HEIGHT*0.7)), this);
					}
					player.setX(yy*50);
					player.setY((int) ((xx*50) - MainClass.HEIGHT + (MainClass.HEIGHT*0.7)));
					addObject(player);
					
				}
				if (tileValues[xx][yy] == 31)
				{
					addObject(new LevelEnd(ObjectId.LevelEnd, yy*50, (int) ((xx*50) - MainClass.HEIGHT + (MainClass.HEIGHT*0.7))));
				}
				
				if (tileValues[xx][yy] == 32)
				{
					addObject(new Coin(ObjectId.Coin, yy*50, (int) ((xx*50) - MainClass.HEIGHT + (MainClass.HEIGHT*0.7))));
				}
				if (tileValues[xx][yy] == 33)
				{
					addObject(new Enemy1(ObjectId.Enemy1, yy*50, (int) ((xx*50) - MainClass.HEIGHT + (MainClass.HEIGHT*0.7))));
				}
			}
		}	
}
}