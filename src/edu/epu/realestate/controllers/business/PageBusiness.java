package edu.epu.realestate.controllers.business;

import edu.epu.realestate.models.PageModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DuongNArtist on 17/12/2015.
 */
public class PageBusiness {
    private static Connection connection = null;

    public static ObservableList<PageModel> select(String key) {
        ObservableList<PageModel> models = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{"%" + key + "%"};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_select_pages", params);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PageModel model = new PageModel();
                model.setPageId(resultSet.getInt("fld_page_id"));
                model.setPageName(resultSet.getString("fld_page_name"));
                model.setPageUrl(resultSet.getString("fld_page_url"));
                model.setPageDescription(resultSet.getString("fld_page_description"));
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

    public static int delete(PageModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getPageId() + ""};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_delete_pages", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int insert(PageModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getPageName(), model.getPageUrl(), model.getPageDescription()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_insert_pages", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int update(PageModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getPageId() + "", model.getPageName(), model.getPageUrl(), model.getPageDescription()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "func_update_pages", params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }
}
