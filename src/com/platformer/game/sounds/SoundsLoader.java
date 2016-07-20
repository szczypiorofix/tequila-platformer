package com.platformer.game.sounds;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundsLoader {

private AudioInputStream audioStream;
private Clip clip;
private final URL jumpSoundFile = getClass().getResource("/jump.wav");
private final URL coinSoundFile = getClass().getResource("/coin10.wav");
private final URL hitSoundFile = getClass().getResource("/hit.wav");
private final URL drinkSoundFile = getClass().getResource("/drink.wav");
private final URL eatSoundFile = getClass().getResource("/eat.wav");
private final URL menuSoundFile1 = getClass().getResource("/menusound1.wav");
private final URL menuSoundFile2 = getClass().getResource("/menusound2.wav");
private final URL powerup = getClass().getResource("/powerup.wav");
private FloatControl gainControl;


//http://www.bfxr.net/

public void playPowerUpSound()
{
	try
	{
		audioStream = AudioSystem.getAudioInputStream((powerup));
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);  // VOLUME CONTROL
		clip.start();
		audioStream = null;
    }
	catch(Exception ex) {
    	ex.printStackTrace();
    	System.exit(-1);
    	}
}

public void playMenuSound2()
{
	try
	{
		audioStream = AudioSystem.getAudioInputStream((menuSoundFile2));
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);  // VOLUME CONTROL
		clip.start();
		audioStream = null;
    }
	catch(Exception ex) {
    	ex.printStackTrace();
    	System.exit(-1);
    	}
}

public void playMenuSound1()
{
	try
	{
		audioStream = AudioSystem.getAudioInputStream((menuSoundFile1));
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);  // VOLUME CONTROL
		clip.start();
		audioStream = null;
    }
	catch(Exception ex) {
    	ex.printStackTrace();
    	System.exit(-1);
    	}
}

public void playEatSound()
{
	try
	{
		audioStream = AudioSystem.getAudioInputStream((eatSoundFile));
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);  // VOLUME CONTROL
		clip.start();
		audioStream = null;
    }
	catch(Exception ex) {
    	ex.printStackTrace();
    	System.exit(-1);
    	}
}

public void playDrinkSound()
{
	try
	{
		audioStream = AudioSystem.getAudioInputStream((drinkSoundFile));
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(+5.0f);  // VOLUME CONTROL
		clip.start();
		audioStream = null;
    }
	catch(Exception ex) {
    	ex.printStackTrace();
    	System.exit(-1);
    	}
}


public void playHitSound()
{
	try
	{
		audioStream = AudioSystem.getAudioInputStream((hitSoundFile));
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);  // VOLUME CONTROL
		clip.start();
		audioStream = null;
    }
	catch(Exception ex) {
    	ex.printStackTrace();
    	System.exit(-1);
    	}
}


public void playCoinSound()
{
	try
	{
		audioStream = AudioSystem.getAudioInputStream((coinSoundFile));
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);  // VOLUME CONTROL
		clip.start();
		audioStream = null;
    }
	catch(Exception ex) {
    	ex.printStackTrace();
    	System.exit(-1);
    	}
}


public void playJumpSound()
{
	try
	{
		audioStream = AudioSystem.getAudioInputStream((jumpSoundFile));
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);  // VOLUME CONTROL
		clip.start();
		audioStream = null;
    }
	catch(Exception ex) {
    	ex.printStackTrace();
    	System.exit(-1);
    	}
}
}