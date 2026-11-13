import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import java.util.List;

public class Item implements FarmItem {
    private String name;
    private double price;
    private double x;
    private double y;
    private double width;
    private double height;

    public Item(String name, double price, double x, double y, double width, double height) {
        this.name = name;
        this.price = price;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void add(FarmItem item) {
        throw new UnsupportedOperationException("Cannot add to a leaf item.");
    }

    @Override
    public void remove(FarmItem item) {
        throw new UnsupportedOperationException("Cannot remove from a leaf item.");
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + name + " ($" + price + ")");
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void draw(Pane pane) {
        Rectangle rect = new Rectangle(x, y, width, height);
        rect.setFill(Color.LIGHTGREEN);
        rect.setStroke(Color.DARKGREEN);

        Text text = new Text(x + 4, y + 14, name);

        pane.getChildren().addAll(rect, text);
    }

    @Override
    public void collect(List<FarmItem> out) {
        out.add(this);
    }

    @Override
    public String toString() {
        return name; // Required for ComboBox display in Task 3
    }
}