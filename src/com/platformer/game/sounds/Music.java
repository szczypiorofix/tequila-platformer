package com.platformer.game.sounds;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.logging.Level;

import com.platformer.game.main.MainClass;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


/** Podstawowa klasa zajmuj�ca si� odtwarzaniem muzyki z plik�w w formacie mp3.
 * @author Piotrek
 *
 */
public class Music {

	
	
/** Piosenka "Wester.mp3"
 * 
 */
public static final int WESTERN = 1;

/** Piosenka "Mirage.mp3"
 * 
 */
public static final int MIRAGE = 2;

/** Obiekt klasy Player. G��wny odtwarzacz mp3 ze strumienia fis.
 *
 */
private Player player;

/** Obiekt klasy InputStream czyli strumie� wej�ciowy z pliku mp3.
 * 
 */
private InputStream fis;

/** Przyjmuje warto�� "true" je�li odtwarzana jest jakakolwiek muzyka. 
 * 
 */
private boolean playing = false;

private int song;

// http://opengameart.org/content/mirage


/** Podstawowy konstruktor klasy Music.
 * 
 * 
 */
public Music()
{
	///https://www.youtube.com/watch?v=LavMuqK5Is0
	// http://opengameart.org/content/western
	song = WESTERN;
}



/** Metoda od�wie�aj�ca strumienie oraz inicjuj�ca nowy odtwarzacz mp3 (Player)
 * 
 * @param song : WESTERN lub MIRAGE
 */
public void restart(int song)
{
	this.song = song;
	try {
		if (this.song == WESTERN) fis = this.getClass().getResourceAsStream("/western.mp3"); 
		if (this.song == MIRAGE) fis = this.getClass().getResourceAsStream("/mirage.mp3");
		player = new Player(new BufferedInputStream(fis));
		MainClass.logging(false, Level.INFO, "Plik z muzyk� nr." +song +" za�adowany poprawnie.");
	} catch (JavaLayerException e) {
		MainClass.logging(false, Level.WARNING, "B��d odczytu strumienia z pliku nr." +song);
		MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(e));
	}		
}

/** Metoda rozpoczynaj�ca odtwarzanie pliku mp3.
 * 
 */
public void play()
{
	try {
		player.play();
		MainClass.logging(false, Level.INFO, "Odtwarzanie pliku z muzyk� nr." +song);
	} catch (JavaLayerException e) {
		MainClass.logging(false, Level.WARNING, "B��d odtwarzania z pliku nr." +song);
		MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(e));
	}		
}

/** Metoda zatrzymuj�ca odtwarzanie pliku mp3.
 * 
 */
public void stop()
{
	if (playing)
	{
		player.close();
		playing = false;		
	}
}

/** Metoda zwracaj�ca warto�� true lub false w zale�no�ci od tego czy plik jest w tym momencie odtwarzany.
 * @return true lub false
 */
public boolean isPlaying() {
	return playing;
}


/** Metoda przypisujaca warto�� true lub false do warto�ci "playing", co wp�ywa na odtwarzanie lub zatrzymanie odtwarzania pliku mp3.
 * @param playing true lub false - plik ma by� odtwarzany b�d� nie.
 * 
 */
public void setPlaying(boolean playing) {
	this.playing = playing;
}

public int getSong() {
	return song;
}

public void setSong(int song) {
	this.song = song;
}
}