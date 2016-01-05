package edu.epu.realestate.controllers;

import edu.epu.realestate.MainApplication;
import edu.epu.realestate.bundles.Strings;
import edu.epu.realestate.controllers.business.CategoryBusiness;
import edu.epu.realestate.controllers.business.MethodBusiness;
import edu.epu.realestate.controllers.business.PageBusiness;
import edu.epu.realestate.models.CategoryModel;
import edu.epu.realestate.models.MethodModel;
import edu.epu.realestate.models.PageModel;
import edu.epu.realestate.models.dialogs.ConfirmDialog;
import edu.epu.realestate.models.dialogs.MessageDialog;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by DuongNArtist on 17/12/2015.
 */
public class ManagerController implements Initializable {

    public static final int METHOD = 0;
    public static final int CATEGORY = 1;
    public static final int PAGE = 2;

    private MainApplication application;
    // Method Manager
    @FXML
    private TextField txtMethodName;
    @FXML
    private ListView<MethodModel> lstMethods;
    private MethodModel methodModel = null;
    // Category Manager
    @FXML
    private TextField txtCategoryName;
    @FXML
    private ListView<CategoryModel> lstCategories;
    private CategoryModel categoryModel = null;
    // Page Manager
    @FXML
    private TableColumn fldPageName;
    @FXML
    private TableColumn fldPageUrl;
    @FXML
    private TableColumn fldPageDescription;
    @FXML
    private TextField txtPageKey;
    @FXML
    private TextField txtPageName;
    @FXML
    private TextField txtPageUrl;
    @FXML
    private TextArea txtPageDescription;
    @FXML
    private TableView<PageModel> tblPages;
    private PageModel pageModel = null;

    public MainApplication getApplication() {
        return application;
    }

    public void setApplication(MainApplication application) {
        this.application = application;
    }

    @FXML
    private void newMethod() {
        methodModel = null;
        txtMethodName.clear();
        txtMethodName.requestFocus();
    }

    @FXML
    private void saveMethod() {
        String name = txtMethodName.getText().trim();
        if (name.length() > 0) {
            int updated = 0;
            if (methodModel != null) {
                methodModel.setMethodName(name);
                updated = MethodBusiness.update(methodModel);
            } else {
                methodModel = new MethodModel();
                methodModel.setMethodName(name);
                updated = MethodBusiness.insert(methodModel);
            }
            showResult(updated, METHOD);
        } else {
            MessageDialog.show(application.getStage(), MessageDialog.WARNING, Strings.title_warning, Strings.content_invalid_input);
        }
        newMethod();
    }

    @FXML
    private void deleteMethod() {
        if (methodModel != null) {
            if (ConfirmDialog.show(application.getStage(), Strings.title_confirm, Strings.record_confirm_delete)) {
                showResult(MethodBusiness.delete(methodModel), METHOD);
            }
        }
        newMethod();
    }

    @FXML
    private void newCategory() {
        categoryModel = null;
        txtCategoryName.clear();
        txtCategoryName.requestFocus();
    }

    @FXML
    private void saveCategory() {
        String name = txtCategoryName.getText().trim();
        if (name.length() > 0) {
            int updated = 0;
            if (categoryModel != null) {
                categoryModel.setCategoryName(name);
                updated = CategoryBusiness.update(categoryModel);
            } else {
                categoryModel = new CategoryModel();
                categoryModel.setCategoryName(name);
                updated = CategoryBusiness.insert(categoryModel);
            }
            showResult(updated, CATEGORY);
        } else {
            MessageDialog.show(application.getStage(), MessageDialog.WARNING, Strings.title_warning, Strings.content_invalid_input);
        }
        newCategory();
    }

    @FXML
    private void deleteCategory() {
        if (categoryModel != null) {
            if (ConfirmDialog.show(application.getStage(), Strings.title_confirm, Strings.record_confirm_delete)) {
                showResult(CategoryBusiness.delete(categoryModel), CATEGORY);
            }
        }
        newCategory();
    }

    @FXML
    private void newPage() {
        methodModel = null;
        txtPageName.clear();
        txtPageUrl.clear();
        txtPageDescription.clear();
        txtPageName.requestFocus();
    }

