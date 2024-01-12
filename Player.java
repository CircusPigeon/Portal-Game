import java.awt.*;
public class Player
{
    private Color color;
    private int x, y, size, jumpV0Y, jumpV0X;
    private long startTy, startTx, currentT;
    private double ty, tx, jumpVY, jumpVX;
    private boolean right, left, jumpY, jumpX;
    
    public Player (int xPos, int yPos)
    {
        jumpY = false;
        jumpX = false;
        color = Color.WHITE;
        size = 15;
        x = xPos;
        y = yPos;
    }
    
    public void set (int xPos, int yPos)
    {
        jumpY = false;
        jumpX = false;
        x = xPos;
        y = yPos;
    }
    
    public void hide ()
    {
        x = -100;
        y = -100;
        size = 0;
    }
    
    public void draw (Graphics page)
    {
        page.setColor(color);
        page.fillRect(x, y, size, size);
    }
    
    public void move ()
    {
        //movement
        if (right && !Game.onWall(x + 2, y, size) && x + size <= Game.WIDTH)
        {
            x += 2;
        }
        if (left && !Game.onWall(x - 2, y, size) && x >= 0)
        {
            x -= 2;
        }
        if (!jumpY && !Game.onWall(x, y + 4, size) && y + size <= Game.HEIGHT)
        {
            y += 4;
        }
        //fix infinite wrap-around jumps
        if (jumpY && !Game.onWall(x, (int) (y - jumpVY), size)
        && y + size - jumpVY <= Game.HEIGHT)
        {
            ty = (System.currentTimeMillis() - startTy) / 1000.0;
            if (jumpVY > -4)
            { 
                jumpVY = jumpV0Y - 5 * Math.pow(ty, 2);
                y -= jumpVY;
            }
            else
            {
                y += 4; 
            }
        }
        else
        { 
            jumpY = false; 
        }
        if (jumpX && !Game.onWall(x, (int) (x + jumpVX), size)
        && x + size + jumpVX <= Game.WIDTH && jumpVX > 0)
        {
            tx = (System.currentTimeMillis() - startTx) / 1000.0;
            jumpVX = jumpV0X - 5 * Math.pow(tx, 2);
            x += jumpVX;
        }
        else
        { 
            jumpX = false; 
        }
        
        //wrap-around
        if (x < 0 && !Game.onWall(Game.WIDTH - size, y, size))
        {
            x = Game.WIDTH - size;
        }
        if (x + size > Game.WIDTH && !Game.onWall(0, y, size))
        {
            x = 0;
        }
        if (y + size > Game.HEIGHT && !Game.onWall(x, 0, size))
        {
            y = 0;
        }
        if (y < 0 && !Game.onWall(x, Game.HEIGHT - size, size))
        {
            y = Game.HEIGHT - size;
        }
    }
    
    public void setRight (boolean r)
    {
        right = r;
    }
    
    public void setLeft (boolean l)
    {
        left = l;
    }
    
    //make work for down springs
    public void setJumpY (int n)
    {
        if (!jumpY || n > 3)
        { 
            ty = 0;
            startTy = System.currentTimeMillis(); 
            jumpY = true;
            jumpV0Y = n;
            jumpVY = n;
        }
    }
    
    //make work for left springs
    public void setJumpX (int n)
    {
        tx = 0;
        startTx = System.currentTimeMillis(); 
        jumpX = true;
        jumpV0X = n;
        jumpVX = n;
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
}