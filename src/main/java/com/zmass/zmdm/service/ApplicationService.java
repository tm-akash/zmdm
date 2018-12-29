/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.zmdm.service;

import android.content.pm.PackageInfo;
import com.zmass.zmdm.entityManagerFactory.EntityManagerUtility;
import com.zmass.zmdm.util.UnzipUtility;
import com.zmss.zmdm.entity.Application;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import org.springframework.stereotype.Service;

/**
 *
 * @author ZMF
 */
@Service
public class ApplicationService {

    EntityManagerFactory emf = EntityManagerUtility.getEntity();

    public Application getApkInfo(String apkFilePath) {

        try {
            Application application = new Application();
            File file = new File(apkFilePath);

            String unzipPath = "/ZMDM_unzip_java/";
            File dir = new File(unzipPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

//            int fileSizeInKb = (int) (file.length() / (1024 * 1024));
            int fileSizeInKb = (int) (file.length());
            try (ApkFile apkFile = new ApkFile(new File(apkFilePath))) {
                ApkMeta apkMeta = apkFile.getApkMeta();
                application.setName(apkMeta.getLabel());
                application.setPackageName(apkMeta.getPackageName());
                int versionCode = (int) (long) apkMeta.getVersionCode();
                application.setVersionCode(versionCode);
                application.setVersionName(apkMeta.getVersionName());
                application.setUploadedDate(new Date());
//                String iconPath = "F:/unzip_java/" + apkMeta.getIcon();

                String iconPath = "/ZMDM_unzip_java/" + apkMeta.getIcon();
                System.out.println("icon Path: "+iconPath);
                UnzipUtility uz = new UnzipUtility();
//                uz.unZipIt(apkFilePath, "F://unzip_java");
                uz.unZipIt(apkFilePath, "/ZMDM_unzip_java");
                System.out.println("info: " + PackageInfo.CONTENTS_FILE_DESCRIPTOR);
                System.out.println("icon: " + iconPath);
                File iconUploadPath = new File(iconPath);
//                String imgPathHead = "F:/samplePic/";
                String imgPathHead = "/ZMDM_Application_Repository/APK_ICON/";
                String imgPathTail = ".png";
                File imagePath = new File(imgPathHead + apkMeta.getPackageName() + apkMeta.getVersionName() + imgPathTail);
                iconUploadPath.renameTo(imagePath);

//                // Reading a Image file from file system
//                FileInputStream imageInFile = new FileInputStream(iconUploadPath);
//                byte imageData[] = new byte[(int) file.length()];
//                imageInFile.read(imageData);
//                String base64Image = Base64.getEncoder().encodeToString(imageData);
                application.setIcon(apkMeta.getPackageName() + apkMeta.getVersionName() + imgPathTail);
                //application.setIcon(iconPath.getBytes());
                Integer fileSize = fileSizeInKb;
                application.setSize(fileSize.toString());
                return application;

            } catch (IOException io) {
                io.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
