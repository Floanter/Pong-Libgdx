package xyz.floanter.pong.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import space.earlygrey.shapedrawer.ShapeDrawer;

public abstract class Scene
{
    public abstract void handleInput();
    public abstract void update(float delta);
    public abstract void render(SpriteBatch spriteBatch, ShapeDrawer shapeDrawer);
    public abstract void dispose();
}
