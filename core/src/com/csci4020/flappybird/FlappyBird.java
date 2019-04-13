package com.csci4020.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;

	Texture[] birds;

	// keep track of which bird we'd like to display
	int flapState = 0;
	float birdY = 0;
	float velocity = 0;

	// keeps track of the state of the game
	int gameState = 0;
	float gravity = 2;

	Texture topTube;
	Texture bottomTube;

	// distance between the pipes
	float gap = 400;
	// to set different sizes of the tubes
	float maxTubeOffset;
    float tubeOffset;
	Random randomGenerator;

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
        birdY = Gdx.graphics.getHeight() / 2 - birds[0].getHeight() / 2;

        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        maxTubeOffset = Gdx.graphics.getHeight() / 2 - gap / 2 - 100;
        randomGenerator = new Random();
	}

	/**
	 * Runs continuously while app is running
	 */
	@Override
	public void render () {

	    // Draw background first so it doesn't cover up the bird and pipes
        // start displaying sprits
        batch.begin();
        // take up the whole screen
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (gameState != 0) {

            batch.draw(topTube, Gdx.graphics.getWidth() / 2 - topTube.getWidth() / 2, Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset);
            batch.draw(bottomTube, Gdx.graphics.getWidth() / 2 - bottomTube.getWidth() / 2, Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset);

            // moves bird up when tapped
            if (Gdx.input.justTouched()) {

                velocity = -30;
                // 0.5 because we want 0.5 or -0.5 since we're working in center of the screen
                // used to shift the pipes up and down. half of height minus the gap - 100 for each pipe
                tubeOffset = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
            }

            if (birdY > 0 || velocity < 0) {

                // effect of gravity
                velocity += gravity;
                birdY -= velocity;
            }
        }
        else {

            if (Gdx.input.justTouched()) {

                // will begin the velocity and movement of the bird
                gameState = 1;
            }
        }

        if (flapState == 0) {
            flapState = 1;
        }
        else {
            flapState = 0;
        }

        // draw bird in the center of screen
        batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth() / 2, birdY);
        // end drawing sprites
        batch.end();
	}
	
	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
	}
}
