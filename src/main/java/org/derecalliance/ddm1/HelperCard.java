package org.derecalliance.ddm1;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;


public class HelperCard extends VBox {

    public HelperCard(Helper helper) {
        setSpacing(10);
        setPadding(new Insets(10));
        setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        Label nameLabel = new Label("Name: " + helper.getName());
        Label roleLabel = new Label("Role: " + helper.getRole());
        Label contactLabel = new Label("Contact: " + helper.getContact());

        getChildren().addAll(nameLabel, roleLabel, contactLabel);
    }
}

