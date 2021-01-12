package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HrmsAddEmployeePage extends CommonMethods{
    @FindBy (id="firstName")
    public WebElement firstNameField;

    @FindBy(id="middleName")
    public WebElement middleNameField;

    @FindBy (id="lastName")
    public WebElement lastNameField;

    public HrmsAddEmployeePage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy (id="employeeId")
    public WebElement employeeIDField;

    @FindBy (id="photofile")
    public WebElement addPhotoBtn;

    @FindBy (id="chkLogin")
    public WebElement createLoginCheckBox;

    @FindBy (id="btnSave")
    public WebElement saveBtn;

    @FindBy (id="user_name")
    public WebElement userNameField;

    @FindBy (id="user_password")
    public WebElement passwordField;

    @FindBy (id="re_password")
    public WebElement confirmPwField;

}
