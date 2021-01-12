package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HrmsEmployeeListPage extends CommonMethods {
    @FindBy (id="empsearch_id")
    public WebElement idField;

    public HrmsEmployeeListPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy (id="searchBtn")
    public WebElement searchBtn;

    /*public boolean idSearch(String id){
        String resultId = driver.findElement(By.xpath("//a[text()='"+id+"']")).getText();
        if(resultId.equals(id)){
            return true;
        }else{
            return false;
        }
    }*/
}
