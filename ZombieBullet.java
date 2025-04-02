import java.awt.Graphics;
import java.awt.Color;

public class ZombieBullet {
    private double x,y, vx, vy;
    private int diam;
    public ZombieBullet(ZombieShooter z, Player p){
        x = (int)z.getCenterX();
        y = (int)z.getCenterY();

        vx = (p.getCenterX() - z.getCenterX());
        vy = (p.getCenterY() - z.getCenterY());
        double divide = Math.sqrt(Math.pow(vx,2) + Math.pow(vy,2));
        double multiplier = 5;

        vx = (vx / divide) * multiplier;
        vy = (vy /divide) * multiplier;

        diam = 5;



    }
    public void act(Player p){
        double pX = p.getCenterX();
        double pY = p.getCenterY();

        x += vx;
        y += vy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void drawSelf(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval((int)(x+.5),(int)(y+.5),diam,diam);
    }


}
