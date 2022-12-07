package com.image.processing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

public class ImageProcessingApplication extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ImageProcessingApplication.class.getResource("interface.fxml"));
        VBox vBox = fxmlLoader.load();
        ProcessController processController = fxmlLoader.getController();
        processController.setStage(stage);
        Image image = new Image(String.valueOf(ImageProcessingApplication.class.getResource("icon.jpg")));
        stage.getIcons().add(image);
        stage.setTitle("Photoshop");
        Scene scene = new Scene(vBox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}