package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shape;
	Ball ball;
	playerOne player1;
	playerTwo player2;
	public static final int SPEED = 12;
	float x = 50;
	float y = 50;
	float xSpeed = 3;
	
	public void create () {
		shape = new ShapeRenderer();
		ball = new Ball(150,200,10,SPEED,5);
		player1 = new playerOne(2,Gdx.graphics.getHeight() / 2);
		player2 = new playerTwo(Gdx.graphics.getWidth()- 18,(Gdx.graphics.getHeight())/2);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		player1.movement();
		player2.movement();
		ball.update();
		ball.draw(shape);
		ball.drawLine(shape);
		player1.draw(shape);
		player2.draw(shape);
		shape.end();
		ball.checkCollision(player1,player2);
	}
	
	@Override
	public void dispose () {
		shape.dispose();
	}
}
