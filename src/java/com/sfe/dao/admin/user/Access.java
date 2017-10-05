package com.sfe.dao.admin.user;

import com.sfe.conn.AdminDb;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Access {

    private static final Log log = LogFactory.getLog("origlogger");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());

    public static String getRMCodeByWindowsUserName(String userName) {
        String sql = "select employeeID from Employee_Details where WindowsUserName = ?";
        String str = AdminDb.getValue(sql, 1, 1, userName);
        return str;
    }

    public static boolean userExists(String employeeID, String userPw) {
        String sql = "select count(*)cnt from Employee_Details where employeeID = ? and password = ?";
        String in = employeeID + "," + userPw;
        String str = AdminDb.getValue(sql, 1, 2, in);
        return Integer.parseInt(str) > 0;
    }

    public static void changePassword(String employeeID, String userPw) {
        String sql = "update Employee_Details set password = ?  where employeeID = ?";
        String in = userPw + "," + employeeID;
        AdminDb.dbWork(sql, 2, in);
    }

    public static void disableUser(int userId) {
        String sql = "update user_creds_tbl set disabled_from_date = try_convert(date, ?, 111), disabled_upto_date = try_convert(date, ?, 111) where user_id = ?";
        String in = sdf.format(new Date()) + "," + sdf.format(getExpDate(1000)) + "," + userId;
        AdminDb.dbWork(sql, 3, in);
    }

    public static void enableUser(int employeeID) {
        String sql = "update user_creds_tbl set disabled_from_date = try_convert(date, ?, 111), disabled_upto_date = try_convert(date, ?, 111) where user_id = ?";
        String in = sdf.format(getExpDate(1000)) + "," + sdf.format(getExpDate(1000)) + "," + employeeID;
        AdminDb.dbWork(sql, 3, in);
    }

    public static void logoutUser(String employeeID) {
        String sql = "delete from tbl_userCurrentLoggedIn where txt_employeeID = ?";
        AdminDb.dbWork(sql, 1, employeeID);
    }

    public static ArrayList loggedInUsers(String uname) {
        String sql = "select user_name, sol_id from logged_in_user where user_name <>?";
        return AdminDb.execArrayLists(sql, 1, uname, 2);
    }

    public static ArrayList lockedUsers() {
        String sql = "select user_name, sol_id from user_creds_tbl where num_pwd_attempts >= 3";
        return AdminDb.execArrayLists(sql, 0, "", 2);
    }

    public static void markWrongLoginAttempt(String userName) {
        String s = "select num_pwd_attempts from user_creds_tbl where user_name = ?";
        String str = AdminDb.getValue(s, 1, 1, userName);
        int k = Integer.parseInt(str);
        String sql = "update user_creds_tbl set num_pwd_attempts =? where user_name = ?";
        String in = String.valueOf(k + 1) + "," + userName;
        AdminDb.dbWork(sql, 2, in);
    }

    public static void markNotNewUser(String userName) {
        String sql = "update user_creds_tbl set new_user_flg =? where user_name = ?";
        String in = "N," + userName;
        AdminDb.dbWork(sql, 2, in);
    }

    public static void markCorrectLoginAttempt(String userName) {
        String sql = "update user_creds_tbl set num_pwd_attempts =? where user_name = ?";
        String in = "0," + userName;
        AdminDb.dbWork(sql, 2, in);
    }

    public static void MarkloginUser(String employeeID, Date loggedInTime) {
        loggedInTime = new Date();
        String loggedinTm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss", Locale.US).format(loggedInTime);
        String sql = "insert into Excalibur_Usage (employeeID,loginTime) values(?,?)";
        String in = employeeID + "," + loggedinTm;
        AdminDb.dbWork(sql, 2, in);
    }

    public static void loginUser(String employeeID, String UserName, String userType) {
        String sql = "insert into tbl_userCurrentLoggedIn (txt_UserName,txt_employeeID,txt_UserType) values(?,?,?)";
        String in = UserName + "," + employeeID + "," + userType;
        AdminDb.dbWork(sql, 3, in);
    }

    public static boolean userIsLoggedIn(String employeeID) {
        String sql = "select count(*)cnt from tbl_userCurrentLoggedIn where txt_employeeID = ?";
        String str = AdminDb.getValue(sql, 1, 1, employeeID);
        return Integer.parseInt(str) > 0;
    }

    private static Date getExpDate(int numberOfDays) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, numberOfDays);
        return cal.getTime();
    }

}
