public final class FarmBuilder {

    private FarmBuilder() {
        // Utility class: no instances needed.
    }

    public static FarmItem buildFarm() {

        // Create the objects in the assignment's table order.
        FarmItem farm = new ItemContainer(
                "Farm", 0.0, 0, 0, 800, 600);

        FarmItem barn = new ItemContainer(
                "Barn", 20000.0, 20, 40, 360, 300);

        FarmItem livestockArea = new ItemContainer(
                "Livestock Area", 3000.0, 40, 70, 200, 140);

        FarmItem cow = new Item(
                "Cow", 1500.0, 70, 120, 50, 30);

        FarmItem milkStorage = new Item(
                "Milk Storage", 4000.0, 260, 70, 100, 60);

        FarmItem storageBuilding = new ItemContainer(
                "Storage Building", 8000.0, 420, 40, 360, 160);

        FarmItem tractor = new Item(
                "Tractor", 45000.0, 450, 90, 100, 50);

        FarmItem tiller = new Item(
                "Tiller", 2500.0, 600, 90, 80, 50);

        FarmItem commandCenter = new ItemContainer(
                "Command Center", 10000.0, 20, 380, 260, 180);

        FarmItem drone = new Item(
                "Drone", 1200.0, 120, 440, 60, 60);

        FarmItem soyCrop = new Item(
                "Soy Crop", 6000.0, 420, 240, 360, 320);

        // Add objects to their parents in the required order.
        farm.add(barn);
        barn.add(livestockArea);
        livestockArea.add(cow);
        barn.add(milkStorage);

        farm.add(storageBuilding);
        storageBuilding.add(tractor);
        storageBuilding.add(tiller);

        farm.add(commandCenter);
        commandCenter.add(drone);

        farm.add(soyCrop);

        return farm;
    }
}