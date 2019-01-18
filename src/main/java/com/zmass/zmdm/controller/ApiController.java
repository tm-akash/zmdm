/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.zmdm.controller;

import com.zmass.emp.tracking.util.CommonRes;
import com.zmass.zmdm.entityManagerFactory.EntityManagerUtility;
import com.zmass.zmdm.jpaController.ApplicationJpaController;
import com.zmass.zmdm.jpaController.DeviceJpaController;
import com.zmass.zmdm.jpaController.DistrictJpaController;
import com.zmass.zmdm.jpaController.ProfileApplicationMappingJpaController;
import com.zmass.zmdm.jpaController.ProfileJpaController;
import com.zmass.zmdm.jpaController.ProjectFleetJpaController;
import com.zmass.zmdm.jpaController.StateJpaController;
import com.zmass.zmdm.util.Base64Coder;
import com.zmass.zmdm.util.Constants;
import com.zmass.zmdm.util.EncryptHelper;
import com.zmass.zmdm.util.JsonUtil;
import com.zmss.zmdm.entity.Application;
import com.zmss.zmdm.entity.Device;
import com.zmss.zmdm.entity.District;
import com.zmss.zmdm.entity.Profile;
import com.zmss.zmdm.entity.ProfileApplicationMapping;
import com.zmss.zmdm.entity.ProjectFleet;
import com.zmss.zmdm.entity.State;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ZMF
 */
@RestController
public class ApiController implements Constants {
    
    CommonRes cres = null;
    
    EntityManagerFactory emf = EntityManagerUtility.getEntity();
    
    @RequestMapping(value = "/stateListApi", method = RequestMethod.GET)
    public String stateList() throws IOException {
        StateJpaController stateJpaController = new StateJpaController(emf);
        List<State> state = stateJpaController.findStateEntities();
        cres = new CommonRes("States have been downloaded successfully", state, 200);
        return encryptRequest(JsonUtil.toJson(cres));
//        return (JsonUtil.toJson(state));

    }
    
    @RequestMapping(value = "/districtListApi", method = RequestMethod.POST)
    public String districtList(@RequestBody String data) throws IOException {
        data = decryptResponse(data);
        State state = (State) JsonUtil.fromJson(data, State.class);
        
        DistrictJpaController districtJpaController = new DistrictJpaController(emf);
        List<District> district = districtJpaController.findDistrictList(state.getCharCode());
        
        cres = new CommonRes("District have been downloaded successfully", district, 200);
        return encryptRequest(JsonUtil.toJson(cres));
//        return (JsonUtil.toJson(district));

    }

    //for encryption
    private static String encryptRequest(String data) {
        String encryptedString = null;
        try {
            //encryptedString = AESHelper.getInstance().encrypt(data);

            EncryptHelper encrypterHelper = new EncryptHelper();
            byte[] endata = new byte[0];
            endata = encrypterHelper.encryptData(data.getBytes());
            return Base64Coder.encodeBuftoString(endata);
            
        } catch (Exception e) {
            
        }
        
        return encryptedString;
    }
//for decryption

    private static String decryptResponse(String data) {
        String decryptedString = null;
        try {
            //decryptedString = AESHelper.getInstance().decrypt(data);
            EncryptHelper encrypterHelper = new EncryptHelper();
            
            byte[] dcdata = new byte[0];
            dcdata = encrypterHelper.decryptData(Base64Coder.decode(data));
            return new String(dcdata);
        } catch (Exception e) {
            e.printStackTrace();
//        Log.e(TAG, "Exception in parsing->" + e);
        }
        return decryptedString;
    }
    
