package edu.epu.realestate.controllers;

import edu.epu.realestate.MainApplication;
import edu.epu.realestate.bundles.Strings;
import edu.epu.realestate.controllers.business.*;
import edu.epu.realestate.controllers.parsers.PropertyModelParser;
import edu.epu.realestate.models.*;
import edu.epu.realestate.models.dialogs.ConfirmDialog;
import edu.epu.realestate.models.dialogs.MessageDialog;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by DuongNArtist on 12/12/2015.
 */
public class PropertyController implements Initializable {

    @FXML
    private TableView<PropertyModel> tblProperties;
    @FXML
    private TableColumn fldPropertyId;
    @FXML
    private TableColumn fldGroupId;
    @FXML
    private TableColumn fldPropertyUrl;
    @FXML
    private TableColumn fldPropertyTitle;
    @FXML
    private TableColumn fldPropertySquare;
    @FXML
    private TableColumn fldPropertyPrice;
    @FXML
    private TableColumn fldPropertyProject;
    @FXML
    private TableColumn fldPropertyAddress;
    @FXML
    private TableColumn fldPropertyDescription;
    @FXML
    private TableColumn fldPropertyContact;
    @FXML
    private TableColumn fldPropertyMobile;
    @FXML
    private TableColumn fldPropertyEmail;
    @FXML
    private TextField txtPropertyKey;
    private PropertyModel propertyModel = null;
    @FXML
    private TextField txtPropertyId;
    @FXML
    private ComboBox<GroupModel> cmbGroupId;
    @FXML
    private TextField txtPropertyUrl;
    @FXML
    private TextField txtPropertyTitle;
    @FXML
    private TextField txtPropertySquare;
    @FXML
    private TextField txtPropertyPrice;
    @FXML
    private TextField txtPropertyProject;
    @FXML
    private TextField txtPropertyAddress;
    @FXML
    private TextArea txtPropertyDescription;
    @FXML
    private TextField txtPropertyContact;
    @FXML
    private TextField txtPropertyMobile;
    @FXML
    private TextField txtPropertyEmail;
    @FXML
    private ComboBox<GroupModel> cmbGroup;
    @FXML
    private ComboBox<MethodModel> cmbMethod;
    @FXML
    private ComboBox<CategoryModel> cmbCategory;
    @FXML
    private ComboBox<PageModel> cmbPage;
    @FXML
    private Label lblCount;

    private MainApplication application;

    public PropertyController() {

    }

    public MainApplication getApplication() {
        return application;
    }

