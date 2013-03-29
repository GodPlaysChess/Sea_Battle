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
            Ships.add(new Ship(v ,Ship.ENEMY));
            shipCD = 0;
        }
    }

    public void move(){

    }



}
