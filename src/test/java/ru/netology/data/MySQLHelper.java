package ru.netology.data;

import lombok.SneakyThrows;

import java.sql.DriverManager;
import java.sql.Statement;

public class MySQLHelper {
    private MySQLHelper() {
    }

    @SneakyThrows
    private static Statement connection() {
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        return connection.createStatement();
    }


    @SneakyThrows
    public static String getVerificationCodeFor() {
        var date = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        String verificationCod = null;
        var connect = connection();
        var resultSet = connect.executeQuery(date);
        if (resultSet.next()) {
            verificationCod = resultSet.getString(1);
        }
        return verificationCod;
    }

    @SneakyThrows
    public static String getNoVerificationCodeFor() {
        var noVerificationCod = "0";
        return noVerificationCod;
    }

    @SneakyThrows
    public static void cleanMySQLDate() {
        var connect = connection();
        connection().execute("DELETE FROM auth_codes");
        connection().execute("DELETE FROM card_transactions");
        connection().execute("DELETE FROM cards");
        connection().execute("DELETE FROM users");
    }

}
