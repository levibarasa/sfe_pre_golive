package com.sfe.dao.branch;

import com.sfe.conn.AdminDb;
import java.util.ArrayList;

public class Branch {

    public static ArrayList getAllBranches() {
        String sql = "select sol_id, sol_desc, bank_id from service_outlet_table order by sol_id desc";
        return AdminDb.execArrayLists(sql, 0, "", 3);
    }

}
