package org.derecalliance.ddm1;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.geometry.Insets;


import javafx.scene.Scene;
import javafx.util.Pair;
import org.derecalliance.ddm1.state.Secret;
import org.derecalliance.ddm1.state.State;

import java.util.ArrayList;
import java.util.Optional;

import static javafx.scene.layout.Priority.ALWAYS;

public class SharerMainTab extends Tab {

    private final ComboBox<String> secretDropdown;

    public SharerMainTab(Stage primaryStage) {
        secretDropdown = new ComboBox<>();
        paintSharerMainTab();
    }

    public void paintSharerMainTab() {
        populateSecretDropdown();


        this.setText("Sharer");
        Label l = new Label("hi");

        StackPane stackPane = new StackPane();
//        stackPane.setStyle("-fx-background-color: #e0e0e0");

//        System.out.println("primaryStage dimensions: " + primaryStage.getScene().getWidth() + ", " + primaryStage.getScene().getHeight());
//        System.out.println("Pane dimensions: " + stackPane.getHeight() + ", " + stackPane.getPrefHeight() + ", " + stackPane.getMinHeight() );


        BorderPane sharerContent = new BorderPane();
//        sharerContent.setStyle("-fx-background-color: #0000ff");

        // Create a top row with a dropdown menu for secrets and a Plus button
        HBox topRow = new HBox(10);

        ArrayList< Secret> secrets = State.getInstance().getSecrets();
        if (secrets.size() == 0) {
            Label noSecretsLabel = new Label("You have no secrets");
            topRow.getChildren().add(noSecretsLabel);
        } else {
            secretDropdown.setPromptText("Select a Secret");
            topRow.getChildren().add(secretDropdown);
        }


        // Create a middle pane with a label
        Pane middlePane = createMiddlePane();

        Button plusButton = new Button("+");
        plusButton.setOnAction(e -> createNewSecret(middlePane));
        topRow.getChildren().add(plusButton);


        // Create a bottom navigation bar
        HBox bottomNavBar = createBottomNavBar(middlePane);


        // Set the top row at the top of the BorderPane
        sharerContent.setTop(topRow);
        sharerContent.setCenter(middlePane);

        // Set the bottom navigation bar at the bottom of the BorderPane
        sharerContent.setBottom(bottomNavBar);

//        pane.getChildren().add(sharerContent);
        stackPane.getChildren().add(sharerContent);
        this.setContent(stackPane);
    }

    private void createNewSecret(Pane currentPane) {
        // Implement logic to create a new secret and add it to the ArrayList
        // You can open a new dialog or scene to input the secret details
        // For simplicity, we'll just add a dummy secret
//        secretDropdown.getItems().add("Sample Secret");
//        secretDropdown.setPromptText("Select a Secret");

        showAddSecretDialog(currentPane);
    }

    private HBox createBottomNavBar(Pane middlePane) {
        HBox bottomNavBar = new HBox();
        bottomNavBar.setStyle("-fx-background-color: #303F9F;"); // Background color for the navigation bar
        bottomNavBar.setPadding(new Insets(8, 16, 8, 16));

        // Create buttons for the bottom navigation bar
        Button versionsButton = new Button("Versions");
        Button helpersButton = new Button("Helpers");

        // Add action handlers to switch content when buttons are clicked
        versionsButton.setOnAction(e -> showVersionsContent(middlePane));
        helpersButton.setOnAction(e -> showHelpersContent(middlePane));

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
        middlePane.setStyle("-fx-background-color: #ff00000;"); // Background
        return middlePane;
    }

    private void showAddSecretDialog(Pane currentPane) {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add Secret");
        dialog.setHeaderText("Enter Secret Details");

        // Set the button types.
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create the name and secret text fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        TextField secretName = new TextField();
        secretName.setPromptText("Name");
        TextArea secretText = new TextArea();
        secretText.setPromptText("Secret Text");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(secretName, 1, 0);
        grid.add(new Label("Text:"), 0, 1);
        grid.add(secretText, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Set the size of the dialog to match the size of the current pane.
        dialog.getDialogPane().setPrefSize(currentPane.getWidth(), currentPane.getHeight());

        // Request focus on the name field by default.
        Platform.runLater(secretName::requestFocus);

        // Convert the result to a name-secretText-pair when the save button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Pair<>(secretName.getText(), secretText.getText());
            }
            return null;
        });

        // Show the dialog and capture the result.
        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(secretData -> {
            System.out.println("Name=" + secretData.getKey() + ", Text=" + secretData.getValue());
            // Here, you can add code to handle the secret data
            Secret secret = new Secret(secretData.getKey(),
                    secretData.getValue());
            State.getInstance().addSecret(secret);

            System.out.println("SecretDrop down has items count: " +
                    secretDropdown.getItems().size());
            System.out.println("State secrets has items count: " +
                    State.getInstance().getSecrets().size());
            paintSharerMainTab();
        });
    }

    public void populateSecretDropdown() {
        secretDropdown.getItems().clear();
        ArrayList<Secret> secrets = State.getInstance().getSecrets();
        for (int i = 0; i < secrets.size(); i++) {
            secretDropdown.getItems().add(secrets.get(i).getName());
        }
    }
    private void showVersionsContent(Pane pane) {
        // Create a new scene for the VersionsTab and switch to it
        VersionsTab versionsTab = new VersionsTab();
        System.out.println("In showVersionsContent");
        System.out.println("Pane: " + pane.getHeight() + ", " + pane.getWidth());
        pane.getChildren().clear();
        Label l = new Label("Versions");

        pane.setStyle("-fx-background-color: #f00000;"); // Background

        ((StackPane) pane).getChildren().add(versionsTab.getView());
    }

    private void showHelpersContent(Pane pane) {
        // Create a new scene for the VersionsTab and switch to it
        HelpersTab helpersTab = new HelpersTab();

        pane.getChildren().clear();
        pane.setStyle("-fx-background-color: #00f000;"); // Background

        Label l = new Label("Helpers");
        ((StackPane) pane).getChildren().add(helpersTab.getView());
    }
}
