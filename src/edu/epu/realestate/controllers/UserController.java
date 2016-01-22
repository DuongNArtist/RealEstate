package edu.epu.realestate.controllers;

import edu.epu.realestate.MainApplication;
import edu.epu.realestate.bundles.Strings;
import edu.epu.realestate.controllers.business.UserBusiness;
import edu.epu.realestate.models.dialogs.ConfirmDialog;
import edu.epu.realestate.models.dialogs.MessageDialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by DuongNArtist on 11/12/2015.
 */
public class UserController implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    private MainApplication application;
    private MainController mainController;

    public UserController() {

    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public MainApplication getApplication() {
        return application;
    }

    public void setApplication(MainApplication application) {
        this.application = application;
    }


    @FXML
    private void loginSystem() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        if (username.length() > 3 && password.length() > 3) {
            if (UserBusiness.select(username, password).size() > 0) {
                mainController.getPanMenu().setVisible(true);
                mainController.showGroup();
            } else {
                MessageDialog.show(mainController.getApplication().getStage(), MessageDialog.WARNING, Strings.title_warning, Strings.invalid_user);
            }
        } else {
            MessageDialog.show(mainController.getApplication().getStage(), MessageDialog.WARNING, Strings.title_warning, Strings.invalid_input);
        }
    }


    @FXML
    private void exitApplication() {
        if (ConfirmDialog.show(mainController.getApplication().getStage(), Strings.confirm_exit_title, Strings.confirm_exit_message)) {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
