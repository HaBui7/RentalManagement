package hoangvaha.frontend.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TenantPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TenantPage.class.getResource("/hoangvaha/frontend/fxml/tenantHomePage.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load());
        String css = getClass().getResource("/hoangvaha/frontend/styles/style.css").toExternalForm();
        loginScene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
