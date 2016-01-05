package edu.epu.realestate.controllers.business;

import edu.epu.realestate.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DuongNArtist on 17/12/2015.
 */
public class UserBusiness {

    private static Connection connection = null;

    public static ObservableList<UserModel> select(String username, String password) {
        ObservableList<UserModel> userModels = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{username, password};
            ResultSet resultSet = DatabaseConnector.getCallableStatement(connection, "func_select_users", params).executeQuery();
            while (resultSet.next()) {
                UserModel userModel = new UserModel();
                userModel.setUsername(resultSet.getString("fld_username"));
                userModel.setPassword(resultSet.getString("fld_password"));
                userModel.setEmail(resultSet.getString("fld_email"));
                userModels.add(userModel);
                break;
            }
            DatabaseConnector.closeResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return userModels;
    }
}
