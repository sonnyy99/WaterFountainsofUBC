package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) throws IOException {
        FountainLocations fl = new FountainLocations();
        fl.run();
        ReadWebPage.read();
        launch(args);
        fl.saveFountain(fl.fileFountains);
        fl.saveBuilding(fl.fileBuildings);
    }

    // Followed this tutorial series https://www.youtube.com/watch?v=S_JN7zO12H4&t=287s
    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("Water Fountains of UBC");

        Label mainMenu = new Label("MAIN MENU");
        mainMenu.setStyle("-fx-font-size: 24");

        Button addFountainButton = new Button("Add a Water Fountain");
        Button removeFountainButton = new Button("Remove a Water Fountain");
        Button printAllFountainsButton = new Button("Display all Water Fountains");
        Button printBuildingFountainsButton = new Button("Find all Water Fountains in a Building");

        addFountainButton.setOnAction(e -> AddFountainWindow.display());
        removeFountainButton.setOnAction(e -> RemoveFountainWindow.display());
        printAllFountainsButton.setOnAction(e -> ShowAllFountainsWindow.display());
        printBuildingFountainsButton.setOnAction(e -> ShowBuildingFountainsWindow.display());

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(mainMenu, addFountainButton, removeFountainButton,
                printAllFountainsButton, printBuildingFountainsButton);

        Scene scene = new Scene(layout, 300, 300);
        mainStage.setScene(scene);
        mainStage.show();

    }
}

