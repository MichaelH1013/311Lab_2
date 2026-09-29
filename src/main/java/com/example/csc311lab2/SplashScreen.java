package com.example.csc311lab2;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.InputStream;

public class SplashScreen {

    private final StackPane root = new StackPane();
    private final ProgressBar progressBar = new ProgressBar(0);
    private final Label percentLabel = new Label("0%");
    private final Button nextButton = new Button("NEXT");

    public SplashScreen(Runnable onNext) {
        root.getStyleClass().add("splash-root");


        InputStream imgStream = getClass().getResourceAsStream("splash.jpg");
        if (imgStream != null) {
            ImageView bg = new ImageView(new Image(imgStream));
            bg.setPreserveRatio(false);
            bg.fitWidthProperty().bind(root.widthProperty());
            bg.fitHeightProperty().bind(root.heightProperty());
            root.getChildren().add(bg);
        }

        progressBar.setPrefWidth(420);
        progressBar.setPrefHeight(32);
        progressBar.getStyleClass().add("splash-progress");
        percentLabel.getStyleClass().add("splash-percent");

        StackPane barHolder = new StackPane(progressBar, percentLabel);
        barHolder.setMaxSize(420, 32);


        nextButton.getStyleClass().add("next-button");
        nextButton.setPrefSize(140, 40);
        nextButton.setVisible(false);
        nextButton.setOnAction(e -> onNext.run());


        VBox stack = new VBox(15, barHolder, nextButton);
        stack.setAlignment(Pos.TOP_CENTER);
        stack.setPadding(new Insets(35, 0, 0, 0));
        stack.setPickOnBounds(false);
        root.getChildren().add(stack);

        progressBar.progressProperty().addListener((obs, o, n) ->
                percentLabel.setText((int) (n.doubleValue() * 100) + "%"));
    }

    public StackPane getRoot() {
        return root;
    }


    public void play() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(progressBar.progressProperty(), 1.0)));
        timeline.setOnFinished(e -> nextButton.setVisible(true));
        timeline.play();
    }
}
