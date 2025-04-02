import java.awt.Graphics;

public class ZombieShooter extends Zombie {
    private ZombieBullet b;
    public ZombieShooter(Player p){
        super(1);
        b = new ZombieBullet(this, p);

    }
    public void act(Player p){
        b.act(p);

        double bx = b.getX();
        double by = b.getY();
        if(bx < 0 || bx > 1000)
            b = new ZombieBullet(this, p);
        else if (by  < 0 || by> 750)
            b = new ZombieBullet(this , p);


        double dist = distance(this.getCenterX(), this.getCenterY(), p.getCenterX(), p.getCenterY());
        if(dist > 150)
            move(p);

    }
    public void drawB(Graphics g){
        b.drawSelf(g);
    }

}
