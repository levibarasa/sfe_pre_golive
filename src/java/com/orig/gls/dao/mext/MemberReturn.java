package com.orig.gls.dao.mext;

import com.orig.common.methods.CommonMethods;
import com.orig.gls.conn.AdminDb;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MemberReturn {

    private static final Log log = LogFactory.getLog("origlogger");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

    public static int considerDaysOut(int custId) {
        int numOfDays = 0;
        try {        
        String sql = "select last_modified_date from general_acct_mast_table where cust_id = ?";
        String da = AdminDb.getValue(sql, 1, 1, String.valueOf(custId));
        Date lastModDate = sdf.parse(da);
            Date curr = new Date();
            numOfDays = CommonMethods.numDaysOut(curr, lastModDate);
           
        } catch(Exception asd) {
            log.debug(asd.getMessage());
        }
        return numOfDays;
    }

    public static boolean retainClass(int custId, int numDays) {
        return considerDaysOut(custId) < numDays;
    }
}
