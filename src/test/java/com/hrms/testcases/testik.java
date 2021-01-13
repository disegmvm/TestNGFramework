package com.hrms.testcases;

import java.io.FileNotFoundException;


/*Create a method that will not be handling exception and throwing it at caller.
        In main method call method and handle the exception.

        Expected Output:

        java.io.FileNotFoundException:  (No such file or directory)*/

public class testik extends Exception{

static void error() throws Exception {
    throw new FileNotFoundException("(No such file or directory)");
}

    public static void main(String[] args){
        try {
            error();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
