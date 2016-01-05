package edu.epu.realestate.controllers.business;

import edu.epu.realestate.models.PropertyModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DuongNArtist on 17/12/2015.
 */
public class PropertyBusiness {
    private static Connection connection = null;

    public static ObservableList<PropertyModel> select(String key) {
        ObservableList<PropertyModel> models = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{"%" + key + "%"};
            ResultSet resultSet = DatabaseConnector.getCallableStatement(connection, "func_select_properties", params).executeQuery();
            while (resultSet.next()) {
                PropertyModel model = new PropertyModel();
                model.setPropertyId(resultSet.getInt("fld_property_id"));
                model.setGroupId(resultSet.getInt("fld_group_id"));
                model.setPropertyUrl(resultSet.getString("fld_property_url"));
                model.setPropertyTitle(resultSet.getString("fld_property_title"));
                model.setPropertySquare(resultSet.getString("fld_property_square"));
                model.setPropertyPrice(resultSet.getString("fld_property_price"));
                model.setPropertyProject(resultSet.getString("fld_property_project"));
                model.setPropertyAddress(resultSet.getString("fld_property_address"));
                model.setPropertyDescription(resultSet.getString("fld_property_description"));
                model.setPropertyContact(resultSet.getString("fld_property_contact"));
                model.setPropertyMobile(resultSet.getString("fld_property_mobile"));
                model.setPropertyEmail(resultSet.getString("fld_property_email"));
                models.add(model);
            }
            DatabaseConnector.closeResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return models;
    }

    public static ObservableList<PropertyModel> filterByGroup(int groupId) {
        ObservableList<PropertyModel> models = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{"" + groupId};
            ResultSet resultSet = DatabaseConnector.getCallableStatement(connection, "func_select_properties_by_group_id", params).executeQuery();
            while (resultSet.next()) {
                PropertyModel model = new PropertyModel();
                model.setPropertyId(resultSet.getInt("fld_property_id"));
                model.setGroupId(resultSet.getInt("fld_group_id"));
                model.setPropertyUrl(resultSet.getString("fld_property_url"));
                model.setPropertyTitle(resultSet.getString("fld_property_title"));
                model.setPropertySquare(resultSet.getString("fld_property_square"));
                model.setPropertyPrice(resultSet.getString("fld_property_price"));
                model.setPropertyProject(resultSet.getString("fld_property_project"));
                model.setPropertyAddress(resultSet.getString("fld_property_address"));
                model.setPropertyDescription(resultSet.getString("fld_property_description"));
                model.setPropertyContact(resultSet.getString("fld_property_contact"));
                model.setPropertyMobile(resultSet.getString("fld_property_mobile"));
                model.setPropertyEmail(resultSet.getString("fld_property_email"));
                models.add(model);
            }
            DatabaseConnector.closeResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return models;
    }

    public static ObservableList<PropertyModel> filter(int method, int category, int page) {
        ObservableList<PropertyModel> models = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{"" + method, "" + category, "" + page};
            ResultSet resultSet = DatabaseConnector.getCallableStatement(connection, "func_select_properties_by_id", params).executeQuery();
            while (resultSet.next()) {
                PropertyModel model = new PropertyModel();
                model.setPropertyId(resultSet.getInt("fld_property_id"));
                model.setGroupId(resultSet.getInt("fld_group_id"));
                model.setPropertyUrl(resultSet.getString("fld_property_url"));
                model.setPropertyTitle(resultSet.getString("fld_property_title"));
                model.setPropertySquare(resultSet.getString("fld_property_square"));
                model.setPropertyPrice(resultSet.getString("fld_property_price"));
                model.setPropertyProject(resultSet.getString("fld_property_project"));
                model.setPropertyAddress(resultSet.getString("fld_property_address"));
                model.setPropertyDescription(resultSet.getString("fld_property_description"));
                model.setPropertyContact(resultSet.getString("fld_property_contact"));
                model.setPropertyMobile(resultSet.getString("fld_property_mobile"));
                model.setPropertyEmail(resultSet.getString("fld_property_email"));
                models.add(model);
            }
            DatabaseConnector.closeResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return models;
    }

    public static int delete(PropertyModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getPropertyId() + ""};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_delete_properties", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int insert(PropertyModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getGroupId() + "", model.getPropertyUrl(), model.getPropertyTitle(), model.getPropertyPrice(), model.getPropertySquare(), model.getPropertyProject(), model.getPropertyAddress(), model.getPropertyDescription(), model.getPropertyContact(), model.getPropertyMobile(), model.getPropertyEmail()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_insert_properties", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int update(PropertyModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getPropertyId() + "", model.getGroupId() + "", model.getPropertyUrl(), model.getPropertyTitle(), model.getPropertyPrice(), model.getPropertySquare(), model.getPropertyProject(), model.getPropertyAddress(), model.getPropertyDescription(), model.getPropertyContact(), model.getPropertyMobile(), model.getPropertyEmail()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_update_properties", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }
}
