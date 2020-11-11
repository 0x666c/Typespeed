package io.x666c.typespeed.gui.menus;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import io.x666c.glib4j.graphics.Renderer;
import io.x666c.typespeed.Word;
import io.x666c.typespeed.gui.Screen;
import io.x666c.typespeed.gui.panel.InputField;
import io.x666c.typespeed.gui.panel.Scoreboard;

public class GameScreen extends Screen {
	
	public static final Font font = new Font("Courier New", Font.PLAIN, 14);
	
	public int wordDelay = 1800;
	public int wordsPerWave = 2;
	public int maxWordsOnScreen = 8;
	
	public int missed = 0;
	public int hit = 0;
	
	public int score = 0;
	public int lives = 20;
	
	public ArrayList<Word> activeWords = new ArrayList<>();
	
	public InputField inputField;
	public Scoreboard scoreboard;
	
	public GameScreen() {
		inputField = new InputField(this);
		scoreboard = new Scoreboard(this);
		
		new Timer().scheduleAtFixedRate(new TimerTask() {
			public void run() {
				for (int i = 0; i < wordsPerWave; i++) {
					if(activeWords.size() >= maxWordsOnScreen)
						return;
					activeWords.add(new Word());
				}
			}
		}, 100, wordDelay);
	}

	public void render(Renderer r) {
		r.font(font);

		for (Word word : activeWords) {
			word.render(r);
		}

		inputField.render(r);
		scoreboard.render(r);
	}

	public void update() {
		for (Iterator<Word> iterator = activeWords.iterator(); iterator.hasNext();) {
			Word word = iterator.next();
			
			word.update();
			if(word.fadeOut <= 0) {
				iterator.remove();
				
				score -= Math.min(0, (((word.difficulty+1)*(word.difficulty+1)) * 10) - ((word.speed+1)*(word.speed+1)));
			}
		}

		inputField.update();
		scoreboard.update();
	}

	@Override
	public void reset() {
		
	}
	
	public void checkInput(String str) {
		Word word = null;
		for (Iterator<Word> iterator = activeWords.iterator(); iterator.hasNext();) {
			word = iterator.next();
			
			if(word.matches(str)) {
				iterator.remove(); // Match first added word
				
				// (diff^2 * 10) + (diff^(speed+1.2))^2
				score += (((word.difficulty+1)*(word.difficulty+1)) * 10) + (Math.pow(word.difficulty+1, word.speed+1.2)*Math.pow(word.difficulty+1, word.speed+1.2));
				
				scoreboard.charInput();
				hit++;
				
				return;
			}
		}
		score -= Math.min(0, (((word.difficulty+1)*(word.difficulty+1)) * 2) - ((word.speed+1)*(word.speed+1)));
		
		missed++;
	}
	
	public static int stringWidth(String text) {
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		return (int)(font.getStringBounds(text, frc).getWidth());
	}
	
}