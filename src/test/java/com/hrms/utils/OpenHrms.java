package com.hrms.utils;

import com.hrms.pages.HrmsLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OpenHrms {
    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();
        CommonMethods.driver = new FirefoxDriver();
        CommonMethods.driver.navigate().to(ConfigsReader.getPropertyValue("url"));
        HrmsLoginPage loginPage = new HrmsLoginPage();
        CommonMethods.sendText(loginPage.usernameTextBox,ConfigsReader.getPropertyValue("username"));
        CommonMethods.sendText(loginPage.passwordTextBox, ConfigsReader.getPropertyValue("password"));
        CommonMethods.click(loginPage.loginBtn);
        CommonMethods.driver.manage().window().maximize();
    }
}
