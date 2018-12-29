/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.zmdm.util;

/**
 *
 * @author Devilal.T
 */
public interface Constants {
    
    
    public static int HTTP_SUCCESS = 200;
    public static int HTTP_BADREQUEST = 400;
    public static int HTTP_FAILURE = 500;
    public static int INTERNAL_SERVER_ERROR= 404;
    
    public static String EMP_LOGIN_SUCCESS="Employee logged in..";
    public static String EMP_LOGIN_FAILED="Invalid Credentials";
    public static String PIN_CHANGE_SUCCESS="Pin changed Successfully";
    public static String PIN_CHANGE_FAILED="Invalid Pin, Please enter correct Pin";
    
    public static String CS_ASSIGNED="ASSIGNED";
    public static String CS_CLOSED="CLOSED";
    public static String CS_PENDING="PENDING";
    public static String CS_ESCALATED="ESCALATED";
    public static String CS_SOLVED="SOLVED";
    public static String CS_REOPEN="REOPEN";
//    public static String CS_="";
    
    public static int DEVICE_FOUND = 777;
    public static int DEVICE_NOT_FOUND = 000;
    
    
    public static String DEVICE_NOT_FOUND_MESSAGE = "Device is not mapped to the company";
    public static String DEVICCE_FOUND_MESSAGE = "Device found";
}
