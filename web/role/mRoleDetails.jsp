
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.dao.admin.role.Role,com.orig.gls.utils.App" errorPage="" %>
<%
    String function = (String) session.getAttribute("roleAct");
    boolean isModify = App.isModify(function);
    boolean isDelete = App.isDelete(function);
    boolean isInquire = App.isInquire(function);
    String role = (String) session.getAttribute("rlnm");
    String roleId = (String) session.getAttribute("rlId");
    String hidden = "";
    String label = "";
    String readonly = "";
    if (isInquire) {
        hidden = "hidden='true'";
        readonly = "readonly='true'";
        label = "Inquire";
    }
    if (isModify) {
        label = "Modify";
    }
    if (isDelete) {
        readonly = "readonly='true'";
        label = "Delete";
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
            if (${dupwc == 'true'}) {
                alert("ERROR\nUnverified workclass modification detected. Verify before proceeding!");
            }
            if (${modsuc == 'true'}) {//delsuc
                alert("SUCCESS\nWorkClass Modified Successfully!");
            }
            if (${delsuc == 'true'}) {//
                alert("SUCCESS\nWorkClass Deleted Successfully!");
            }
            if (${wdnexist == 'true'}) {//delsuc
                alert("ERROR\nWorkClass Does not Exist!");
            }
        </script>
    </head>
    <body>
        <h2 style="font: bold 90% 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;font-size: 16px">Role Maintenance</h2>
        <form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=dommrole" onsubmit=" return validatePasswords(this)">
            <div class="div">
                <table width="100%" border="0" align="left" cellpadding="5" cellspacing="1">
                    <tr>
                        <th colspan="12" align="left" scope="col"><div class="header">&nbsp;Role Maintenance</div></th>
                    </tr>
                    <td><input type="hidden" name="func" id="func" readonly="true"  value=" <%= function%>"/></td>
                    <tr>
                        <td>Role</td>
                        <td>:</td>
                        <td><input type="text" name="rolename" id="rolename" value="<%=role%>" <%=readonly%>  onkeyup="this.value = this.value.toUpperCase();" maxlength="6"/></td>
                        <td></td>
                        <td>Role Id</td>
                        <td>:</td>
                        <td><input type="text" name="roleid" id="roleid" readonly="true"  value=" <%= roleId%>"/></td>
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
                        <td><input type="reset" name="Submit2" value="Reset"   class="redButton" <%= hidden%>/></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><label>
                                <input name="<%= label%>" <%= hidden%> class="redButton" type="submit" onclick="MM_validateForm('solid', '', 'R', 'branchname', '', 'R', 'groupcode', '', 'R',
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