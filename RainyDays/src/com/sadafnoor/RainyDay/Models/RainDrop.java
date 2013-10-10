package com.sadafnoor.RainyDay.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class RainDrop extends ParticleEffect{
public Vector2 position;
public Vector2  acceleration;
public Vector2  velocity;

public RainDrop(Vector2 position){
	acceleration = new Vector2();
	velocity = new Vector2();
	
	this.position=position;
}

public void setLocation(float f, float i) {
	position.x=f;
	position.y=i;
}
public void update(float delta) {
	position.add(
			velocity.cpy().mul(delta));
}
}
