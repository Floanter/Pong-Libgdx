package xyz.floanter.pong.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class SceneManager
{
    private Scene currentScene;

    public SceneManager(Scene initialScene)
    {
        currentScene = initialScene;
    }

    public void changeScene(Scene newScene)
    {
        currentScene = newScene;
    }

    public void update(float delta)
    {
        currentScene.update(delta);
    }

    public void render(SpriteBatch spriteBatch, ShapeDrawer shapeDrawer)
    {
        currentScene.render(spriteBatch, shapeDrawer);
    }

    public void dispose() { currentScene.dispose(); }
}
