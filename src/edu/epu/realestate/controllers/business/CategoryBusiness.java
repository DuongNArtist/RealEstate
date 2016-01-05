package edu.epu.realestate.controllers.business;

import edu.epu.realestate.models.CategoryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DuongNArtist on 17/12/2015.
 */
public class CategoryBusiness {
    private static Connection connection = null;

    public static ObservableList<CategoryModel> select(String key) {
        ObservableList<CategoryModel> models = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{"%" + key + "%"};
            ResultSet resultSet = DatabaseConnector.getCallableStatement(connection, "func_select_categories", params).executeQuery();
            while (resultSet.next()) {
                CategoryModel model = new CategoryModel();
                model.setCategoryId(resultSet.getInt("fld_category_id"));
                model.setCategoryName(resultSet.getString("fld_category_name"));
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

    public static int insert(CategoryModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getCategoryName()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_insert_categories", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int update(CategoryModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getCategoryId() + "", model.getCategoryName()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_update_categories", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int delete(CategoryModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getCategoryId() + ""};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_delete_categories", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

}
