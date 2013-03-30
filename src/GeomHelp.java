import com.sun.prism.shader.DrawCircle_Color_Loader;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public static void renderPolygon(ArrayList<Vec> AV, Graphics2D g) {

        Polygon p = new Polygon();
        for (int i = 0; i < AV.size(); i++) {
            p.addPoint((int) AV.get(i).getX(), (int) AV.get(i).getY());
        }
        g.drawPolygon(p);

    }


    public static void fillPolygon(ArrayList<Vec> AV, Graphics2D g) {

        Polygon p = new Polygon();
        for (int i = 0; i < AV.size(); i++) {
            p.addPoint((int) AV.get(i).getX(), (int) AV.get(i).getY());
        }
        g.fillPolygon(p);

    }

    /**
     * This method returns transforms an array of RANDOM vertexes (in Vec format
     * to the vertexes of GOOD-LOOKING polygon
     */

    public static ArrayList<Vec> findPolygon(ArrayList<Vec> InputVertexes) {
        float ymax = 0;
        float ymin = 100000;
        int index = 0;
        Vec temp = new Vec();

        ArrayList<Vec> result = new ArrayList<Vec>();

        //Sorting a random vectors in an increasing-X order;

        Collections.sort(InputVertexes, new Comparator<Vec>() {
            @Override
            public int compare(Vec o1, Vec o2) {
                if (o1.getX() < o2.getX())
                    return -1;
                else if (o1.getX() > o2.getX())
                    return 1;
                else
                    return 0;
            }
        });

        //now for a simplest case we need to find Vector with smallest Y coordinate (in the most cases it will possess higher X coordinate)
        //Looking through whole array and a vector corresponding to the minimum value of Y to temp vector. Then set this vector as a second vertex of the polygon.

            for (int i = 0; i < InputVertexes.size(); i++) {
                ymin = Math.min(ymin, InputVertexes.get(i).getY());
                if (ymin == InputVertexes.get(i).getY())
                    index = i;
            }


        // Now checking that Ymin != Xmin


        //the first point of the polygon is the Xmin point(whatever Y it has)

        result.add(InputVertexes.get(0));

        //Now let's insert all useful points between Xmin and Ymin:
        if (index > 0)
            for (int i = 1; i <= index; i++) {
                if (InputVertexes.get(i).getY() <= result.get(result.size() - 1).getY()) {
                    result.add(InputVertexes.get(i));
                }
            }

        //result.add(new Vec(temp));
        // Adding points between Ymin and Xmax
        if (index < InputVertexes.size() - 1)
            for (int i = index + 1; i <= InputVertexes.size() - 1; i++) {
                if (InputVertexes.get(i).getY() >= result.get(result.size() - 1).getY() && InputVertexes.get(i).getY() <= InputVertexes.get(InputVertexes.size() - 1).getY()) {
                    result.add(InputVertexes.get(i));
                }
            }


        //Now the easier procedure with the next(3rd point) (max X, whatever Y), cause our array is already sorted in X:
        // result.add(InputVertexes.get(InputVertexes.size() - 1));


        //And the last point is (whatever X, Y max). The similar procedure as step 2.

            for (int i = 0; i < InputVertexes.size(); i++) {
                ymax = Math.max(ymax, InputVertexes.get(i).getY());
                if (ymax == InputVertexes.get(i).getY())
                    index = i;
            }



        // Third quater points:
        if (index < InputVertexes.size() - 1)
            for (int i = InputVertexes.size() - 2; i >= index; i--) {
                if (InputVertexes.get(i).getY() >= result.get(result.size() - 1).getY()) {
                    result.add(InputVertexes.get(i));
                }
            }
        //
        //   result.add(new Vec(temp));
        //Fourth quater
        if (index > 0)
            for (int i = index - 1; i >= 0; i--) {
                if (InputVertexes.get(i).getY() >= InputVertexes.get(0).getY() && InputVertexes.get(i).getY() <= result.get(result.size() - 1).getY()) {
                    result.add(InputVertexes.get(i));
                }
            }
        //And finally adding the 5th point to the polygon, to perform lines_intersect search method later


        return result;
    }

    /*private Vec findNextPoint() {

        return;
    } */

}