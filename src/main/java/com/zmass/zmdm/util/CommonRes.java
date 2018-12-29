/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.emp.tracking.util;

/**
 *
 * @author SunilRaghuvanshi
 */
public class CommonRes {

    public String message;
    public int status;
    public Object response;

    public CommonRes(String message, Object response, int status) {

        this.message = message;
        this.response = response;
        this.status = status;
    }
    
        public CommonRes(String message, int status) {

        this.message = message;
        this.status = status;
    }

}
