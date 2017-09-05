<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
            <link href="include/admin.css" rel="stylesheet" type="text/css">
                <link href="include/menu.css" rel="stylesheet" type="text/css">
                    <link href="include/main.css" rel="stylesheet" type="text/css">
                        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
                            <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
                            <title>Simbank GLS</title>
                            <style>
                                html,body{
                                    height: 100%;
                                    text-align: center;
                                    font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
                                    font-size: small;
                                    color:#FFFFFF;
                                    background-image: url(images/plain_white_background.jpg);
                                }
                                .style1 {
                                    font-size: 16px;
                                    font-weight: bold;
                                    color: #FFFFFF;
                                    text-decoration:underline;
                                }
                                .style3 {
                                    font-size: 18px;
                                    font-weight: bold;
                                    color: #5757D9;
                                    text-decoration:blink;
                                }
                                .myBox {
                                    margin: 0.5in auto;
                                    color: #FFFFFF;
                                    width: 600px;
                                    padding: 20px;
                                    text-align: left;
                                    background-color: #019ADD;
                                    border: 3px solid #fff;

                                    /* Do rounding (native in Firefox and Safari) */
                                    -webkit-border-radius: 20px;
                                    -moz-border-radius: 20px;
                                }
                                .style4 {color: #FFFFFF}
                                .style5 {color: #5757D9}
                                .style6 {color: #FFFFFF; font-weight: bold; }
                            </style>
                            <script type="text/javascript">
                                if (${userlocked == 'true'}) {
                                    alert("You have exceeded the maximum Login Attempts\nUser is Locked");
                                }
                                if (${userdnexists == 'true'}) {
                                    alert("Log in failed. Check User Credentials");
                                }
                                if (${pwdexpired == 'true'}) {
                                    alert("User Password Has Expired");
                                }
                                if (${usernotverified == 'true'}) {
                                    alert("User Maintenance is incomplete");
                                }
                                if (${acctexpired == 'true'}) {
                                    alert("User account has expired");
                                }
                                if (${usrdisabled == 'true'}) {
                                    alert("User account is disabled");
                                }
                                if (${userlogged == 'true'}) {
                                    alert("User is already logged in. Log in again to kill session former session");
                                }
                                if (${pwdchanged == 'true'}) {
                                    alert("You have successfully updated your password. Kindly Login");
                                }
                            </script>
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
                                            if ((val = val.value) != "") {
                                                if (test.indexOf('isEmail') != -1) {
                                                    p = val.indexOf('@');
                                                    if (p < 1 || p == (val.length - 1))
                                                        errors += '- ' + nm + ' must contain an e-mail address.\n';
                                                } else if (test != 'R') {
                                                    num = parseFloat(val);
                                                    if (isNaN(val))
                                                        errors += '- ' + nm + ' must contain a number.\n';
                                                    if (test.indexOf('inRange') != -1) {
                                                        p = test.indexOf(':');
                                                        min = test.substring(8, p);
                                                        max = test.substring(p + 1);
                                                        if (num < min || max < num)
                                                            errors += '- ' + nm + ' must contain a number between ' + min + ' and ' + max + '.\n';
                                                    }
                                                }
                                            } else if (test.charAt(0) == 'R')
                                                errors += '- ' + nm + ' is required.\n';
                                        }
                                    }
                                    if (errors)
                                        alert('The following error(s) occurred:\n' + errors);
                                    document.MM_returnValue = (errors == '');
                                }

                            </script>
                            </head>

                            <body>
                                <div class="myBox">
                                    <table width="" border="0"  align="center" style=" background:#019ADD">
                                        <tr>
                                            <th width="45" scope="col">&nbsp;</th>
                                            <th width="498" scope="col">&nbsp;</th>
                                            <th width="43" scope="col">&nbsp;</th>
                                        </tr>
                                        <tr>
                                            <th colspan="3" bgcolor="#D5DD80" scope="col"><div align="center"><span class="style3"><span class="style4">Group Lending Solution</span></span> </div></th>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td><form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=doLog">
                                                    <table width="100%" border="0" align="center">
                                                        <tr>
                                                            <th colspan="3" scope="col">
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <td rowspan="8"><img src="images/logo.jpg" width="220" height="80" /></td>
                                                            <td colspan="2">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2"><div align="left"><span class="style1">User Login </span> </div></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td width="22%"><span class="style6">User Name :</span> 
                                                                <span class="style5">
                                                                    <label></label>
                                                                </span></td>
                                                            <td width="32%"><label>
                                                                    <input name="Username" onkeyup="this.value = this.value.toUpperCase();" type="text" id="Username" />
                                                                </label></td>
                                                        </tr>
                                                        <tr>
                                                            <td><span class="style6">&nbsp;Password :
                                                                    <label>              </label>
                                                                </span>
                                                                <span class="style5">
                                                                    <label></label>
                                                                </span></td>
                                                            <td><label>
                                                                    <input name="Password" type="password" id="Password" />
                                                                </label></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2">

                                                                <div align="center">
                                                                    <input name="Submit2" type="submit" class="redButton" onclick="MM_validateForm('Username', '', 'R', 'Password', '', 'R');
                                                                            return document.MM_returnValue" value=" Log In "/>
                                                                </div></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2"><label>
                                                                </label></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="3">&nbsp;</td>
                                                        </tr>
                                                    </table>
                                                </form>    </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </table>
                                    <hr class="footertext">&nbsp;</hr>
                                    <div class="footertext">
                                        <p align="center">Copyright 2002 - 2020 - <b>Powered by Simbank</b> - All Rights Reserved <br/></p>
                                    </div>
                            </body>
                            </html>