    public void setApplication(MainApplication application) {
        this.application = application;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGroups();
        initMethods();
        initCategories();
        initPages();
        fldPropertyId.setCellValueFactory(new PropertyValueFactory<MethodModel, Integer>("propertyId"));
        fldGroupId.setCellValueFactory(new PropertyValueFactory<MethodModel, Integer>("groupId"));
        fldPropertyUrl.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("propertyUrl"));
        fldPropertyTitle.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("propertyTitle"));
        fldPropertySquare.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("propertySquare"));
        fldPropertyPrice.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("propertyPrice"));
        fldPropertyProject.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("propertyProject"));
        fldPropertyAddress.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("propertyAddress"));
        fldPropertyDescription.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("propertyDescription"));
        fldPropertyContact.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("propertyContact"));
        fldPropertyMobile.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("propertyMobile"));
        fldPropertyEmail.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("propertyEmail"));
        tblProperties.setItems(PropertyBusiness.select(txtPropertyKey.getText().trim()));
        count();
        txtPropertyKey.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    tblProperties.setItems(PropertyBusiness.select(txtPropertyKey.getText().trim()));
                    count();
                }
            }
        });
    }

    private void initGroups() {
        cmbGroupId.setItems(GroupBusiness.select(""));
        cmbGroupId.setConverter(new StringConverter<GroupModel>() {
            @Override
            public String toString(GroupModel model) {
                return model.getGroupTitle();
            }

            @Override
            public GroupModel fromString(String string) {
                return null;
            }
        });
        cmbGroup.setItems(cmbGroupId.getItems());
        cmbGroup.setConverter(new StringConverter<GroupModel>() {
            @Override
            public String toString(GroupModel model) {
                return model.getGroupTitle();
            }

            @Override
            public GroupModel fromString(String string) {
                return null;
            }
        });
    }

    private void initMethods() {
        cmbMethod.setItems(MethodBusiness.select(""));
        cmbMethod.setConverter(new StringConverter<MethodModel>() {
            @Override
            public String toString(MethodModel model) {
                return model.getMethodName();
            }

            @Override
            public MethodModel fromString(String string) {
                return null;
            }
        });
    }

    private void initCategories() {
        cmbCategory.setItems(CategoryBusiness.select(""));
        cmbCategory.setConverter(new StringConverter<CategoryModel>() {
            @Override
            public String toString(CategoryModel model) {
                return model.getCategoryName();
            }

            @Override
            public CategoryModel fromString(String string) {
                return null;
            }
        });
    }

    private void initPages() {
        cmbPage.setItems(PageBusiness.select(""));
        cmbPage.setConverter(new StringConverter<PageModel>() {
            @Override
            public String toString(PageModel model) {
                return model.getPageName();
            }

            @Override
            public PageModel fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void clickMouse() {
        propertyModel = tblProperties.getSelectionModel().getSelectedItem();
        if (propertyModel != null) {
            txtPropertyId.setText(propertyModel.getPropertyId() + "");
            for (GroupModel aModel : cmbGroupId.getItems()) {
                if (aModel.getGroupId() == propertyModel.getGroupId()) {
                    cmbGroupId.setValue(aModel);
                    break;
                }
            }
            txtPropertyUrl.setText(propertyModel.getPropertyUrl());
            txtPropertyTitle.setText(propertyModel.getPropertyTitle());
            txtPropertySquare.setText(propertyModel.getPropertySquare());
            txtPropertyPrice.setText(propertyModel.getPropertyPrice());
            txtPropertyProject.setText(propertyModel.getPropertyProject());
            txtPropertyAddress.setText(propertyModel.getPropertyAddress());
            txtPropertyDescription.setText(propertyModel.getPropertyDescription());
            txtPropertyContact.setText(propertyModel.getPropertyContact());
            txtPropertyMobile.setText(propertyModel.getPropertyMobile());
            txtPropertyEmail.setText(propertyModel.getPropertyEmail());
        }
        txtPropertyUrl.requestFocus();
    }

    @FXML
    private void crawlProperty() {
        if (propertyModel != null) {
            for (GroupModel model : cmbGroupId.getItems()) {
                if (model.getGroupId() == propertyModel.getGroupId()) {
                    int propertyId = propertyModel.getPropertyId();
                    propertyModel = PropertyModelParser.parsePropertyModel(propertyModel.getPropertyUrl(), propertyModel.getGroupId());
                    propertyModel.setPropertyId(propertyId);
                    txtPropertyUrl.setText(propertyModel.getPropertyUrl());
                    txtPropertyTitle.setText(propertyModel.getPropertyTitle());
                    txtPropertySquare.setText(propertyModel.getPropertySquare());
                    txtPropertyPrice.setText(propertyModel.getPropertyPrice());
                    txtPropertyProject.setText(propertyModel.getPropertyProject());
                    txtPropertyAddress.setText(propertyModel.getPropertyAddress());
                    txtPropertyDescription.setText(propertyModel.getPropertyDescription());
                    txtPropertyContact.setText(propertyModel.getPropertyContact());
                    txtPropertyMobile.setText(propertyModel.getPropertyMobile());
                    txtPropertyEmail.setText(propertyModel.getPropertyEmail());
                }
            }
        }
    }


    @FXML
    private void newProperty() {
        propertyModel = null;
        txtPropertyId.clear();
        txtPropertyUrl.clear();
        txtPropertyTitle.clear();
        txtPropertySquare.clear();
        txtPropertyPrice.clear();
        txtPropertyProject.clear();
        txtPropertyAddress.clear();
        txtPropertyDescription.clear();
        txtPropertyContact.clear();
        txtPropertyMobile.clear();
        txtPropertyEmail.clear();
        txtPropertyUrl.requestFocus();
    }

    @FXML
    private void saveProperty() {
        String propertyUrl = txtPropertyUrl.getText();
        int groupId = cmbGroupId.getValue().getGroupId();
        String propertyTitle = txtPropertyTitle.getText();
        String propertyPrice = txtPropertyPrice.getText();
        String propertySquare = txtPropertySquare.getText();
        String propertyProject = txtPropertyProject.getText();
        String propertyAddress = txtPropertyAddress.getText();
        String propertyDescription = txtPropertyDescription.getText();
        String propertyContact = txtPropertyContact.getText();
        String propertyMobile = txtPropertyMobile.getText();
        String propertyEmail = txtPropertyEmail.getText();
        if (propertyUrl.length() > 0) {
            int updated = 0;
            if (propertyModel != null) {
                propertyModel = new PropertyModel(propertyModel.getPropertyId(), groupId, propertyUrl, propertyTitle, propertyPrice, propertySquare, propertyProject, propertyAddress, propertyDescription, propertyContact, propertyMobile, propertyEmail);
                updated = PropertyBusiness.update(propertyModel);
            } else {
                propertyModel = new PropertyModel(-1, groupId, propertyUrl, propertyTitle, propertyPrice, propertySquare, propertyProject, propertyAddress, propertyDescription, propertyContact, propertyMobile, propertyEmail);
                updated = PropertyBusiness.insert(propertyModel);
            }
            showResult(updated);
        } else {
            MessageDialog.show(application.getStage(), MessageDialog.WARNING, Strings.title_warning, Strings.content_invalid_input);
        }
    }

    @FXML
    private void deleteProperty() {
        if (propertyModel != null) {
            if (ConfirmDialog.show(application.getStage(), Strings.title_confirm, Strings.record_confirm_delete)) {
                showResult(PropertyBusiness.delete(propertyModel));
            }
        }
        newProperty();
    }

    private void showResult(int result) {
        if (result > 0) {
            tblProperties.setItems(PropertyBusiness.select(txtPropertyKey.getText().trim()));
            count();
            MessageDialog.show(application.getStage(), MessageDialog.INFO, Strings.title_info, Strings.database_succeed);
        } else {
            MessageDialog.show(application.getStage(), MessageDialog.ERROR, Strings.title_error, Strings.database_failed);
        }
    }

    @FXML
    private void filterProperty() {
        System.out.println("selected");
        MethodModel methodModel = cmbMethod.getSelectionModel().getSelectedItem();
        CategoryModel categoryModel = cmbCategory.getSelectionModel().getSelectedItem();
        PageModel pageModel = cmbPage.getSelectionModel().getSelectedItem();
        if (methodModel != null && categoryModel != null && pageModel != null) {
            tblProperties.setItems(PropertyBusiness.filter(methodModel.getMethodId(), categoryModel.getCategoryId(), pageModel.getPageId()));
            count();
        }
    }

    @FXML
    private void filterByGroup() {
        System.out.println("selected");
        GroupModel groupModel = cmbGroup.getSelectionModel().getSelectedItem();
        if (groupModel != null) {
            tblProperties.setItems(PropertyBusiness.filterByGroup(groupModel.getGroupId()));
            count();
        }
    }

    private void count() {
        lblCount.setText("được " + tblProperties.getItems().size() + " kết quả");
    }
}
