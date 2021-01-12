package com.hrms.testcases;

import com.hrms.utils.ExcelReader;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Map;

public class testik {
    public static void main(String[] args) {
        ExcelReader.openExcel("C:\\Users\\PDD\\eclipse-workspace\\TestNGFramework\\src\\test\\resources\\testdata\\5Employees.xlsx");
        ExcelReader.getSheet("Sheet1");
        /*for (int i=0; i<=5; i++){
            System.out.println(ExcelReader.getCellsData(1,i));
        }*/
        System.out.println(ExcelReader.getCellsData(1,5));
        /*System.out.println(ExcelReader.getColsNumber(3));
        System.out.println(ExcelReader.getRowsNumber());*/
    }
}
