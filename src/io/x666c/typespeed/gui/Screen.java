package io.x666c.typespeed.gui;

import io.x666c.glib4j.graphics.Renderer;
import io.x666c.typespeed.Typespeed;

public abstract class Screen {
	
	protected void switchScreen(Screen screen) {
		Typespeed.INSTANCE.switchScreen(screen);
	}
	
	public abstract void render(Renderer r);
	public abstract void update();
	
	public abstract void reset();
	
}