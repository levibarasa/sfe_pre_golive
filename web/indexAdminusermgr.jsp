<%@page import="com.sfe.dao.customer.Customer"%>
<%@page import="java.util.ArrayList"%>

<html>
    <head>
        <title>SFE Tool</title>
        <link href="include/admin.css" rel="stylesheet" type="text/css">
        <link href="include/menu.css" rel="stylesheet" type="text/css">
        <link href="include/main.css" rel="stylesheet" type="text/css">

        <link rel="stylesheet" type="text/css" href="include/menu.css">

    </style>
    <script type="text/javascript" src="include/jquery-1.4.2.min.js"></script>
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
    <script type="text/javascript">

        if (${adminloggedin == 'true'}) {
            alert("You have successfully as Admin");
        }
    </script>
    <style type="text/css">
        <!--
        html,body{
            text-align: center;
            background-image: url(images/plain_white_background.jpg);
        }
        input[type=submit] {
            width: 20em;  height: 2em; border-radius: 12px;
        }
        input[type=button] {
            width: 20em;  height: 2em; border-radius: 12px;
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
            background-color: #24315e;
        }
        .style2 {color: #FFFFFF}
        .bg {background-color: #24315e}
        #container {
            width: 960px;
            margin: 0 auto;
        }

        #primary {
            float: left;
            width: 240px;
        }

        #content {
            float: left;
            width: 480px;
        }

        #secondary {
            float: left;
            width: 240px;
        }

        #footer {
            clear: both;
        }
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
    <script type="text/javascript">
        var popup;
        function getRmCodeValue() {
            popup = window.open("rpt/rmCodeList.jsp", "Functions", "width=500,height=400, resizable=false");
            popup.focus();
            return false;
        }
    </script>

        <script  type="text/javascript">
            function doapostDriver() {
                var rmCode = document.getElementById("rmCode").value;
                window.location.href = 'do?MOD=BOK&ACT=doadexistcustomer&rmCode=' + rmCode;
            }

        </script>  


</head>
<body>
    <%@ include file="include/header_one.jsp" %>
    <%
        // onSubmit="return doapostDriver();"
        %>
    

        <form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=doadexistcustomer" >
      <center><strong>Create/Update Rm Details</strong><br/><br/>
                
                 
  <table width="600">
                    <tr width="600">
                        <td    style="color:gray; font-style:italic;">RM Code :</td>
                     <td> </td>
                        <td  style="color: gray; font-style:italic;">
                            <input type="text"   id="rmCode" name="rmCode" value=""  >
                            <a href="" onClick="return getRmCodeValue()"><img src="images/search.png"></a>
                            RM Name:</td>
                             <td> 
                               <input type="text"   id="rmName" name="rmName" value=""  >
                             </td>
                    </tr>  
                    <tr width="600">
                        <td    style="color:gray; font-style:italic;">Access Level</td>
                         <td> </td>
                        <td>  
                             
                            <select name="accesslevel" id="accesslevel">
                               
                                <option value ="1" > 1</option>  
                                <option value ="1" > 1</option>  
                                <option value ="1" > 1</option>  
                                
                            </select> 
                        </td>
                         <td> </td>
                    </tr> 
                    <tr width="600">
                        <td    style="color:gray; font-style:italic;">Windows UserName</td>
                        <td> </td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text"   id="winUsername" name="winUsername" value=""  >
                           </td>
                         <td> </td>
                    </tr>
                    <tr width="600">
                        <td    style="color:gray; font-style:italic;">Designation</td>
                         <td> </td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text"   id="designation" name="designation" value=""  >
                        </td>
                         <td> 
                          
                      </td>
                    </tr>
                    <tr width="600">
                        <td    style="color:gray; font-style:italic;">Branch Code</td>
                         <td> </td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text"   id="branchcode" name="branchcode" value=""  >
                           <a href="" onClick="return getBranchValue()"><img src="images/search.png"></a> Branch Name
                        </td>
                         <td> 
                         <input type="text"   id="branchname" name="branchname" value=""  >
                      </td>
                    </tr>
                     <tr width="600">
                        <td    style="color:gray; font-style:italic;">Region </td>
                         <td> </td>
                        <td   style="color: gray; font-style:italic;"> 
                          <input type="text"   id="region" name="region" value=""  >
                           <a href="" onClick="return getRegionValue()"><img src="images/search.png"></a></td>
                         <td>&nbsp;</td>
    </tr>
                    <tr width="600">
                        <td    style="color:gray; font-style:italic;">Branch Manager Code</td>
                         <td> </td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text"   id="regioncode" name="regioncode" value=""  >
                           <a href="" onClick="return getBMValue()"><img src="images/search.png"></a> BM Name
                   </td>
                         <td> 
                         <input type="text"   id="regionname" name="regionname" value=""  >
                      </td>
                    </tr>

                    <%
                    }
                    %>
                </table>
                <br/><br/>
                <center><input type="button"  name="save" value="Save" width="100%" id="save"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 10em;  height: 2em; border-radius: 12px;">
                
                &nbsp;&nbsp;&nbsp;
                <input type="button"  name="update" value="Update" width="100%" id="update"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 10em;  height: 2em; border-radius: 12px;">
          </center>

        </form>
    </center> 


    <%@ include file="include/footer_one.jsp" %>

