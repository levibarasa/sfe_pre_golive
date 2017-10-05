<%@page import="java.util.ArrayList"%>
<%@page import="com.sfe.dao.customer.Customer"%>
<html>
    <header>
       <script  type="text/javascript"> 
       function doapostDriver(trackid) { 
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
                window.location.href = 'do?MOD=BOK&ACT=doacustomer&trackid=' + trackid+'&marketedProduct=' + marketedProduct  +'&product=' + product + '&custId=' + custId + '&rmCode=' + rmCode + '&clientcontacted=' + clientcontactedj + '&salescommitment=' + salescommitmentj + '&docsubmitted=' + docsubmittedj + '&closed=' + closedj + '&comments=' + commentsj + '&scheduledcalldate=' + scheduledcalldatej + '&currency=' + currency + '&productvalue=' + productvalue+'&leadsrc='+leadsrc;
            }
    
          </script>
  </header>

    <body>
        <%
 
  String marketedProduct = request.getParameter("marketedProduct");
  String custId = request.getParameter("custId"); 
 String product = request.getParameter("product");
 String rmCode = request.getParameter("rmCode");
 String clientcontacted = request.getParameter("clientcontacted");
 String salescommitment = request.getParameter("salescommitment");
 String docsubmitted = request.getParameter("docsubmitted");
 String closed = request.getParameter("closed");
 String comments = request.getParameter("comments");
 String currency = request.getParameter("currency");
 String productvalue = request.getParameter("productvalue");
 String scheduledcalldate = request.getParameter("scheduledcalldate");
 String trackid = request.getParameter("trackid");
 String leadsrc = request.getParameter("leadsrc"); 
       %>

        <form id="form1" name="form1" method="post" onSubmit="return doapostDriver('<%= trackid %>');" action="do?MOD=BOK&ACT=doacustomer" >
            <center><strong>SELL PRODUCT
                </strong>

                <br/><br/>
           
               <input type="hidden" name="trackid" id="trackid" value="<%= trackid %>">
                <input type="hidden" name="rmCode" id="rmCode" value="<%= rmCode %>">
                <table width="400">
                    <tr width="400">
                        <td    style="color: black; font-style:italic;">&nbsp; </td>
                        <td   style="color: black; font-style:italic;">&nbsp; </td>
                    </tr><tr width="400">
                        <td    style="color:gray; font-style:italic;">Customer ID</td>
                        <td  style="color: gray; font-style:italic;">
                            <input type="text" readonly="readonly"  id="custId" name="custId" value="<%=custId%>"  > </td>
                    </tr>  
                      <tr width="400">
                        <td    style="color:gray; font-style:italic;">Product Sold

                        </td>
                        <td   style="color: gray; font-style:italic;"> 

                            <input type="text" readonly="readonly"  id="product" value="<%=product%>"   name="product"  >
                        </td>
                    </tr> 
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Average Product Value</td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text" readonly="readonly"   id="productvalue" name="productvalue" value="<%=productvalue%>"  >
                        </td>
                    </tr>
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Marketed Product</td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text" readonly="readonly"   id="marketedProduct" name="marketedProduct" value="<%=marketedProduct%>"  >
                        </td>
                    </tr>
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Currency</td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text" readonly="readonly"   id="currency" name="currency" value="<%=currency%>"  >
                        </td>
                    </tr>
                      <tr width="400">
                        <td    style="color:gray; font-style:italic;">Client Contacted</td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text" readonly="readonly"   id="clientcontacted" name="clientcontacted" value="<%=clientcontacted%>"  >
                        </td>
                    </tr>
						<tr width="400">
                        <td    style="color:gray; font-style:italic;">Sales Commitment</td>
                        <td  style="color: gray; font-style:italic;">
                            <input type="text" readonly="readonly"   id="salescommitment" name="salescommitment" value="<%=salescommitment%>"  > </td>
                    </tr>  
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Docs Submitted</td>
                        <td   style="color: gray; font-style:italic;"> 

                            <input type="text" readonly="readonly" id="docsubmitted" value="<%=docsubmitted%>"   name="docsubmitted"  >
                        </td>
                    </tr> 
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Closed</td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text" readonly="readonly"   id="closed" name="closed" value="<%=closed%>"  >
                        </td>
                    </tr>
                       <tr width="400">
                        <td    style="color:gray; font-style:italic;">Comments</td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text" readonly="readonly"   id="comments" name="comments" value="<%=comments%>"  >
                        </td>
                    </tr>
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Scheduled Date</td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text" readonly="readonly"   id="scheduledcalldate" name="scheduledcalldate" value="<%=scheduledcalldate%>"  >
                        </td>
                    </tr>
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Lead Source</td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text" readonly="readonly"   id="leadsrc" name="leadsrc" value="<%=leadsrc%>"  >
                        </td>
                    </tr>
                    <%
                    }
                    %>
                </table>
                <br/><br/>
                <center><input type="submit"  name="update" value="Sell Product" width="100%" id="update"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 20em;  height: 2.5em; border-radius: 12px;"></center>

        </form>
    </center>
    <p>&nbsp;</p>
</body>
</html>