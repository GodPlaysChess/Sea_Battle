import java.awt.*;
import java.util.*;

public class Obstacle {


    private int type;
    private Vec position = new Vec(0, 0);
    private int size;

    public final static int CLIFF = 1;
    public final static int ISLAND = 2;

    public ArrayList<Vec> Vertexes = new ArrayList();

    public int getVertSize() {
        return Vertexes.size();
    }


    Obstacle(int xpos, int ypos, int size, int num_vertexes, int type) {
        this.size = size;
        this.type = type;
        position.setV(xpos, ypos);
        ArrayList<Vec> TempVertexes = new ArrayList<Vec>();
        for (int i = 0; i < num_vertexes; i++) {
            TempVertexes.add(new Vec(xpos + (float) Math.random() * 2 * size - size, ypos + (float) Math.random() * 2 * size - size));
        }
        Vertexes = new ArrayList<Vec>(GeomHelp.findPolygon(TempVertexes));
        Vertexes.add(Vertexes.get(0));
    }

    public int getSize(){
        return size;
    }

    public Vec getPosition(){
        return position;
    }

    public void render(Graphics2D g) {
        if (type == ISLAND) {
            g.setColor(Color.GREEN);
            ArrayList<Vec> VertForrender = new ArrayList<Vec>(Vertexes);
            VertForrender.remove(VertForrender.size() - 1);
            GeomHelp.fillPolygon(VertForrender, g);
        }
        if (type == CLIFF) {
            g.setColor(new Color(104, 74, 29));
            ArrayList<Vec> VertForrender = new ArrayList<Vec>(Vertexes);
            VertForrender.remove(VertForrender.size() - 1);
            GeomHelp.fillPolygon(VertForrender, g);
        }
    }


}
