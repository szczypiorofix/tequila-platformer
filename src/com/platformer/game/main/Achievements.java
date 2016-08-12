package com.platformer.game.main;

import java.awt.image.BufferedImage;

import com.platformer.game.graphics.BufferedImageLoader;


public class Achievements {



public static final int maxAchievements = 5;
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



public Achievements()
{
	loader = new BufferedImageLoader();
	jump10Image = loader.loadImage("/A10jumps.png");;
	jump25Image = loader.loadImage("/A25jumps.png");;
	coin20Image = loader.loadImage("/A20coins.png");;
	coin50Image = loader.loadImage("/A50coins.png");;
	powerup3Image = loader.loadImage("/A3powerups.png");;
	
	achievementCount = showAchievementCooldown;
	jump10Count = 0;
	jump25Count = 0;
	coin20Count = 0;
	coin50Count = 0;
	powerup3Count = 0;
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
}