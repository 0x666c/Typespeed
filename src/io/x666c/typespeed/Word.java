package io.x666c.typespeed;

import java.awt.Color;

import io.x666c.glib4j.graphics.Renderer;
import io.x666c.glib4j.math.MathUtil;
import io.x666c.typespeed.gui.menus.GameScreen;

public class Word {
	
	public String word;
	public double difficulty;
	public double speed;
	
	public float x, y;
	
	static double mapLog(double value, double start1, double stop1, double start2, double stop2) {
		start2 = Math.log(start2);
		stop2 = Math.log(stop2);

		double outgoing = Math.exp(start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1)));
		return outgoing;
	}
	
	public Word() {
		word = WordScraper.getWord();
		speed = MathUtil.random(0.5f, 0.8f);
		difficulty = MathUtil.map(word.length(), 1, 16, 0, 1);
		
		x = -GameScreen.stringWidth(word);
		y = MathUtil.random(10, 500 - 26);
		
	}
	
	public boolean matches(String w) {
		System.out.println(w + ":" + word);
		return word.equals(w);
	}
	
	public Word(String word) {
		
	}
	
	public double fadeOut = 255;
	private boolean missed = false;
	public void render(Renderer r) {
		final Color d1 = new Color(0x39ff14);
		final Color d2 = new Color(0xf05e23);
		final Color d3 = new Color(0xff2800);
		r.color(difficulty < 0.28 ? d1 : difficulty < 0.5 ? d2 : d3);
		r.alpha((int)fadeOut);
		r.text(word, x, y);
		
		if(missed) {
			fadeOut -= 8;
		}
	}
	
	public void update() {
		if(x > 800 - GameScreen.stringWidth(word))
			missed = true;
		else
			x += speed;
	}
	
}