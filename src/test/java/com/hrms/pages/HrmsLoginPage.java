package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HrmsLoginPage extends CommonMethods {

    @FindBy (id="txtUsername")
    public WebElement usernameTextBox;

    @FindBy(xpath = "//input[@id = 'txtPassword']")
    public WebElement passwordTextBox;

    @FindBy(css = "input#btnLogin")
    public WebElement loginBtn;

    @FindBy(id="spanMessage")
    public WebElement errorLoginMsg;

    public HrmsLoginPage(){
        PageFactory.initElements(driver,this);
    }

}


