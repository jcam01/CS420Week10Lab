import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class WindowView extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Farm View");

        // Keep the map at the assignment's required size.
        Pane farmPane = new Pane();
        farmPane.setPrefSize(800, 600);
        farmPane.setMinSize(800, 600);
        farmPane.setMaxSize(800, 600);

        // Use Member 2's builder instead of rebuilding the farm here.
        FarmItem farm = FarmBuilder.buildFarm();

        // The application's one draw call, on the root.
        farm.draw(farmPane);

        // Build the dropdown list from the tree.
        List<FarmItem> farmItems = new ArrayList<>();
        farm.collect(farmItems);

        System.out.println(
                "Items and containers on the farm: " + farmItems.size()
        );

        // Find the Drone item through the interface.
        FarmItem droneItem = null;

        for (FarmItem item : farmItems) {
            if ("Drone".equals(item.getName())) {
                droneItem = item;
                break;
            }
        }

        if (droneItem == null) {
            throw new IllegalStateException(
                    "The farm must contain an item named Drone."
            );
        }

        final double droneStartX =
                droneItem.getX() + droneItem.getWidth() / 2.0;

        final double droneStartY =
                droneItem.getY() + droneItem.getHeight() / 2.0;

        // Add the circle after drawing so it appears above the farm.
        Circle drone = new Circle(
                droneStartX,
                droneStartY,
                12,
                Color.DODGERBLUE
        );

        farmPane.getChildren().add(drone);

        ComboBox<FarmItem> items = new ComboBox<>();
        items.getItems().addAll(farmItems);
        items.setPrefWidth(220);
        items.getSelectionModel().selectFirst();

        Button visitButton = new Button("Visit Item/Container");
        Button scanButton = new Button("Scan Farm");

        // Visit the selected object, then return home.
        visitButton.setOnAction(event -> {
            FarmItem selected =
                    items.getSelectionModel().getSelectedItem();

            if (selected == null) {
                return;
            }

            SequentialTransition route = new SequentialTransition();

            route.getChildren().add(
                    createFlight(
                            drone, selected, droneStartX, droneStartY
                    )
            );

            route.getChildren().add(createHomeFlight(drone));

            playRoute(route, items, visitButton, scanButton);
        });

        // Collect a scan route from the tree, skipping the root.
        scanButton.setOnAction(event -> {
            List<FarmItem> scanItems = new ArrayList<>();
            farm.collect(scanItems);

            SequentialTransition route = new SequentialTransition();

            for (FarmItem target : scanItems) {
                if (target == farm) {
                    continue;
                }

                route.getChildren().add(
                        createFlight(
                                drone, target, droneStartX, droneStartY
                        )
                );
            }

            route.getChildren().add(createHomeFlight(drone));

            playRoute(route, items, visitButton, scanButton);
        });

        HBox controls = new HBox(
                10, items, visitButton, scanButton
        );
        controls.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(farmPane);
        root.setBottom(controls);

        // Let the scene fit both the full map and the controls.
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

    private TranslateTransition createFlight(
            Circle drone,
            FarmItem target,
            double droneStartX,
            double droneStartY) {

        double targetCenterX =
                target.getX() + target.getWidth() / 2.0;

        double targetCenterY =
                target.getY() + target.getHeight() / 2.0;

        TranslateTransition flight = new TranslateTransition(
                Duration.seconds(0.8), drone
        );

        flight.setToX(targetCenterX - droneStartX);
        flight.setToY(targetCenterY - droneStartY);

        flight.setOnFinished(event ->
                System.out.println(
                        "Drone reached: " + target.getName()
                )
        );

        return flight;
    }

    private TranslateTransition createHomeFlight(Circle drone) {
        TranslateTransition home = new TranslateTransition(
                Duration.seconds(0.8), drone
        );

        home.setToX(0);
        home.setToY(0);

        home.setOnFinished(event ->
                System.out.println("Drone back at Command Center")
        );

        return home;
    }

    private void playRoute(
            SequentialTransition route,
            ComboBox<FarmItem> items,
            Button visitButton,
            Button scanButton) {

        // Prevent overlapping flights.
        items.setDisable(true);
        visitButton.setDisable(true);
        scanButton.setDisable(true);

        route.setOnFinished(event -> {
            items.setDisable(false);
            visitButton.setDisable(false);
            scanButton.setDisable(false);
        });

        route.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}