    @FXML
    private void savePage() {
        String pageName = txtPageName.getText();
        String pageUrl = txtPageUrl.getText();
        String pageDescription = txtPageDescription.getText().trim();
        if (pageName.length() > 0 && pageUrl.length() > 0 && pageDescription.length() > 0) {
            int updated = 0;
            if (pageModel != null) {
                pageModel = new PageModel(pageModel.getPageId(), pageName, pageUrl, pageDescription);
                updated = PageBusiness.update(pageModel);
            } else {
                pageModel = new PageModel(-1, pageName, pageUrl, pageDescription);
                updated = PageBusiness.insert(pageModel);
            }
            showResult(updated, PAGE);
        } else {
            MessageDialog.show(application.getStage(), MessageDialog.WARNING, Strings.title_warning, Strings.content_invalid_input);
        }
        newPage();
    }

    @FXML
    private void deletePage() {
        if (pageModel != null) {
            if (ConfirmDialog.show(application.getStage(), Strings.title_confirm, Strings.record_confirm_delete)) {
                showResult(PageBusiness.delete(pageModel), PAGE);
            }
        }
        newPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMethods();
        initCategories();
        initPages();
    }

    private void initPages() {
        fldPageName.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("pageName"));
        fldPageUrl.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("pageUrl"));
        fldPageDescription.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("pageDescription"));
        tblPages.setItems(PageBusiness.select(txtPageKey.getText().trim()));
        tblPages.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pageModel = tblPages.getSelectionModel().getSelectedItem();
                if (pageModel != null) {
                    txtPageName.setText(pageModel.getPageName());
                    txtPageUrl.setText(pageModel.getPageUrl());
                    txtPageDescription.setText(pageModel.getPageDescription());
                }
            }
        });
        txtPageKey.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                PageBusiness.select(newValue.trim());
            }
        });
    }


    private void initMethods() {
        lstMethods.setItems(MethodBusiness.select(""));
        lstMethods.setCellFactory(new Callback<ListView<MethodModel>, ListCell<MethodModel>>() {

            @Override
            public ListCell<MethodModel> call(ListView<MethodModel> p) {
                ListCell<MethodModel> cell = new ListCell<MethodModel>() {

                    @Override
                    protected void updateItem(MethodModel model, boolean bln) {
                        super.updateItem(model, bln);
                        if (model != null) {
                            setText(model.getMethodName());
                        }
                    }
                };
                return cell;
            }
        });
        lstMethods.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                methodModel = lstMethods.getSelectionModel().getSelectedItem();
                if (methodModel != null) {
                    txtMethodName.setText(methodModel.getMethodName());
                }
            }
        });
    }


    private void initCategories() {
        lstCategories.setItems(CategoryBusiness.select(""));
        lstCategories.setCellFactory(new Callback<ListView<CategoryModel>, ListCell<CategoryModel>>() {

            @Override
            public ListCell<CategoryModel> call(ListView<CategoryModel> p) {
                ListCell<CategoryModel> cell = new ListCell<CategoryModel>() {

                    @Override
                    protected void updateItem(CategoryModel model, boolean bln) {
                        super.updateItem(model, bln);
                        if (model != null) {
                            setText(model.getCategoryName());
                        }
                    }
                };
                return cell;
            }
        });
        lstCategories.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                categoryModel = lstCategories.getSelectionModel().getSelectedItem();
                if (categoryModel != null) {
                    txtCategoryName.setText(categoryModel.getCategoryName());
                }
            }
        });
    }

    private void showResult(int result, int source) {
        if (result > 0) {
            switch (source) {
                case METHOD:
                    initMethods();
                    break;
                case CATEGORY:
                    initCategories();
                    break;
                case PAGE:
                    tblPages.setItems(PageBusiness.select(txtPageKey.getText().trim()));
                    break;
                default:
                    break;
            }
            MessageDialog.show(application.getStage(), MessageDialog.INFO, Strings.title_info, Strings.database_succeed);
        } else {
            MessageDialog.show(application.getStage(), MessageDialog.ERROR, Strings.title_error, Strings.database_failed);
        }
    }
}
