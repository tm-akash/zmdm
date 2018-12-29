/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.zmdm.util;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author ZMF
 */
public class JsonUtil {

    public static String objToJson(Object obj) throws IOException {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw e;
        }

    }

    public static Object jsonToObj(String obj, Class c) throws IOException {
        try {
            return new ObjectMapper().readValue(obj, c);
        } catch (IOException e) {
            throw e;
        }

    }

    public static JSONObject objectToJSONObject(Object object) {
        Object json = null;
        JSONObject jsonObject = null;
        try {
            json = new JSONTokener(object.toString()).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json instanceof JSONObject) {
            jsonObject = (JSONObject) json;
        }
        return jsonObject;
    }

    public static JSONArray objectToJSONArray(Object object) {
        Object json = null;
        JSONArray jsonArray = null;
        try {
            json = new JSONTokener(object.toString()).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json instanceof JSONArray) {
            jsonArray = (JSONArray) json;
        }
        return jsonArray;
    }

    public static Object fromJson(String obj, Class c) throws IOException {
        Gson gson = new Gson();
        Object json = gson.fromJson(obj, c);
        return json;

    }

    public static String toJson(Object obj) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;

    }

}

