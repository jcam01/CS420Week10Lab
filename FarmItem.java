import javafx.scene.layout.Pane;
import java.util.List;

public interface FarmItem {

    void add(FarmItem item);
    void remove(FarmItem item);

    double getPrice();
    String getName();
    void display(String indent);

    double getX();
    double getY();
    double getWidth();
    double getHeight();

    void draw(Pane pane);
    void collect(List<FarmItem> out);
}