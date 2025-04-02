import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Zombie {
    private double health;
    private double x;
    private double y;
    private int diam;
    private double vx;
    private double vy;
    private boolean exists;
    private int dropR;
    private boolean beingAttacked;
    private int frame;
    private Color defaultColor;
    private int killed;
    private double dps;
    private int type;

    private void create(int xx, int yy, int d, int hp, double speed, double damage) {
        defaultColor = Color.green;
        x= xx;
        y = yy;
        diam = d;
        health = hp;
        vx = speed;
        vy = speed;
        dps= damage;
    }
    public Zombie(int t){  //decides the type of zombie; 0 = normal
                            //1 = shooter, 2 = thrower, 3 = armor, 4 = light, 5 = heavy, 6 = exploder
        type = t;
        if(t <=3){
            create(10,10,20,100,1,10);
        }
        else if(t == 4){
            create(20,20,10, 50, 5, 5);
        }
        else if(t == 5){
            create(30,30,30, 500, .5, 20);
        }
        else if (t == 6)
            create(40,40,20,100, 2, 10);

    }

    public double getHealth(){return health;}
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getDiam() {
        return diam;
    }

    public double getCenterX() {
        return (diam / 2) + x;
    }
    public boolean getExists() {return exists;}
    public double getCenterY() {
        return (diam / 2) + y;
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
    public void move(Player p){

        //System.out.println(health);
        frame++;
        if(frame %10 < 5)
            setNotBeingAttacked();

        double pCenterX = p.getCenterX();
        double pCenterY = p.getCenterY();
        double zCenterX = getCenterX();
        double zCenterY = getCenterY();
        int pRadius = p.getDiam()/2;
        int zRadius = getDiam()/2;
        double dist = distance(zCenterX, zCenterY, pCenterX, pCenterY);

        int speed = 1;

        if(pCenterX > zCenterX && pCenterY > zCenterY){
            //bot right
            vx = speed;
            vy = speed;
        }
        else if (pCenterX < zCenterX && pCenterY < zCenterY ){
            //player top left
            vx = -speed;
            vy = -speed;
        }
        else if (pCenterX > zCenterX && pCenterY < zCenterY ){
            //top right
            vx = speed;
            vy = -speed;
        }
        else if (pCenterX < zCenterX  && pCenterY > zCenterY ){
            //bottom left
            vx = -speed;
            vy = speed;
        }
        else if (pCenterY == zCenterY ){
            if(pCenterX < zCenterX ) {
                vx = -speed;
                vy = speed;
            }
            else {
                vx = speed;
                vy = 0;
            }
        }
        else if (pCenterX == zCenterX){
            if(pCenterY<zCenterY) {
                vx = 0;
                vy = -speed;
            }
            else
            {
                vx = 0;
                vy = speed;
            }
        }
        x+=vx; y+=vy;
    }
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if(key==75) {
            health = 0;
            //this.handleDeath();
        }


    }
    public boolean isDead()
    {
        if(health <= 0)
            return true;
        else
            return false;
    }
    public void getAttacked(double dps) {
        beingAttacked = true;
        health -= dps;
        if(health <= 0)
            health = 0;
    }
//    public Coin death () {
//        //formula= int x = (int)(Math.random()*(HighValue-LowValue+1)) +lowValue;
//        double zombieX = getX() + diam + dropR;
//        double zombieY = getY() + diam + dropR;
//        double xx = (int) (Math.random() * (zombieX - getX() + 1)) + getX();
//        double yy = (int) (Math.random() * (zombieY - getY() + 1)) + getY();
//        Coin c = new Coin((int)xx, (int)yy, 15);
//        exists = false;
//        return c;
//    }
    public void drawSelf(Graphics g){

        g.setColor(defaultColor);
        g.fillOval((int)(x + .5),(int)(y + .5),diam,diam);

    }
    public void setDiam(int d){diam = d;}
    public void setX(double xx){x = xx;}
    public void setY(double yy){y = yy;}
    public void updateX(Player player)
    {
        x += -player.getVx();
    }
    public void updateY(Player player)
    {
        y += -player.getVy();
    }
    public void setNotBeingAttacked() {
        beingAttacked = false;
    }
    public void handleCollision(Zombie z){
        double thisCenterX = this.getCenterX();
        double thisCenterY = this.getCenterY();
        double zCenterX = z.getCenterX();
        double zCenterY = z.getCenterY();

        int thisRadius = diam / 2;
        int pRadius = z.getDiam() / 2;
        double dist = distance(thisCenterX, thisCenterY, zCenterX, zCenterY);
        if (dist <= thisRadius + pRadius) {
            if(thisCenterX < zCenterX)
            {
                x--;
                z.pushX(1);
            }
            else
            {
                x++;
                z.pushX(-1);
            }
            if(thisCenterY < zCenterY)
            {
                y--;
                z.pushY(1);
            }
            else
            {
                y++;
                z.pushY(-1);
            }
        }
    }
    public void pushX(int dist)
    {
        x += dist;
    }
    public void pushY(int dist)
    {
        y+= dist;
    }


}

