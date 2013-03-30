import java.awt.*;
import java.util.ArrayList;

public class Enemy {

    ArrayList<Ship> Ships = new ArrayList<Ship>();
    ArrayList<Turret> Turrets = new ArrayList<Turret>();
    private int maxEnemyShips = 1;
    private int shipCD = 50;




    Enemy(Vec v) {
        Ships.add(new Ship(v, Ship.ENEMY));
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < Ships.size(); i++) Ships.get(i).render(g);
    }

    public void update() {
        for (int i = 0; i < Ships.size(); i++) {
            Ships.get(i).update();
            if (Ships.get(i).decomposition == 0)
                Ships.remove(i);
        }
        if (Ships.size() < 10 && shipCD < 50)
            shipCD++;

    }



    public void addShip(Vec v) {
        if (Ships.size() < maxEnemyShips && shipCD == 50) {
            Ships.add(new Ship(v, Ship.ENEMY));
            shipCD = 0;
        }
    }

    public void analyze() {
        findShip();
    }

    public void move(){
        for (int i = 0; i < Ships.size(); i++){
            Ships.get(i).ship_turret.takeAim(Game.MyShipPosition);
           // Ships.get(i).ship_turret.fire();
        }
    }

    public float checkDistance(Ship ship1, Ship ship2) {
        return ship1.position.distance(ship2.position);
    }


    public boolean CouldHit(Ship ship1, Ship ship2) {
        //check if turret
        return false;
    }

    public void takeAim(Ship MyShip) {
        //could find only if "can see"
    }


    public void findShip() {
        // if no ship on lines, than search
    }
}


/**
 * Should put the methods ("patterns") in a priority queue;
 * All CheckMethods are instant. Do every turn. And then chose corresponding movement method.
 * Note: could move and spin turret correspondingly, but sometimes it is better to rotate both ship and tower simultaneously
 * Note2: may be later would be interesting to add some randomness to AI decisions
 *
 */


