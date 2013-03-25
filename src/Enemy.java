import java.awt.*;
import java.util.ArrayList;

public class Enemy {

    ArrayList<Ship> Ships = new ArrayList<Ship>();
    ArrayList<Turret> Turrets = new ArrayList<Turret>();
    private Vec spawnPoint = new Vec(120, 150);


    Enemy(Vec v) {
        Ships.add(new Ship(v));
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < Ships.size(); i++) Ships.get(i).render(g);

    }

    public void update() {

        for (int i = 0; i < Ships.size(); i++) {
            // Ships.get(i).ship_turret.setPosition(Ships.get(i).getPosition());
            // Ships.get(i).ship_turret.fpos.setV(Ships.get(i).ship_turret.getPosition().addReturn(Ships.get(i).ship_turret.getFire_direction().multiplied(10)));
            // Ships.get(i).ship_turret.incTimeToCD();

            Ships.get(i).update(); // checking for collision here,
        }


    }


}
