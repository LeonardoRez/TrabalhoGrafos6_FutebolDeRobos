package grafos6_futebolderobos;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

public class Futebol {

    private Point2D robo; //coordenadas do robô
    private List<Point2D> adversarios; //lista com coordenadas dos outros robos

    public Futebol() {
        robo = new Point2D(10.5, 7.2);
        adversarios = new ArrayList<>();
    }
    
}
