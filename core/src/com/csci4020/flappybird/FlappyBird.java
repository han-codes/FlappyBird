package com.csci4020.flappybird;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import org.w3c.dom.css.Rect;

enum FlapStates
{
	FLAP_UP,
	FLAP_DOWN;
}

enum GameStates
{
	GAME_PAUSED,
	GAME_RUNNING,
	GAME_OVER;
}

public class FlappyBird extends ApplicationAdapter
{
	// Set up global variables
	private Texture background;
	private Texture[] birds;
	private Texture gameover;
	private Texture topTube;
	private Texture bottomTube;

	// Prepare some game variables
	private final float tubeGap = 500;
	private float maxTubeOffset;
	private final float tubeVelocity = 4;
	private final int numberOfTubes = 3;
	private float distanceBetweenTubes;
	private float[] tubeX = new float[numberOfTubes];
	private float[] tubeOffset = new float[numberOfTubes];
	private Rectangle[] topTubeRectangles = new Rectangle[numberOfTubes];
	private Rectangle[] bottomTubeRectangles = new Rectangle[numberOfTubes];

	private Circle birdCircle = new Circle();
	private FlapStates flapState = FlapStates.FLAP_DOWN;
	private float birdY = 0;
	private float birdVelocity = 0;

	private Random randomGenerator = new Random();
	private int scoringTube = 0;
	private int score = 0;

	private GameStates gameState = GameStates.GAME_PAUSED;

	SpriteBatch batch = new SpriteBatch();

	/**
	 * This method is the first method to get called
	 */
	@Override
	public void create()
	{
		// Set up the images as textures
		background = new Texture("bg.png");
		gameover = new Texture("gameover.png");
		topTube = new Texture("toptube.png");
		bottomTube = new Texture("bottomtube.png");

		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");

		// Set up some game constants
		distanceBetweenTubes = Gdx.graphics.getWidth() / 2;
		maxTubeOffset = Gdx.graphics.getHeight() / 2 - tubeGap;

		startGame();
	}


	/**
	 *
	 */
	public void startGame()
	{
		// Set the starting point of the bird
		birdY = Gdx.graphics.getHeight() / 2 - birds[0].getHeight() / 2;

		for (int i = 0; i < numberOfTubes; i++)
		{
			tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - tubeGap);

			// Reset tube X
			tubeX[i] = Gdx.graphics.getWidth() / 2 - topTube.getWidth() / 2 + Gdx.graphics.getWidth() + i * distanceBetweenTubes;

			topTubeRectangles[i] = new Rectangle();
			bottomTubeRectangles[i] = new Rectangle();
		}
	}

	/**
	 * This method runs continuously
	 */
	@Override
	public void render()
	{
		// Initialize the sprite batch
		batch.begin();
		// sprites are layered by what is drawn first
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if (gameState == GameStates.GAME_RUNNING)
		{
			// If the bird has passed a tube, manage score
			if (tubeX[scoringTube] < Gdx.graphics.getWidth())
			{
				score++;

				if (scoringTube < numberOfTubes - 1)
					scoringTube++;
				else
					scoringTube = 0;
			}

			// Move bird on screen tap
			if (Gdx.input.justTouched())
				birdVelocity = -30;

			// Manage tubes that need to be drawn
			for (int i = 0; i < numberOfTubes; i++)
			{
				// Move a tube back to the start after it goes off screen
				if (tubeX[i] < -topTube.getWidth())
				{
					tubeX[i] += numberOfTubes * distanceBetweenTubes;
					tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - tubeGap); // TODO Might need to hard value adjust
				}
				else
					tubeX[i] -= tubeVelocity;

				// Draw the tubes
				batch.draw(topTube, tubeX[i], Gdx.graphics.getHeight() / 2 + tubeGap / 2 + tubeOffset[i]);
				batch.draw(bottomTube, tubeX[i], Gdx.graphics.getHeight() / 2 - tubeGap / 2 - bottomTube.getHeight() + tubeOffset[i]);
			}
		}
		else if (gameState == GameStates.GAME_PAUSED)
		{
			if (Gdx.input.justTouched())
				gameState = GameStates.GAME_RUNNING;
		}
		else if (gameState == GameStates.GAME_OVER)
		{
			// Display the game over graphic and pause
			batch.draw(gameover, Gdx.graphics.getWidth() / 2 - gameover.getWidth() / 2, Gdx.graphics.getHeight() / 2 - gameover.getHeight() / 2);

			// TODO RESET THE GAME
		}

		// Alternate the flap state
		if (flapState == FlapStates.FLAP_UP)
			flapState = FlapStates.FLAP_DOWN;
		else
			flapState = FlapStates.FLAP_UP;

		// Finish drawing the rest of the sprites
		batch.draw(birds[flapState.ordinal()], Gdx.graphics.getWidth() / 2 - birds[flapState.ordinal()].getWidth() / 2, birdY);

		// All sprites have been drawn on the gameboard
		batch.end();

		// Draw the collision
		birdCircle.set(Gdx.graphics.getWidth() / 2, birdY + birds[flapState.ordinal()].getHeight() / 2, birds[flapState.ordinal()].getWidth() / 2);

		for (int i = 0; i < numberOfTubes; i++)
		{
			if (Intersector.overlaps(birdCircle, topTubeRectangles[i]) || Intersector.overlaps(birdCircle, bottomTubeRectangles[i]))
			{
				gameState = GameStates.GAME_OVER;
			}
		}
	}
}