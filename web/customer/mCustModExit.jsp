<link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.dao.customer.Customer" errorPage="" %>
<%
    Customer frd = new Customer();
    ArrayList all = frd.getAllUnMappedExitAccountMod((String) session.getAttribute("uname"));
    int size = all.size();
%>

<style type="text/css">
    <!--
    .style10 {color: #000000; font-weight: bold; font-size: 12px; }
    .style11 {color: #E49C7C}
    -->
</style>

<!--script type="text/javascript">
    if (${cverified == 'true'}) {
        alert("Record Verified Successfully");
    }
    if (${cmexitverified == 'true'}) {
        alert("Member Exit Verified Successfully");
    }
</script-->
<script>
    function deleteDriver(id) {
        if (confirm('Want to Verify this Record?')) {
            window.location.href = 'do?MOD=BOK&ACT=domodcustomer&did=' + id;
        }
    }
</script>
<div class="header">Unverified Mapping and Modification</div>
<br/>
<br/>
<form method="post">
    <div class="div">
        <table width="95%" align="center"  style="border:#5757D9 solid 2px;padding:10px;" border="1">
            <tr>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Customer Number </span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Customer Name </span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Account Number </span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Account Open Date</span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Account Branch Id</span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Scheme Type</span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Scheme Code</span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Sub Group Id</span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Status</span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Action</span></th>
            </tr>
            <%            for (int i = 0; i < size; i++) {
                    ArrayList one = (ArrayList) all.get(i);
            %>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><%=(String) one.get(0)%></div></td>
                <td><div align="center"><%=(String) one.get(2)%></div></td>
                <td><div align="center"><%=(String) one.get(1)%></div></td>
                <td><div align="center"><%=(String) one.get(3)%></div></td>
                <td><div align="center"><%=(String) one.get(4)%></div></td>
                <td><div align="center"><%=(String) one.get(5)%></div></td>
                <td><div align="center"><%=(String) one.get(6)%></div></td>
                <td><div align="center"><%=(String) one.get(7)%></div></td>
                <td><div align="center"><%=(String) one.get(8)%></div></td>
                <td>
                    <input value="Verify" type="button" class="redButton" onclick="javascript:deleteDriver('<%=(String) one.get(0)%>')" size="6"/>
                </td>
            </tr>
            <% }%>
        </table>
    </div>
</form>