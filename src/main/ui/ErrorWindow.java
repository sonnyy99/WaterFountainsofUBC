package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorWindow {

    public static void display(String title, String errorMessage) {
        Stage window = new Stage();

        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label(errorMessage);

        Button exitButton = new Button("Click here to close");
        exitButton.setOnAction(e -> window.close());

        VBox layout = new VBox(20);
        layout.getChildren().add(label);
        layout.getChildren().add(exitButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
