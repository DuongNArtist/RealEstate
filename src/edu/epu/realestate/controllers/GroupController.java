package edu.epu.realestate.controllers;

import edu.epu.realestate.MainApplication;
import edu.epu.realestate.bundles.Strings;
import edu.epu.realestate.controllers.business.*;
import edu.epu.realestate.controllers.parsers.PropertyModelParser;
import edu.epu.realestate.controllers.parsers.PropertyURLParser;
import edu.epu.realestate.models.*;
import edu.epu.realestate.models.dialogs.ConfirmDialog;
import edu.epu.realestate.models.dialogs.MessageDialog;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class GroupController implements Initializable {

    private ObservableList<String> subUrls = FXCollections.observableArrayList();
    private ObservableList<String> mainUrls = FXCollections.observableArrayList();
    private Thread thread = null;
    private GroupModel groupModel = null;
    private boolean running = false;

    private Runnable parserRunnable = null;
    private Runnable getMainUrlsRunnable = null;
    private Runnable getSubUrlsRunnable = null;
    private Runnable stopGetSubUrlsRunnable = null;

    @FXML
    private TableView<GroupModel> tblGroups;
    @FXML
    private TableColumn fldGroupId;
    @FXML
    private TableColumn fldMethodId;
    @FXML
    private TableColumn fldCategoryId;
    @FXML
    private TableColumn fldPageId;
    @FXML
    private TableColumn fldGroupUrl;
    @FXML
    private TableColumn fldGroupTitle;
    @FXML
    private TableColumn fldGroupParam;
    @FXML
    private ComboBox<MethodModel> cmbMethod;
    @FXML
    private ComboBox<CategoryModel> cmbCategory;
    @FXML
    private ComboBox<PageModel> cmbPage;
    @FXML
    private TextField txtGroupKey;
    @FXML
    private TextField txtGroupId;
    @FXML
    private ComboBox<MethodModel> cmbMethodId;
    @FXML
    private ComboBox<CategoryModel> cmbCategoryId;
    @FXML
    private ComboBox<PageModel> cmbPageId;
    @FXML
    private TextField txtGroupUrl;
    @FXML
    private TextField txtGroupTitle;
    @FXML
    private TextField txtGroupParam;
    @FXML
    private ListView<String> lstUrls;
    @FXML
    private Button btnStopCrawl;
    @FXML
    private Button btnStartCrawl;
    @FXML
    private Button btnStartGetSubUrls;
    @FXML
    private Button btnStopGetSubUrls;
    @FXML
    private Button btnStartGetMainUrls;
    @FXML
    private Button btnStopGetMainUrls;
    @FXML
    private Button btnSaveGroup;
    @FXML
    private Button btnDeleteGroup;
    @FXML
    private ProgressBar prgProgress;
    @FXML
    private Label lblLink;
    @FXML
    private Label lblCount;

    private String prefix = "";
    private int start = 0;
    private int end = 0;
    private int step = 0;

    private int current = 0;
    private int length = 0;

    private MainApplication application;

    public GroupController() {
        parserRunnable = new Runnable() {
            @Override
            public void run() {
                current = 0;
                length = subUrls.size();
                for (String url : subUrls) {
                    if (running) {
                        if (Utilities.isValidUrl(url)) {
                            PropertyBusiness.insert(PropertyModelParser.parsePropertyModel(url
                                    , groupModel.getGroupId()));
                        } else {
                            System.out.println("Đường dẫn không hợp lệ!");
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                updateProgress(url);
                            }
                        });
                    }
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stopCrawl();
                    }
                });
            }

            private void updateProgress(String url) {
                current++;
                double progress = (double) current / length;
                prgProgress.setProgress(progress);
                lblLink.setText("[" + current + "/" + length + "] - " + url);
            }
        };

        getMainUrlsRunnable = new Runnable() {
            @Override
            public void run() {
                current = 0;
                if (running) {
                    String url = groupModel.getGroupUrl();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            updateProgress(url);
                        }
                    });
                }
                for (int i = start; i <= end; i += step) {
                    if (running) {
                        String url = String.format(prefix, i);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                updateProgress(url);
                            }
                        });
                    }
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stopGetMainUrls();
                    }
                });
            }

            private void updateProgress(String url) {
                current++;
                mainUrls.add(url);
                lblLink.setText("[" + current + "] - " + url);
                System.out.println(url);
            }
        };

        getSubUrlsRunnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mainUrls.size(); i++) {
                    if (running) {
                        PropertyURLParser.parseSubUrlsFromMainUrl(subUrls, mainUrls.get(i), lblLink);
                    }
                }
                Platform.runLater(stopGetSubUrlsRunnable);
            }
        };

        stopGetSubUrlsRunnable = new Runnable() {
            @Override
            public void run() {
                if (thread != null) {
                    boolean retry = true;
                    while (retry) {
                        running = false;
                        try {
                            thread.join();
                            retry = false;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    thread = null;
                    btnStopGetSubUrls.setVisible(false);
                    btnStartCrawl.setVisible(true);
                }
            }
        };
    }

    public MainApplication getApplication() {
        return application;
    }

    public void setApplication(MainApplication application) {
        this.application = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fldGroupId.setCellValueFactory(new PropertyValueFactory<MethodModel, Integer>("groupId"));
        fldMethodId.setCellValueFactory(new PropertyValueFactory<MethodModel, Integer>("methodId"));
        fldCategoryId.setCellValueFactory(new PropertyValueFactory<MethodModel, Integer>("categoryId"));
        fldPageId.setCellValueFactory(new PropertyValueFactory<MethodModel, Integer>("pageId"));
        fldGroupUrl.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("groupUrl"));
        fldGroupTitle.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("groupTitle"));
        fldGroupParam.setCellValueFactory(new PropertyValueFactory<MethodModel, String>("groupParam"));
        tblGroups.setItems(GroupBusiness.select(""));
        count();
        txtGroupKey.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    tblGroups.setItems(GroupBusiness.select(txtGroupKey.getText().trim()));
                    count();
                }
            }
        });
        intiMethods();
        initCategories();
        initPages();
        btnStartCrawl.setVisible(false);
        btnStopCrawl.setVisible(false);
        btnStopGetMainUrls.setVisible(false);
        btnStartGetSubUrls.setVisible(false);
        btnStopGetSubUrls.setVisible(false);
    }


    private void intiMethods() {
        cmbMethodId.setItems(MethodBusiness.select(""));
        cmbMethodId.setConverter(new StringConverter<MethodModel>() {
            @Override
            public String toString(MethodModel model) {
                return model.getMethodName();
            }

            @Override
            public MethodModel fromString(String string) {
                return null;
            }
        });
        cmbMethod.setItems(cmbMethodId.getItems());
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
        cmbCategoryId.setItems(CategoryBusiness.select(""));
        cmbCategoryId.setConverter(new StringConverter<CategoryModel>() {
            @Override
            public String toString(CategoryModel model) {
                return model.getCategoryName();
            }

            @Override
            public CategoryModel fromString(String string) {
                return null;
            }
        });
        cmbCategory.setItems(cmbCategoryId.getItems());
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
        cmbPageId.setItems(PageBusiness.select(""));
        cmbPageId.setConverter(new StringConverter<PageModel>() {
            @Override
            public String toString(PageModel model) {
                return model.getPageName();
            }

            @Override
            public PageModel fromString(String string) {
                return null;
            }
        });
        cmbPage.setItems(cmbPageId.getItems());
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
        groupModel = tblGroups.getSelectionModel().getSelectedItem();
        if (groupModel != null) {
            txtGroupId.setText(groupModel.getGroupId() + "");
            for (MethodModel aModel : cmbMethodId.getItems()) {
                if (aModel.getMethodId() == groupModel.getMethodId()) {
                    cmbMethodId.setValue(aModel);
                }
            }
            for (CategoryModel aModel : cmbCategoryId.getItems()) {
                if (aModel.getCategoryId() == groupModel.getCategoryId()) {
                    cmbCategoryId.setValue(aModel);
                    break;
                }
            }
            for (PageModel aModel : cmbPageId.getItems()) {
                if (aModel.getPageId() == groupModel.getPageId()) {
                    cmbPageId.setValue(aModel);
                    break;
                }
            }
            txtGroupUrl.setText(groupModel.getGroupUrl());
            txtGroupTitle.setText(groupModel.getGroupTitle());
            txtGroupParam.setText(groupModel.getGroupParam());
        }
    }

    @FXML
    private void newGroup() {
        groupModel = null;
        txtGroupId.clear();
        txtGroupUrl.clear();
        txtGroupTitle.clear();
        txtGroupParam.clear();
    }

    @FXML
    private void saveGroup() {
        PageModel pageModel = cmbPageId.getSelectionModel().getSelectedItem();
        MethodModel methodModel = cmbMethodId.getSelectionModel().getSelectedItem();
        CategoryModel categoryModel = cmbCategoryId.getSelectionModel().getSelectedItem();
        String myTitle = methodModel.getMethodName() + " - " + categoryModel.getCategoryName() + " - " + pageModel.getPageName();
        txtGroupTitle.setText(myTitle);
        String url = txtGroupUrl.getText();
//        try {
//            Document document = Jsoup.connect(url).get();
//            txtGroupTitle.setText(document.title());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String title = txtGroupTitle.getText();
        String groupPage = txtGroupParam.getText();
        int methodId = cmbMethodId.getValue().getMethodId();
        int categoryId = cmbCategoryId.getValue().getCategoryId();
        int pageId = cmbPageId.getValue().getPageId();
        if (url.length() > 0) {
            int updated = 0;
            if (groupModel != null) {
                groupModel = new GroupModel(groupModel.getGroupId(), methodId, categoryId, pageId, url, title, groupPage);
                updated = GroupBusiness.update(groupModel);
            } else {
                groupModel = new GroupModel(-1, methodId, categoryId, pageId, url, title, groupPage);
                updated = GroupBusiness.insert(groupModel);
            }
            showResult(updated);
        } else {
            MessageDialog.show(application.getStage(), MessageDialog.WARNING, Strings.title_warning, Strings.content_invalid_input);
        }
    }

    @FXML
    private void deleteGroup() {
        if (groupModel != null) {
            if (ConfirmDialog.show(application.getStage(), Strings.title_confirm, Strings.record_confirm_delete)) {
                showResult(GroupBusiness.delete(groupModel));
            }
        }
        newGroup();
    }

    private void showResult(int result) {
        if (result > 0) {
            tblGroups.setItems(GroupBusiness.select(txtGroupKey.getText().trim()));
            count();
            MessageDialog.show(application.getStage(), MessageDialog.INFO, Strings.title_info, Strings.database_succeed);
        } else {
            MessageDialog.show(application.getStage(), MessageDialog.ERROR, Strings.title_error, Strings.database_failed);
        }
    }

    @FXML
    private void startGetSubUrls() {
        if (mainUrls.size() > 0) {
            subUrls.clear();
            lstUrls.setItems(subUrls);
            running = true;
            thread = new Thread(getSubUrlsRunnable);
            thread.start();
            btnStartGetSubUrls.setVisible(false);
            btnStopGetSubUrls.setVisible(true);
        }
    }

    @FXML
    private void stopGetSubUrls() {
        stopGetSubUrlsRunnable.run();
    }

    @FXML
    private void startGetMainUrls() {
        if (groupModel != null) {
            String[] params = groupModel.getGroupParam().split(" ");
            if (params.length == 4) {
                try {
                    prefix = params[0];
                    start = Integer.parseInt(params[1]);
                    end = Integer.parseInt(params[2]);
                    step = Integer.parseInt(params[3]);
                    System.out.println(prefix);
                    System.out.println(start);
                    System.out.println(end);
                    prgProgress.setProgress(0);
                    lblLink.setText("");
                    mainUrls.clear();
                    lstUrls.setItems(mainUrls);
                    running = true;
                    thread = new Thread(getMainUrlsRunnable);
                    thread.start();
                    btnStartGetMainUrls.setVisible(false);
                    btnStopGetMainUrls.setVisible(true);
                } catch (NumberFormatException e) {

                }
            }
        }
    }

    @FXML
    private void stopGetMainUrls() {
        if (thread != null) {
            boolean retry = true;
            while (retry) {
                running = false;
                try {
                    thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            thread = null;
            btnStopGetMainUrls.setVisible(false);
            btnStartGetSubUrls.setVisible(true);
        }
    }

    @FXML
    private void startCrawl() {
        if (subUrls.size() > 0) {
            running = true;
            thread = new Thread(parserRunnable);
            thread.start();
            btnStartCrawl.setVisible(false);
            btnStopCrawl.setVisible(true);
        }
    }

    @FXML
    private void stopCrawl() {
        if (thread != null) {
            boolean retry = true;
            while (retry) {
                running = false;
                try {
                    thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            thread = null;
            btnStopCrawl.setVisible(false);
            btnStartGetMainUrls.setVisible(true);
        }
    }

    @FXML
    private void filterGroup() {
        MethodModel methodModel = cmbMethod.getSelectionModel().getSelectedItem();
        CategoryModel categoryModel = cmbCategory.getSelectionModel().getSelectedItem();
        PageModel pageModel = cmbPage.getSelectionModel().getSelectedItem();
        if (methodModel != null && categoryModel != null && pageModel != null) {
            tblGroups.setItems(GroupBusiness.filter(methodModel.getMethodId(), categoryModel.getCategoryId(), pageModel.getPageId()));
            count();
        }
        System.out.println("selected");
    }


    private void count() {
        lblCount.setText("được " + tblGroups.getItems().size() + " bản ghi");
    }
}
