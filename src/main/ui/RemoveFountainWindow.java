package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Building;
import model.Fountain;

import static model.ListOfFountain.allFountains;

class RemoveFountainWindow {
    private static Stage window;

    public static void display() {
        setupStage();
        GridPane grid = setupGrid();

        Label removePosition = new Label("Remove the fountain at this position: ");
        TextField removePositionInput = new TextField();

        Button enterButton = new Button("Click here to enter the data");

        placeOnGrid(removePosition, removePositionInput, enterButton);

        enterButton.setOnAction(e -> checkInput(removePositionInput.getText()));

        grid.getChildren().addAll(removePosition, removePositionInput, enterButton);

        finalizeScene(grid);
    }

    private static void checkInput(String position) {
        if (isListEmpty()) {
            PopupWindow.display("ERROR", "There are already no water fountains!");
        } else {
            try {
                int index = Integer.parseInt(position) - 1;
                if (index < 0) {
                    throw new NumberFormatException();
                } else {
                    removeFountain(index);
                }
            } catch (NumberFormatException e) {
                PopupWindow.display("ERROR", "Please type in a valid fountain position");
            }
        }
    }

    private static void removeFountain(int index) {
        try {
            Fountain f = allFountains.get(index);
            Building b = f.getBuilding();
            b.getFountains().remove(f);
            allFountains.remove(f);
            PopupWindow.display("Success!", "Fountain removed; returning to the main menu");
            window.close();
        } catch (IndexOutOfBoundsException e) {
            PopupWindow.display("ERROR", "There is not a fountain at this position");
        }
    }

    private static Boolean isListEmpty() {
        return allFountains.size() == 0;
    }

    private static void setupStage() {
        window = new Stage();
        window.setTitle("Removing a Water Fountain");
        window.initModality(Modality.APPLICATION_MODAL);
    }

    private static GridPane setupGrid() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        return grid;
    }

    private static void finalizeScene(GridPane grid) {
        Scene scene = new Scene(grid, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void placeOnGrid(Label removePosition, TextField removePositionInput, Button enterButton) {
        GridPane.setConstraints(removePosition, 0, 0);
        GridPane.setConstraints(removePositionInput, 1, 0);
        GridPane.setConstraints(enterButton, 0, 1);
    }
}