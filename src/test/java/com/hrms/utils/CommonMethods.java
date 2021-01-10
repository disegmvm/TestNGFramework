package com.hrms.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonMethods {
    protected static WebDriver driver;

    public static void setUpWithSpecificURL(String browser, String url){
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
    }
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
    public static void tearDown(){
        if (driver!=null){
            driver.quit();
        }
    }

}
