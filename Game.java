import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Game extends JPanel implements KeyListener
{
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static final int NUMLVLS = 4;
    private long startT;
    private double time;
    private int level, life, gX, gY, gS, x1, y1;
    private static int pX, pY, pS;
    private Goal goal;
    private static Player player;
    private static ArrayList<Wall> walls;
    private static ArrayList<Hazard> hazards;
    private static ArrayList<Spring> springs;
    
    public Game ()
    {
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        setFocusable(true);
        startT = System.currentTimeMillis(); 
        level = 1;
        life = 3;
        goal = new Goal();
        walls = new ArrayList();
        hazards = new ArrayList();
        springs = new ArrayList();
        
        //level 1
        player = new Player(50, 100);
        x1 = 50; y1 = 100;
        goal.set(Color.GREEN, 900, 500);
        walls.add(new Wall());
        walls.get(0).set(Color.CYAN, 0, 200, 250, 500);
        hazards.add(new Hazard());
        hazards.get(0).set(Color.ORANGE, 250, 680, 750, 20);
    }
    
    public void run ()
    {
        while (life > 0)
        {
            player.move();
            
            //player-goal collision
            pX = player.getX();
            pY = player.getY();
            pS = player.getSize();
            gX = goal.getX();
            gY = goal.getY();
            gS = goal.getSize();
            if (pX + pS >= gX && pX <= gX + gS &&
            pY + pS >= gY && pY <= gY + gS)
            {
                level ++;
                
                if (level == NUMLVLS)
                {
                    setBackground(Color.CYAN);
                    player.hide();
                    goal.hide();
                    for (Wall wall: walls)
                    {
                        wall.hide();
                    }
                }
                else if (level == 2)
                {
                    setBackground(Color.GREEN);
                    player.set(50, 350);
                    x1 = 50; y1 = 350;
                    goal.set(Color.BLUE, 600, 450);
                    walls.get(0).set(Color.MAGENTA, 0, 600, 1000, 30);
                    hazards.get(0).set(Color.RED, 450, 500, 550, 50);
                    springs.add(new Spring());
                    springs.get(0).set(Color.YELLOW, 380, 200, 20, 300, 4, "right");
                }
                else if (level == 3)
                {
                    setBackground(Color.BLUE);
                    player.set(50, 400);
                    x1 = 50; y1 = 400;
                    goal.set(Color.RED, 800, 100);
                    walls.get(0).set(Color.BLACK, 450, 0, 30, 550);
                    walls.add(new Wall());
                    walls.get(1).set(Color.BLACK, 300, 350, 30, 350); 
                    walls.add(new Wall());
                    walls.get(2).set(Color.BLACK, 0, 0, 30, 700);
                    hazards.get(0).hide();
                    springs.get(0).set(Color.YELLOW, 600, 680, 400, 20, 7, "up");
                }
            }
            
            //player-hazard collision
            for (Hazard hazard: hazards)
            {
                int hX = hazard.getX();
                int hY = hazard.getY();
                int hL = hazard.getLength();
                int hW = hazard.getWidth();
                if (pX + pS >= hX && pX <= hX + hL
                && pY + pS >= hY && pY <= hY + hW)
                {
                    life --;
                    player.set(x1, y1);
                }
            }
            
            //player-spring collisions
            for (Spring spring: springs)
            {
                int sX = spring.getX();
                int sY = spring.getY();
                int sL = spring.getLength();
                int sW = spring.getWidth();
                if (pX + pS > sX && pX < sX + sL
                && pY < sY + sW && pY + pS >= sY 
                && spring.getType().equals("up"))
                {
                    player.setJumpY(spring.getSpringiness());
                }
                if (pX + pS > sX && pX < sX + sL
                && pY <= sY + sW && pY + pS > sY 
                && spring.getType().equals("down"))
                {
                    player.setJumpY(-spring.getSpringiness());
                }
                if (pY + pS > sY && pY < sY + sW
                && pX <= sX + sL && pX + pS > sX
                && spring.getType().equals("right"))
                {
                    player.setJumpX(spring.getSpringiness());
                }
                if (pY + pS > sY && pY < sY + sW
                && pX < sX + sW && pX + pS >= sX 
                && spring.getType().equals("left"))
                {
                    player.setJumpX(-spring.getSpringiness());
                }
            }
            
            try   
            { Thread.sleep(10); }
            catch (InterruptedException ex)
            {}
            repaint();
        }
        
        //lose screen
        setBackground(Color.YELLOW);
        player.hide();
        goal.hide();
        for (Wall wall: walls)
        {
            wall.hide();
        }
        for (Hazard hazard: hazards)
        {
            hazard.hide();
        }
        for (Spring spring: springs)
        {
            spring.hide();
        }
    }
    
    public void paintComponent (Graphics page)
    {
        if (life == 0)
        {
            page.setColor(Color.BLACK);
            setFont(new Font("serif", Font.PLAIN, 50));
            page.drawString("Damn", 350, 300);
            page.drawString("You lost in", 300, 370);
            page.drawString("" + time + " seconds", 305, 440);
        }
        else if (level == NUMLVLS)
        {
            page.setColor(Color.BLACK);
            setFont(new Font("serif", Font.PLAIN, 50));
            page.drawString("Congrats", 350, 300);
            page.drawString("You finished in", 300, 370);
            page.drawString("" + time + " seconds", 305, 440);
        }
        else
        {
            super.paintComponent(page);
            page.setColor(Color.WHITE);
            time = (System.currentTimeMillis() - startT) / 1000.0; 
            setFont(new Font("serif", Font.PLAIN, 25));
            page.drawString("" + time, 900, 50);
            page.drawString("Lives: " + life, 50, 50);
            player.draw(page);
            goal.draw(page);
            for (Wall wall: walls)
            {
                wall.draw(page);
            }
            for (Hazard hazard: hazards)
            {
                hazard.draw(page);
            }
            for (Spring spring: springs)
            {
                spring.draw(page);
            }
        }
    }
    
    public static boolean onWall (int pX, int pY, int pS)
    {
        for (Wall wall: walls)
        {
            int wX = wall.getX();
            int wY = wall.getY();
            int wL = wall.getLength();
            int wW = wall.getWidth();
            if (pX + pS >= wX && pX <= wX + wL
            && pY + pS >= wY && pY <= wY + wW)
            {
                return true;
            }
        }
        return false;
    }
    
    public void keyPressed (KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            player.setRight(true);
            player.setLeft(false);
        }
        else if (event.getKeyCode() == KeyEvent.VK_LEFT)
        {
            player.setLeft(true);
            player.setRight(false);
        }
        if (event.getKeyCode() == KeyEvent.VK_UP)
        {
            player.setJumpY(3);
        }
        this.repaint();
    }
    
    public void keyReleased (KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            player.setRight(false);
        }
        else if (event.getKeyCode() == KeyEvent.VK_LEFT)
        {
            player.setLeft(false);
        }
        this.repaint();
    }
    
    public void keyTyped (KeyEvent event)
    {
    }
}