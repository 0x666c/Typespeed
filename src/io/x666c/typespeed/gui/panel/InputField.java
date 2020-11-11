package io.x666c.typespeed.gui.panel;

import java.awt.Font;
import java.util.Stack;

import io.x666c.glib4j.graphics.Renderer;
import io.x666c.glib4j.input.Input;
import io.x666c.typespeed.gui.menus.GameScreen;

public class InputField {
	
	private static final Font FONT  = new Font("Courier New", Font.PLAIN, 18);
	private static final Font FONT2 = new Font("Courier New", Font.PLAIN, 14);
	
	private Stack<Character> charBuffer;
	
	private GameScreen gameScreen;
	
	public InputField(GameScreen t) {
		gameScreen = t;
		
		charBuffer = new Stack<Character>();
	}
	
	public void render(Renderer r) {
		r.font(FONT);
		r.fill();
		r.color(~0);
		r.rectangle(0, r.height() - 25, r.width() - 1, 2);
		
		r.text(">", 5, r.height() - 5);
		r.text("<", 150, r.height() - 5);
		
		r.font(FONT2);
		
		int iter = 0;
		for (Character ch : charBuffer) {
			r.text(ch + "", 20 + (iter * 8), r.height() - 6);
			iter++;
		}
	}
	
	private boolean canAdd = true;
	
	public void update() {
		char c = Input.keyboard.lastOnce();
		
		if(c != '\0') {
			if(c == 10) { // Enter
				String check = "";
				for (Character ch : charBuffer) {
					check += ch;
				}
				gameScreen.checkInput(check);
				charBuffer.clear();
				canAdd = true;
			}
			else if(c == 8) { // Backspace
				canAdd = (charBuffer.size() < 16);
				if(!charBuffer.isEmpty())
					charBuffer.pop();
			} else if(canAdd && (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
				charBuffer.push(Character.toLowerCase(c));
				canAdd = (charBuffer.size() < 16);
				
				gameScreen.scoreboard.charInput();
			}
		}
	}
	
}