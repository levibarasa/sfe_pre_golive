<%@page import="java.util.ArrayList"%>
<%@page import="com.sfe.dao.customer.Customer"%>
<html>
    <header>
        <script language="javaScript" type="text/javascript" src="include/sim.js"></script>
        <script type="text/javascript" src="include/jquery.1.4.2.js"></script>
        <script type="text/javascript" src="include/jsDatePick.jquery.min.1.3.js"></script>

        <link rel="stylesheet" type="text/css" media="all" href="include/jsDatePick_ltr.min.css" />
        <script type="text/javascript">
            var popup;
            function getMarketedProductValue(id) {
                popup = window.open("prodsList.jsp?custId=" + id, "Functions", "width=500,height=400, resizable=false");
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
            function doapostDriver(id) {
                //  var marketedProduct = getCheckedProducts(marketedProduct); 
                var marketedProduct = document.getElementById("marketedProduct").value;
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
                window.location.href = 'do?MOD=BOK&ACT=doacustomer&marketedProduct=' + marketedProduct + '&product=' + product + '&custId=' + id + '&rmCode=' + rmCode + '&clientcontacted=' + clientcontactedj + '&salescommitment=' + salescommitmentj + '&docsubmitted=' + docsubmittedj + '&closed=' + closedj + '&comments=' + commentsj + '&scheduledcalldate=' + scheduledcalldatej + '&currency=' + currency + '&productvalue=' + productvalue;
            }

        </script>  
    </header>

    <body>
        <%

            String name = request.getParameter("name");
            String rmCode = request.getParameter("rmCode");
            String custId = request.getParameter("custId");
            String product[] = request.getParameterValues("product");
            //String clientcontacted = request.getParameter("clientcontacted");
            //String salescommitment = request.getParameter("salescommitment");
            //String docsubmitted = request.getParameter("docsubmitted");
            //String closed = request.getParameter("closed");
            //String comments = request.getParameter("comments");
            String scheduledcalldate = request.getParameter("scheduledcalldate");
            String custName = Customer.getCustName(custId);
            String newCustName = Customer.getNewCustName(custId);
            if (custName == "" || custName == null) {
                custName = newCustName;
            }
        %>
        <form id="form1" name="form1" method="post" onSubmit="return doapostDriver('<%= custId%>');" action="do?MOD=BOK&ACT=doacustomer">
            <center>
                <strong>
                    Update Customer Tracker Details
                </strong>

                <br/><br/>

                <input type="hidden" name="rmCode" id="rmCode" value="<%= rmCode%>">
                <table width="400"> 
                    <tr width="400">
                        <td    style="color: black; font-style:italic;">&nbsp; </td>
                        <td   style="color: black; font-style:italic;">&nbsp; </td>
                    </tr><tr width="400">
                        <td    style="color:gray; font-style:italic;">Customer ID :</td>
                        <td  style="color: gray; font-style:italic;">
                            <input type="text"   id="custId" name="custId" value="<%=custId%>"  > </td>
                    </tr>
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Customer Name :

                        </td>
                        <td   style="color: gray; font-style:italic;"> 

                            <input type="text" id="custName" value="<%=custName%>"   name="custName"  >
                        </td> 
                    </tr> 
                    <%

                        ArrayList trackerList = Customer.getProductDefaultUpdate(custId, scheduledcalldate);

                        int size = trackerList.size();
                        String Currency, Product_value, Marketed_Value, Client_Contacted, Sales_Commitment, Docs_Submitted, Closed, Comments, Filled_Week;
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
                    %>
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">
                            Currency :<br/>
                            <select name="currency" id="currency">
                                <option selected="selected"  value="Ksh">Ksh</option>
                                <option value="USD">USD</option>
                                <option value="Tsh">Tsh</option>
                                <option value="Ush">Ush</option>
                            </select>
                        </td>
                        <td   style="color: gray; font-style:italic;"> 
                            Average Product Value :<br/>
                            <input type="text" id="productvalue" value="<%= Product_value%>"   name="productvalue"  >
                        </td> 
                    </tr> 
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Marketed Products

                        </td>
                        <td   style="color: gray; font-style:italic;">
                            <%

                                if (Marketed_Value == "" || Marketed_Value == null) {
                            %>
                            <br/> 
                            <select name="marketedProduct" id="marketedProduct" multiple="multiple" size=5 style="height:100px;-moz-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);
                                    -webkit-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);        
                                    box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);">
                                <%
                                    ArrayList prdlst = Customer.getOtherCustProducts(custId);
                                    System.out.println("Other products: " + prdlst);
                                    for (Object prd : prdlst) {

                                %> 
                                <option  value ="<%=prd%>" > <%=prd%></option>  
                                <%
                                    }

                                %>
                            </select>
                            <%                  } else {
                            %>
                            <br/> 
                            <select name="marketedProduct" id="marketedProduct" multiple="multiple" size=5 style="height:100px;-moz-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);
                                    -webkit-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);        
                                    box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);">
                                <option selected="selected" value ="<%=Marketed_Value%>" > <%=Marketed_Value%></option>  
                                <%
                                    }
                                %>
                            </select>
                        </td>

                    </tr> 
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Client Contacted

                        </td>
                        <td   style="color: gray; font-style:italic;"> 
                            <select width="20px" id="clientcontacted" name="clientcontacted">
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
                        </td>
                    </tr>
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Sales Commitment

                        </td>
                        <td   style="color: gray; font-style:italic;"> 
                            <select width="20px" id="salescommitment" name="salescommitment">
                                <%
                                    if (Sales_Commitment.equalsIgnoreCase("Yes")) {
                                %>
                                <option selected="selected" value="<%= Sales_Commitment%>"><%= Sales_Commitment%></option>
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
                    </tr> 
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Docs Submited</td>
                        <td   style="color: gray; font-style:italic;"> 
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
                        </td>
                    </tr>
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Closed</td>
                        <td   style="color: gray; font-style:italic;"> 
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
                    </tr>  
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Comments</td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text" id="comments"  value="<%= Comments%>" name="comments" size="30" >
                        </td>
                    </tr>
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Scheduled Date</td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text"  name="scheduledcalldate" value="<%= Filled_Week%>" id="scheduledcalldate">
                        </td>
                    </tr> 
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;"> 
                            Product(s) Sold :         
                        </td>
                        <td > 
                            <%
                                ArrayList soldProducts = Customer.getProductByDate(custId, Filled_Week);

                                System.out.println("Sold products: " + soldProducts);

                                int noOfSoldProds = soldProducts.size();
                                if (noOfSoldProds > 0) {
                            %> 
                            <select name="product" id="product" multiple="multiple" size=5 style="height:100px;-moz-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);
                                    -webkit-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);        
                                    box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);">
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
                            <%                        } else {
                            %>
                            <br/>  
                            <select name="product" id="product" multiple="multiple" size=5 style="height:100px;-moz-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);
                                    -webkit-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);        
                                    box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);">
                                <%
                                    ArrayList prodlst = Customer.getOtherCustProducts(custId);
                                    System.out.println("Other products: " + prodlst);
                                    for (Object prood : prodlst) {

                                %> 
                                <option value ="<%=prood%>" > <%=prood%></option>  
                                <%
                                    }

                                %>
                            </select>

                            <%                             }
                                }
                            %>
                        </td>
                    </tr>



                </table>

                <br/><br/>
                <center><input type="submit"   name="update" value="Save/Update" width="100%" id="update"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 20em;  height: 2.5em; border-radius: 12px;"></center>

        </form>
    </center>
</body>
</html>