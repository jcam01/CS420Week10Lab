import java.util.ArrayList;
import java.util.List;

class StorageBuilding implements FarmItem {
    private final String name;
    private final double price;
    private List<FarmItem> contents;

    public StorageBuilding(String name, double price) {
        this.name = name;
        this.price = price;
        this.contents = new ArrayList<>();
    }

    @Override
    public void add(FarmItem item) {
        contents.add(item);
    }

    @Override
    public void remove(FarmItem item) {
        contents.remove(item);
    }

    @Override
    public double getPrice() {
        double total = price;

        for (FarmItem item : contents) {
            total += item.getPrice();
        }

        return total;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + name + " (total $" + getPrice() + ")");

        for (FarmItem item : contents) {
            item.display(indent + "    ");
        }
    }
}
