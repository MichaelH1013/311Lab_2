package com.example.csc311lab2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.InputStream;

public class LoginScreen {

    private final HBox root = new HBox();
    private final VBox form = new VBox(20);   // swapped between login / register
    private final Runnable onLogin;
    private final Runnable onCancel;

    public LoginScreen(Runnable onLogin, Runnable onCancel) {
        this.onLogin = onLogin;
        this.onCancel = onCancel;
        root.getStyleClass().add("login-root");


        form.setAlignment(Pos.BOTTOM_LEFT);
        form.setPadding(new Insets(0, 0, 40, 60));
        showLoginForm();


        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        VBox leftColumn = new VBox(buildCircleLogo(), spacer, form);
        leftColumn.setPrefWidth(440);
        leftColumn.setMinWidth(440);


        StackPane ram = buildRam();
        HBox.setHgrow(ram, Priority.ALWAYS);

        root.getChildren().addAll(leftColumn, ram);
    }


    private void showLoginForm() {
        TextField username = field("Username");
        PasswordField password = passwordField("Password");

        Button loginBtn = grayButton("LOGIN", 96);
        loginBtn.setOnAction(e -> onLogin.run());

        Button registerBtn = grayButton("REGISTER", 96);
        registerBtn.setOnAction(e -> showRegisterForm());

        Button cancelBtn = grayButton("CANCEL", 96);
        cancelBtn.setOnAction(e -> onCancel.run());

        HBox buttons = new HBox(16, loginBtn, registerBtn, cancelBtn);
        buttons.setMaxWidth(320);

        form.getChildren().setAll(username, password, buttons);
    }


    private void showRegisterForm() {
        TextField firstName = field("First name");
        TextField lastName = field("Last name");
        TextField email = field("Email");
        PasswordField password = passwordField("Password");

        Button submitBtn = grayButton("REGISTER", 152);
        submitBtn.setOnAction(e -> showLoginForm());   // no real logic yet

        Button backBtn = grayButton("CANCEL", 152);
        backBtn.setOnAction(e -> showLoginForm());

        HBox buttons = new HBox(16, submitBtn, backBtn);
        buttons.setMaxWidth(320);

        form.getChildren().setAll(firstName, lastName, email, password, buttons);
    }


    private TextField field(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setPrefSize(320, 50);
        tf.setMaxWidth(320);
        return tf;
    }

    private PasswordField passwordField(String prompt) {
        PasswordField pf = new PasswordField();
        pf.setPromptText(prompt);
        pf.setPrefSize(320, 50);
        pf.setMaxWidth(320);
        return pf;
    }

    private Button grayButton(String text, double width) {
        Button b = new Button(text);
        b.getStyleClass().add("gray-button");
        b.setPrefSize(width, 50);
        return b;
    }


    private StackPane buildCircleLogo() {
        StackPane holder = new StackPane();
        holder.setAlignment(Pos.CENTER);

        holder.setPadding(new Insets(30, 60, 0, 60));

        Circle circle = new Circle(90);
        circle.getStyleClass().add("logo-circle");
        Text text = new Text("FARMINGDALE\nSTATE COLLEGE\nALUMNI and\nFOUNDATION");
        text.getStyleClass().add("logo-text");
        text.setTextAlignment(TextAlignment.CENTER);

        StackPane circleStack = new StackPane(circle, text);
        circleStack.setMaxSize(180, 180);
        holder.getChildren().add(circleStack);
        return holder;
    }


    private StackPane buildRam() {
        StackPane holder = new StackPane();
        holder.setAlignment(Pos.CENTER);

        InputStream imgStream = getClass().getResourceAsStream("ram.jpg");
        if (imgStream != null) {
            ImageView view = new ImageView(new Image(imgStream));
            view.setPreserveRatio(true);
            view.setFitHeight(540);
            holder.getChildren().add(view);
        }
        return holder;
    }

    public HBox getRoot() {
        return root;
    }
}
