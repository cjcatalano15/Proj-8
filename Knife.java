
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;
import java.util.ArrayList;
public class Knife
{
    private double width;
    private double height;
    private double x;
    private double y;
    private boolean isRight;
    private Player player;
    private long frame;

    public Knife(Player p)
    {
        player = p;
        x = p.getCenterX();
        y = player.getCenterY() - (height/2);
        width = 150;
        height = 30;

        frame = 0;
        isRight = false;
    }
    public void drawSelf(Graphics g){
        g.setColor(Color.red);
        g.fillOval((int)(x+.5), (int)(y+.5), (int)(width + .5), (int)(height + .5));
    }
    public void act(Player p)
    {
        frame++;

        if(frame%50<=24)
        {
            isRight = false;
        }
        else
            isRight = true;

        if(isRight)
        {
            x=player.getCenterX();
        }
        else {
            x = player.getCenterX() - width;
        }
        y = player.getCenterY() - (height/2);

    }

}

