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
   private int width;
   private int height;
   private int diam;
   private int x;
   private int y;
   private boolean isRight;
   private Player player;
   private long frame;
   
   public Knife(int w,int h)
   {
       width = w;
       height = h; 
   }
   public void drawSelf(Graphics g){
        g.setColor(Color.red);
        g.fillOval(x+diam/2, y+diam/2, width, height);
    } 
   public void act(Player p)
   {
       frame = Driver.getFrame();
       if(frame%100>=49)
       {
           isRight = false;
       }
       else
           isRight = true;
       if(isRight==true)
       {
           x=(int)(player.getCenterX()+diam);
           y = (int)(player.getCenterY());
       }
       else
           x = (int)(player.getCenterX()-diam);
           y = (int)(player.getCenterY());
   }
   
}
