package guru.qa.junit.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {

    public TextBoxPage openPage() {
        open("/text-box");
        return this;
    }

    public TextBoxPage setFullName(String fullName) {
        $("#userName").setValue(fullName);
        return this;
    }

    public TextBoxPage setEmail(String email) {
        $("#userEmail").setValue(email);
        return this;
    }

    public TextBoxPage setCurrentAddress(String currentAddress) {
        $("#currentAddress").setValue(currentAddress);
        return this;
    }

    public TextBoxPage setPermanentAddress(String permanentAddress) {
        $("#permanentAddress").setValue(permanentAddress);
        return this;
    }

    public TextBoxPage submitForm() {
        $("#submit").click();
        return this;
    }

    public TextBoxPage checkResult(String textValue) {
        $("#output").shouldHave(text(textValue));
        return this;
    }
}
