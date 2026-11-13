import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.animation.SequentialTransition;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class WindowView extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Farm View");

        BorderPane borderPane = new BorderPane();
        Pane farmPane = new Pane();
        farmPane.setPrefSize(800, 600);

        // Build the farm tree from the table specification
        ItemContainer farm = new ItemContainer("Farm", 0, 0, 0, 800, 600);
        
        ItemContainer barn = new ItemContainer("Barn", 20000, 20, 40, 360, 300);
        farm.addChild(barn);

        ItemContainer livestockArea = new ItemContainer("Livestock Area", 3000, 40, 70, 200, 140);
        barn.addChild(livestockArea);

        Item cow = new Item("Cow", 1500, 70, 120, 50, 30);
        livestockArea.addChild(cow);

        Item milkStorage = new Item("Milk Storage", 4000, 260, 70, 100, 60);
        barn.addChild(milkStorage);

        ItemContainer storageBuilding = new ItemContainer("Storage Building", 8000, 420, 40, 360, 160);
        farm.addChild(storageBuilding);

        Item tractor = new Item("Tractor", 45000, 450, 90, 100, 50);
        storageBuilding.addChild(tractor);

        Item tiller = new Item("Tiller", 2500, 600, 90, 80, 50);
        storageBuilding.addChild(tiller);

        ItemContainer commandCenter = new ItemContainer("Command Center", 10000, 20, 380, 260, 180);
        farm.addChild(commandCenter);

        Item droneItem = new Item("Drone", 1200, 120, 440, 60, 60);
        commandCenter.addChild(droneItem);

        Item soyCrop = new Item("Soy Crop", 6000, 420, 240, 360, 320);
        farm.addChild(soyCrop);

        // Draw the farm once on the root pane
        farm.draw(farmPane);

        // Create the drone circle centered on the Drone item and add it after drawing the farm
        double droneStartX = droneItem.getX() + droneItem.getWidth() / 2;
        double droneStartY = droneItem.getY() + droneItem.getHeight() / 2;
        Circle drone = new Circle(droneStartX, droneStartY, 12, Color.DODGERBLUE);
        farmPane.getChildren().add(drone);

        borderPane.setCenter(farmPane);

        // Collect items for the drop-down list
        List<FarmItem> farmItems = new ArrayList<>();
        farm.collect(farmItems);
        System.out.println("Items and containers on the farm: " + farmItems.size());

        ComboBox<FarmItem> items = new ComboBox<>();
        items.getItems().addAll(farmItems);
        if (!farmItems.isEmpty()) {
            items.getSelectionModel().selectFirst();
        }

        Button visitButton = new Button("Visit Item/Container");
        Button scanButton = new Button("Scan Farm");

        // Wire up the Visit button with TranslateTransition and SequentialTransition
        visitButton.setOnAction(e -> {
            FarmItem selected = items.getSelectionModel().getSelectedItem();
            if (selected != null) {
                double targetX = selected.getX() + selected.getWidth() / 2;
                double targetY = selected.getY() + selected.getHeight() / 2;

                double toX = targetX - droneStartX;
                double toY = targetY - droneStartY;

                TranslateTransition outTransition = new TranslateTransition(Duration.seconds(0.8), drone);
                outTransition.setToX(toX);
                outTransition.setToY(toY);
                outTransition.setOnFinished(ev -> System.out.println("Drone reached: " + selected));

                TranslateTransition homeTransition = new TranslateTransition(Duration.seconds(0.8), drone);
                homeTransition.setToX(0);
                homeTransition.setToY(0);
                homeTransition.setOnFinished(ev -> System.out.println("Drone back at Command Center"));

                SequentialTransition seq = new SequentialTransition(outTransition, homeTransition);
                seq.play();
            }
        });

        HBox controls = new HBox(10, items, visitButton, scanButton);
        controls.setPadding(new Insets(10));
        borderPane.setBottom(controls);

        Scene scene = new Scene(borderPane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}