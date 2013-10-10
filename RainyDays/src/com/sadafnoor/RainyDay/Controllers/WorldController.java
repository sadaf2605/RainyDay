package com.sadafnoor.RainyDay.Controllers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.sadafnoor.RainyDay.Models.*;
import com.sadafnoor.RainyDay.Models.Bob.State;


public class WorldController {

	enum Keys {
		LEFT, RIGHT, JUMP, FIRE
	}

	private World 	world;
	private Bob 	bob;
	private Background background;
	private RainDrop [] rainDrops;

	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.FIRE, false);
	};

	public WorldController(World world) {
		this.world = world;
		this.bob = world.getBob();
		this.background =world.getBackground();
		this.rainDrops=world.getRainDrops();
	}

	// ** Key presses and touches **************** //

	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}

	public void jumpPressed() {
		keys.get(keys.put(Keys.JUMP, true));
	}

	public void firePressed() {
		keys.get(keys.put(Keys.FIRE, false));
	}

	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}

	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}

	public void jumpReleased() {
		keys.get(keys.put(Keys.JUMP, false));
	}

	public void fireReleased() {
		keys.get(keys.put(Keys.FIRE, false));
	}

	/** The main update method **/
	public void update(float delta) {
		if (Gdx.input.getAccelerometerY()<-10){
			keys.get(keys.put(Keys.RIGHT, false));
			keys.get(keys.put(Keys.LEFT, true));
		}else if (Gdx.input.getAccelerometerY()>10){
			keys.get(keys.put(Keys.LEFT, false));
			keys.get(keys.put(Keys.RIGHT, true));
		}
		processInput();
		bob.update(delta);
		background.update(delta);
		
		for(RainDrop r :rainDrops){
			r.update(delta);
		}
		
	}

	private void raincollition(RainDrop r){
		if (r.position.y-bob.position.y <bob.SIZE && r.position.x-bob.position.x <bob.SIZE ){
			world.textToDisplay="MOM: \"You will get cold!\" MOM Anger:"+(++bob.score);
			
		}else{
			world.textToDisplay="MOM anger:"+(bob.score);
		}
	}
	/** Change Bob's state and parameters based on input controls **/
	private void processInput() {
		
		
		if (keys.get(Keys.LEFT)) {
			// left is pressed
			bob.facingLeft=true;
			bob.state=State.WALKING;
			bob.velocity.x = -Bob.SPEED;
			background.velocity.x= -Background.SPEED;
			for(RainDrop r :rainDrops){
				r.velocity.x= -Background.SPEED;
				raincollition(r);
			}
		}
		if (keys.get(Keys.RIGHT)) {
			// left is pressed
			bob.facingLeft=false;
			bob.state=State.WALKING;
			bob.velocity.x = Bob.SPEED;
			background.velocity.x= Background.SPEED;
			for(RainDrop r :rainDrops){
				r.velocity.x= Background.SPEED;
				raincollition(r);
				
			}
		}
		// need to check if both or none direction are pressed, then Bob is idle
		if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
				(!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
			bob.state=State.IDLE;
			// acceleration is 0 on the x
			bob.acceleration.x = 0;
			// horizontal speed is 0
			bob.velocity.x = 0;
			
			background.acceleration.x=0;
			background.velocity.x = 0;
			
			for(RainDrop r :rainDrops){
				r.acceleration.x=0;
				r.velocity.x = 0;
			}
		}
	}
}