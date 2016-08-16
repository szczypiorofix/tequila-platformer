package com.platformer.game.main;

import java.awt.image.BufferedImage;

import com.platformer.game.graphics.BufferedImageLoader;


public class Achievements {



public static final int maxAchievements = 10;
private final int showAchievementCooldown = 200;
private boolean showAchievement = false;
private int achievementCount;
private String achievementText;
private BufferedImage achievementImage;


private BufferedImageLoader loader;

private int jump10Count;
private static final int A_10JUMPS = 10;
private boolean jumpCount10Complete = false;
private final String jump10Text = "Wykona³eœ 10 skoków !!!";
private final BufferedImage jump10Image;

private int jump25Count;
private static final int A_25JUMPS = 15;   // 25 - A_10JUMPS
private boolean jumpCount25Complete = false;
private final String jump25Text = "Wykona³eœ 25 skoków !!!";
private final BufferedImage jump25Image;

private int coin20Count;
private static final int A_20COINS = 20;
private boolean coinCount20Complete = false;
private final String coin20Text = "Zebra³eœ 20 monet !!!";
private final BufferedImage coin20Image;

private int coin50Count;
private static final int A_50COINS = 30;   // 50 - A_20COINS
private boolean coinCount50Complete = false;
private final String coin50Text = "Zebra³eœ 50 monet !!!";
private final BufferedImage coin50Image;

private int powerup3Count;
private static final int A_3POWERUP = 3;
private boolean powerupCount3Complete = false;
private final String powerup3Text = "Zebra³eœ 3 power-upy !!!";
private final BufferedImage powerup3Image;

private int complete1LevelCount;
private static final int A_COMPLETE1LEVEL = 1;
private boolean complete1LevelComplete = false;
private final String complete1LevelText = "Ukoñczy³eœ 1 poziom !!!";
private final BufferedImage complete1LevelImage;

private int complete2LevelCount;
private static final int A_COMPLETE2LEVEL = 1;
private boolean complete2LevelComplete = false;
private final String complete2LevelText = "Ukoñczy³eœ 2 poziom !!!";
private final BufferedImage complete2LevelImage;

private int complete3LevelCount;
private static final int A_COMPLETE3LEVEL = 1;
private boolean complete3LevelComplete = false;
private final String complete3LevelText = "Ukoñczy³eœ 3 poziom !!!";
private final BufferedImage complete3LevelImage;

private int complete4LevelCount;
private static final int A_COMPLETE4LEVEL = 1;
private boolean complete4LevelComplete = false;
private final String complete4LevelText = "Ukoñczy³eœ 4 poziom !!!";
private final BufferedImage complete4LevelImage;

private int complete5LevelCount;
private static final int A_COMPLETE5LEVEL = 1;
private boolean complete5LevelComplete = false;
private final String complete5LevelText = "Ukoñczy³eœ 5 poziom !!!";
private final BufferedImage complete5LevelImage;


public Achievements()
{
	loader = new BufferedImageLoader();
	jump10Image = loader.loadImage("/A10jumps.png");
	jump25Image = loader.loadImage("/A25jumps.png");
	coin20Image = loader.loadImage("/A20coins.png");
	coin50Image = loader.loadImage("/A50coins.png");
	powerup3Image = loader.loadImage("/A3powerups.png");
	complete1LevelImage = loader.loadImage("/Acomplete1Level.png");
	complete2LevelImage = loader.loadImage("/Acomplete2Level.png");
	complete3LevelImage = loader.loadImage("/Acomplete3Level.png");
	complete4LevelImage = loader.loadImage("/Acomplete4Level.png");
	complete5LevelImage = loader.loadImage("/Acomplete5Level.png");
	
	achievementCount = showAchievementCooldown;
	jump10Count = 0;
	jump25Count = 0;
	coin20Count = 0;
	coin50Count = 0;
	powerup3Count = 0;
	complete1LevelCount = 0;
	complete2LevelCount = 0;
	complete3LevelCount = 0;
	complete4LevelCount = 0;
	complete5LevelCount = 0;
	achievementText = "";
	achievementImage = null;
}

public void restartLevel()
{
	jump10Count = 0;
	jump25Count = 0;
	coin20Count = 0;
	coin50Count = 0;
	powerup3Count = 0;
	complete1LevelCount = 0;
}

public void addJump10Count()
{
	if (!jumpCount10Complete) {
		jump10Count++;
		if (jump10Count>= A_10JUMPS) {
			jumpCount10Complete = true;
			MainClass.ac[0] = true;
			setAchievementText(jump10Text);
			setAchievementImage(jump10Image);
			setShowAchievement(true);
		}
	}
}

public void addJump25Count()
{
	if (!jumpCount25Complete) {
		jump25Count++;
		if (jump25Count>= A_25JUMPS) {
			jumpCount25Complete = true;
			MainClass.ac[1] = true;
			setAchievementText(jump25Text);
			setAchievementImage(jump25Image);
			setShowAchievement(true);
		}
	}
}

public void addCoin20Count()
{
	if (!coinCount20Complete) {
		coin20Count++;
		if (coin20Count>= A_20COINS) {
			coinCount20Complete = true;
			MainClass.ac[2] = true;
			setAchievementText(coin20Text);
			setAchievementImage(coin20Image);
			setShowAchievement(true);
		}
	}
}

public void addCoin50Count()
{
	if (!coinCount50Complete) {
		coin50Count++;
		if (coin50Count>= A_50COINS) {
			coinCount50Complete = true;
			MainClass.ac[3] = true;
			setAchievementText(coin50Text);
			setAchievementImage(coin50Image);
			setShowAchievement(true);
		}
	}
}

public void addPowerup3Count()
{
	if (!powerupCount3Complete) {
		powerup3Count++;
		if (powerup3Count>= A_3POWERUP) {
			powerupCount3Complete = true;
			MainClass.ac[4] = true;
			setAchievementText(powerup3Text);
			setAchievementImage(powerup3Image);
			setShowAchievement(true);
		}
	}
}

public void addComplete1LevelCount()
{
	if (!complete1LevelComplete) {
		complete1LevelCount++;
		if (complete1LevelCount>= A_COMPLETE1LEVEL) {
			complete1LevelComplete = true;
			MainClass.ac[5] = true;
			setAchievementText(complete1LevelText);
			setAchievementImage(complete1LevelImage);
			setShowAchievement(true);
		}
	}
}

public void addComplete2LevelCount()
{
	if (!complete2LevelComplete) {
		complete2LevelCount++;
		if (complete2LevelCount>= A_COMPLETE2LEVEL) {
			complete2LevelComplete = true;
			MainClass.ac[6] = true;
			setAchievementText(complete2LevelText);
			setAchievementImage(complete2LevelImage);
			setShowAchievement(true);
		}
	}
}

public void addComplete3LevelCount()
{
	if (!complete3LevelComplete) {
		complete3LevelCount++;
		if (complete3LevelCount>= A_COMPLETE3LEVEL) {
			complete3LevelComplete = true;
			MainClass.ac[7] = true;
			setAchievementText(complete3LevelText);
			setAchievementImage(complete3LevelImage);
			setShowAchievement(true);
		}
	}
}

public void addComplete4LevelCount()
{
	if (!complete4LevelComplete) {
		complete4LevelCount++;
		if (complete4LevelCount>= A_COMPLETE4LEVEL) {
			complete2LevelComplete = true;
			MainClass.ac[8] = true;
			setAchievementText(complete4LevelText);
			setAchievementImage(complete4LevelImage);
			setShowAchievement(true);
		}
	}
}

public void addComplete5LevelCount()
{
	if (!complete5LevelComplete) {
		complete5LevelCount++;
		if (complete5LevelCount>= A_COMPLETE5LEVEL) {
			complete5LevelComplete = true;
			MainClass.ac[9] = true;
			setAchievementText(complete5LevelText);
			setAchievementImage(complete5LevelImage);
			setShowAchievement(true);
		}
	}
}

public boolean isShowAchievement() {
	return showAchievement;
}

public void setShowAchievement(boolean showAchievement) {
	this.showAchievement = showAchievement;
}

public int getAchievementCount() {
	return achievementCount;
}

public void setAchievementCount(int achievementCount) {
	this.achievementCount = achievementCount;
}

public int getShowAchievementCooldown() {
	return showAchievementCooldown;
}

public String getAchievementText() {
	return achievementText;
}

public void setAchievementText(String achievementText) {
	this.achievementText = achievementText;
}



public boolean isJumpCount10Complete() {
	return jumpCount10Complete;
}

public boolean isJumpCount25Complete() {
	return jumpCount25Complete;
}

public boolean isCoinCount20Complete() {
	return coinCount20Complete;
}

public boolean isCoinCount50Complete() {
	return coinCount50Complete;
}

public boolean isPowerupCount3Complete() {
	return powerupCount3Complete;
}

public boolean isComplete1LevelComplete() {
	return complete1LevelComplete;
}

public boolean isComplete2LevelComplete() {
	return complete2LevelComplete;
}

public boolean isComplete3LevelComplete() {
	return complete3LevelComplete;
}

public boolean isComplete4LevelComplete() {
	return complete4LevelComplete;
}

public boolean isComplete5LevelComplete() {
	return complete5LevelComplete;
}





public String getJump10Text() {
	return jump10Text;
}

public String getJump25Text() {
	return jump25Text;
}

public String getCoin20Text() {
	return coin20Text;
}

public String getCoin50Text() {
	return coin50Text;
}

public String getPowerup3Text() {
	return powerup3Text;
}

public String getComplete1LevelText() {
	return complete1LevelText;
}

public String getComplete2LevelText() {
	return complete2LevelText;
}

public String getComplete3LevelText() {
	return complete3LevelText;
}

public String getComplete4LevelText() {
	return complete4LevelText;
}

public String getComplete5LevelText() {
	return complete5LevelText;
}





public BufferedImage getAchievementImage() {
	return achievementImage;
}

public void setAchievementImage(BufferedImage achievementImage) {
	this.achievementImage = achievementImage;
}




public void setJumpCount10Complete(boolean jumpCount10Complete) {
	this.jumpCount10Complete = jumpCount10Complete;
}

public void setJumpCount25Complete(boolean jumpCount25Complete) {
	this.jumpCount25Complete = jumpCount25Complete;
}

public void setCoinCount20Complete(boolean coinCount20Complete) {
	this.coinCount20Complete = coinCount20Complete;
}

public void setCoinCount50Complete(boolean coinCount50Complete) {
	this.coinCount50Complete = coinCount50Complete;
}

public void setPowerupCount3Complete(boolean powerupCount3Complete) {
	this.powerupCount3Complete = powerupCount3Complete;
}

public void setComplete1LevelComplete(boolean complete1LevelComplete) {
	this.complete1LevelComplete = complete1LevelComplete;
}

public void setComplete2LevelComplete(boolean complete2LevelComplete) {
	this.complete2LevelComplete = complete2LevelComplete;
}

public void setComplete3LevelComplete(boolean complete3LevelComplete) {
	this.complete3LevelComplete = complete3LevelComplete;
}

public void setComplete4LevelComplete(boolean complete4LevelComplete) {
	this.complete4LevelComplete = complete4LevelComplete;
}

public void setComplete5LevelComplete(boolean complete5LevelComplete) {
	this.complete5LevelComplete = complete5LevelComplete;
}





public BufferedImage getJump10Image() {
	return jump10Image;
}

public BufferedImage getJump25Image() {
	return jump25Image;
}

public BufferedImage getCoin20Image() {
	return coin20Image;
}

public BufferedImage getCoin50Image() {
	return coin50Image;
}

public BufferedImage getPowerup3Image() {
	return powerup3Image;
}

public BufferedImage getComplete1LevelImage() {
	return complete1LevelImage;
}

public BufferedImage getComplete2LevelImage() {
	return complete2LevelImage;
}

public BufferedImage getComplete3LevelImage() {
	return complete3LevelImage;
}

public BufferedImage getComplete4LevelImage() {
	return complete4LevelImage;
}

public BufferedImage getComplete5LevelImage() {
	return complete5LevelImage;
}
}