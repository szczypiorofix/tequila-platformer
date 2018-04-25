package com.platformer.game.main;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.platformer.game.graphics.Textures;


/** Klasa Achievements, w obiektcie której przechowywane są Osiągnięcia.
 * @author Piotrek
 *
 */
public class Achievements {

	private HashMap<Integer, Boolean> achievementsList;
	public static final int maxAchievements = 24;
	private final int showAchievementCooldown = 210;
	private boolean showAchievement = false;
	private int achievementCount;
	private String achievementText;
	private String achievementTextShort;
	private BufferedImage achievementImage;


	private int jump10Count;
	private static final int A_10JUMPS = 10;
	private boolean jumpCount10Complete = false;
	private final String jump10Text = "Wykonałeś 10 skoków w ciągu jednego poziomu.";
	private final String jump10TextShort = "10 skoków - pchła.";


	private int jump25Count;
	private static final int A_25JUMPS = 25;
	private boolean jumpCount25Complete = false;
	private final String jump25Text = "Wykonałeś 25 skoków w ciągu jednego poziomu.";
	private final String jump25TextShort = "25 skoków - żaba.";


	private int jump50Count;
	private static final int A_50JUMPS = 50;
	private boolean jumpCount50Complete = false;
	private final String jump50Text = "Wykonałeś 50 skoków w ciągu jednego poziomu.";
	private final String jump50TextShort = "50 skoków - kangur.";


	private int coin20Count;
	private static final int A_20COINS = 20;
	private boolean coinCount20Complete = false;
	private final String coin20Text = "Zebrałeś 20 monet w ciągu jednego poziomu.";
	private final String coin20TextShort = "Zbieracz monet - 20.";


	private int coin50Count;
	private static final int A_50COINS = 50;
	private boolean coinCount50Complete = false;
	private final String coin50Text = "Zebrałeś 50 monet w ciągu jednego poziomu.";
	private final String coin50TextShort = "Zbieracz monet - 50.";


	private int coin100Count;
	private static final int A_100COINS = 100;
	private boolean coinCount100Complete = false;
	private final String coin100Text = "Zebrałeś 100 monet w ciągu jednego poziomu.";
	private final String coin100TextShort = "Zbieracz monet - 100.";

	private int coin150Count;
	private static final int A_150COINS = 150;
	private boolean coinCount150Complete = false;
	private final String coin150Text = "Zebrałeś 150 monet w ciągu jednego poziomu.";
	private final String coin150TextShort = "Zbieracz monet - 150.";

	private int powerup3Count;
	private static final int A_3POWERUP = 3;
	private boolean powerupCount3Complete = false;
	private final String powerup3Text = "Zebrałeś 3 power-upy w ciągu jednego poziomu.";
	private final String powerup3TextShort = "Naładowany - 3 powerupy.";

	private int complete1LevelCount;
	private static final int A_COMPLETE1LEVEL = 1;
	private boolean complete1LevelComplete = false;
	private final String complete1LevelText = "Ukończyłeś 1 poziom.";
	private final String complete1LevelTextShort = "1 poziom.";

	private int complete2LevelCount;
	private static final int A_COMPLETE2LEVEL = 1;
	private boolean complete2LevelComplete = false;
	private final String complete2LevelText = "Ukończyłeś 2 poziom.";
	private final String complete2LevelTextShort = "2 poziom.";

	private int complete3LevelCount;
	private static final int A_COMPLETE3LEVEL = 1;
	private boolean complete3LevelComplete = false;
	private final String complete3LevelText = "Ukończyłeś 3 poziom.";
	private final String complete3LevelTextShort = "3 poziom.";

	private int complete4LevelCount;
	private static final int A_COMPLETE4LEVEL = 1;
	private boolean complete4LevelComplete = false;
	private final String complete4LevelText = "Ukończyłeś 4 poziom.";
	private final String complete4LevelTextShort = "4 poziom.";

	private int complete5LevelCount;
	private static final int A_COMPLETE5LEVEL = 1;
	private boolean complete5LevelComplete = false;
	private final String complete5LevelText = "Ukończyłeś 5 poziom.";
	private final String complete5LevelTextShort = "5 poziom.";

	private int complete6LevelCount;
	private static final int A_COMPLETE6LEVEL = 1;
	private boolean complete6LevelComplete = false;
	private final String complete6LevelText = "Ukończyłeś 6 poziom.";
	private final String complete6LevelTextShort = "6 poziom.";

