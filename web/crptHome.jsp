<%@page import="com.sfe.dao.customer.Customer"%>
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
        window.onload = function () {
            new JsDatePick({
                useMode: 2,
                target: "todate",
                dateFormat: "%d/%m/%Y"
            });
            new JsDatePick({
                useMode: 2,
                target: "fromdate",
                dateFormat: "%d/%m/%Y"
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
        function getRegionValue() {
            popup = window.open("rpt/rmRegion.jsp", "Functions", "width=500,height=400, resizable=false");
            popup.focus();
            return false;
        }
        function getBranchValue() { 
         var region = document.getElementById("region").value;
            popup = window.open("rpt/rmBranch.jsp?region="+region, "Functions", "width=500,height=400, resizable=false");
            popup.focus();
            return false;
        }
        function getSegmentValue() {
            var rmName = document.getElementById("rmName").value;
            popup = window.open("rpt/rmSegment.jsp?rmCode="+rmName, "Functions", "width=500,height=400, resizable=false");
            popup.focus();
            return false;
        }
        function getRmCodeValue() {
            var branch = document.getElementById("branch").value;
            popup = window.open("rpt/rmCodeList.jsp?branch="+branch, "Functions", "width=500,height=400, resizable=false");
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
    <script type="text/javascript">
        var popup;

        function getReminders() {
            var rmCode = document.getElementById("rmCode").value;
            popup = window.open("reminder.jsp?rmCode=" + rmCode, "Reminder List", "width=1000,height=600");
            popup.focus();
            return false
        }
    </script>
    <script type="text/javascript">
        function dopostRptDriver() {
            // ,  ,  ,
            var rmCode = document.getElementById("rmCode").value;
            var region = document.getElementById("region").value;
            var branch = document.getElementById("branch").value;
            var segment = document.getElementById("segment").value;
            window.location.href = 'rptHome.jsp?rmCode=' + rmCode+'&region='region+'&branch='branch+'&segment='segment;
        }
        function dopostDriver() {
            var rmCode = document.getElementById("rmCode").value;
            window.location.href = 'weeklycalllist.jsp?rmCode=' + rmCode;
        }

        function completeListRmCode() {
            var rmCode = document.getElementById("rmCode").value;
            window.location.href = 'completelist.jsp?rmCode=' + rmCode;
        }
    </script>
    <style type="text/css">
        <!--
        html,body{
            text-align: center;
            background-image: url(images/plain_white_background.jpg);
        }
        input[type=submit] {
            width: 20em;  height: 2em;
        }
        input[type=button] {
            width: 20em;  height: 2em;
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
</head>
<body>
    <%@ include file="include/header_one.jsp" %> 
    <input type="hidden" name="rmCode" id="rmCode" value="<%= user_code %>">
    <%    
    String designation  = request.getParameter("designation");
    String rmCode  = request.getParameter("rmCode");  
    
    rmCode = user_code; 
        String region =Customer.getRMRegion(rmCode);
        String branch =Customer.getRMBranch(rmCode);
    %>
    <table width="100%">
        <tr width="100%">
            <td width="20%"><br/>
                <p> <input type="button" name="home" value="Home" onClick="window.location = 'index.jsp'" width="100%" id="home"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="weeklycalllist" onClick=" return dopostDriver();window.location = 'weeklycalllist.jsp'" value="Daily Call List" width="100%" id="weeklycalllist"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="completelist" onClick=" return completeListRmCode(); window.location = 'completelist.jsp'" value="Complete List" width="100%" id="completelist"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="reports" onClick="return dopostRptDriver(); window.location = 'rptHome.jsp'" value="Reports" width="100%" id="reports"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="updaterevenue" value="Update Revenue/Income" width="100%" id="updaterevenue"   style="opacity: 0.6; cursor: not-allowed;color:#ffffff;background-color:#24315e"></p>


            </td>
            <td width="80%">
               
            
<h2 style="font: bold 90% 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;font-size: 16px">Reports Module</h2>
<form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=gorpt" onsubmit=" return validatePasswords(this)">
            <center> 

                  
                <table width="600" align="right">
                    <tr width="600">
                     <td >&nbsp; </td>
                        <td    style="color: black; font-style:italic;">&nbsp; </td>
                         
                    </tr><tr width="600">
                        <td    style="color:black;font-size:12px;font-weight:bold;">Report:</td>
                        <td  style="color: black; ">
                        
                        <label>
                    <input name="rFunction" type="text" value ="" readonly="true"  id="rFunction" onkeyup="caps(this)" class="textboxes" />
                     <a href="" onclick="return getRPTFunctionValue()"><img src="images/search.png"></a>
                     </label>
                    </td>
                    </tr>  
                    
                    <%  
                       
                    if(designation.equalsIgnoreCase("CSO")  || designation.equalsIgnoreCase("RM") ||  designation.equalsIgnoreCase("RO")){
 
                      %>
                    
                      <tr width="600">
                        <td    style="color:black; font-size:12px;font-weight:bold;">Region:</td>
                        <td   style="color: black; "> 

                           <label>
                    <input name="region" type="text" value ="<%=region%>" readonly="true"  id="region" onkeyup="caps(this)" class="textboxes" />
                      </label>
                        </td>
                    </tr> 
                    <tr width="600">
                        <td    style="color:black; font-size:12px;font-weight:bold;">Branch:</td>
                        <td   style="color: black; ">  <label>
                    <input name="branch" type="text" value ="<%=branch%>" readonly="true"  id="branch" onkeyup="caps(this)" class="textboxes" />
                      </label>
                        </td>
                    </tr> 
                    <tr width="600">
                        <td    style="color:black;font-size:12px;font-weight:bold;">RM:</td>
                        <td   style="color: black;">  
                 <input type="hidden" name="rmCode" onkeyup="this.value = this.value.toUpperCase();" value="<%=rmCode%>" id="rmCode" readonly="true" />       
                        <label>
                    <input name="rmName" type="text" value ="<%=rmCode%>" readonly="true"  id="rmName" onkeyup="caps(this)" class="textboxes" />
                       </label>
                        </td>
                    </tr>
                     
                    <tr width="600">
                        <td    style="color:black;font-size:12px;font-weight:bold;">Segment:</td>
                        <td   style="color: black;"> 
                             <label>
                    <input name="segment" type="text" value ="ALL" readonly="true"  id="segment" onkeyup="caps(this)" class="textboxes" />
                      </label>
                        </td>
                    </tr> 
                    
                     <%  
                        }
                else if(designation.equalsIgnoreCase("BM")) {

                     
                %>
                
                <tr width="600">
                        <td    style="color:black; font-size:12px;font-weight:bold;">Region:</td>
                        <td   style="color: black; "> 

                           <label>
                    <input name="region" type="text" value ="<%=region%>" readonly="true"  id="region" onkeyup="caps(this)" class="textboxes" />
                      </label>
                        </td>
                    </tr> 
                    <tr width="600">
                        <td    style="color:black; font-size:12px;font-weight:bold;">Branch:</td>
                        <td   style="color: black; ">  <label>
                    <input name="branch" type="text" value ="ALL" readonly="true"  id="branch" onkeyup="caps(this)" class="textboxes" />
                     <a href="" onclick="return getBranchValue()"><img src="images/search.png"></a>
                     </label>
                        </td>
                    </tr> 
                    <tr width="600">
                        <td    style="color:black;font-size:12px;font-weight:bold;">RM:</td>
                        <td   style="color: black;">  
                 <input type="hidden" name="rmCode" onkeyup="this.value = this.value.toUpperCase();" value="" id="rmCode" readonly="true" />       
                        <label>
                    <input name="rmName" type="text" value ="ALL" readonly="true"  id="rmName" onkeyup="caps(this)" class="textboxes" />
                     <a href="" onclick="return getRmCodeValue()"><img src="images/search.png"></a>
                     </label>
                        </td>
                    </tr> 
                     
                    <tr width="600">
                        <td    style="color:black;font-size:12px;font-weight:bold;">Segment:</td>
                        <td   style="color: black;"> 
                             <label>
                    <input name="segment" type="text" value ="ALL" readonly="true"  id="segment" onkeyup="caps(this)" class="textboxes" />
                     <a href="" onclick="return getSegmentValue()"><img src="images/search.png"></a>
                     </label>
                        </td>
                    </tr> 
                 
                            <%
                                }
                        else{
                        %>
                <tr width="600">
                        <td    style="color:black; font-size:12px;font-weight:bold;">Region:</td>
                        <td   style="color: black; "> 

                           <label>
                    <input name="region" type="text" value ="ALL" readonly="true"  id="region" onkeyup="caps(this)" class="textboxes" />
                     <a href="" onclick="return getRegionValue()"><img src="images/search.png"></a>
                     </label>
                        </td>
                    </tr> 
                    <tr width="600">
                        <td    style="color:black; font-size:12px;font-weight:bold;">Branch:</td>
                        <td   style="color: black; ">  <label>
                    <input name="branch" type="text" value ="ALL" readonly="true"  id="branch" onkeyup="caps(this)" class="textboxes" />
                     <a href="" onclick="return getBranchValue()"><img src="images/search.png"></a>
                     </label>
                        </td>
                    </tr> 
                    <tr width="600">
                        <td    style="color:black;font-size:12px;font-weight:bold;">RM:</td>
                        <td   style="color: black;">  
                 <input type="hidden" name="rmCode" onkeyup="this.value = this.value.toUpperCase();" value="" id="rmCode" readonly="true" />       
                        <label>
                    <input name="rmName" type="text" value ="ALL" readonly="true"  id="rmName" onkeyup="caps(this)" class="textboxes" />
                     <a href="" onclick="return getRmCodeValue()"><img src="images/search.png"></a>
                     </label>
                        </td>
                    </tr> 
                     
                    <tr width="600">
                        <td    style="color:black;font-size:12px;font-weight:bold;">Segment:</td>
                        <td   style="color: black;"> 
                             <label>
                    <input name="segment" type="text" value ="ALL" readonly="true"  id="segment" onkeyup="caps(this)" class="textboxes" />
                     <a href="" onclick="return getSegmentValue()"><img src="images/search.png"></a>
                     </label>
                        </td>
                    </tr> 
                


             <%
            }
                    
                    %>
                    <tr width="600">
                        <td    style="color:black; font-size:12px;font-weight:bold;">Report Format:</td>
                        <td  style="color: black;">
                        
                        <label>
                    <input name="rtpfmt" type="text" value ="" readonly="true"  id="rtpfmt" onkeyup="caps(this)" class="textboxes" />
                     <a href="" onclick="return getFunctionValue()"><img src="images/search.png"></a>
                     </label>
                    </td>
                    </tr>  
                    <tr width="600">
                     <td style="color:black; font-size:12px;font-weight:bold;" >From Date:</td> <td >
                        <input type="text" name="fromdate" readonly="true" onkeyup="this.value = this.value.toUpperCase();"  id="fromdate" required="true"/>
                       
                        
                        </td>
                         
                    </tr>
                     <tr width="600">
                     <td style="color:black;font-size:12px;font-weight:bold;"> To Date: </td>
                        <td    style="color: black;"><input type="text" name="todate" readonly="true" onkeyup="this.value = this.value.toUpperCase();"  id="todate" required="true"/> </td>
                         
                    </tr>
                    
                    
                     <tr width="600">
                     <td >
                     <label>

                    <div align="right">
                        <input type="reset" name="Submit2" value="Reset"   class="redButton"/>
                    </div>
                </label> 
                      </td>
                        <td    style="color: black; ">
                         <div align="center">
                        <label>  
                    <input size="10" name="Submit" class="redButton" type="submit" onclick="MM_validateForm('rFunction', '', 'R', 'groupcode', '', 'R', 'fromdate', '', 'R', 'todate', '', 'R');
                            return document.MM_returnValue;
                            validatePasswords(this)" value="Go" />
                </label>
                </div>
                         </td>
                         
                    </tr>
                     <tr width="600">
                     <td >&nbsp; </td>
                        <td    style="color: black; "></td>
                       
                    </tr>
                </table>
                <br/><br/>
                <center>
                
                
                
                
                </center>

        </form>
    </center>
    <p align="left">
        <img src="images/rpt.png" width="300" height="300">
    </p> 


            </td> 
        </tr>
    </table>



    <%@ include file="include/footer_one.jsp" %>

