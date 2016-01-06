package edu.epu.realestate.controllers;

import edu.epu.realestate.MainApplication;
import edu.epu.realestate.bundles.Strings;
import edu.epu.realestate.models.dialogs.ConfirmDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by DuongNArtist on 11/12/2015.
 */
public class MainController implements Initializable {

    @FXML
    private BorderPane panRoot;
    @FXML
    private Button btnSignOut;
    @FXML
    private Button btnShowManager;
    @FXML
    private Button btnShowGroup;
    @FXML
    private Button btnShowProperty;
    @FXML
    private HBox panMenu;
    private MainApplication application;

    public MainController() {

    }

    public MainApplication getApplication() {
        return application;
    }

    public void setApplication(MainApplication application) {
        this.application = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signIn();
        btnShowManager.setGraphic(new ImageView(new Image("file:res/images/manager.png")));
        btnShowGroup.setGraphic(new ImageView(new Image("file:res/images/group.png")));
        btnShowProperty.setGraphic(new ImageView(new Image("file:res/images/property.png")));
        btnSignOut.setGraphic(new ImageView(new Image("file:res/images/signout.png")));
    }

    public void signIn() {
        panMenu.setVisible(false);
        try {
            URL url = MainApplication.class.getResource("views/user_view.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            VBox pane = loader.load();
            panRoot.setCenter(pane);
            UserController controller = loader.getController();
            controller.setApplication(application);
            controller.setMainController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showManager() {
        btnShowManager.requestFocus();
        try {
            URL url = MainApplication.class.getResource("views/manager_view.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            HBox pane = loader.load();
            panRoot.setCenter(pane);
            ManagerController controller = loader.getController();
            controller.setApplication(application);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void showGroup() {
        btnShowGroup.requestFocus();
        try {
            URL url = MainApplication.class.getResource("views/group_view.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            VBox pane = loader.load();
            panRoot.setCenter(pane);
            GroupController controller = loader.getController();
            controller.setApplication(application);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showProperty() {
        btnShowProperty.requestFocus();
        try {
            URL url = MainApplication.class.getResource("views/property_view.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            VBox pane = loader.load();
            panRoot.setCenter(pane);
            PropertyController controller = loader.getController();
            controller.setApplication(application);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void signOut() {
        if (ConfirmDialog.show(application.getStage(), Strings.confirm_signout_title, Strings.confirm_signout_message)) {
            signIn();
        }
    }

    public HBox getPanMenu() {
        return panMenu;
    }

    public void setPanMenu(HBox panMenu) {
        this.panMenu = panMenu;
    }
}
