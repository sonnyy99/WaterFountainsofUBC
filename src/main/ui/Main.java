package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    private Button enterButton;

    public static void main(String[] args) throws IOException {
        FountainLocations fl = new FountainLocations();
        fl.run();
        ReadWebPage.read();
        launch(args);
        // fl.saveFountain(fl.fileFountains);
        // fl.saveBuilding(fl.fileBuildings);
    }

    // Followed this tutorial series https://www.youtube.com/watch?v=S_JN7zO12H4&t=287s
    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("Water Fountains of UBC");

        Label mainMenu = new Label("MAIN MENU");
        Label addLabel = new Label("Enter [1] to add a water fountain");
        Label removeLabel = new Label("Enter [2] to remove a water fountain");
        Label printAllLabel = new Label("Enter [3] to display all water fountains");
        Label printBuildingLabel = new Label("Enter [4] to display all fountains in a building");

        TextField inputString = new TextField();

        enterButton = new Button("Click here to enter your choice");
        enterButton.setOnAction(e -> isValid(inputString.getText()));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(mainMenu, addLabel, removeLabel, printAllLabel,
                printBuildingLabel, inputString, enterButton);

        Scene scene = new Scene(layout, 500, 300);
        mainStage.setScene(scene);
        mainStage.show();

    }

    private Boolean isValid(String text) {
        try {
            int choice = Integer.parseInt(text);
            if (choice < 0 || choice > 4) {
                throw new NumberFormatException();
            }
            chooseOptions(choice);

            return true;
        } catch (NumberFormatException e) {
            PopupWindow.display("ERROR", "You must enter an integer between 1 and 4");
            return false;
        }
    }

    private void chooseOptions(int choice) {
        if (choice == 1) {
            AddFountainWindow.display();
        }
        if (choice == 2) {
            RemoveFountainWindow.display();
        }
        if (choice == 3) {
            ShowAllFountainsWindow.display();
        }
        if (choice == 4) {
            ShowBuildingFountainsWindow.display();
        }
    }

}
