/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.zmdm.controller;

import com.zmass.zmdm.entityManagerFactory.EntityManagerUtility;
import com.zmass.zmdm.jpaController.AdminJpaController;
import com.zmass.zmdm.jpaController.ApplicationJpaController;
import com.zmass.zmdm.jpaController.DeviceJpaController;
import com.zmass.zmdm.jpaController.ProfileApplicationMappingJpaController;
import com.zmass.zmdm.jpaController.ProfileJpaController;
import com.zmass.zmdm.jpaController.ProjectFleetJpaController;
import com.zmass.zmdm.jpaController.exceptions.NonexistentEntityException;
import com.zmass.zmdm.service.ApplicationService;
import com.zmass.zmdm.util.JsonUtil;
import com.zmss.zmdm.entity.Admin;
import com.zmss.zmdm.entity.Application;
import com.zmss.zmdm.entity.Device;
import com.zmss.zmdm.entity.Profile;
import com.zmss.zmdm.entity.ProfileApplicationMapping;
import com.zmss.zmdm.entity.ProjectFleet;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ZMF
 */
@Controller
public class WebController {

    @Autowired
    ApplicationService applicationService;

    HttpSession session;

    EntityManagerFactory emf = EntityManagerUtility.getEntity();

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public String checkLogin(HttpServletRequest request, HttpServletResponse response, Map map) {
        try {

            String email = request.getParameter("email");
            String password = request.getParameter("password");
//            String email = "akash@gmail.com";
//            String password = "123";
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setPassword(password);
//            System.out.println("login:  "+email+password);
            AdminJpaController adminJpaController = new AdminJpaController(emf);
            Admin foundUser = adminJpaController.findUser(admin);
            if (foundUser == null) {
                String message = "Username/Password is incorrect...";
//                redirectAttributes.addFlashAttribute("message", message);
                map.put("message", message);
//                System.out.println("user doesnt exists");
                return "index";
            }

            session = request.getSession(true);
            session.setAttribute("email", foundUser.getEmail());
            session.setAttribute("name", foundUser.getName());
            session.setAttribute("role", foundUser.getRole());

//            System.out.println("****** " + email + password);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/index";
        }

        return "welcome";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, Map message, String logOutMessage) {
        try {
            HttpSession session = request.getSession(false);
            response.setHeader("Cache-control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", -1);
            System.out.println("before logut: " + session.getId());
            session.invalidate();
            System.out.println("after logout: " + session.getId());

        } catch (Exception e) {
            return "redirect:/index";
        }
        System.out.println("logout msg: " + logOutMessage);
        message.put("message", logOutMessage);
        return "index";
    }

    @RequestMapping(value = "forms", method = RequestMethod.GET)
    public String forms() {
        return "forms";
    }

    @RequestMapping(value = "applicationsDetails", method = RequestMethod.GET)
    public String openApplicationDetails(HttpServletRequest request) {
        try {
            String username = request.getSession().getAttribute("name").toString();
            return "applicationsDetails";
        } catch (NullPointerException ne) {
//            ne.printStackTrace();
            return "errorPage";
        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }

    }

