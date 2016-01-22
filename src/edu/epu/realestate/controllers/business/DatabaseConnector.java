package edu.epu.realestate.controllers.business;

import edu.epu.realestate.bundles.Strings;
import edu.epu.realestate.models.dialogs.MessageDialog;

import java.sql.*;

/**
 * Created by DuongNArtist on 12/12/2015.
 */
public class DatabaseConnector {

    public static final String DB_NAME = "db_realestate";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NAME + "?" +
                    "user=" + DB_USER + "&password=" + DB_PASS);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection == null) {
            MessageDialog.show(null, MessageDialog.WARNING, Strings.title_warning, "Không có kết nối cơ sở dữ liệu!");
        }
        return connection;
    }

    public static String getQuery(String procedure, int number) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{CALL " + procedure + "(");
        for (int i = 0; i < number - 1; i++) {
            stringBuilder.append("?, ");
        }
        stringBuilder.append("?)}");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static CallableStatement getCallableStatement(Connection connection, String procedure, String[] params) {
        CallableStatement statement = null;
        try {
            String query = getQuery(procedure, params.length);
            statement = connection.prepareCall(query);
            for (int i = 0; i < params.length; i++) {
                statement.setNString(i + 1, params[i]);
            }
            //statement.registerOutParameter(params.length, Types.INTEGER);
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
                preparedStatement = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
                resultSet = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
