import java.util.ArrayList;
import java.util.List;

public class FarmInventory {

    public static void main(String[] args) {

        FarmItem farm = FarmBuilder.buildFarm();

        List<FarmItem> allItems = new ArrayList<>();
        farm.collect(allItems);

        System.out.println(
                "Items and containers on the farm: "
                        + allItems.size()
        );

        System.out.println();
        System.out.println("Tree order:");

        for (FarmItem item : allItems) {
            System.out.println(item.getName());
        }

        System.out.println();
        System.out.printf(
                "Total farm value: $%,.2f%n",
                farm.getPrice()
        );
    }
}