    @RequestMapping(value = "initialRequest", method = RequestMethod.POST)
    public String initialRequest(@RequestBody String data) throws IOException {
        try {
            
            data = decryptResponse(data);
//        State state = (State) JsonUtil.fromJson(data, State.class);
            Device device = (Device) JsonUtil.fromJson(data, Device.class);
            DeviceJpaController deviceJpaController = new DeviceJpaController(emf);
            Device deviceFromDB = deviceJpaController.findDeviceByIMEI(device);
            if (deviceFromDB == null) {
                cres = new CommonRes(DEVICE_NOT_FOUND_MESSAGE, DEVICE_NOT_FOUND);
            } else if (deviceFromDB != null) {
                cres = new CommonRes(DEVICCE_FOUND_MESSAGE,deviceFromDB, DEVICE_FOUND);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            cres = new CommonRes("Internal Server Error", INTERNAL_SERVER_ERROR);
        }
        return encryptRequest(JsonUtil.objToJson(cres));
//        return JsonUtil.objToJson(cres);
    }
    
    @RequestMapping(value = "/syncRequest", method = RequestMethod.POST)
    public String syncRequest(@RequestBody String data) throws IOException {
        try {
            data = decryptResponse(data);
//            System.out.println("encrypt: " + encryptRequest(data));
            JSONArray applicationArray = new JSONArray();
            JSONObject dataObject = new JSONObject();
            JSONObject responseObject = new JSONObject();
            Device device = (Device) JsonUtil.fromJson(data, Device.class);
            DeviceJpaController deviceJpaController = new DeviceJpaController(emf);
            Device deviceResponse = deviceJpaController.findDeviceByIMEI(device);
            deviceResponse.setSyncStatus("SYNC");
            deviceResponse.setInstalledApp(device.getInstalledApp());
            deviceResponse.setUninstalledApp(device.getUninstalledApp());
            deviceResponse.setStatus("ACTIVE");
            if(device.getLatitude() != null){
            deviceResponse.setLatitude(device.getLatitude());
            }
            if(device.getLongitude() != null){
            deviceResponse.setLongitude(device.getLongitude());
            }
            deviceResponse.setLastSync(new Date());
            deviceJpaController.edit(deviceResponse);
            ProjectFleetJpaController projectFleetJpaController = new ProjectFleetJpaController(emf);
            ProjectFleet projectFleetResponse = projectFleetJpaController.findProjectFleet(deviceResponse.getProjectFleetId());
            ProfileApplicationMappingJpaController pamJpaController = new ProfileApplicationMappingJpaController(emf);
            List<ProfileApplicationMapping> listOfApplication = pamJpaController.getProfileIdByProfileId(projectFleetResponse.getProfileId().toString());
            dataObject.put("profileId", projectFleetResponse.getProfileId());
            ProfileJpaController profileJpaController = new ProfileJpaController(emf);
            Profile profile = profileJpaController.findProfile(projectFleetResponse.getProfileId());
            dataObject.put("profileName", profile.getName());
            dataObject.put("profileStatus", profile.getStatus());
            dataObject.put("syncInterval", profile.getSyncInterval());
            
            for (ProfileApplicationMapping pam : listOfApplication) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                ApplicationJpaController applicationInfo = new ApplicationJpaController(emf);
//                System.out.println("pam id: " + pam.getApplicationId());
                Application info = applicationInfo.findApplication(pam.getApplicationId());
//                System.out.println("info details: " + info.getId());
                String getIconName = info.getIcon();
                String imgPathHead = "/ZMDM_Application_Repository/APK_ICON/";
                String iconPath = imgPathHead + getIconName;
//                System.out.println("icon Path: " + "com.zero.aepsmerchantonboarding1.0.3.png");
                String base64 = DatatypeConverter.printBase64Binary(Files.readAllBytes(Paths.get(iconPath)));
//                System.out.println("base 64: " + base64);
                hm.put("icon", base64);
                hm.put("applicationName", info.getName());
                hm.put("packageName", info.getPackageName());
                hm.put("versionCode", info.getVersionCode().toString());
                hm.put("versionName", info.getVersionName());
                hm.put("size", info.getSize());
                hm.put("url", info.getName());
                applicationArray.put(hm);
            }
            dataObject.put("applications", applicationArray);
//            System.out.println("result: " + dataObject);
            responseObject.put("responseCode", HTTP_SUCCESS);
            responseObject.put("responseMessage", "list of applications");
            responseObject.put("data", dataObject);
//            System.out.println("result: " + responseObject);
//            return encryptRequest(JsonUtil.objToJson(responseObject));
//            cres = new CommonRes("list of application", dataObject, HTTP_SUCCESS);

            return encryptRequest(responseObject.toString());
//            return responseObject.toString();
            
        } catch (Exception e) {
            e.printStackTrace();
            cres = new CommonRes("internal server error", HTTP_SUCCESS);
            return encryptRequest(JsonUtil.objToJson(cres));
        }
        
    }
    
    @RequestMapping(value = "deviceRegister", method = RequestMethod.POST)
    public String deviceRegister(@RequestBody String data) throws IOException {
        try {
            System.out.println("in device register");
            data = decryptResponse(data);
            Device device = (Device) JsonUtil.fromJson(data, Device.class);
            System.out.println("imei: " + device.getImei());
            if (device.getImei().equals("")) {
                cres = new CommonRes("imei didnt recieved", HTTP_BADREQUEST);
                return encryptRequest(JsonUtil.objToJson(cres));
            }
            DeviceJpaController deviceJpaController = new DeviceJpaController(emf);
            try {
                Device deviceExistOrNot = deviceJpaController.findDeviceByIMEI(device);
                if (deviceExistOrNot != null) {
                    Device deviceRequestData = (Device) JsonUtil.fromJson(data, Device.class);
                    deviceExistOrNot.setLastSync(new Date());
                    deviceExistOrNot.setOwnerName(deviceRequestData.getOwnerName());
                    deviceExistOrNot.setState(deviceRequestData.getState());
                    deviceExistOrNot.setDistrict(deviceRequestData.getDistrict());
                    deviceJpaController.edit(deviceExistOrNot);
                    cres = new CommonRes("777", HTTP_SUCCESS);
//                    return JsonUtil.objToJson(cres);
                    return encryptRequest(JsonUtil.objToJson(cres));
                }
            } catch (Exception ne) {
                System.out.println("into inner catch");
                ne.printStackTrace();
            }
            deviceJpaController.create(device);
            cres = new CommonRes("1", HTTP_SUCCESS);
            return encryptRequest(JsonUtil.objToJson(cres));
//            return JsonUtil.objToJson(cres);
        } catch (Exception e) {
            e.printStackTrace();
            cres = new CommonRes("internal server error", HTTP_FAILURE);
            return encryptRequest(JsonUtil.objToJson(cres));
        }
    }
    
//    @RequestMapping(value = "deviceCheck", method = RequestMethod.POST)
//    public String deviceCheck(@RequestBody String data) throws IOException{
//       String requestString;
//        try{
//            requestString = decryptResponse(data);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            cres = new CommonRes("Incorrect request to server", HTTP_SUCCESS);
//            return encryptRequest(JsonUtil.objToJson(cres));
//        }
//        try{
//            DeviceJpaController deviceJpaController = new DeviceJpaController(emf);
//           Device device = (Device) JsonUtil.fromJson(requestString, Device.class);
//           
//        }
//        catch(Exception e){
//            e.printStackTrace();
//             cres = new CommonRes("Internal server error", HTTP_FAILURE);
//            return encryptRequest(JsonUtil.objToJson(cres));
//        }
//        
//        
//       
//    }
    
}
