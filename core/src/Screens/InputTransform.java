package Screens;

public class InputTransform
{
    private static int appWidth = 480;
    private static int appHeight = 320;

    public static float getCursorToModelX(int screenX, int cursorX) 
    {
        return (((float)cursorX) * appWidth) / ((float)screenX); 
    }

    public static float getCursorToModelY(int screenY, int cursorY) 
    {
        return ((float)(screenY - cursorY)) * appHeight / ((float)screenY) ; 
    }
}