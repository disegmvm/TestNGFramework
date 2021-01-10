package com.hrms.testcases;

import com.hrms.pages.HrmsDashboardPage;
import com.hrms.pages.HrmsLoginPage;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import org.junit.Assert;
import org.testng.annotations.Test;

public class LoginTest extends CommonMethods {
    @Test
    public void adminLogin(){
        HrmsLoginPage loginPage = new HrmsLoginPage();
        sendText(loginPage.usernameTextBox, ConfigsReader.getPropertyValue("username"));
        sendText(loginPage.passwordTextBox, ConfigsReader.getPropertyValue("password"));
        click(loginPage.loginBtn);
        HrmsDashboardPage dbPage = new HrmsDashboardPage();
        Assert.assertTrue(dbPage.welcomeMessage.isDisplayed());
        System.out.println("Is welcome message displayed? - "+dbPage.welcomeMessage.isDisplayed());
    }
}
