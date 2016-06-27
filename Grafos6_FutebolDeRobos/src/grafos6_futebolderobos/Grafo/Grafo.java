package grafos6_futebolderobos.Grafo;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

public class Grafo {

    private List<Vertice> V;//conjunto de vértices
    private List<Aresta> E;//conjunto de duplas de vértices (arestas)

    public Grafo() {
        V = new ArrayList<>();
        E = new ArrayList<>();
    }

    public List<Aresta> getE() {
        return E;
    }

    public List<Vertice> getV() {
        return V;
    }

    public void addAresta(Vertice v1, Vertice v2, double peso) {
        boolean naoTem = true;
        for (Aresta x : E) { //procura uma aresta com esses vértices
            if (x.hasThisEdges(v1, v2)) {
                naoTem = false;
                break;
            }
        }
        if (naoTem) { //adiciona aresta na lista
            Aresta e = new Aresta(v1, v2, peso);
            E.add(e);
        }
    }

    public void addVertice( Point2D v) {
        boolean naoTem = true;
        for (Vertice x : V) { //procura um vertice com as mesmas coordenadas
            if (x.equals(v)) {
                naoTem = false;
                break;
            }
        }
        if (naoTem) { //adiciona aresta na lista
            V.add(new Vertice(v));
        }
    }

    public void printArestas() {
        for(Aresta a: E){
            System.out.println(a.toString());
        }
    }

}
