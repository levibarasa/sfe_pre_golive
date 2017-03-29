/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.fileuploads;

import com.orig.gls.prop.GlsFile;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Levi
 */
public class FileUpload {

    private static final Log log = LogFactory.getLog("origlogger");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());

    public ArrayList getUsersList() {
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

    public ArrayList getCustomersList() {
        ArrayList arr = new ArrayList();
        GlsFile pr = new GlsFile();
        String uploadFilepath = pr.getDBProperty().getProperty("customer.file");
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
                one.add(split[3]);
                one.add(split[4]);
                one.add(split[5]);
                one.add(split[6]);
                one.add(split[7]);
                one.add(split[8]);
                one.add(split[9]);
                one.add(split[10]);
                one.add(split[11]);
                one.add(split[12]);
                one.add(split[13]);
                one.add(split[14]);
                one.add(split[15]);
                one.add(split[16]);
                one.add(split[17]);
                one.add(split[18]);
                one.add(split[19]);
                one.add(split[20]);
                one.add(split[21]);

                arr.add(one);
            }
            fs.close();
            br.close();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
        }
        return arr;
    }

    public ArrayList getRolesList() {
        ArrayList arr = new ArrayList();
        GlsFile pr = new GlsFile();
        String uploadFilepath = pr.getDBProperty().getProperty("role.file");
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
                one.add(split[3]);
                one.add(split[4]);
                one.add(split[5]);
                one.add(split[6]);
                one.add(split[7]);
                one.add(split[8]);
                arr.add(one);
            }
            fs.close();
            br.close();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
        }
        return arr;
    }

    public ArrayList getLoanDemandsList() {
        ArrayList arr = new ArrayList();
        GlsFile pr = new GlsFile();
        String uploadFilepath = pr.getDBProperty().getProperty("loandemand.file");
        String line;
        try {
            FileInputStream fs = new FileInputStream(uploadFilepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            String[] split;
            while ((line = br.readLine()) != null && (line.trim().length() >= 1)) {
                split = line.split("\\|");
                ArrayList one = new ArrayList();
                one.add(split[0].trim());
                one.add(split[1].trim());
                one.add(split[2].trim());
                arr.add(one);
            }
            fs.close();
            br.close();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
        }
        return arr;
    }

    public ArrayList getLoansList() {
        ArrayList arr = new ArrayList();
        GlsFile pr = new GlsFile();
        String uploadFilepath = pr.getDBProperty().getProperty("loan.file");
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
                one.add(split[3]);
                one.add(split[4]);
                one.add(split[5]);
                arr.add(one);
            }
            fs.close();
            br.close();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
        }
        return arr;
    }

    public ArrayList getSomeList() {
        ArrayList arr = new ArrayList();
        GlsFile pr = new GlsFile();
        String uploadFilepath = pr.getDBProperty().getProperty("customer.file");
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
                one.add(split[3]);
                one.add(split[4]);
                one.add(split[5]);
                one.add(split[6]);
                one.add(split[7]);
                one.add(split[8]);
                one.add(split[9]);
                one.add(split[10]);
                one.add(split[11]);
                one.add(split[12]);
                one.add(split[13]);
                one.add(split[14]);
                one.add(split[15]);
                one.add(split[16]);
                one.add(split[17]);
                one.add(split[18]);
                one.add(split[19]);
                one.add(split[20]);
                one.add(split[21]);

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

        FileUpload fu = new FileUpload();

        ArrayList all = fu.getRolesList();

        for (int i = 0; i < all.size(); i++) {
            ArrayList one = (ArrayList) all.get(i);

             
            System.out.println((String) one.get(0) + " " + (String) one.get(1) + " " + (String) one.get(2)+ " " + (String) one.get(3) + " " + (String) one.get(4));
        }

    }
}
