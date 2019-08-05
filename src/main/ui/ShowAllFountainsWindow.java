package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Fountain;

import static model.ListOfFountain.allFountains;

class ShowAllFountainsWindow {

    private static Stage window;
    private static TableView<Fountain> table;

    public static void display() {
        setupStage();

        Button enterButton = new Button("Click here to close the window");
        enterButton.setOnAction(e -> window.close());

        TableColumn<Fountain, String> floorColumn = new TableColumn<>("Floor");
        TableColumn<Fountain, String> buildingColumn = new TableColumn<>("Building Name");
        TableColumn<Fountain, String> typeColumn = new TableColumn<>("Type");
        TableColumn<Fountain, String> descriptionColumn = new TableColumn<>("Description");

        setColumnValues(floorColumn, buildingColumn, typeColumn, descriptionColumn);

        table = new TableView<>();
        table.setItems(getFountains());
        table.getColumns().addAll(floorColumn, buildingColumn, typeColumn, descriptionColumn);

        VBox layout = new VBox();
        layout.getChildren().addAll(table);
        finalizeScene(layout);
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

    private static void finalizeScene(VBox layout) {
        Scene scene = new Scene(layout, 600, 300);
        window.setScene(scene);
        window.show();
    }


    private static void setupStage() {
        window = new Stage();
        window.setTitle("Displaying All Water Fountains");
        window.initModality(Modality.NONE);
    }

    private static ObservableList<Fountain> getFountains() {
        ObservableList<Fountain> fountains = FXCollections.observableArrayList();
        fountains.addAll(allFountains);
        return fountains;
    }
}
