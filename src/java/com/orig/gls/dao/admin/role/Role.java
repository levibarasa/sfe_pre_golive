package com.orig.gls.dao.admin.role;

import com.orig.gls.conn.AdminDb;
import com.orig.gls.utils.App;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Role {

    private static final Log log = LogFactory.getLog(App.LOGGER);

    public static int getRoleId() {
        return roleId;
    }

    public static void setRoleId(int aRoleId) {
        roleId = aRoleId;
    }
    static SimpleDateFormat sdf = new SimpleDateFormat(App.DATEFORMAT, Locale.getDefault());

    private static int roleId;

    public static boolean roleExists(String roleDesc) {
        String sql = "select count(*)CNT from role_profile_table where role_desc = ?";
        String str = AdminDb.getValue(sql, 1, 1, roleDesc);
        return Integer.parseInt(str) > 0;
    }

    public static String getRoleDesc(String roleId) {
        String sql = "select role_desc from role_profile_table where role_id = ?";
        return AdminDb.getValue(sql, 1, 1, roleId);
    }

    // Role exists in mod
    public static boolean roleExistsMod(String roleDesc) {
        String sql = "select count(*)CNT from role_profile_table_mod where role_desc = ?";
        String str = AdminDb.getValue(sql, 1, 1, roleDesc);
        return Integer.parseInt(str) > 0;
    }

    public static ArrayList getAllRoles() {
        String sql = "select role_desc, rcre_time, rcre_user_id, bank_id from role_profile_table where del_flg = ? and entity_cre_flg = ?";
        String in = "N,Y";
        return AdminDb.execArrayLists(sql, 2, in, 4);
    }

    public static ArrayList getUnverifiedRoles() {
        String sql = "select role_desc, rcre_time, rcre_user_id, bank_id from role_profile_table_mod";
        return AdminDb.execArrayLists(sql, 0, "", 4);
    }

    public static void deleteModRole(int roleId) {
        String sql = "delete from rol_profile_table_mod  where role_id = ?";
        AdminDb.dbWork(sql, 1, String.valueOf(roleId));
    }

    public static int addRole(String roleDesc, String entityCreFlg, String delFlg, String lchgUserId, Date lchgTime, String rcreUserId, Date rcreTime, String bankId) {
        String sql = "insert into ROLE_PROFILE_TABLE(BANK_ID, DEL_FLG, ENTITY_CRE_FLG, LCHG_TIME, LCHG_USER_ID, RCRE_TIME, RCRE_USER_ID,ROLE_DESC) values(?,?,?,?,to_date(?,'dd/MM/yyyy'),?,to_date(?,'dd/MM/yyyy'),?,?)";
        int randomInt = 0;
        int status = 0;
        String in = bankId + "," + delFlg + "," + entityCreFlg + "," + lchgTime + "," + lchgUserId + "," + rcreTime + "," + rcreUserId + "," + roleDesc;

        status = AdminDb.dbWork(sql, 8, in);
        if (status == 1) {
            randomInt = getRoleId(roleDesc);
        }
        return randomInt;
    }

    public static void addRoleMod(int roleId, String roleDesc, String entityCreFlg, String delFlg, String lchgUserId, Date lchgTime, String rcreUserId, Date rcreTime, String bankId) {
        String sql = "insert into ROLE_PROFILE_TABLE_MOD(ROLE_ID,BANK_ID, DEL_FLG, ENTITY_CRE_FLG, LCHG_TIME, LCHG_USER_ID, RCRE_TIME, RCRE_USER_ID,ROLE_DESC) values(?,?,?,?,to_date(?,'dd/MM/yyyy'),?,to_date(?,'dd/MM/yyyy'),?,?)";
        String in = roleId + "," + bankId + "," + delFlg + "," + entityCreFlg + "," + lchgTime + "," + lchgUserId + "," + rcreTime + "," + rcreUserId + "," + roleDesc;
        AdminDb.dbWork(sql, 9, in);
    }

    public static void executeAddRole(String roleDesc, String entityCreFlg, String delFlg, String lchgUserId, Date lchgTime, String rcreUserId, Date rcreTime, String bankId) {
        int role = addRole(roleDesc, entityCreFlg, delFlg, lchgUserId, lchgTime, rcreUserId, rcreTime, bankId);
        addRoleMod(role, roleDesc, entityCreFlg, delFlg, lchgUserId, lchgTime, rcreUserId, rcreTime, bankId);
        log.debug("Role Id: " + role + " Added Successfully");
    }

// Execute mapping 
    public static void executeAddMapping(String delFlg, String mopId, String mopText, String mopUrl, Date rcreTime, String rcreUser, int resId, String roleName, String rcreRep) {
        int role = addMapping(delFlg, mopId, mopText, mopUrl, rcreTime, rcreUser, resId, roleName, rcreRep);
        addMappingMod(role, delFlg, resId, mopId, mopText, mopUrl, rcreTime, rcreUser, resId, roleName);
        log.debug("Role Id: " + role + " Mapped Successfully");
    }

    public static int getMapId(String mopId) {
        String sql = "select MAP_ID from res_mapping where mopId = ?";
        String str = AdminDb.getValue(sql, 1, 1, mopId);
        return Integer.parseInt(str);
    }

    // Map Role to Menu options 
    public static int addMapping(String delFlg, String mopId, String mopText, String mopUrl, Date rcreTime, String rcreUser, int resId, String roleName, String rcreRep) {
        String sql = "insert into res_mapping(DEL_FLG, MOP_ID, MOP_TEXT, MOP_URL, RCRE_TIME,RCRE_USER, RES_ID, ROLE_NAME, RCRE_REP)values (?,?,?,?,?,to_date(?,'dd/MM/yyyy'),?,?,?,?)";
        Random randomGenerator = new Random();
        int randomInt = 0;
         String in = delFlg + "," + mopId + "," + mopText + "," + mopUrl + "," + rcreTime + "," + rcreUser + "," + String.valueOf(resId) + "," + roleName + "," + rcreRep;
        int status = AdminDb.dbWork(sql, 9, in);
        if(status == 1){
       randomInt = getMapId(mopId);
        }
        return randomInt;
    }

    public static void addMappingMod(int modId, String delFlg, int mapId, String mopId, String mopText, String mopUrl, Date rcreTime, String rcreUser, int resId, String roleName) {
        String sql = "insert into res_mapping(DEL_FLG, MOP_ID, MOP_TEXT, MOP_URL, RCRE_TIME,RCRE_USER, RES_ID, ROLE_NAME)values (?,?,?,?,?,to_date(?,'dd/MM/yyyy'),?,?,?)";
        String in =  delFlg + "," + mopId + "," + mopText + "," + mopUrl + "," + rcreTime + "," + rcreUser + "," + String.valueOf(resId) + "," + roleName;
        AdminDb.dbWork(sql, 8, in);
    }

    public static int getRoleId(String roleDesc) {
        String sql = "select role_id from role_profile_table where role_desc = ?";
        String str = AdminDb.getValue(sql, 1, 1, roleDesc);
        return Integer.parseInt(str);
    }

    public static ArrayList getRoleDetails(String role) {
        String sql = "select role_desc, rcre_time, rcre_user_id, bank_id, role_id from role_profile_table where role_desc = ?";
        ArrayList arr = AdminDb.execArrayLists(sql, 1, role, 5);
        return arr;
    }

    public static void verifyRoleDetails(int roleId) {
        String sql = "select entityCreFlg, delFlg, lchg_time, lchg_user_id from role_profile_table_mod where role_id = ?";
        ArrayList ar = AdminDb.execArrayLists(sql, 1, String.valueOf(roleId), 4);
        verifyRole(roleId, (String) ar.get(0), (String) ar.get(1), (Date) ar.get(2), (String) ar.get(3));
    }

    private static void verifyRole(int roleId, String entrcreFlg, String delFlg, Date lchgTime, String lchgUser) {
        String sql = "update role_profie_table set del_flg = ?, entity_cre_flg = ?, lchg_time = ?, lchg_user_id = ?, where role_id=?";
        String in = delFlg + "," + entrcreFlg + "," + lchgTime + "," + lchgUser + "," + roleId;
        AdminDb.dbWork(sql, 5, in);
    }

    public static ArrayList getMenuOptions() {
        String sql = "select mop_id from resources";
        return AdminDb.execArrayLists(sql, 0, "", 1);
    }
//    public static void main(String[] args) {
//        ArrayList roles = getAllRoles(); 
//     //select role_desc, rcre_time, rcre_user_id, bank_id  
//     System.out.println("--Desc --  time ---userId -- bankId--");
//        for(int w = 1; w <= roles.size(); w++){
//           String roleDsc =  roles.get(0).toString();
//            String rcretime = roles.get(1).toString();
//             String rcreuserID =  roles.get(2).toString();
//             String bankId = roles.get(3).toString();
//           System.out.println("--Desc --  time ---userId -- bankId--");      
//        }
//    }
}
