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
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Levi
 */
public class CustomerProduct {
 SFEUPLOADProp pr = new SFEUPLOADProp();
    String fileName = pr.getDBProperty().getProperty("upload.customerproduct");
    public CustomerProduct() {
    }
    public static void main(String[] args) {
       CustomerProduct customerProduct = new CustomerProduct(); 
        customerProduct.printProducts();
    }
    public void printProducts(){
        CustomerProduct customerProduct = new CustomerProduct(); 
     
        System.out.println(customerProduct.readFromExcel(fileName));
    }
    
 public static void insertCustProd(String PERIOD_YEAR,String PERIOD_MONTH,String PRIMARY_SOL_ID,String SOL_DESC,String  RM_CODE ,
         String  RM_NAME ,String  CUST_ID ,String  CUST_NAME  ,String  CUST_TYPE  ,String  AGENCY_BANKING
 , String  BUSINESS_TRANSACTION ,String  CALL_DEPOSIT , String  COLLECTION_SCHEME ,String FCY_TRANSACTION ,String FLEXI_DEPOSIT ,String HOUSING_LOAN
 , String HP_LOAN ,String  IMS ,String  INSURANCE_PREMIUM  , String  LOCKER_SECURITY ,String  NGO ,String  ONLINE_SAVERS  ,String  OVERDRAFT ,
 String PERSONAL_TRANSACTION ,String TERM_DEPOSIT ,String TERM_LOAN ,String  TRADE_FINANCE ,String YOUNG_SAVERS ,String BANK_ASSURANCE,
String CREDIT_CARDS ,String PREPAID_CARDS ,String INTERNET_BANKING ,String Customer_ID ,String BM_Code) {
         

        String sql = "INSERT INTO [dbo].[Customer_Product1] (PERIOD_YEAR,PERIOD_MONTH,PRIMARY_SOL_ID,SOL_DESC, RM_CODE ,  RM_NAME , CUST_ID , CUST_NAME  , CUST_TYPE  , AGENCY_BANKING"
                + " , BUSINESS_TRANSACTION , CALL_DEPOSIT ,  COLLECTION_SCHEME , FCY_TRANSACTION , FLEXI_DEPOSIT , HOUSING_LOAN "
                + ", HP_LOAN , IMS , INSURANCE_PREMIUM  ,  LOCKER_SECURITY , NGO , ONLINE_SAVERS  , OVERDRAFT , "
                + "PERSONAL_TRANSACTION , TERM_DEPOSIT , TERM_LOAN ,  TRADE_FINANCE , YOUNG_SAVERS , BANK_ASSURANCE  ,"
                + " CREDIT_CARDS , PREPAID_CARDS , INTERNET_BANKING , Customer_ID , BM_Code , Regional_Manager_Code ) "
                + "VALUES (?,?,? ,?,? ,? ,?,? ,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,? ,'NULL','0')";
        String in = PERIOD_YEAR+ "," +PERIOD_MONTH+ "," +PRIMARY_SOL_ID+ "," +SOL_DESC+ "," + RM_CODE + "," +  RM_NAME + "," + CUST_ID + "," + CUST_NAME  + "," + CUST_TYPE  + "," + AGENCY_BANKING+ "," + BUSINESS_TRANSACTION + "," + CALL_DEPOSIT + "," +  COLLECTION_SCHEME + "," + FCY_TRANSACTION + "," + FLEXI_DEPOSIT + "," + HOUSING_LOAN + "," + HP_LOAN + "," + IMS + "," + INSURANCE_PREMIUM  + "," +  LOCKER_SECURITY + "," + NGO + "," + ONLINE_SAVERS  + "," + OVERDRAFT + "," +PERSONAL_TRANSACTION + "," + TERM_DEPOSIT + "," + TERM_LOAN + "," +  TRADE_FINANCE + "," + YOUNG_SAVERS + "," + BANK_ASSURANCE+ "," +  CREDIT_CARDS + "," + PREPAID_CARDS + "," + INTERNET_BANKING + "," + CUST_ID ;
        AdminDb.dbWork(sql, 33, in);
    }
 public static Vector readFromExcel(String fileName) {
     CustomerProduct cp = new CustomerProduct();
        Vector cellVectorHolder = new Vector();
        try {
            FileInputStream myInput = new FileInputStream(new File(fileName));
            Workbook myWorkBook = new XSSFWorkbook(myInput);
            Sheet mySheet = myWorkBook.getSheetAt(0);
            Iterator rowIter = mySheet.rowIterator();
            while (rowIter.hasNext()) {
                Row myRow = (Row) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                if (myRow.getRowNum() == 0  || myRow.getRowNum() == 1  || myRow.getRowNum() == 2  || myRow.getRowNum() == 3  || myRow.getRowNum() == 4  || myRow.getRowNum() == 5  || myRow.getRowNum() == 6  || myRow.getRowNum() == 7  || myRow.getRowNum() == 8  || myRow.getRowNum() == 9  || myRow.getRowNum() == 10) {
                    continue;
                }
                //insert here
 cp.insertCustProd(
         myRow.getCell(0).getStringCellValue(),myRow.getCell(1).getRichStringCellValue().toString(),
         myRow.getCell(2).getRichStringCellValue().toString(),myRow.getCell(3).getRichStringCellValue().toString(),
       myRow.getCell(4).getRichStringCellValue().toString() , myRow.getCell(5).getRichStringCellValue().toString(),
       myRow.getCell(6).getRichStringCellValue().toString() ,myRow.getCell(7).getRichStringCellValue().toString() ,
       myRow.getCell(8).getRichStringCellValue().toString()  ,myRow.getCell(9).getRichStringCellValue().toString(),
  myRow.getCell(10).getRichStringCellValue().toString()  ,myRow.getCell(11).getRichStringCellValue().toString(),
  myRow.getCell(12).getRichStringCellValue().toString(),myRow.getCell(13).getRichStringCellValue().toString() ,
   myRow.getCell(14).getRichStringCellValue().toString(),myRow.getCell(15).getRichStringCellValue().toString(),
  myRow.getCell(16).getRichStringCellValue().toString() ,myRow.getCell(17).getRichStringCellValue().toString(),
  myRow.getCell(18).getRichStringCellValue().toString()  ,myRow.getCell(19).getRichStringCellValue().toString(),
  myRow.getCell(20).getStringCellValue() ,myRow.getCell(21).getStringCellValue()
         ,myRow.getCell(22).getStringCellValue() ,
 myRow.getCell(23).getStringCellValue(),myRow.getCell(24).getStringCellValue() ,myRow.getCell(25).getStringCellValue(),myRow.getCell(25).getStringCellValue() ,myRow.getCell(27).getStringCellValue() ,
         myRow.getCell(28).getStringCellValue(),
myRow.getCell(29).getStringCellValue() ,myRow.getCell(30).getStringCellValue() ,myRow.getCell(31).getStringCellValue() ,"0","0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellVectorHolder;
    }
    
}
