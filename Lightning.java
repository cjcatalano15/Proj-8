import java.awt.*;

public class Lightning {
    private double x,y;
    private int diam;
    private int width, height;
    int state;
    private long frame;
    public Lightning(int w, int h){
        width = w;
        height = h;
        diam = 50;
        state = 1; // 1 == hasnt struck, 2 = waiting, 3 = currently striking
        frame = 0;
    }
    public void act(){
        if(state ==1){
            double randX = Math.random()* (width-diam);
            double randY = Math.random()*(height-diam);

            x = randX;
            y = randY;
            state = 2;
        }
        else if(state ==2){
            frame++;

            if(frame>100){
                state = 3;
                frame = 0;
            }
        }
        else if(state == 3){
            frame ++;
            if(frame > 50){
                state = 4;
            }
        }
    }

    public int getState() {
        return state;
    }

    public void drawSelf(Graphics g){
        if(state == 2){
            g.setColor(Color.ORANGE);
            g.fillOval((int)(x + .5),(int)(y+.5),diam,diam);
        }
        else if(state ==3){
            g.setColor(Color.blue);
            g.fillOval((int)(x + .5),(int)(y+.5),diam,diam);
        }
    }
}
