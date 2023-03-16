package xyz.floanter.pong.paddle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import space.earlygrey.shapedrawer.ShapeDrawer;
import xyz.floanter.pong.util.Globals;

public class Paddle
{
    private final Rectangle self;
    private final int upKey;
    private final int downKey;
    private float deltaY;
    private int points;
    public Paddle(Rectangle options, int whichPlayerAmI)
    {
        self    = options;
        upKey   = (whichPlayerAmI == 1) ? Input.Keys.W : Input.Keys.UP;
        downKey = (whichPlayerAmI == 1) ? Input.Keys.S : Input.Keys.DOWN;
        deltaY  = 0;
        points  = 0;
    }

    public void update(float delta)
    {
        boolean isUpKeyPressed   = Gdx.input.isKeyPressed(upKey);
        boolean isDownKeyPressed = Gdx.input.isKeyPressed(downKey);
        boolean deltaYIsUpBorder = deltaY > 0;

        if (isUpKeyPressed)
            deltaY += Globals.Paddle_Speed * delta;
        else if (isDownKeyPressed)
            deltaY -= Globals.Paddle_Speed * delta;
        else
            deltaY = 0;

        if (deltaYIsUpBorder)
            //System.out.println(Math.min(Globals.Window_Height - self.height, self.y + deltaY * delta));
            self.y = Math.min(225, self.y + deltaY * delta);
        else
            self.y = Math.max(0, self.y + deltaY * delta);
    }

    public void render(SpriteBatch spriteBatch, ShapeDrawer shapeDrawer)
    {
        spriteBatch.begin();
        shapeDrawer.filledRectangle(self, Globals.Things_Color);
        spriteBatch.end();
    }

    public void addPoint()
    {
        points += 1;
    }

    public int getPoints()
    {
        return points;
    }

    public Rectangle getPaddleRectangle()
    {
        return self;
    }
}
