package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.MySQLHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.data.DataHelper.getInvalidErrorInfo;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement noVerify = $("[data-test-id=error-notification] .notification__content");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify() {
        codeField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        codeField.setValue(MySQLHelper.getVerificationCodeFor());
        verifyButton.click();
        return new DashboardPage();
    }

    public void invalidVerify() {
        codeField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        codeField.setValue(MySQLHelper.getNoVerificationCodeFor());
        verifyButton.click();
        noVerify.should(Condition.exactText(getInvalidErrorInfo().getErrorText())).shouldBe(visible);
    }
}
