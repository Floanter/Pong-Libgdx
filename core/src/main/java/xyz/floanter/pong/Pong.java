package xyz.floanter.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import space.earlygrey.shapedrawer.ShapeDrawer;
import xyz.floanter.pong.scenes.MenuScene;
import xyz.floanter.pong.util.Globals;
import xyz.floanter.pong.util.SceneManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Pong extends ApplicationAdapter
{
    private SpriteBatch spriteBatch;
    private ShapeDrawer shapeDrawer;
    private Camera camera;
    private Viewport viewport;

    public static SceneManager sceneManager;
    private Texture texture;
    @Override
    public void create()
    {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap);
        pixmap.dispose();

        TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);
        spriteBatch  = new SpriteBatch();
        shapeDrawer  = new ShapeDrawer(spriteBatch, region);
        camera       = new OrthographicCamera(Globals.Virtual_Width, Globals.Virtual_Height);
        viewport     = new FitViewport(Globals.Virtual_Width, Globals.Virtual_Height, camera);
        sceneManager = new SceneManager(new MenuScene());
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0.1333f, 0.1333f, 0.1333f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        sceneManager.update(Gdx.graphics.getDeltaTime());
        sceneManager.render(spriteBatch, shapeDrawer);
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose()
    {
        sceneManager.dispose();
        spriteBatch.dispose();
        texture.dispose();
    }
}
