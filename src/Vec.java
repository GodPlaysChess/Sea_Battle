import java.awt.geom.Point2D;

public class Vec {
    private float x;
    private float y;

    Vec(float x, float y) {
        this.x = x;
        this.y = y;
    }

    Vec(Point2D.Float p) {
        x = p.x;
        y = p.y;
    }

    Vec(Point2D p) {
        x = (float) p.getX();
        y = (float) p.getY();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float a) {
        x = a;
    }

    public void setY(float b) {
        x = b;
    }

    public void setV(Vec v) {
        x = v.getX();
        y = v.getY();
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float distance(Vec v) {
        return ((float) Math.sqrt((v.getX() - x) * (v.getX() - x) + (v.getY() - y) * (v.getY() - y)));
    }

    public void add(float a) {
        x = getX() + a;
        y = getY() + a;
    }

    public void add(float a, float b) {
        x = getX() + a;
        y = getY() + b;
    }

    public void add(Vec v) {
        x = getX() + v.getX();
        y = getY() + v.getY();
    }

    public Vec addReturn (Vec v){
        return new Vec(getX()+v.getX(), getY()+v.getY());
    }

    public Vec subReturn(Vec v){
        return new Vec(getX()-v.getX(), getY()-v.getY());
    }

    public void multiply(float a) {
        x = getX()*a;
        y = getY()*a;
    }

    public Vec multiplied(float a) {
        return new Vec(getX()*a, getY()*a);
    }

    public float getAngle(){
        return(-(float)Math.atan(getY()/getX()));
    }

    public Vec normalize() {
        return new Vec(getX() / length(), getY() / length());
    }

    public void turn(float a)/**in DEG*/{
        float alpha = (float)Math.toRadians(a);
        x = (float)(getX()*Math.cos(alpha)+getY()*Math.sin(alpha));
        y = (float)(-getX()*Math.sin(alpha)+getY()*Math.cos(alpha));

    }

    boolean OutOfBounds(float borderX, float borderY, float sizeX, float sizeY) {
        if (getX() < sizeX && getY() < sizeY && getX() > borderX && getY() > borderY)
            return false;
        else return true;
    }

    public String toString(){
        return (getX() + " , " +getY());
    }

}
