package fr.hugosimony.monopoly.audio.sounds;

import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import fr.hugosimony.monopoly.Variables;

public class Sound {

	public static void playSound(URL sound) {
		
		/*
		 * Play a sound with the volume set by the user (default : 65%)
		 */
		
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			if (Variables.VOLUME_Main < 0f || Variables.VOLUME_Main > 1f)
		        throw new IllegalArgumentException("Volume not valid: " + Variables.VOLUME_Main);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
		    gainControl.setValue(20f * (float) Math.log10(Variables.VOLUME_Main+0.15));
		    clip.start();
		    new Timer().schedule(new TimerTask() {@Override public void run() {clip.close(); this.cancel();}}, 20000, 1);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
	}
	
}