    @RequestMapping(value = "/getApplicationList", method = RequestMethod.POST)
    public @ResponseBody
    String getApplicationList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ApplicationJpaController applicationJpaController = new ApplicationJpaController(emf);
        List<Application> applicationList = applicationJpaController.findApplicationEntities();
        JSONArray responseList = new JSONArray();
        for (Application o : applicationList) {
//            System.out.println("application list: " + o.getName());
//            System.out.println("application list: " + o.getPackageName());
//            System.out.println("application list: " + o.getSize());
//            System.out.println("application list: " + o.getUploadedBy());
//            System.out.println("application list: " + o.getVersionName());
//            String base64Encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(o.getIcon());
//                String base64Encoded = Base64.toBase64String(o.getIcon());
//               String base64Encoded = Arrays.toString(o.getIcon());
            String base64Encoded = new String(o.getIcon());
            String sizeTemp = o.getSize();
            Float sizeInMb = (Float.parseFloat(sizeTemp) / (1024 * 1024));
            double roundOff = (double) Math.round(sizeInMb * 100) / 100;
            String setSize = roundOff + " Mb";
            o.setSize(setSize);
//            System.out.println("application list: " + base64Encoded);

//            System.out.println("application list: " + o.getUploadedDate());
//            System.out.println("application list: " + o.getId());
//            System.out.println("application list: " + o.getVersionCode());
//            System.out.println("result: " + JsonUtil.objToJson(o));
            responseList.put(o);
        }
//        System.out.println("application List: "+JsonUtil.objToJson(applicationList));
//        System.out.println("response List: "+JsonUtil.objToJson(responseList));
        return JsonUtil.objToJson(responseList);
    }

    @RequestMapping(value = "/uploadAPK", method = RequestMethod.POST)
    public synchronized String uploadAPK(HttpServletRequest request,
            @RequestParam("fileUpload") MultipartFile file, ModelMap map, Map message) throws Exception {
        //        file.getOriginalFilename().split("\\.")[1];
        if (file.isEmpty()) {
            message.put("message", "No file has been selected");
            return "applicationsDetails";
        }
        String extension = file.getOriginalFilename().split("\\.")[1];
        System.out.println("extension: " + extension);
        if (!extension.equalsIgnoreCase("apk")) {
            message.put("message", "The file type you are trying to upload is not correct");
            return "applicationsDetails";
        } else {
        }
        if (!file.isEmpty()) {
            try {
                String name = "akashAPK";
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
//                 String rootPath = System.getProperty("catalina.home");
                //path to save the profile pic
//                String rootPath = "F:\\samplePic"; //path for the profile pic set for localhost
                String rootPath = "/ZMDM_Application_Repository/";
                File dir = new File(rootPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + name + ".apk");
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                String filePath = dir.getAbsolutePath() + File.separator + name + ".apk";
                Application application = applicationService.getApkInfo(filePath);
                application.setUploadedBy(request.getSession().getAttribute("name").toString());
                if (application == null) {
                    System.out.println("apk file could not be processed.");
                    return "errorPage";
                }
                File originalPath = new File(filePath);
                String renamePath = dir.getAbsolutePath() + File.separator + application.getName() + ".apk";
                File renameFile = new File(renamePath);
                if (!renameFile.exists()) {
                    boolean success = originalPath.renameTo(renameFile);
                    if (success == false) {
                        System.out.println("file could not be renamed");
                        serverFile.delete();
                        message.put("message", "The file type you are trying to upload cannot be uploaded due to access rights issue in file.");
                        return "applicationsDetails";
//                        return "errorPage";
                    }
                }
                try {
                    ApplicationJpaController applicationJpaController = new ApplicationJpaController(emf);
                    Application applicationExistCheck = applicationJpaController.getApplicationByApplicationPackageNameAndVersionCode(application);
                    System.out.println("uploading application package name: " + applicationExistCheck.getPackageName());
                    if (!applicationExistCheck.getPackageName().equals(null)) {
                        message.put("message", "Sorry!! Cannot upload this application as it already exist with same version.");
                        return "applicationsDetails";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
//                        message.put("message", "Sorry!! Cannot upload this application as it already exist with same version.");
//                        return "applicationsDetails";
                }

                ApplicationJpaController applicationJpaController = new ApplicationJpaController(emf);
                applicationJpaController.create(application);

//                logger.info("Server File Location="
//                        + serverFile.getAbsolutePath());
                return "redirect:/applicationsDetails";
            } catch (Exception e) {
                e.printStackTrace();
                return "errorPage";
            }
        } else {

            return "errorPage";
        }

    }

    @RequestMapping(value = "deleteAPK", method = RequestMethod.POST)
    public String deleteAPK(HttpServletRequest request, HttpServletResponse response) throws NonexistentEntityException {
        String applicationId = request.getParameter("applicationId");
        System.out.println("apk to be delete id: " + applicationId);
        ApplicationJpaController applicationJpaController = new ApplicationJpaController(emf);
        Application appInfo = applicationJpaController.findApplication(Integer.parseInt(applicationId));
        String rootPath = "/ZMDM_Application_Repository/";
        File dir = new File(rootPath);
        File serverFile = new File(dir.getAbsolutePath() + File.separator + appInfo.getName() + ".apk");
        File iconFile = new File(dir.getAbsolutePath() + File.separator + appInfo.getPackageName() + appInfo.getVersionName() + ".png");
        serverFile.delete();
        applicationJpaController.destroy(Integer.parseInt(applicationId));
        System.out.println("application id: " + applicationId);
        return "redirect:/applicationsDetails";
    }

    @RequestMapping(value = "getAllInfoOfApk", method = RequestMethod.POST)
    public @ResponseBody
    String getAllInfoOfApk(HttpServletRequest request, HttpServletResponse response) {
        try {
            String applicationId = request.getParameter("applicationId");
            System.out.println("application id: " + applicationId);
            List<Profile> profileList = new LinkedList<>();
            ProfileApplicationMappingJpaController profileApplicationMappingJpaController = new ProfileApplicationMappingJpaController(emf);
            List<ProfileApplicationMapping> listOfProfileId = profileApplicationMappingJpaController.getProfileIdByApplicationId(applicationId);
            for (ProfileApplicationMapping pam : listOfProfileId) {
                System.out.println("list of profile: " + pam.getProfileId());
                System.out.println("list of application: " + pam.getApplicationId());
                ProfileJpaController profileJpaController = new ProfileJpaController(emf);
                Profile profile = profileJpaController.findProfile(pam.getProfileId());
                if (profile != null) {
                    System.out.println("mapped profile id: " + profile.getId());
                    profileList.add(profile);
                }
            }
            String profileListResponse = JsonUtil.toJson(profileList);
            System.out.println("profile List: " + profileListResponse);
            return profileListResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "profileDetails", method = RequestMethod.GET)
    public String profileDetails(HttpServletRequest request) {
        try {
            String username = request.getSession().getAttribute("name").toString();
            return "profileDetails";
        } catch (Exception e) {
            return "errorPage";
        }

    }

    @RequestMapping(value = "getProfileList", method = RequestMethod.POST)
    public @ResponseBody
    String getProfileList(HttpServletRequest request, HttpServletResponse response) {
        try {
//            System.out.println("in get profile List..");
            ProfileJpaController profileJpaController = new ProfileJpaController(emf);
            List<Profile> getProfileList = profileJpaController.findProfileEntities();
            String getProfileListResponse = JsonUtil.toJson(getProfileList);
//            System.out.println("profile list response: "+getProfileListResponse);
            return getProfileListResponse;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "getApkNamesList", method = RequestMethod.POST)
    public @ResponseBody
    String getApkNamesList(HttpServletRequest request, HttpServletResponse response) {
        try {
            ApplicationJpaController applicationJpaController = new ApplicationJpaController(emf);
            List<Application> applicationList = applicationJpaController.findApplicationEntities();
            HashMap applicationNameList = new LinkedHashMap();
            for (Application a : applicationList) {
                applicationNameList.put(a.getId(), a.getName());
            }
            String applicationNameListResponse = JsonUtil.toJson(applicationNameList);
            return applicationNameListResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "addNewProfile", method = RequestMethod.POST)
    public @ResponseBody
    String addNewProfile(@RequestParam(value = "selectedValues[]") List<Integer> selectedValues, HttpServletRequest request, HttpServletResponse response, Map message) {
        try {
            Profile profile = new Profile();
            profile.setName(request.getParameter("profileName"));
            profile.setSyncInterval(Integer.parseInt(request.getParameter("syncInterval")));
            profile.setDescription(request.getParameter("description"));
            profile.setCreatedOn(new Date());
            profile.setUpdatedBy(request.getSession().getAttribute("name").toString());
            profile.setUpdatedOn(new Date());
            profile.setStatus("ACTIVE");
            ProfileJpaController profileJpaController = new ProfileJpaController(emf);
//           String selectedValues = request.getParameter("selectedValues");
//            System.out.println("selectedValues: "+selectedValues);
            int profileId = profileJpaController.create(profile);
//            System.out.println("profile id: "+profileId);
            for (Object o : selectedValues) {
                ProfileApplicationMapping profileApplicationMapping = new ProfileApplicationMapping();
                profileApplicationMapping.setApplicationId((Integer) o);
                profileApplicationMapping.setProfileId(profileId);
                ProfileApplicationMappingJpaController profileApplicationMappingJpaController = new ProfileApplicationMappingJpaController(emf);
                profileApplicationMappingJpaController.create(profileApplicationMapping);
            }
            return "Profile added successfully";

        } catch (java.lang.NullPointerException npe) {
            npe.printStackTrace();
            message.put("message", "Your session got expired.");
            String logOutMessage = "Your session has expired.";
            logout(request, response, message, logOutMessage);
            return "Sorry! Your session expired. Please login and try again.";

        } catch (Exception e) {
            e.printStackTrace();
            return "internal server error";
        }
    }

    @RequestMapping(value = "deleteProfile", method = RequestMethod.POST)
    public @ResponseBody
    String deleteProfile(HttpServletRequest request, HttpServletResponse response) {
        try {
            String profileIdRequest = request.getParameter("profileId");
            Integer profileId = Integer.parseInt(profileIdRequest);
            ProfileJpaController profileJpaController = new ProfileJpaController(emf);
            profileJpaController.destroy(profileId);
            return "Profile deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "internal server error";
        }
    }

    @RequestMapping(value = "getProfileInfoById", method = RequestMethod.POST)
    public @ResponseBody
    String getProfileInfoById(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProfileJpaController profileJpaController = new ProfileJpaController(emf);
            Profile profileInfo = profileJpaController.findProfile(Integer.parseInt(request.getParameter("profileId")));
            ProfileApplicationMappingJpaController profileApplicationMappingJpaController = new ProfileApplicationMappingJpaController(emf);
            List<ProfileApplicationMapping> proAppMapList = profileApplicationMappingJpaController.getProfileIdByProfileId(request.getParameter("profileId"));
            HashMap<String, String> responseMap = new LinkedHashMap<>();
            responseMap.put("name", profileInfo.getName());
            responseMap.put("description", profileInfo.getDescription());
            responseMap.put("syncInterval", profileInfo.getSyncInterval().toString());
            responseMap.put("profileId", profileInfo.getId().toString());
            for (ProfileApplicationMapping pam : proAppMapList) {
                responseMap.put(pam.getApplicationId().toString(), pam.getProfileId().toString());
            }

            return JsonUtil.objToJson(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "updateProfile", method = RequestMethod.POST)
    public @ResponseBody
    String updateProfile(@RequestParam(value = "selectedValues[]") List<Integer> selectedValues, HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("in updateProfile");
            ProfileJpaController profileJpaControllerForProfileInfo = new ProfileJpaController(emf);
            Profile profile = profileJpaControllerForProfileInfo.findProfile(Integer.parseInt(request.getParameter("profileId")));
            profile.setUpdatedOn(new Date());
            profile.setUpdatedBy(request.getSession().getAttribute("name").toString());
            profile.setId(Integer.parseInt(request.getParameter("profileId")));
            profile.setDescription(request.getParameter("description"));
            profile.setName(request.getParameter("profileName"));
            profile.setSyncInterval(Integer.parseInt(request.getParameter("syncInterval")));
            ProfileJpaController profileJpaController = new ProfileJpaController(emf);
            profileJpaController.edit(profile);
            String profileId = request.getParameter("profileId");
            ProfileApplicationMappingJpaController forDeleteEntry = new ProfileApplicationMappingJpaController(emf);
            List<ProfileApplicationMapping> listForDelete = forDeleteEntry.deleteEntryListForProfileId(profileId);
            for (ProfileApplicationMapping p : listForDelete) {
                ProfileApplicationMappingJpaController deleteEntry = new ProfileApplicationMappingJpaController(emf);
                deleteEntry.destroy(p.getId());
            }
            for (Object o : selectedValues) {
                ProfileApplicationMapping profileApplicationMapping = new ProfileApplicationMapping();
                profileApplicationMapping.setApplicationId((Integer) o);
                profileApplicationMapping.setProfileId(Integer.parseInt(profileId));
                ProfileApplicationMappingJpaController profileApplicationMappingJpaController = new ProfileApplicationMappingJpaController(emf);
                profileApplicationMappingJpaController.create(profileApplicationMapping);
            }

            return "Updated successfully";

        } catch (Exception e) {
            e.printStackTrace();
            return "internal server error";
        }
    }

    @RequestMapping(value = "projectFleetDetails", method = RequestMethod.GET)
    public String projectFleetDetails(HttpServletRequest request) {
        try{
            String username = request.getSession().getAttribute("name").toString();
             return "projectFleetDetails";
        }
        catch(Exception e){
            return "errorPage";
        }
       
    }

    @RequestMapping(value = "getProfileNamesList", method = RequestMethod.POST)
    public @ResponseBody
    String getProfileNamesList() {
        try {
//            System.out.println("in profile name list controller..");
            ProfileJpaController profileJpaController = new ProfileJpaController(emf);
            List<Profile> profileList = profileJpaController.findProfileEntities();
            HashMap profileNameList = new LinkedHashMap();
            for (Profile a : profileList) {
                profileNameList.put(a.getId(), a.getName());
            }
            String profileNameListResponse = JsonUtil.toJson(profileNameList);
//            System.out.println("profile names list: " + profileNameListResponse);
            return profileNameListResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "addNewFleet", method = RequestMethod.POST)
    public @ResponseBody
    String addNewFleet(@RequestParam(value = "selectedValues[]") List<Integer> selectedValues, HttpServletRequest request, HttpServletResponse response) {
        try {
            ProjectFleet projectFleet = new ProjectFleet();
            projectFleet.setName(request.getParameter("fleetName"));
            projectFleet.setDescription(request.getParameter("description"));

            for (Object o : selectedValues) {
                projectFleet.setProfileId((Integer) o);
            }
            ProjectFleetJpaController projectFleetJpaController = new ProjectFleetJpaController(emf);
            projectFleetJpaController.create(projectFleet);

            return "fleet added successfully";

        } catch (Exception e) {
            e.printStackTrace();
            return "internal server error";
        }
    }

    @RequestMapping(value = "getFleetList", method = RequestMethod.POST)
    public @ResponseBody
    String getFleetList(HttpServletRequest request, HttpServletResponse response) {
        try {
//            System.out.println("in get profile List..");
            ProjectFleetJpaController projectFleetJpaController = new ProjectFleetJpaController(emf);
            List<ProjectFleet> getFleetList = projectFleetJpaController.findProjectFleetEntities();
            HashMap<String, String> responseList = new LinkedHashMap<>();
            JSONArray responseArray = new JSONArray();
            for (ProjectFleet pf : getFleetList) {
                responseList.put("id", pf.getId().toString());
                responseList.put("name", pf.getName());
                responseList.put("description", pf.getDescription());

                ProfileJpaController profileJpaController = new ProfileJpaController(emf);
                List<Profile> profileList = profileJpaController.findProfileEntities();
                HashMap profileNameList = new LinkedHashMap();
                for (Profile a : profileList) {
                    if (a.getId() == pf.getProfileId()) {
                        responseList.put("profileId", a.getName());
                    }
                }
                responseArray.put(responseList);
            }
            String getFleetListResponse = (responseArray).toString();
//            System.out.println("profile list response: " + getFleetListResponse);
            return getFleetListResponse;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "deleteProjectFleet", method = RequestMethod.POST)
    public @ResponseBody
    String deleteProjectFleet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String projectFleetId = request.getParameter("projectFleetId");
            Integer fleetId = Integer.parseInt(projectFleetId);
            ProjectFleetJpaController projectFleetJpaController = new ProjectFleetJpaController(emf);
            projectFleetJpaController.destroy(fleetId);
            return "Project fleet deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "internal server error";
        }
    }

    @RequestMapping(value = "getFleetById", method = RequestMethod.POST)
    public @ResponseBody
    String getFleetById(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProjectFleetJpaController projectFleetJpaController = new ProjectFleetJpaController(emf);
            ProjectFleet fleetInfo = projectFleetJpaController.findProjectFleet(Integer.parseInt(request.getParameter("fleetId")));
            HashMap<String, String> responseMap = new LinkedHashMap<>();
            responseMap.put("fleetId", fleetInfo.getId().toString());
            responseMap.put("name", fleetInfo.getName());
            responseMap.put("description", fleetInfo.getDescription());
            responseMap.put("profileId", fleetInfo.getProfileId().toString());

            return JsonUtil.objToJson(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "updateProjectFleet", method = RequestMethod.POST)
    public @ResponseBody
    String updateProjectFleet(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProjectFleetJpaController projectFleetJpaController = new ProjectFleetJpaController(emf);
            ProjectFleet projectFleet = projectFleetJpaController.findProjectFleet(Integer.parseInt(request.getParameter("fleetId")));
            projectFleet.setDescription(request.getParameter("description"));
            projectFleet.setName(request.getParameter("fleetName"));
            projectFleet.setProfileId(Integer.parseInt(request.getParameter("selectedValues")));
            projectFleet.setId(Integer.parseInt(request.getParameter("fleetId")));
            ProfileJpaController profileJpaController = new ProfileJpaController(emf);
            projectFleetJpaController.edit(projectFleet);

            return "updated successfully";

        } catch (Exception e) {
            e.printStackTrace();
            return "internal server error";
        }
    }

    @RequestMapping(value = "downloadPath/{apkName}", method = RequestMethod.GET)
    public void downloadApk(@PathVariable String apkName, HttpServletRequest request, HttpServletResponse response) {
        try {

            File file = null;
//            String type = "AEPS Merchant OnBoarding.apk";
//            String trimmedNamed = apkName.replaceAll("\\s+","");
            String EXTERNAL_FILE_PATH = "/ZMDM_Application_Repository/" + apkName + ".apk";
            System.out.println("external file path: " + EXTERNAL_FILE_PATH);
            file = new File(EXTERNAL_FILE_PATH);

            if (!file.exists()) {
                String errorMessage = "Sorry. The file you are looking for does not exist";
                System.out.println(errorMessage);
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
                outputStream.close();
                return;
            }

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                System.out.println("mimetype is not detectable, will take default");
                mimeType = "application/octet-stream";
            }

            System.out.println("mimetype : " + mimeType);

            response.setContentType(mimeType);

            /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

            /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
            //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            //Copy bytes from source to destination(outputstream in this example), closes both streams.
            FileCopyUtils.copy(inputStream, response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @RequestMapping(value = "getProfileById", method = RequestMethod.POST)
    public @ResponseBody
    String getProfileById(HttpServletRequest request, HttpServletResponse response) {
        try {

            ProfileJpaController profileJpaController = new ProfileJpaController(emf);
            Profile proInfo = profileJpaController.findProfile(Integer.parseInt(request.getParameter("profileId")));
            HashMap<String, String> responseMap = new LinkedHashMap<>();
            responseMap.put("profileId", proInfo.getId().toString());
            responseMap.put("name", proInfo.getName());
            responseMap.put("description", proInfo.getDescription());
            responseMap.put("syncInterval", proInfo.getSyncInterval().toString());

            ProfileApplicationMappingJpaController pamController = new ProfileApplicationMappingJpaController(emf);
            List<ProfileApplicationMapping> appIdList = pamController.getProfileIdByProfileId(request.getParameter("profileId"));
            for (ProfileApplicationMapping pam : appIdList) {
                responseMap.put(pam.getApplicationId().toString(), pam.getApplicationId().toString());
            }

            return JsonUtil.objToJson(responseMap);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "deviceDetails", method = RequestMethod.GET)
    public String deviceDetails(HttpServletRequest request) {
        try{
            String username = request.getSession().getAttribute("name").toString();
            return "deviceDetails";
        }
        catch(Exception e){
            return "errorPage";
        }
        
    }

    @RequestMapping(value = "getDeviceDetailList", method = RequestMethod.POST)
    public @ResponseBody
    String getDeviceDetailList() {
        try {
            DeviceJpaController deviceJpaController = new DeviceJpaController(emf);
            List<Device> listOfDevice = deviceJpaController.findDeviceEntities();
            List<Device> responseList = new LinkedList<Device>();
            for (Device d : listOfDevice) {
                ProjectFleetJpaController projectFleetJpaController = new ProjectFleetJpaController(emf);
                try {
                    ProjectFleet pf = projectFleetJpaController.findProjectFleet(d.getProjectFleetId());
                    d.setStatus(pf.getName()); //to display name of the fleet instead of project fleet id **jugaad
                } catch (Exception e) {
//                    e.printStackTrace();
                    String temp = "";
                    d.setStatus(temp); //to display name of the fleet instead of project fleet id **jugaad
                }
//                ProjectFleet pf = projectFleetJpaController.findProjectFleet(d.getProjectFleetId());
                //to display name of the fleet instead of project fleet id **jugaad
                responseList.add(d);
            }
            return JsonUtil.objToJson(responseList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "getDeviceInfo", method = RequestMethod.POST)
    public @ResponseBody
    String getDeviceInfo(HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            DeviceJpaController deviceJpaController = new DeviceJpaController(emf);
            Device deviceInfo = deviceJpaController.findDevice(Integer.parseInt(id));
            ProjectFleetJpaController projectFleetJpaController = new ProjectFleetJpaController(emf);
             Map<String, String> responseString = new LinkedHashMap<>();
            try{
                 ProjectFleet projectFleet = projectFleetJpaController.findProjectFleet(deviceInfo.getProjectFleetId());
                  responseString.put("fleetName", projectFleet.getName());
            }
            catch(Exception e){
//                e.printStackTrace();
                 responseString.put("fleetName", "N.A.");
            }
           
           
            responseString.put("imei", deviceInfo.getImei());           
            responseString.put("ownerName", deviceInfo.getOwnerName());
            responseString.put("deviceModel", deviceInfo.getDeviceModel());
            responseString.put("deviceToken", deviceInfo.getDeviceToken());
            responseString.put("district", deviceInfo.getDistrict());
            responseString.put("osVersion", deviceInfo.getOsVersion());
            responseString.put("syncStatus", deviceInfo.getSyncStatus());
            SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm");
            String strDate = formatter.format(deviceInfo.getLastSync());
            responseString.put("lastSync", strDate);
            responseString.put("longitude", deviceInfo.getLongitude().toString());
            responseString.put("latitude", deviceInfo.getLatitude().toString());
            responseString.put("osApiLevel", deviceInfo.getOsApiLevel().toString());
            responseString.put("installedApp", deviceInfo.getInstalledApp());
            responseString.put("unInstalledApp", deviceInfo.getUninstalledApp());

            return JsonUtil.objToJson(responseString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "deleteDevice", method = RequestMethod.POST)
    public @ResponseBody
    String deleteDevice(HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            DeviceJpaController deviceJpaController = new DeviceJpaController(emf);
            deviceJpaController.destroy(Integer.parseInt(id));
            return "device deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "updateDeviceFleet", method = RequestMethod.POST)
    public @ResponseBody
    String updateDeviceFleet(HttpServletRequest request) {
        try {
            String deviceId = request.getParameter("id");
            System.out.println("id: " + request.getParameter("id"));
            String fleetId = request.getParameter("fleetId");
            System.out.println("fleet id: " + request.getParameter("fleetId"));
            if (deviceId.contains(",")) {
                String[] deviceIdArray = deviceId.split(",");
                for (int i = 0; i < deviceIdArray.length; i++) {
                    System.out.println("device id: " + deviceIdArray[i]);
                    DeviceJpaController deviceJpaController = new DeviceJpaController(emf);
                    if (deviceIdArray[i] != "") {
                        Device device = deviceJpaController.findDevice(Integer.parseInt(deviceIdArray[i]));
                        device.setProjectFleetId(Integer.parseInt(fleetId));
                        deviceJpaController.edit(device);
                    }
                }
            } else {

//                if(deviceId)
                DeviceJpaController deviceJpaController = new DeviceJpaController(emf);
                System.out.println("device id: " + (deviceId));
                Device device = deviceJpaController.findDevice(Integer.parseInt(deviceId));
                device.setProjectFleetId(Integer.parseInt(fleetId));
                deviceJpaController.edit(device);
            }

            return "Updated to the fleet successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "internal server error,Please try again after sometime.";
        }
    }

    @RequestMapping(value = "getListOfFleetMappedToProfile", method = RequestMethod.POST)
    public @ResponseBody
    String getListOfFleetMappedToProfile(HttpServletRequest request) {
        try {
            String profileId = request.getParameter("profileId");
            System.out.println("profile id to get fleet : " + profileId);
            ProjectFleetJpaController projectFleetJpaController = new ProjectFleetJpaController(emf);
            List<ProjectFleet> listOfFleet = projectFleetJpaController.findProjectFleetByProfileId(Integer.parseInt(profileId));
            return JsonUtil.objToJson(listOfFleet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "welcome", method = RequestMethod.GET)
    public String welcome(HttpServletRequest request) {
        try {
            String name = request.getSession().getAttribute("name").toString();
            System.out.println("username: " + name);
            return "welcome";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPage";
        }

    }
}
