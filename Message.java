import greenfoot.*;
import java.awt.Color;
 
public class Message extends Actor
{
    public Message()
    {
        updateImage("");
    }
     
    public Message(String text)
    {
        updateImage(text);
    }
     
    public void setText(String text)
    {
        updateImage(text);
    }
     
    private void updateImage(String text)
    {
        setImage(new GreenfootImage(text, 30, Color.black, Color.white));
    }
}