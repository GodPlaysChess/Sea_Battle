import java.awt.geom.Point2D;

public class Vec {
    private float x;
    private float y;

    Vec() {
        x = 0;
        y = 0;
    }

    Vec(Vec v) {
        setV(v);
    }

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

    public int compareTo(Vec v) {
        if (x > v.getX())
            return 1;
        else if (x < getX())
            return -1;
        else return 0;
    }

    public void setV(Vec v) {
        x = v.getX();
        y = v.getY();
    }

    public void setV(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float distance(Vec v) {
        return ((float) Math.sqrt((v.getX() - x) * (v.getX() - x) + (v.getY() - y) * (v.getY() - y)));
    }

    public float distance(Vec v1, Vec v2){
        float s1, s2, s3;
        s1 = this.subReturn(v1).length();
        s2 = this.subReturn(v2).length();
        s3 = v2.subReturn(v1).length();

        return (float)(Math.sqrt(s2*s2-(s2*s2-s3*s3-s1*s1)*(s2*s2-s3*s3-s1*s1)/(4*s3*s3)));
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

    public Vec addReturn(Vec v) {
        return new Vec(getX() + v.getX(), getY() + v.getY());
    }

    public Vec addReturn(float a, float b) {
        return new Vec(getX() + a, getY() + b);
    }

    public Vec subReturn(Vec v) {
        return new Vec(getX() - v.getX(), getY() - v.getY());
    }

    public void multiply(float a) {
        x = getX() * a;
        y = getY() * a;
    }

    public Vec multiplied(float a) {
        return new Vec(getX() * a, getY() * a);
    }

    public double getAngle() {                   //in Rads
        if (getX() > 0) return (-Math.atan(getY() / getX()));
        else return (-Math.atan(getY() / getX()) + Math.PI);
    }

    public Vec normalize() {

        return new Vec(getX() / length(), getY() / length());
    }

    public void turn(float a)/**in DEG*/
    {
        float alpha = (float) Math.toRadians(a);
        x = (float) (getX() * Math.cos(alpha) + getY() * Math.sin(alpha));
        y = (float) (-getX() * Math.sin(alpha) + getY() * Math.cos(alpha));

    }

    boolean OutOfBounds(float borderX, float borderY, float sizeX, float sizeY) {
        if (getX() < sizeX && getY() < sizeY && getX() > borderX && getY() > borderY)
            return false;
        else return true;
    }

    public String toString() {
        return (getX() + " , " + getY());
    }

    static boolean linesIntersect(Vec a1, Vec a2, Vec b1, Vec b2) {

        float a = (a1.getX() * b1.getY() - b1.getX() * a1.getY() - a1.getX() * b2.getY() + b2.getX() * a1.getY() + b1.getX() * b2.getY() - b2.getX() * b1.getY()) / (a1.getX() * b1.getY() - b1.getX() * a1.getY() - a1.getX() * b2.getY() - a2.getX() * b1.getY() + b1.getX() * a2.getY() + b2.getX() * a1.getY() + a2.getX() * b2.getY() - b2.getX() * a2.getY());
        float b = -(a1.getX() * a2.getY() - a2.getX() * a1.getY() - a1.getX() * b1.getY() + b1.getX() * a1.getY() + a2.getX() * b1.getY() - b1.getX() * a2.getY()) / (a1.getX() * b1.getY() - b1.getX() * a1.getY() - a1.getX() * b2.getY() - a2.getX() * b1.getY() + b1.getX() * a2.getY() + b2.getX() * a1.getY() + a2.getX() * b2.getY() - b2.getX() * a2.getY());

        return (a > 0 && a < 1 && b > 0 && b < 1);
    }


}
