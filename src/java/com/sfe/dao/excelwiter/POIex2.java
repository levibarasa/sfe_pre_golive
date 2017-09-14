///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sfe.dao.excelwiter;
//
///**
// *
// * @author Levi
// */
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Iterator;
//import java.util.Properties;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
///**
// *
// * @author nidhi
// */
//public class POIex2 {
//
//    XSSFRow row;
// //   Employee e = new Employee();
//
//    public static void main(String[] args) throws IOException {
//
//        String fileName = "/home/nidhi/Downloads/Employees.xlsx";
//        POIex2 poIex2 = new POIex2();
//        poIex2.readFile(fileName);
//    }
//
//    public void readFile(String fileName) throws FileNotFoundException, IOException {
//        FileInputStream fis;
//        try {
//            System.out.println("-------------------------------READING THE SPREADSHEET-------------------------------------");
//            fis = new FileInputStream(fileName);
//            XSSFWorkbook workbookRead = new XSSFWorkbook(fis);
//            XSSFSheet spreadsheetRead = workbookRead.getSheetAt(0);
//
//            Iterator< Row> rowIterator = spreadsheetRead.iterator();
//            while (rowIterator.hasNext()) {
//                row = (XSSFRow) rowIterator.next();
//                Iterator< Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    cell.setCellType(CellType.STRING);
//                    switch (cell.getColumnIndex()) {
//                        case 0:
//                            System.out.print(
//                                    cell.getStringCellValue() + " \t\t");
//                            break;
//                        case 1:
//                            System.out.print(
//                                    cell.getStringCellValue() + " \t\t");
//                            break;
//                        case 2:
//                            System.out.print(
//                                    cell.getStringCellValue() + " \t\t");
//                            break;
//                        case 3:
//                            System.out.print(
//                                    cell.getStringCellValue() + " \t\t");
//                            break;
//                        case 4:
//                            System.out.print(
//                                    cell.getStringCellValue() + " \t\t");
//                            break;
//                    }
//                }
//                System.out.println();
//                e.empId = Integer.parseInt(row.getCell(0).getStringCellValue());
//                e.empName = row.getCell(1).getStringCellValue();
//                e.gender = row.getCell(2).getStringCellValue();
//                e.salary = row.getCell(3).getStringCellValue();
//
//                InsertRowInDB(e.empId, e.empName, e.gender, e.salary);
//            }
//            System.out.println("Values Inserted Successfully");
//
//            fis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void InsertRowInDB(int empId, String empName, String gender, String salary) {
//
//        Float salaryDB = Float.parseFloat(salary);
//        try {
//
//            Properties properties = new Properties();
//            properties.setProperty("user", "root");
//            properties.setProperty("password", "root");
//            properties.setProperty("useSSL", "false");
//            properties.setProperty("autoReconnect", "true");
//
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Attendance", properties);
//            Statement stmt = connect.createStatement();
//            PreparedStatement ps = null;
//            String sql = "INSERT INTO `Attendance`.`Employee_Master`\n"
//                    + "(`EmployeeId`,\n"
//                    + "`EmployeeName`,\n"
//                    + "`Gender`,\n"
//                    + "`Salary`)\n"
//                    + "VALUES(?,?,?,?)";
//            ps = connect.prepareStatement(sql);
//            ps.setInt(1, empId);
//            ps.setString(2, empName);
//            ps.setString(3, gender);
//            ps.setFloat(4, salaryDB);
//            ps.executeUpdate();
//            connect.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
