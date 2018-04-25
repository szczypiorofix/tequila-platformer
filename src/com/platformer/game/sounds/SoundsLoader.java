package com.platformer.game.sounds;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.logging.Level;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import com.platformer.game.main.MainClass;


public class SoundsLoader {

	private Clip clip;
	private FloatControl gainControl;


	//http://www.bfxr.net/

	public SoundsLoader(String path) {
		try {
			InputStream audioSrc = getClass().getResourceAsStream(path);
			InputStream bufferedIn = new BufferedInputStream(audioSrc);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED
					, baseFormat.getSampleRate()
					, 16
					, baseFormat.getChannels()
					, baseFormat.getChannels() *2
					, baseFormat.getSampleRate()
					, false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			MainClass.logging(false, Level.INFO, "Plik dźwiękowy (.wav) " +path +" załadowany poprawnie");
		} catch(Exception ex) {
            MainClass.logging(false, Level.WARNING, "Błąd odczytu z pliku " +path);
            MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(ex));
        }
	}


	public void play() {
		if (clip == null) return;

		clip.stop();
		clip.setFramePosition(0);

		while (!clip.isRunning()) {
			clip.start();
		}
	}

	public void stop() {
		if (clip.isRunning()) clip.stop();
	}

	public boolean isPlaying() {
		return clip.isRunning();
	}

	public void close() {
		stop();
		clip.drain();
		clip.close();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		while (!clip.isRunning()) {
			clip.start();
		}
	}

	public void setVolume(Float volume) {
		gainControl.setValue(volume);
	}
}
