package com.sadafnoor.RainyDay.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

 /** The blocks making up the world **/
 Array<Block> blocks = new Array<Block>();
 /** Our player controlled hero **/
 Bob bob;
 
 Background background;
 
 RainDrop [] rainDrops;

 // Getters -----------
 public Array<Block> getBlocks() {
  return blocks;
 }
 public Bob getBob() {
  return bob;
 }
 public Background getBackground() {
	  return background;
	 }
 public RainDrop [] getRainDrops(){
	 return rainDrops;
 }
 // --------------------

 public World() {
  createDemoWorld();
 }

 private void createDemoWorld() {
  bob = new Bob(new Vector2(6, 1));
  background=new Background(new Vector2(0, 0));

  rainDrops=new RainDrop[5];
  for (int n=0;n<rainDrops.length;n++){
	  rainDrops[n] = new RainDrop(new Vector2(n*2, 6));
	  System.out.println("position set to: "+rainDrops[n].position.x);
  	}
  
  
  for (int i = 0; i < 10; i++) {
   blocks.add(new Block(new Vector2(i, 0)));
   blocks.add(new Block(new Vector2(i, 7)));
   if (i > 2)
    blocks.add(new Block(new Vector2(i, 1)));
  }
  blocks.add(new Block(new Vector2(9, 2)));
  blocks.add(new Block(new Vector2(9, 3)));
  blocks.add(new Block(new Vector2(9, 4)));
  blocks.add(new Block(new Vector2(9, 5)));

  blocks.add(new Block(new Vector2(6, 3)));
  blocks.add(new Block(new Vector2(6, 4)));
  blocks.add(new Block(new Vector2(6, 5)));
 }
}