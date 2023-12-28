package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class PongAI {
    private playerTwo paddleTwo;
    private Ball ball;

    public PongAI(playerTwo paddleTwo, Ball ball) {
        this.paddleTwo = paddleTwo;
        this.ball = ball;
    }

    public void update() {
        
        float aiCenterY = paddleTwo.y + paddleTwo.height / 2.0f;
        float ballCenterY = ball.y+ ball.size / 2.0f;

        // Adjust the AI paddle's position based on the ball's position
        if (aiCenterY < ballCenterY) {
            // Move AI paddle up
            paddleTwo.y += playerTwo.SPEED;
        } else if (aiCenterY > ballCenterY) {
            // Move AI paddle down
            paddleTwo.y -= playerTwo.SPEED;
        }

        // Ensure the AI paddle stays within the screen boundaries
        if (paddleTwo.y < 0) {
            paddleTwo.y = 0;
        }
        if (paddleTwo.y + paddleTwo.height > Gdx.graphics.getHeight()) {
            paddleTwo.y = Gdx.graphics.getHeight() - paddleTwo.height;
        }
    }
}