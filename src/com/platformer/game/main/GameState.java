package com.platformer.game.main;

/** Enum zawieraj¹cy informacje w jakim aktualnie stanie znajduje siê gra i co nale¿y prezentowaæ w danym momencie na ekranie czyli poszczególne menusy oraz sama gra.
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
	
	/** Wyœwietlane na pocz¹tku gry
	 * 
	 */
	Intro,
	
	
	/** Wyœwietlane na zakoñczenie gry
	 * 
	 */
	Outro,
	
	
	/** G³ówne menu gry.
	 * 
	 */
	MainMenu,
	
	/** In-game menu.
	 * 
	 */
	Menu,
	
	/** Œmieræ gracza.
	 * 
	 */
	Death,
	
	/** Przejœcie gracza do nastêpnego poziomu.
	 * 
	 */
	NextLevel,
	
	/** Podmenu menu g³ównego - Jak Graæ.
	 * 
	 */
	JakGrac,
	
	/** Podmenu menu g³ównego - Najlepsze Wyniki.
	 * 
	 */
	NajlepszeWyniki,
	
	/** Podmenu menu g³ównego - Osi¹gniêcia.
	 * 
	 */
	Osiagniecia,
	
	/** Podmenu menu g³ównego - ZnajdŸki.
	 * 
	 */
	Znajdzki,
	
	/** Podmenu menu g³ównego - O Grze.
	 * 
	 */
	OGrze,
	
	/** Polecenie zamkniêcia gry.
	 * 
	 */
	Zakoncz;
}