package com.platformer.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


import com.platformer.game.graphics.Textures;


public class HUD {

private Textures tex = MainClass.getTexturesInstance();


public HUD()
{
}

public void showGameHud(Graphics2D g2d, GameState gameState, int fps_count, int ticks_count)
{
	switch (gameState)
	{
	case Game: {
		break;
	}
	case Menu: {
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 44f));
		g2d.setColor(Color.BLUE);
		g2d.drawImage(tex.menuBg, 300, 120, null);
		g2d.drawString("WZNÓW GRÊ", 360, 180);
		g2d.drawString("MENU G£ÓWNE", 360, 260);
		g2d.drawString("WYJŒCIE", 360, 340);
		break;
	}
	case Death: {
		g2d.drawImage(tex.menuBg, 300, 120, null);
		g2d.setColor(Color.RED);
		g2d.setFont(MainClass.texasFont.deriveFont(68f));
		g2d.drawString("NIE ¯YJESZ ...", 355, 220);
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 38f));
		g2d.drawString("SPACJA - RESTART", 340, 320);
		break;
	}
	case NextLevel: {
		g2d.setColor(Color.GRAY);
		g2d.fillRect(300, 100, 400, 450);
		g2d.setColor(Color.YELLOW);
		g2d.drawRect(2999, 99, 402, 342);
		g2d.setFont(MainClass.texasFont.deriveFont(Font.BOLD, 54f));
		g2d.drawString("POZIOM "+MainScreen.LEVEL +" UKOÑCZONY !!!", 315, 155);
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 42f));
		g2d.drawString("TWÓJ WYNIK: " +MainScreen.SCORE, 375, 240);
		g2d.drawString("CZAS: " +MainScreen.time, 380, 300);
		g2d.drawString("BONUS CZASOWY: " + (int) MainScreen.time_bonus, 340, 370);
		g2d.drawString("WYNIK KOÑCOWY: " +(int) MainScreen.TOTAL_SCORE, 340, 430);
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 26f));
		g2d.setColor(Color.WHITE);
		g2d.drawString("PODAJ SWOJE IMIÊ I NACIŒNIJ ENTER:", 320, 480);
		g2d.setFont(MainClass.smokunFont.deriveFont(Font.BOLD, 32f));
		g2d.setColor(Color.CYAN);
		g2d.drawString(MainScreen.playerName , 490-(MainScreen.playerName.length() * 8), 520);
		break;
	}
	case MainMenu: {
		g2d.setFont(new Font("Arial", Font.BOLD, 14));
		g2d.setColor(Color.BLACK);
		g2d.drawString("Tequila Platformer ver. 0.5 (build 0), 2016.", 10, 590);
		break;
	}
	default: {
			
	}
	}
}

}