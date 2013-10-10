package com.sadafnoor.RainyDays;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sadafnoor.RainyDay.RainyDays;


public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "RainyDays";
		cfg.useGL20 = true;
		cfg.width = 480;
		cfg.height = 320;
		new LwjglApplication(new RainyDays(), cfg);
	}
}
