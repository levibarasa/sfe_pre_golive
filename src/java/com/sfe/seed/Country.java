package com.sfe.seed;

import com.sfe.conn.AdminDb;
import java.util.ArrayList;

public class Country {

    public static ArrayList getEnabledCountries() {
        String sql = "select country_code, country_name, rcre_time from country_codes where enabled_flg = ?";
        return AdminDb.execArrayLists(sql, 1, "Y", 3);
    }

    public static ArrayList getEnabledCurrencies() {
        String sql = "select currency_code, currency_name, rcre_time from currency_codes where enabled_flg = ?";
        return AdminDb.execArrayLists(sql, 1, "Y", 3);
    }
}
