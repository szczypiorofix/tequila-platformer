package com.platformer.game.main;


public class Achievements {


private final int showAchievementCooldown = 100;
private boolean showAchievement = false;
private int achievementCount;
private String achievementsText = "";


private int jump10Count;
private static final int A_10JUMPS = 10;
private boolean jumpCount10Complete = false;

private int jump25Count;
private static final int A_25JUMPS = A_10JUMPS + 15;
private boolean jumpCount25Complete = false;

private int coin20Count;
private static final int A_20COINS = 20;
private boolean coinCount20Complete = false;

private int coin50Count;
private static final int A_50COINS = 30;   // 50 - A_20COINS
private boolean coinCount50Complete = false;

private int powerup3Count;
private static final int A_3POWERUP = 3;
private boolean powerupCount3Complete = false;


	
public Achievements()
{
	achievementCount = showAchievementCooldown;
	jump10Count = 0;
	jump25Count = 0;
	coin20Count = 0;
	coin50Count = 0;
	powerup3Count = 0;
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
			setAchievementsText("Wykona³eœ 10 skoków !!!");
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
			setAchievementsText("Wykona³eœ 25 skoków !!!");
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
			setAchievementsText("Zebra³eœ 20 monet !!!");
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
			setAchievementsText("Zebra³eœ 50 monet !!!");
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
			setAchievementsText("Zebra³eœ 3 power-upy !!!");
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

public String getAchievementsText() {
	return achievementsText;
}

public void setAchievementsText(String achievementsText) {
	this.achievementsText = achievementsText;
}


// ACHIEVEMENTS STATE
public boolean isJumpCount10Complete() {
	return jumpCount10Complete;
}

public boolean isJumpCount25Complete() {
	return jumpCount25Complete;
}

public boolean isCoinCount20Complete() {
	return coinCount20Complete;
}

public boolean isPowerupCount3Complete() {
	return powerupCount3Complete;
}
}