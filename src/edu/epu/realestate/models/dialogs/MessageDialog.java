package edu.epu.realestate.models.dialogs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by DuongNArtist on 14/12/2015.
 */
public class MessageDialog {

    public static final int INFO = 0;
    public static final int ERROR = 1;
    public static final int WARNING = 2;
    private static boolean ok = false;

    public static boolean show(Stage context, int type, String title, String message) {
        ok = false;
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(context);
        stage.setResizable(false);
        VBox boxRoot = new VBox(10);
        boxRoot.setAlignment(Pos.CENTER);
        boxRoot.setPadding(new Insets(10, 10, 10, 10));
        HBox boxTop = new HBox(10);
        boxTop.setAlignment(Pos.CENTER);
        ImageView imgIcon = new ImageView();
        imgIcon.setFitWidth(50);
        imgIcon.setFitHeight(50);
        String path = "file:res/images/";
        switch (type) {
            case INFO:
                path += "info.png";
                break;

            case ERROR:
                path += "error.png";
                break;

            case WARNING:
                path += "warning.png";
                break;
            default:
                break;
        }
        imgIcon.setImage(new Image(path));
        Label lblContent = new Label(message);
        lblContent.setWrapText(true);
        boxTop.getChildren().addAll(imgIcon, lblContent);
        Button button = new Button("Đóng");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ok = true;
                stage.close();
            }
        });
        boxRoot.getChildren().addAll(boxTop, button);
        Scene scene = new Scene(boxRoot);
        stage.setScene(scene);
        stage.showAndWait();
        return ok;
    }
}
