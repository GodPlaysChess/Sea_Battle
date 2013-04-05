import java.awt.*;

public class Coin extends ObjectOnMap {
    public final int radii = 9;

    public Coin() {
        position.setX((float) Math.random() * GameData.sea.sizeX + GameData.sea.borderX);
        position.setY((float) Math.random() * GameData.sea.sizeY + GameData.sea.borderY);
        decomposition = 5;
    }

    public void render(Graphics2D g) {
        if (!is_destroyed){
        g.setColor(Color.YELLOW);
        GeomHelp.FillCircle((int) position.getX(), (int) position.getY(), radii, g);
        g.setColor(Color.BLACK);
        GeomHelp.DrawCircle((int) position.getX(), (int) position.getY(), radii + 1, g);
        }
    }

    protected void checkBulletHit() {
        for (int j = GameData.Bullets.size() - 1; j >= 0; j--)
            if (position.distance(GameData.Bullets.get(j).getPos(), GameData.Bullets.get(j).getNextPos()) <= radii) {
                GameData.Bullets.remove(j);
                GameData.Coins.remove(this);
                break;
            }
    }

}
