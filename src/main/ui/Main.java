package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application implements EventHandler<ActionEvent> {

    private Button addButton;
    private Button removeButton;
    private Button printAllButton;
    private Button printBuildingButton;

    public static void main(String[] args) throws IOException {
        launch(args);
        FountainLocations fl = new FountainLocations();
        ReadWebPage.read();
        fl.run();
    }

    // Followed this tutorial https://www.youtube.com/watch?v=S_JN7zO12H4&t=287s
    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("Water Fountains of UBC");

        Label mainMenu = new Label("Main Menu");

        addButton = new Button("Click to add a water fountain");
        removeButton = new Button("Click to remove a water fountain");
        printAllButton = new Button("Click to display all water fountains");
        printBuildingButton = new Button("Click to display all fountains in a building");

        VBox layout = new VBox(30);
        layout.getChildren().add(mainMenu);
        layout.getChildren().add(addButton);
        layout.getChildren().add(removeButton);
        layout.getChildren().add(printAllButton);
        layout.getChildren().add(printBuildingButton);

        Scene scene = new Scene(layout, 500, 300);
        mainStage.setScene(scene);
        mainStage.show();

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == addButton) {
            System.out.println("DAB!");
        }
    }
}
