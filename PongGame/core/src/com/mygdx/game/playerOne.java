package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class playerOne {
	public static final float SPEED = 10;
	float x;
	float y;
	float height = 100;
	float width = 15;
	
	Color colorOne = Color.CYAN;
	public playerOne(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void movement() {
		if(Gdx.input.isKeyPressed(Keys.W)) {
			y += SPEED;
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
			y -= SPEED;
		}
		if (y < 0) {
	        y = 0;
	    }
	    if (y + height > Gdx.graphics.getHeight()) {
	        y = Gdx.graphics.getHeight() - height;
	    }
	    
	    if(Gdx.input.isKeyPressed(Keys.D)) {
			x += SPEED;
		}
		if(Gdx.input.isKeyPressed(Keys.A)) {
			x -= SPEED;
		}
		if (x < 0) {
	        x = 0;
	    }
	    if (x + height > Gdx.graphics.getWidth()) {
	        x = Gdx.graphics.getWidth() - width;
	    }
	}
	
	
		
	
	public void draw(ShapeRenderer shape) {
		shape.setColor(colorOne);
		shape.rect(x,y, width,height);
		
	}
}
