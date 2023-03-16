package xyz.floanter.pong.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import space.earlygrey.shapedrawer.ShapeDrawer;
import xyz.floanter.pong.ball.Ball;
import xyz.floanter.pong.paddle.Paddle;
import xyz.floanter.pong.util.Globals;
import xyz.floanter.pong.util.Scene;

import static xyz.floanter.pong.Pong.sceneManager;

public class PlayScene extends Scene
{
    private enum States
    {
        Serve,
        Go,
        Win
    }

    private States currentState;
    private final Paddle player1;
    private final Paddle player2;
    private final Ball ball;
    private final BitmapFont font;

    private final Sound hit;
    private final Sound point;
    public PlayScene()
    {
        player1 = new Paddle(new Rectangle(10, Globals.Virtual_Height - 30, 5, 20), 1);
        player2 = new Paddle(new Rectangle(Globals.Virtual_Width - 5 - 10, 10, 5, 20), 2);
        ball    = new Ball(new Rectangle(Globals.Virtual_Width / 2 - 2.5f, Globals.Virtual_Height / 2 - 2.5f, 5, 5));

        TextureRegion fontTexture = new TextureRegion(new Texture("font.png"));

        font         = new BitmapFont(Gdx.files.internal("font.fnt"), fontTexture);
        hit          = Gdx.audio.newSound(Gdx.files.internal("hit.wav"));
        point        = Gdx.audio.newSound(Gdx.files.internal("point.wav"));
        currentState = States.Serve;
    }

    @Override
    public void handleInput() {}

    @Override
    public void update(float delta)
    {
        player1.update(delta);
        player2.update(delta);

        switch (currentState)
        {
            case Go:
                playStateLogic(delta);
                break;
            case Serve:
                serveStateLogic();
                break;
            case Win:
                winStateLogic();
                break;
            default:
                break;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeDrawer shapeDrawer)
    {
        drawLines(spriteBatch, shapeDrawer);
        player1.render(spriteBatch, shapeDrawer);
        player2.render(spriteBatch, shapeDrawer);
        ball.render(spriteBatch, shapeDrawer);

        spriteBatch.begin();
        switch (currentState)
        {
            case Go:
                drawScores(spriteBatch);
                break;
            case Serve:
                font.draw(spriteBatch,"Press ENTER to serve", Globals.Virtual_Width / 2f - 180f, Globals.Virtual_Height / 2f - 10f);
                drawScores(spriteBatch);
                break;
            case Win:
                String playerThatWin = (player1.getPoints() == 10) ? "Player 1 Win!" : "Player 2 Win!";
                font.draw(spriteBatch, playerThatWin, Globals.Virtual_Width / 2 - 110, Globals.Virtual_Height / 2 + 10);
                break;
            default:
                break;
        }
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        font.dispose();
    }


    private void drawLines(SpriteBatch spriteBatch, ShapeDrawer shapeDrawer)
    {
        spriteBatch.begin();
        for (float i = 0; i < Globals.Virtual_Height; i+=20)
            shapeDrawer.filledRectangle((float) (Globals.Virtual_Width / 2 - 1.75), i, 3.5f, 10f, Globals.Things_Color);
        spriteBatch.end();
    }

    private void drawScores(SpriteBatch spriteBatch)
    {
        font.draw(spriteBatch, "" + player1.getPoints(), Globals.Virtual_Width / 2 - 50, Globals.Virtual_Height - Globals.Virtual_Height / 3);
        font.draw(spriteBatch, "" + player2.getPoints(), Globals.Virtual_Width / 2 + 30, Globals.Virtual_Height - Globals.Virtual_Height / 3);
    }

    private void winStateLogic()
    {
        boolean isEnterPressed = Gdx.input.isKeyPressed(Input.Keys.ENTER);
        if (isEnterPressed) sceneManager.changeScene(new MenuScene());
    }

    private void serveStateLogic()
    {
        ball.setRandomDeltaSpeed((int) Globals.getRandomNumber(1, 2));

        boolean IsEnterPressed = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        if (IsEnterPressed) currentState = States.Go;
        if (player1.getPoints() == 10 || player2.getPoints() == 10) currentState = States.Win;
    }
    private void playStateLogic(float delta)
    {
        ball.update(delta);

        if (ball.isCollidingWithScreenEdges())
        {
            hit.play();
            ball.reverseDeltaYSpeed();
        }
        boolean ballIsCollidingWithPlayer1 = ball.isCollidingWithPaddle(player1.getPaddleRectangle());
        boolean ballIsCollidingWithPlayer2 = ball.isCollidingWithPaddle(player2.getPaddleRectangle());
        if (ballIsCollidingWithPlayer1 || ballIsCollidingWithPlayer2)
        {
            hit.play();
            ball.deltaSpeed.x = -ball.deltaSpeed.x * 1.03f;
            ball.deltaSpeed.y = -ball.deltaSpeed.y * 1.03f;
            ball.setRandomDeltaSpeedY();
        }

        if (ball.exitsLeftSide())
        {
            player2.addPoint();
            point.play();
            ball.reset();
            currentState = States.Serve;
        }

        if (ball.exitsRightSide())
        {
            player1.addPoint();
            point.play();
            ball.reset();
            currentState = States.Serve;
        }
    }
}
