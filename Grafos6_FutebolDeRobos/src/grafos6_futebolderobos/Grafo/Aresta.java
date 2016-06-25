package grafos6_futebolderobos.Grafo;

public class Aresta {

    private Vertice v1, v2;
    private double peso;

    public Aresta(Vertice v1, Vertice v2, double peso) {
        if (v1.compareTo(v2) == 0) {
            throw new IllegalArgumentException("Os vértices não podem ser iguais!");
        }
        if (v1.compareTo(v2) == -1) {
            this.v1 = v1;
            this.v2 = v2;
        } else {
            this.v1 = v2;
            this.v2 = v1;
        }
        this.peso = peso;
    }

    public Vertice getV1() {
        return v1;
    }

    public Vertice getV2() {
        return v2;
    }

    public double getPeso() {
        return peso;
    }

    public boolean hasThisEdges(Vertice v1, Vertice v2) {
        if (this.v1.equals(v1) && this.v2.equals(v2) || this.v1.equals(v2) && this.v2.equals(v1)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + v1 + ";" + v2 + ")";
    }

}
