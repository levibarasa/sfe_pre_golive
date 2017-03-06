<link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.dao.admin.user.User, com.orig.gls.utils.App" errorPage="" %>
<%
    User frd = new User();
    String uname = (String) session.getAttribute("uname");
    ArrayList all = frd.getAllUsers(uname);
    int size = all.size();
%>

<%
    String function = (String) session.getAttribute("ufunction");
    boolean isverify = App.isVerify(function);
    boolean isModify = App.isModify(function);
    boolean isDelete = App.isDelete(function);
    boolean isCancel = App.isCancel(function);
    boolean isInquire = App.isInquire(function);
    String ishidden = "";
    String label = "";
    String ishiddenv = "";
    String ishiddenib = "";
    String isreadonlym = "readonly='true'";
    String isreadonlymcode = "";
    if (isverify || isDelete || isCancel || isInquire) {
        ishiddenv = "hidden='true'";
    }
    if (isInquire) {
        label = "Inquire";
        ishiddenib = "hidden='true'";
    }
    if (isModify) {
        label = "Modify";
        isreadonlym = "";
        isreadonlymcode = "readonly='true'";
    }
    if (isverify) {
        label = "Verify";
        isreadonlym = "";
        isreadonlymcode = "readonly='true'";
    }
    if (isDelete) {
        label = "Delete";
        isreadonlym = "";
        isreadonlymcode = "readonly='true'";
    }

%>
<script>
    function deleteDriver(id) {
        if (confirm('Modify/inquire this record?')) {
            window.location.href = 'do?MOD=BOK&ACT=domuser&did=' + id;
        }
    }
</script>
<style type="text/css">
    <!--
    .style10 {color: #000000; font-weight: bold; font-size: 12px; }
    .style11 {color: #E49C7C}
    -->
</style>
<script type="text/javascript">

    if (${umodified == 'true'}) {
        alert("User Modified Successfully");
    }
    if (${udeleted == 'true'}) {
        alert("User Deleted Successfully");
    }
</script>
<div class="header">Active Users</div>
<br/>
<br/>
<form method="post">
    <table width="95%" align="center"  style="border:#019ADD solid 2px;padding:10px;" border="1">
        <tr>
            <th bgcolor="#019ADD" scope="col"><span class="style10">Username </span></th>
            <th bgcolor="#019ADD" scope="col"><span class="style10">Work class </span></th>
            <th bgcolor="#019ADD" scope="col"><span class="style10">User Id</span></th>
            <th bgcolor="#019ADD" scope="col"><span class="style10">Action</span></th>
        </tr>
        <%            for (int i = 0; i < size; i++) {
                ArrayList one = (ArrayList) all.get(i);
        %>
        <tr style="height:30px; padding:4px;">
            <td><div align="center"><%=(String) one.get(0)%></div></td>
            <td><div align="center"><%=(int) one.get(1)%></div></td>
            <td><div align="center"><%=(String) one.get(2)%></div></td>
            <td>
                <input value="<%=label%>" type="button" class="redButton" onclick="javascript:deleteDriver(<%=(String) one.get(2)%>)" size="6"/>
            </td>
        </tr>
        <% }%>
    </table>
</form>