import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {
    private double x;
    private double y;
    private double maxHealth;
    private double health;
    private int diam;
    private boolean alive;
    boolean beingAttacked;
    private double vx, vy;
    private int frame;
    private double speed;
    private boolean closeZombRight;
    private double originalHealth;

    public Player(int w, int h) {
        diam = 30;
        x = 300;
        y = 400;
        vx = 0;
        vy = 0;
//        maxHealth = 100;
//        health = 100;
//        beingAttacked=false;
//        frame = 0;
        speed = 2.5;
//        originalHealth = 100;
    }
    public double getSpeed(){return speed;}
    public double getMaxHealth(){return maxHealth;}
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getVx()
    {
        return vx;
    }
    public double getVy(){return vy;}
    public void setBeingAttacked(boolean kk){beingAttacked =kk;}
    public int getDiam()
    {
        return diam;
    }
    public double getCenterX()
    {
        return (diam/2) + x;
    }
    public double getHealth(){return health;}
    public double getCenterY()
    {
        return (diam/2) + y;
    }
    public void act()
    {
//        frame++;
//
//        if(health>= maxHealth){
//            health = maxHealth;
//        }
//
//        if(frame %10 <= 5)
//            setNotBeingAttacked();
        x+=vx;
        y+=vy;

    }
    public void increaseSpeed (){
        speed += .5;
    }
    public void increaseMaxHealth(){
        double increaseBy =  originalHealth  * .2;
        maxHealth += increaseBy;

        if(maxHealth >= originalHealth*2 ){
            maxHealth = originalHealth*2;
        }
    }
    public void heal(){
        double increaseBy = maxHealth* .25;
        health += increaseBy;
    }
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if(key == 38)
            vy = -speed;
        else if (key == 40)
            vy = speed;

        if (key == 39)
            vx = speed;
        else if (key == 37)
            vx = -speed;


    }
    private void setNotBeingAttacked() {
        beingAttacked = false;
    }
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if(key == 38)
            vy = 0;
        else if (key == 40)
            vy = 0;
        if (key == 39)
            vx = 0;
        else if (key == 37)
            vx = 0;


    }
    public void drawSelf(Graphics g)
    {
        g.setColor(Color.white);
        g.fillOval((int)(x + .5 ),(int)(y + .5),diam,diam);
    }

    public void findCloseZomb(Zombie z){
        double zombX = z.getCenterX();
        double pX = this.getCenterX();

        if(zombX > pX)
            closeZombRight = true;
        else
            closeZombRight = false;
    }
    public void getAttacked(double d){
        beingAttacked = true;
        health -= d;
        if(health <= 0)
            health = 0;
    }

}