	private int complete7LevelCount;
	private static final int A_COMPLETE7LEVEL = 1;
	private boolean complete7LevelComplete = false;
	private final String complete7LevelText = "Ukończyłeś 7 poziom.";
	private final String complete7LevelTextShort = "7 poziom.";

	private int complete8LevelCount;
	private static final int A_COMPLETE8LEVEL = 1;
	private boolean complete8LevelComplete = false;
	private final String complete8LevelText = "Ukończyłeś 8 poziom.";
	private final String complete8LevelTextShort = "8 poziom.";

	private int complete9LevelCount;
	private static final int A_COMPLETE9LEVEL = 1;
	private boolean complete9LevelComplete = false;
	private final String complete9LevelText = "Ukończyłeś 9 poziom.";
	private final String complete9LevelTextShort = "9 poziom.";

	private int complete10LevelCount;
	private static final int A_COMPLETE10LEVEL = 1;
	private boolean complete10LevelComplete = false;
	private final String complete10LevelText = "Ukończyłeś 10 poziom.";
	private final String complete10LevelTextShort = "10 poziom.";

	private int completeGameCount;
	private static final int A_COMPLETEGAME = 1;
	private boolean completeGameComplete = false;
	private final String completeGameText = "Odnalazłeś Matyldę i ukończyłeś grę.";
	private final String completeGameTextShort = "Koniec gry.";

	private int findAllCoinsCount;
	private static final int A_FINDALLCOINS = 1;
	private boolean findAllCoinsComplete = false;
	private final String findAllCoinsText = "Znalazłeś wszystkie monety na danym poziomie.";
	private final String findAllCoinsTextShort = "Zbieracz monet ... wszystkich!";

	private int findAllPowerupsCount;
	private static final int A_FINDALLPOWERUPS = 1;
	private boolean findAllPowerupsComplete = false;
	private final String findAllPowerupsText = "Znalazłeś wszystkie powerupy na danym poziomie.";
	private final String findAllPowerupsTextShort = "Na wysokich obrotach!";

	private int noHarmCount;
	private static final int A_NOHARM = 1;
	private boolean noHarmComplete = false;
	private final String noHarmText = "Ukończyłeś poziom bez utraty zdrowia.";
	private final String noHarmTextShort = "Bez utraty zdrowia.";

	private int megaJumpCount;
	private static final int A_MEGAJUMP = 1;
	private boolean megaJumpComplete = false;
	private final String megaJumpText = "Wykonałeś MEGA SKOK !";
	private final String megaJumpTextShort = "MEGA SKOK !";

	private int sprinterCount;
	private static final int A_SPRINTER = 1;
	private boolean sprinterComplete = false;
	private final String sprinterText = "Ukończyłeś poziom w czasie krótszym niż 1 minuta.";
	private final String sprinterTextShort = "Szybciej, szybciej...";


	public Achievements(HashMap<Integer, Boolean> achievementsList) {
		this.achievementsList = achievementsList;

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
		complete6LevelCount = 0;
		complete7LevelCount = 0;
		complete8LevelCount = 0;
		complete9LevelCount = 0;
		complete10LevelCount = 0;
		completeGameCount = 0;
		findAllCoinsCount = 0;
		findAllPowerupsCount = 0;
		noHarmCount = 0;
		megaJumpCount = 0;
		sprinterCount = 0;
		achievementText = "";
		achievementImage = null;
	}


	public void restartLevel() {
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
		completeGameCount = 0;
		noHarmCount = 0;
		megaJumpCount = 0;
		sprinterCount = 0;
	}

	public void addJump10Count() {
		if (!jumpCount10Complete) {
			jump10Count++;
			if (jump10Count>= A_10JUMPS) {
				jumpCount10Complete = true;
				achievementsList.put(0, true);
				setAchievementText(jump10Text);
				setAchievementTextShort(jump10TextShort);
				setAchievementImage(Textures.getInstance().jump10Image);
				setShowAchievement(true);
			}
		}
	}

	public void addJump25Count() {
		if (!jumpCount25Complete) {
			jump25Count++;
			if (jump25Count>= A_25JUMPS) {
				jumpCount25Complete = true;
				achievementsList.put(1, true);
				setAchievementText(jump25Text);
				setAchievementTextShort(jump25TextShort);
				setAchievementImage(Textures.getInstance().jump25Image);
				setShowAchievement(true);
			}
		}
	}

