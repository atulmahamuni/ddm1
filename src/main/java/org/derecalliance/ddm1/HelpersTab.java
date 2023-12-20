package org.derecalliance.ddm1;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.List;

public class HelpersTab extends Tab {

    private final List<Helper> helpers = new ArrayList<>();

    private StackPane view;

    public HelpersTab() {
        view = new StackPane();
        view.setStyle("-fx-background-color: #FFFFFF;"); // Set background color for HelpersTab

        Label label = new Label("This is the HelpersTab content.");

        view.getChildren().add(label);

        // Create a VBox to hold the list of Helper cards
        VBox helpersContainer = new VBox(10);
        // Populate the helpers ArrayList with Helper objects (you can add your logic here)

        // Create and add Helper cards to the container (populate with data as needed)
        for (Helper helper : helpers) {
            HelperCard helperCard = new HelperCard(helper);
            helpersContainer.getChildren().add(helperCard);
        }

        view.getChildren().add(helpersContainer);
    }
    public StackPane getView() {
        return view;
    }
}

