<html>
    <head>
        <title>Simbank GLS</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link href="include/admin.css" rel="stylesheet" type="text/css">
        <link href="include/main.css" rel="stylesheet" type="text/css">
        <script src="include/jquery-1.11.3.min.js"></script>
        <script src="include/jquery.lettering.js"></script>
        <script src="include/jquery.textillate.js"></script>
        <link href="include/animate.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="STYLESHEET" type="text/css" href="include/calendar.css">
        <script language="JavaScript" type="text/javascript" src="include/simplecalendar.js"></script>
        <link href="css/cssLayout.css" rel="stylesheet" type="text/css">
        <link href="include/menu.css" rel="stylesheet" type="text/css">
        <link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
        <meta http-equiv="cache-control" content="max-age=0" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />
        <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
        <meta http-equiv="pragma" content="no-cache" />
        <script language="javaScript" type="text/javascript" src="include/sim.js"></script>
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
        <style type="text/css">
            <!--
            html,body{
                text-align: center;
                background-image: url(images/plain_white_background.jpg);
            }
            .style1 {
                font-size: 16px;
                font-weight: bold;
                color: #FFFFFF;
            }
            .white{
                color:#000099;
                width:100%;
                height:5%;
                position:fixed;
                float:left;
                bottom:0px;
                background-color: #019ADD;
            }
            .style2 {color: #FFFFFF}
            .bg {background-color: #019ADD}

            -->
        </style>
        <script>
            $(function () {
                $('.style2').textillate({in: {
                        effect: 'fadeInLeftBig'
                    },
                    out: {
                        effect: 'fadeOutLeftBig'
                    },
                    loop: true
                });
            });
        </script>
    </head>
    <body>
        <div class=""> 
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="graybox">
                <tr>
                    <td colspan="3">
                        <%@ include file="include/header.jsp" %>
                    </td>
                </tr>
                <form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=menu">
                    <tr>
                        <td align="right" colspan="3"  bgcolor="#019ADD" style="height:30px;"><span class="style1">
                                <span class="style2">Menu Option </span>
                                <span class="style2">:</span>
                                <span class="style2"><input type="text" name="menu option" onkeyup="this.value = this.value.toUpperCase();" id="menu" value="" /></span>
                                <span class="style2"><input type="submit" name="btnmenu" onclick="MM_validateForm('menu', '', 'R');
                                        return document.MM_returnValue" value="GO" /></span>
                            </span></td>
                    </tr>
                </form>
                <tr>
                    <td width="15%" valign="top" bgcolor="#019ADD" class="navArea"><p>&nbsp;</p>
                        <%@ include  file="include/umenu.jsp" %>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                    </td>
                    <td width="70%" valign="top" class="contentArea" >
                        <table width="100%" border="0" cellspacing="0" cellpadding="10">
                            <tr>
                                <td>
                                    <%
                                        String conPage = (String) session.getAttribute("content_page");
                                        System.out.println("conPage ==> " + conPage);
                                        if (conPage != null) {
                                    %>
                                    <jsp:include page="<%=conPage%>"/>
                                    <%
                                    } else {
                                    %>
                                    <jsp:include page="ucontent.jsp"/>
                                    <%
                                        }
                                    %></td>
                            </tr>
                        </table>
                    </td>
                    <td width="15%" valign="top" bgcolor="#019ADD" class="navArea"><p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                    </td>
                </tr>
            </table>
        </div>
        <p>&nbsp;</p>
        <div class="white">
            <p align="center">Copyright 2002 - 2020 - <b>www.simbatech.biz</b> - All Rights Reserved <br>
        </div>
    </body>
</html>
