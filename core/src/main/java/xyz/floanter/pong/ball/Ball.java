package xyz.floanter.pong.ball;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import space.earlygrey.shapedrawer.ShapeDrawer;
import xyz.floanter.pong.util.Globals;

public class Ball
{
    private final Rectangle self;
    public Vector2 deltaSpeed;
    public Ball(Rectangle options)
    {
        self = options;
        deltaSpeed = new Vector2(0, 0);
    }

    public void update(float delta)
    {
        self.x += deltaSpeed.x * delta;
        self.y += deltaSpeed.y * delta;
    }

    public void render(SpriteBatch spriteBatch, ShapeDrawer shapeDrawer)
    {
        spriteBatch.begin();
        shapeDrawer.filledRectangle(self, Globals.Things_Color);
        spriteBatch.end();
    }

    public void setRandomDeltaSpeed(int toPlayerSide)
    {
        deltaSpeed.x = (toPlayerSide == 1) ? Globals.getRandomNumber(140, 200) : -Globals.getRandomNumber(140, 200);
        deltaSpeed.y = Globals.getRandomNumber(-50, 50);
    }

    public void setRandomDeltaSpeedY()
    {
        if (deltaSpeed.y > 0)
            deltaSpeed.y = -Globals.getRandomNumber(10, 150);
        else
            deltaSpeed.y = Globals.getRandomNumber(10, 150);
    }
    public void reset()
    {
        self.x       = Globals.Virtual_Width  / 2 - self.width  / 2;
        self.y       = Globals.Virtual_Height / 2 - self.height / 2;
        deltaSpeed.x = 0;
        deltaSpeed.y = 0;
    }

    public boolean isCollidingWithPaddle(Rectangle paddleRectangle)
    {
        boolean isLeftEdgeFurther  = self.x > paddleRectangle.x + paddleRectangle.width || paddleRectangle.x > self.x + self.width;
        boolean isBottomEdgeHigher = self.y > paddleRectangle.y + paddleRectangle.height || paddleRectangle.y > self.y + self.height;
        return (isLeftEdgeFurther || isBottomEdgeHigher) ? false : true;
    }

    public boolean isCollidingWithScreenEdges()
    {
        boolean isCollidingWithTopEdge    = self.y >= Globals.Virtual_Height;
        boolean isCollidingWithBottomEdge = self.y <= 0;
        return (isCollidingWithBottomEdge || isCollidingWithTopEdge) ? true : false;
    }

    public void reverseDeltaYSpeed()
    {
        deltaSpeed.y = -deltaSpeed.y;
    }

    public boolean exitsRightSide()
    {
        return self.x >= Globals.Virtual_Width;
    }

    public boolean exitsLeftSide()
    {
        return self.x <= 0;
    }
}
