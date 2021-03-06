package com.platformer.game.main;

import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.logging.Level;

import com.platformer.game.objects.AngryCactus;
import com.platformer.game.objects.BeeObject;
import com.platformer.game.objects.Block;
import com.platformer.game.objects.ButtonBlock;
import com.platformer.game.objects.Clouds;
import com.platformer.game.objects.Coin;
import com.platformer.game.objects.Collectibles;
import com.platformer.game.objects.FallingBlock;
import com.platformer.game.objects.GameObject;
import com.platformer.game.objects.Goat;
import com.platformer.game.objects.LevelEnd;
import com.platformer.game.objects.MovingBlockX;
import com.platformer.game.objects.MovingBlockY;
import com.platformer.game.objects.MovingCrate;
import com.platformer.game.objects.PlayerObject;
import com.platformer.game.objects.PushingMovingBlockX;
import com.platformer.game.objects.PushingMovingBlockY;
import com.platformer.game.objects.SceneryObject;
import com.platformer.game.objects.SpikeBlock;
import com.platformer.game.objects.SpringBlock;
import com.platformer.game.objects.TacoObject;
import com.platformer.game.objects.TequilaBottle;
import com.platformer.game.objects.Tumbleweed;
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
private LinkedList<GameObject> buttonBlock_List = new LinkedList<GameObject>();
private LinkedList<GameObject> pushingMovingBlockX_List = new LinkedList<GameObject>();
private LinkedList<GameObject> pushingMovingBlockY_List = new LinkedList<GameObject>();
private LinkedList<GameObject> spikeBlock_List = new LinkedList<GameObject>();
private LinkedList<GameObject> tumbleweed_List = new LinkedList<GameObject>();
private LinkedList<GameObject> springBlock_List = new LinkedList<GameObject>();
private LinkedList<GameObject> fallingBlock_List = new LinkedList<GameObject>();
private LinkedList<GameObject> clouds_List = new LinkedList<GameObject>();
private LinkedList<GameObject> collectibles_List = new LinkedList<GameObject>();
private LinkedList<GameObject> goat_List = new LinkedList<GameObject>();


private final int ROWS = 300, COLS = 12;
private Camera cam;
private ObjectInputStream ois;
private int[][] tileValues;
private PlayerObject player;
private InputStream in = null;
private Achievements achievements;
private int[] collectibles;


public ObjectsHandler(Camera cam, Achievements achievements, int[] collectibles)
{
	this.cam = cam;
	this.achievements = achievements;
	this.collectibles = collectibles;
}

public void iteratingTick(LinkedList<GameObject> list)
{
	for (int i = 0; i < list.size(); i++) {
		if (list.get(i).getX() < -cam.getX()+1100  &&  list.get(i).getX() > -cam.getX()-280) list.get(i).setVisible(true);
		else list.get(i).setVisible(false);
		
		list.get(i).tick(list);
	}
}

public void tick()
{
	iteratingTick(angryCactus_List);
	iteratingTick(bee_List);
	iteratingTick(springBlock_List);
	iteratingTick(block_List);
	iteratingTick(coin_List);
	iteratingTick(dart_List);
	iteratingTick(levelEnd_List);
	iteratingTick(movingBlockX_List);
	iteratingTick(movingBlockY_List);
	iteratingTick(pushingMovingBlockY_List);
	iteratingTick(pushingMovingBlockX_List);
	iteratingTick(movingCrate_List);
	iteratingTick(player_List);
	iteratingTick(scenery_List);
	iteratingTick(taco_List);
	iteratingTick(tequila_List);
	iteratingTick(water_List);
	iteratingTick(buttonBlock_List);
	iteratingTick(spikeBlock_List);
	iteratingTick(tumbleweed_List);
	iteratingTick(fallingBlock_List);
	iteratingTick(clouds_List);
	iteratingTick(collectibles_List);
	iteratingTick(goat_List);
}

public void iteratingRender(Graphics g, LinkedList<GameObject> list)
{
	for (int i = 0; i < list.size(); i++) {
		if (list.get(i).isVisible()) list.get(i).render(g);
	}
}