	public void addJump50Count() {
		if (!jumpCount50Complete) {
			jump50Count++;
			if (jump50Count>= A_50JUMPS) {
				jumpCount50Complete = true;
				achievementsList.put(2, true);
				setAchievementText(jump50Text);
				setAchievementTextShort(jump50TextShort);
				setAchievementImage(Textures.getInstance().jump50Image);
				setShowAchievement(true);
			}
		}
	}

	public void addCoin20Count() {
		if (!coinCount20Complete) {
			coin20Count++;
			if (coin20Count>= A_20COINS) {
				coinCount20Complete = true;
				achievementsList.put(3, true);
				setAchievementText(coin20Text);
				setAchievementTextShort(coin20TextShort);
				setAchievementImage(Textures.getInstance().coin20Image);
				setShowAchievement(true);
			}
		}
	}

	public void addCoin50Count() {
		if (!coinCount50Complete) {
			coin50Count++;
			if (coin50Count>= A_50COINS) {
				coinCount50Complete = true;
				achievementsList.put(4, true);
				setAchievementText(coin50Text);
				setAchievementTextShort(coin50TextShort);
				setAchievementImage(Textures.getInstance().coin50Image);
				setShowAchievement(true);
			}
		}
	}

	public void addCoin100Count() {
		if (!coinCount100Complete) {
			coin100Count++;
			if (coin100Count>= A_100COINS) {
				coinCount100Complete = true;
				achievementsList.put(5, true);
				setAchievementText(coin100Text);
				setAchievementTextShort(coin100TextShort);
				setAchievementImage(Textures.getInstance().coin100Image);
				setShowAchievement(true);
			}
		}
	}

	public void addCoin150Count() {
		if (!coinCount150Complete) {
			coin150Count++;
			if (coin150Count>= A_150COINS) {
				coinCount150Complete = true;
				achievementsList.put(6, true);
				setAchievementText(coin150Text);
				setAchievementTextShort(coin150TextShort);
				setAchievementImage(Textures.getInstance().coin150Image);
				setShowAchievement(true);
			}
		}
	}


	public void addPowerup3Count() {
		if (!powerupCount3Complete) {
			powerup3Count++;
			if (powerup3Count>= A_3POWERUP) {
				powerupCount3Complete = true;
				achievementsList.put(7, true);
				setAchievementText(powerup3Text);
				setAchievementTextShort(powerup3TextShort);
				setAchievementImage(Textures.getInstance().powerup3Image);
				setShowAchievement(true);
			}
		}
	}

	public void addComplete1LevelCount() {
		if (!complete1LevelComplete) {
			complete1LevelCount++;
			if (complete1LevelCount>= A_COMPLETE1LEVEL) {
				complete1LevelComplete = true;
				achievementsList.put(8, true);
				setAchievementText(complete1LevelText);
				setAchievementTextShort(complete1LevelTextShort);
				setAchievementImage(Textures.getInstance().complete1LevelImage);
				setShowAchievement(true);
			}
		}
	}

	public void addComplete2LevelCount() {
		if (!complete2LevelComplete) {
			complete2LevelCount++;
			if (complete2LevelCount>= A_COMPLETE2LEVEL) {
				complete2LevelComplete = true;
				achievementsList.put(9, true);
				setAchievementText(complete2LevelText);
				setAchievementTextShort(complete2LevelTextShort);
				setAchievementImage(Textures.getInstance().complete2LevelImage);
				setShowAchievement(true);
			}
		}
	}

	public void addComplete3LevelCount() {
		if (!complete3LevelComplete) {
			complete3LevelCount++;
			if (complete3LevelCount>= A_COMPLETE3LEVEL) {
				complete3LevelComplete = true;
				achievementsList.put(10, true);
				setAchievementText(complete3LevelText);
				setAchievementTextShort(complete3LevelTextShort);
				setAchievementImage(Textures.getInstance().complete3LevelImage);
				setShowAchievement(true);
			}
		}
	}

	public void addComplete4LevelCount() {
		if (!complete4LevelComplete) {
			complete4LevelCount++;
			if (complete4LevelCount>= A_COMPLETE4LEVEL) {
				complete2LevelComplete = true;
				achievementsList.put(11, true);
				setAchievementText(complete4LevelText);
				setAchievementTextShort(complete4LevelTextShort);
				setAchievementImage(Textures.getInstance().complete4LevelImage);
				setShowAchievement(true);
			}
		}
	}

