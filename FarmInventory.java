package application;
import java.util.ArrayList;
import java.util.List;

public class FarmInventory {

    public static void main(String[] args) {

        // Part A: Create the farm and its buildings.
        FarmItem farm = new Barn("Farm", 0.0);
        FarmItem redBarn = new Barn("Red Barn", 20000.0);
        FarmItem milkingRoom = new Barn("Milking Room", 5000.0);
        FarmItem storageBuilding =
                new StorageBuilding("Storage Building", 8000.0);

        // Create the individual items.
        FarmItem bessie = new Cow("Bessie", 1500.0);
        FarmItem daisy = new Cow("Daisy", 1200.0);
        FarmItem clover = new Cow("Clover", 1300.0);
        FarmItem tractor = new Tractor("John Deere 5075E", 45000.0);

        // Put the cows inside the Milking Room.
        milkingRoom.add(bessie);
        milkingRoom.add(daisy);

        // Order matters: Milking Room comes before Clover.
        redBarn.add(milkingRoom);
        redBarn.add(clover);

        // Put the tractor inside the Storage Building.
        storageBuilding.add(tractor);

        // Order matters: Red Barn comes before Storage Building.
        farm.add(redBarn);
        farm.add(storageBuilding);

        // Display the original farm and its computed total.
        farm.display("");
        System.out.println("Total farm value: $" + farm.getPrice());

        // Part B: Sell Daisy by removing her from the Milking Room.
        milkingRoom.remove(daisy);

        System.out.println("After selling Daisy:");
        System.out.println("Total farm value: $" + farm.getPrice());
    }
} // FarmInventory ends

interface FarmItem {
	void add(FarmItem item); // Insert item from the object.
	void remove(FarmItem item); // Remove item from the object.
	double getPrice(); // Return the object's total value.
	String getName(); // Return the object's name.
	void display(String indent); // Print the object, anything inside it, as an indented list.
} // add and remove belong here because of the fact that containers can implement these methods, although individual items do not need to themselves.
// FarmItem ends

class Cow implements FarmItem {
    private final double price;
    private final String name;
    public Cow(String name, double price) {
        this.name = name;
        this.price = price;
    }
    @Override
    public void display(String indent) {
        System.out.println(indent + name + " $" + price);
    }
    @Override
    public void add(FarmItem item) {
        throw new UnsupportedOperationException(
                "Cannot add an item to a cow.");
    }

    @Override
    public void remove(FarmItem item) {
        throw new UnsupportedOperationException(
                "Cannot remove an item from a cow.");
    }
    @Override
    public double getPrice() {
        return price;
    }
    @Override
    public String getName() {
        return name;
    }
} //Cow ends

class Tractor implements FarmItem {
    private final double price;
    private final String name;
    public Tractor(String name, double price) {
        this.name = name;
        this.price = price;
    }
    @Override
    public void display(String indent) {
        System.out.println(indent + name + " $" + price);
    }
    @Override
    public void add(FarmItem item) {
        throw new UnsupportedOperationException(
                "Cannot add an item to a tractor.");
    }
    @Override
    public void remove(FarmItem item) {
        throw new UnsupportedOperationException(
                "Cannot remove an item from a tractor.");
    }
    @Override
    public double getPrice() {
        return price;
    }
    @Override
    public String getName() {
        return name;
    }
} // Tractor ends

class Barn implements FarmItem {

    private final String name;
    private final double price;
    private final List<FarmItem> contents;

    public Barn(String name, double price) {
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
        System.out.println(
                indent + name + " (total $" + getPrice() + ")");

        for (FarmItem item : contents) {
            item.display(indent + "    ");
        }
    }
} // Barn ends

class StorageBuilding implements FarmItem {

    private final String name;
    private final double price;
    private final List<FarmItem> contents;

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
} //StorageBuilding ends