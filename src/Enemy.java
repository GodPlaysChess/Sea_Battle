import java.awt.*;
import java.util.ArrayList;

public class Enemy {

    ArrayList<AIShip> Ships = new ArrayList<AIShip>();
    ArrayList<Turret> Turrets = new ArrayList<Turret>();
    private int maxEnemyShips = 1;
    private int shipCD = 50;




    Enemy(Vec v) {
        Ships.add(new AIShip(v, Ship.ENEMY));
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < Ships.size(); i++) {Ships.get(i).render(g);
            Ships.get(i).AIrenderTrajectory(g);}
    }

    public void update() {
        for (int i = 0; i < Ships.size(); i++) {
            Ships.get(i).update();
            if (Ships.get(i).decomposition == 0)
                Ships.remove(i);
        }

        //Counter for AI ship to respawn;
        if (Ships.size() < maxEnemyShips && shipCD < 50)
            shipCD++;

    }

    public void addShip(Vec v) {
        if (Ships.size() < maxEnemyShips && shipCD == 50) {
            Ships.add(new AIShip(v, Ship.ENEMY));
            shipCD = 0;
        }
    }

    public void move(){
        for (int i = 0; i < Ships.size(); i++){
            Ships.get(i).AImove();
        }
    }

}


/**
 * Should put the methods ("patterns") in a priority queue;
 * All CheckMethods are instant. Do every turn. And then chose corresponding movement method.
 * Note: could move and spin turret correspondingly, but sometimes it is better to rotate both ship and tower simultaneously
 * Note2: may be later would be interesting to add some randomness to AI decisions
 *
 */


