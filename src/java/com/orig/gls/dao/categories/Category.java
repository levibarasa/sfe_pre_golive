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
        String sql = "insert into categories(CATEGORYID,BANK_ID,BOCREATEDBY,BODATECREATED,BODATEMODIFIED,BOMODIFIEDBY,CATEGORYCODE,CATEGORYTYPE,CATEGORYVALUE) values(GLS_SEQ.NEXTVAL,?,?,to_date(sysdate,'dd/MM/yyyy'),to_date(sysdate,'dd/MM/yyyy'),?,?,?,?)";
        String in = bankId + "," + bocreatedby + "," + bomodifiedby + "," + categorycode + "," + categorytype + "," + categoryvalue;
        AdminDb.dbWork(sql, 6, in);
    }

    public static ArrayList getCategories(String statuscategory) {
        String sql = "select categorycode, categorytype, categoryvalue, bocreatedby, bank_id from categories where categorytype = ?";
        return AdminDb.execArrayLists(sql, 1, statuscategory, 5);
    }
}
