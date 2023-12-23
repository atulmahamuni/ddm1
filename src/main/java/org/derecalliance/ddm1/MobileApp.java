package org.derecalliance.ddm1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//public class HelloApplication extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}

import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.derecalliance.ddm1.state.State;

import javax.swing.event.ChangeListener;
import java.net.URI;
import java.util.Map;

public class MobileApp extends Application {

    Map<String,String> testNames = Map.of(
            "a","http://localhost:8001",
            "b","http://localhost:8002",
            "c","http://localhost:8003",
            "d","http://localhost:8004",
            "e","http://localhost:8005");
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mobile App");

        // Create a VBox for the sign-in screen
        VBox signInBox = createSignInBox(primaryStage);

        // Create the initial scene with the sign-in screen
        Scene signInScene = new Scene(signInBox, 400, 700);

        // Set the scene for the primary stage
        primaryStage.setScene(signInScene);

        primaryStage.show();
    }

    private VBox createSignInBox(Stage primaryStage) {
        VBox signInBox = new VBox(10);
        signInBox.setPadding(new Insets(20));
        signInBox.setAlignment(javafx.geometry.Pos.CENTER);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label uriLabel = new Label("URI:");
        TextField uriField = new TextField();

        Button submitButton = new Button("Submit");
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (testNames.containsKey(newValue)) {
                uriField.setText(testNames.get(newValue));
            }
        });

        submitButton.setOnAction(e -> {
            // Perform validation
            String name = nameField.getText();
            String uriStr = uriField.getText();
            URI uri;

            if (name.isEmpty()) {
                showAlert("Name cannot be blank.");
            } else if ((uri = isValidUri(uriStr)) == null) {
                showAlert("Invalid URI");
            } else {
                // Successful sign-in
                State.getInstance().setName(name);
                State.getInstance().setUri(uri);
                MainAppController mainAppController = new MainAppController(primaryStage);

                // Initialize the main scene
                Scene mainScene = new Scene(mainAppController.getView(), 400,
                        700);

                // Set the scene for the primary stage
                primaryStage.setScene(mainScene);

                primaryStage.show();
            }
        });

        signInBox.getChildren().addAll(nameLabel, nameField, uriLabel,
                uriField, submitButton);
        return signInBox;
    }

    private URI isValidUri(String uriStr) {
        URI ret = null;
        try {
            ret = new URI(uriStr);
        } catch (Exception e) {

        }
        return ret;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // You can add methods to create and switch to other scenes as needed

    // The start method creates the initial sign-in screen, and you can create
    // additional scenes as your app requires.

}
