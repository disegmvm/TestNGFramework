package com.hrms.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonMethods {


    protected static WebDriver driver;


    /*public static void setUpWithSpecificURL(String browser, String url){
        switch (browser.toLowerCase()){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser");
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }*/

    @BeforeMethod (alwaysRun = true)
    public static void setUp(){
            ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
            switch (ConfigsReader.getPropertyValue("browser")){
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                default:
                    throw new RuntimeException("Invalid browser");
            }
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.navigate().to(ConfigsReader.getPropertyValue("url"));


    }
    @AfterMethod(alwaysRun = true)
    public static void tearDown(){
        if (driver!=null){
            driver.quit();
        }
    }


    /*this method clears a textbox and send specific text to it:
    * */
    public static void sendText(WebElement element, String textToSend){
        element.clear();
        element.sendKeys(textToSend);
    }


    /*this method returns an object of Explicit wait:
    * */
    public static WebDriverWait getExplicitWait(){
        WebDriverWait explicitWait = new WebDriverWait(driver,20);
        return explicitWait;
    }


    /*this method waits until given element becomes clickable:
    * */
    public static void waitUntilClickable(WebElement element){
        getExplicitWait().until(ExpectedConditions.elementToBeClickable(element));
    }


    /*this method waits then click:
    * */
    public static void click(WebElement element){
        waitUntilClickable(element);
        element.click();
    }
}
