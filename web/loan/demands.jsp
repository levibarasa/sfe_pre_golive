<link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.dao.loan.LoanMapping" errorPage="" %>
<%
    LoanMapping frd = new LoanMapping();
    String subgroup = (String) session.getAttribute("subgroupCode");
    String subgroupName = (String) session.getAttribute("subgroupName");
    String fromDate = (String) session.getAttribute("fromDate");
    String toDate = (String) session.getAttribute("toDate");
    ArrayList all = frd.getAlldemandsSubGroup(subgroup, fromDate, toDate);
    int size = all.size();
%>
<style type="text/css">
    <!--
    .style10 {color: #000000; font-weight: bold; font-size: 12px; }
    .style11 {color: #E49C7C}
    .button {color: #FF9E4D}
    -->
</style>
<script type="text/javascript">
    if (${uverified == 'true'}) {
        alert("Record Verified Successfully");
    }
</script>
<div class="header">Loan demands report</div>
<br/>
<br/>
<form method="post" action="do?MOD=BOK&ACT=domuser">
    <div class="div">
        <table width="95%" align="center"  style="border:#019ADD solid 2px;padding:10px;">
            <tr>
                <td>Sub Group Code</td>
                <td><input type="text" name="subgroup" onkeyup="this.value = this.value.toUpperCase();" value="<%=subgroup%>" id="subgroup" readonly="true" /></td>
                <td><input type="text" name="subgroupName" onkeyup="this.value = this.value.toUpperCase();" value="<%=subgroupName%>" id="subgroupName" readonly="true" size="45"/></td>

            </tr>
            <tr>
                <th bgcolor="#019ADD" scope="col" style="width: 25%"><span class="style10">Account No </span></th>
                <th bgcolor="#019ADD" scope="col" style="width: 40%"><span class="style10">Account Name </span></th>
                <th bgcolor="#019ADD" scope="col" style="width: 15%"><span class="style10">Demand Amount</span></th>
            </tr>        <% for (int i = 0; i < size; i++) {
                    ArrayList one = (ArrayList) all.get(i);%>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><%=(String) one.get(0)%></div></td>
                <td><div align="center"><%=(String) one.get(1)%></div></td>
                <td><div align="center"><%=(String) one.get(2)%></div></td>
            </tr>
            <% }
            %>
        </table>
    </div>
</form>