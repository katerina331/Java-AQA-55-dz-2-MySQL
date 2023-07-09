package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.*;
import ru.netology.page.*;

import static com.codeborne.selenide.Selenide.open;

class MysqlDockerTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;


    @BeforeEach
    void setup() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @AfterAll
    static void clear() {
        MySQLHelper.cleanMySQLDate();
    }

    @Test
    void shouldUserFirst() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        dashboardPage = verificationPage.validVerify();
    }

    @Test
    void shouldUserSecond() {
        var authInfo = DataHelper.getOtherAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        dashboardPage = verificationPage.validVerify();
    }

    @Test
    void shouldUserNoValidCode() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.invalidVerify();
    }

    @Test
    void shouldUserNoValidLogin() {
        loginPage.inValidLogin();
    }

    @Test
    void shouldUserNoValidLogin3() {
        loginPage.inValidLogin();
        loginPage.inValidLogin();
        loginPage.inValidLogin();
        loginPage.inLoginEnterErrorCheck();
    }
}