package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.data.DataHelper.*;


public class LoginPage {
    private SelenideElement login = $("[data-test-id=login] input");
    private SelenideElement password = $("[data-test-id=password] input");
    private SelenideElement verifyLogin = $("[data-test-id=action-login]");
    private SelenideElement errorVerify = $("[data-test-id=error-notification] .notification__content");
    private SelenideElement errorBlock = $("[data-test-id=error-block]");

    public static void clearInput(SelenideElement element) {
        element.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        clearInput(login);
        login.setValue(info.getLogin());
        clearInput(password);
        password.setValue(info.getPassword());
        verifyLogin.click();
        return new VerificationPage();
    }

    public void inValidLogin() {
        var invalidInfo = DataHelper.getInvalidAuthInfo();
        clearInput(login);
        login.setValue(invalidInfo.getLogin());
        clearInput(password);
        password.setValue(invalidInfo.getPassword());
        verifyLogin.click();
        errorVerify.should(Condition.exactText(getInvalidLoginErrorInfo().getErrorText())).should(visible);
    }

    public void inLoginEnterErrorCheck() {
        errorBlock.should(Condition.exactText(getLoginEnterErrorInfo().getErrorText())).should(visible);
    }
}