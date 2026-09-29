package com.example.csc311lab2;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class LandingScreen {

    private final BorderPane root = new BorderPane();

    public LandingScreen(Runnable onLogout) {
        root.getStyleClass().add("landing-root");


        MenuItem logout = new MenuItem("Log Out");
        logout.setOnAction(e -> onLogout.run());

        Menu file = new Menu("File");
        file.getItems().addAll(
                new MenuItem("New"),
                new MenuItem("File"),
                new SeparatorMenuItem(),
                logout);
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");
        MenuBar menuBar = new MenuBar(file, edit, help);
        root.setTop(menuBar);


        HBox tables = new HBox(buildTable(), buildTable());
        tables.setAlignment(Pos.TOP_CENTER);
        tables.getStyleClass().add("tables-area");

        ScrollPane scroll = new ScrollPane(tables);
        scroll.setFitToWidth(true);
        scroll.getStyleClass().add("landing-scroll");
        root.setCenter(scroll);
    }

    private VBox buildTable() {
        VBox table = new VBox();
        table.setPrefWidth(320);
        table.setMinWidth(320);
        table.setMaxWidth(320);
        table.getStyleClass().add("db-table");

        Label header = new Label("TableTable");
        header.setMaxWidth(Double.MAX_VALUE);
        header.getStyleClass().add("db-header");
        table.getChildren().add(header);

        for (int i = 0; i < 23; i++) {
            table.getChildren().add(buildRow());
        }
        return table;
    }

    private HBox buildRow() {
        Label name = new Label("Field name");
        name.setPrefWidth(100);
        Label type = new Label("Data type");
        type.setPrefWidth(98);
        Label def = new Label("Default");
        def.setPrefWidth(90);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Label id = new Label("ID");
        id.getStyleClass().add("db-id");

        HBox row = new HBox(name, type, def, spacer, id);
        row.setAlignment(Pos.CENTER_LEFT);
        row.getStyleClass().add("db-row");
        return row;
    }

    public BorderPane getRoot() {
        return root;
    }
}
