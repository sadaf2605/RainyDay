package com.sadafnoor.RainyDay.Models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bob {

 public enum State {
  IDLE, WALKING, JUMPING, DYING
 }
 
 public int score=0;

 public static final float SPEED = 2f; // unit per second
 static final float JUMP_VELOCITY = 1f;
 public static final float SIZE = 1.0f; // half a unit

 public Vector2  position = new Vector2();
 public Vector2  acceleration = new Vector2();
 public Vector2  velocity = new Vector2();
 public Rectangle  bounds = new Rectangle();
 public State  state = State.IDLE;
 public boolean  facingLeft = true;

 public Bob(Vector2 position) {
  this.position = position;
  this.bounds.height = SIZE;
  this.bounds.width = SIZE;
 }
 public void update(float delta) {
		position.add(
				velocity.cpy().mul(delta));
	}
}