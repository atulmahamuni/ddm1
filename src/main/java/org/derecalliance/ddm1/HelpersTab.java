package org.derecalliance.ddm1;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.derecalliance.ddm1.state.Helper;

import java.util.ArrayList;
import java.util.List;
import org.derecalliance.ddm1.qrcode.QRCode;

public class HelpersTab extends Tab {

    private final List<Helper> helpers = new ArrayList<>();

    private StackPane view;

    public HelpersTab() {
        view = new StackPane();
        view.setStyle("-fx-background-color: #FFFFFF;"); // Set background color for HelpersTab
        VBox helpersContainer = new VBox();


        Label label = new Label("This is the HelpersTab content.");


        Button addHelperButton = new Button("Add Helper");
       addHelperButton.setOnAction(e -> addHelperButtonPressed(view));
        helpersContainer.getChildren().add(addHelperButton);

        view.getChildren().add(helpersContainer);
//        view.getChildren().add(label);


        // Create a VBox to hold the list of Helper cards
//        VBox helpersContainer = new VBox(10);
//        // Populate the helpers ArrayList with Helper objects (you can add your logic here)
//
//        // Create and add Helper cards to the container (populate with data as needed)
//        for (Helper helper : helpers) {
//            HelperCard helperCard = new HelperCard(helper);
//            helpersContainer.getChildren().add(helperCard);
//        }
//
//        view.getChildren().add(helpersContainer);
    }

    public void addHelperButtonPressed(StackPane currentPane) {
        System.out.println("IN addHelperButtonPressed");
        QRCode qrcode = new QRCode();
//        qrcode.captureImage(currentPane);
//        qrcode.showCaptureImageDialog(currentPane);
        qrcode.createHelperPane(currentPane);
    }

    public StackPane getView() {
        return view;
    }
}

