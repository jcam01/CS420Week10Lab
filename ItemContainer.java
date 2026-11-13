import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public class ItemContainer implements FarmItem {
    private String name;
    private double price;
    private double x;
    private double y;
    private double width;
    private double height;
    private List<FarmItem> children = new ArrayList<>();

    public ItemContainer(String name, double price, double x, double y, double width, double height) {
        this.name = name;
        this.price = price;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void add(FarmItem item) {
        children.add(item);
    }

    @Override
    public void remove(FarmItem item) {
        children.remove(item);
    }

    @Override
    public double getPrice() {
        double total = price;
        for (FarmItem child : children) {
            total += child.getPrice();
        }
        return total;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + name + " (Container, Base: $" + price + ")");
        for (FarmItem child : children) {
            child.display(indent + "  ");
        }
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
        // Crucial: Container draws itself FIRST so its outline sits behind children
        Rectangle rect = new Rectangle(x, y, width, height);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.SADDLEBROWN);
        rect.setStrokeWidth(2);

        Text text = new Text(x + 6, y + 16, name);

        pane.getChildren().addAll(rect, text);

        // Recursively draw children
        for (FarmItem child : children) {
            child.draw(pane);
        }
    }

    @Override
    public void collect(List<FarmItem> out) {
        out.add(this); // Add container first (tree order rule)
        for (FarmItem child : children) {
            child.collect(out);
        }
    }

    @Override
    public String toString() {
        return name; // Required for ComboBox display in Task 3
    }
}
