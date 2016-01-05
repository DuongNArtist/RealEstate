package edu.epu.realestate;

import edu.epu.realestate.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by DuongNArtist on 11/12/2015.
 */
public class MainApplication extends Application {

    public static final String APP_NAME = "Hệ thống thu thập và trích rút thông tin Bất Động Sản";
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.setTitle(APP_NAME);
        this.stage.setResizable(false);
        try {
            URL url = MainApplication.class.getResource("views/main_view.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            MainController controller = loader.getController();
            controller.setApplication(this);
            this.stage.setScene(scene);
            this.stage.getIcons().add(new Image("file:res/images/icon.png"));
            this.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
