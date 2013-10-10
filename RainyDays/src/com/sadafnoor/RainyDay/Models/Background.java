package com.sadafnoor.RainyDay.Models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Background {
	 public Vector2  position = new Vector2();
	 public Vector2  acceleration = new Vector2();
	 public Vector2  velocity = new Vector2();
	 Rectangle  bounds = new Rectangle();
	 public static final float SIZE = 11f; // half a unit
	 public static final float SPEED = 0.6f; // unit per second
	 
	 public Background(Vector2 position) {
		  this.position = position;
		  this.bounds.height = SIZE;
		  this.bounds.width = SIZE;
		 }
		 public void update(float delta) {
				position.add(
						velocity.cpy().mul(delta));
			}

}
