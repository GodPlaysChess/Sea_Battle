import java.awt.*;
import java.util.ArrayList;

public class AIShip extends Ship {


    private float attacking_distance;
    private boolean has_an_order = false;
    private boolean reached_destination = false;
    private boolean direction_adjusted = false;


    private ArrayList<Vec> Trajectory = new ArrayList<Vec>();


    /**
     * May be it is worth making some command queue..
     */

    public AIShip(Vec v, Color color) {
        super(v, color);
    }

    public void update() {
        AImove();
        super.update();
    }

    public void AImove(ArrayList<Vec> Trajectory) {
        AImoveToTarget(Trajectory.get(0));
        if (Math.abs(position.getX() - Trajectory.get(0).getX()) < 25 && StrictMath.abs(position.getY() - Trajectory.get(0).getY()) < 25) {
            Trajectory.remove(0);
            direction_adjusted = false;
            if (Trajectory.size() == 0)
                has_an_order = false;
        }
    }

    //here is definitely  a problem. And this problem is degrees calculation  . Withour direction adjustement
    public void AImoveToMe() {
        if (AIcheckPath(GameData.MyShipPosition)) {
            increment_position.setV((velocity * (float) (Math.cos(Math.toRadians(direction_degrees)))), velocity * (float) (-Math.sin(Math.toRadians(direction_degrees))));
            double target_angle = GameData.MyShipPosition.subReturn(position).getAngle();  //target angle in radians
            double delta_phi = target_angle - Math.toRadians(direction_degrees);
            if (delta_phi > Math.PI)
                delta_phi -= 2 * Math.PI;
            if (delta_phi < -Math.PI)
                delta_phi += 2 * Math.PI;
            if (delta_phi > 0.05)
                turnLeft();
            else if (delta_phi < -0.05)
                turnRight();
        }
    }


    private void AImoveToTarget(Vec target_position) {
        if (!direction_adjusted)
            AIpatrolAdjustDirection(target_position);
        else
            AIpatrolMoveToNextPoint();
    }

    public void AIpatrolAdjustDirection(Vec target_position) {
        double target_angle = target_position.subReturn(position).getAngle();  //target angle in radians
        double delta_phi = Math.toRadians(direction_degrees) - target_angle;

        if (delta_phi > Math.PI)
            delta_phi -= 2 * Math.PI;
        if (delta_phi < -Math.PI)
            delta_phi += 2 * Math.PI;

        if (delta_phi > 0.05) {
            turnRight();
        } else if (delta_phi < -0.05) {
            turnLeft();
        } else direction_adjusted = true;
    }

    public void AIpatrolMoveToNextPoint() {
        increment_position.setV((velocity * (float) (Math.cos(Math.toRadians(direction_degrees)))), velocity * (float) (-Math.sin(Math.toRadians(direction_degrees))));
    }


    public void AImove() {
        if (!AIDetectMe() && !has_an_order)
            AIfindShip();
        if (!AIDetectMe() && has_an_order)
            AImove(Trajectory);
        else if (AIDetectMe()) {
            Trajectory.clear();
            ship_turret.AItakeAim(GameData.MyShipPosition);
            if (AIcalculateDistance() > 120)
                AImoveToMe();
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


    //Simply doubles the distance between center of the Island to it vertexes hence, add new envelope curve        !may be worth doing this 1.5 of value. Or Vert+1/2PrevVec+1/2NextVec

    private void AIestimateTrajectory(Obstacle O) {
        for (int i = 0; i < O.Vertexes.size() - 1; i++) {
            Vec temp = new Vec((O.Vertexes.get(i).subReturn(O.getPosition())));
            Trajectory.add(O.getPosition().addReturn(temp).addReturn(temp));
        }
    }

    public boolean AIcheckShore() {
        for (int i = 0; i < GameData.sea.Obst.size(); i++)
            for (int j = 0; j < GameData.sea.Obst.get(i).Vertexes.size() - 1; j++)
                if (position.distance(GameData.sea.Obst.get(i).Vertexes.get(j), GameData.sea.Obst.get(i).Vertexes.get(j + 1)) < 50)
                    return true;

        return false;
    }

    public void AIfindShip() {                                                                        //trying to float around random island;
        AIestimateTrajectory(GameData.sea.Obst.get((int) Math.random() * 2));
        has_an_order = true;
        AImove(Trajectory);
    }


    public void AIrenderTrajectory(Graphics2D g) {
        g.setColor(Color.BLACK);
        Polygon p = new Polygon();
        for (int i = 0; i < Trajectory.size(); i++)
            p.addPoint((int) Trajectory.get(i).getX(), (int) Trajectory.get(i).getY());

        g.drawPolygon(p);
    }

}
