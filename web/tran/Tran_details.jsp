<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Date"%> 
<%@page import="java.text.SimpleDateFormat"%>
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.dao.loan.LoanMapping,com.orig.gls.dao.tran.Transact" errorPage="" %>
<%
    LoanMapping frd = new LoanMapping();
    String subgroup = (String) session.getAttribute("subgroupCode");
    String subgroupName = (String) session.getAttribute("subgroupName");
    String fDate = (String) session.getAttribute("fromDate");
    String tDate = (String) session.getAttribute("toDate");

    SimpleDateFormat in = new SimpleDateFormat("dd-MMM-yyyy");

    try {
        Date fromDate_ = in.parse(fDate);
        Date toDate_ = in.parse(tDate);
        // output format
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = fmt.format(fromDate_);
        String toDate = fmt.format(toDate_);

        System.out.println("SubgroupCode:" + subgroup + "\n subgroupName:" + subgroupName);
        System.out.println("\n fromDate:" + fromDate + "\n toDate:" + toDate);

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
<script language="javaScript" type="text/javascript" src="include/sim.js"></script>
<script>
    function deleteDriver(id) {
        if (confirm('Enter repayment details for row id ' + id + '?')) {
            var actType = document.getElementById(id + "amt").value;
            var ract = document.getElementById(id + "actr").value;
            var subg = document.getElementById("subgroup").value;
            window.location.href = 'do?MOD=BOK&ACT=entertran&amt=' + actType + '&actr=' + ract + '&subgroup=' + subg;
        }
    }
</script>
<script type="text/javascript">
    if (${uverified == 'true'}) {
        alert("Record Verified Successfully");
    }
</script>
<div class="header">Loan repayment module</div>
<br/>
<br/>
<form method="post"  >
    <div class="div">
        <table width="95%" align="center"  style="border:#019ADD solid 2px;padding:10px;border:1;">
            <tr>
                <td>Sub Group Code</td>
                <td><input type="text" name="subgroup" onkeyup="this.value = this.value.toUpperCase();" value="<%=subgroup%>" id="subgroup" readonly="true" /></td>
                <td>Sub group Name</td>
                <td colspan="3"><input type="text" name="subgpName" onkeyup="this.value = this.value.toUpperCase();" value="<%=subgroupName%>" id="subgpName" readonly="true" size="45" /></td>
            </tr>
            <tr>
                </th>
                <th bgcolor="#019ADD" scope="col" style="width: 25%"><span class="style10">Account No </span></th>
                <th bgcolor="#019ADD" scope="col" style="width: 40%"><span class="style10">Account Name </span></th>
                <th bgcolor="#019ADD" scope="col" style="width: 15%"><span class="style10">Total Demand Amt</span></th>
                <th bgcolor="#019ADD" scope="col" style="width: 15%"><span class="style10">Paid Amount</span></th>
                <th bgcolor="#019ADD" scope="col" style="width: 15%"><span class="style10"></span></th>
            </tr>  
            <%
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        ArrayList one = (ArrayList) all.get(i);
                        String acid = (String) one.get(0);
                        String name = (String) one.get(1);
                        BigDecimal dmdAmt = new BigDecimal("" + one.get(2) + "");

            %>
            <tr style="height:30px; padding:4px;">
                <td><div>
                        <label>
                            <input type="text" name="<%=i + "actr"%>" value="<%=  acid%>" id="<%=i + "actr"%>" readonly="true"/>
                        </label>
                    </div>
                </td>
                <td><div align="center"><%= name%></div></td>
                <td><div align="center"><%= dmdAmt%></div></td>
                <td><input type="text" name="<%=i + "amt"%>" onkeypress="return isNumberKey(event)" value="" id="<%=i + "amt"%>" required="true" maxlength="8"/></td>
                <td>
                    <input value="Enter" type="button" class="redButton" onclick="MM_validateForm('<%=i + "amt"%>', '', 'R');javascript:deleteDriver(<%=i%>)" size="6"/>
                </td>
            </tr>
            <%             }
            } else { %>
            <tr>
                <td colspan="7">All repayments entered for selected sub group</td>
            </tr>           
            <%}
//end of try catch
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            %>
        </table>
    </div>
</form>