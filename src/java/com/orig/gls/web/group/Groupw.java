package com.orig.gls.web.group;

import com.orig.gls.dao.bank.Bank;
import com.orig.gls.dao.group.Group;
import com.orig.gls.dao.subgroup.SubGroup;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Groupw {

    private static final Log log = LogFactory.getLog("origlogger");

    public static void handleGoGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if ((String) session.getAttribute("uname") != null) {
            String function = request.getParameter("function");
            String grouptype = request.getParameter("grouptype");
            session.setAttribute("gfunction", function);
            switch (grouptype) {
                case "MAIN GROUP":
                    session.setAttribute("content_page", "group/mGroup_b.jsp");
                    break;
                case "SUB-GROUP":
                    session.setAttribute("content_page", "subgroup/msubGroup_b.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleMaintainGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("gverified", false);
        session.setAttribute("gadded", false);
        session.setAttribute("gdeleted", false);
        session.setAttribute("gcancelled", false);
        session.setAttribute("gmodified", false);
        session.setAttribute("sgexists", false);
        session.setAttribute("fatal", false);
        session.setAttribute("sgadded", false);
        session.setAttribute("sgverified", false);
        session.setAttribute("sgmodified", false);
        session.setAttribute("sgdeleted", false);
        session.setAttribute("sgcancelled", false);
        if ((String) session.getAttribute("uname") != null) {
            String bankId = Bank.getBankId();
            String countryCode = Bank.getContryCode(bankId);
            String delFlg = "N";
            String groupAddress = request.getParameter("groupaddress");
            int groupLoans = Integer.parseInt(request.getParameter("totalloanacs"));
            String groupName = request.getParameter("groupname");
            String groupType = request.getParameter("grouptype");
            String groupPhone = request.getParameter("groupphone");
            String grpMgrId = request.getParameter("acctmgr");
            String grpRegNo = request.getParameter("regnumber");
            Date lchgDate = new Date();
            String lchgUserId = (String) session.getAttribute("uname");
            int maxAllowedMembers = Integer.parseInt(request.getParameter("maxmembers"));
            int maxAllowedSubGrps = Integer.parseInt(request.getParameter("maxsgroups"));
            int noOfMembers = 0;
            int noOfSubGrps = 0;
            double outstandingBal = Double.parseDouble(request.getParameter("totalloanbal"));
            double savingsAmt = Double.parseDouble(request.getParameter("totalsavingsbal"));
            Date rcreTime = new Date();
            String rcreUserId = (String) session.getAttribute("uname");
            String gpRegion = request.getParameter("region");
            String groupCode = request.getParameter("groupcode");
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            Date formationDate = new Date();
            String groupCenter = request.getParameter("groupcenter");
            String groupVillage = request.getParameter("groupvillage");
            Date firstMeetDate = new Date();
            Date nxtMeetDate = new Date();
            try {
                formationDate = sdf.parse(request.getParameter("formationdate"));
                firstMeetDate = sdf.parse(request.getParameter("firstmeetingdate"));
                nxtMeetDate = sdf.parse(request.getParameter("nextmeetingdate"));
            } catch (Exception asd) {
                log.debug(asd.getMessage());
            }
            String meetTime = request.getParameter("meetingtime");
            String meetPlace = request.getParameter("meetingplace");
            String gpChair = request.getParameter("chairpersonname");
            String gpTreasurer = request.getParameter("treasurername");
            String gpSecretary = request.getParameter("secretaryname");
            String gpStatus = request.getParameter("status");
            String gpStatusCode = request.getParameter("statusreason");
            int loanAccounts = Integer.parseInt(request.getParameter("totalloanacs"));
            int savingAccounts = Integer.parseInt(request.getParameter("totalsavingacs"));
            int solId = Integer.parseInt(request.getParameter("solid"));
            String branchName = request.getParameter("branchname");
            String meetFrequency = request.getParameter("meetingfrequency");
            String function = (String) session.getAttribute("gfunction");
            String lastOper = "";
            switch (function) {
                case "ADD":
                    branchName = Group.getbranchName(String.valueOf(solId));
                    groupName = request.getParameter("groupname1");
                    if (!Group.groupExists(groupCode, groupName)) {
                        lastOper = "A";
                        String groupTypes;
                        if (groupType.equalsIgnoreCase("RETAIL GROUP")) {
                            groupTypes = "R";
                        } else {
                            groupTypes = "W";
                        }
                        if (groupTypes.equalsIgnoreCase("W")) {
                            maxAllowedSubGrps = 1;
                        }
                        if (Group.executeaddGroup(bankId, countryCode, delFlg, groupAddress, groupLoans, groupName, groupPhone, grpMgrId, grpRegNo, lchgDate, lchgUserId, maxAllowedMembers, maxAllowedSubGrps, noOfMembers, noOfSubGrps, outstandingBal, savingsAmt, rcreTime, rcreUserId, gpRegion, formationDate, groupCenter, groupVillage, firstMeetDate, meetTime, meetPlace, gpChair, gpTreasurer, gpSecretary, gpStatus, gpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, lastOper, groupTypes)) {
                            session.setAttribute("gadded", true);
                            session.setAttribute("content_page", "group/mGroup_a.jsp");
                        } else {
                            session.setAttribute("fatal", true);
                            session.setAttribute("content_page", "group/mGroup_b.jsp");
                        }
                    } else {
                        session.setAttribute("gexists", true);
                        session.setAttribute("content_page", "group/mGroup_b.jsp");
                    }
                    break;
                case "VERIFY":
                    int groupId = Group.getGroupId(groupCode, groupName);
                    lastOper = Group.getLastOper(groupId);
                    switch (lastOper) {
                        case "A":
                            Group.verifyGroup(groupId);
                            session.setAttribute("gverified", true);
                            session.setAttribute("content_page", "group/mGroup_a.jsp");
                            break;
                        case "D":
                            Group.deleteGroup(groupId, (String) session.getAttribute("uname"));
                            Group.verifyGroup(groupId);
                            session.setAttribute("gverified", true);
                            session.setAttribute("content_page", "group/mGroup_a.jsp");
                            break;
                        case "M":
                            Group.modifyGroup(groupId, (String) session.getAttribute("uname"));
                            Group.verifyGroup(groupId);
                            session.setAttribute("gverified", true);
                            session.setAttribute("content_page", "group/mGroup_a.jsp");
                            break;
                    }
                    break;
                case "MODIFY":
                    groupId = Group.getGroupId(groupCode, groupName);
                    lastOper = "M";
                    if (Group.addGroupModDetails(groupId, bankId, countryCode, delFlg, groupAddress, groupLoans, groupName, groupPhone, grpMgrId, grpRegNo, lchgDate, lchgUserId, maxAllowedMembers, maxAllowedSubGrps, noOfMembers, noOfSubGrps, outstandingBal, savingsAmt, rcreTime, rcreUserId, gpRegion, groupCode, formationDate, groupCenter, groupVillage, firstMeetDate, nxtMeetDate, meetTime, meetPlace, gpChair, gpTreasurer, gpSecretary, gpStatus, gpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, lastOper)) {
                        session.setAttribute("gmodified", true);
                        session.setAttribute("content_page", "group/mGroup_a.jsp");
                    }
                    break;
                case "DELETE":
                    groupId = Group.getGroupId(groupCode, groupName);
                    lastOper = "D";
                    if (Group.addGroupModDetails(groupId, bankId, countryCode, delFlg, groupAddress, groupLoans, groupName, groupPhone, grpMgrId, grpRegNo, lchgDate, lchgUserId, maxAllowedMembers, maxAllowedSubGrps, noOfMembers, noOfSubGrps, outstandingBal, savingsAmt, rcreTime, rcreUserId, gpRegion, groupCode, formationDate, groupCenter, groupVillage, firstMeetDate, nxtMeetDate, meetTime, meetPlace, gpChair, gpTreasurer, gpSecretary, gpStatus, gpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, lastOper)) {
                        session.setAttribute("gdeleted", true);
                        session.setAttribute("content_page", "group/mGroup_a.jsp");
                    }
                    break;
                case "CANCEL":
                    groupId = Group.getGroupId(groupCode, groupName);
                    Group.verifyGroup(groupId);
                    session.setAttribute("gcancelled", true);
                    session.setAttribute("content_page", "group/mGroup_a.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleMaintainSubGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("sgexists", false);
        session.setAttribute("fatal", false);
        session.setAttribute("sgadded", false);
        session.setAttribute("sgverified", false);
        session.setAttribute("sgmodified", false);
        session.setAttribute("sgdeleted", false);
        session.setAttribute("sgcancelled", false);
        session.setAttribute("gverified", false);
        session.setAttribute("gadded", false);
        session.setAttribute("gdeleted", false);
        session.setAttribute("gcancelled", false);
        session.setAttribute("gmodified", false);
        if ((String) session.getAttribute("uname") != null) {
            String bankId = Bank.getBankId();
            String countryCode = Bank.getContryCode(bankId);
            String delFlg = "N";
            String groupAddress = request.getParameter("groupaddress");
            int groupLoans = Integer.parseInt(request.getParameter("totalloanacs"));
            String groupName = request.getParameter("groupname");
            String subgroupName = request.getParameter("subgroupname");
            String groupPhone = request.getParameter("groupphone");
            String grpMgrId = request.getParameter("acctmgr");
            String grpRegNo = request.getParameter("regnumber");
            Date lchgDate = new Date();
            String lchgUserId = (String) session.getAttribute("uname");
            int maxAllowedMembers = Integer.parseInt(request.getParameter("maxmembers"));
            int noOfMembers = 0;
            double outstandingBal = Double.parseDouble(request.getParameter("totalloanbal"));
            double savingsAmt = Double.parseDouble(request.getParameter("totalsavingsbal"));
            Date rcreTime = new Date();
            String rcreUserId = (String) session.getAttribute("uname");
            String gpRegion = request.getParameter("region");
            String groupCode = request.getParameter("groupcode");
             SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            Date formationDate = new Date();
            String groupCenter = request.getParameter("groupcenter");
            String groupVillage = request.getParameter("groupvillage");
            Date firstMeetDate = new Date();
            try {
                formationDate = sdf.parse(request.getParameter("formationd"));
                firstMeetDate = sdf.parse(request.getParameter("firstmeetingd"));
            } catch (Exception asd) {
                log.debug(asd.getMessage());
            }
            String meetTime = request.getParameter("meetingtime");
            String meetPlace = request.getParameter("meetingplace");
            String gpChair = request.getParameter("chairpersonname");
            String gpTreasurer = request.getParameter("treasurername");
            String gpSecretary = request.getParameter("secretaryname");
            String gpStatus = request.getParameter("status");
            String gpStatusCode = request.getParameter("statusreason");
            int loanAccounts = Integer.parseInt(request.getParameter("totalloanacs"));
            int savingAccounts = Integer.parseInt(request.getParameter("totalsavingacs"));
            int solId = Integer.parseInt(request.getParameter("solid"));
            String branchName = request.getParameter("branchname");
            String meetFrequency = request.getParameter("meetingfrequency");
            String accountNo = request.getParameter("subgroupAcnt");
            String accountName = request.getParameter("subgroupacntname");
            String function = (String) session.getAttribute("gfunction");
            String subgroupCode;
            Random random = new Random();
            int randomInt = random.nextInt(100);
            String runNu = String.format("%03d", randomInt);

            if (function.equalsIgnoreCase("ADD")) {
                subgroupCode = groupCenter.substring(0, 3) + solId + runNu;
            } else {
                subgroupCode = request.getParameter("subgroupcode");
            }
            String lastOper = "";
            int groupId = Group.getGroupIdForSubGrp(groupCode);
            System.out.println(subgroupCode);
            switch (function) {
                case "ADD":
                    if (!SubGroup.subgroupExists(subgroupCode, subgroupName)) {
                        lastOper = "A";
                        if (SubGroup.executeaddSubGroup(bankId, countryCode, delFlg, groupAddress, groupLoans, subgroupName, groupPhone, grpMgrId, grpRegNo, lchgDate, lchgUserId, maxAllowedMembers, groupId, noOfMembers, outstandingBal, savingsAmt, rcreTime, rcreUserId, gpRegion, subgroupCode, formationDate, groupCenter, groupVillage, firstMeetDate, meetTime, meetPlace, gpChair, gpTreasurer, gpSecretary, gpStatus, gpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, lastOper, accountNo, accountName)) {
                            session.setAttribute("sgadded", true);
                            session.setAttribute("content_page", "group/mGroup_a.jsp");
                        } else {
                            session.setAttribute("fatal", true);
                            session.setAttribute("content_page", "subgroup/msubGroup_b.jsp");
                        }
                    } else {
                        session.setAttribute("sgexists", true);
                        session.setAttribute("content_page", "subgroup/msubGroup_b.jsp");
                    }
                    break;
                case "VERIFY":
                    int subgroupId = SubGroup.getsubGroupId(subgroupCode, subgroupName);
                    System.out.println("Sub group code " + subgroupCode);
                    groupCode = SubGroup.getgroupCode(subgroupCode, subgroupName);
                    lastOper = SubGroup.getLastOper(subgroupCode, subgroupName);
                    System.out.println("Last operation type " + lastOper);
                    System.out.println("group Id of subgroup being verified " + groupCode);
                    switch (lastOper) {
                        case "A":
                            System.out.println("Last operation type " + lastOper);
                            System.out.println("group Code of subgroup being verified " + groupCode);
                            SubGroup.verifySubGroup(subgroupId);
                            Group.addSubgroup(groupCode, (String) session.getAttribute("uname"));
                            session.setAttribute("sgverified", true);
                            session.setAttribute("content_page", "group/mGroup_a.jsp");
                            break;
                        case "D":
                            SubGroup.deleteSubGroup(subgroupId, (String) session.getAttribute("uname"));
                            SubGroup.verifySubGroup(subgroupId);
                            Group.removeSubgroup(groupCode, (String) session.getAttribute("uname"));
                            session.setAttribute("sgverified", true);
                            session.setAttribute("content_page", "group/mGroup_a.jsp");
                            break;
                        case "M":
                            SubGroup.modifySubGroup(subgroupCode, (String) session.getAttribute("uname"));
                            SubGroup.verifySubGroup(subgroupId);
                            session.setAttribute("sgverified", true);
                            session.setAttribute("content_page", "group/mGroup_a.jsp");
                            break;
                    }
                    break;
                case "MODIFY":
                    subgroupId = SubGroup.getsubGroupId(subgroupCode, subgroupName);
                    groupId = SubGroup.getGroupId(subgroupCode);
                    lastOper = "M";
                    if (SubGroup.addSubGroupModDetails(subgroupId, bankId, countryCode, delFlg, groupAddress, groupLoans, subgroupName, groupPhone, grpMgrId, grpRegNo, lchgDate, lchgUserId, maxAllowedMembers, groupId, noOfMembers, outstandingBal, savingsAmt, rcreTime, rcreUserId, gpRegion, subgroupCode, formationDate, groupCenter, groupVillage, firstMeetDate, meetTime, meetPlace, gpChair, gpTreasurer, gpSecretary, gpStatus, gpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, lastOper, accountNo, accountName)) {
                        session.setAttribute("sgmodified", true);
                        session.setAttribute("content_page", "group/mGroup_a.jsp");
                    } else {
                        session.setAttribute("fatal", true);
                        session.setAttribute("content_page", "group/mGroup_a.jsp");
                    }
                    break;
                case "DELETE":
                    subgroupId = SubGroup.getsubGroupId(subgroupCode, subgroupName);
                    groupId = SubGroup.getGroupId(subgroupCode);
                    lastOper = "D";
                    if (SubGroup.addSubGroupModDetails(subgroupId, bankId, countryCode, delFlg, groupAddress, groupLoans, subgroupName, groupPhone, grpMgrId, grpRegNo, lchgDate, lchgUserId, maxAllowedMembers, groupId, noOfMembers, outstandingBal, savingsAmt, rcreTime, rcreUserId, gpRegion, subgroupCode, formationDate, groupCenter, groupVillage, firstMeetDate, meetTime, meetPlace, gpChair, gpTreasurer, gpSecretary, gpStatus, gpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, lastOper, accountNo, accountName)) {
                        session.setAttribute("sgdeleted", true);
                        session.setAttribute("content_page", "group/mGroup_a.jsp");
                    }
                    break;
                case "CANCEL":
                    subgroupId = SubGroup.getsubGroupId(subgroupCode, subgroupName);
                    SubGroup.verifySubGroup(subgroupId);
                    session.setAttribute("sgcancelled", true);
                    session.setAttribute("content_page", "group/mGroup_a.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

}
