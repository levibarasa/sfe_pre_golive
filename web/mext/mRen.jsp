
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.dao.customer.Customer" errorPage="" %>
<%
    Customer frd = new Customer();
    String account = (String) session.getAttribute("account");
    ArrayList all = frd.getAccountDetails(account);
    int size = all.size();
    int custId = 0;
    String acctName, savingsAcnt, loanAcnt, solId, groupCode, groupName, subgroupCode, subgroupName;
    acctName = savingsAcnt = loanAcnt = solId = groupCode = groupName = subgroupCode = subgroupName = "";
    for (int i = 0; i < size; i++) {
        ArrayList one = (ArrayList) all.get(i);
        custId = (int) one.get(0);
        acctName = (String) one.get(1);
        savingsAcnt = (String) one.get(3);
        loanAcnt = (String) one.get(2);
        solId = (String) one.get(4);
        groupCode = (String) one.get(5);
        subgroupCode = (String) one.get(6);
        groupName = (String) one.get(7);
        subgroupName = (String) one.get(8);
    }
%>
<html>
    <head>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
        <meta http-equiv="cache-control" content="max-age=0" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />
        <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
        <meta http-equiv="pragma" content="no-cache" />
        <link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <script language="javaScript" type="text/javascript" src="include/sim.js"></script>
        <script language="javaScript" type="text/javascript" src="include/jquery-1.11.3.min.js"></script>
        <script language="javaScript" type="text/javascript" src="include/jquery-ui.min.js"></script>
        <link rel="stylesheet" type="text/css" media="all" href="include/jsDatePick_ltr.min.css">
        <script type="text/javascript" src="include/jsDatePick.min.1.3.js"></script>
        <script type="text/javascript">
            function MM_findObj(n, d) { //v4.01
                var p, i, x;
                if (!d)
                    d = document;
                if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
                    d = parent.frames[n.substring(p + 1)].document;
                    n = n.substring(0, p);
                }
                if (!(x = d[n]) && d.all)
                    x = d.all[n];
                for (i = 0; !x && i < d.forms.length; i++)
                    x = d.forms[i][n];
                for (i = 0; !x && d.layers && i < d.layers.length; i++)
                    x = MM_findObj(n, d.layers[i].document);
                if (!x && d.getElementById)
                    x = d.getElementById(n);
                return x;
            }
            function MM_validateForm() { //v4.0
                var i, p, q, nm, test, num, min, max, errors = '', args = MM_validateForm.arguments;
                for (i = 0; i < (args.length - 2); i += 3) {
                    test = args[i + 2];
                    val = MM_findObj(args[i]);
                    if (val) {
                        nm = val.name;
                        if ((val = val.value) !== "") {
                            if (test.indexOf('isEmail') !== -1) {
                                p = val.indexOf('@');
                                if (p < 1 || p === (val.length - 1))
                                    errors += '- ' + nm + ' must contain an e-mail address.\n';
                            } else if (test !== 'R') {
                                num = parseFloat(val);
                                if (isNaN(val))
                                    errors += '- ' + nm + ' must contain a number.\n';
                                if (test.indexOf('inRange') !== -1) {
                                    p = test.indexOf(':');
                                    min = test.substring(8, p);
                                    max = test.substring(p + 1);
                                    if (num < min || max < num)
                                        errors += '- ' + nm + ' must contain a number between ' + min + ' and ' + max + '.\n';
                                }
                            }
                        } else if (test.charAt(0) === 'R')
                            errors += '- ' + nm + ' is required.\n';
                    }
                }
                if (errors)
                    alert('The following error(s) occurred:\n' + errors);
                document.MM_returnValue = (errors === '');
            }

        </script>
        <script type="text/javascript">
            var popup;
            function getGroupCodeValue(id) {
                popup = window.open("customer/grouppop_all.jsp?d=" + id, "Functions", "width=500,height=400");
                popup.focus();
                return false;
            }

            function getSubGroupCodeValue(id) {
                popup = window.open("customer/subgrouppop_all.jsp?d=" + id, "Functions", "width=500,height=400");
                popup.focus();
                return false;
            }
        </script>
        <script type="text/javascript">
            if (${fatal == 'true'}) {
                alert("ERROR\nA Fatal Error Has Occured\nPlease Contact System Administrator!");
            }
            if (${modsuc == 'true'}) {
                alert("Success\Group Mapping re-instatement successful.\nawaiting verification");
            }
        </script>
    </head>
    <body>
        <h2 style="font: bold 90% 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;font-size: 16px">Customer Maintenance: Reinstatement</h2>
        <form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=domcustomer" onsubmit=" return validatePasswords(this)">
            <div class="div">
                <table width="100%" border="0" align="left" cellpadding="5" cellspacing="1">
                    <tr>
                        <th colspan="12" align="left" scope="col"><div class="header">&nbsp;Customer Maintenance</div></th>
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
                        <td>Sol Id</td>
                        <td>:</td>
                        <td><input type="text" name="solid" id="solid" onkeyup="this.value = this.value.toUpperCase();" value=" <%= solId%>" readonly="true" /></td>
                        <td colspan="4"></td>
                    </tr> 
                    <tr>
                        <th colspan="7" align="left" scope="col"><div class="header">&nbsp;Group Details</div></th>
                    </tr>
                    <tr>
                        <td>Group Code</td>
                        <td>:</td>
                        <td><input type="text" name="<%= custId + "group"%>" onkeyup="this.value = this.value.toUpperCase();" value="<%= groupCode%>" id="<%= custId + "group"%>" readonly="true" /></td>
                        <td><a href="" onclick="return getGroupCodeValue('<%= custId%>')"><img src="images/search.png"/></a></td>
                        <td>Group Name</td>
                        <td>:</td>
                        <td><input type="text" name="<%= custId + "groupName"%>" onkeyup="this.value = this.value.toUpperCase();" value="<%= groupName%>" id="<%= custId + "groupName"%>" readonly="readonly" /></td>            
                        <td></td>
                    </tr>
                    <tr>
                        <td>Sub Group Code</td>
                        <td>:</td>
                        <td><input type="text" name="<%= custId + "subgroup"%>" onkeyup="this.value = this.value.toUpperCase();" value="<%= subgroupCode%>" id="<%= custId + "subgroup"%>" readonly="true" /></td>
                        <td><a href="" onclick="return getSubGroupCodeValue('<%=custId%>')"/><img src="images/search.png"/></a></td>
                        <td>Sub Group Name</td>
                        <td>:</td>
                        <td><input type="text" name="<%= custId + "subgroupName"%>" onkeyup="this.value = this.value.toUpperCase();" value="<%= subgroupName%>" id="<%= custId + "subgroupName"%>" readonly="readonly" /></td>            
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
                                <input name="Submit" class="redButton" type="submit" onclick="MM_validateForm('solid', '', 'R', 'branchname', '', 'R', 'groupcode', '', 'R',
                                            'groupname', '', 'R', 'acctmgr', '', 'R', 'regnumber', '', 'R', 'formationdate', '', 'R', 'region', '', 'R', 'groupcenter', '', 'R',
                                            'groupvillage', '', 'R', 'groupaddress', '', 'R', 'groupphone', '', 'R', 'firstmeetingdate', '', 'R', 'nextmeetingdate', '', 'R',
                                            'meetingtime', '', 'R', 'meetingplace', '', 'R', 'maxmembers', '', 'R', 'maxsgroups', '', 'R', 'status', '', 'R', 'statusreason', '', 'R',
                                            'totalmembers', '', 'R', 'meetingfrequency', '', 'R', 'totalsavingacs', '', 'R', 'totalsavingsbal', '', 'R', 'totalloanacs', '', 'R',
                                            'totalloanbal', '', 'R');
                                    return document.MM_returnValue" value="Submit" />
                            </label></td>
                        <td></td>
                    </tr>
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
    </body>
</html>