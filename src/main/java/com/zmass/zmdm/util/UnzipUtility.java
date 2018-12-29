/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.zmdm.util;

/**
 *
 * @author ZMF
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
 
/**
 * This utility extracts files and directories of a standard zip file to
 * a destination directory.
 * @author www.codejava.net
 *
 */
public class UnzipUtility {
   /**
     * Unzip it
     * @param zipFile input zip file
     * @param output zip file output folder
     */
    public void unZipIt(String zipFile, String outputFolder){

     byte[] buffer = new byte[1024];
    	
     try{
    		
    	//create output directory is not exists
    	File folder = new File(outputFolder);
    	if(!folder.exists()){
    		folder.mkdir();
    	}
    		
    	//get the zip file content
    	ZipInputStream zis = 
    		new ZipInputStream(new FileInputStream(zipFile));
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();
    		
    	while(ze!=null){
    			
    	   String fileName = ze.getName();
           File newFile = new File(outputFolder + File.separator + fileName);
                
           System.out.println("file unzip : "+ newFile.getAbsoluteFile());
                
            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();
              
            FileOutputStream fos = new FileOutputStream(newFile);             

            int len;
            while ((len = zis.read(buffer)) > 0) {
       		fos.write(buffer, 0, len);
            }
        		
            fos.close();   
            ze = zis.getNextEntry();
    	}
    	
        zis.closeEntry();
    	zis.close();
    		
    	System.out.println("Done");
    		
    }catch(IOException ex){
       ex.printStackTrace(); 
    }
   }    
}
