import java.awt.*;
import javax.swing.ImageIcon;


public class Bullet
{
    private double x;
    private double y;
    private double vx;
    private double vy;
    private int diam;
    private Player player;
    private Zombie zombie;


    public Bullet (Player p, int d, Zombie z){
        player = p;
        diam = d;
        x = player.getCenterX() - diam/2;
        y = player.getCenterY() - diam/2;
        vx = 5;
        vy = 5;
        zombie = z;

    }
    private double distance(double x1, double y1, double x2, double y2) {
        double part1 = Math.pow((x2 - x1), 2);
        double part2 = Math.pow((y2 - y1), 2);
        double equation = part1 + part2;
        double output = Math.sqrt(equation);
        //System.out.println(output);
        return output;
    }


    public void drawSelf(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval((int)x,(int)y,diam,diam);



    }


    public double getCenterY() {
        return (diam / 2) + y;
    }


    public double getCenterX() {
        return (diam / 2) + x;
    }

    public void act1(){
        double thisCenterX = this.getCenterX();
        double thisCenterY = this.getCenterY();
        double zCenterX = zombie.getCenterX();
        double zCenterY = zombie.getCenterY();


        vx = zCenterX - thisCenterX;
        vy = zCenterY - thisCenterY;


        double hyp = Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2));
        if(hyp == 0)
        {
            hyp += .1;
        }
        vx = vx / hyp;
        vy = vy / hyp;


        int speed = 10;
        vx*= speed;
        vy *= speed;


        x += vx;
        y += vy;


    }
    public boolean attackZombie (){
        double thisCenterX = this.getCenterX();
        double thisCenterY = this.getCenterY();
        double zCenterX = zombie.getCenterX();
        double zCenterY = zombie.getCenterY();


        int thisRadius = diam / 2;
        int pRadius = zombie.getDiam() / 2;
        double dist = distance(thisCenterX, thisCenterY, zCenterX, zCenterY);
        if (dist <= thisRadius + pRadius) {
            zombie.getAttacked(100);
            return true;
        }
        return false;
    }
}

