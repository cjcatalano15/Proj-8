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
public class Driver extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variables
    private int WIDTH;
    private int HEIGHT;
    private ArrayList <ZombieThrower> z;
    private ArrayList <ZombieShooter> zs;
    private ArrayList <Zombie> zombs;
    private Player player;
    private static long frame;
    private ZombieExploder testZ;


    //Default Constructor
    public Driver() {
        WIDTH = 1000;
        HEIGHT = 750;
        frame = 0;

        player = new Player(WIDTH, HEIGHT);

        //Setting up the GUI
        JFrame gui = new JFrame(); // makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes sure program can close
        gui.setTitle("Driver"); // this is the title of the game... you can change it
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30));//setting size for gui
        gui.setResizable(false);//makes it so the gui cannot be resized
        gui.getContentPane().add(this);//adding this class to the gui

        /*If after you finish everything, you can declare your buttons or other things
         *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
         */
        gui.pack();//packs everything together;
        gui.setLocationRelativeTo(null);  // makes so the gui opens in the center of the screen
        gui.setVisible(true);//makes the gui visible
        gui.addKeyListener(this);//stating that this object will listen to the keyboard
        gui.addMouseListener(this);//stating that this object will listen to the mouse
        gui.addMouseMotionListener(this);//stating that this object will acknowledge when the mouse moves


        z = new ArrayList<ZombieThrower>();
        for (int i = 0; i < 5; i++)
            z.add(new ZombieThrower(player));

        zs = new ArrayList<ZombieShooter>();
        for (int i = 0; i < 5; i++)
            zs.add(new ZombieShooter(player));

        zombs = new ArrayList<Zombie>();
//
//        for (int i = 0; i < 2; i++){
//            zombs.add(z);
//            zombs.add(zs);
//        }
        testZ = new ZombieExploder();

    }
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e)
    {
        //getting the key pressed
        player.keyPressed(e);
        testZ.keyPressed(e);

    }
    //All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {
        //Drawing a Blue Rectangle to be the background
        g.setColor(Color.GRAY);
        g.fillRect(0,0, WIDTH, HEIGHT);

//        for(ZombieThrower curr : z) {
//            curr.drawSelf(g);
//            curr.drawE(g);
//        }
//
//        for(ZombieShooter curr : zs) {
//            curr.drawSelf(g);
//            curr.drawB(g);
//        }
        testZ.drawSelf(g);


        player.drawSelf(g);

    }
    public double distance (int x1, int y1, int x2, int y2)
    {
        double part1 = Math.pow((x1-x2), 2);
        double part2 = Math.pow((y2-y1), 2);
        double equation = part1 + part2;
        double output = Math.sqrt(equation);
        System.out.println(output);
        return output;

    }
    public void loop()
    {
        player.act();
//        for(ZombieThrower curr : z) {
//            curr.act(player);
//            for(int i = 0; i < z.size(); i++)
//                curr.handleCollision(z.get(i));
//        }
//        for(ZombieShooter curr : zs) {
//            curr.act(player);
//            for(int i = 0; i < zs.size(); i++)
//                curr.handleCollision(z.get(i));
       // }
        testZ.act(player);
//        for(int i = 0; i < zombs.size(); i++){
//            zombs.get(i);
//            for (int i = 0; i < z.size(); i++)
//                curr.handleCollision(z.get(i));
//        }



        //Do not write below this
        frame++;
        repaint();
    }
    public static long getFrame(){
        return frame;
    }
    //These methods are required by the compiler.
    //You might write code in these methods depending on your goal.
    public void keyTyped(KeyEvent e)
    {
    }
    public void keyReleased(KeyEvent e)
    {
        player.keyReleased(e);
    }
    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {
    }
    public void mouseDragged(MouseEvent e)
    {
    }
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        gameThread.start();
    }

    public static void main(String[] args)
    {
        Driver g = new Driver();
        g.start(60);
    }
}


