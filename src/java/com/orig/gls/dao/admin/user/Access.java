package com.orig.gls.dao.admin.user;

import com.orig.gls.conn.AdminDb;
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

    public static boolean userExists(String userName, String userPw) {
        String sql = "select count(*)cnt from user_creds_tbl where user_name = ? and user_pw = ?";
        String in = userName + "," + userPw;
        String str = AdminDb.getValue(sql, 1, 2, in);
        return Integer.parseInt(str) > 0;
    }

    public static void changePassword(String userName, String userPw) {
        String sql = "update user_creds_tbl set user_pw = ?, new_user_flg = ? where user_name = ?";
        String in = userPw + ",N," + userName;
        AdminDb.dbWork(sql, 3, in);
    }

    public static void disableUser(int userId) {
        String sql = "update user_creds_tbl set disabled_from_date = ?, disabled_upto_date = ? where user_id = ?";
        String in = sdf.format(new Date()) + "," + getExpDate(1000) + "," + userId;
        AdminDb.dbWork(sql, 3, in);
    }

    public static void enableUser(int userId) {
        String sql = "update user_creds_tbl set disabled_from_date = ?, disabled_upto_date = ? where user_id = ?";
        String in = getExpDate(1000) + "," + getExpDate(1000) + "," + userId;
        AdminDb.dbWork(sql, 3, in);
    }

    public static void logoutUser(String userName) {
        String sql = "delete from logged_in_user where user_name = ?";
        AdminDb.dbWork(sql, 1, userName);
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

    public static void loginUser(String userName, String solId, Date loggedInTime, String sessionId) {
        String sql = "insert into logged_in_user(logged_in_time, session_id, sol_id, user_name) values (GETDATE(),?,?,?)";
        String in = sessionId + "," + solId + "," + userName;
        AdminDb.dbWork(sql, 3, in);
    }

    public static boolean userIsLoggedIn(String userName) {
        String sql = "select count(*)cnt from logged_in_user where user_name = ?";
        String str = AdminDb.getValue(sql, 1, 1, userName);
        return Integer.parseInt(str) > 0;
    }

    private static Date getExpDate(int numberOfDays) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, numberOfDays);
        return cal.getTime();
    }
}
