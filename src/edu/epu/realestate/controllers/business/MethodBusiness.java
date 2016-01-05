package edu.epu.realestate.controllers.business;

import edu.epu.realestate.models.MethodModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DuongNArtist on 17/12/2015.
 */
public class MethodBusiness {

    private static Connection connection = null;

    public static ObservableList<MethodModel> select(String key) {
        ObservableList<MethodModel> models = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{"%" + key + "%"};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_select_methods", params);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MethodModel model = new MethodModel();
                model.setMethodId(resultSet.getInt("fld_method_id"));
                model.setMethodName(resultSet.getString("fld_method_name"));
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

    public static int insert(MethodModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getMethodName()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_insert_methods", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int update(MethodModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getMethodId() + "", model.getMethodName()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection,
                    "func_update_methods", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int delete(MethodModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getMethodId() + ""};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_delete_methods", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }
}
