package com.platformer.game.main;

import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import com.platformer.game.objects.AngryCactus;
import com.platformer.game.objects.BeeObject;
import com.platformer.game.objects.Block;
import com.platformer.game.objects.Coin;
import com.platformer.game.objects.GameObject;
import com.platformer.game.objects.LevelEnd;
import com.platformer.game.objects.MovingBlockX;
import com.platformer.game.objects.MovingBlockY;
import com.platformer.game.objects.MovingCrate;
import com.platformer.game.objects.PlayerObject;
import com.platformer.game.objects.SceneryObject;
import com.platformer.game.objects.TacoObject;
import com.platformer.game.objects.TequilaBottle;
import com.platformer.game.objects.WaterObject;

public class ObjectsHandler {


private LinkedList<GameObject> angryCactus_List = new LinkedList<GameObject>();
private LinkedList<GameObject> bee_List = new LinkedList<GameObject>();
private LinkedList<GameObject> block_List = new LinkedList<GameObject>();
private LinkedList<GameObject> coin_List = new LinkedList<GameObject>();
private LinkedList<GameObject> dart_List = new LinkedList<GameObject>();
private LinkedList<GameObject> levelEnd_List = new LinkedList<GameObject>();
private LinkedList<GameObject> movingBlockX_List = new LinkedList<GameObject>();
private LinkedList<GameObject> movingBlockY_List = new LinkedList<GameObject>();
private LinkedList<GameObject> movingCrate_List = new LinkedList<GameObject>();
private LinkedList<GameObject> player_List = new LinkedList<GameObject>();
private LinkedList<GameObject> scenery_List = new LinkedList<GameObject>();
private LinkedList<GameObject> taco_List = new LinkedList<GameObject>();
private LinkedList<GameObject> tequila_List = new LinkedList<GameObject>();
private LinkedList<GameObject> water_List = new LinkedList<GameObject>();



private final int ROWS = 200, COLS = 40;
private Camera cam;
private ObjectInputStream ois;
private int[][] tileValues;
private PlayerObject player;
private InputStream in = null;


public ObjectsHandler(Camera cam)
{
	this.cam = cam;
}

public void iteratingTick(LinkedList<GameObject> list)
{
	for (int i = 0; i < list.size(); i++) list.get(i).tick(list);
}

public void tick()
{
	iteratingTick(angryCactus_List);
	iteratingTick(bee_List);
	iteratingTick(block_List);
	iteratingTick(coin_List);
	iteratingTick(dart_List);
	iteratingTick(levelEnd_List);
	iteratingTick(movingBlockX_List);
	iteratingTick(movingBlockY_List);
	iteratingTick(movingCrate_List);
	iteratingTick(player_List);
	iteratingTick(scenery_List);
	iteratingTick(taco_List);
	iteratingTick(tequila_List);
	iteratingTick(water_List);

}

public void iteratingRender(Graphics g, LinkedList<GameObject> list)
{
	for (int i = 0; i < list.size(); i++) list.get(i).render(g);
}

public void render(Graphics g)
{
	iteratingRender(g, angryCactus_List);
	iteratingRender(g, bee_List);
	iteratingRender(g, block_List);
	iteratingRender(g, coin_List);
	iteratingRender(g, dart_List);
	iteratingRender(g, levelEnd_List);
	iteratingRender(g, movingBlockX_List);
	iteratingRender(g, movingBlockY_List);
	iteratingRender(g, movingCrate_List);
	iteratingRender(g, scenery_List);
	iteratingRender(g, taco_List);
	iteratingRender(g, tequila_List);
	iteratingRender(g, player_List);
	iteratingRender(g, water_List);
}

public void clearLevel()
{
	angryCactus_List.clear();
	bee_List.clear();
	block_List.clear();
	coin_List.clear();
	dart_List.clear();
	levelEnd_List.clear();
	movingBlockX_List.clear();
	movingBlockY_List.clear();
	movingCrate_List.clear();
	player_List.clear();
	scenery_List.clear();
	taco_List.clear();
	tequila_List.clear();
	water_List.clear();
}

public void resetLevel()
{
	MainScreen.COINS = 0;
	MainScreen.SCORE = 0;
	MainScreen.TOTAL_SCORE = 0;
	MainScreen.minutes = 0;
	MainScreen.seconds = 0;
	MainScreen.milis = 0f;
	MainScreen.time_bonus = MainScreen.MAX_TIME_BONUS;
	//player.setX(100);
	//player.setY(100);
	
	//player.setHealth(5);
}

public PlayerObject getPlayer()
{
	return player;
}

public void switchLevel()
{
	clearLevel();
	resetLevel();
	
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
	cam.setX(0);
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
				{
					block_List.add(new Block(ObjectId.Block, yy*50, (xx*50) - MainClass.HEIGHT, tileValues[xx][yy]));
				}
					
				if (tileValues[xx][yy] >= 16 && tileValues[xx][yy] < 30)
				{
					scenery_List.add(new SceneryObject(ObjectId.Scenery, yy*50, (xx*50) - MainClass.HEIGHT, tileValues[xx][yy]));
				}
					
				if (tileValues[xx][yy] == 30)
				{
					player = new PlayerObject(ObjectId.Player, yy*50, (xx*50) - MainClass.HEIGHT, this);
					player_List.add(player);					
				}
				if (tileValues[xx][yy] == 31)
				{
					levelEnd_List.add(new LevelEnd(ObjectId.LevelEnd, yy*50, (xx*50) - MainClass.HEIGHT));
				}
				
				if (tileValues[xx][yy] == 32)
				{
					coin_List.add(new Coin(ObjectId.Coin, yy*50, (xx*50) - MainClass.HEIGHT));
				}
				if (tileValues[xx][yy] == 33)
				{
					bee_List.add(new BeeObject(ObjectId.BeeEnemy, yy*50, (xx*50) - MainClass.HEIGHT));
				}
				if (tileValues[xx][yy] == 34)
				{
					tequila_List.add(new TequilaBottle(ObjectId.Tequila, yy*50, (xx*50) - MainClass.HEIGHT));
				}
				if (tileValues[xx][yy] == 35)
				{
					taco_List.add(new TacoObject(ObjectId.Taco, yy*50, (xx*50) - MainClass.HEIGHT));
				}
				if (tileValues[xx][yy] == 36)
				{
					movingBlockX_List.add(new MovingBlockX(ObjectId.MovingBlockX, yy*50, (xx*50) - MainClass.HEIGHT));
				}
				if (tileValues[xx][yy] == 37)
				{
					movingBlockY_List.add(new MovingBlockY(ObjectId.MovingBlockY, yy*50, (xx*50) - MainClass.HEIGHT));
				}
				if (tileValues[xx][yy] == 38)
				{
					water_List.add(new WaterObject(ObjectId.Water, yy*50, (xx*50) - MainClass.HEIGHT, 0));
				}
				if (tileValues[xx][yy] == 39)
				{
					water_List.add(new WaterObject(ObjectId.Water, yy*50, (xx*50) - MainClass.HEIGHT, 1));
				}
				if (tileValues[xx][yy] == 40)
				{
					angryCactus_List.add(new AngryCactus(ObjectId.AngryCactus, yy*50, (xx*50) - MainClass.HEIGHT, 1));
				}
				if (tileValues[xx][yy] == 41)
				{
					movingCrate_List.add(new MovingCrate(ObjectId.MovingCrate, yy*50, (xx*50) - MainClass.HEIGHT, this));
				}
			}
		}	
}

public LinkedList<GameObject> getAngryCactus_List() {
	return angryCactus_List;
}

public LinkedList<GameObject> getBee_List() {
	return bee_List;
}

public LinkedList<GameObject> getBlock_List() {
	return block_List;
}

public LinkedList<GameObject> getCoin_List() {
	return coin_List;
}

public LinkedList<GameObject> getDart_List() {
	return dart_List;
}

public LinkedList<GameObject> getLevelEnd_List() {
	return levelEnd_List;
}

public LinkedList<GameObject> getMovingBlockX_List() {
	return movingBlockX_List;
}

public LinkedList<GameObject> getMovingBlockY_List() {
	return movingBlockY_List;
}

public LinkedList<GameObject> getMovingCrate_List() {
	return movingCrate_List;
}

public LinkedList<GameObject> getPlayer_List() {
	return player_List;
}

public LinkedList<GameObject> getScenery_List() {
	return scenery_List;
}

public LinkedList<GameObject> getTaco_List() {
	return taco_List;
}

public LinkedList<GameObject> getTequila_List() {
	return tequila_List;
}

public LinkedList<GameObject> getWater_List() {
	return water_List;
}
}