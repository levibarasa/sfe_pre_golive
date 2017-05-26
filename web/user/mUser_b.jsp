 <%@page import="com.orig.gls.utils.App"%>
<%@page import="com.orig.gls.dao.admin.user.User"%>
<%@ page import="java.sql.*,java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
    User frd = new User();
    String account = (String) session.getAttribute("userid");
    ArrayList all = frd.getUserDetails(account);
    int size = all.size();

%>
<%    String function = (String) session.getAttribute("ufunction");
    boolean isCancel = App.isCancel(function);
    boolean isInquire = App.isInquire(function);
    String ishiddenib = "";
    if (isCancel || isInquire) {
        ishiddenib = "hidden='true'";
    }
%>
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
            window.onload = function () {
                new JsDatePick({
                    useMode: 2,
                    target: "disableFrom",
                    dateFormat: "%d-%M-%Y"
                });
                new JsDatePick({
                    useMode: 2,
                    target: "disableTo",
                    dateFormat: "%d-%M-%Y"
                });
                new JsDatePick({
                    useMode: 2,
                    target: "passexdt",
                    dateFormat: "%d-%M-%Y"
                });
                new JsDatePick({
                    useMode: 2,
                    target: "actexdt",
                    dateFormat: "%d-%M-%Y"
                });
                new JsDatePick({
                    useMode: 2,
                    target: "lastaccess",
                    dateFormat: "%d-%M-%Y"
                });
            };
        </script>
        
        <script type="text/javascript">
    if (${umodified == 'true'}) {
        alert("User Modified Successfully");
    }
    if (${udeleted == 'true'}) {
        alert("User Deleted Successfully");
    }
</script>
        <script type="text/javascript">
            var popup;
            function getSolValueValue() {
                popup = window.open("pop/solpop.jsp", "Functions", "width=500,height=400");
                popup.focus();
                return false;
            }
            function getGroupCodeValue() {
                popup = window.open("pop/grouppop_all.jsp", "Functions", "width=500,height=400");
                popup.focus();
                return false;
            }

            function getSubGroupCodeValue() {
                popup = window.open("pop/subgrouppop_all.jsp", "Functions", "width=500,height=400");
                popup.focus();
                return false;
            }
        </script>
       
         <%           
             for (int i = 0; i < size; i++) {
                ArrayList one = (ArrayList) all.get(i);
                ////  userid,uname,roleid,
        %>
<form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=domoduser">
  
     <table width="80%" border="0" align="left">
         <tr>
                    <th colspan="12" align="left" scope="col"><div class="header">&nbsp;User Maintenance</div></th>
    </tr>
    <input type="hidden" name="function" id="function" value="${ufunction}" />
    
     <input type="hidden" name="userid" id="userid" value="<%= (String) one.get(11)%>" />
     
        <tr>
                    <td>Username</td>
                    <td>:</td>
                    <td><input type="text" name="username" id="username" value=" <%= (String) one.get(0)%>"  /></td>
                    <td></td>
                    <td>Role Id</td>
                    <td>:</td>
                    <td><input type="text" name="roleid" id="roleid" onkeyup="this.value = this.value.toUpperCase();"   value=" <%= (String) one.get(1)%>"/></td>
                </tr>
                <tr>
                    <td>New user?</td>
                    <td>:</td>
                    <td><input type="text" name="newuser" id="newuser" onkeyup="this.value = this.value.toUpperCase();" value=" <%= (String) one.get(2)%>"  /></td>
                    <td></td>
                    <td>User Status</td>
                    <td>:</td>
                    <td><input type="text" name="userStatus" id="userStatus" onkeyup="this.value = this.value.toUpperCase();" value=" <%= (String) one.get(3)%>"  /></td>
                </tr>        
                <tr>
                    <td>Disabled From</td>
                    <td>:</td>
                    <td><input type="text" name="disableFrom" onkeyup="this.value = this.value.toUpperCase();" id="disableFrom"    value="<%= (String) one.get(4)%>" /></td>
                    <td></td>
                    <td>Disabled To</td>
                    <td>:</td>
                    <td><input type="text" name="disableTo" onkeyup="this.value = this.value.toUpperCase();" id="disableTo"   value="<%= (String) one.get(5)%>" /></td>
                </tr>
                <tr>
                    <td>Password expiry date</td>
                    <td>:</td>
                    <td><input type="text" name="passexdt" onkeyup="this.value = this.value.toUpperCase();" id="passexdt"    value="<%= (String) one.get(6)%>" /></td>
                    <td></td>
                    <td>Account expiry date</td>
                    <td>:</td>
                    <td><input type="text" name="actexdt" onkeyup="this.value = this.value.toUpperCase();" id="actexdt"   value="<%= (String) one.get(7)%>" /></td>
                </tr>
                <tr>
                    <td>Last access date</td>
                    <td>:</td>
                    <td><input type="text" name="lastaccess" onkeyup="this.value = this.value.toUpperCase();" id="lastaccess"  value="<%= (String) one.get(10)%>" /></td>
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
                    <td><input type="reset" name="Submit2" value="Reset"   class="redButton" <%= ishiddenib%>/></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><label> 
                              
                            <input name="Submit" <%= ishiddenib%> class="redButton" type="submit"    value="Submit" />
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
    </table>
</form>
                                             <% }%>