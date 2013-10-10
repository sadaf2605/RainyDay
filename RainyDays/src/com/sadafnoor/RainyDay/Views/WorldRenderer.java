package com.sadafnoor.RainyDay.Views;


import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.sadafnoor.RainyDay.Models.*;

public class WorldRenderer {

 private World world;
 private OrthographicCamera cam;

 /** for debug rendering **/
 ShapeRenderer debugRenderer = new ShapeRenderer();

 public WorldRenderer(World world) {
  this.world = world;
  world.render=this;
  
  this.cam = new OrthographicCamera(10, 7);
  this.cam.position.set(5, 3.5f, 0);
  this.cam.update();
  
  spriteBatch = new SpriteBatch();
  loadTextures();
 }

 


	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;
	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private int width;
	private int height;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis
	public void setSize (int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}
   private void drawBlocks() {

	          for (Block block : world.getBlocks()) {
	              spriteBatch.draw(blockTexture, block.position.x * ppuX, block.position.y * ppuY, Block.SIZE * ppuX, Block.SIZE * ppuY);
	          }

	      }


/** Textures **/
	private Texture bobTexture;
	private Texture blockTexture;



	private static final int        FRAME_COLS = 6;      
    private static final int        FRAME_ROWS = 5;        
    Animation                       walkAnimation;          
    Texture                         walkSheet;              
    TextureRegion[]                 walkFrames;             
    TextureRegion                   currentFrame;           
    
    float stateTime;
	private Texture backgroundTexture;
	private Texture rainTexture;


	

	private RainDrop[] rainDrops;
	private FileHandle textureAtlas;
	private FileHandle effectFile;
	public String textToDisplay;                                        

	private void loadTextures() {
		walkSheet = new  Texture(Gdx.files.internal("animation_sheet.png"));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 
				FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
                for (int j = 0; j < FRAME_COLS; j++) {
                        walkFrames[index++] = tmp[i][j];
                }
        }
        walkAnimation = new Animation(0.025f, walkFrames);

        stateTime = 0f;
        
        backgroundTexture = new Texture(Gdx.files.internal("images/background.png"));
        bobTexture = new  Texture(Gdx.files.internal("images/bob.png"));
        blockTexture = new Texture(Gdx.files.internal("images/block.png"));
        rainTexture = new Texture(Gdx.files.internal("images/droplet.png"));
        
        effectFile=Gdx.files.internal("data/rain.b");
        textureAtlas=Gdx.files.internal("data");

	}	
	public void render(float delta) {
		 spriteBatch.begin();
		 
		 
		 	drawBackground();
		 			//drawBlocks();
			drawBob();
			
			drawParticles(delta);

			
			textToDisplay = world.textToDisplay();
			BitmapFont font = new BitmapFont();
			//Gdx.graphics.getGL20().glClearColor(0, 0, 0, 0); 
			//Gdx.graphics.getGL20().glClear(Gdx.gl10.GL_COLOR_BUFFER_BIT);
			font.draw(spriteBatch, textToDisplay, 2f*ppuX,2f*ppuY);
		 		
		 spriteBatch.end();

			if (debug)
				drawDebug();
		  }
	

	int i=0;
	int random=2;
	private Background background;
	
	private void drawParticles(float delta) {
		
		// TODO Auto-generated method stub
		rainDrops=world.getRainDrops();
		
		
		for (int a=0; a<rainDrops.length;a++){
			rainDrops[a].start();
			rainDrops[a].position.y-=0.1;
//			System.out.println(rainDrops[a].position.y);
			//Gdx.graphics.getHeight()/2
			rainDrops[a].setPosition(rainDrops[a].position.x*ppuX,(rainDrops[a].position.y)*ppuY);
			//rainDrops[a].setPosition(rainDrops[a].position.x*ppuX,rainDrops[a].position.y*ppuY);
			rainDrops[a].draw(spriteBatch,delta);
			if(rainDrops[a].isComplete()||rainDrops[a].position.y<0){
				rainDrops[a].position.y=Gdx.graphics.getHeight()/ppuY;
				//rainDrops[a].position.y=Gdx.graphics.getHeight();
				rainDrops[a].dispose();
				rainDrops[a].load(effectFile, textureAtlas);
				rainDrops[a].start();
    		}
			
        }
		
		
	}
	
	private void drawRain() {
		
		spriteBatch.draw(rainTexture, 2f*ppuX,2f*ppuY );
	}
	private void drawBackground() {
		// TODO Auto-generated method stub
		
		background = world.getBackground();
		spriteBatch.draw(backgroundTexture, background.position.x * ppuX, background.position.y * ppuX, Background.SIZE* ppuX, Background.SIZE* ppuX);
		
	}
	
	int facex=1;
	private Bob bob;
	private void drawBob() {
		bob = world.getBob();

		
		if(bob.facingLeft){
			facex=-1;
		}else{
			facex=1;
		}

		if (bob.state==bob.state.WALKING){
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        spriteBatch.draw(currentFrame, bob.position.x * ppuX, bob.position.y * ppuY, facex*Bob.SIZE * ppuX, Bob.SIZE * ppuY);

		}
		else if(bob.state==bob.state.IDLE){
			spriteBatch.draw(bobTexture, bob.position.x * ppuX, bob.position.y * ppuY, facex*Bob.SIZE * ppuX, Bob.SIZE * ppuY);
		}


	}
	private void drawDebug() {
		// render blocks
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
		for (Block block : world.getBlocks()) {
			Rectangle rect = block.bounds;
			float x1 = block.position.x + rect.x;
			float y1 = block.position.y + rect.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		// render Bob
		Bob bob = world.getBob();
		Rectangle rect = bob.bounds;
		float x1 = bob.position.x + rect.x;
		float y1 = bob.position.y + rect.y;
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();
	}
 }