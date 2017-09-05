<%@page import="com.orig.gls.utils.App"%>
<%@page import="com.orig.gls.dao.admin.user.User"%>
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.dao.customer.Customer" errorPage="" %>
<%
    
    User frd = new User();
    String uname = (String) session.getAttribute("uname");
    ArrayList all = frd.getUnverifiedUsers(uname);
    int size = all.size();
%>

<style type="text/css">
    <!--
    .style10 {color: #000000; font-weight: bold; font-size: 12px; }
    .style11 {color: #E49C7C}
    -->
</style>

<script type="text/javascript">
    if (${uverified == 'true'}) {
        alert("Record Verified Successfully");
    }
</script>
<script type="text/javascript">
    var popup;
    function getGroupCode() {
        popup = window.open("pop/grouppop_all.jsp", "Functions", "width=500,height=400");
        popup.focus();
        return false;
    }

    function getSubGroup() {
        popup = window.open("pop/subgrouppop_all.jsp", "Functions", "width=500,height=400");
        popup.focus();
        return false;
    }
</script>
<div class="header">All unverified users</div>
<br/>
<br/>
<form method="post"  action="do?MOD=BOK&ACT=domuser">
    <div class="div">
        <table width="95%" align="center"  style="border:#5757D9 solid 2px;padding:10px;" border="1">
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
                <td><div align="center"><%=(String) one.get(1)%></div></td>
                <td><div align="center"><%=(String) one.get(2)%></div></td>
                <td>
                    <input type="hidden" name="username" value="<%=(String) one.get(0)%>" />
                    <input type="hidden" name="userid" value="<%=(String) one.get(2)%>" />
                    <input type="submit" name="btn" value="Verify user" class="style10" />
                </td>
            </tr>
            <% }%>
        </table>
    </div>
</form>