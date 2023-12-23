package org.derecalliance.ddm1.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javafx.scene.image.ImageView;
//import java.awt.Color;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class QRCode {
    private double startX, startY, endX, endY;
    private Pane root;
    private Scene scene;


    public void createHelperPane(Pane currentPane) {
        int[] rectDimensions = {250, 250};
        int[] sceneDimensions = {rectDimensions[0] + 50,
                rectDimensions[1] + 100};
        Stage initStage = new Stage();
        initStage.setOpacity(0.4);

        Rectangle transparentArea = new Rectangle(rectDimensions[0],
                rectDimensions[1]);
        transparentArea.setFill(Color.TRANSPARENT);
        transparentArea.setStroke(Color.BLACK); // For visibility
        transparentArea.opacityProperty().set(0.1);

        VBox box = new VBox();
        Button captureButton = new Button("Capture");
        captureButton.setOnAction(e -> capture(null, transparentArea));
        captureButton.setStyle("-fx-background-color:rgba(ff,0,0,1)");
        box.getChildren().addAll(transparentArea, captureButton);
        final Scene scene2 = new Scene(box, sceneDimensions[0],
                sceneDimensions[1]);
        scene2.setFill(Color.TRANSPARENT);
        initStage.setScene(scene2);

        initStage.show();
    }

    private void capture(Pane helperPane, Rectangle transparentArea) {
        try {
            // Calculate the bounds of the transparent area in screen coordinates
            Bounds bounds = transparentArea.localToScreen(transparentArea.getBoundsInLocal());

            // Capture the screen portion
            Robot robot = new Robot();
            System.out.println("Capturing: (" + bounds.getMinX() + ", " +
                    bounds.getMinY() + ", " + bounds.getWidth() + ", " + bounds.getHeight() + ")");
            Rectangle2D captureRect = new Rectangle2D(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
//            Rectangle2D captureRect = new Rectangle2D(0, 0, 500, 500);
            WritableImage screenshot = robot.getScreenCapture(null, captureRect);

            // Save the image
            File file = new File("captured_image.png");
            ImageIO.write(SwingFXUtils.fromFXImage(screenshot, null), "png", file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public StackPane createQR(String data,
                                String charset,
                                int height, int width) {

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    new String(data.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, width, height);


//        MatrixToImageWriter.writeToFile(
//                matrix,
//                path.substring(path.lastIndexOf('.') + 1),
//                new File(path));

            // Convert BitMatrix to BufferedImage
            BufferedImage bufferedImage = toBufferedImage(bitMatrix);

            // Convert BufferedImage to WritableImage
            WritableImage writableImage = fromBufferedImage(bufferedImage);

            // Use an ImageView to display the WritableImage
            ImageView imageView = new ImageView(writableImage);

            StackPane root = new StackPane(imageView);

//            Scene scene = new Scene(root, bufferedImage.getWidth(), bufferedImage.getHeight());
//
//            primaryStage.setTitle("BitMatrix Display");
//            primaryStage.setScene(scene);
//            primaryStage.show();
            return root;
        } catch (Exception e) {
            System.out.println("Exception in createQR");
        }
        return null;
    }

    private BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ?
                        java.awt.Color.BLACK.getRGB() : java.awt.Color.WHITE.getRGB());
            }
        }
        return image;
    }

    private WritableImage fromBufferedImage(BufferedImage bufferedImage) {
        WritableImage writableImage = null;
        if (bufferedImage != null) {
            writableImage = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
            javafx.embed.swing.SwingFXUtils.toFXImage(bufferedImage, writableImage);
        }
        return writableImage;
    }


