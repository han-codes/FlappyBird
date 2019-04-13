package com.csci4020.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;

	/**
	 * Runs when app begins
	 */
	@Override
	public void create () {
	    // manages displaying the sprites
		batch = new SpriteBatch();
        background = new Texture(("bg.png"));
	}

	/**
	 * Runs continuously while app is running
	 */
	@Override
	public void render () {

	    // start displaying sprits
	    batch.begin();
	    // take up the whole screen
	    batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    // end drawing sprites
        batch.end();
	}
	
	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
	}
}
