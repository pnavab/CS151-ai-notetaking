module com.example.ainotetakingfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.ainotetakingfx to javafx.fxml;
    exports com.example.ainotetakingfx;
}