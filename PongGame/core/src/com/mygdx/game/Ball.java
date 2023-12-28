package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    Color color = Color.WHITE;
    private long lastResetTime = 0;
    private static final long RESET_DELAY = 3000; // 3 seconds in milliseconds

    private BitmapFont font;  // Add a BitmapFont for rendering text
    private SpriteBatch spriteBatch;  // Add a SpriteBatch for rendering text

    public Ball(int x, int y, int size, int xSpeed, int ySpeed, int player1Score, int player2Score) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        font = new BitmapFont();
        spriteBatch = new SpriteBatch();
    }

    public void update(Game game) {
        if (lastResetTime > 0) {
            long elapsedTime = System.currentTimeMillis() - lastResetTime;
            if (elapsedTime < RESET_DELAY) {
                int secondsRemaining = (int) ((RESET_DELAY - elapsedTime) / 1000);

                // Timer
                spriteBatch.begin();
                font.draw(spriteBatch, "Ball Spawning in: " + secondsRemaining + " seconds", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() - 50);
                spriteBatch.end();
            } else {
                lastResetTime = 0;
                resetBall(game);  
            }
        } else { //Scorer
            if (x < 0) {
              
                game.player2Score++;
                resetBall(game);
            } else if (x > Gdx.graphics.getWidth()) {
               
                game.player1Score++;
                resetBall(game);
            }

            if (y > Gdx.graphics.getHeight() || y < 0) {
                ySpeed = -ySpeed;
            }
            
        }
        x += xSpeed;
        y += ySpeed;
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
    }

    public void drawLine(ShapeRenderer shape) {
        shape.setColor(Color.WHITE);
        shape.rect(Gdx.graphics.getWidth() / 2, 0, 3, 600);
    }

    public void checkCollision(playerOne paddleOne, playerTwo paddleTwo) {
        if (collidesWith(paddleOne) || collidesWith(paddleTwo)) {
            xSpeed = -xSpeed;
            ySpeed = ySpeed;
        }
    }

    private boolean collidesWith(playerOne paddleOne) {
        float paddleLeft = paddleOne.x;
        float paddleRight = paddleOne.x + paddleOne.width;
        float paddleTop = paddleOne.y;
        float paddleBottom = paddleOne.y + paddleOne.height;

        float closestX = clamp(x, paddleLeft, paddleRight);
        float closestY = clamp(y, paddleTop, paddleBottom);

        float distanceX = x - closestX;
        float distanceY = y - closestY;
        float distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);

        return distanceSquared < (size * size);
    }

    private boolean collidesWith(playerTwo paddleTwo) {
        float paddleLeft = paddleTwo.x;
        float paddleRight = paddleTwo.x + paddleTwo.width;
        float paddleTop = paddleTwo.y;
        float paddleBottom = paddleTwo.y + paddleTwo.height;

        float closestX = clamp(x, paddleLeft, paddleRight);
        float closestY = clamp(y, paddleTop, paddleBottom);

        float distanceX = x - closestX;
        float distanceY = y - closestY;
        float distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);

        return distanceSquared < (size * size);
    }

    private float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    private void resetBall(Game game) {
        x = Gdx.graphics.getWidth() / 2;
        y = Gdx.graphics.getHeight() / 2;
        xSpeed = -xSpeed;
        ySpeed = -ySpeed;
        lastResetTime = System.currentTimeMillis();
    }

}
