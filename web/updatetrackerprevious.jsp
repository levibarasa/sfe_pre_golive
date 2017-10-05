<%@page import="java.util.ArrayList"%>
<%@page import="com.sfe.dao.customer.Customer"%>
<html>
    <header>
   
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
            
             function getMarketProductValue() { 
                  var custId = document.getElementById("custId").value;
                 var rmCode = document.getElementById("rmCode").value;
                var todayproduct  = document.getElementById("todayproduct").value; 
                var scheduledcalldate = document.getElementById("scheduledcalldate").value; 
                popup = window.open("updprodsList.jsp?custId="+ custId + "&todayproduct=" + todayproduct+ "&rmCode=" + rmCode + "&scheduledcalldate=" + scheduledcalldate, "Functions", "width=500,height=400, resizable=false");
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
     
     function getProductValue() {
                var scheduledcalldate = document.getElementById("scheduledcalldate").value;
                 var custId = document.getElementById("custId").value;
                popup = window.open("customerproductlist.jsp?custId="+ custId +"&scheduledcalldate="+scheduledcalldate, "Functions", "width=500,height=400");
                popup.focus();
                return false;
            }
            
         function getAddProductsValue(id) {
            var rmCode = document.getElementById("rmCode").value; 
            popup = window.open("addproductprev.jsp?custId=" + id + "&rmCode=" + rmCode, "Select Products ", "width=1000,height=800");
            popup.focus();
            return false
        }
            
            </script>   
   <script  type="text/javascript"> 
     
             
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
 
            </script>  
        </header>
    
    <body>
        <%
    
  String rmCode = request.getParameter("rmCode"); 
  String custId = request.getParameter("custId");
   String product[] = request.getParameterValues("product"); 
  String clientcontacted = request.getParameter("clientcontacted");
  String salescommitment = request.getParameter("salescommitment");
  String docsubmitted = request.getParameter("docsubmitted");
  String closed = request.getParameter("closed");
  String comments = request.getParameter("comments");
  String scheduledcalldate = request.getParameter("scheduledcalldate");
String custName = Customer.getCustName(custId);

    %>
<form id="form1" name="form1" method="post"   action="do?MOD=BOK&ACT=doacustomer" >
<center>
<strong>
Update Customer Tracker Previous  Details
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
                    <tr>
                        <th>  Product(s) Marketed:</th>
                       <td>  
                            <%
                                ArrayList soldProducts = Customer.getTodayProduct(custId, scheduledcalldate);
 
                                int noOfSoldProds = soldProducts.size(); 
                            %> 
                            <select name="todayproduct" id="todayproduct" size ="4">
                                <%
                                    for (int k = 0; k < noOfSoldProds; k++) {
                                        ArrayList two = (ArrayList) soldProducts.get(k);
                                        String prdo = (String) two.get(0);
                                %> 
                                <option value ="<%=prdo%>" > <%=prdo%></option>  
                                <%
                                    }


                                %>
                            </select> 
                       
                       </td>
                    </tr>
                    <tr>
                        <td> Select Date</td>
                        <td> 
                            <input  readonly="readonly" name="scheduledcalldate" type="text" id="scheduledcalldate" value="<%= scheduledcalldate%>" size="10">
                            </td>
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
                        Lead Source
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

                        ArrayList trackerList = Customer.getProductDefaultUpdate(custId, scheduledcalldate); 
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
                        <a href="" onclick="return getProductValue()"><img src="images/search.png"></a>
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