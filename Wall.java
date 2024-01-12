import java.awt.*;
public class Wall extends Block
{
    private String direction;
    private int speed;
    
    public Wall ()
    {
        direction = "none";
    }
    
    public void set (Color c, int xPos, int yPos, int l, int w, String t, int k)
    {
        super.set(c, xPos, yPos, l, w);
        direction = t;
        speed = k;
    }
    
    public void move ()
    {
        if (direction.equals("vertical"))
        {
            //tbd
            
        }
        else if (direction.equals("horizontal"))
        {
            //tbd
            
        }
    }
}