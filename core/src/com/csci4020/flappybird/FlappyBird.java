package com.csci4020.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;

	Texture[] birds;

	// keep track of which bird we'd like to display
	int flapState = 0;

	/**
	 * Runs when app begins
	 */
	@Override
	public void create () {
	    // manages displaying the sprites
		batch = new SpriteBatch();
        background = new Texture(("bg.png"));
        birds = new Texture[2];
        birds[0] = new Texture("bird.png");
        birds[1] = new Texture("bird2.png");
	}

	/**
	 * Runs continuously while app is running
	 */
	@Override
	public void render () {

	    if (Gdx.input.justTouched()) {

	        Gdx.app.log("Touch","Tapped screen");
        }

	    if (flapState == 0) {
	        flapState = 1;
        }
        else {
            flapState = 0;
        }
	    // start displaying sprits
	    batch.begin();
	    // take up the whole screen
	    batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    // draw bird in the center of screen
	    batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth() / 2, Gdx.graphics.getHeight() / 2 - birds[flapState].getHeight() / 2);
	    // end drawing sprites
        batch.end();
	}
	
	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
	}
}
