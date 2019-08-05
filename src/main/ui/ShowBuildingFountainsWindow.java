package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Building;
import model.Fountain;
import model.ListOfBuilding;

public class ShowBuildingFountainsWindow {
    static Stage window;
    static TableView<Fountain> table;

    public static void display() {
        setupStage();

        Label buildingLabel = new Label("Building Name: ");
        TextField buildingText = new TextField();

        Button enterButton = new Button("Click here to enter the data");
        enterButton.setOnAction(e -> enterBuilding(buildingText.getText()));
        
        TableColumn<Fountain, String> floorColumn = new TableColumn<>("Floor");
        TableColumn<Fountain, String> buildingColumn = new TableColumn<>("Building Name");
        TableColumn<Fountain, String> typeColumn = new TableColumn<>("Type");
        TableColumn<Fountain, String> descriptionColumn = new TableColumn<>("Description");

        setColumnValues(floorColumn, buildingColumn, typeColumn, descriptionColumn);

        table = new TableView<>();
        table.getColumns().addAll(floorColumn, buildingColumn, typeColumn, descriptionColumn);

        GridPane layout = new GridPane();
        GridPane.setConstraints(buildingLabel, 0, 0);
        GridPane.setConstraints(buildingText, 0, 1);
        GridPane.setConstraints(enterButton, 0, 2);
        GridPane.setConstraints(table, 0, 3);

        layout.getChildren().addAll(buildingLabel, buildingText, enterButton, table);
        finalizeScene(layout);
    }

    private static void enterBuilding(String buildingName) {
        try {
            Building b = ListOfBuilding.getBuilding(buildingName);
            table.setItems(getFountains(b));
        } catch (NullPointerException e) {
            PopupWindow.display("ERROR", "That building does not have any fountains");
        }
    }

    private static void setColumnValues(TableColumn<Fountain, String> floorColumn,
                                        TableColumn<Fountain, String> buildingColumn,
                                        TableColumn<Fountain, String> typeColumn,
                                        TableColumn<Fountain, String> descriptionColumn) {
        floorColumn.setMinWidth(150);
        floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
        buildingColumn.setMinWidth(150);
        buildingColumn.setCellValueFactory(new PropertyValueFactory<>("buildingName"));
        typeColumn.setMinWidth(150);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setMinWidth(150);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    private static void finalizeScene(GridPane layout) {
        Scene scene = new Scene(layout, 600, 400);
        window.setScene(scene);
        window.show();
    }


    private static void setupStage() {
        window = new Stage();
        window.setTitle("Displaying Water Fountains in a Building");
        window.initModality(Modality.NONE);
    }

    public static ObservableList<Fountain> getFountains(Building b) {
        ObservableList<Fountain> fountains = FXCollections.observableArrayList();
        fountains.addAll(b.getFountains());
        return fountains;
    }
}
