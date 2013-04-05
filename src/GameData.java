import java.awt.*;
import java.util.ArrayList;

public class GameData {
    private static int timeCounter = 0;

    public static ArrayList<Bullet> Bullets = new ArrayList<Bullet>();
    public static Field sea = new Field(800, 500);
    public static Enemy enemy = new Enemy(sea.spawnPoint1);
    public static Ship myShip = new Ship(new Vec(850, 550), Ship.PLAYER1);
    public static Vec MyShipPosition = myShip.position;
    public static int score = 0;
    public static ArrayList<Coin> Coins = new ArrayList<Coin>();
    //public static ArrayList<Obstacle> Obst = new ArrayList();

    public final static Color PLAYER1 = new Color(78, 194, 19);
    public final static Color ENEMY = new Color(255, 89, 63);
    public final static Color SHIP = new Color(163, 82, 0);

    public static int getTimeCounter() {
        return timeCounter;
    }

    public static void increaseTimeCounter() {
        timeCounter++;
    }
}
