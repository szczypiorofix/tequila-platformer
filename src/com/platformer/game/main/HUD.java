package com.platformer.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


import com.platformer.game.graphics.Textures;


/** Klasa wy�wietlaj�ca teksty i dane na ekranie w zale�no�ci od stanu gry.
 * @author Piotrek
 *
 */
public class HUD {


public HUD()
{
}


/** Metoda rysuj�ca na ekranie informacje i dane w zale�no�ci od stanu gry - czyli tzn. HUD.
 * @param g2d - Obiekt Graphics2D.
 * @param gameState - stan gry.
 * @param achievements - obiekt klasy Achievements.
 * @param hallOfFame - obiekt klasy HallOfFame.
 * @param fps_count - (int) ilo�� fps�w.
 * @param ticks_count - (int) ilo�� updat�w logiki gry.
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
	case Death: {
		g2d.drawImage(Textures.getInstance().menuBg, 300, 120, null);
		g2d.setColor(Color.RED);
		g2d.setFont(MainClass.texasFont.deriveFont(68f));
		g2d.drawString("NIE �YJESZ", 420, 240);
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 38f));
		g2d.drawString("SPACJA - RESTART", 405, 320);
		break;
	}
	case NextLevel: {
		g2d.setColor(Color.GRAY);
		g2d.fillRect(300, 100, 400, 450);
		g2d.setColor(Color.YELLOW);
		g2d.drawRect(2999, 99, 402, 342);
		g2d.setFont(MainClass.texasFont.deriveFont(Font.BOLD, 54f));
		g2d.drawString("POZIOM "+MainScreen.LEVEL +" UKO�CZONY !!!", 315, 155);
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 42f));
		g2d.drawString("TW�J WYNIK: " +MainScreen.SCORE, 375, 240);
		g2d.drawString("CZAS: " +MainScreen.time, 380, 300);
		g2d.drawString("BONUS CZASOWY: " + (int) MainScreen.time_bonus, 340, 370);
		g2d.drawString("WYNIK KO�COWY: " +(int) MainScreen.TOTAL_SCORE, 340, 430);
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 26f));
		g2d.setColor(Color.WHITE);
		g2d.drawString("PODAJ SWOJE IMI� I NACI�NIJ ENTER:", 320, 480);
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
		g2d.drawImage(Textures.getInstance().howToPlayImage, 145, 5, null);
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
	
	g2d.setFont(MainClass.arial14Font);
	g2d.setColor(Color.BLACK);
	g2d.drawString("FPS: "+fps_count +" Ticks: "+ ticks_count, MainClass.WIDTH - 160, MainClass.HEIGHT - 10);
}

}