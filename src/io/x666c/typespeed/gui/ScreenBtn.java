package io.x666c.typespeed.gui;

import java.awt.Color;

import io.x666c.glib4j.graphics.Renderer;
import io.x666c.glib4j.input.Input;
import io.x666c.glib4j.math.phys.Collider;
import io.x666c.typespeed.gui.menus.GameScreen;

public class ScreenBtn {
	
	private String text;
	private Color color;
	
	private Collider collider;
	
	private Runnable action;
	
	private boolean pressedBefore = false;
	
	public ScreenBtn(int x, int y, String text) {
		this(x, y, text, Color.WHITE);
	}
	
	public ScreenBtn(int x, int y, String text, Color c) {
		this.text = text;
		this.color = c;
		
		collider = new Collider(x - 5, y, GameScreen.stringWidth(text) + 5, 20);
	}
	
	public void render(Renderer r) {
		r.draw();
		
		r.color(color);
		r.text(text, collider.x() + 5, collider.y() + 14);
	}
	
	private char charShortcut = '\0';
	
	public void update() {
		if((Input.mouse.lmb() && !pressedBefore && collider.contains(Input.mouse.xy(0, 0))) || Input.keyboard.keyOnce(charShortcut)) {
			color = Color.RED;
			System.out.println(11);
			action.run();
			pressedBefore = true;
		}
		if(!Input.mouse.lmb()) {
			pressedBefore = false;
		}
	}
	
	public void addAction(char ch, Runnable r) {
		charShortcut = ch;
		action = r;
	}
	
}