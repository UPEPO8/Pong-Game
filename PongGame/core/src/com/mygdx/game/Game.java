package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Game extends ApplicationAdapter {
    ShapeRenderer shape;
    Ball ball;
    playerOne player1;
    playerTwo player2;
    PongAI pongAI;
    private BitmapFont font;
    private SpriteBatch spriteBatch;
    public int player1Score;
    public int player2Score;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        ball = new Ball(150, 200, 10, 5, 5, 0, 0); // Initial scores set to 0
        player1 = new playerOne(2, Gdx.graphics.getHeight() / 2);
        player2 = new playerTwo(Gdx.graphics.getWidth() - 18, (Gdx.graphics.getHeight()) / 2);
        pongAI = new PongAI(player2, ball);
        font = new BitmapFont();
        spriteBatch = new SpriteBatch();
        player1Score = 0;
        player2Score = 0;
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shape.begin(ShapeRenderer.ShapeType.Filled);
        player1.movement();
        player2.movement();
        ball.update(this);
     
        ball.draw(shape);
        ball.drawLine(shape);
        player1.draw(shape);
        player2.draw(shape);
        shape.end();

        renderScoreboards();
        ball.checkCollision(player1, player2);
    }

    private void renderScoreboards() {
        spriteBatch.begin();
        font.getData().setScale(2); // Set the font size
        font.setColor(Color.WHITE);

        // Player 1 Scoreboard (Top Left)
        String player1ScoreText = "Player 1: " + player1Score;
        GlyphLayout bounds1 = font.draw(spriteBatch, player1ScoreText, 0, 0);
        font.draw(spriteBatch, player1ScoreText, 10, Gdx.graphics.getHeight() - bounds1.height);

        // Player 2 Scoreboard (Top Right)
        String player2ScoreText = "Player 2: " + player2Score;
        GlyphLayout bounds2 = font.draw(spriteBatch, player2ScoreText, 0, 0);
        font.draw(spriteBatch, player2ScoreText, Gdx.graphics.getWidth() - bounds2.width - 10, Gdx.graphics.getHeight() - bounds2.height);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        shape.dispose();
        spriteBatch.dispose();
        font.dispose();
    }
}
