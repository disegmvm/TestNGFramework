package com.hrms.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
    public void onTestSuccess(ITestResult result){
        CommonMethods.screenShot("passed/"+CommonMethods.getTimeStamp()+result.getName());
    }
    public void onTestFailure(ITestResult result){
        CommonMethods.screenShot("failed/"+CommonMethods.getTimeStamp()+result.getName());
    }

}