	public void addComplete5LevelCount() {
		if (!complete5LevelComplete) {
			complete5LevelCount++;
			if (complete5LevelCount>= A_COMPLETE5LEVEL) {
				complete5LevelComplete = true;
				achievementsList.put(12, true);
				setAchievementText(complete5LevelText);
				setAchievementTextShort(complete5LevelTextShort);
				setAchievementImage(Textures.getInstance().complete5LevelImage);
				setShowAchievement(true);
			}
		}
	}

	public void addComplete6LevelCount() {
		if (!complete6LevelComplete) {
			complete6LevelCount++;
			if (complete6LevelCount>= A_COMPLETE6LEVEL) {
				complete6LevelComplete = true;
				achievementsList.put(13, true);
				setAchievementText(complete6LevelText);
				setAchievementTextShort(complete6LevelTextShort);
				setAchievementImage(Textures.getInstance().complete6LevelImage);
				setShowAchievement(true);
			}
		}
	}

	public void addComplete7LevelCount() {
		if (!complete7LevelComplete) {
			complete7LevelCount++;
			if (complete7LevelCount>= A_COMPLETE7LEVEL) {
				complete7LevelComplete = true;
				achievementsList.put(14, true);
				setAchievementText(complete7LevelText);
				setAchievementTextShort(complete7LevelTextShort);
				setAchievementImage(Textures.getInstance().complete7LevelImage);
				setShowAchievement(true);
			}
		}
	}

	public void addComplete8LevelCount() {
		if (!complete8LevelComplete) {
			complete8LevelCount++;
			if (complete8LevelCount>= A_COMPLETE8LEVEL) {
				complete8LevelComplete = true;
				achievementsList.put(15, true);
				setAchievementText(complete8LevelText);
				setAchievementTextShort(complete8LevelTextShort);
				setAchievementImage(Textures.getInstance().complete8LevelImage);
				setShowAchievement(true);
			}
		}
	}

	public void addComplete9LevelCount() {
		if (!complete9LevelComplete) {
			complete9LevelCount++;
			if (complete9LevelCount>= A_COMPLETE9LEVEL) {
				complete9LevelComplete = true;
				achievementsList.put(16, true);
				setAchievementText(complete9LevelText);
				setAchievementTextShort(complete9LevelTextShort);
				setAchievementImage(Textures.getInstance().complete9LevelImage);
				setShowAchievement(true);
			}
		}
	}

	public void addComplete10LevelCount() {
		if (!complete10LevelComplete) {
			complete10LevelCount++;
			if (complete10LevelCount>= A_COMPLETE10LEVEL) {
				complete10LevelComplete = true;
				achievementsList.put(17, true);
				setAchievementText(complete10LevelText);
				setAchievementTextShort(complete10LevelTextShort);
				setAchievementImage(Textures.getInstance().complete10LevelImage);
				setShowAchievement(true);
			}
		}
	}

	public void addCompleteGameCount() {
		if (!completeGameComplete) {
			completeGameCount++;
			if (completeGameCount>= A_COMPLETEGAME) {
				completeGameComplete = true;
				achievementsList.put(18, true);
				setAchievementText(completeGameText);
				setAchievementTextShort(completeGameTextShort);
				setAchievementImage(Textures.getInstance().completeGameImage);
				setShowAchievement(true);
			}
		}
	}

	public void addFindAllCoinsCount() {
		if (!findAllCoinsComplete) {
			findAllCoinsCount++;
			if (findAllCoinsCount>= A_FINDALLCOINS) {
				findAllCoinsComplete = true;
				achievementsList.put(19, true);
				setAchievementText(findAllCoinsText);
				setAchievementTextShort(findAllCoinsTextShort);
				setAchievementImage(Textures.getInstance().findAllCoinsImage);
				setShowAchievement(true);
			}
		}
	}

	public void addFindAllPowerupsCount() {
		if (!findAllPowerupsComplete) {
			findAllPowerupsCount++;
			if (findAllPowerupsCount>= A_FINDALLPOWERUPS) {
				findAllPowerupsComplete = true;
				achievementsList.put(20, true);
				setAchievementText(findAllPowerupsText);
				setAchievementTextShort(findAllPowerupsTextShort);
				setAchievementImage(Textures.getInstance().findAllPowerupsImage);
				setShowAchievement(true);
			}
		}
	}

