package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.*;
import ru.netology.page.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    SelenideElement noVerify = $("[data-test-id=error-notification] .notification__content");
    var expectedInfoBlock = "Блокировка! Неверно указан логин или пароль три раза";
    loginPage.inValidLogin();
    loginPage.inValidLogin();
    loginPage.inValidLogin();
    var actualInfoBlock = noVerify.getText();
    assertEquals(expectedInfoBlock, actualInfoBlock);
  }
}