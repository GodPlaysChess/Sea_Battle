import java.awt.*;
import java.util.ArrayList;

public class Field {

    public final int borderX = 100;
    public final int borderY = 100;
    public final int sizeX;
    public final int sizeY;

    public final static Color SEA = new Color(137, 183, 255);

    public final Vec spawnPoint1 = new Vec(borderX + 50, borderY + 50);
    private Vec spwanPoint2 = new Vec(00, 0);

    public ArrayList<Obstacle> Obst = new ArrayList();


    Field(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        addObstacles();
    }

    private void addObstacles() {
        Obst.add(new Obstacle(250, 480, 50, 80, Obstacle.ISLAND));
        Obst.add(new Obstacle(450, 280, 100, 60, Obstacle.CLIFF));
    }


    public void render(Graphics2D g) {
        g.setColor(SEA);
        g.fillRect(borderX, borderY, sizeX, sizeY);
        for (int i = 0; i < Obst.size(); i++) {
            Obst.get(i).render(g);
        }

        for (int i =0; i< GameData.Coins.size();i++)
            GameData.Coins.get(i).render(g);

        //Vec temp = new Vec(O.getPosition().addReturn(O.Vertexes.get(i).subReturn(O.getPosition()))); - envelope curve for beauty
    }
}
