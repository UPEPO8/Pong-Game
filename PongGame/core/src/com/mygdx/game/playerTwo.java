package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class playerTwo {
	
	public static final float SPEED = 10;
	float x;
	float y;
	float height = 100;
	float width = 15;
	
	public playerTwo(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void movement() {
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			y += SPEED;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= SPEED;
		}
		if (y < 0) {
	        y = 0;
	    }
	    if (y + height > Gdx.graphics.getHeight()) {
	        y = Gdx.graphics.getHeight() - height;
	    }
	}
		

	public void draw(ShapeRenderer shape) {
		shape.setColor(Color.GREEN);
		shape.rect(x, y, width, height);
	}

	public void moveUp() {
		// TODO Auto-generated method stub
		
	}

	public void moveDown() {
		// TODO Auto-generated method stub
		
	}
}
