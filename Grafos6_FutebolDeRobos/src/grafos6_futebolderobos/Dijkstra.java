package grafos6_futebolderobos;

import grafos6_futebolderobos.Grafo.*;
import javafx.geometry.Point2D;

class ColunaCaminho {

    final public Vertice ID;//vertice atual
    public Vertice og;//origem
    public double c;//custo
    public boolean e; //já foi explorado

    public ColunaCaminho(Vertice ID, double c) {
        this.ID = ID;
        this.og = null;
        this.c = c;
        e = false;
    }
}

public class Dijkstra {

    ColunaCaminho[] lista; //indices dos vértices
    double[][] mp; //matriz com os pesos
    Grafo g;
    int quant;

    //classe que encontra o menor caminho
    public Dijkstra(Grafo g) {
        this.g = g;
        quant = g.quantV();
        lista = new ColunaCaminho[g.quantV()];
        mp = new double[g.quantV()][g.quantV()];
        int t = 0;
        for (Vertice v : g.getV()) {
            lista[t++] = new ColunaCaminho(v, -1);
        }
        for (int i = 0; i < g.quantV(); i++) {
//            System.out.print("[ ");
            for (int j = 0; j < g.quantV(); j++) {
                mp[i][j] = g.getPesoAresta(lista[i].ID, lista[j].ID);
//                System.out.print(" \t" + mp[i][j]);
            }
//            System.out.print("]\n");
        }
        printMatriz();
        printLista();
    }

    public void menorCaminho(Vertice o, Vertice dest) {
        int i = getIndice(o);
        int j = getIndice(dest);
        if (i >= 0 && j >= 0) {
            menorCaminho(i, j);
        } else {
            System.out.println("Vertices não fazem parte do grafo");
        }
    }

    public int getIndice(Vertice v) {
        for (int i = 0; i < lista.length; i++) {
            if (lista[i].ID.isEqual(v)) {
                return i;
            }
        }
        return -1;
    }

    public void menorCaminho(int o, int dest) {
        lista[o].c = 0;
        expandir(o);
        while (hasNaoExpandido(dest)) {
            int ind = menorCustoNaoExp();
            if (ind >= 0 && ind != dest) {
                expandir(ind);
            }

        }
        if (lista[dest].og == null) {
            System.out.println("Nao existe caminho");
        } else {
            backTrack(dest);
        }

    }

    public void backTrack(int i) {
        if (lista[i].og == null) {
            System.out.println(lista[i].ID.toString());
            return;
        }
        System.out.print(lista[i].ID.toString()+"<-");
        backTrack(getIndice(lista[i].og));
    }

    public int menorCustoNaoExp() {//menor custo ainda nao expandido
        double menor = 999999999;
        for(ColunaCaminho c: lista){
            if(!(c.e)){
                if(c.c > 0)
                    menor = c.c;
            }
        }
        int aux = -1;
        for (int i = 0; i < lista.length; i++) {
            if (!(lista[i].e)) {
                if (menor > lista[i].c) {
                    menor = lista[i].c;
                    aux = i;
                }
            }
        }
        return aux;
    }

    public void expandir(int posV) {
//        int posV; //posição do nó que está sendo expendido
//        for (posV = 0; posV < lista.length; posV++) {
//            if (lista[posV].ID.isEqual(v)) {
//                break;
//            }
//        }

        System.out.println("EXPANDINDO "+ lista[posV].ID.toString());

        if (posV < lista.length && posV >= 0) {
            lista[posV].e = true; //marcando como expandido
            for (int i = 0; i < lista.length; i++) { //procurando outros vertices
                if (!lista[i].e) { //que ainda nao foram expandidos
                    double custo = mp[posV][i];
                    if (custo > 0 && (custo < lista[i].c || lista[i].c == -1)) {
                        lista[i].c = custo + lista[posV].c;
                        lista[i].og = lista[posV].ID;
                        System.out.println("vertice: "+lista[i].ID.toString()+", custo: "+lista[i].c);
                    }
                }
            }
        } else {
            System.out.println("Vertice não pertence a lista");
        }
        System.out.println("fim da expansão\n\n");
    }

    public boolean hasNaoExpandido(int dest) {
        for (ColunaCaminho c : lista) {
            if (lista[dest] != c) {
                if (!c.e) {
                    return true;
                }
            }
        }
        return false;
    }

    private void printMatriz() {
        for (int i = 0; i < g.quantV(); i++) {
            System.out.print("[ ");
            for (int j = 0; j < g.quantV(); j++) {
                System.out.print(" \t" + mp[i][j]);
            }
            System.out.print("]\n");
        }
    }

    private void printLista() {
        for (ColunaCaminho c : lista) {
            if (c.og != null) {
                if (c.e) {
                    System.out.println("ID: " + c.ID.getID() + ", origem: " + c.og.getID() + ", peso: " + c.c + ", expandido: true");
                } else {
                    System.out.println("ID: " + c.ID.getID() + ", origem: " + c.og.getID() + ", peso: " + c.c + ", expandido: false");
                }
            } else {
                System.out.println("ID: " + c.ID.getID() + ", origem: null, peso: " + c.c + ", expandido: false");
            }
        }
    }

    public static void main(String[] args) {
        Grafo g = new Grafo();
        g.addVertice(new Point2D(0, 0));
        g.addVertice(new Point2D(1, 1));
        g.addVertice(new Point2D(2, 2));
        g.addVertice(new Point2D(3, 3));
        g.addVertice(new Point2D(4, 4));
        g.addAresta(g.getV().get(0), g.getV().get(1), 5);
        g.addAresta(g.getV().get(0), g.getV().get(2), 1);
        g.addAresta(g.getV().get(0), g.getV().get(3), 22);
        g.addAresta(g.getV().get(1), g.getV().get(3), 6);
        g.addAresta(g.getV().get(2), g.getV().get(3), 20);
        g.addAresta(g.getV().get(3), g.getV().get(4), 2);
        Dijkstra d = new Dijkstra(g);
        d.menorCaminho(g.getV().get(0),g.getV().get(3));
    }
}
