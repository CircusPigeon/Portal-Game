import java.awt.*;
public class Block
{
    private Color color;
    private int x, y, length, width;
    
    public Block ()
    {
        x = 0;
        y = 0;
        length = 0;
        width = 0;
    }
    
    public void draw (Graphics page)
    {
        page.setColor(color);
        page.fillRect(x, y, length, width);
    }
    
    public void set (Color c, int xPos, int yPos, int l, int w)
    {
        color = c;
        x = xPos;
        y = yPos;
        length = l;
        width = w;
    }
    
    public void hide ()
    {
        x = 0;
        y = 0;
        length = 0;
        width = 0;
    }
    
    public int getX ()
    {
        return x;
    }
    
    public int getY ()
    {
        return y;
    }
    
    public int getLength ()
    {
        return length;
    }
    
    public int getWidth ()
    {
        return width;
    }
}
    