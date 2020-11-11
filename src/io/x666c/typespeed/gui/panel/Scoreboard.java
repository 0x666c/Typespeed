package io.x666c.typespeed.gui.panel;

import io.x666c.glib4j.graphics.Renderer;
import io.x666c.typespeed.gui.menus.GameScreen;

public class Scoreboard {
	
	private GameScreen gameScreen;
	
	private int missed = 0;
	private int hit = 0;
	private int cps = 0;
	private int maxCps = 0;
	private int wordOnScreen = 0;
	private int missedLeft;
	
	private int score = 0;
	
	public Scoreboard(GameScreen g) {
		gameScreen = g;
		missedLeft = g.lives;
	}
	
	public void render(Renderer r) {
		r.text("Score:", 198, r.height() - 7);
		r.text("" + score, 198 + (7 * 7) + 2, r.height() - 7);
		
		r.text("Hit:", 310, r.height() - 7);
		r.text("" + hit, 310 + (7 * 5), r.height() - 7);
		
		r.text("Missed:", 377, r.height() - 7);
		r.text("" + missed, 377 + (7 * 8) + 2, r.height() - 7);
		
		r.text("aCPS:", 464, r.height() - 7);
		r.text("" + cps, 464 + (7 * 6), r.height() - 7);
		
		r.text("mCPS:", 554, r.height() - 7);
		r.text("" + maxCps, 554 + (7 * 6), r.height() - 7);
		
		r.text("oSC:", 640, r.height() - 7);
		r.text("" + wordOnScreen, 640 + (7 * 5), r.height() - 7);
		
		r.text("Lives:", 620 + 100, r.height() - 7);
		r.text("" + missedLeft, 620 + (7 * 7) + 2 + 100, r.height() - 7);
	}
	
	private int ctr = 0; // 60
	private int wrdInc = 0;
	public void update() {
		score = gameScreen.score;
		hit = gameScreen.hit;
		missed = gameScreen.missed;
		maxCps = cps > maxCps ? cps : maxCps;
		wordOnScreen = gameScreen.activeWords.size();
		missedLeft = gameScreen.lives;
		
		ctr++;
		if(ctr == 60) {
			cps = wrdInc;
			wrdInc = 0;
			ctr = 0;
		}
	}
	
	public void charInput() {
		wrdInc++;
	}
	
}