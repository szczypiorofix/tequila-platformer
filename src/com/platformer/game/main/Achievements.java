package com.platformer.game.main;

import java.awt.image.BufferedImage;

import com.platformer.game.graphics.BufferedImageLoader;


public class Achievements {


public static final int maxAchievements = 16;
private final int showAchievementCooldown = 200;
private boolean showAchievement = false;
private int achievementCount;
private String achievementText;
private String achievementTextShort;
private BufferedImage achievementImage;


private BufferedImageLoader loader;

private int jump10Count;
private static final int A_10JUMPS = 10;
private boolean jumpCount10Complete = false;
private final String jump10Text = "Wykona³eœ 10 skoków w ci¹gu jednego poziomu";
private final String jump10TextShort = "10 skoków - pch³a";
private final BufferedImage jump10Image;

private int jump25Count;
private static final int A_25JUMPS = 25;
private boolean jumpCount25Complete = false;
private final String jump25Text = "Wykona³eœ 25 skoków w ci¹gu jednego poziomu";
private final String jump25TextShort = "25 skoków - ¿aba";
private final BufferedImage jump25Image;

private int jump50Count;
private static final int A_50JUMPS = 50;
private boolean jumpCount50Complete = false;
private final String jump50Text = "Wykona³eœ 50 skoków w ci¹gu jednego poziomu";
private final String jump50TextShort = "50 skoków - kangur";
private final BufferedImage jump50Image;

private int coin20Count;
private static final int A_20COINS = 20;
private boolean coinCount20Complete = false;
private final String coin20Text = "Zebra³eœ 20 monet w ci¹gu jednego poziomu";
private final String coin20TextShort = "Zbieracz monet - 20";
private final BufferedImage coin20Image;

private int coin50Count;
private static final int A_50COINS = 50;
private boolean coinCount50Complete = false;
private final String coin50Text = "Zebra³eœ 50 monet w ci¹gu jednego poziomu";
private final String coin50TextShort = "Zbieracz monet - 50";
private final BufferedImage coin50Image;

private int coin100Count;
private static final int A_100COINS = 100;
private boolean coinCount100Complete = false;
private final String coin100Text = "Zebra³eœ 100 monet w ci¹gu jednego poziomu";
private final String coin100TextShort = "Zbieracz monet - 100";
private final BufferedImage coin100Image;

private int coin150Count;
private static final int A_150COINS = 150;
private boolean coinCount150Complete = false;
private final String coin150Text = "Zebra³eœ 150 monet w ci¹gu jednego poziomu";
private final String coin150TextShort = "Zbieracz monet - 150";
private final BufferedImage coin150Image;

private int powerup3Count;
private static final int A_3POWERUP = 3;
private boolean powerupCount3Complete = false;
private final String powerup3Text = "Zebra³eœ 3 power-upy w ci¹gu jednego poziomu";
private final String powerup3TextShort = "Na³adowany - 3 powerupy";
private final BufferedImage powerup3Image;

private int complete1LevelCount;
private static final int A_COMPLETE1LEVEL = 1;
private boolean complete1LevelComplete = false;
private final String complete1LevelText = "Ukoñczy³eœ 1 poziom";
private final String complete1LevelTextShort = "Ukoñczy³eœ 1 poziom";
private final BufferedImage complete1LevelImage;

private int complete2LevelCount;
private static final int A_COMPLETE2LEVEL = 1;
private boolean complete2LevelComplete = false;
private final String complete2LevelText = "Ukoñczy³eœ 2 poziom";
private final String complete2LevelTextShort = "Ukoñczy³eœ 2 poziom";
private final BufferedImage complete2LevelImage;

private int complete3LevelCount;
private static final int A_COMPLETE3LEVEL = 1;
private boolean complete3LevelComplete = false;
private final String complete3LevelText = "Ukoñczy³eœ 3 poziom";
private final String complete3LevelTextShort = "Ukoñczy³eœ 3 poziom";
private final BufferedImage complete3LevelImage;

private int complete4LevelCount;
private static final int A_COMPLETE4LEVEL = 1;
private boolean complete4LevelComplete = false;
private final String complete4LevelText = "Ukoñczy³eœ 4 poziom";
private final String complete4LevelTextShort = "Ukoñczy³eœ 4 poziom";
private final BufferedImage complete4LevelImage;

private int complete5LevelCount;
private static final int A_COMPLETE5LEVEL = 1;
private boolean complete5LevelComplete = false;
private final String complete5LevelText = "Ukoñczy³eœ 5 poziom";
private final String complete5LevelTextShort = "Ukoñczy³eœ 5 poziom";
private final BufferedImage complete5LevelImage;

private int findAllCoinsCount;
private static final int A_FINDALLCOINS = 1;
private boolean findAllCoinsComplete = false;
private final String findAllCoinsText = "Znalaz³eœ wszystkie monety na danym poziomie";
private final String findAllCoinsTextShort = "Zbieracz monet ... wszystkich!";
private final BufferedImage findAllCoinsImage;

private int findAllPowerupsCount;
private static final int A_FINDALLPOWERUPS = 1;
private boolean findAllPowerupsComplete = false;
private final String findAllPowerupsText = "Znalaz³eœ wszystkie powerupy na danym poziomie";
private final String findAllPowerupsTextShort = "Na wysokich obrotach!";
private final BufferedImage findAllPowerupsImage;

private int noHarmCount;
private static final int A_NOHARM = 1;
private boolean noHarmComplete = false;
private final String noHarmText = "Ukoñczy³eœ poziom bez utraty zdrowia";
private final String noHarmTextShort = "Bez utraty zdrowia";
private final BufferedImage noHarmImage;


public Achievements()
{
	loader = new BufferedImageLoader();
	jump10Image = loader.loadImage("/A10jumps.png");
	jump25Image = loader.loadImage("/A25jumps.png");
	jump50Image = loader.loadImage("/A50jumps.png");
	coin20Image = loader.loadImage("/A20coins.png");
	coin50Image = loader.loadImage("/A50coins.png");
	coin100Image = loader.loadImage("/A100coins.png");
	coin150Image = loader.loadImage("/A150coins.png");
	powerup3Image = loader.loadImage("/A3powerups.png");
	complete1LevelImage = loader.loadImage("/Acomplete1Level.png");
	complete2LevelImage = loader.loadImage("/Acomplete2Level.png");
	complete3LevelImage = loader.loadImage("/Acomplete3Level.png");
	complete4LevelImage = loader.loadImage("/Acomplete4Level.png");
	complete5LevelImage = loader.loadImage("/Acomplete5Level.png");
	findAllCoinsImage = loader.loadImage("/AfindAllCoins.png");
	findAllPowerupsImage = loader.loadImage("/AfindAllPowerup.png");
	noHarmImage = loader.loadImage("/AnoHarm.png");
	
	achievementCount = showAchievementCooldown;
	jump10Count = 0;
	jump25Count = 0;
	jump50Count = 0;
	coin20Count = 0;
	coin50Count = 0;
	coin100Count = 0;
	coin150Count = 0;
	powerup3Count = 0;
	complete1LevelCount = 0;
	complete2LevelCount = 0;
	complete3LevelCount = 0;
	complete4LevelCount = 0;
	complete5LevelCount = 0;
	findAllCoinsCount = 0;
	findAllPowerupsCount = 0;
	noHarmCount = 0;
	achievementText = "";
	achievementImage = null;
}

public void restartLevel()
{
	jump10Count = 0;
	jump25Count = 0;
	jump50Count = 0;
	coin20Count = 0;
	coin50Count = 0;
	coin100Count = 0;
	coin150Count = 0;
	powerup3Count = 0;
	findAllCoinsCount = 0;
	findAllPowerupsCount = 0;
	noHarmCount = 0;
}

public void addJump10Count()
{
	if (!jumpCount10Complete) {
		jump10Count++;
		if (jump10Count>= A_10JUMPS) {
			jumpCount10Complete = true;
			MainClass.ac.put(0, true);
			setAchievementText(jump10Text);
			setAchievementTextShort(jump10TextShort);
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
			MainClass.ac.put(1, true);
			setAchievementText(jump25Text);
			setAchievementTextShort(jump25TextShort);
			setAchievementImage(jump25Image);
			setShowAchievement(true);
		}
	}
}

public void addJump50Count()
{
	if (!jumpCount50Complete) {
		jump50Count++;
		if (jump50Count>= A_50JUMPS) {
			jumpCount50Complete = true;
			MainClass.ac.put(2, true);
			setAchievementText(jump50Text);
			setAchievementTextShort(jump50TextShort);
			setAchievementImage(jump50Image);
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
			MainClass.ac.put(3, true);
			setAchievementText(coin20Text);
			setAchievementTextShort(coin20TextShort);
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
			MainClass.ac.put(4, true);
			setAchievementText(coin50Text);
			setAchievementTextShort(coin50TextShort);
			setAchievementImage(coin50Image);
			setShowAchievement(true);
		}
	}
}

public void addCoin100Count()
{
	if (!coinCount100Complete) {
		coin100Count++;
		if (coin100Count>= A_100COINS) {
			coinCount100Complete = true;
			MainClass.ac.put(5, true);
			setAchievementText(coin100Text);
			setAchievementTextShort(coin100TextShort);
			setAchievementImage(coin100Image);
			setShowAchievement(true);
		}
	}
}

public void addCoin150Count()
{
	if (!coinCount150Complete) {
		coin150Count++;
		if (coin150Count>= A_150COINS) {
			coinCount150Complete = true;
			MainClass.ac.put(6, true);
			setAchievementText(coin150Text);
			setAchievementTextShort(coin150TextShort);
			setAchievementImage(coin150Image);
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
			MainClass.ac.put(7, true);
			setAchievementText(powerup3Text);
			setAchievementTextShort(powerup3TextShort);
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
			MainClass.ac.put(8, true);
			setAchievementText(complete1LevelText);
			setAchievementTextShort(complete1LevelTextShort);
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
			MainClass.ac.put(9, true);
			setAchievementText(complete2LevelText);
			setAchievementTextShort(complete2LevelTextShort);
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
			MainClass.ac.put(10, true);
			setAchievementText(complete3LevelText);
			setAchievementTextShort(complete3LevelTextShort);
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
			MainClass.ac.put(11, true);
			setAchievementText(complete4LevelText);
			setAchievementTextShort(complete4LevelTextShort);
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
			MainClass.ac.put(12, true);
			setAchievementText(complete5LevelText);
			setAchievementTextShort(complete5LevelTextShort);
			setAchievementImage(complete5LevelImage);
			setShowAchievement(true);
		}
	}
}

public void addFindAllCoinsCount()
{
	if (!findAllCoinsComplete) {
		findAllCoinsCount++;
		if (findAllCoinsCount>= A_FINDALLCOINS) {
			findAllCoinsComplete = true;
			MainClass.ac.put(13, true);
			setAchievementText(findAllCoinsText);
			setAchievementTextShort(findAllCoinsTextShort);
			setAchievementImage(findAllCoinsImage);
			setShowAchievement(true);
		}
	}
}

public void addFindAllPowerupsCount()
{
	if (!findAllPowerupsComplete) {
		findAllPowerupsCount++;
		if (findAllPowerupsCount>= A_FINDALLPOWERUPS) {
			findAllPowerupsComplete = true;
			MainClass.ac.put(14, true);
			setAchievementText(findAllPowerupsText);
			setAchievementTextShort(findAllPowerupsTextShort);
			setAchievementImage(findAllPowerupsImage);
			setShowAchievement(true);
		}
	}
}

public void addNoHarmCount()
{
	if (!noHarmComplete) {
		noHarmCount++;
		if (noHarmCount>= A_NOHARM) {
			noHarmComplete = true;
			MainClass.ac.put(15, true);
			setAchievementText(noHarmText);
			setAchievementTextShort(noHarmTextShort);
			setAchievementImage(noHarmImage);
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

public String getAchievementTextShort() {
	return achievementTextShort;
}

public void setAchievementTextShort(String achievementTextShort) {
	this.achievementTextShort = achievementTextShort;
}

public BufferedImage getAchievementImage() {
	return achievementImage;
}

public void setAchievementImage(BufferedImage achievementImage) {
	this.achievementImage = achievementImage;
}







public boolean isJumpCount10Complete() {
	return jumpCount10Complete;
}

public boolean isJumpCount25Complete() {
	return jumpCount25Complete;
}

public boolean isJumpCount50Complete() {
	return jumpCount50Complete;
}

public boolean isCoinCount20Complete() {
	return coinCount20Complete;
}

public boolean isCoinCount50Complete() {
	return coinCount50Complete;
}

public boolean isCoinCount100Complete() {
	return coinCount100Complete;
}

public boolean isCoinCount150Complete() {
	return coinCount150Complete;
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

public boolean isFindAllCoinsComplete() {
	return findAllCoinsComplete;
}

public boolean isFindAllPowerupsComplete() {
	return findAllPowerupsComplete;
}

public boolean isNoHarmComplete() {
	return noHarmComplete;
}





public String getJump10Text() {
	return jump10Text;
}

public String getJump10TextShort() {
	return jump10TextShort;
}

public String getJump25Text() {
	return jump25Text;
}

public String getJump25TextShort() {
	return jump25TextShort;
}

public String getJump50Text() {
	return jump50Text;
}

public String getJump50TextShort() {
	return jump50TextShort;
}

public String getCoin20Text() {
	return coin20Text;
}

public String getCoin20TextShort() {
	return coin20TextShort;
}

public String getCoin50Text() {
	return coin50Text;
}

public String getCoin50TextShort() {
	return coin50TextShort;
}

public String getCoin100Text() {
	return coin100Text;
}

public String getCoin100TextShort() {
	return coin100TextShort;
}

public String getCoin150Text() {
	return coin150Text;
}

public String getCoin150TextShort() {
	return coin150TextShort;
}

public String getPowerup3Text() {
	return powerup3Text;
}

public String getPowerup3TextShort() {
	return powerup3TextShort;
}

public String getComplete1LevelText() {
	return complete1LevelText;
}

public String getComplete1LevelTextShort() {
	return complete1LevelTextShort;
}

public String getComplete2LevelText() {
	return complete2LevelText;
}

public String getComplete2LevelTextShort() {
	return complete2LevelTextShort;
}

public String getComplete3LevelText() {
	return complete3LevelText;
}

public String getComplete3LevelTextShort() {
	return complete3LevelTextShort;
}

public String getComplete4LevelText() {
	return complete4LevelText;
}

public String getComplete4LevelTextShort() {
	return complete4LevelTextShort;
}

public String getComplete5LevelText() {
	return complete5LevelText;
}

public String getComplete5LevelTextShort() {
	return complete5LevelTextShort;
}

public String getFindAllCoinsText() {
	return findAllCoinsText;
}

public String getFindAllCoinsTextShort() {
	return findAllCoinsTextShort;
}

public String getFindAllPowerupsText() {
	return findAllPowerupsText;
}

public String getFindAllPowerupsTextShort() {
	return findAllPowerupsTextShort;
}

public String getNoHarmText() {
	return noHarmText;
}

public String getNoHarmTextShort() {
	return noHarmTextShort;
}






public void setJumpCount10Complete(boolean jumpCount10Complete) {
	this.jumpCount10Complete = jumpCount10Complete;
}

public void setJumpCount25Complete(boolean jumpCount25Complete) {
	this.jumpCount25Complete = jumpCount25Complete;
}

public void setJumpCount50Complete(boolean jumpCount50Complete) {
	this.jumpCount50Complete = jumpCount50Complete;
}

public void setCoinCount20Complete(boolean coinCount20Complete) {
	this.coinCount20Complete = coinCount20Complete;
}

public void setCoinCount50Complete(boolean coinCount50Complete) {
	this.coinCount50Complete = coinCount50Complete;
}

public void setCoinCount100Complete(boolean coinCount100Complete) {
	this.coinCount100Complete = coinCount100Complete;
}

public void setCoinCount150Complete(boolean coinCount150Complete) {
	this.coinCount150Complete = coinCount150Complete;
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

public void setFindAllCoinsComplete(boolean findAllCoinsComplete) {
	this.findAllCoinsComplete = findAllCoinsComplete;
}

public void setFindAllPowerupsComplete(boolean findAllPowerupsComplete) {
	this.findAllPowerupsComplete = findAllPowerupsComplete;
}

public void setNoHarmComplete(boolean noHarmComplete) {
	this.noHarmComplete = noHarmComplete;
}





public BufferedImage getJump10Image() {
	return jump10Image;
}

public BufferedImage getJump25Image() {
	return jump25Image;
}

public BufferedImage getJump50Image() {
	return jump50Image;
}

public BufferedImage getCoin20Image() {
	return coin20Image;
}

public BufferedImage getCoin50Image() {
	return coin50Image;
}

public BufferedImage getCoin100Image() {
	return coin100Image;
}

public BufferedImage getCoin150Image() {
	return coin150Image;
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

public BufferedImage getFindAllCoinsImage() {
	return findAllCoinsImage;
}

public BufferedImage getFindAllPowerupsImage() {
	return findAllPowerupsImage;
}

public BufferedImage getNoHarmImage() {
	return noHarmImage;
}
}