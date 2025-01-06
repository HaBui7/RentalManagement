package hoangvaha.frontend.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/hoangvaha/frontend/fxml/tenant/tenantMainLayout.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Tenant Application");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
