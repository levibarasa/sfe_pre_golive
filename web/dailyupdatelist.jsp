

<%@page import="java.util.ArrayList"%>
<%@page import="com.sfe.dao.customer.Customer"%>
<html>
    <header>
    <link rel="stylesheet" type="text/css" media="all" href="jsDatePick_ltr.min.css" />
        <link href="include/admin.css" rel="stylesheet" type="text/css">
        <link href="include/menu.css" rel="stylesheet" type="text/css">
        <link href="include/main.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="include/menu.css">

        </style>
        <script type="text/javascript" src="include/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="include/jquery.1.4.2.js"></script>
        <script type="text/javascript" src="include/jsDatePick.jquery.min.1.3.js"></script>
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
            window.onload = function () {
                new JsDatePick({
                    useMode: 2,
                    target: "inputField",
                    dateFormat: "%d/%m/%Y",
                    selectedDate: {//This is an example of what the full configuration offers.
                        day: 5, //For full documentation about these settings please see the full version of the code.
                        month: 9,
                        year: 2006
                    },
                    yearsRange: [1978, 2020],
                    limitToToday: false,
                    cellColorScheme: "beige",
                    dateFormat: "%d/%m/%Y",
                    imgPath: "img/",
                    weekStartDay: 1
                });
            };
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
    
    
        <script language="javaScript" type="text/javascript" src="include/sim.js"></script>
        <script type="text/javascript" src="include/jquery.1.4.2.js"></script>
        <script type="text/javascript" src="include/jsDatePick.jquery.min.1.3.js"></script>

        <link rel="stylesheet" type="text/css" media="all" href="include/jsDatePick_ltr.min.css" />
        <script type="text/javascript">
            var popup;
            function getMarketedProductValue(id) { 
                 var rmCode = document.getElementById("rmCode").value;
                 var custName = document.getElementById("custName").value; 
                popup = window.open("prodsList.jsp?custId=" + id+ "&rmCode=" + rmCode+ "&custName=" + custName, "Functions", "width=500,height=400, resizable=false");
                popup.focus();
                return false;
            }
        </script>
        <script type="text/javascript">
            window.onload = function () {
                new JsDatePick({
                    useMode: 2,
                    target: "scheduledcalldate",
                    dateFormat: "%d/%m/%Y",
                    selectedDate: {
                        day: 5,
                        month: 9,
                        year: 2017
                    },
                    yearsRange: [1978, 2020],
                    limitToToday: false,
                    cellColorScheme: "beige",
                    dateFormat: "%d/%m/%Y",
                    imgPath: "img/",
                    weekStartDay: 1
                });

            };
        </script>
        <script  type="text/javascript">
            var popup;

            function getCheckedProducts(chkboxName) {
                var products = document.getElementById(chkboxName);
                var productsChecked = [];
                for (var i = 0; i < products.options.length; i++) {
                    if (products.options[i].selected === true) {
                        productsChecked.push(products.options[i].value);
                    }
                }

                return productsChecked.length > 0 ? productsChecked : null;
            }

            function getAddProductsValue(id) {
                var rmCode = document.getElementById("rmCode").value;
                  popup = window.open("addproduct.jsp?custId=" + id + "&rmCode=" + rmCode, "Select Products ", "width=1000,height=800");
                popup.focus();
                return false
            }
            
     
        </script>   
        <script  type="text/javascript">
 
             function updateTodayProductValue(id) {
                var rmCode = document.getElementById("rmCode").value;
                var scheduledcalldate = document.getElementById("scheduledcalldate").value;
                popup = window.open("updatetrackerprevious.jsp?custId=" + id + "&rmCode=" + rmCode + "&scheduledcalldate=" + scheduledcalldate, "Update Customer Information", "width=1000,height=600");
                popup.focus();
                return false
             }
             
            function getCustSellValue(id) {
                var marketedProduct = document.getElementById("marketedProduct").value;
                var custId = document.getElementById("custId").value;
                var product = document.getElementById("product").value;
                var rmCode = document.getElementById("rmCode").value;
                var clientcontactedj = document.getElementById("clientcontacted").value;
                var salescommitmentj = document.getElementById("salescommitment").value;
                var docsubmittedj = document.getElementById("docsubmitted").value;
                var closedj = document.getElementById("closed").value;
                var commentsj = document.getElementById("comments").value;
                var currency = document.getElementById("currency").value;
                var productvalue = document.getElementById("productvalue").value;
                var scheduledcalldatej = document.getElementById("scheduledcalldate").value;
                var leadsrc = document.getElementById("leadsrc").value; 
            popup = window.open("posttracker.jsp?trackid=" + id+"&marketedProduct=" + marketedProduct  +"&product=" + product + "&custId=" + custId + "&rmCode=" + rmCode + "&clientcontacted=" + clientcontactedj + "&salescommitment=" + salescommitmentj + "&docsubmitted=" + docsubmittedj + "&closed=" + closedj + "&comments=" + commentsj + "&scheduledcalldate=" + scheduledcalldatej + "&currency=" + currency + "&productvalue=" + productvalue+"&leadsrc="+leadsrc, "Update Customer Information", "width=1000,height=600");
            popup.focus();
            return false
            }
          
            function doapostDriver(trackid) { 
                var trackid = document.getElementById("trackid").value;
                var custId = document.getElementById("custId").value;
                var product = document.getElementById("product").value;
                var productsold = document.getElementById("productsold").value;
                var rmCode = document.getElementById("rmCode").value;
                var clientcontactedj = document.getElementById("clientcontacted").value;
                var salescommitmentj = document.getElementById("salescommitment").value;
                var docsubmittedj = document.getElementById("docsubmitted").value;
                var closedj = document.getElementById("closed").value;
                var commentsj = document.getElementById("comments").value;
                var currency = document.getElementById("currency").value;
                var productvalue = document.getElementById("productvalue").value;
                var scheduledcalldatej = document.getElementById("scheduledcalldate").value;
                var leadsrc = document.getElementById("leadsrc").value; 
                window.location.href = 'do?MOD=BOK&ACT=doacustomer&trackid=' + trackid +'&productsold=' + productsold +'&product=' + product + '&custId=' + custId + '&rmCode=' + rmCode + '&clientcontacted=' + clientcontactedj + '&salescommitment=' + salescommitmentj + '&docsubmitted=' + docsubmittedj + '&closed=' + closedj + '&comments=' + commentsj + '&scheduledcalldate=' + scheduledcalldatej + '&currency=' + currency + '&productvalue=' + productvalue+'&leadsrc='+leadsrc;
            }
 

        function doaddprodpostDriver(id) {
              var rmCode = document.getElementById("rmCode").value; 
             window.location.href = 'do?MOD=BOK&ACT=doaddproduct&custId=' + id + '&rmCode=' + rmCode ;
            }

        </script>  
    </header>

    <body>
        <%

            String name = request.getParameter("name");
            String rmCode = request.getParameter("rmCode");
            String custId = request.getParameter("custId");
            String custId1 = (String) session.getAttribute("custId");
            String rmCode1 = (String) session.getAttribute("rmCode"); 
            String todayproduct = request.getParameter("todayproduct");
            String scheduledcalldate = request.getParameter("scheduledcalldate");
            String custName = Customer.getCustName(custId);
            String newCustName = Customer.getNewCustName(custId);
            if (custName == "" || custName == null) {
                custName = newCustName;
            }
            if (custId == "" || custId == null) {
                custId = custId1;
            }
            if (rmCode == "" || rmCode == null) {
                rmCode = rmCode1;
            }
        %>
        <form id="form1" name="form1" method="post"  action="do?MOD=BOK&ACT=doacustomer">
            <center>
                <strong>
                    Update Customer Tracker Details
                </strong>

                <br/><br/>

                
                
                <input type="hidden" name="rmCode" id="rmCode" value="<%= rmCode%>">
                <table><tr>
                        <th>Customer ID  </th>
                        <td>  <input type="text"   id="custId" name="custId" value="<%=custId%>"  > </td>
                        
                    </tr> 
                <tr>
                        <th> Customer Name</th>
                       <td>  <input type="text" id="custName" value="<%=custName%>"   name="custName"  > </td>
                    </tr>
                  </table>  
                  
                    
                    
                  <div class="zui-wrapper">
                    <div class="zui-scroller">
                        <table class="zui-table"> 
                        <tr>
                    <th width="20">  
                       Product  
                        </th>
                        <th>   
                        Lead(s) Source
                        </th>
                        <th> 
                            Currency  
                        </th>
                         
                        <th>  
                        Average<br/> Product Value 
                        </th><th>  
                        Client <br/>Contacted
                        </th><th>
                        Sales <br/>Commitment
                        </th><th>Docs <br/>Submited  
                        
                        </th><th> 
                        Closed
                        </th>
                        <th>  
                        Product Sold
                        </th>
                        <th>  
                        Comments
                        </th><th> 
                        Scheduled Date
                        </th> 
                       
                        
                        <th>   
                       Save/Update
                        </th>
                    </tr> 
			 <%
                        ArrayList trackerList = Customer.getProductUpdater(custId, scheduledcalldate,todayproduct); 
                        int size = trackerList.size();
                        String Currency, Product_value, Marketed_Value, Client_Contacted, Sales_Commitment, 
                                Docs_Submitted, Closed, Comments, Filled_Week,Lead_Source,Product_Sold,trackId;
                        for (int i = 0; i < size; i++) { 
                            ArrayList one = (ArrayList) trackerList.get(i);
                            Currency = (String) one.get(0);
                            Product_value = (String) one.get(1);
                            Marketed_Value = (String) one.get(2);
                            Client_Contacted = (String) one.get(3);
                            Sales_Commitment = (String) one.get(4);
                            Docs_Submitted = (String) one.get(5);
                            Closed = (String) one.get(6);
                            Comments = (String) one.get(7);
                            Filled_Week = (String) one.get(8);
                            Lead_Source = (String) one.get(9);
                            Product_Sold = (String) one.get(10);
                            trackId = (String) one.get(11);
                            if(!Marketed_Value.equalsIgnoreCase("D")){
                    %>		
                    <tr>
                        <td width="20"> 
                        <input  readonly="readonly"  name="product" type="text" id="product" value="<%= Marketed_Value%>" size="20"  >
                        
                         </td>
                         <td> 
                        <input  name="leadsrc" type="text"  id="leadsrc" value="<%=Lead_Source%>" size="10">
                         </td>  
                        <td>
                        
                        <select name="currency" id="currency" width="5">
                                <option selected="selected"  value="Ksh">Ksh</option>
                                <option value="USD">USD</option> 
                            </select>
                        </td>
                        <td> 
                        <input   name="productvalue" type="text" id="productvalue" value="<%= Product_value%>" size="10"  >
                        
                         </td>
                         <td>
                          <select id="clientcontacted" name="clientcontacted" width="10">
                                <%
                                    if (Client_Contacted.equalsIgnoreCase("Yes")) {
                                %>
                                <option selected="selected" value="<%= Client_Contacted%>"><%= Client_Contacted%></option>
                                <%
                                } else {
                                %>
                                <option value="No">No</option>
                                <option value="Yes">Yes</option>
                                <%
                                    }
                                %>
                            </select>   
                        </td><td>  
                         <select width="10" id="salescommitment" name="salescommitment">
                                <%
                                    if (Sales_Commitment.equalsIgnoreCase("Yes")) {
                                %>
                                <option selected="selected" value="<%= Sales_Commitment%>"><%= Sales_Commitment%></option>
                                <%
                                } else {
                                %>
                                <option  value="No">No</option>
                                <option value="Yes">Yes</option>
                                <%
                                    }
                                %>

                            </select>
                        </td><td>  
                         <select width="20px" id="docsubmitted" name="docsubmitted"> 
                                <%
                                    if (Docs_Submitted.equalsIgnoreCase("Yes")) {
                                %>
                                <option selected="selected"  value="<%= Docs_Submitted%>"><%= Docs_Submitted%></option>
                                <%
                                } else {
                                %>
                                <option value="No">No</option>
                                <option value="Yes">Yes</option>
                                <%
                                    }
                                %>
                            </select>
                        </td><td style="width:50px">
                        <select width="20px" id="closed" name="closed">
                                <%
                                    if (Closed.equalsIgnoreCase("Yes")) {
                                %>
                                <option  selected="selected"  value="<%= Closed%>"><%= Closed%></option>
                                <%
                                } else {
                                %>
                                <option value="No">No</option>
                                <option value="Yes">Yes</option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                        <td> <select id="productsold" name="productsold" width="10">
                                <%
                                    if (Product_Sold.equalsIgnoreCase("Yes")) {
                                %>
                                <option selected="selected" value="<%= Product_Sold%>"><%= Product_Sold%></option>
                                <%
                                } else {
                                %>
                                <option value="No">No</option>
                                <option value="Yes">Yes</option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                        <td> 
                            <input type="hidden" name="trackid" id="trackid" value="<%= trackId%>">
                         <input type="text" id="comments"  value="<%= Comments%>" name="comments" size="30" >
                        </td><td style="width:50px">  
                            
                            <input  readonly="readonly" name="scheduledcalldate" type="text" id="scheduledcalldate" value="<%= Filled_Week%>" size="10">
                        </td>
                        <td> 
                            
                       <center><input type="submit" onClick="javascript:doapostDriver();"  name="update" value="Save/Update" width="100%" id="update"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 10em;  height: 2em; border-radius: 12px;"></center>
                        <%
                            System.out.println("Product Id to post : "+trackId);
                            %>
            
            </td>
                       
                    </tr>
                    <%    
                        }
                                }
                            %>  
                </table>
             </div>
        </div>   

    </form>  
          </center>
</body>
</html>