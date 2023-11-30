package ru.ui;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MyPage {
    private SelenideElement clickMe = $(By.xpath("//button[text()='Click Me']"));

    public boolean isVisible() {
        return clickMe.isDisplayed();
    }
}