import java.awt.*;

public class Field {

    public final int borderX = 100;
    public final int borderY = 100;
    public final int sizeX;
    public final int sizeY;

    public final Vec spawnPoint1 = new Vec(borderX + 50, borderY + 50);
    private Vec spwanPoint2 = new Vec(00, 0);

    Field(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

    }


    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawRect(borderX, borderY, sizeX, sizeY);
    }
}
