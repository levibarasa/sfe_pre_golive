<%@page import="com.sfe.dao.customer.Customer"%>
<html>
    <%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
             java.util.*" errorPage="" %>
    <%
        String custId = request.getParameter("custId");
        String scheduledcalldate = request.getParameter("scheduledcalldate");
        ArrayList all = Customer.getAllDefaultProductUpdate(custId);
        int size = all.size();
        
System.out.println("Products for Date: "+scheduledcalldate);
    %>	
    <head>
        <style type="text/css">
            <!--
            .style10 {color: #013567; font-weight: bold; font-size: 14px; }
            -->
        </style>
        <script type="text/javascript"> 
            function getAccountValue(prod,ldsrc,curncy,prodvalue,
            clientcontctd,salescomtmnt,docsubmttd,closd
            ,prodsold,trckid,comnts,schduldclldte) { 
                if (window.opener !== null && !window.opener.closed) {
                   var product = window.opener.document.getElementById("product");
                    var leadsrc = window.opener.document.getElementById("leadsrc");
                    var currency = window.opener.document.getElementById("currency");
                    var productvalue = window.opener.document.getElementById("productvalue");
                    var clientcontacted = window.opener.document.getElementById("clientcontacted");
                    var salescommitment = window.opener.document.getElementById("salescommitment");
                    var docsubmitted = window.opener.document.getElementById("docsubmitted");
                    var closed = window.opener.document.getElementById("closed");
                    var productsold = window.opener.document.getElementById("productsold");
                    var trackid = window.opener.document.getElementById("trackid");
                    var comments = window.opener.document.getElementById("comments");
                    var scheduledcalldate = window.opener.document.getElementById("scheduledcalldate");
                    
            product.value = prod; //for innerhtml
                    leadsrc.value = ldsrc;
                    currency.value = curncy;
                    productvalue.value = prodvalue;
                    clientcontacted.value = clientcontctd;
                    salescommitment.value = salescomtmnt;
                    docsubmitted.value = docsubmttd;
                    closed.value = closd;
                    productsold.value = prodsold;
                    trackid.value = trckid;
                    comments.value = comnts;
                    scheduledcalldate.value = schduldclldte; 
                    

                    window.close();
                }

            }

        </script>

    </head>
    <div class="header">All Todays Products </div>
    <br/>
    <form id="popup">
        <table width="95%" align="center"  style="border:#013567 solid 2px;padding:10px;" border="0">
            <tr>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Product </span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Date </span></th> 
            </tr>
            <%
                String Currency, Product_value, Marketed_Value, Client_Contacted, Sales_Commitment, 
                                Docs_Submitted, Closed, Comments, Filled_Week,Lead_Source,Product_Sold,trackId;
                for (int i = 0; i < size; i++) {
                    ArrayList one = (ArrayList) all.get(i);
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
            %>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" 
         onclick="getAccountValue('<%=Marketed_Value%>', '<%=Lead_Source%>', '<%=Currency%>', '<%=Product_value%>'
                     , '<%=Client_Contacted%>', '<%=Sales_Commitment%>', '<%=Docs_Submitted%>', '<%=Closed%>'
                             , '<%=Product_Sold%>', '<%=trackId%>', '<%=Comments%>', '<%=Filled_Week%>')" id="product">
                            <%=Marketed_Value%></a></div></td>
                <td><div align="center"><%=Filled_Week%></div></td> 
            </tr>
            <% }%>
        </table>
    </form>
</html>