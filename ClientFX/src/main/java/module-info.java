module com.clientfx.clientfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.clientfx to javafx.fxml;
    exports com.clientfx;
}