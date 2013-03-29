import java.awt.*;
import java.util.*;

public class Obstacle {


    private int type;
    private Vec position = new Vec(0, 0);

    public final static int CLIFF = 1;
    public final static int ISLAND = 2;

    public ArrayList<Vec> Vertexes = new ArrayList();

    public int getVertSize() {
        return Vertexes.size();
    }

    Obstacle(Vec position, int size, int num_vertexes, int type) {


        for (int i = 0; i < num_vertexes; i++) {
            Vertexes.add(new Vec(position.getX() + (float) Math.random() * 2 * size - size, position.getY() + (float) Math.random() * 2 * size - size));
        }
        Vertexes.add(Vertexes.get(0));
        this.type = type;


    }

    Obstacle(int xpos, int ypos, int size, int num_vertexes, int type) {
        this.type = type;
        position.setV(xpos, ypos);
        ArrayList<Vec> TempVertexes = new ArrayList<Vec>();
        for (int i = 0; i < num_vertexes; i++) {
            TempVertexes.add(new Vec(xpos + (float) Math.random() * 2 * size - size, ypos + (float) Math.random() * 2 * size - size));
        }
        Vertexes = new ArrayList<Vec>(GeomHelp.findPolygon(TempVertexes));
    }

    public void render(Graphics2D g) {
        if (type == CLIFF) {
            g.setColor(Color.GREEN);
            GeomHelp.fillPolygon(Vertexes, g);
        }
        if (type == ISLAND) {
            g.setColor(new Color(154, 106, 46));
            GeomHelp.FillCircle((int) position.getX(), (int) position.getY(), 50, g);
        }
    }


}
