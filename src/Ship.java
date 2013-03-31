import java.awt.*;

public class Ship extends ObjectOnMap {


    private Vec increment_position = new Vec();
    private int increment_direction_degrees = 0;

    private int direction_degrees;
    private int direction_degrees_before;
    private int length;
    private int width;
    private int velocity;
    private Color color;
    public Turret ship_turret = null;
    public final static Color PLAYER1 = new Color(78, 194, 19);
    public final static Color ENEMY = new Color(255, 89, 63);
    public final static Color SHIP = new Color(163, 82, 0);

    public boolean collision_detected = false;


    Ship(Vec v, Color color) {
        position.setV(v);
        direction_degrees = 0;
        length = 20;
        width = 10;
        velocity = 5;


        for (int i = 0; i < 5; i++) {
            Points.add(new Vec(0, 0));
        }

        ship_turret = new Turret(this, color);
    }

    public void update() {
        move();
        super.update();
        ship_turret.update();
        Points = GeomHelp.findPolygon(position.getX(), position.getY(), length, width, (float) Math.toRadians(direction_degrees));
    }


    public void moveStraight() {
        increment_position.setV((velocity * (float) (Math.cos(Math.toRadians(direction_degrees)))), velocity * (float) (-Math.sin(Math.toRadians(direction_degrees))));

    }

    public void turnLeft() {
        increment_direction_degrees = 5;
        ship_turret.turnLeft();
    }

    public void turnRight() {
        increment_direction_degrees -= 5;
        ship_turret.turnRight();
    }


    public void CollisionCheck(Ship ship) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (Vec.linesIntersect(Points.get(i), Points.get(i + 1), ship.Points.get(j), ship.Points.get(j + 1))) {
                    collision_detected = true;
                    moveback();
                }
    }

    public void CollisionCheck(Obstacle obstacle) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < obstacle.Vertexes.size() - 1; j++)
                if (Vec.linesIntersect(Points.get(i), Points.get(i + 1), obstacle.Vertexes.get(j), obstacle.Vertexes.get(j + 1))) {
                    collision_detected = true;
                    moveback();
                }
    }

    public void render(Graphics2D g) {
        if (!is_destroyed) {
            g.setColor(SHIP);
            GeomHelp.fillPolygon(position.getX(), position.getY(), length, width, (float) Math.toRadians(direction_degrees), g);
            ship_turret.render(g);
        } else {
            g.setColor(Color.RED);
            GeomHelp.fillPolygon(position.getX(), position.getY(), length, width, (float) Math.toRadians(direction_degrees), g);
        }
    }

    public Vec getPosition() {
        return position;
    }

    public Vec getDirectionVector() {
        return new Vec((float) Math.cos(Math.toRadians(direction_degrees)), (float) -Math.sin(Math.toRadians(direction_degrees)));
    }


    private void move(Vec increment_position, int increment_direction_degrees) {
        position_before.setV(position);
        direction_degrees_before = direction_degrees;

        position.add(increment_position);
        direction_degrees += increment_direction_degrees;
    }

    protected void move() {
        position_before.setV(position);
        direction_degrees_before = direction_degrees;

        position.add(increment_position);
        direction_degrees += increment_direction_degrees;

        increment_position.setV(0, 0);
        increment_direction_degrees = 0;
    }

    protected void moveback() {
        position.setV(position_before);
        direction_degrees = direction_degrees_before;
        collision_detected = false;
    }


    public void AImoveToTarget(Vec target_position) {
        if (AIcheckPath(target_position)) {
            increment_position.setV((velocity * (float) (Math.cos(Math.toRadians(direction_degrees)))), velocity * (float) (-Math.sin(Math.toRadians(direction_degrees))));
            double target_angle = target_position.subReturn(position).getAngle();  //target angle in radians
            if (target_angle - Math.toRadians(direction_degrees) > 0.05)
                turnLeft();
            else if (target_angle - Math.toRadians(direction_degrees) < -0.05)
                turnRight();
            move();
        }
    }


    public void AImove() {
        if (!AIDetectMe())
            AIfindShip();
        else if (AIDetectMe()) {
            ship_turret.AItakeAim(GameData.MyShipPosition);
            if (AIcalculateDistance() > 120)
                AImoveToTarget(GameData.MyShipPosition);
        }
    }

    public boolean AIDetectMe() {
        for (int i = 0; i < GameData.sea.Obst.size(); i++)
            for (int j = 0; j < GameData.sea.Obst.get(i).Vertexes.size() - 1; j++)
                if (Vec.linesIntersect(position, GameData.MyShipPosition, GameData.sea.Obst.get(i).Vertexes.get(j), GameData.sea.Obst.get(i).Vertexes.get(j + 1)))
                    return false;
        return true;
    }


    public float AIcalculateDistance() {
        return position.distance(GameData.MyShipPosition);
    }


    public boolean AIcheckPath(Vec destination) {
        for (int i = 0; i < GameData.sea.Obst.size(); i++)
            for (int j = 0; j < GameData.sea.Obst.get(i).Vertexes.size() - 1; j++)
                if (Vec.linesIntersect(position, destination, GameData.sea.Obst.get(i).Vertexes.get(j), GameData.sea.Obst.get(i).Vertexes.get(j + 1)))
                    return false;
        return true;
    }

    public void AIpatrol(Obstacle O) {
        //to find my ship it floats around the obstacles one by one.

        // first get the positions.
    }

    public boolean AIcheckShore() {
        for (int i = 0; i < GameData.sea.Obst.size(); i++)
            for (int j = 0; j < GameData.sea.Obst.get(i).Vertexes.size() - 1; j++)
                if (position.distance(GameData.sea.Obst.get(i).Vertexes.get(j), GameData.sea.Obst.get(i).Vertexes.get(j + 1)) < 50)
                    return true;

        return false;
    }

    public void AIfindShip() {
        AImoveToTarget(GameData.sea.Obst.get(0).getPosition().addReturn(GameData.sea.Obst.get(0).getSize(), GameData.sea.Obst.get(0).getSize()));   //Very bad. make it good

    }

}
