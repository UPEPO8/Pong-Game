package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
	int x;
	int y;
	int size;
	int xSpeed;
	int ySpeed;
	//float MAX_REFLECTION_ANGLE = 10;
	Color color = Color.WHITE;
	public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	
	public void update() {
		x += xSpeed;
		y += ySpeed;
		
		
		if(x > Gdx.graphics.getWidth()) {
			xSpeed = -xSpeed;
		}
		if(x < 0) {
			xSpeed = -xSpeed;
		}
		
		
		if(y > Gdx.graphics.getHeight()) {
			ySpeed = -ySpeed;
		}
		if(y < 0) {
			ySpeed = -ySpeed;
		}
			
	}
	
	public void draw(ShapeRenderer shape) {
		shape.setColor(color);
		shape.circle(x, y, size);
	}
	
	public void drawLine(ShapeRenderer shape) {
		shape.setColor(Color.WHITE);
		shape.rect(Gdx.graphics.getWidth()/2, 0, 3, 600);
	}
	
	public void checkCollision(playerOne paddleOne, playerTwo paddleTwo) {
			 if (collidesWith(paddleOne)) {
			     xSpeed = -xSpeed;
			     ySpeed = ySpeed;	
			 } 
			 if(collidesWith(paddleTwo)) {
				 xSpeed = -xSpeed;
			     ySpeed = ySpeed;	
			 }
	}
	
	private boolean collidesWith(playerOne paddleOne) {
		float paddleLeft = paddleOne.x;
	    float paddleRight = paddleOne.x + paddleOne.width;
	    float paddleTop = paddleOne.y;
	    float paddleBottom = paddleOne.y + paddleOne.height;

	    // Calculate the closest point on the paddle to the center of the ball
	    float closestX = clamp(x, paddleLeft, paddleRight);
	    float closestY = clamp(y, paddleTop, paddleBottom);

	    // Calculate the distance between the closest point and the center of the ball
	    float distanceX = x - closestX;
	    float distanceY = y - closestY;
	    float distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);

	    // Check if the distance is less than the radius of the ball
	    return distanceSquared < (size * size);
		
	}
	
	private boolean collidesWith(playerTwo paddleTwo) {
		float paddleLeft = paddleTwo.x;
	    float paddleRight = paddleTwo.x + paddleTwo.width;
	    float paddleTop = paddleTwo.y;
	    float paddleBottom = paddleTwo.y + paddleTwo.height;

	    // Calculate the closest point on the paddle to the center of the ball
	    float closestX = clamp(x, paddleLeft, paddleRight);
	    float closestY = clamp(y, paddleTop, paddleBottom);

	    // Calculate the distance between the closest point and the center of the ball
	    float distanceX = x - closestX;
	    float distanceY = y - closestY;
	    float distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);

	    // Check if the distance is less than the radius of the ball
	    return distanceSquared < (size * size);
		
	}
	private float clamp(float value, float min, float max) {
	    return Math.max(min, Math.min(max, value));
	}
}

