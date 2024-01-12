import java.awt.*;
public class Goal
{
    private Color color;
    private int x, y, size;
    
    public Goal ()
    {
        size = 15;
    }
    
    public void draw (Graphics page)
    {
        page.setColor(color);
        page.fillRect(x, y, size, size);
    }
    
    public void set (Color c, int xPos, int yPos)
    {
        color = c;
        x = xPos;
        y = yPos;
    }
    
    public void hide ()
    {
        size = 0;
    }
    
    public int getX ()
    {
        return x;
    }
    
    public int getY ()
    {
        return y;
    }
    
    public int getSize ()
    {
        return size;
    }
    
    public Color getColor ()
    {
        return color;
    }
}