public void render(Graphics g)
{
	iteratingRender(g, clouds_List);
	iteratingRender(g, scenery_List);
	iteratingRender(g, angryCactus_List);
	iteratingRender(g, block_List);
	iteratingRender(g, coin_List);
	iteratingRender(g, levelEnd_List);
	iteratingRender(g, movingBlockX_List);
	iteratingRender(g, movingBlockY_List);
	iteratingRender(g, pushingMovingBlockX_List);
	iteratingRender(g, pushingMovingBlockY_List);
	iteratingRender(g, movingCrate_List);
	iteratingRender(g, taco_List);
	iteratingRender(g, buttonBlock_List);
	iteratingRender(g, tequila_List);
	iteratingRender(g, bee_List);
	iteratingRender(g, dart_List);
	iteratingRender(g, collectibles_List);
	iteratingRender(g, goat_List);
	iteratingRender(g, player_List);
	iteratingRender(g, fallingBlock_List);
	iteratingRender(g, water_List);
	iteratingRender(g, spikeBlock_List);
	iteratingRender(g, springBlock_List);
	iteratingRender(g, tumbleweed_List);
}

public void clearLevel()
{
	angryCactus_List.clear();
	bee_List.clear();
	block_List.clear();
	buttonBlock_List.clear();
	coin_List.clear();
	dart_List.clear();
	levelEnd_List.clear();
	movingBlockX_List.clear();
	movingBlockY_List.clear();
	pushingMovingBlockX_List.clear();
	pushingMovingBlockY_List.clear();
	movingCrate_List.clear();
	player_List.clear();
	scenery_List.clear();
	taco_List.clear();
	tequila_List.clear();
	water_List.clear();
	spikeBlock_List.clear();
	tumbleweed_List.clear();
	springBlock_List.clear();
	fallingBlock_List.clear();
	clouds_List.clear();
	collectibles_List.clear();
	goat_List.clear();
}

public void resetLevelStatistics()
{
	MainScreen.COINS = 0;
	MainScreen.SCORE = 0;
	MainScreen.TOTAL_SCORE = 0;
	MainScreen.minutes = 0;
	MainScreen.seconds = 0;
	MainScreen.millis = 0;
	MainScreen.time_bonus = MainScreen.MAX_TIME_BONUS;
}

public PlayerObject getPlayer()
{
	return player;
}

