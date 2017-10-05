/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfe.uploads;

import com.sfe.conn.AdminDb;
import com.sfe.uploads.prop.SFEUPLOADProp;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
/**
 *
 * @author Levi
 */
public class CustomerDemo { 
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    SFEUPLOADProp pr = new SFEUPLOADProp();
    String fileName = pr.getDBProperty().getProperty("upload.customerdemo");

    public CustomerDemo() {
    }

    public static void main(String[] args) {
        CustomerDemo customerDemo = new CustomerDemo();
        customerDemo.printProducts() ; 
    }

    public void printProducts() {
        CustomerDemo customerDemo = new CustomerDemo();
         customerDemo.readFromExcel(fileName); 
    }

     public void executeCustomerDemoInsert() {
          
        CustomerDemo cd = new CustomerDemo();
        Vector dataHolder = cd.readFromExcel(fileName); 
        cd.deleteCustomerDemo();
        String custId ="";
        for (int i = 0; i < dataHolder.size(); i++) {
            Vector cellStoreVector = (Vector) dataHolder.elementAt(i);
            for (int j = 0; j < cellStoreVector.size(); j++) {
               
                if (j < cellStoreVector.size() - 1) { 
                     Cell CUST_ID = (Cell) cellStoreVector.get(j-j); 
                     Cell RM_CODE = (Cell) cellStoreVector.get((j+24) -j);
                    if(CUST_ID.getCellType() == Cell.CELL_TYPE_STRING  || RM_CODE.getCellType() == Cell.CELL_TYPE_STRING ) {
                         custId = CUST_ID.getRichStringCellValue().getString(); 
                          Cell CUST_COMU_ADDR1 = (Cell) cellStoreVector.get((j+7)-j);
            Cell CUST_NAME = (Cell) cellStoreVector.get((j+2)-j);Cell OCCUPATION = (Cell) cellStoreVector.get((j+11)-j);
            Cell PHONE_NUM1 = (Cell) cellStoreVector.get((j+21)-j);Cell CUST_FIRST_ACCT_DATE = (Cell) cellStoreVector.get((j+12)-j);
            Cell CUST_MARITAL_STATUS = (Cell) cellStoreVector.get((j+20)-j);Cell EMAIL_ID = (Cell) cellStoreVector.get((j+23)-j);
            Cell CITY = (Cell) cellStoreVector.get((j+9)-j);Cell DATE_OF_BIRTH = (Cell) cellStoreVector.get((j+18)-j);
            Cell SEGMENT = (Cell) cellStoreVector.get((j+15)-j); 
             
              cd.insertCustProd(custId,CUST_NAME.getRichStringCellValue().getString() , PHONE_NUM1.getRichStringCellValue().getString() , DATE_OF_BIRTH.getRichStringCellValue().getString() ,
           CUST_MARITAL_STATUS.getRichStringCellValue().getString() , CITY.getRichStringCellValue().getString() , SEGMENT.getRichStringCellValue().getString(), CUST_COMU_ADDR1.getRichStringCellValue().getString() 
                      , OCCUPATION.getRichStringCellValue().getString() , 
          CUST_FIRST_ACCT_DATE.getRichStringCellValue().getString(),  EMAIL_ID.getRichStringCellValue().getString() , RM_CODE.getRichStringCellValue().getString());           
                    }
               }
            }
        }
     }
     public void deleteCustomerDemo() {
        String sql = "delete from [dbo].[Cust_Demo_1]";
        AdminDb.dbWork(sql, 0, "");
    }
    public static void insertCustProd(String CUST_ID , String CUST_NAME , String PHONE_NUM1 , String DATE_OF_BIRTH ,
            String CUST_MARITAL_STATUS , String CITY , String SEGMENT, String CUST_COMU_ADDR1 , String OCCUPATION , 
            String CUST_FIRST_ACCT_DATE, String EMAIL_ID , String RM_CODE) {
    String today = sdf1.format(new Date());
    String Age ="0";  
     String Years_with_Bank  = "0";
     String RM_Code  ="0";
     String RmCod ="0";
     RmCod = getRmCode(CUST_NAME);
//    if(!CUST_FIRST_ACCT_DATE.equalsIgnoreCase("") && !DATE_OF_BIRTH.equalsIgnoreCase("") ){
//        
//        int thisyr = Integer.parseInt(today.substring(0, 4));
//        if(CUST_FIRST_ACCT_DATE.length() > 0){
//        int joinYear =Integer.parseInt(CUST_FIRST_ACCT_DATE.substring(0, 4));
//        Years_with_Bank = String.valueOf(thisyr - joinYear);
//    }
//        if(DATE_OF_BIRTH.length() > 3){
//        int yob =Integer.parseInt(DATE_OF_BIRTH.substring(0, 4));
//        
//        Age  = String.valueOf(thisyr - yob);
//        }else
//        {
//        Age ="0";
//        }
//        }
    if(!RM_CODE.equalsIgnoreCase("")|| !RM_CODE.equalsIgnoreCase(null)){
        if(RM_Code.length() < 4){
        RM_Code = RM_CODE.substring(1, 4);
        }
    } else if(RmCod.length() <4)
    {
        
    RM_Code =RmCod.substring(1, 4);
    }
//    else
//    {
//        RM_Code ="0";
//    }
        if(!custIdExists(CUST_ID)) {
        String sql = "insert into [dbo].[Cust_Demo_1] (Customer_ID,Name,Permanent_phonenumber,Age,Marital_Status,"
                + "City,Customer_Type,Permanent_Address,Occupation,First_Account_date,email_ID,RM_Code,"
                + " Years_with_Bank,BM_Code,RM_Code2,BM_Code1,Regional_Manager_code) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String in = CUST_ID + "," + CUST_NAME + "," + PHONE_NUM1 + "," + Age + "," + 
                CUST_MARITAL_STATUS + "," + CITY + "," + SEGMENT + ","
                + CUST_COMU_ADDR1 + "," + OCCUPATION + "," + CUST_FIRST_ACCT_DATE + "," + 
                EMAIL_ID + "," + RM_Code+ ","
                + Years_with_Bank + "," + RM_Code + "," + RM_Code +
                "," + RM_Code + "," + RM_Code;
        AdminDb.dbWork(sql, 17, in);
        }
    }
    
