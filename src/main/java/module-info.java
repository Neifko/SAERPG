module com.hsn.saerpgihm {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.hsn.saerpgihm to javafx.fxml;
    exports model;
    exports iofile;
    exports client;
    exports view;
}