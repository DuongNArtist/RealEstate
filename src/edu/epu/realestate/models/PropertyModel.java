package edu.epu.realestate.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by DuongNArtist on 14/12/2015.
 */
public class PropertyModel {
    private IntegerProperty propertyId;
    private IntegerProperty groupId;
    private StringProperty propertyUrl;
    private StringProperty propertyTitle;
    private StringProperty propertySquare;
    private StringProperty propertyPrice;
    private StringProperty propertyProject;
    private StringProperty propertyAddress;
    private StringProperty propertyDescription;
    private StringProperty propertyContact;
    private StringProperty propertyMobile;
    private StringProperty propertyEmail;

    public PropertyModel() {
        this(-1, -1, null, null, null, null, null, null, null, null, null, null);
    }

    public PropertyModel(int propertyId, int groupId, String propertyUrl, String propertyTitle, String propertyPrice, String propertySquare, String propertyProject, String propertyAddress, String propertyDescription, String propertyContact, String propertyMobile, String propertyEmail) {
        this.propertyId = new SimpleIntegerProperty(propertyId);
        this.groupId = new SimpleIntegerProperty(groupId);
        this.propertyUrl = new SimpleStringProperty(propertyUrl);
        this.propertyTitle = new SimpleStringProperty(propertyTitle);
        this.propertyPrice = new SimpleStringProperty(propertyPrice);
        this.propertySquare = new SimpleStringProperty(propertySquare);
        this.propertyProject = new SimpleStringProperty(propertyProject);
        this.propertyAddress = new SimpleStringProperty(propertyAddress);
        this.propertyDescription = new SimpleStringProperty(propertyDescription);
        this.propertyContact = new SimpleStringProperty(propertyContact);
        this.propertyMobile = new SimpleStringProperty(propertyMobile);
        this.propertyEmail = new SimpleStringProperty(propertyEmail);
    }

    public int getPropertyId() {
        return propertyId.get();
    }

    public void setPropertyId(int propertyId) {
        this.propertyId.set(propertyId);
    }

    public IntegerProperty propertyIdProperty() {
        return propertyId;
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

    public String getPropertyUrl() {
        return propertyUrl.get();
    }

    public void setPropertyUrl(String propertyUrl) {
        this.propertyUrl.set(propertyUrl);
    }

    public StringProperty propertyUrlProperty() {
        return propertyUrl;
    }

    public String getPropertyTitle() {
        return propertyTitle.get();
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle.set(propertyTitle);
    }

    public StringProperty propertyTitleProperty() {
        return propertyTitle;
    }

    public String getPropertySquare() {
        return propertySquare.get();
    }

    public void setPropertySquare(String propertySquare) {
        this.propertySquare.set(propertySquare);
    }

    public StringProperty propertySquareProperty() {
        return propertySquare;
    }

    public String getPropertyPrice() {
        return propertyPrice.get();
    }

    public void setPropertyPrice(String propertyPrice) {
        this.propertyPrice.set(propertyPrice);
    }

    public StringProperty propertyPriceProperty() {
        return propertyPrice;
    }

    public String getPropertyAddress() {
        return propertyAddress.get();
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress.set(propertyAddress);
    }

    public StringProperty propertyAddressProperty() {
        return propertyAddress;
    }

    public String getPropertyDescription() {
        return propertyDescription.get();
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription.set(propertyDescription);
    }

    public StringProperty propertyDescriptionProperty() {
        return propertyDescription;
    }

    public String getPropertyContact() {
        return propertyContact.get();
    }

    public void setPropertyContact(String propertyContact) {
        this.propertyContact.set(propertyContact);
    }

    public StringProperty propertyContactProperty() {
        return propertyContact;
    }

    public String getPropertyMobile() {
        return propertyMobile.get();
    }

    public void setPropertyMobile(String propertyMobile) {
        this.propertyMobile.set(propertyMobile);
    }

    public StringProperty propertyMobileProperty() {
        return propertyMobile;
    }

    public String getPropertyEmail() {
        return propertyEmail.get();
    }

    public void setPropertyEmail(String propertyEmail) {
        this.propertyEmail.set(propertyEmail);
    }

    public StringProperty propertyEmailProperty() {
        return propertyEmail;
    }

    public String getPropertyProject() {
        return propertyProject.get();
    }

    public void setPropertyProject(String propertyProject) {
        this.propertyProject.set(propertyProject);
    }

    public StringProperty propertyProjectProperty() {
        return propertyProject;
    }
}
