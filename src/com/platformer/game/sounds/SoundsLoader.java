package com.platformer.game.sounds;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundsLoader {

private AudioInputStream jumpAudioStream;
private Clip jumpClip;
private final URL jumpSoundFile = getClass().getResource("/Jump.wav");
private final URL coinSoundFile = getClass().getResource("/coin10.wav");
private FloatControl gainControl;


//http://www.bfxr.net/


public void playCoinSound()
{
	try
	{
		jumpAudioStream = AudioSystem.getAudioInputStream((coinSoundFile));
		jumpClip = AudioSystem.getClip();
		jumpClip.open(jumpAudioStream);
		gainControl = (FloatControl) jumpClip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);  // VOLUME CONTROL
		jumpClip.start();
		jumpAudioStream = null;
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
		jumpAudioStream = AudioSystem.getAudioInputStream((jumpSoundFile));
		jumpClip = AudioSystem.getClip();
		jumpClip.open(jumpAudioStream);
		gainControl = (FloatControl) jumpClip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);  // VOLUME CONTROL
		jumpClip.start();
		jumpAudioStream = null;
    }
	catch(Exception ex) {
    	ex.printStackTrace();
    	System.exit(-1);
    	}
}
}