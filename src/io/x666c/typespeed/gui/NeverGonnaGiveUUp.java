package io.x666c.typespeed.gui;

import java.io.BufferedInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import com.sun.jna.Library;
import com.sun.jna.Native;

import io.x666c.typespeed.WordScraper;

public class NeverGonnaGiveUUp { // Joke class :)
	
	private static final String[] lyrics = "Never gonna give you up Never gonna let you down Never gonna run around and desert you Never gonna make you cry Never gonna say goodbye Never gonna tell a lie and hurt you  We've known each other for so long Your heart's been aching, but You're too shy to say it Inside, we both know what's been going on We know the game and we're gonna play it  And if you ask me how I'm feeling Don't tell me you're too blind to see  Never gonna give you up Never gonna let you down Never gonna run around and desert you Never gonna make you cry Never gonna say goodbye Never gonna tell a lie and hurt you  Never gonna give you up Never gonna let you down Never gonna run around and desert you Never gonna make you cry Never gonna say goodbye Never gonna tell a lie and hurt you".split(" ");
	
	/*private static interface BeepLib extends Library {
		public void Beep(int freq, int dur);
	}
	
	static {
		try {
			bl = Native.load("kernel32", BeepLib.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	private static boolean alreadyPlaying = false;
	
	public static void playTheChime() {
		WordScraper.rickRoll(lyrics);
		
		if(alreadyPlaying)
			return;
		
		new Thread(() -> {
			alreadyPlaying = true;
			
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(NeverGonnaGiveUUp.class.getResourceAsStream("/rick.wav"))));
				FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				volume.setValue(-9);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			
			/*// Never gonna give you up
			bl.Beep(440, 130);
			bl.Beep(494, 130);
			Sleep.during(100);
			bl.Beep(587, 130);
			bl.Beep(494, 130);
			Sleep.during(100);
			bl.Beep(740, 300);
			bl.Beep(740, 300);
			bl.Beep(659, 500);
			Sleep.during(100);
			
			// Never gonna let you down
			bl.Beep(440, 130);
			bl.Beep(494, 130);
			Sleep.during(100);
			bl.Beep(587, 130);
			bl.Beep(494, 130);
			Sleep.during(100);
			bl.Beep(659, 300);
			bl.Beep(659, 300);
			bl.Beep(587, 300);
			bl.Beep(554, 200);
			bl.Beep(494, 200);
			Sleep.during(200);
			
			// Never gonna run around and desert you
			bl.Beep(440, 130);
			bl.Beep(494, 130);
			Sleep.during(100);
			bl.Beep(587, 130);
			bl.Beep(494, 130);
			Sleep.during(100);
			bl.Beep(587, 250);
			Sleep.during(100);
			bl.Beep(659, 150);
			bl.Beep(554, 250);
			Sleep.during(100);
			bl.Beep(440, 250);
			Sleep.during(100);
			bl.Beep(440, 130);
			bl.Beep(659, 300);
			Sleep.during(100);
			bl.Beep(587, 300);*/
			//alreadyPlaying = false;
		}).start();
	}
	
}