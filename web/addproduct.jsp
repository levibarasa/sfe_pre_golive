<%@page import="java.util.ArrayList"%>
<%@page import="com.sfe.dao.customer.Customer"%>
<html>
    <header>


        <script  type="text/javascript">
            function doapostDriver(id) {
                var rmCode = document.getElementById("rmCode").value;
                var clientcontactedj = document.getElementById("clientcontacted").value;
                var salescommitmentj = document.getElementById("salescommitment").value;
                var docsubmittedj = document.getElementById("docsubmitted").value;
                var closedj = document.getElementById("closed").value;
                var commentsj = document.getElementById("comments").value;
                var scheduledcalldatej = document.getElementById("scheduledcalldate").value;
                window.location.href = 'do?MOD=BOK&ACT=doacustomer&custId=' + id + '&rmCode=' + rmCode + '&clientcontacted=' + clientcontactedj + '&salescommitment=' + salescommitmentj + '&docsubmitted=' + docsubmittedj + '&closed=' + closedj + '&comments=' + commentsj + '&scheduledcalldate=' + scheduledcalldatej;
            }

        </script>  
        <script  type="text/javascript">



            var popup;
            function getAddProductsValue(id) {
                var rmCode = document.getElementById("rmCode").value;
                popup = window.open("addproduct.jsp?custId=" + id + "&rmCode=" + rmCode, "Select Products ", "width=400,height=400");
                popup.focus();
                return false
            }
            
            function getCheckedProducts(chkboxName) {
                 var products = document.getElementById(chkboxName);
                var productsChecked = [];
                for (var i = 0; i < products.length; i++) {
                    if (products[i].checked) {
                        productsChecked.push(products[i]);
                    }
                }
                 
                return productsChecked.length > 0 ? productsChecked : null;
            }

 
               function getCustUpdateCValue(id) {
                var rmCode = document.getElementById("rmCode").value;
                var prods = getCheckedProducts(product);
                popup = window.open("updatetracker.jsp?product=" +prods + "&rmCode=" + rmCode + "&custId=" + id, "Update Customer Information", "width=600,height=600");
                popup.focus();
                return false
            }

        </script>   
        
    </header>

    <body>
    <center>
        <%

            String rmCode = request.getParameter("rmCode");
            String custId = request.getParameter("custId");

        %>
        
        <form action="updatetracker.jsp" name="form2" id="form2">
            <table>
                     
            <%
                ArrayList list2 = Customer.getOtherCustProducts(custId);
                for (Object product2 : list2) {

            %>  
            <tr>
            <td width="300px;">
                <input style="margin-left: 20px;" type="checkbox" name="product" id="product" value="<%=product2%>"> <%=product2%> <br/>
            </td></tr>
                <%
                    }
                %>
                
           
                    
            
            </table>
            <br/><br/>
            <input type="hidden" name="rmCode" id="rmCode" value="<%= rmCode%>">
        <input type="hidden" name="custId" id="custId" value="<%= custId%>">
            <center><input type="submit"  name="sell" value="Sell" width="100%" id="sell" onclick="return getCustUpdateCValue('<%= custId%>')"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 15em;  height: 2em; border-radius: 12px;"></center>

        </form>
    </center>
</body>
</html>