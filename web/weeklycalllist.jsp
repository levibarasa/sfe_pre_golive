<%@page import="com.sfe.dao.customer.Customer"%>
<html>
    <head>
        <title>SFE Tool</title>
        <link href="include/admin.css" rel="stylesheet" type="text/css">
        <link href="include/menu.css" rel="stylesheet" type="text/css">
        <link href="include/main.css" rel="stylesheet" type="text/css">

        <link rel="stylesheet" type="text/css" href="include/menu.css">
        <%
            String rmCode = request.getParameter("rmCode");
            String rmCode1 = (String) session.getAttribute("rmCode");

            if (rmCode == null || rmCode == "") {
                rmCode = rmCode1;
            }
            System.out.println("RM Code: " + rmCode);
            ArrayList ar = Customer.getDailyList(rmCode);
            int size = ar.size();

        %>
    </style>
    <script type="text/javascript" src="include/jquery-1.4.2.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link href="include/admin.css" rel="stylesheet" type="text/css">
    <link href="include/main.css" rel="stylesheet" type="text/css">
    <script src="include/jquery-1.11.3.min.js"></script>
    <script src="include/jquery.lettering.js"></script>
    <script src="include/code.jquery.comjquery-1.12.4.js"></script>
    <script src="include/jquery.dataTables.min.js"></script>
    <script src="include/jquery.textillate.js"></script>
    <link href="include/animate.css" rel="stylesheet" type="text/css">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="STYLESHEET" type="text/css" href="include/calendar.css">
    <link href="include/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
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
        var popup;
        function completeListRmCode() {
            var rmCode = document.getElementById("rmCode").value;
            window.location.href = 'completelist.jsp?rmCode=' + rmCode;
        }

        function getCustUpdateValue(id) {
            var rmCode = document.getElementById("rmCode").value;
            var clientcontacted = document.getElementById("clientcontacted").value;
            var salescommitment = document.getElementById("salescommitment").value;
            var docsubmitted = document.getElementById("docsubmitted").value;
            var closed = document.getElementById("closed").value;
            var comments = document.getElementById("comments").value;
            var scheduledcalldate = document.getElementById("scheduledcalldate").value;
            popup = window.open("updatetrackerlist.jsp?custId=" + id + "&rmCode=" + rmCode + "&clientcontacted=" + clientcontacted + "&salescommitment=" + salescommitment + "&docsubmitted=" + docsubmitted + "&closed=" + closed + "&comments=" + comments + "&scheduledcalldate=" + scheduledcalldate, "Update Customer Information", "width=1000,height=600");
            popup.focus();
            return false
        }
        function addExistingCustomerAllValue() {
            var rmCode = document.getElementById("rmCode").value;
            popup = window.open("addexistingcustomerAll.jsp?rmCode=" + rmCode, "Add Existing Customer", "width=600,height=400");
            popup.focus();
            return false
        }
        function getCustInfoValue(id) {
            popup = window.open("customerInformation.jsp?custId=" + id, "Customer Information", "width=1000,height=800");
            popup.focus();
            return false
        }
        function getPrevWklyListCrtValue() {
            var rmCode = document.getElementById("rmCode").value;
            popup = window.open("callistperiod.jsp?rmCode=" + rmCode, "Previous Weekly List", "width=600,height=600");
            popup.focus();
            return false
        }

        function addNewCustomerValue() {
            var rmCode = document.getElementById("rmCode").value;
            popup = window.open("addnewcustomer.jsp?rmCode=" + rmCode, "Add New Customer", "width=600,height=600");
            popup.focus();
            return false
        }

        function addExistingCustomerValue() {
            var rmCode = document.getElementById("rmCode").value;
            popup = window.open("addexistingcustomer.jsp?rmCode=" + rmCode, "Add Existing Customer", "width=600,height=400");
            popup.focus();
            return false
        }
        function postRmCode() {
            var rmCode = document.getElementById("rmCode").value;
            window.location.href = 'do?MOD=BOK&ACT=dogenerate&rmCode=' + rmCode;
        }
        function dopostRptDriver() {
            var rmCode = document.getElementById("rmCode").value;
            var designation  = document.getElementById("designation").value;
            window.location.href = 'crptHome.jsp?rmCode=' + rmCode+'&designation='+designation;
        }
    </script>
    <script type="text/javascript">

        if (${custrack == 'true'}) {
            alert("Sorry.There was a problem updating weekly list");
        }
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#example').DataTable({
                "scrollY": 200,
                "scrollX": true
            });
        });
    </script>
    <style type="text/css">
        div.dataTables_wrapper {
            width: 800px;
            margin: 0 auto;
        }

        html,body{
            text-align: center;
            background-image: url(images/plain_white_background.jpg);
        }
        input[type=submit] {
            width: 15em;  height: 2em; border-radius: 12px;
        }
        input[type=button] {
            width: 15em;  height: 2em; border-radius: 12px;
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
        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            border: 1px solid #ddd;
        }

        th, td {
            border: none;
            text-align: left;
            padding: 8px;
        }

        .fix {
            position: absolute;
            *position: relative; /*ie7*/
            margin-left: -100px;
            width: 100px;
        }
        .outer {
            position: relative;
        }
        .inner {
            overflow-x: scroll;
            overflow-y: visible;
            width: 400px; 
            margin-left: 100px;
        }

        tr:nth-child(even){background-color: #f2f2f2}

        .zui-table {
            border: none;
            border-right: solid 1px #DDEFEF;
            border-collapse: separate;
            border-spacing: 0;
            font: normal 10px Arial, sans-serif;
            width: 100%
        }
        .zui-table thead th {
            background-color: #DDEFEF;
            border: none;
            color: #336B6B;
            padding: 10px;
            text-align: left;
            text-shadow: 1px 1px 1px #fff;
            white-space: nowrap;
        }
        .zui-table tbody td {
            border-bottom: solid 1px #DDEFEF;
            color: #333;
            padding: 10px;
            text-shadow: 1px 1px 1px #fff;
            white-space: nowrap;
        }
        .zui-wrapper {
            position: relative;
        }
        .zui-scroller {
            margin-left: 0 px;
            overflow-x: auto;
            overflow-y: scroll;
            padding-bottom: 5px;
            max-width: 1000px; 
            max-height: 350px;
        }
        .zui-table .zui-sticky-col {
            border-left: solid 1px #DDEFEF;
            border-right: solid 1px #DDEFEF;
            left: 0;
            position: absolute;
            top: auto;
            width: 80px;
        }
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
    <Strong style="font-size:12px; align-self center;"><u>
            Customers to be contacted 
        </u></Strong>
    <img src="images/alarm.png" name="alarm" width="50" height="30"   border="0"/>
    <input type="button" name="preweeklycallist" onclick="return getPrevWklyListCrtValue()"  value="Previous Call List" width="100%" id="preweeklycallist"   style="color:#ffffff;background-color:#24315e">
    <img src="images/user.png" name="alarm" width="30" height="30"   border="0"/>
    <input type="button" name="addnewcustomer" onclick="return addNewCustomerValue()" value="Add New Customer" width="100%" id="addnewcustomer"   style="color:#ffffff;background-color:#24315e">
    <input type="button" name="addexistingcustomer" onclick="return addExistingCustomerValue()" value="Add My Customer" width="100%" id="addnewcustomer"   style="color:#ffffff;background-color:#24315e">
    <input type="button" name="addexistingcustomer" onclick="return addExistingCustomerAllValue()" value="Add Any Bank Customer" width="100%" id="addnewcustomer"   style="color:#ffffff;background-color:#24315e">
    <table width="100%">  
        <tr width="100%">
            <td width="20%"><br/>
                <p> <input type="button" name="home" onClick="window.location = 'index.jsp'" value="Home" width="100%" id="home"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="weeklycalllist" onClick="window.location = 'weeklycalllist.jsp'" value="Daily Call List" width="100%" id="weeklycalllist"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="completelist" onClick=" return completeListRmCode();" value="Complete List" width="100%" id="completelist"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="reports" onClick="return dopostRptDriver(); window.location = 'crptHome.jsp'" value="Reports" width="100%" id="reports"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="updaterevenue" value="Update Revenue/Income" width="100%" id="updaterevenue"   style="opacity: 0.6; cursor: not-allowed;color:#ffffff;background-color:#24315e"></p>
            </td>
            <td> 


                <%  
              String designation = Customer.getRMDesignation(rmCode);
            
//window.location = 'completelist.jsp' ;
                    if (size > 0) {
                %>
                <%@page import="java.util.List"%>
                <%@page import="java.util.ArrayList"%> 
                <form id="form1" name="form1" method="post" >
                    <input type="hidden" id ="rmCode" value="<%=rmCode%>" name="rmCode" >
                    <input type="hidden" id ="designation" value="<%=designation%>" name="designation" >
                    <td width="60%">
                        <div class="zui-wrapper">
                            <div class="zui-scroller">
                                <table class="zui-table">
                                    <thead>
                                    <th>Update</th>
                                    <th >Customer ID </th>
                                    <th >Name</th>
                                    <th>Phone Number</th>
                                    <th>Customer Type</th>
                                    <th>Email ID</th>
                                    <th>Occupation</th>
                                    <th>Current Product Holdings</th>
                                    <th>Client Contacted</th>
                                    <th>Sales Commitment</th>
                                    <th>Docs Submitted</th>
                                    <th>Closed</th>
                                    <th>Comments</th>
                                    <th>Scheduled Call Date</th>

                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList list = new ArrayList();
                                            String Customer_ID, Name, Permanent_phonenumber,
                                                    Customer_Type, email_ID, Occupation, Client_Contacted,
                                                    Sales_Commitment, Docs_Submitted, Closed, Comments, Filled_Week;
                                            for (int i = 0; i < size; i++) {
                                                ArrayList one = (ArrayList) ar.get(i);

                                                Customer_ID = (String) one.get(0);

                                                Name = (String) one.get(1);
                                                Permanent_phonenumber = (String) one.get(2);
                                                Customer_Type = (String) one.get(3);
                                                email_ID = (String) one.get(4);
                                                Occupation = (String) one.get(5);
                                                Client_Contacted = (String) one.get(6);
                                                Sales_Commitment = (String) one.get(7);
                                                Docs_Submitted = (String) one.get(8);
                                                Closed = (String) one.get(9);
                                                Comments = (String) one.get(10);
                                                Filled_Week = (String) one.get(11);


                                        %>   

                                        <tr style="font-size: 9px">
                                            <td  style="font-size: 9px;" width="80px">
                                                <a href ="" onclick="return getCustUpdateValue('<%= Customer_ID%>')">
                                                    <img src="images/update.jpg" name="alarm" width="20" height="20"   border="0"/> 
                                                </a>
                                            </td> 
                                            <td style="font-size: 9px;width: 50px;overflow: hidden; text-overflow: ellipsis;" width="80px"> 
                                                <a  href="" onclick="return getCustInfoValue('<%= Customer_ID%>')" style="color: #666666; text-decoration: none;" >
                                                    <input readonly="readonly" style="border: none;background-color: transparent;" type="text" value="<%=Customer_ID%>" name ="custId" id="custId">
                                                </a>
                                            </td>
                                            <td   style="font-size: 9px">
                                                <a href="" onclick="return getCustInfoValue('<%= Customer_ID%>')" style="color: #666666; text-decoration: none;word-wrap: break-word;">  
                                                    <span style="display:block;width:200px;word-wrap:break-word;">
                                                        <input readonly="readonly" style="border: none;background-color: transparent;" type="text" value="<%=Name%>" name ="name" id="name"> 


                                                    </span> 

                                                </a>
                                            </td>
                                            <td   width="80px" style="font-size:9px;color: #666666;"><%=Permanent_phonenumber%></td>
                                            <td    width="80px" style="font-size:9px;color: #666666;"><%=Customer_Type%></td>
                                            <td   width="80px" style="font-size:9px;color: #666666;"><%=email_ID%></td>
                                            <td    width="80px" style="font-size:9px;color: #666666;"><%=Occupation%></td>
                                            <%

                                                int rating = Customer.getCurrentProducts(Customer_ID).size();
                                            %> 

                                            <td   width="80px"   <%
                                                if (rating < 1) {
                                                  %> 
                                                  style="background-color:red;font-size: 9px;"
                                                  <%
                                                  } else
                                                  %> 
                                                  <% if (rating == 2) {
                                                  %> 
                                                  style="font-size: 9px;background-color:yellow;"
                                                  <%
                                                  } else
                                                  %> 
                                                  <% if (rating == 3) {
                                                  %> 
                                                  style="font-size: 9px;background-color:yellow;"
                                                  <%
                                                  } else
                                                  %> 
                                                  <% if (rating == 4) {
                                                  %> 
                                                  style="font-size: 9px;background-color:yellow;"
                                                  <%
                                                  } else
                                                  %> 

                                                  <% if (rating > 5) {
                                                  %> 
                                                  style="font-size: 9px;background-color:green;"
                                                  <%
                                                      }
                                                  %> 
                                                  >
                                                <%=rating%>
                                            </td>


                                            <td style="font-size: 9px;width: 50px;overflow: hidden; text-overflow: ellipsis;" width="80px"> 
                                                <input readonly="readonly" style="border: none;background-color: transparent;" type="text" value="<%=Client_Contacted%>" name ="clientcontacted" id="clientcontacted">
                                            </td>

                                            <td style="font-size: 9px;width: 50px;overflow: hidden; text-overflow: ellipsis;" width="80px"> 
                                                <input readonly="readonly" style="border: none;background-color: transparent;" type="text" value="<%=Sales_Commitment%>" name ="salescommitment" id="salescommitment">
                                            </td>

                                            <td style="font-size: 9px;width: 50px;overflow: hidden; text-overflow: ellipsis;" width="80px"> 
                                                <input readonly="readonly" style="border: none;background-color: transparent;" type="text" value="<%=Docs_Submitted%>" name ="docsubmitted" id="docsubmitted">
                                            </td>

                                            <td style="font-size: 9px;width: 50px;overflow: hidden; text-overflow: ellipsis;" width="80px"> 
                                                <input readonly="readonly" style="border: none;background-color: transparent;" type="text" value="<%=Closed%>" name ="closed" id="closed">
                                            </td>

                                            <td  style="font-size: 9px;" width="80px">
                                                <input readonly="readonly"  type="text" id ="comments" value="<%=Comments%>" id="comments"  name="comments" size="30">
                                            </td>
                                            <td  style="font-size: 9px;" width="80px"> 
                                                <input readonly="readonly"  type="text" value="<%=Filled_Week%>" name="scheduledcalldate" id="scheduledcalldate">
                                            </td> 

                                        </tr>

                                        <%
                                            }

                                        %> 

                                    </tbody>
                                </table>
                            </div>
                        </div>



                </form>  

                <%                         } else {
                %>
                <form id="generatelist" name="generatelist"  method="post" action="do?MOD=BOK&ACT=dogenerate">
                    <center>   
                        <p> <input type="submit" onsubmit="return postRmCode()"  name="generatelist" value="Generate Today's List"  id="generatelist"   style=" width: 20em;  height: 3em; color:#ffffff;background-color:#24315e;font-size:16px;"></p>
                    </center>  
                </form>

                <%
                    }
                %>


            </td>
        </tr>
    </table>

</div>

<%@ include file="include/footer_one.jsp" %>

