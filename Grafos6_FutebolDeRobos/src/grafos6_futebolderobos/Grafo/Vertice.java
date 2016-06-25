package grafos6_futebolderobos.Grafo;

import javafx.geometry.Point2D;

public class Vertice {

    private Point2D coord;
    private int ID;
    private static int contID;

    public Vertice(Point2D coord) {
        this.coord = coord;
        ID = contID;
        contID++;
    }

    public Vertice(double x, double y) {
        Point2D coord = new Point2D(x, y);
        this.coord = coord;
        ID = contID;
        contID++;
    }

    public boolean isEqual(Vertice v2) {
        return coord.equals(v2.getCoord());
    }

    public Point2D getCoord() {
        return coord;
    }

    public int getID() {
        return ID;
    }

    public int compareTo(Vertice v) {
        if (this.ID == v.getID()) {
            return 0;
        } else {
            if (this.ID < v.getID()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    @Override
    public String toString() {
        return "(" + coord.getX() + " , " + coord.getY() + ")";
    }

}
