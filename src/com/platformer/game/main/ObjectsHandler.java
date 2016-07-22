package com.platformer.game.main;

import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import com.platformer.game.objects.Block;
import com.platformer.game.objects.Coin;
import com.platformer.game.objects.BeeObject;
import com.platformer.game.objects.GameObject;
import com.platformer.game.objects.LevelEnd;
import com.platformer.game.objects.PlayerObject;
import com.platformer.game.objects.SceneryObject;
import com.platformer.game.objects.TacoObject;
import com.platformer.game.objects.TequilaBottle;

public class ObjectsHandler {


public LinkedList<GameObject> first_object = new LinkedList<GameObject>();
public LinkedList<GameObject> second_object = new LinkedList<GameObject>();
public final int ROWS = 200, COLS = 40;
private Camera cam;
private ObjectInputStream ois;
private int[][] tileValues;
private PlayerObject player;
private InputStream in = null;


public ObjectsHandler(Camera cam)
{
	this.cam = cam;
}

public void tick()
{
	for (int i = 0; i < first_object.size(); i++)
	{
		first_object.get(i).tick(first_object);
	}
	for (int i = 0; i < second_object.size(); i++)
	{
		second_object.get(i).tick(second_object);
	}
}

public void render(Graphics g)
{
	//for (int i = 0; i < object.size(); i++)
	//{
		//tempObject = object.get(i);
		//if (tempObject.getId() != ObjectId.Player) {
			//	tempObject.render(g);    // PLAYER NA PIERWSZYM MIEJSCU
		//}
		//else {
		///	tempPlayer = (PlayerObject) tempObject;
		//}
	//}
	//tempPlayer.render(g);
	
	for (int i = 0; i < first_object.size(); i++)
	{
		first_object.get(i).render(g);
	}
	for (int i = 0; i < second_object.size(); i++)
	{
		second_object.get(i).render(g);
	}
}

private void clearLevel()
{
	first_object.clear();
	second_object.clear();
}

public void addObject(GameObject object)
{
	if (object.getId() != ObjectId.BeeEnemy && object.getId() != ObjectId.Player) this.first_object.add(object);
	else this.second_object.add(object);
}


public void removeObject(GameObject object)
{
	this.first_object.remove(object);
	this.second_object.remove(object);
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
		loadLevel(2);
		break;
	case 2:
		loadLevel(3);
		break;
	case 3:
		loadLevel(4);
		break;
	case 4:
		loadLevel(5);
		break;
	}
	
	MainScreen.LEVEL++;
}

public void loadLevel(int level)
{
	tileValues = new int[40][100];
	
	try {
		if (level == 1) in = getClass().getResourceAsStream("/level1.lvl");
		if (level == 2) in = getClass().getResourceAsStream("/level2.lvl");
		if (level == 3) in = getClass().getResourceAsStream("/level3.lvl");
		if (level == 4) in = getClass().getResourceAsStream("/level4.lvl");
		if (level == 5) in = getClass().getResourceAsStream("/level5.lvl");
		
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
					addObject(new Block(ObjectId.Block, yy*50, (int) ((xx*50) - MainClass.HEIGHT), tileValues[xx][yy]));
				if (tileValues[xx][yy] >= 16 && tileValues[xx][yy] < 30)
					addObject(new SceneryObject(ObjectId.Scenery, yy*50, (int) ((xx*50) - MainClass.HEIGHT), tileValues[xx][yy]));
				if (tileValues[xx][yy] == 30)
				{
					 /// TRZEBA PAMIÊTAÆ O DODANIU PLAYERA !!! INACZEJ GRA WYRZUCA B£AD W KLASIE CAMERA !!!
					if (player == null) {
						player = new PlayerObject(ObjectId.Player, yy*50, (int) ((xx*50) - MainClass.HEIGHT), this);
					}
					player.setX(yy*50);
					player.setY((int) ((xx*50) - MainClass.HEIGHT));
					addObject(player);
					
				}
				if (tileValues[xx][yy] == 31)
				{
					addObject(new LevelEnd(ObjectId.LevelEnd, yy*50, (int) ((xx*50) - MainClass.HEIGHT)));
				}
				
				if (tileValues[xx][yy] == 32)
				{
					addObject(new Coin(ObjectId.Coin, yy*50, (int) ((xx*50) - MainClass.HEIGHT)));
				}
				if (tileValues[xx][yy] == 33)
				{
					addObject(new BeeObject(ObjectId.BeeEnemy, yy*50, (int) ((xx*50) - MainClass.HEIGHT)));
				}
				if (tileValues[xx][yy] == 34)
				{
					addObject(new TequilaBottle(ObjectId.Tequila, yy*50, (int) ((xx*50) - MainClass.HEIGHT)));
				}
				if (tileValues[xx][yy] == 35)
				{
					addObject(new TacoObject(ObjectId.Taco, yy*50, (int) ((xx*50) - MainClass.HEIGHT)));
				}
			}
		}	
}
}