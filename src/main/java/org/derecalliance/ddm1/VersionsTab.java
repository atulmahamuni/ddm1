package org.derecalliance.ddm1;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.List;

public class VersionsTab extends Tab {

    private final List<Version> versions = new ArrayList<>();
    private StackPane view;

    public VersionsTab() {
        view = new StackPane();
        view.setStyle("-fx-background-color: #FFFFFF;"); // Set background color for HelpersTab

        Label label = new Label("This is the VersionsTab content.");

        view.getChildren().add(label);

        // Create a VBox to hold the list of Version cards
        VBox versionsContainer = new VBox(10);
        // Populate the versions ArrayList with Version objects (you can add your logic here)

        // Create and add Version cards to the container (populate with data as needed)
        for (Version version : versions) {
            VersionCard versionCard = new VersionCard(version);
            versionsContainer.getChildren().add(versionCard);
        }

        view.getChildren().add(versionsContainer);
    }
    public StackPane getView() {
        return view;
    }
}

