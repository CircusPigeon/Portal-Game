import java.awt.*;
public class Spring extends Block
{
    private int springiness;
    private String type;
    
    public Spring ()
    {
    }
    
    public void set (Color c, int xPos, int yPos, int l, int w, int k, String t)
    {
        super.set(c, xPos, yPos, l, w);
        springiness = k;
        type = t;
    }
    
    public int getSpringiness ()
    {
        return springiness;
    }
    
    public String getType ()
    {
        return type;
    }
}