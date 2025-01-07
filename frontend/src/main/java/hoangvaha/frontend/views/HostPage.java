package hoangvaha.frontend.views;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HostPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HostPage.class.getResource("/hoangvaha/frontend/fxml/host/hostManageRental.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}