package grafos6_futebolderobos.Grafo;

public class Aresta {

    private int v1, v2, peso;

    public Aresta(int v1, int v2, int peso) {
        if (v1 < v2) {
            this.v1 = v1;
            this.v2 = v2;
        } else {
            this.v1 = v2;
            this.v2 = v1;
        }
        this.peso = peso;
    }

    public int getV2() {
        return v2;
    }

    public int getV1() {
        return v1;
    }
    public boolean compareTo(int v1, int v2){
        if((this.v1==v1 && this.v2==v2) || (this.v1==v2 && this.v2==v1))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "(" + v1 + "," + v2 + ")";
    }

}
