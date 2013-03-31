import java.awt.*;

public class Turret {
    private Vec position = new Vec(0, 0);
    public final int cooldown; //in millisec

    private float fire_angle = 0;
    public Vec fpos = new Vec(0, 0);
    //Vector of the center of the cannon
    private Color color;

    private int TimeToCD = 1000;

    Turret(Ship s, Color color) {
        // position = s.getPosition();
        position = s.position;                                           //The same vector as the corresponding ship position
        cooldown = 5;
        this.color = color;

    }

    Turret(Vec xy, int cd, Color color) {   //constructor for elswhere locataed Turrel
        cooldown = cd;
        position.setV(xy);
        this.color = color;
    }

    public void turnLeft() {
        fire_angle += 5; //turn on +5degrees
    }

    public void turnRight() {
        fire_angle -= 5; //turn on -5degrees
    }

    public void render(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        GeomHelp.fillPolygon((int) fpos.getX(), (int) fpos.getY(), 15, 3, (float) Math.toRadians(fire_angle), g);

        g.setColor(color);
        GeomHelp.FillCircle((int) position.getX(), (int) position.getY(), 10, g);
    }

    public void setPosition(Vec v) {
        position.setV(v);
    }

    public Vec getPosition() {
        return position;
    }

    public Vec getFire_direction() {
        return new Vec((float) Math.cos(Math.toRadians(fire_angle)), (float) -Math.sin(Math.toRadians(fire_angle)));
    }


    public void fire() {
        if (TimeToCD > cooldown) {
            TimeToCD = 0;
            GameData.Bullets.add(new Bullet(fpos.addReturn(getFire_direction().multiplied(16)), getFire_direction()));   // was Game.Bullets.add(new Bullet(getPosition().addReturn(getFire_direction().multiplied(30)), getFire_direction()));
        }
    }


    public void AItakeAim(Vec aim_position) {

        double target_fire_angle = aim_position.subReturn(position).getAngle();  //target angle in radians
        double delta_phi = target_fire_angle - Math.toRadians(fire_angle);
        if (delta_phi > Math.PI)
            delta_phi -= 2*Math.PI;
        if (delta_phi < -Math.PI)
            delta_phi += 2*Math.PI;

        if (delta_phi > 0.05)
            turnLeft();
        else if (delta_phi < -0.05)
            turnRight();
        else fire();

        System.out.println((int) Math.toDegrees(target_fire_angle));

        //Probably make boolean and return true if aimed, and false otherwise;
    }


    public void incTimeToCD() {
        TimeToCD++;
    }

    public float getFireAngle() {
        return fire_angle;
    }

    //moves turret along with the ship
    public void update() {
        //setPosition(s.position);
        fpos.setV(getPosition().addReturn(getFire_direction().multiplied(10)));
        incTimeToCD();
    }
}
