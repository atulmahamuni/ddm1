package org.derecalliance.ddm1;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.geometry.Insets;


import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.concurrent.Callable;

import static javafx.scene.layout.Priority.ALWAYS;

public class SharerTab extends Tab {

    private final ComboBox<String> secretDropdown;

    public SharerTab(Stage primaryStage) {
        this.setText("Sharer");
        Label l = new Label("hi");

        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: #e0e0e0");

//        VBox vbox = new VBox();
//        vbox.setStyle("-fx-background-color: #f0f00f");
//        VBox.setVgrow(vbox, ALWAYS);
//
//        vbox.getChildren().add(l);
//        pane.getChildren().add(vbox);
//        pane.getChildren().add(l);

        System.out.println("primaryStage dimensions: " + primaryStage.getScene().getWidth() + ", " + primaryStage.getScene().getHeight());
        System.out.println("Pane dimensions: " + stackPane.getHeight() + ", " + stackPane.getPrefHeight() + ", " + stackPane.getMinHeight() );


        BorderPane sharerContent = new BorderPane();
        sharerContent.setStyle("-fx-background-color: #0000ff");
//        sharerContent.setPrefSize(primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());


        // Create a top row with a dropdown menu for secrets and a Plus button
        HBox topRow = new HBox(10);
        secretDropdown = new ComboBox<>();
        secretDropdown.setPromptText("Select a Secret");

        Button plusButton = new Button("+");
        plusButton.setOnAction(e -> createNewSecret(primaryStage));

        topRow.getChildren().addAll(secretDropdown, plusButton);

        // Create a middle pane with a label
        Pane middlePane = createMiddlePane();

        // Create a bottom navigation bar
        HBox bottomNavBar = createBottomNavBar(primaryStage);

        // Set the top row at the top of the BorderPane
        sharerContent.setTop(topRow);

        // Set the middle pane in the center of the BorderPane
//        sharerContent.setCenter(middlePane);
//        VBox centerVBox = new VBox();
//        VBox.setVgrow(centerVBox, ALWAYS);
//        centerVBox.getChildren().addAll(middlePane); // You can add more elements here
//        sharerContent.setCenter(centerVBox);

        // Set the bottom navigation bar at the bottom of the BorderPane
        sharerContent.setBottom(bottomNavBar);

//        pane.getChildren().add(sharerContent);
        stackPane.getChildren().add(sharerContent);
        this.setContent(stackPane);
    }

    private void createNewSecret(Stage primaryStage) {
        // Implement logic to create a new secret and add it to the ArrayList
        // You can open a new dialog or scene to input the secret details
        // For simplicity, we'll just add a dummy secret
        secretDropdown.getItems().add("Sample Secret");
        secretDropdown.setPromptText("Select a Secret");
    }

    private HBox createBottomNavBar(Stage primaryStage) {
        HBox bottomNavBar = new HBox();
        bottomNavBar.setStyle("-fx-background-color: #303F9F;"); // Background color for the navigation bar
        bottomNavBar.setPadding(new Insets(8, 16, 8, 16));

        // Create buttons for the bottom navigation bar
        Button versionsButton = new Button("Versions");
        Button helpersButton = new Button("Helpers");

        // Add action handlers to switch content when buttons are clicked
        versionsButton.setOnAction(e -> showVersionsContent(primaryStage));
        helpersButton.setOnAction(e -> showHelpersContent(primaryStage));

        // Set button styles (you can customize these as needed)
        versionsButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        helpersButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");

        // Add buttons to the navigation bar
        bottomNavBar.getChildren().addAll(versionsButton, helpersButton);
        HBox.setHgrow(bottomNavBar, ALWAYS); // Ensures it spans the width

        return bottomNavBar;
    }

    private Pane createMiddlePane() {
        Pane middlePane = new StackPane();
        middlePane.setStyle("-fx-background-color: #FFFFFF;"); // Background color for the middle pane

        Label helloLabel = new Label("Hello");

        ((StackPane) middlePane).getChildren().add(helloLabel);
        return middlePane;
    }

    private void showVersionsContent(Stage primaryStage) {
        // Create a new scene for the VersionsTab and switch to it
        VersionsTab versionsTab = new VersionsTab();
        Scene versionsScene = new Scene(versionsTab.getView(), 400, 300);

        primaryStage.setScene(versionsScene);
    }

    private void showHelpersContent(Stage primaryStage) {
        // Create a new scene for the HelpersTab and switch to it
        HelpersTab helpersTab = new HelpersTab();
        Scene helpersScene = new Scene(helpersTab.getView(), 400, 300);

        primaryStage.setScene(helpersScene);
    }
}
