package com.example.csc311lab2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class HelloApplication extends Application {

    private Stage primaryStage;

    public static String stylesheet() {
        return Objects.requireNonNull(
                HelloApplication.class.getResource("styles.css")).toExternalForm();
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        showSplash();
    }

    private void showSplash() {
        Stage splashStage = new Stage(StageStyle.UNDECORATED);

        // Only moves on when the user clicks NEXT
        SplashScreen splash = new SplashScreen(() -> {
            splashStage.close();
            showLogin();
        });

        Scene scene = new Scene(splash.getRoot(), 960, 540);
        scene.getStylesheets().add(stylesheet());
        splashStage.setScene(scene);
        splashStage.centerOnScreen();
        splashStage.show();

        splash.play();
    }

    // Also used by the File > Log Out menu item
    private void showLogin() {
        LoginScreen login = new LoginScreen(this::showLanding, Platform::exit);

        Scene scene = new Scene(login.getRoot(), 1000, 650);
        scene.getStylesheets().add(stylesheet());
        primaryStage.setTitle("Farmingdale Alumni & Foundation - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    private void showLanding() {
        LandingScreen landing = new LandingScreen(this::showLogin);

        Scene scene = new Scene(landing.getRoot(), 1000, 700);
        scene.getStylesheets().add(stylesheet());
        primaryStage.setTitle("Farmingdale Alumni & Foundation");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch();
    }
}
