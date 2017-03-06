<%@page import="com.orig.gls.utils.App,com.orig.gls.dao.admin.user.User, com.orig.gls.dao.admin.role.Role, java.util.*"%>
<%
    ArrayList all = Role.getMenuOptions();
    int size = all.size();
    ArrayList role = User.getRoles();
    int siz = role.size();
%>
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
    <script type="text/javascript">
        var popup;
        function getValue() {
            var func = document.getElementById("function").value;
            if (func === "VERIFY") {
                popup = window.open("pop/wcpop_ver.jsp", "Workclasses", "width=500,height=400");
            } else {
                popup = window.open("pop/wcpop.jsp", "Workclasses", "width=500,height=400");
            }
            if (func === "ADD") {
                window.opener.document.form1.rolename.readOnly = false;
            } else {
                window.opener.document.form1.rolename.readOnly = true;
            }
            popup.focus();
            return false;
        }
        function getFunctionValue() {
            popup = window.open("role/function.jsp", "Workclasses", "width=500,height=400");
            popup.focus();
            return false;
        }
    </script>
    <script type="text/javascript">
        function validatePasswords(theForm) {
            if (theForm.Username.value.length < 6) {
                alert("Username Cannot be Less than 6characters");
                theForm.Username.focus();
                return false;
            }
            if (theForm.Password.value !== theForm.Password1.value)
            {
                alert("Passwords Do Not Match");
                theForm.Password1.focus();
                return false;
            }
            if (theForm.function.selectedIndex < 0)
            {
                alert("Function Must be Selected.");
                theForm.function.focus();
                return false;
            }
            if (theForm.function.selectedIndex === 0)
            {
                alert("Function Must be Selected.");
                theForm.function.focus();
                return false;
            }
            return true;
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
        if (${wdnexist == 'true'}) {//delsuc
            alert("ERROR\nWorkClass Does not Exist!");
        }
        if (${fatal == 'true'}) {//delsuc
            alert("ERROR\nFatal error occured!");
        }
    </script>
    <style type="text/css">
        .menuOptns{
            width: 100px;
            height:100px;
            overflow-y:scroll;  
            margin-left:5em;
            overflow-x:scroll;
            padding-bottom:1px;
        }
    </style>
</head>
<h2 style="font: bold 90% 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;font-size: 16px">Role Maintenance</h2>
<form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=maprole" onsubmit=" return validatePasswords(this)">
    <table width="70%" border="0" align="center" cellpadding="5" cellspacing="2">
        <tr>
            <th colspan="5" scope="col"><div class="header">&nbsp;Map role access</div></th>
        </tr>
        <tr>
            <td width="20%">&nbsp;</td>
            <td width="27%">&nbsp;</td>
            <td width="2%">&nbsp;</td>
            <td width="31%">&nbsp;</td>
            <td width="20%">&nbsp;</td>
        </tr>
        <tr>
            <td>Role </td>
            <td><label>
                    <select name="role" id="role" onkeyup="caps(this)" class="textboxes" required="true">
                        <% for (int i = 0; i < siz; i++) {%>
                        <option> <%=(String) role.get(i)%></option>
                        <% }
                        %>
                    </select>
                </label></td>
            <td>&nbsp;</td>
            <td>Menu Option</td>
            <td class="menuOptns"><label>
                    <% for (int i = 0; i < size; i++) {%>
                    <p><input type="checkbox" name="menuOpt" value="<%=(String) all.get(i)%>"/><%=(String) all.get(i)%></p>
                        <% }
                        %>
                </label></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><label>

                    <div align="right">
                        <input type="reset" name="Submit2" value="Reset"   class="redButton"/>
                    </div>
                </label></td>
            <td>&nbsp;</td>
            <td><label>
                    <input name="Submit" class="redButton" type="submit" onclick="MM_validateForm('menuOpt', '', 'R', 'role', '', 'R');
                            return document.MM_returnValue;
                            validatePasswords(this)" value="Go"/>
                </label></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td colspan="5"><div class="header">&nbsp;</div></td>
        </tr>
    </table>
</form>
