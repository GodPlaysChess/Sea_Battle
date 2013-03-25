import com.sun.prism.shader.DrawCircle_Color_Loader;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * a simple class, to help process with geometric objects onto the field.
 * Render polygons, find the coordinates, rotate polygons etc..
 * Class methods are static, alike with java.Math *
 * Angles are had to be set in radians
 */

public class GeomHelp {

    public final static Vec FindFR(float x, float y, float a, float b, float alpha) {
        return new Vec(x + a * (float) Math.cos(alpha) + b * (float) Math.sin(alpha), y - a * (float) Math.sin(alpha) + b * (float) Math.cos(alpha));
    }

    public final static Vec FindFL(float x, float y, float a, float b, float alpha) {
        return new Vec(x + a * (float) Math.cos(alpha) - b * (float) Math.sin(alpha), y - a * (float) Math.sin(alpha) - b * (float) Math.cos(alpha));
    }

    public final static Vec FindRR(float x, float y, float a, float b, float alpha) {
        return new Vec(x - a * (float) Math.cos(alpha) + b * (float) Math.sin(alpha), y + a * (float) Math.sin(alpha) + b * (float) Math.cos(alpha));
    }

    public final static Vec FindRL(float x, float y, float a, float b, float alpha) {
        return new Vec(x - a * (float) Math.cos(alpha) - b * (float) Math.sin(alpha), y + a * (float) Math.sin(alpha) - b * (float) Math.cos(alpha));

    }

    /**
     * Function renders a oriented rectangle
     *
     * @param x , y - center coordinates, a,b half sizes and alpha - angle between a and OX in radians
     */
    public static void renderPolygon(float x, float y, float a, float b, float alpha, Graphics2D g) {
        Polygon p = buildPolygon(x, y, a, b, alpha);
        g.drawPolygon(p);
    }

    private static Polygon buildPolygon(float x, float y, float a, float b, float alpha) {
        ArrayList<Vec> result = findPolygon(x, y, a, b, alpha);
        Polygon p = new Polygon();
        for (int i = 0; i < 4; i++) {
            p.addPoint((int) result.get(i).getX(), (int) result.get(i).getY());
        }
        return p;
    }

    public static ArrayList<Vec> findPolygon(float x, float y, float a, float b, float alpha) {
        ArrayList<Vec> result = new ArrayList<Vec>();

        result.add(FindFR(x, y, a, b, alpha));
        result.add(FindFL(x, y, a, b, alpha));
        result.add(FindRL(x, y, a, b, alpha));
        result.add(FindRR(x, y, a, b, alpha));
        result.add(result.get(0));
        return result;

    }

    public static void fillPolygon(float x, float y, float a, float b, float alpha, Graphics2D g) {
        Polygon p = buildPolygon(x, y, a, b, alpha);
        g.fillPolygon(p);
    }

    public static Point2D.Float normlize(Point2D v) {
        float dist = (float) v.distance(0, 0);
        Point2D.Float p = new Point2D.Float();
        p.setLocation(v.getX() / dist, v.getY() / dist);
        return p;
        //return new Point2D.Float(v.getX() / dist, v.getY() / dist);
    }

    public static Point2D.Float timesConst(Point2D.Float p, float a) {
        Point2D.Float p1 = new Point2D.Float();
        p1.setLocation(p.getX() * a, p.getY() * a);
        return p1;
    }

    public static Point2D.Float add(Point2D.Float a, Point2D.Float b) {
        Point2D.Float p1 = new Point2D.Float();
        p1.setLocation(a.getX() + b.getX(), a.getY() + b.getY());
        return p1;
    }

    public static void FillCircle(int x, int y, int r, Graphics2D g) {
        g.fillOval(x - r, y - r, 2 * r, 2 * r);

    }


}