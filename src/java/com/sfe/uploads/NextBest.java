/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfe.uploads;

/**
 *
 * @author Levi
 */
import com.sfe.conn.AdminDb;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.sfe.uploads.prop.SFEUPLOADProp;

public class NextBest {

    SFEUPLOADProp pr = new SFEUPLOADProp();
    String fileName = pr.getDBProperty().getProperty("upload.nextbest");

    public NextBest() {
    }

    public static void main(String[] args) {
        NextBest nb = new NextBest();
        nb.executeNextBestInsert();
    }

    public void executeNextBestInsert() {
        NextBest nb = new NextBest();
        Vector dataHolder = nb.readFromExcel(fileName);
        nb.deleteNextBest();
        for (int i = 0; i < dataHolder.size(); i++) {
            Vector cellStoreVector = (Vector) dataHolder.elementAt(i);
            for (int j = 0; j < cellStoreVector.size(); j++) {
                if (j < cellStoreVector.size() - 1) {
                    Cell CustId = (Cell) cellStoreVector.get(j);
                    Cell product = (Cell) cellStoreVector.get(j + 1);
                    nb.insertNextBest(CustId.getRichStringCellValue().getString(), product.getRichStringCellValue().getString());
                }

            }
        }
    }

    public void insertNextBest(String CustId, String product) {
        String sql = "insert into [dbo].[Next_Best_Product] (CUST_ID, PRODUCT) values(?,?)";
        String in = CustId + "," + product;
        AdminDb.dbWork(sql, 2, in);

    }

    public void deleteNextBest() {
        String sql = "delete from [dbo].[Next_Best_Product]";
        AdminDb.dbWork(sql, 0, "");
    }

    public static Vector readFromExcel(String fileName) {
        Vector cellVectorHolder = new Vector();
        try {
            FileInputStream myInput = new FileInputStream(new File(fileName));
            Workbook myWorkBook = new XSSFWorkbook(myInput);
            Sheet mySheet = myWorkBook.getSheetAt(0);
            Iterator rowIter = mySheet.rowIterator();
            while (rowIter.hasNext()) {
                Row myRow = (Row) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                if (myRow.getRowNum() == 0) {//|| myRow.getRowNum() == 1) {
                    continue;
                }
                Vector cellStoreVector = new Vector();
                while (cellIter.hasNext()) {
                    Cell myCell = (Cell) cellIter.next();
                    cellStoreVector.addElement(myCell);
                }
                cellVectorHolder.addElement(cellStoreVector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellVectorHolder;
    }
}
