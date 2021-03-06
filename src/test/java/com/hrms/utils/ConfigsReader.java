package com.hrms.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigsReader {
    static Properties prop;

    public static Properties readProperties(String filePath){
        try {
            FileInputStream fis = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fis);
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("Your file is not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Can't load your file");
            e.printStackTrace();
        }
        return prop;
    }

    public static String getPropertyValue (String key){
        return readProperties(Constants.CONFIGURATION_FILEPATH).getProperty(key);
       // return prop.getProperty(key);
    }

}
