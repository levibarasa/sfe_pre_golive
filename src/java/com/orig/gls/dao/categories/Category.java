package com.orig.gls.dao.categories;

import com.orig.gls.conn.AdminDb;
import java.util.ArrayList;

public class Category {

    public static boolean categoryExists(String categorycode, String categorytype) {
        String sql = "select count(*)cnt from categories where categorycode=? and categorytype=?";
        String in = categorycode + "," + categorytype;
        String str = AdminDb.getValue(sql, 1, 2, in);
        return Integer.parseInt(str) > 0;
    }

    public static void addCategories(String bankId, String categorycode, String categorytype, String categoryvalue, String bocreatedby, String bomodifiedby) {
        String sql = "insert into categories(BANK_ID,BOCREATEDBY,BODATECREATED,BODATEMODIFIED,BOMODIFIEDBY,CATEGORYCODE,CATEGORYTYPE,CATEGORYVALUE) values(?,?,TRY_CONVERT(?, 'dd/MM/yyyy', 102) ,TRY_CONVERT(?, 'dd/MM/yyyy', 102),?,?,?,?)";
        String in = bankId + "," + bocreatedby + "," + bomodifiedby + "," + categorycode + "," + categorytype + "," + categoryvalue;
        AdminDb.dbWork(sql, 6, in);
    }

    public static ArrayList getCategories(String statuscategory) {
        String sql = "select categorycode, categorytype, categoryvalue, bocreatedby, bank_id from categories where categorytype = ?";
        return AdminDb.execArrayLists(sql, 1, statuscategory, 5);
    }
}
