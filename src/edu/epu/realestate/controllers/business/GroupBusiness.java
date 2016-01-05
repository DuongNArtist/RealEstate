package edu.epu.realestate.controllers.business;

import edu.epu.realestate.models.GroupModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DuongNArtist on 17/12/2015.
 */
public class GroupBusiness {
    private static Connection connection = null;

    public static ObservableList<GroupModel> select(String key) {
        ObservableList<GroupModel> models = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{"%" + key + "%"};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_select_groups", params);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                GroupModel model = new GroupModel();
                model.setGroupId(resultSet.getInt("fld_group_id"));
                model.setMethodId(resultSet.getInt("fld_method_id"));
                model.setCategoryId(resultSet.getInt("fld_category_id"));
                model.setPageId(resultSet.getInt("fld_page_id"));
                model.setGroupUrl(resultSet.getString("fld_group_url"));
                model.setGroupTitle(resultSet.getString("fld_group_title"));
                model.setGroupParam(resultSet.getString("fld_group_param"));
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

    public static ObservableList<GroupModel> filter(int method, int category, int page) {
        ObservableList<GroupModel> models = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{"" + method, "" + category, "" + page};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_select_groups_by_id", params);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                GroupModel model = new GroupModel();
                model.setGroupId(resultSet.getInt("fld_group_id"));
                model.setMethodId(resultSet.getInt("fld_method_id"));
                model.setCategoryId(resultSet.getInt("fld_category_id"));
                model.setPageId(resultSet.getInt("fld_page_id"));
                model.setGroupUrl(resultSet.getString("fld_group_url"));
                model.setGroupTitle(resultSet.getString("fld_group_title"));
                model.setGroupParam(resultSet.getString("fld_group_param"));
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

    public static int update(GroupModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getGroupId() + "", model.getMethodId() + "", model.getCategoryId() + "", model.getPageId() + "", model.getGroupUrl(), model.getGroupTitle(), model.getGroupParam()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_update_groups", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int delete(GroupModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getGroupId() + ""};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_delete_groups", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int insert(GroupModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getMethodId() + "", model.getCategoryId() + "", model.getPageId() + "", model.getGroupUrl(), model.getGroupTitle(), model.getGroupParam()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_insert_groups", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

}
