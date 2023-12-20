package org.derecalliance.ddm1;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class VersionCard extends VBox {

    public VersionCard(Version version) {
        setSpacing(10);
        setPadding(new Insets(10));
        setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        Label nameLabel = new Label("Name: " + version.getName());
        Label descriptionLabel = new Label("Description: " + version.getDescription());
        Label dateLabel = new Label("Date: " + version.getDate());

        getChildren().addAll(nameLabel, descriptionLabel, dateLabel);
    }
}

