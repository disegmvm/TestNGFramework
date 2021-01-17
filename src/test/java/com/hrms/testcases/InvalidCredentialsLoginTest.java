package com.hrms.testcases;

import com.hrms.pages.HrmsLoginPage;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import com.hrms.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class InvalidCredentialsLoginTest extends CommonMethods {
    @DataProvider
    public Object[][] InvalidLoginData(){
        Object[][] data = {
                //invalid username and pw:
                {"cerf", "cerfcerf", "Invalid credentials"},

                //invalid username:
                {"Admin1", ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH).
                        getProperty("password"), "Invalid credentials"},

                //invalid pw:
                {ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH).
                getProperty("username"), "asdasdasd", "Invalid credentials"},

                //empty username:
                {"", ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH).
                getProperty("password"), "Username cannot be empty"},

                //empty password:
        {ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH).
                getProperty("username"), "", "Password cannot be empty"}
        };
        return data;
    }

    @Test (dataProvider="InvalidLoginData",  groups = "regression")
    public void invalidCredentialsLoginTest(String username, String pw, String msg){
        HrmsLoginPage loginPage = new HrmsLoginPage();
        sendText(loginPage.usernameTextBox,username);
        sendText(loginPage.passwordTextBox, pw);
        click(loginPage.loginBtn);
        Assert.assertEquals(loginPage.errorLoginMsg.getText(),msg);
    }
}
