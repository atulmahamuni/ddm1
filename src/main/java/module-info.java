module org.derecalliance.ddm1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires javafx.swing;
    requires com.google.zxing;
    requires com.google.zxing.javase;

    opens org.derecalliance.ddm1 to javafx.fxml;
    exports org.derecalliance.ddm1;
    exports org.derecalliance.ddm1.state;
    opens org.derecalliance.ddm1.state to javafx.fxml;
}