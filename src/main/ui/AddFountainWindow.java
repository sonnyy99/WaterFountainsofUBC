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

class AddFountainWindow {
    private static Stage window;

    public static void display() {
        setupStage();
        GridPane grid = setupGrid();

        Label floor = new Label("Floor: ");
        Label building = new Label("Building: ");
        Label type = new Label("Type (Electronic/Mechanical): ");
        Label description = new Label("Location Description: ");

        TextField floorInput = new TextField();
        TextField buildingInput = new TextField();
        TextField typeInput = new TextField();
        TextField descriptionInput = new TextField();

        Button enterButton = new Button("Click here to enter the data");

        placeOnGrid(floor, building, type, description, floorInput, buildingInput,
                typeInput, descriptionInput, enterButton);

        enterButton.setOnAction(e -> addFountain(floorInput.getText(), buildingInput.getText(),
                typeInput.getText(), descriptionInput.getText()));

        grid.getChildren().addAll(floor, floorInput, building, buildingInput,
                type, typeInput, description, descriptionInput, enterButton);

        finalizeScene(grid);
    }

    private static void finalizeScene(GridPane grid) {
        Scene scene = new Scene(grid, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void placeOnGrid(Label floor, Label building, Label type, Label description,
                                    TextField floorInput, TextField buildingInput, TextField typeInput,
                                    TextField descriptionInput, Button enterButton) {
        GridPane.setConstraints(floor, 0, 0);
        GridPane.setConstraints(floorInput, 1, 0);
        GridPane.setConstraints(building, 0, 1);
        GridPane.setConstraints(buildingInput, 1, 1);
        GridPane.setConstraints(type, 0, 2);
        GridPane.setConstraints(typeInput, 1, 2);
        GridPane.setConstraints(description, 0, 3);
        GridPane.setConstraints(descriptionInput, 1, 3);
        GridPane.setConstraints(enterButton, 0, 4);
    }

    private static void setupStage() {
        window = new Stage();
        window.setTitle("Adding a Water Fountain");
        window.initModality(Modality.APPLICATION_MODAL);
    }

    private static GridPane setupGrid() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        return grid;
    }

    private static void addFountain(String floor, String building, String type, String description) {
        try {
            int floorInt = Integer.parseInt(floor);
            if (floorInt <= 0) {
                throw new NumberFormatException();
            } else if (!type.equals("Mechanical") && !type.equals("Electronic")) {
                throw new FountainTypeException();
            } else {
                Fountain f = new Fountain(floorInt, building, type, description);
                addIfNotExists(building, f);
            }
        } catch (NumberFormatException e) {
            PopupWindow.display("ERROR", "Ensure all fields are filled correctly");
        } catch (FountainTypeException e) {
            PopupWindow.display("ERROR", "Make sure the fountain type is correct");
        }

    }

    private static void addIfNotExists(String building, Fountain f) {
        if (f.fountainExists(f)) {
            PopupWindow.display("ERROR", "That fountain already exists!");
        } else {
            setBuilding(building, f);
            allFountains.add(f);
            PopupWindow.display("Success!", "Fountain added; returning to the main menu");
            window.close();
        }
    }
}
