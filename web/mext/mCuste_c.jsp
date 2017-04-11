<link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.dao.mext.Mext" errorPage="" %>
<%
    String subgroup = (String) session.getAttribute("custId");
    String func = (String) session.getAttribute("mextren");
    String status;
    String label;
    String info;
    switch (func) {
        case "EXIT MEMBER":
            label = "Exit";
            status = "A";
            info = "Exited";
            break;
        default:
            status = "D";
            label = "Re-instate";
            info = "Re-instated";
            break;
    }
    ArrayList all = Mext.getAllMembers(subgroup, status);
    int size = all.size(); 
     String custId="";
    String acctName, savingsAcnt, loanAcnt, solId, subgroupCode, subgroupName, acctType, operAcnt;
    acctName = savingsAcnt = loanAcnt = solId = subgroupCode = subgroupName = acctType = operAcnt = "";

    for (int i = 0; i < size; i++) {
        ArrayList one = (ArrayList) all.get(0); 
        custId = (String) one.get(0);
        acctName = (String) one.get(1);
        savingsAcnt = (String) one.get(3);
        operAcnt = (String) one.get(4);
        loanAcnt = (String) one.get(2);
        solId = (String) one.get(5);
        subgroupCode = (String) one.get(6);
        subgroupName = (String) one.get(7);
        acctType = (String) one.get(8);
    }
    if (size > 0) {
%>
<style type="text/css">
    <!--
    .style10 {color: #000000; font-weight: bold; font-size: 12px; }
    .style11 {color: #E49C7C}
    .button {color: #FF9E4D}
    -->
</style>
<script type="text/javascript">
    if (${mextsuc == 'true'}) {
        alert("Member exit process completed successfully.\n Awaiting verification");
    }
    if (${mextfal == 'true'}) {
        alert("Member exit process failed.\nLoan amount is larger than compulsory savings account.\n Only a member without a running loan or whose\ncompulsory savings is more than sum of outstanding loan amount can be exited directly.");
    }
    if (${mrenfal == 'true'}) {
        alert("Member re-instatement process failed.\nCustomer has been out of group lending for more than the specified grace period.\n New erollment is the way to go.");
    }
    if (${fatal == 'true'}) {
        alert("Gross error.\n Contact system administrator");
    }
</script>
<script>
    function deleteDriver(id) {
        if (confirm('Want to Exit/re-instate this Member?')) {
            window.location.href = 'do?MOD=BOK&ACT=domodmext&did=' + id;
        }
    }
</script>
<div class="header">Selected Customer for exit</div>
<br/>
<br/>
<form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=domodmext" onsubmit=" return validatePasswords(this)">
    <div class="div">
        <table width="100%" border="0" align="left" cellpadding="5" cellspacing="1">
            <tr>
                <th colspan="12" align="left" scope="col"><div class="header">&nbsp;Customer Mapping Maintenance</div></th>
            </tr>
            <input type="hidden" name="function" id="function" value="${cfunction}" />
            <tr>
                <td>Customer No</td>
                <td>:</td>
                <td><input type="text" name="accountNo" id="accountNo" value="<%=custId%>" readonly="true"/></td>
                <td></td>
                <td>Customer Name</td>
                <td>:</td>
                <td><input type="text" name="accountName" id="accountName" onkeyup="this.value = this.value.toUpperCase();" readonly="true"  value=" <%= acctName%>"/></td>
            </tr>
            <tr>
                <td>Compulsory savings account</td>
                <td>:</td>
                <td><input type="text" name="savingsAcnt" onkeyup="this.value = this.value.toUpperCase();" id="savingsAcnt"  readonly="true" value="<%= savingsAcnt%>" /></td>
                <td></td>
                <td>Loan account number</td>
                <td>:</td>
                <td><input type="text" name="loanAcnt" onkeyup="this.value = this.value.toUpperCase();" id="loanAcnt"  readonly="true" value="<%= loanAcnt%>" /></td>
            </tr>        
            <tr>
                <td>Operative account</td>
                <td>:</td>
                <td><input type="text" name="operAcnt" onkeyup="this.value = this.value.toUpperCase();" id="operAcnt"  readonly="true" value="<%= operAcnt%>" /></td>
                <td></td>
                <td>Sol Id</td>
                <td>:</td>
                <td><input type="text" name="solid" id="solid" onkeyup="this.value = this.value.toUpperCase();" value=" <%= solId%>" readonly="true" /></td>
            </tr> 
            <tr>
                <th colspan="7" align="left" scope="col"><div class="header">&nbsp;Group Details</div></th>
            </tr>
            <tr>
                <td>Sub Group Code</td>
                <td>:</td>
                <td><input type="text" name="<%= custId + "subgroup"%>" onkeyup="this.value = this.value.toUpperCase();" value="<%= subgroupCode%>" id="<%= custId + "subgroup"%>" readonly="true" /></td>
                <td></td>
                <td>Sub Group Name</td>
                <td>:</td>
                <td><input type="text" name="<%= custId + "subgroupName"%>" onkeyup="this.value = this.value.toUpperCase();" value="<%= subgroupName%>" id="<%= custId + "subgroupName"%>" readonly="readonly" /></td>            
                <td></td>
            </tr>
            <tr>
                <td>Credit rating</td>
                <td>:</td>
                <td>
                    <label>
                        <select name="<%= custId + "custType"%>" id="<%= custId + "custType"%>">
                            <option><%= acctType%></option>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </label> <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>

            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td><input type="reset" name="Submit2" value="Reset"   class="redButton"/></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><label>
                        <input name="Submit" class="redButton" type="submit" onclick="MM_validateForm('function', '', 'R', 'accountNo', '', 'R', 'accountName', '', 'R',
                                        'solid', '', 'R', 'savingsAcnt', '', 'R', 'operAcnt', '', 'R', 'loanAcnt', '', 'R');
                                return document.MM_returnValue" value="Submit" />
                    </label></td>
                <td></td>
            </tr>
            <% } else {%>

            <tr>
                <td colspan="8">Customer cannot not <%=info%>. Check customer number.</td>
            </tr>
            <%}%>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td colspan="12"><div class="header">&nbsp;</div></td>
            </tr>
        </table>
    </div>
</form>