package com.orig.gls.dao.branch;

import com.orig.gls.conn.AdminDb;
import java.util.ArrayList;

public class Branch {

    public static ArrayList getAllBranches() {
        String sql = "select sol_id, sol_desc, bank_id from service_outlet_table order by sol_id desc";
        return AdminDb.execArrayLists(sql, 0, "", 3);
    }
     
}
