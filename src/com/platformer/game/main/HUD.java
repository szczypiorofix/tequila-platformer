package com.platformer.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


import com.platformer.game.graphics.Textures;


/** Klasa wyświetlająca teksty i dane na ekranie w zależności od stanu gry.
 * @author Piotrek
 *
 */
public class HUD {


public HUD()
{
}


/** Metoda rysująca na ekranie informacje i dane w zależności od stanu gry - czyli tzn. HUD.
 * @param g2d - Obiekt Graphics2D.
 * @param gameState - stan gry.
 * @param achievements - obiekt klasy Achievements.
 * @param hallOfFame - obiekt klasy HallOfFame.
 * @param fps_count - (int) ilość fpsów.
 * @param ticks_count - (int) ilość updatów logiki gry.
 * 
 * @see GameState
 */
public void showGameHud(Graphics2D g2d, GameState gameState, Achievements achievements, HallOfFame hallOfFame, int fps_count, int ticks_count)
{
	switch (gameState)
	{
	case Game: {
		break;
	}
	case Menu: {
		break;
	}
	case Intro: {
		g2d.setFont(MainClass.arial14Font);
		g2d.setColor(Color.BLACK);
		
		if (MainClass.language == MainClass.Languages.polish) g2d.drawString(TextResources.BACK_TO_MAIN_MENU_SKIP_PL, 350, MainClass.HEIGHT - 20);
		if (MainClass.language == MainClass.Languages.english) g2d.drawString(TextResources.BACK_TO_MAIN_MENU_SKIP_ENG, 350, MainClass.HEIGHT - 20);
		break;
	}
	case Death: {
		g2d.drawImage(Textures.getInstance().menuBg, 300, 120, null);
		g2d.setColor(Color.YELLOW);
		g2d.setFont(MainClass.texasFont.deriveFont(68f));
		
		if (MainClass.language == MainClass.Languages.polish) g2d.drawString(TextResources.PLAYER_DEAD_PL, 420, 240);
		if (MainClass.language == MainClass.Languages.english) g2d.drawString(TextResources.PLAYER_DEAD_ENG, 420, 240);
		
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 38f));
		if (MainClass.language == MainClass.Languages.polish) g2d.drawString(TextResources.PLAYER_RESTART_PL, 405, 320);
		if (MainClass.language == MainClass.Languages.english) g2d.drawString(TextResources.PLAYER_RESTART_ENG, 405, 320);
		break;
	}
	case NextLevel: {
		g2d.setColor(Color.GRAY);
		g2d.fillRect(300, 100, 400, 450);
		g2d.setColor(Color.YELLOW);
		g2d.drawRect(2999, 99, 402, 342);
		g2d.setFont(MainClass.texasFont.deriveFont(Font.BOLD, 54f));
		
		if (MainClass.language == MainClass.Languages.polish) g2d.drawString(TextResources.LEVEL_PL +" "+MainScreen.LEVEL +" " +TextResources.FINISHED_PL, 315, 155);
		if (MainClass.language == MainClass.Languages.english) g2d.drawString(TextResources.LEVEL_ENG +" "+MainScreen.LEVEL +" " +TextResources.FINISHED_ENG, 315, 155);
		
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 42f));
		g2d.drawString("TWÓJ WYNIK: " +MainScreen.SCORE, 375, 240);
		g2d.drawString("CZAS: " +MainScreen.time, 380, 300);
		g2d.drawString("BONUS CZASOWY: " + (int) MainScreen.time_bonus, 340, 370);
		g2d.drawString("WYNIK KOŃCOWY: " +(int) MainScreen.TOTAL_SCORE, 340, 430);
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 26f));
		g2d.setColor(Color.WHITE);
		g2d.drawString("PODAJ SWOJE IMIĘ I NACIŚNIJ ENTER:", 320, 480);
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 32f));
		g2d.setColor(Color.CYAN);
		g2d.drawString(MainScreen.playerName , 490-(MainScreen.playerName.length() * 8), 520);
		break;
	}
	case MainMenu: {
		g2d.setFont(MainClass.arial14Font);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Tequila Platformer ver." +MainClass.GAME_VER +" (Build " +MainClass.BUILD +") 2016.", 10, 590);
		break;
	}
	case JakGrac: {
		if (MainClass.language == MainClass.Languages.polish) g2d.drawImage(Textures.getInstance().howToPlayImagePL, 145, 5, null);
		if (MainClass.language == MainClass.Languages.english) g2d.drawImage(Textures.getInstance().howToPlayImageEN, 145, 5, null);
		break;
	}
	case Osiagniecia: {
		break;
	}
	case NajlepszeWyniki: {
		break;
	}
	case Znajdzki: {
		break;
	}
	case OGrze: {
		g2d.drawImage(Textures.getInstance().creditsImage, 255, 15, null);
		break;
	}
	default: {	
	}
	}
	//if (MainClass.DEBUG_MODE) {
		g2d.setFont(MainClass.arial14Font);
		g2d.setColor(Color.BLACK);
		g2d.drawString("FPS: "+fps_count +" Ticks: "+ ticks_count, MainClass.WIDTH - 200, MainClass.HEIGHT - 10);
	//}
}

}