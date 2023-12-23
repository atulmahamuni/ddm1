package org.derecalliance.ddm1;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import org.derecalliance.ddm1.qrcode.QRCode;
import org.derecalliance.ddm1.state.Secret;
import org.derecalliance.ddm1.state.State;

import java.util.ArrayList;
import java.util.Map;

public class HelperMainTab extends Tab {

    public HelperMainTab() {
        this.setText("Helper");

//        Button plusButton = new Button("+");
//        plusButton.setOnAction(e -> createNewSharer(middlePane));
//        topRow.getChildren().add(plusButton);

        StackPane stackPane = new StackPane();
        BorderPane helperContent = new BorderPane();
        HBox topRow = new HBox(10);

        Pane middlePane = new StackPane();
        VBox middleBox = new VBox();
        VBox.setVgrow(middleBox, Priority.ALWAYS);
        Label helpersLabel = new Label("Helpers");
        middleBox.getChildren().add(helpersLabel);
        middlePane.getChildren().add(middleBox);

        HBox bottomRow = new HBox(10);

        Button plusButton = new Button("+");
        plusButton.setOnAction(e -> createNewSharer(middlePane));
        bottomRow.getChildren().add(plusButton);


        // Set the top row at the top of the BorderPane
        helperContent.setTop(topRow);
        helperContent.setCenter(middlePane);
        helperContent.setBottom(bottomRow);

//        pane.getChildren().add(sharerContent);
        stackPane.getChildren().add(helperContent);
        this.setContent(stackPane);
    }

    public void createNewSharer(Pane pane) {
        System.out.println("Creating new sharer");
        QRCode qrCode = new QRCode();
        StackPane qrPane = qrCode.createQR("This is working", "UTF-8", 200,
                200);
        pane.getChildren().add(qrPane);
    }
}
