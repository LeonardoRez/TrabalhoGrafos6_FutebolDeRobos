package grafos6_futebolderobos.Grafo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

    private List<Integer> V;//conjunto de vértices
    private List<Integer[]> E;//conjunto de duplas de vértices (arestas)

    public Grafo() {
        V = new ArrayList<>();
        E = new ArrayList<>();
    }

    public List<Integer[]> getE() {
        return E;
    }

    public void setE(List<Integer[]> E) {
        this.E = E;
    }

    public List<Integer> getV() {
        return V;
    }

    public void setV(List<Integer> V) {
        this.V = V;
    }

    public void addAresta(int v1, int v2) {
        boolean naoTem = true;
        for (Integer[] x : E) { //procura uma aresta com esses vértices
            if ((v1 == x[0] && v2 == x[1]) || (v1 == x[1] && v2 == x[1])) {
                naoTem = false;
                break;
            }
        }
        if (naoTem) { //adiciona aresta na lista
            Integer[] e = {v1, v2};
            E.add(e);
        }
    }

    public void addVertice(int v) {
    }
    public void printAresta(){}

}
