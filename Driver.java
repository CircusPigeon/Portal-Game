import javax.swing.*;
import java.awt.*;
public class Driver
{
    public static void main (String[] args)
    {
        JFrame frame = new JFrame("Portal Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0, 0);
        Game game = new Game();
        frame.getContentPane().add(game);
        frame.pack();
        frame.setVisible(true);
        game.run();
    }
}