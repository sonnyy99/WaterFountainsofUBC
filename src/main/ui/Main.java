package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application implements EventHandler<ActionEvent> {

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
        Label addLabel = new Label("Enter [1] to add a water fountain");
        Label removeLabel = new Label("Enter [2] to remove a water fountain");
        Label printAllLabel = new Label("Enter [3] to display all water fountains");
        Label printBuildingLabel = new Label("Enter [4] to display all fountains in a building");

        TextField inputString = new TextField();

        Button enterButton = new Button("Click here to enter your choice");
        enterButton.setOnAction(e -> isValid(inputString.getText()));

        VBox layout = new VBox(20);
        layout.getChildren().add(mainMenu);
        layout.getChildren().add(addLabel);
        layout.getChildren().add(removeLabel);
        layout.getChildren().add(printAllLabel);
        layout.getChildren().add(printBuildingLabel);
        layout.getChildren().add(inputString);
        layout.getChildren().add(enterButton);

        Scene scene = new Scene(layout, 500, 300);
        mainStage.setScene(scene);
        mainStage.show();

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        //if (actionEvent.getSource() == addButton) {
           // System.out.println("DAB!");
        //}
    }

    private boolean isValid(String text) {
        try {
            int choice = Integer.parseInt(text);
            if (choice < 0 || choice > 4) {
                throw new NumberFormatException();
            }
            System.out.println(choice);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter an integer between 1 and 4");
            return false;
        }
    }
}
