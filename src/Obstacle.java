import java.awt.*;
import java.util.ArrayList;

public class Obstacle {

    private int num_vertexes;
    private int type;

    public final static int CLIFF = 1;
    public final static int ISLAND = 2;

    private ArrayList<Vec> Vertexes = new ArrayList();


    Obstacle(Vec position, int size, int num_vertexes, int type) {


        for (int i = 0; i < num_vertexes; i++) {
            Vertexes.add(new Vec(position.getX() + (float)Math.random() * 2 * size - size, position.getY() + (float) Math.random() * 2 * size - size));
        }
        this.type = type;
        this.num_vertexes = num_vertexes;

    }

    Obstacle(int xpos, int ypos, int size, int num_vertexes, int type) {
        for (int i = 0; i < num_vertexes; i++) {
            Vertexes.add(new Vec(xpos + (float)Math.random() * 2 * size - size, ypos + (float) Math.random() * 2 * size - size));
        }
        this.type = type;
        this.num_vertexes = num_vertexes;

    }

    public void render(Graphics2D g) {
        g.setColor(Color.GRAY);
        GeomHelp.fillPolygon(Vertexes, g);
    }


}
