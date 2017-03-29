/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.prop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Levi
 */
public class TestFile {
    private static final Log log = LogFactory.getLog("origlogger");
    
   public static ArrayList getUsersList() {
        ArrayList arr = new ArrayList();
        GlsFile pr = new GlsFile();
        String uploadFilepath = pr.getDBProperty().getProperty("user.file");
        String line;
        try {
            FileInputStream fs = new FileInputStream(uploadFilepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            String[] split;
            while ((line = br.readLine()) != null) {
                split = line.split("\\|");
                ArrayList one = new ArrayList();
                one.add(split[0]);
                one.add(split[1]);
                one.add(split[2]);
                arr.add(one);
            }
            fs.close();
			br.close();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
        }
        return arr;
    } 
   
   public static ArrayList getLoanDemandsList() {
        ArrayList arr = new ArrayList();
        GlsFile pr = new GlsFile();
        String uploadFilepath = pr.getDBProperty().getProperty("loandemand.file");
        String line;
        try {
            FileInputStream fs = new FileInputStream(uploadFilepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            String[] split;
            while ((line = br.readLine()) != null) {
                split = line.split("\\|");
                ArrayList one = new ArrayList();
                one.add(split[0]); one.add(split[1]);one.add(split[2]);  
                arr.add(one);
            }
            fs.close();
			br.close();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
        }
        return arr;
    }
   
    public static void main(String[] args) {
        ArrayList user = getLoanDemandsList();
        for (int k = 0; k < user.size(); k++) {
            ArrayList one = (ArrayList) user.get(k);
            
             String ACID = (String) one.get(2);
            String Disb_date = (String) one.get(1);
            String Disb_amt = (String) one.get(0);
            System.out.println((String) one.get(0)+" "+(String) one.get(1)+" "+(String) one.get(2));
    }
    }
}