public void switchLevel()
{
	clearLevel();
	resetLevelStatistics();
	player.setHealth(player.getMaxHealth());
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
	case 5:
		loadLevel(6);
		break;
	case 6:
		loadLevel(7);
		break;
	case 7:
		loadLevel(8);
		break;
	case 8:
		loadLevel(9);
		break;
	case 9:
		loadLevel(10);
		break;
	case 10:
		loadLevel(11);
		break;
	}
	cam.setX(0);
	if (MainScreen.LEVEL < 11) MainScreen.LEVEL++;
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
		if (level == 6) in = getClass().getResourceAsStream("/level6.lvl");
		if (level == 7) in = getClass().getResourceAsStream("/level7.lvl");
		if (level == 8) in = getClass().getResourceAsStream("/level8.lvl");
		if (level == 9) in = getClass().getResourceAsStream("/level9.lvl");
		if (level == 10) in = getClass().getResourceAsStream("/level10.lvl");
		if (level == 11) in = getClass().getResourceAsStream("/level11.lvl");
		
		ois = new ObjectInputStream(in);
		tileValues = (int[][]) (ois.readObject());
		ois.close();
		MainClass.logging(false, Level.INFO, "Pliku poziomu nr. "+level +" załadowany poprawnie.");
	}
	catch (IOException | ClassNotFoundException ioe)
	{
		MainClass.logging(false, Level.WARNING, "Błąd odczytu pliku poziomu nr. "+level);
		MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(ioe));
	}
	

		for (int xx = 0; xx < ROWS; xx++)
		for (int yy = 0; yy < COLS; yy++)
		{
			
			if (tileValues[yy][xx] != -1)
			{
				
				if (tileValues[yy][xx] < 16) block_List.add(new Block(xx*50, (yy*50)+550, tileValues[yy][xx]));
					
				if (tileValues[yy][xx] >= 16 && tileValues[yy][xx] < 30) scenery_List.add(new SceneryObject(xx*50, (yy*50)+550, tileValues[yy][xx]));
					
				if (tileValues[yy][xx] == 28)
				{
					player = new PlayerObject(xx*50, (yy*50) +440, this, achievements, collectibles);
					player_List.add(player);					
				}
				if (tileValues[yy][xx] == 29) levelEnd_List.add(new LevelEnd(xx*50, (yy*50)+550));
				
				if (tileValues[yy][xx] == 30) coin_List.add(new Coin(xx*50, (yy*50)+550));
				
				if (tileValues[yy][xx] == 31) bee_List.add(new BeeObject(xx*50, (yy*50)+550));
				
				if (tileValues[yy][xx] == 32) tequila_List.add(new TequilaBottle(xx*50, (yy*50)+550));
				
				if (tileValues[yy][xx] == 33) taco_List.add(new TacoObject(xx*50, (yy*50)+550));
				
				if (tileValues[yy][xx] == 34) movingBlockX_List.add(new MovingBlockX(xx*50, (yy*50)+550));
				
				if (tileValues[yy][xx] == 35) movingBlockY_List.add(new MovingBlockY(xx*50, (yy*50)+550));
				
				if (tileValues[yy][xx] == 36) water_List.add(new WaterObject(xx*50, (yy*50)+550, 0));
				
				if (tileValues[yy][xx] == 37) water_List.add(new WaterObject(xx*50, (yy*50)+550, 1));
				
				if (tileValues[yy][xx] == 38) angryCactus_List.add(new AngryCactus(xx*50, (yy*50)+550));
				
				if (tileValues[yy][xx] == 39) movingCrate_List.add(new MovingCrate(xx*50, (yy*50)+550, this));
				
				if (tileValues[yy][xx] == 40) buttonBlock_List.add(new ButtonBlock(xx*50, (yy*50)+550, this));
				
				if (tileValues[yy][xx] == 41) pushingMovingBlockX_List.add(new PushingMovingBlockX(xx*50, (yy*50)+550));
				
				if (tileValues[yy][xx] == 42) pushingMovingBlockY_List.add(new PushingMovingBlockY(xx*50, (yy*50)+550));
				
				if (tileValues[yy][xx] == 43) spikeBlock_List.add(new SpikeBlock(xx*50, (yy*50)+550));
				
				if (tileValues[yy][xx] == 44) tumbleweed_List.add(new Tumbleweed(xx*50, (yy*50)+550, this));
				
				if (tileValues[yy][xx] == 45) springBlock_List.add(new SpringBlock(xx*50, (yy*50)+550));
				
				if (tileValues[yy][xx] == 46) fallingBlock_List.add(new FallingBlock(xx*50, (yy*50)+550, this));
				
				if (tileValues[yy][xx] == 47) clouds_List.add(new Clouds(xx*50, (yy*50)+550, cam, 1));
				
				if (tileValues[yy][xx] == 48) clouds_List.add(new Clouds(xx*50, (yy*50)+550, cam, 2));
				
				if (tileValues[yy][xx] == 49) clouds_List.add(new Clouds(xx*50, (yy*50)+550, cam, 3));
				
				if (tileValues[yy][xx] == 50) collectibles_List.add(new Collectibles(xx*50, (yy*50)+550, this));
				
				if (tileValues[yy][xx] == 51) goat_List.add(new Goat(xx*50, (yy*50)+550));
			}
		}
		MainClass.logging(false, Level.INFO, "Wszystkie obiekty poziomu gry "+level +" zostały załadowane poprawnie.");
		player.setMaxCoins(coin_List.size());
		player.setMaxPowerups(tequila_List.size() + taco_List.size());
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

public LinkedList<GameObject> getButtonBlock_List() {
	return buttonBlock_List;
}

public LinkedList<GameObject> getPushingMovingBlockX_List() {
	return pushingMovingBlockX_List;
}

public LinkedList<GameObject> getPushingMovingBlockY_List() {
	return pushingMovingBlockY_List;
}

public LinkedList<GameObject> getSpikeBlock_List() {
	return spikeBlock_List;
}

public LinkedList<GameObject> getTumbleweed_List() {
	return tumbleweed_List;
}

public LinkedList<GameObject> getSpringBlock_List() {
	return springBlock_List;
}

public LinkedList<GameObject> getFallingBlock_List() {
	return fallingBlock_List;
}

public LinkedList<GameObject> getClouds_List() {
	return clouds_List;
}

public LinkedList<GameObject> getCollectibles_List() {
	return collectibles_List;
}

public LinkedList<GameObject> getGoat_List() {
	return goat_List;
}
}