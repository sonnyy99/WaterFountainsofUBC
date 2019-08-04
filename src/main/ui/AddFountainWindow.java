package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Fountain;
import model.exceptions.FountainTypeException;

import static model.ListOfFountain.allFountains;
import static model.ListOfFountain.setBuilding;

public class AddFountainWindow {
    static Stage window;

    public static void display() {
        window = new Stage();

        window.setTitle("Adding a Water Fountain");
        window.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label floor = new Label("Floor: ");
        GridPane.setConstraints(floor, 0, 0);
        TextField floorInput = new TextField();
        GridPane.setConstraints(floorInput, 1, 0);

        Label building = new Label("Building: ");
        GridPane.setConstraints(building, 0, 1);
        TextField buildingInput = new TextField();
        GridPane.setConstraints(buildingInput, 1, 1);

        Label type = new Label("Type (Electronic/Mechanical): ");
        GridPane.setConstraints(type, 0, 2);
        TextField typeInput = new TextField();
        GridPane.setConstraints(typeInput, 1, 2);

        Label description = new Label("Location Description: ");
        GridPane.setConstraints(description, 0, 3);
        TextField descriptionInput = new TextField();
        GridPane.setConstraints(descriptionInput, 1, 3);

        Button enterButton = new Button("Click here to enter the data");
        GridPane.setConstraints(enterButton, 0, 4);
        enterButton.setOnAction(e -> addFountain(floorInput.getText(), buildingInput.getText(),
                typeInput.getText(), descriptionInput.getText()));


        grid.getChildren().addAll(floor, floorInput, building, buildingInput,
                type, typeInput, description, descriptionInput, enterButton);

        Scene scene = new Scene(grid, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void addFountain(String floor, String building, String type, String description) {
        try {
            int floorInt = Integer.parseInt(floor);
            if (floorInt <= 0) {
                throw new NumberFormatException();
            } else if (!type.equals("Mechanical") && !type.equals("Electronic")) {
                throw new FountainTypeException();
            } else {
                Fountain f = new Fountain(floorInt, building, type, description);
                setBuilding(building, f);
                allFountains.add(f);
                PopupWindow.display("Success!", "Fountain added; returning to the main menu");
                window.close();
            }
        } catch (NumberFormatException e) {
            PopupWindow.display("ERROR", "Ensure all fields are filled correctly");
        } catch (FountainTypeException e) {
            PopupWindow.display("ERROR", "Make sure the fountain type is correct");
        }

    }
}
