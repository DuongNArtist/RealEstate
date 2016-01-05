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
public class ConfirmDialog {

    private static boolean ok = false;

    public static boolean show(Stage context, String title, String message) {
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
        ImageView imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setImage(new Image("file:res/images/help.png"));
        Label label = new Label(message);
        boxTop.getChildren().addAll(imageView, label);
        Button btnOk = new Button("Đồng ý");
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ok = true;
                stage.close();
            }
        });
        Button btnCancel = new Button("Hủy bỏ");
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ok = false;
                stage.close();
            }
        });
        HBox boxBottom = new HBox(10);
        boxBottom.setAlignment(Pos.CENTER);
        boxBottom.getChildren().addAll(btnOk, btnCancel);
        boxRoot.getChildren().addAll(boxTop, boxBottom);
        Scene scene = new Scene(boxRoot);
        stage.setScene(scene);
        stage.showAndWait();
        return ok;
    }
}
