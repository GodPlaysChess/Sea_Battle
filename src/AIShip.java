import java.awt.*;
import java.util.ArrayList;

public class AIShip extends Ship {
    private float attacking_distance;
    private boolean forced_to_move = false;
    private boolean reached_destination = false;


    public AIShip(Vec v, Color color) {
        super(v, color);
    }

    public void move(ArrayList<Vec> Trajectory) {
        forced_to_move = true;
        for (int i=0; i< Trajectory.size(); i++){
            while (position!=Trajectory.get(i))
                AImoveToTarget(Trajectory.get(i));
            if (position==Trajectory.get(i))
                Trajectory.remove(i);
        }
    }

}
