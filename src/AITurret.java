import java.awt.*;

public class AITurret extends Turret {


    AITurret(Vec xy, int cd, Color color) {
        super(xy, cd, color);
    }


    public void update() {
        super.update();
        AItakeAim(GameData.MyShipPosition);
    }
}
