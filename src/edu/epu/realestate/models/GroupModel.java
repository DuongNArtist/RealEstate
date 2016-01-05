package edu.epu.realestate.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by DuongNArtist on 14/12/2015.
 */
public class GroupModel {
    private IntegerProperty groupId;
    private IntegerProperty methodId;
    private IntegerProperty categoryId;
    private IntegerProperty pageId;
    private StringProperty groupUrl;
    private StringProperty groupTitle;
    private StringProperty groupParam;

    public GroupModel() {
        this(-1, -1, -1, -1, null, null, null);
    }

    public GroupModel(int groupId, int methodId, int categoryId, int pageId, String groupUrl, String groupTitle, String groupParam) {
        this.groupId = new SimpleIntegerProperty(groupId);
        this.methodId = new SimpleIntegerProperty(methodId);
        this.categoryId = new SimpleIntegerProperty(categoryId);
        this.pageId = new SimpleIntegerProperty(pageId);
        this.groupUrl = new SimpleStringProperty(groupUrl);
        this.groupTitle = new SimpleStringProperty(groupTitle);
        this.groupParam = new SimpleStringProperty(groupParam);
    }

    public int getGroupId() {
        return groupId.get();
    }

    public void setGroupId(int groupId) {
        this.groupId.set(groupId);
    }

    public IntegerProperty groupIdProperty() {
        return groupId;
    }

    public String getGroupTitle() {
        return groupTitle.get();
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle.set(groupTitle);
    }

    public StringProperty groupTitleProperty() {
        return groupTitle;
    }

    public String getGroupUrl() {
        return groupUrl.get();
    }

    public void setGroupUrl(String groupUrl) {
        this.groupUrl.set(groupUrl);
    }

    public StringProperty groupUrlProperty() {
        return groupUrl;
    }

    public int getMethodId() {
        return methodId.get();
    }

    public void setMethodId(int methodId) {
        this.methodId.set(methodId);
    }

    public IntegerProperty methodIdProperty() {
        return methodId;
    }

    public int getCategoryId() {
        return categoryId.get();
    }

    public void setCategoryId(int categoryId) {
        this.categoryId.set(categoryId);
    }

    public IntegerProperty categoryIdProperty() {
        return categoryId;
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

    public String getGroupParam() {
        return groupParam.get();
    }

    public void setGroupParam(String groupParam) {
        this.groupParam.set(groupParam);
    }

    public StringProperty groupParamProperty() {
        return groupParam;
    }

}
