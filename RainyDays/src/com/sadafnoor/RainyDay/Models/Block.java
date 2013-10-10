package com.sadafnoor.RainyDay.Models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block {

 public static final float SIZE = 1f;

 public Vector2  position = new Vector2();
 public Rectangle  bounds = new Rectangle();

 public Block(Vector2 pos) {
  this.position = pos;
  this.bounds.width = SIZE;
  this.bounds.height = SIZE;
 }

}