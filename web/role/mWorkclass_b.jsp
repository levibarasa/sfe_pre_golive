<link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.dao.admin.role.Role, com.orig.gls.utils.App" errorPage="" %>
<%
    String function = (String) session.getAttribute("roleAct");
    boolean isverify = App.isVerify(function);
    ArrayList all = null;
    boolean isModify = App.isModify(function);
    boolean isDelete = App.isDelete(function);
    boolean isInquire = App.isInquire(function);
    String label = "";
    if (isInquire) {
        all = Role.getAllRoles();
        label = "Inquire";
    }
    if (isModify) {
        all = Role.getAllRoles();
        label = "Modify";
    }
    if (isverify) {
        all = Role.getUnverifiedRoles();
        label = "Verify";
    }
    if (isDelete) {
        all = Role.getAllRoles();
        label = "Delete";
    }
    int size = all.size();
%>

<style type="text/css">
    <!--
    .style10 {color: #000000; font-weight: bold; font-size: 12px; }
    .style11 {color: #E49C7C}
    -->
</style>
<script>
    function deleteDriver(label, id) {
        if (confirm(label + ' role ' + id + '?')) {
            window.location.href = 'do?MOD=BOK&ACT=domrole&did=' + id;
        }
    }
</script>
<script type="text/javascript">
    if (${dupwc == 'true'}) {
        alert("ERROR\nWorkClass already exists!");
    }
    if (${wcadded == 'true'}) {
        alert("SUCCESS\nWorkClass added Successfully!");
    }
    if (${modsuc == 'true'}) {//delsuc
        alert("SUCCESS\nWorkClass Modified Successfully!");
    }
    if (${delsuc == 'true'}) {//
        alert("SUCCESS\nWorkClass Deleted Successfully!");
    }
    if (${wcver == 'true'}) {//
        alert("SUCCESS\nWorkClass Verified Successfully!");
    }
    if (${wdnexist == 'true'}) {//delsuc
        alert("ERROR\nWorkClass Does not Exist!");
    }
    if (${fatal == 'true'}) {//delsuc
        alert("ERROR\nFatal error occured!");
    }
</script>
<div class="header">Role Maintenance</div>
<br/>
<br/>
<form method="post">
    <div class="div">
        <table width="95%" align="center"  style="border:#5757D9 solid 2px;padding:10px;" border="1">
            <tr>
                <th bgcolor="#5757D9" scope="col"><span class="style10">Role</span></th>
                <th bgcolor="#5757D9" scope="col"><span class="style10">Bank Id</span></th>
                <th bgcolor="#5757D9" scope="col"><span class="style10">Rcre User</span></th>
                <th bgcolor="#5757D9" scope="col"><span class="style10">Rcre Date</span></th>
                <th bgcolor="#5757D9" scope="col"><span class="style10">Action</span></th>
            </tr>
            <%            for (int i = 0; i < size; i++) {
                    ArrayList one = (ArrayList) all.get(i);
            %>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><%=(String) one.get(0)%></div></td>
                <td><div align="center"><%=(String) one.get(3)%></div></td>
                <td><div align="center"><%=(String) one.get(2)%></div></td>
                <td><div align="center"><%=(String) one.get(1)%></div></td>
                <td>
                    <input value="<%=label%>" type="button" class="redButton" onclick="javascript:deleteDriver('<%=label%>', '<%= (String) one.get(0)%>')" size="6"/>
                </td>
            </tr>
            <% }%>
        </table>
    </div>
</form>