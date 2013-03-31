import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends JFrame implements KeyListener, MouseListener {

  /*
    private Ship ship = null;
    private Field sea = null;

    private Enemy enemy = null;

    public static ArrayList<Bullet> Bullets = new ArrayList<Bullet>();
    public static Vec MyShipPosition = new Vec();
*/

    private boolean up_pressed = false;
    private boolean down_pressed = false;
    private boolean left_pressed = false;
    private boolean right_pressed = false;
    private boolean fire_pressed = false;
    private boolean a_pressed = false;
    private boolean d_pressed = false;


    Game() {
        super("Sea Battle");
        setSize(1224, 768);
        setLocation(300, 120);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createBufferStrategy(2);

        this.setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
    }

    public void update() {
        handle_events();
        GameData.myShip.update();
        GameData.enemy.update();

        GameData.sea.update();      // Nothing yet there. Mb wort to put MoveBullets there

        resolveCollisions();
        moveBullets();

        GameData.enemy.addShip(GameData.sea.spawnPoint1);
    }

    public void moveBullets() {
        for (int j = 0; j < GameData.Bullets.size(); j++) {
            GameData.Bullets.get(j).move();
            if (GameData.Bullets.get(j).CheckCollision())
                GameData.Bullets.remove(j);
        }
    }

    private void resolveCollisions() {
        for (int i = 0; i < GameData.enemy.Ships.size(); i++)
            GameData.myShip.CollisionCheck(GameData.enemy.Ships.get(i));

        //Colliions with obstales
        for (int i = 0; i < GameData.sea.Obst.size(); i++) {
            GameData.myShip.CollisionCheck(GameData.sea.Obst.get(i));                      //My Ship
            for (int j = 0; j < GameData.enemy.Ships.size(); j++)                        //Enemy Ships
                GameData.enemy.Ships.get(j).CollisionCheck(GameData.sea.Obst.get(i));
        }
    }


    private void render_statistics(Graphics2D g) {

        g.setColor(Color.BLACK);
        g.drawRect(900, 100, 250, 500);
        //g.drawString(String.valueOf(enemy.Ships.get(0).is_destroyed), 910, 150);
        g.drawString("Your ship position", 980, 150);
//        g.drawString(String.valueOf(GameData.enemy.Ships.get(0).ship_turret.getFireAngle()), 910, 180);
        g.drawString(GameData.myShip.getPosition().toString(), 1000, 180);
        g.drawString(GameData.myShip.ship_turret.getFire_direction().toString(), 1000, 210);
        // g.drawString(getMousePosition().toString(),310,240);


        //g.drawString(String.valueOf(GameData.enemy.Ships.get(0).direction_degrees), 910, 180);



        // g.drawString(Bullets.get(0).getPos().toString(), 950, 180);

    }

    public void display() {
        BufferStrategy bf = this.getBufferStrategy();
        Graphics2D g = null;

        try {
            g = (Graphics2D) bf.getDrawGraphics();
            g.clearRect(0, 0, 1224, 768);
            GameData.sea.render(g);
            GameData.myShip.render(g);
            GameData.enemy.render(g);
            render_statistics(g);

            for (int j = 0; j < GameData.Bullets.size(); j++) {
                GameData.Bullets.get(j).render(g);
            }

        } finally {
            if (g != null)
                g.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();

    }

    private void handle_events() {
        if (!GameData.myShip.is_destroyed && !GameData.myShip.collision_detected) {
            if (up_pressed)
                GameData.myShip.moveStraight();
            if (left_pressed)
                GameData.myShip.turnLeft();
            if (right_pressed)
                GameData.myShip.turnRight();
            if (fire_pressed)
                GameData.myShip.ship_turret.fire();
            if (a_pressed)
                GameData.myShip.ship_turret.turnLeft();
            if (d_pressed)
                GameData.myShip.ship_turret.turnRight();
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up_pressed = true;
                break;
            case KeyEvent.VK_LEFT:
                left_pressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                right_pressed = true;
                break;
            case KeyEvent.VK_F:
                fire_pressed = true;
                break;
            case KeyEvent.VK_A:
                a_pressed = true;
                break;
            case KeyEvent.VK_D:
                d_pressed = true;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up_pressed = false;
                break;
            case KeyEvent.VK_LEFT:
                left_pressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                right_pressed = false;
                break;
            case KeyEvent.VK_F:
                fire_pressed = false;
                break;
            case KeyEvent.VK_A:
                a_pressed = false;
                break;
            case KeyEvent.VK_D:
                d_pressed = false;
                break;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}




