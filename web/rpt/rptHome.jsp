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
        window.onload = function () {
            new JsDatePick({
                useMode: 2,
                target: "todate",
                dateFormat: "%d-%M-%Y"
            });
            new JsDatePick({
                useMode: 2,
                target: "fromdate",
                dateFormat: "%d-%M-%Y"
            });
        };
    </script>
    <script type="text/javascript">
        if (${dtErr == 'true'}) {
            alert("One of your selected dates is greater than current date.");
        }
    </script>
    <script type="text/javascript">
        var popup;
        function getSubGroupCodeValue() {
            popup = window.open("loan/subGroups.jsp", "Functions", "width=500,height=400, resizable=false");
            popup.focus();
            return false;
        }
    </script>
    <script type="text/javascript">
        var popup;
        function getRPTFunctionValue() {
            popup = window.open("rpt/function.jsp", "Functions", "width=500,height=400, resizable=no,scrollbars=yes");
            popup.focus();
            return false;
        }
    </script>
    <script type="text/javascript">
        var popup;
        function getFunctionValue() {
            popup = window.open("rpt/rptformat.jsp", "Functions", "width=500,height=400, resizable=no,scrollbars=yes");
            popup.focus();
            return false;
        }
    </script>
</head>
<h2 style="font: bold 90% 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;font-size: 16px">Reports Module</h2>
<form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=gorpt" onsubmit=" return validatePasswords(this)">
    <table width="90%" border="0" align="left" cellpadding="5" cellspacing="2">
        <tr>
            <th colspan="8" scope="col"><div class="header">&nbsp;Reports Module</div></th>
        </tr>
        <%   
                String rtype = (String) session.getAttribute("rFunction") ;
                 
                String rtpfmt = (String) session.getAttribute("rtpfmt") ;
                System.out.println("Report type "+rtype);
                System.out.println("Report format "+rtpfmt);
        %>
        <tr>
            <td><label>Function</label></td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="rFunction" type="text"  value ="<%=rtype%>" readonly="true"  id="rFunction" onkeyup="caps(this)" class="textboxes" />
                    <td><!--a href="" onclick="return getRPTFunctionValue()"><img src="images/search.png"></a-->
                    </td>
                </label>
            </td>
            <td><label>Report Format</label></td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="rtpfmt" type="text"  value ="<%=rtpfmt%>" readonly="true"  id="rtpfmt" onkeyup="caps(this)" class="textboxes" />
                    <td><!--a href="" onclick="return getFunctionValue()"><img src="images/search.png"></a-->
                    </td>
                </label>
            </td>

        </tr>
        <tr>
            <td>Sub group code</td>
            <td>:</td>
            <td><input type="text" name="subgroup" onkeyup="this.value = this.value.toUpperCase();" value="" id="subgroup" readonly="true" /></td>
            <td><a href="" onclick="return getSubGroupCodeValue()"/><img src="images/search.png"/></a></td>
            <td>Sub group Name</td>
            <td>:</td>
            <td colspan="2"><input type="text" name="subgroupName" onkeyup="this.value = this.value.toUpperCase();" value="" id="subgroupName" readonly="true" /></td>

        </tr>
        <tr>
            <td>From date</td>
            <td>:</td>
            <td><input type="text" name="fromdate" readonly="true" onkeyup="this.value = this.value.toUpperCase();"  id="fromdate" required="true"/></td>
            <td></td>
            <td>To date</td>
            <td>:</td>
            <td><input type="text" name="todate" readonly="true" onkeyup="this.value = this.value.toUpperCase();"  id="todate" required="true"/></td>
            <td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><label>

                    <div align="right">
                        <input type="reset" name="Submit2" value="Reset"   class="redButton"/>
                    </div>
                </label></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td><label>
                    <input name="Submit" class="redButton" type="submit" onclick="MM_validateForm('rFunction', '', 'R', 'subgroup', '', 'R', 'fromdate', '', 'R', 'todate', '', 'R');
                            return document.MM_returnValue;
                            validatePasswords(this)" value="Go" />
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
            <td colspan="8"><div class="header">&nbsp;</div></td>
        </tr>
    </table>
</form>
