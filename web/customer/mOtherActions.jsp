<link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.dao.customer.Customer, com.orig.gls.utils.App" errorPage="" %>
<%
    Customer frd = new Customer();
    ArrayList all = frd.getAllVerfiedAccounts();
    int size = all.size();
    String function = (String) session.getAttribute("cfunction");
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
        label = "Exit";
        isreadonlym = "";
        isreadonlymcode = "readonly='true'";
    }
%>

<style type="text/css">
    <!--
    .style10 {color: #000000; font-weight: bold; font-size: 12px; }
    .style11 {color: #E49C7C}
    -->
</style>
<script>
    function deleteDriver(id) {
        if (confirm('Modify/inquire this customer record?')) {
            window.location.href = 'do?MOD=BOK&ACT=domodcustomer&did=' + id;
        }
    }
</script>
<script type="text/javascript">
    if (${delsuc == 'true'}) {
        alert("Success\nAccount mapping deleted successful\nawaiting verification");
    }
    if (${fatal == 'true'}) {
        alert("ERROR\nA Fatal Error Has Occured\nPlease Contact System Administrator!");
    }
    if (${modsuc == 'true'}) {
        alert("Success\Group Mapping details successfuly modified\nawaiting verification");
    }
</script>
<div class="header">Mapped Accounts Panel</div>
<br/>
<br/>
<form method="post">
    <div class="div">
        <table width="95%" align="center"  style="border:#5757D9 solid 2px;padding:10px;" border="1">
            <tr>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Customer Number </span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Customer Name </span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Customer Branch Id</span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Sub Group Code</span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Action</span></th>
            </tr>
            <%            for (int i = 0; i < size; i++) {
                    ArrayList one = (ArrayList) all.get(i);
            %>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><%=(String) one.get(0)%></div></td>
                <td><div align="center"><%=(String) one.get(1)%></div></td>
                <td><div align="center"><%=(String) one.get(2)%></div></td>
                <td><div align="center"><%=(String) one.get(3)%></div></td>
                <td>
                    <input value="<%=label%>" type="button" class="redButton" onclick="javascript:deleteDriver('<%= (String) one.get(0)%>')" size="6"/>
                </td>
            </tr>
            <% }%>
        </table>
    </div>
</form>