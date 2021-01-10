package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HrmsDashboardPage extends CommonMethods {
    @FindBy(id = "welcome")
    public WebElement welcomeMessage;
    public HrmsDashboardPage(){
        PageFactory.initElements(driver,this);
    }
}
