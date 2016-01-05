package edu.epu.realestate.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by DuongNArtist on 12/12/2015.
 */
public class MethodModel {

    private IntegerProperty methodId;
    private StringProperty methodName;

    public MethodModel() {
        this(-1, null);
    }

    private MethodModel(int methodId, String methodName) {
        this.methodId = new SimpleIntegerProperty(methodId);
        this.methodName = new SimpleStringProperty(methodName);
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

    public String getMethodName() {
        return methodName.get();
    }

    public void setMethodName(String methodName) {
        this.methodName.set(methodName);
    }

    public StringProperty methodNameProperty() {
        return methodName;
    }
}
