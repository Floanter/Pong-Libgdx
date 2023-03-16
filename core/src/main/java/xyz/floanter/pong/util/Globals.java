package xyz.floanter.pong.util;

import com.badlogic.gdx.graphics.Color;

public class Globals
{
    public static final int Window_Width = 1280;
    public static final int Window_Height = 720;
    public static final float Virtual_Width = 432;
    public static final float Virtual_Height = 243;
    public static final String Title = "Pong!";
    public static final Color Things_Color = new Color(0.7843f, 0.7843f, 0.7843f, 1f);
    public static final float Paddle_Speed = 400;
    private Globals() {}

    public static float getRandomNumber(int min, int max)
    {
        return (float) (Math.random() * (max - min) + min);
    }
}
