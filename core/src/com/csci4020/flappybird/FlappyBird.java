package com.csci4020.flappybird;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

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
	private Rectangle[] topTubeRectangles;
	private Rectangle[] bottomTubeRectangles;



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

	}

	public void startGame()
	{

	}

	/**
	 * This method runs continuously
	 */
	@Override
	public void render()
	{

	}

	@Override
	public void dispose()
	{

	}
}