	public void addNoHarmCount() {
		if (!noHarmComplete) {
			noHarmCount++;
			if (noHarmCount>= A_NOHARM) {
				noHarmComplete = true;
				achievementsList.put(21, true);
				setAchievementText(noHarmText);
				setAchievementTextShort(noHarmTextShort);
				setAchievementImage(Textures.getInstance().noHarmImage);
				setShowAchievement(true);
			}
		}
	}


	public void addMegaJumpCount() {
		if (!megaJumpComplete) {
			megaJumpCount++;
			if (megaJumpCount>= A_MEGAJUMP) {
				megaJumpComplete = true;
				achievementsList.put(22, true);
				setAchievementText(megaJumpText);
				setAchievementTextShort(megaJumpTextShort);
				setAchievementImage(Textures.getInstance().megaJumpImage);
				setShowAchievement(true);
			}
		}
	}


	public void addSprinterCount() {
		if (!sprinterComplete) {
			sprinterCount++;
			if (sprinterCount>= A_SPRINTER) {
				sprinterComplete = true;
				achievementsList.put(23, true);
				setAchievementText(sprinterText);
				setAchievementTextShort(sprinterTextShort);
				setAchievementImage(Textures.getInstance().sprinterImage);
				setShowAchievement(true);
			}
		}
	}


	public HashMap<Integer, Boolean> getAchievementsList() {
		return achievementsList;
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

	public int getAchievementsUnlocked() {
		int unlocked = 0;
		for (int i = 0; i < achievementsList.size(); i++) {
			if (achievementsList.get(i)) unlocked++;
		}
		return unlocked;
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

	public boolean isComplete6LevelComplete() {
		return complete6LevelComplete;
	}

	public boolean isComplete7LevelComplete() {
		return complete7LevelComplete;
	}

	public boolean isComplete8LevelComplete() {
		return complete8LevelComplete;
	}

	public boolean isComplete9LevelComplete() {
		return complete9LevelComplete;
	}

	public boolean isComplete10LevelComplete() {
		return complete10LevelComplete;
	}

	public boolean isCompleteGameComplete() {
		return completeGameComplete;
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

	public boolean isMegaJumpComplete() {
		return megaJumpComplete;
	}

	public boolean isSprinterComplete() {
		return sprinterComplete;
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

	public String getComplete6LevelText() {
		return complete6LevelText;
	}

	public String getComplete6LevelTextShort() {
		return complete6LevelTextShort;
	}

	public String getComplete7LevelText() {
		return complete7LevelText;
	}

	public String getComplete7LevelTextShort() {
		return complete7LevelTextShort;
	}

	public String getComplete8LevelText() {
		return complete8LevelText;
	}

	public String getComplete8LevelTextShort() {
		return complete8LevelTextShort;
	}

	public String getComplete9LevelText() {
		return complete9LevelText;
	}

	public String getComplete9LevelTextShort() {
		return complete9LevelTextShort;
	}

	public String getComplete10LevelText() {
		return complete10LevelText;
	}

	public String getComplete10LevelTextShort() {
		return complete10LevelTextShort;
	}

	public String getCompleteGameText() {
		return completeGameText;
	}

	public String getCompleteGameTextShort() {
		return completeGameTextShort;
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

	public String getMegaJumpText() {
		return megaJumpText;
	}

	public String getMegaJumpTextShort() {
		return megaJumpTextShort;
	}

	public String getSprinterText() {
		return sprinterText;
	}

	public String getSprinterTextShort() {
		return sprinterTextShort;
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

	public void setComplete6LevelComplete(boolean complete6LevelComplete) {
		this.complete6LevelComplete = complete6LevelComplete;
	}

	public void setComplete7LevelComplete(boolean complete7LevelComplete) {
		this.complete7LevelComplete = complete7LevelComplete;
	}

	public void setComplete8LevelComplete(boolean complete8LevelComplete) {
		this.complete8LevelComplete = complete8LevelComplete;
	}

	public void setComplete9LevelComplete(boolean complete9LevelComplete) {
		this.complete9LevelComplete = complete9LevelComplete;
	}

	public void setComplete10LevelComplete(boolean complete10LevelComplete) {
		this.complete10LevelComplete = complete10LevelComplete;
	}

	public void setCompleteGameComplete(boolean completeGameComplete) {
		this.completeGameComplete = completeGameComplete;
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

	public void setMegaJumpComplete(boolean megaJumpComplete) {
		this.megaJumpComplete = megaJumpComplete;
	}

	public void setSprinterComplete(boolean sprinterComplete) {
		this.sprinterComplete = sprinterComplete;
	}
}
