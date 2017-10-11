<head>
    <script src="include/jquery-1.11.3.min.js"></script>
    <script src="include/jquery.lettering.js"></script>
    <script src="include/jquery.textillate.js"></script>
    <link href="include/animate.css" rel="stylesheet" type="text/css">
    <%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
             java.util.*" errorPage="" %>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <link href="include/admin.css" rel="stylesheet" type="text/css">
    <link href="include/menu.css" rel="stylesheet" type="text/css">
    <link href="include/main.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <meta http-equiv="cache-control" content="max-age=0" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="pragma" content="no-cache" />
    <script language="javaScript" type="text/javascript" src="include/sim.js"></script>
    <link rel="stylesheet" type="text/css" href="include/menu.css">
    <%
       // String uname = (String) session.getAttribute("uname");
       // String classname = (String) session.getAttribute("uwcname");
    %>
    <style type="text/css">
        body
        {
            background-color: #FFFFFF;
            color: #000000;
        }
        input.logout {
            width: 10em;  height: 2em;
        }
    </style>
    <script>
        function doLogout() {
            if (confirm("Are you Sure you want to Logout?")) {
                logout.submit();
            }

        }
    </script>

    <script type="text/javascript" src="include/jquery-1.4.2.min.js"></script>
</head>
<script>
        $(function () {
            $('.demo2').textillate({in: {
                    effect: 'rotateOutDownRight'
                },
                out: {
                    effect: 'bounce'
                },
                loop: true
            });
        });
</script>

<table width="100%" border="0" cellspacing="0" cellpadding="4" bgcolor="#24315e">
    <tr>
        <td width="200" rowspan="3" bgcolor="#24315e"><a href="#"><img src="images/inmlogo.png" name="Logo" width="200" height="80"   border="0"/></a></td>
        <td width="100%" height="61" bgcolor="#24315e" style="padding-left:20px;font-size:12px; font-style: italic; color: #ffffff; text-align: right">

            <%
                String user_name = (String) session.getAttribute("name");
                String user_code = (String) session.getAttribute("code");
                String user_branch = (String) session.getAttribute("branch");
                String user_region = (String) session.getAttribute("region");
                String user_access_leve = (String) session.getAttribute("accessLevel");
            %>

            Welcome, <%=user_name%>  <br />  
            Relationship Officer Code : <%=user_code%>  <br /> 
            Branch : <%=user_branch%>  <br /> 
            Region : <%=user_region%> 

        </td>
        <td width="200" rowspan="3" bgcolor="#24315e">
            <form name="logout"  method="post" action="do?MOD=BOK&ACT=Logout">
                <input type="submit" value="    Logout    " onclick="doLogout()" width="100" class="logout" bgcolor="#24315e" style="background-color: #C8C8C8; color: #eee;" size="30">
            </form> 
        </td>
    </tr>
    <tr>
        <td align="center" height="27" bgcolor="#24315e" class="demo2" style="padding-left:20px;color:#000000"><b>

            </b></td>
    </tr>
    <tr>
        <td bgcolor="#24315e" style="padding-left:20px;">&nbsp;  </td>
    </tr>
</table>

