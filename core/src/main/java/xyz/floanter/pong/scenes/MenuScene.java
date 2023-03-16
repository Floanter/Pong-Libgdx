package xyz.floanter.pong.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import space.earlygrey.shapedrawer.ShapeDrawer;
import xyz.floanter.pong.util.Globals;
import xyz.floanter.pong.util.Scene;

import static xyz.floanter.pong.Pong.sceneManager;

public class MenuScene extends Scene
{
    private final BitmapFont font;
    public MenuScene()
    {
        TextureRegion fontTexture = new TextureRegion(new Texture("font.png"));
        font = new BitmapFont(Gdx.files.internal("font.fnt"), fontTexture);
    }

    @Override
    public void handleInput()
    {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            sceneManager.changeScene(new PlayScene());
    }

    @Override
    public void update(float delta)
    {

        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeDrawer shapeDrawer)
    {
        spriteBatch.begin();

        font.draw(spriteBatch, "Welcome to Pong!",Globals.Virtual_Width / 2 - 140, Globals.Virtual_Height - 30);
        font.draw(spriteBatch, "Press ENTER", Globals.Virtual_Width / 2 - 100, Globals.Virtual_Height / 2 - 10);

        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        font.dispose();
    }
}
