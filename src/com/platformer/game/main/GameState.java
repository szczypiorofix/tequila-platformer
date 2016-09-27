package com.platformer.game.main;

/** Enum zawieraj�cy informacje w jakim aktualnie stanie znajduje si� gra i co nale�y prezentowa� w danym momencie na ekranie czyli poszczeg�lne menusy oraz sama gra.
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
	
	/** Wy�wietlane na pocz�tku gry
	 * 
	 */
	Intro,
	
	
	/** Wy�wietlane na zako�czenie gry
	 * 
	 */
	Outro,
	
	
	/** G��wne menu gry.
	 * 
	 */
	MainMenu,
	
	/** In-game menu.
	 * 
	 */
	Menu,
	
	/** �mier� gracza.
	 * 
	 */
	Death,
	
	/** Przej�cie gracza do nast�pnego poziomu.
	 * 
	 */
	NextLevel,
	
	/** Podmenu menu g��wnego - Jak Gra�.
	 * 
	 */
	JakGrac,
	
	/** Podmenu menu g��wnego - Najlepsze Wyniki.
	 * 
	 */
	NajlepszeWyniki,
	
	/** Podmenu menu g��wnego - Osi�gni�cia.
	 * 
	 */
	Osiagniecia,
	
	/** Podmenu menu g��wnego - Znajd�ki.
	 * 
	 */
	Znajdzki,
	
	/** Podmenu menu g��wnego - O Grze.
	 * 
	 */
	OGrze,
	
	/** Polecenie zamkni�cia gry.
	 * 
	 */
	Zakoncz;
}