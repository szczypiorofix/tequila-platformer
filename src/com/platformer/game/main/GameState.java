package com.platformer.game.main;

/** Enum zawierający informacje w jakim aktualnie stanie znajduje się gra i co należy prezentować w danym momencie na ekranie czyli poszczególne menusy oraz sama gra.
 * 
 * Game, MainMenu, Menu, Death, NextLevel, JakGrac, NajlepszeWyniki, Osiagniecia, Znajdzki, OGrze, Zakoncz
 * 
 * @author Piotrek
 *
 */
public enum GameState {

	/** Gra "w trakcie" na aktualnym poziomie.
	 * 
	 */
	Game,
	
	/** Wyświetlane na początku gry
	 * 
	 */
	Intro,
	
	/** Wyświetlane na zakończenie gry
	 * 
	 */
	Outro,
		
	/** Menu wyboru poziomów - poziomy dostępne wybierane na podstawie osiągnięć ukończonych leveli
	 * 
	 */
	LevelChoose,
		
	/** Główne menu gry.
	 * 
	 */
	MainMenu,
	
	/** In-game menu.
	 * 
	 */
	Menu,
	
	/** Śmierć gracza.
	 * 
	 */
	Death,
	
	/** Przejście gracza do następnego poziomu.
	 * 
	 */
	NextLevel,
	
	/** Podmenu menu głównego - Jak Grać.
	 * 
	 */
	JakGrac,
	
	/** Podmenu menu głównego - Najlepsze Wyniki.
	 * 
	 */
	NajlepszeWyniki,
	
	/** Podmenu menu głównego - Osiągnięcia.
	 * 
	 */
	Osiagniecia,
	
	/** Podmenu menu głównego - Znajdźki.
	 * 
	 */
	Znajdzki,
	
	/** Podmenu menu głównego - O Grze.
	 * 
	 */
	OGrze,
	
	/** Polecenie zamknięcia gry.
	 * 
	 */
	Zakoncz;
}