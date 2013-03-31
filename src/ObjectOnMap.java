import java.awt.*;
import java.util.ArrayList;

public class ObjectOnMap {

    protected Vec position = new Vec(0, 0);
    protected Vec position_before = new Vec(0, 0);
    protected Vec velocity;
    protected boolean is_destroyed = false;
    protected int decomposition = 100;                                           // amount of time in Millisec, the destroyed body to leave on the field.
    private int dec_speed = 0;
    private boolean bullet_hit = false;
    protected ArrayList<Vec> Points = new ArrayList<Vec>();


    public void render(Graphics2D g) {

    }

    protected void destroy() {
        is_destroyed = true;
        decompositionStart();
    }

    private void decompositionStart() {
        dec_speed = 5;
    }

    protected void update() {
        checkBulletHit();
        decomposition -= dec_speed;
    }

    private void checkBulletHit() {
        for (int j = GameData.Bullets.size() - 1; j >= 0; j--)
            for (int i = 0; i < Points.size() - 1; i++) {
                if (Vec.linesIntersect(GameData.Bullets.get(j).getPos(), GameData.Bullets.get(j).getNextPos(), Points.get(i), Points.get(i + 1))) {
                    System.out.println("HIT");
                    destroy();
                    GameData.Bullets.remove(j);
                    break;
                }
            }
    }
}
