package grafos6_futebolderobos;

import grafos6_futebolderobos.Grafo.Grafo;
import grafos6_futebolderobos.Grafo.Vertice;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

public class Futebol {

    private Point2D robo; //coordenadas do robô
    private Point2D gol;
    private List<Point2D> adversarios; //lista com coordenadas dos outros robos
    private Grafo g; //grafo só com os caminhos/pesos aceitos
    private double a, b; //constantes a e b da equação da reta principal robo->gol
    private double raio = 0.25;

    public Futebol(Point2D robo, Point2D gol) {
        this.robo = robo;//(3.58,14.89)
        this.gol = gol; //(18.84,6.66)
        g = new Grafo();
        g.addVertice(robo);
        g.addVertice(gol);
        a = (robo.getY() - gol.getY()) / (robo.getX() - gol.getX()); //constante "a" da reta principal
        b = ((-1 * a) * robo.getX()) + robo.getY(); //constante "b" da reta principal
        adversarios = new ArrayList<>(); //(8.4,13.92), (9.51,10.89), (12.25,11.77), (13.8,9.33)
    }

    public void addAdversario(Point2D a) {
        if (!isInList(a)) {
            adversarios.add(a);
        }
    }

    public void addAdversario(double x, double y) {
        Point2D a = new Point2D(x, y);
        if (!isInList(a)) {
            adversarios.add(a);
        }
    }

    private boolean isInList(Point2D a) {
        for (Point2D x : adversarios) {
            if (a.getX() == x.getX() && a.getY() == x.getY()) {
                return true;
            }
        }
        return false;
    }

    public void montaGrafo() {
        calcPontos();
        adicionaArestas();

    }

    public void adicionaArestas() {
        for (int i = 0; i < g.getV().size() - 1; i++) {
            for (int j = i + 1; j < g.getV().size(); j++) {
                confereAresta(g.getV().get(i), g.getV().get(j));
            }
        }
    }

    private void confereAresta(Vertice v1, Vertice v2) { //p2 é o destino
        Point2D p1, p2;

        p1 = v1.getCoord();
        p2 = v2.getCoord();

        double at = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        double bt = ((-1 * at) * p1.getX()) + p1.getY();

        boolean flag = true;
        for (Point2D a : adversarios) {
            double Cx, Cy;
            Cx = a.getX();
            Cy = a.getY();
            double delta = Math.pow((2 * at * bt - 2 * at * Cy - 2 * Cx), 2) - 4 * (at * at + 1) * (bt * bt - 2 * Cy * bt + Cx * Cx - raio * raio);
//            System.out.println("Delta: " + delta);
            if (delta > 0) {
                Point2D[] temp = getIntersecao(Cx, Cy, at, bt);
                if (p1.getX() < p2.getX()) {
                    if ((temp[0].getX() > p1.getX() && temp[0].getX() < p2.getX()) || (temp[1].getX() > p1.getX() && temp[1].getX() < p2.getX())) //vê se está no segmento de reta'
                    {

                        if (temp[0].distance(temp[1]) > 1.25 * raio) {
                            flag = false;
                            break;
                        }
                    }
                } else if ((temp[0].getX() < p1.getX() && temp[0].getX() > p2.getX()) || (temp[1].getX() < p1.getX() && temp[1].getX() > p2.getX())) {

                    if (temp[0].distance(temp[1]) > 1.25 * raio) {
                        flag = false;
                        break;
                    }
                }
            }
        }

        if (flag) {
            g.addAresta(new Vertice(p1), new Vertice(p2), p1.distance(p2));
        }

    }

    public void calcPontos() {
        for (Point2D a : adversarios) {
            calcPontos(a);
        }
    }

    private void calcPontos(Point2D c) { //calcula os pontos que serão caminho pro robô e adiciona no grafo
        double ap = (-1) / a;
        double bp = c.getX() / a + c.getY();
        Point2D p[] = getIntersecao(c.getX(), c.getY(), ap, bp);
        g.addVertice(p[0]);//adicionando os vértices novos ao grafo
        g.addVertice(p[1]);

    }

    public Point2D[] getIntersecao(double Cx, double Cy, double ap, double bp) { //pontos em que a reta corta a circunferencia

        double x1 = (-(2 * ap * bp - 2 * ap * Cy - 2 * Cx) + Math.sqrt(Math.pow((2 * ap * bp - 2 * ap * Cy - 2 * Cx), 2) - 4 * (ap * ap + 1) * (bp * bp - 2 * Cy * bp + Cx * Cx - raio * raio))) / (2 * (ap * ap + 1));
        double x2 = (-(2 * ap * bp - 2 * ap * Cy - 2 * Cx) - Math.sqrt(Math.pow((2 * ap * bp - 2 * ap * Cy - 2 * Cx), 2) - 4 * (ap * ap + 1) * (bp * bp - 2 * Cy * bp + Cx * Cx - raio * raio))) / (2 * (ap * ap + 1));
//        System.out.println("X': " + x1);
//        System.out.println("X'': " + x2);

        double y1 = ap * x1 + bp;
        double y2 = ap * x2 + bp;

//        System.out.println("Y': " + y1);
//        System.out.println("Y'': " + y2);
        Point2D p[] = {new Point2D(x1, y1), new Point2D(x2, y2)};
        return p;

    }

    public void printaArestas() {
        g.printArestas();
    }

    public static void main(String[] args) {
        Futebol f = new Futebol(new Point2D(3.58, 14.89), new Point2D(18.84, 6.66));
        f.addAdversario(8.4, 13.92);
        f.addAdversario(9.51, 10.89);
        f.addAdversario(12.25, 11.77);
        f.addAdversario(13.8, 9.33);
        f.montaGrafo();
        f.printaArestas();
        //(8.4,13.92), (9.51,10.89), (12.25,11.77), (13.8,9.33)
    }

}
