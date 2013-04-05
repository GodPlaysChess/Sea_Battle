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
        if (this.equals(GameData.myShip)==false && this.decomposition != 5) //It was tricky way to write (if this!=coin) cuz only coin has decomposition time 5
            GameData.score++;
        decompositionStart();
    }

    private void decompositionStart() {
        dec_speed = 5;
    }

    protected void update() {
        checkBulletHit();
        decomposition -= dec_speed;
    }

    protected void checkBulletHit() {
        for (int j = GameData.Bullets.size() - 1; j >= 0; j--)
            for (int i = 0; i < Points.size() - 1; i++) {
                if (Vec.linesIntersect(GameData.Bullets.get(j).getPos(), GameData.Bullets.get(j).getNextPos(), Points.get(i), Points.get(i + 1))) {
                    GameData.Bullets.remove(j);
                    if (!is_destroyed)
                        destroy();
                    break;
                }
            }
    }
}
