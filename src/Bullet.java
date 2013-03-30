import java.awt.*;
import java.awt.geom.Point2D;

public class Bullet {

    private Vec position = new Vec(0, 0);
    private Vec velocity = new Vec(0, 0);

    Bullet(Vec p, Vec v) {
        velocity.setV(v.normalize().multiplied(5));
        position.setV(p);
    }

    public void move() {
        position.add(velocity);
    }

    public Vec getNextPos() {
        return (getPos().addReturn(velocity));
    }

    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval((int) position.getX(), (int) position.getY(), 6, 6);
    }

    public Vec getPos() {
        return position;
    }


    public boolean CheckCollision(Field sea) {
        if (getPos().OutOfBounds(sea.borderX, sea.borderY, sea.sizeX + sea.borderX, sea.sizeY + sea.borderY))
            return true;

        for (int i = 0; i < sea.Obst.size(); i++) {
            for (int k = 0; k < sea.Obst.get(i).Vertexes.size() - 1; k++)
                if (Vec.linesIntersect(getPos(), getNextPos(), sea.Obst.get(i).Vertexes.get(k), sea.Obst.get(i).Vertexes.get(k+1)))
                    return true;
        }

        return false;
    }
}

