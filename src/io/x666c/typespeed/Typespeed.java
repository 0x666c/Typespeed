package io.x666c.typespeed;

import io.x666c.glib4j.GFrame;
import io.x666c.glib4j.graphics.Renderer;
import io.x666c.typespeed.gui.Screen;
import io.x666c.typespeed.gui.menus.GameScreen;
import io.x666c.typespeed.gui.menus.MainMenu;

public class Typespeed {
	
	public static Typespeed INSTANCE;
	
	public static void main(String[] args) {
		System.out.println("Downloading words");
		WordScraper.fillStack(1000);
		System.out.println("Done");
		
		INSTANCE = new Typespeed();
		GFrame f = GFrame.auto(INSTANCE);
		f.setUps(160); // Idk should be enough for smooth input and not-so-fast paced gameplay
		f.background(0);
		f.synchronize(true);
		f.setSize(800, 500);
		f.setTitle("JTypespeed");
		f.start();
		f.getDrawCanvas().requestFocus();
	}
	
	
	private Screen currentScreen;
	
	public Typespeed() {
		currentScreen = new MainMenu();
	}
	
	public void render(Renderer r) {
		r.font(GameScreen.font);
//		throw new RuntimeException();
		currentScreen.render(r);
	}
	
	public void update() {
		currentScreen.update();
	}
	
	public void switchScreen(Screen screen) {
		currentScreen.reset();
		currentScreen = screen;
	}
	
}