import java.awt.*;
import java.awt.geom.Point2D;

public class Bullet {

    private Vec position = new Vec(0, 0);
    private Vec velocity = new Vec(0, 0);

    boolean invisible = false;

    public Bullet(Vec p, Vec v) {
        velocity.setV(v.normalize().multiplied(8));
        position.setV(p);
    }

    public Bullet(Vec p, Vec v, char i) {
        velocity.setV(v.normalize().multiplied(8));
        position.setV(p);
        invisible = true;

    }

    public void move() {
        position.add(velocity);
    }

    public Vec getNextPos() {
        if (!invisible)
            return (getPos().addReturn(velocity));
        else
            return (GameData.MyShipPosition);
    }

    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        if (!invisible)
            g.fillOval((int) position.getX(), (int) position.getY(), 6, 6);
    }

    public Vec getPos() {
        return position;
    }

    //this method checks whether bullet collides with Field obstacles / goes out of the field.
    //hitting the objects is the other method, which sits in the ObjectsOnMap class.
    //I.e every object checks itself for collision with the bullet
    public boolean CheckCollision() {
        if (getPos().OutOfBounds(GameData.sea.borderX, GameData.sea.borderY, GameData.sea.sizeX + GameData.sea.borderX, GameData.sea.sizeY + GameData.sea.borderY))
            return true;

        for (int i = 0; i < GameData.sea.Obst.size(); i++) {
            for (int k = 0; GameData.sea.Obst.get(i).getType() != Obstacle.ISLAND && k < GameData.sea.Obst.get(i).Vertexes.size() - 1; k++)
                if (Vec.linesIntersect(getPos(), getNextPos(), GameData.sea.Obst.get(i).Vertexes.get(k), GameData.sea.Obst.get(i).Vertexes.get(k + 1)))
                    return true;
        }

        return false;
    }


}

