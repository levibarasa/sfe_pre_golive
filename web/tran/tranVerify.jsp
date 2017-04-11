<%@page import="java.util.ArrayList"%>
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,java.util.*,
         com.orig.gls.dao.tran.Transact" errorPage="" %>
<%
    String subgroup = (String) session.getAttribute("subgroupCode");
    String subgroupName = (String) session.getAttribute("subgroupName");
    ArrayList all = Transact.verifyTransacctions(subgroup);
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
        alert("Record Posted Successfully");
    }
</script>
<div class="header">Loan repayment module</div>
<br/>
<br/>
<form method="post" action="do?MOD=BOK&ACT=posttran">
    <div class="div">
        <table width="95%" align="center"  style="border:#019ADD solid 2px;padding:10px;">
            <tr>
                <td>Sub Group Code</td>
                <td><input type="text" name="subgroup" onkeyup="this.value = this.value.toUpperCase();" value="<%=subgroup%>" id="subgroup" readonly="true" /></td>
                <td>Sub group Name</td>
                <td colspan="2"><input type="text" name="subgpName" onkeyup="this.value = this.value.toUpperCase();" value="<%=subgroupName%>" id="subgpName" readonly="true" size="45" /></td>
            </tr>
            <tr>
                <th bgcolor="#019ADD" scope="col" style="width: 15%"><span class="style10">Tran id </span></th>
                <th bgcolor="#019ADD" scope="col" style="width: 25%"><span class="style10">Tran Amount </span></th>
                <th bgcolor="#019ADD" scope="col" style="width: 15%"><span class="style10">Tran Type</span></th>
                <th bgcolor="#019ADD" scope="col" style="width: 15%"><span class="style10">Account Number</span></th>            
                <th bgcolor="#019ADD" scope="col" style="width: 15%"><span class="style10">Sub Group Code</span></th>
            </tr>  
            <%
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        ArrayList one = (ArrayList) all.get(i);%>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><%=(String) one.get(0)%></div></td>
                <td><div align="center"><%= one.get(1)%></div></td>
                <td><div align="center"><%=(String) one.get(2)%></div></td>
                <td><div align="center"><%=(String) one.get(3)%></div></td>
                <td><div align="center"><%=(String) one.get(4)%></div></td>
            </tr>

            <%             }
            } else { %>
            <tr>
                <td colspan="5">No unverified transactions available for you</td>
            </tr>           
            <%}%>
            <tr>
                <td><input type="reset" name="Submit2" value="Reset"   class="redButton"/></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><label>
                        <input name="Submit" class="redButton" type="submit" onclick="return document.MM_returnValue" value="Post" />
                    </label></td>
            </tr>
        </table>
    </div>
</form>