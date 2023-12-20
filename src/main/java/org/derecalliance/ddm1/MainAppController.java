package org.derecalliance.ddm1;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainAppController {

    private final Stage primaryStage;
    private final TabPane tabPane;

    public MainAppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.tabPane = new TabPane();
//        VBox tabPaneBox = new VBox();
//
//        VBox.setVgrow(tabPaneBox, Priority.ALWAYS);

//        this.tabPane.setPrefHeight(500);
        this.tabPane.setPrefSize(primaryStage.getScene().getWidth(),
                primaryStage.getScene().getHeight());
        this.tabPane.setStyle("-fx-background-color: #00ff00");

        initializeTabs();
    }

    private void initializeTabs() {
        // Create and add "Sharer" tab
        Tab sharerTab = new SharerTab(primaryStage);
        tabPane.getTabs().add(sharerTab);

        // Create and add "Helper" tab
        Tab helperTab = new HelperTab();
        tabPane.getTabs().add(helperTab);
    }

    public VBox original_getView() {
        VBox mainBox = new VBox(300);
        mainBox.getChildren().add(tabPane);
        return mainBox;
    }
    public Pane getView() {
        Pane pane = new Pane();
        VBox mainBox = new VBox();
        mainBox.getChildren().add(tabPane);

        pane.setStyle("-fx-background-color: #ff0000");
//        Label l = new Label("Hello");
        pane.getChildren().add(mainBox);

        return pane;
    }
}