//    public void showCaptureImageDialog(Pane currentPane) {
//        // Create the custom dialog.
//        Dialog<Pair<String, String>> dialog = new Dialog<>();
//        dialog.setTitle("Scan QR code");
//        dialog.setHeaderText("Scan QR code");
//
//        // Set the button types.
//        ButtonType saveButtonType = new ButtonType("Capture",
//                ButtonBar.ButtonData.OK_DONE);
//        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
//
//        captureImage(currentPane);
//        // Create the name and secret text fields.
////        GridPane grid = new GridPane();
////        grid.setHgap(10);
////        grid.setVgap(10);
////        grid.setPadding(new Insets(20, 10, 10, 10));
////
////        TextField secretName = new TextField();
////        secretName.setPromptText("Name");
////        TextArea secretText = new TextArea();
////        secretText.setPromptText("Secret Text");
////
////        grid.add(new Label("Name:"), 0, 0);
////        grid.add(secretName, 1, 0);
////        grid.add(new Label("Text:"), 0, 1);
////        grid.add(secretText, 1, 1);
////
////        dialog.getDialogPane().setContent(grid);
//
//        // Set the size of the dialog to match the size of the current pane.
//        dialog.getDialogPane().setPrefSize(currentPane.getWidth(), currentPane.getHeight());
//
//        // Request focus on the name field by default.
////        Platform.runLater(secretName::requestFocus);
//
//        // Convert the result to a name-secretText-pair when the save button is clicked.
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == saveButtonType) {
//                System.out.println("Taking a screeenshot now");
////                return new Pair<>(secretName.getText(), secretText.getText());
//                return new Pair<>("Abc", "Def");
//            }
//            return null;
//        });
//
//        // Show the dialog and capture the result.
//        Optional<Pair<String, String>> result = dialog.showAndWait();
//        result.ifPresent(secretData -> {
//            System.out.println("Name=" + secretData.getKey() + ", Text=" + secretData.getValue());
////            // Here, you can add code to handle the secret data
////            Secret secret = new Secret(secretData.getKey(),
////                    secretData.getValue());
////            State.getInstance().addSecret(secret);
////
////            System.out.println("SecretDrop down has items count: " +
////                    secretDropdown.getItems().size());
////            System.out.println("State secrets has items count: " +
////                    State.getInstance().getSecrets().size());
////            paintSharerMainTab();
//        });
//    }

//    public void captureImage(Pane pane) {
//        root = new Pane();
//        scene = new Scene(root, 600, 400);
//
//        scene.setOnMousePressed(event -> {
//            if (event.getButton() == MouseButton.PRIMARY) {
//                startX = event.getX();
//                startY = event.getY();
//            }
//        });
//
//        scene.setOnMouseDragged(event -> {
//            if (event.getButton() == MouseButton.PRIMARY) {
//                endX = event.getX();
//                endY = event.getY();
//                drawRectangle();
//            }
//        });
//
//        scene.setOnMouseReleased(event -> {
//            if (event.getButton() == MouseButton.PRIMARY) {
//                endX = event.getX();
//                endY = event.getY();
//                captureAndSaveScreenshot();
//                root.getChildren().clear(); // Clear the rectangle after capturing the screenshot
//            }
//        });
//
//        pane.getChildren().add(scene.getRoot());
////        primaryStage.setTitle("Screenshot App");
////        primaryStage.setScene(scene);
////        primaryStage.show();
//    }
//
//    private void drawRectangle() {
//        root.getChildren().clear(); // Clear previous rectangles
//        root.getChildren().add(new javafx.scene.shape.Rectangle(startX, startY, endX - startX, endY - startY));
//    }
//
//    private void captureAndSaveScreenshot() {
////        WritableImage writableImage = new WritableImage((int) scene.getWidth(), (int) scene.getHeight());
//        SnapshotParameters params = new SnapshotParameters();
//        params.setFill(Color.TRANSPARENT); // making white background go away
//
//        // Translate viewport to scene coordinates
//        Rectangle2D viewport = new Rectangle2D(Math.min(startX, endX), Math.min(startY, endY),
//                Math.abs(endX - startX), Math.abs(endY - startY));
//        params.setViewport(viewport);
//
//        javafx.scene.robot.Robot robot = new Robot();
//        WritableImage imgReturn = robot.getScreenCapture(null, viewport);
//
////        root.snapshot(params, writableImage);
//
//        File directory = new File("scannedqrs");
//        if (!directory.exists()) {
//            directory.mkdir();
//        }
//
//        File file = new File("scannedqrs/screenshot.png");
//        try {
////            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
//            ImageIO.write(SwingFXUtils.fromFXImage(imgReturn, null), "png", file);
//            System.out.println("Screenshot saved: " + file.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}