    public  static String getRmCode(String custName){
        custName =custName.trim();
      String sql = "select distinct RM_CODE from [dbo].[Customer_Product1] where  CUST_NAME = ?";
        return AdminDb.getValue(sql, 1, 1, custName);
    }
    public static Vector readFromExcel(String fileName) {
        CustomerDemo cd = new CustomerDemo();
          Vector cellVectorHolder = new Vector();
        try {
            FileInputStream myInput = new FileInputStream(new File(fileName));
            Workbook myWorkBook = new XSSFWorkbook(myInput);
            Sheet mySheet = myWorkBook.getSheetAt(0);
            Iterator rowIter = mySheet.rowIterator();
            while (rowIter.hasNext()) {
                Row myRow = (Row) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                if (myRow.getRowNum() == 0 || myRow.getRowNum() == 1 || myRow.getRowNum() == 2 || myRow.getRowNum() == 3 || myRow.getRowNum() == 4 || myRow.getRowNum() == 5 || myRow.getRowNum() == 6) {
                    continue;
                }
                   if(myRow.getCell(22).getRichStringCellValue().getString().length() > 3 &&  myRow.getCell(22).getRichStringCellValue().getString().length() > 3 &&  myRow.getCell(10).getRichStringCellValue().getString().length() > 3  ){
               
                    cd.insertCustProd(myRow.getCell(0).getStringCellValue(),myRow.getCell(1).getRichStringCellValue().getString() ,
                         myRow.getCell(19).getRichStringCellValue().getString()
                         , myRow.getCell(16).getRichStringCellValue().getString() ,
           myRow.getCell(18).getRichStringCellValue().getString() , myRow.getCell(7).getRichStringCellValue().getString() , 
           myRow.getCell(13).getRichStringCellValue().getString(), myRow.getCell(5).getRichStringCellValue().getString() 
                      , myRow.getCell(9).getRichStringCellValue().getString() , 
          myRow.getCell(10).getRichStringCellValue().getString(),  myRow.getCell(21).getRichStringCellValue().getString() , 
          myRow.getCell(22).getRichStringCellValue().getString()); 
                }      
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellVectorHolder;
    }
    public static boolean custIdExists(String Customer_ID) {
        String sql = "select count(*)cont from [dbo].[Cust_Demo_1]  where Customer_ID = ?";
        String in = Customer_ID;
        String str = AdminDb.getValue(sql, 1, 1, in);
        return Integer.parseInt(str) > 0;
    }
    
    
}