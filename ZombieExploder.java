public class ZombieExploder extends Zombie{

    long explode;
    private int state;
    public ZombieExploder(){
        super(6);
        //1 = alive, 2 = dead before explode, 3 = explode
        state = 1;
    }
    public void act(Player p){
        long frame = Driver.getFrame();
        boolean dead = this.isDead();

        if(dead && state == 1){
            state = 2;
            explode = frame + 50;
        }

        if(state == 1)
            move(p);
        else if(state == 2){
            if(frame>explode)
                state = 3;
        }
        else{
                if(state == 3) {
                    this.setX(this.getX() - 13);
                    this.setY(this.getY() - 13);
                    this.setDiam(52);
                    state = 4;
                }
                //damage player
        }

    }
}
