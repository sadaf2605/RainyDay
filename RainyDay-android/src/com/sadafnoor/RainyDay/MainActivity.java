package com.sadafnoor.RainyDay;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.sadafnoor.RainyDay.StarAssault;

public class MainActivity extends AndroidApplication {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
	    cfg.useGL20 = true;
	    cfg.useAccelerometer = true;
	    cfg.useCompass = false;

	    initialize(new StarAssault(), cfg);
	}
}