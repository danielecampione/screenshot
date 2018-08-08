package com.campione_tech.demo;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * 
 * 
 * @author D. Campione
 *
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Take a screenshot");
        Button btn = new Button();
        btn.setText("Take a screenshot");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                BufferedImage image;
                try {
                    image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Save Resource File");
                    fileChooser.setInitialFileName("screenshot.png");

                    String userHome = System.getProperty("user.home");
                    File initialDirectory = new File(userHome);
                    if (initialDirectory.exists()) {
                        fileChooser.setInitialDirectory(initialDirectory);
                    }
                    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png"));
                    File selectedFile = fileChooser.showSaveDialog(primaryStage);
                    if (selectedFile != null) {
                        ImageIO.write(image, "png", selectedFile);

                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText("Information Dialog");
                        alert.setContentText("A screenshot has been taken.");
                        alert.showAndWait();
                    }
                } catch (HeadlessException e1) {
                    e1.printStackTrace();
                } catch (AWTException e1) {
                    e1.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}