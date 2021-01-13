package com.hrms.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonMethods {


    protected static WebDriver driver;


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


    /**
     * Generates random String:
     * @return
     */
    public static String generateRandomString(){
        int length =10;
        boolean useLetters=true;
        boolean useNumbers=true;
        return RandomStringUtils.random(length,useLetters,useNumbers);
    }


    /**
     * Sends the text to WebElement:
     * @param element
     * @param textToSend
     */
    public static void sendText(WebElement element, String textToSend){
        element.clear();
        if(textToSend!=null) {
            element.sendKeys(textToSend);
        }
    }

    /**
     * Returns timeStamp:
     * @return
     */
    public static String getTimeStamp(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return sdf.format(date);
    }


    /**
     * Returns an Object of explicit wait:
     * @return
     */
    public static WebDriverWait getExplicitWait(){
        WebDriverWait explicitWait = new WebDriverWait(driver,20);
        return explicitWait;
    }


    /**
     * Waits until WebElement becomes clickable:
     * @param element
     */
    public static void waitUntilClickable(WebElement element){
        getExplicitWait().until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * Waits for WebElement to become clickable then clicks it:
     * @param element
     */
    public static void click(WebElement element){
        waitUntilClickable(element);
        element.click();
    }

    /**
     * Returns JSExecutor:
     * @return
     */
    public static JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return js;
    }

    /**
     * Clicks the WebElement using JSExecutor:
     * @param element
     */
    public static void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click",element);
    }

    /**
     * This method takes screenshot.
     * @param filename
     */
    public static void screenShot(String filename){
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH+filename+".png"));
        } catch (IOException e) {
            System.out.println("Error reading your file");
            e.printStackTrace();
        }
    }
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
}
