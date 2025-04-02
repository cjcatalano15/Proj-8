import java.awt.*;

public class ZombieEffect {
    private double x,y, vx, vy;
    private double px, py;
    private int diam;
    private boolean hit;
    private long curr, end;
    private boolean exists;
    public ZombieEffect(ZombieThrower z, Player p){
        hit = false;
        x = (int)z.getCenterX();
        y = (int)z.getCenterY();


        diam = 5;
        exists = true;
        px = p.getCenterX();
        py = p.getCenterY();
        vx = (p.getCenterX() - z.getCenterX());
        vy = (p.getCenterY() - z.getCenterY());
        double divide = Math.sqrt(Math.pow(vx,2) + Math.pow(vy,2));
        double multiplier = 5;

        vx = (vx / divide) * multiplier;
        vy = (vy /divide) * multiplier;

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
    public void act(Player p){
        long frame = Driver.getFrame();
        if(exists) {
            int thisR = diam / 2;
            int otherR = p.getDiam() / 2;
            double dist = distance(this.getCenterX(), this.getCenterY(), px, py);

            if (dist <= thisR + otherR && !hit) {
                diam = 30;
                hit = true;
                curr = frame;
                end = curr + 150;
            }

            if (hit) {
                if (curr > end)
                    exists = false;
                /*else
                    //damage player
                 */
            }
            else {
                x += vx;
                y += vy;
            }
        }

        curr = frame;
    }
    public double getCenterX()
    {
        return (diam/2) + x;
    }
    public boolean getExists(){return exists;}

    public double getCenterY()
    {
        return (diam/2) + y;
    }
    public void drawSelf(Graphics g){
        if(exists) {
            g.setColor(Color.ORANGE);
            g.fillOval((int) (x + .5), (int) (y + .5), diam, diam);
        }
    }
}
