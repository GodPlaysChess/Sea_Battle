import java.awt.*;

public class Field {

    public final int borderX = 100;
    public final int borderY = 100;
    public final int sizeX;
    public final int sizeY;

    Field(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawRect(borderX, borderY, sizeX, sizeY);
    }
}
