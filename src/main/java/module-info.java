module org.derecalliance.ddm1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.derecalliance.ddm1 to javafx.fxml;
    exports org.derecalliance.ddm1;
}