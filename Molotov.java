import java.awt.*;

public class Molotov {
    private double x, y, vx, vy;
    private double targetX, targetY;
    private Player player;
    private Zombie zomb;
    private boolean exists;
    private int state, diam; // 1 means its traveling, 2 means it has landed
    private double zx, zy;
    private long frame;
    public Molotov(Player p, Zombie z){
        player = p;
        state = 1;
        zomb = z;
        diam = 5;
        exists = true;

        x = player.getCenterX();
        y = player.getCenterY();
        zx = zomb.getCenterX();
        zy = zomb.getCenterY();
        targetX = zomb.getX();
        targetY = zomb.getY();

        vx = (z.getCenterX() - player.getCenterX());
        vy = (z.getCenterY() - player.getCenterY());
        double divide = Math.sqrt(Math.pow(vx,2) + Math.pow(vy,2));
        double multiplier = 2;

        vx = (vx / divide) * multiplier;
        vy = (vy /divide) * multiplier;
    }
    public double getCenterY() {
        return (diam / 2) + y;
    }
    public double getCenterX() {
        return (diam / 2) + x;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean getExists()
    {
        return exists;
    }
    public double distance(double x1, double y1, double x2, double y2)
    {
        double part1 = Math.pow((x2-x1), 2);
        double part2 = Math.pow((y2-y1), 2);
        double equation = part1 + part2;
        double output = Math.sqrt(equation);
        //System.out.println(output);
        return output;
    }
    private void setX(double xx){x = xx;}
    private void setY(double yy){y = yy;}
    public void act(Zombie z){
        long end = 0;
        frame = Driver.getFrame();
        int thisR = diam / 2;
        int otherR = z.getDiam() / 2;
        double dist = distance(this.getCenterX(), this.getCenterY(), zx, zy);
        if(state != 2 && dist + thisR <= thisR + otherR){
            state = 2;
            end = frame + 700;
        }


         if(state == 1){
            x += vx;
            y += vy;
         }
         else{

             if(frame<=end){
                 this.setX(this.getX() - 13);
                 this.setY(this.getY() - 13);
                 diam = 50;
             }
             else
                 exists = false;
         }
    }
    public void drawSelf(Graphics g){
        g.setColor(Color.red);
        g.fillOval((int)(x+.5), (int)(y+.5), diam, diam);
    }
}
