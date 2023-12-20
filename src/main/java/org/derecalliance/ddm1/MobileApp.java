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

public class MobileApp extends Application {

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

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            // Perform validation
            String name = nameField.getText();
            String phoneNumber = phoneField.getText();

            if (name.isEmpty()) {
                showAlert("Name cannot be blank.");
            } else if (!isValidPhoneNumber(phoneNumber)) {
                showAlert("Invalid phone number format.");
            } else {
                // Successful sign-in, switch to the next scene (you can implement this part later)
                // For now, we'll just close the application
//                primaryStage.close();
                MainAppController mainAppController = new MainAppController(primaryStage);

                // Initialize the main scene
                Scene mainScene = new Scene(mainAppController.getView(), 400,
                        700);

                // Set the scene for the primary stage
                primaryStage.setScene(mainScene);

                primaryStage.show();
            }
        });

        signInBox.getChildren().addAll(nameLabel, nameField, phoneLabel, phoneField, submitButton);
        return signInBox;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Implement your phone number validation logic here
        // For simplicity, we'll just check if it contains digits and is 10 characters long
//        return phoneNumber.matches("\\d{10}");
        return true;
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
