package edu.epu.realestate.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by DuongNArtist on 12/12/2015.
 */
public class PageModel {

    private IntegerProperty pageId;
    private StringProperty pageName;
    private StringProperty pageUrl;
    private StringProperty pageDescription;

    public PageModel() {
        this(-1, null, null, null);
    }

    public PageModel(int pageId, String pageName, String pageUrl, String pageDescription) {
        this.pageId = new SimpleIntegerProperty(pageId);
        this.pageName = new SimpleStringProperty(pageName);
        this.pageUrl = new SimpleStringProperty(pageUrl);
        this.pageDescription = new SimpleStringProperty(pageDescription);
    }

    public String getPageDescription() {
        return pageDescription.get();
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription.set(pageDescription);
    }

    public StringProperty pageDescriptionProperty() {
        return pageDescription;
    }

    public String getPageUrl() {
        return pageUrl.get();
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl.set(pageUrl);
    }

    public StringProperty pageUrlProperty() {
        return pageUrl;
    }

    public String getPageName() {
        return pageName.get();
    }

    public void setPageName(String pageName) {
        this.pageName.set(pageName);
    }

    public StringProperty pageNameProperty() {
        return pageName;
    }

    public int getPageId() {
        return pageId.get();
    }

    public void setPageId(int pageId) {
        this.pageId.set(pageId);
    }

    public IntegerProperty pageIdProperty() {
        return pageId;
    }
}
