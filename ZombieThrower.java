import java.awt.*;

public class ZombieThrower extends Zombie {
    private ZombieEffect e;
    public ZombieThrower(Player p){
        super(2);
        e = new ZombieEffect(this, p);
    }
    public void act(Player p){

        e.act(p);

        if(!e.getExists())
            e = new ZombieEffect(this, p);
        move(p);
    }
    public void drawE(Graphics g){
        e.drawSelf(g);
    }

}
