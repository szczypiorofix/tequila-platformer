package platformer;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundsLoader {

private AudioInputStream jumpAudioStream;
private Clip jumpClip;
private final URL jumpSoundFile = getClass().getResource("/res/Jump.wav");



//http://www.bfxr.net/


public void playJumpSound()
{
	try
	{
		jumpAudioStream = AudioSystem.getAudioInputStream((jumpSoundFile));
		jumpClip = AudioSystem.getClip();
		jumpClip.open(jumpAudioStream);
		jumpClip.start();
		jumpAudioStream = null;
    }
	catch(Exception ex) {
    	ex.printStackTrace();
    	System.exit(-1);
    	}
}
}