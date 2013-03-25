import java.awt.*;
import java.awt.geom.Point2D;

public class Bullet {

    private Vec position = new Vec(0,0);
    private Vec velocity = new Vec(0,0);

    Bullet(Vec p, Vec v) {
        velocity.setV(v.normalize().multiplied(50));
        position.setV(p);
    }

    public void move() {
        position.add(velocity);
    }

    public Vec getNextPos(){
        return (getPos().addReturn(velocity));
    }

    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval((int) position.getX(), (int) position.getY(), 6, 6);
    }

    public Vec getPos(){
        return position;
    }


}
