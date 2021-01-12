package com.hrms.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    static Workbook wb;
    static Sheet sheet;


    public static void openExcel(String filepath){
        try {
            FileInputStream fis = new FileInputStream(filepath);
            wb = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            System.out.println("No such file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("cant access your workbook");
            e.printStackTrace();
        }
    }

    public static void getSheet(String sheetName){
        sheet = wb.getSheet(sheetName);
    }

    public static int getRowsNumber(){
        return sheet.getPhysicalNumberOfRows();
    }

    public static int getColsNumber(int rowIndex){
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }


    public static String getCellsData(int rowIndex, int cellIndex){
        if(sheet.getRow(rowIndex).getCell(cellIndex)!=null){
            return sheet.getRow(rowIndex).getCell(cellIndex).toString();
        }else{
            return null;
        }
    }


    public static List<Map<String,String>> excelToListMap(){
        List<Map<String,String>> list = new ArrayList();
        for (int row =1; row<getRowsNumber();row++){
            Map<String,String> tempMap = new LinkedHashMap<>();
            for (int coll=0; coll<getColsNumber(row); coll++){
                tempMap.put(getCellsData(0,coll),getCellsData(row,coll));
            }
            list.add(tempMap);
        }
        return list;
    }
}
