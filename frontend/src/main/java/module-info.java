module hoangvaha.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

//    opens hoangvaha.frontend to javafx.fxml;
//    exports hoangvaha.frontend;
    exports hoangvaha.frontend.views;
    opens hoangvaha.frontend.views to javafx.fxml;
    exports hoangvaha.frontend.controllers;
    opens hoangvaha.frontend.controllers to javafx.fxml;
}