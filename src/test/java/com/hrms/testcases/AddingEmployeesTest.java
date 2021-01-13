package com.hrms.testcases;

import com.hrms.pages.HrmsAddEmployeePage;
import com.hrms.pages.HrmsDashboardPage;
import com.hrms.pages.HrmsEmployeeListPage;
import com.hrms.pages.HrmsLoginPage;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import com.hrms.utils.Constants;
import com.hrms.utils.ExcelReader;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AddingEmployeesTest extends CommonMethods {

String excelFilePath = Constants.EXCEL_FILEPATH;
String employeesId;

        @Test (groups = "regression")
        public void addingEmployees(){
        TakesScreenshot ts = (TakesScreenshot) driver;
        HrmsLoginPage loginPage = new HrmsLoginPage();
        sendText(loginPage.usernameTextBox, ConfigsReader.getPropertyValue("username"));
        sendText(loginPage.passwordTextBox, ConfigsReader.getPropertyValue("password"));
        click(loginPage.loginBtn);
        HrmsDashboardPage db = new HrmsDashboardPage();
        Actions action = new Actions(driver);
        ExcelReader.openExcel(excelFilePath);
        ExcelReader.getSheet("Sheet1");
        List<Map<String,String>> employeeList = ExcelReader.excelToListMap();
        HrmsAddEmployeePage addEmpPage = new HrmsAddEmployeePage();

        //starting to add 5 employees:
        for (Map<String,String> eachEmployee:employeeList) {

            //navigating to Add Employee page:
            action.moveToElement(db.pimBtn).perform();
            waitUntilClickable(db.addEmployeeBtn);
            db.addEmployeeBtn.click();

            //filling out all of the required forms:
            sendText(addEmpPage.firstNameField, eachEmployee.get("FirstName"));
            sendText(addEmpPage.lastNameField, eachEmployee.get("LastName"));
            sendText(addEmpPage.middleNameField, eachEmployee.get("MiddleName"));
            addEmpPage.addPhotoBtn.sendKeys(eachEmployee.get("Photo"));
            addEmpPage.createLoginCheckBox.click();
            waitUntilClickable(addEmpPage.userNameField);
            sendText(addEmpPage.userNameField, generateRandomString());
            sendText(addEmpPage.passwordField, eachEmployee.get("password"));
            sendText(addEmpPage.confirmPwField, eachEmployee.get("password"));
            addEmpPage.saveBtn.click();

            //collecting current employees id:
            WebElement id = CommonMethods.driver.findElement(By.id("personal_txtEmployeeId"));
            employeesId = id.getAttribute("value");

            //verifying:
            //navigating to all employees list:
            action.moveToElement(db.pimBtn).perform();
            waitUntilClickable(db.employeeListBtn);
            db.employeeListBtn.click();

            //looking for specific id:
            HrmsEmployeeListPage employeeListPage = new HrmsEmployeeListPage();
            sendText(employeeListPage.idField, employeesId);
            employeeListPage.searchBtn.click();

            //locating specific id in the results table:
            WebElement idResult = CommonMethods.driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td[2]/a"));
            String resultId = idResult.getText();
            Assert.assertEquals(employeesId, resultId);

            //navigating to just created employees page:
            idResult.click();

            //taking screenshot:
            getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("empPic")));
            screenShot(eachEmployee.get("FirstName"));
        }
    }
}
