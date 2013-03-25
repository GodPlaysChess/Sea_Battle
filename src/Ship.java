import java.awt.*;

public class Ship extends ObjectOnMap {


    private int direction_degrees;
    private int length;
    private int width;
    private int velocity;
    public Turret ship_turret = null;


    Ship(Vec v) {
        position.setV(v);
        direction_degrees = 0;
        length = 20;
        width = 10;
        velocity = 5;

        for (int i = 0; i < 5; i++) {
            Points.add(new Vec(0, 0));
        }

        ship_turret = new Turret(this);
    }

    public void update() {
        super.update();
        ship_turret.setPosition(position);
        ship_turret.fpos.setV(ship_turret.getPosition().addReturn(ship_turret.getFire_direction().multiplied(10)));
        ship_turret.incTimeToCD();

        Points = GeomHelp.findPolygon(position.getX(), position.getY(), length, width, (float) Math.toRadians(direction_degrees));


    }

    public void moveStraight() {
        position.add(velocity * (float) (Math.cos(Math.toRadians(direction_degrees))), velocity * (float) (-Math.sin(Math.toRadians(direction_degrees))));
    }

    public void turnLeft() {
        direction_degrees += 5;
        ship_turret.turnLeft();
    }

    public void turnRight() {
        direction_degrees -= 5;
        ship_turret.turnRight();
    }

    public void render(Graphics2D g) {
        if (!is_destroyed) {
            g.setColor(Color.BLACK);
            GeomHelp.renderPolygon(position.getX(), position.getY(), length, width, (float) Math.toRadians(direction_degrees), g);
            ship_turret.render(g);
        } else {
            g.setColor(Color.RED);
            GeomHelp.renderPolygon(position.getX(), position.getY(), length, width, (float) Math.toRadians(direction_degrees), g);
        }


    }

    public Vec getPosition() {
        return position;
    }

    public Vec getDirectionVector() {
        return new Vec((float) Math.cos(Math.toRadians(direction_degrees)), (float) -Math.sin(Math.toRadians(direction_degrees)));
    }
}
