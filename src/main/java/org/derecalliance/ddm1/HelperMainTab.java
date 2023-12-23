package org.derecalliance.ddm1;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import org.derecalliance.ddm1.qrcode.QRCode;
import org.derecalliance.ddm1.state.Secret;
import org.derecalliance.ddm1.state.State;
//import org.derecalliance.derec.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.thebuildingblocks.keypr.sharer.*;
import com.thebuildingblocks.keypr.helper.*;
import com.thebuildingblocks.keypr.helper.tools.*;
import org.derecalliance.derec.api.*;



public class HelperMainTab extends Tab {

    public HelperMainTab() {
        this.setText("Helper");
        System.out.println("Helper main for " + State.getInstance().getName() + ", URI: " + State.getInstance().getUri());
        initHelperMainTab();
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


        helperContent.setTop(topRow);
        helperContent.setCenter(middlePane);
        helperContent.setBottom(bottomRow);

        stackPane.getChildren().add(helperContent);
        this.setContent(stackPane);
    }

    public void initHelperMainTab() {
        if (!State.getInstance().isHelperStarted()) {
            try {
                DeRecIdentity myIdentity =
                        new DeRecIdentity(State.getInstance().getName(), "Contact",
                                State.getInstance().getUri().toString(), "publicKey");
                List<DeRecIdentity> list = Collections.singletonList(myIdentity);
                TestHelperServer helperServer = new TestHelperServer();
                helperServer.startServer(State.getInstance().getUri().getPort(),
                        list);
                State.getInstance().setHelperStarted(true);
            } catch (Exception e) {
                System.out.println("Exception in initHelperMainTab");
                e.printStackTrace();
            }
        }
    }

    public void createNewSharer(Pane pane) {
        System.out.println("Creating new sharer");
        QRCode qrCode = new QRCode();
        StackPane qrPane = qrCode.createQR("This is working", "UTF-8", 200,
                200);
        pane.getChildren().add(qrPane);
    }
}
