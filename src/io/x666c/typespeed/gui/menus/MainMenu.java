package io.x666c.typespeed.gui.menus;

import io.x666c.glib4j.graphics.Renderer;
import io.x666c.typespeed.gui.NeverGonnaGiveUUp;
import io.x666c.typespeed.gui.Screen;
import io.x666c.typespeed.gui.ScreenBtn;

public class MainMenu extends Screen {
	
	private ScreenBtn logo_easteregg;
	
	private ScreenBtn play;
	private ScreenBtn multiplayer;
	private ScreenBtn rtfm;
	private ScreenBtn rules;
	private ScreenBtn exit;
	
	public MainMenu() {
		final int centerX = ((800 / 2) - (GameScreen.stringWidth("JTypespeed v1.0") / 2) - 10);
		final int centerY = (500 / 2);
		
		logo_easteregg 	= new ScreenBtn(centerX, centerY - 165, "JTypespeed v1.0");
		
		play 			= new ScreenBtn(centerX - 25, centerY - 75, "1. It's time to kick ass");
		multiplayer 	= new ScreenBtn(centerX - 25, centerY - 50, "2. r3KT some scrubs");
		rtfm 			= new ScreenBtn(centerX - 25, centerY - 25, "3. RTFM");
		rules 			= new ScreenBtn(centerX - 25, centerY - 0, "4. Change the world");
		exit 			= new ScreenBtn(centerX - 25, centerY + 25, "5. GTFO");
		
		play.addAction('1', () -> {
			switchScreen(new GameScreen());
		});
		multiplayer.addAction('2', () -> {
			//switchScreen(new GameScreen());
		});
		rtfm.addAction('3', () -> {
			//switchScreen(new GameScreen());
		});
		rules.addAction('4', () -> {
			//switchScreen(new GameScreen());
		});
		exit.addAction('5', () -> {
			//switchScreen(new GameScreen());
			System.exit(0);
		});
		
		logo_easteregg.addAction('\0', () -> {
			NeverGonnaGiveUUp.playTheChime();
		});
	}
	
	public void render(Renderer r) {
		r.color(~0);
		
		logo_easteregg.render(r);
		play.render(r);
		multiplayer.render(r);
		rtfm.render(r);
		rules.render(r);
		exit.render(r);
	}
	
	public void update() {
		logo_easteregg.update();
		play.update();
		multiplayer.update();
		rtfm.update();
		rules.update();
		exit.update();
	}

	public void reset() {
		
	}
	
}