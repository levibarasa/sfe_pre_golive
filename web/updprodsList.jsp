<%@page import="com.sfe.dao.customer.Customer"%>
<html>
    <%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
             java.util.*" errorPage="" %>
    <%
         String custId = request.getParameter("custId"); 
         String rmCode = request.getParameter("rmCode");
         String custName = request.getParameter("custName");
         
        ArrayList all = Customer.getOtherCustProducts(custId); 
        int size = all.size();
    %>	
    <head>
        <style type="text/css">
            <!--
            .style10 {color: #013567; font-weight: bold; font-size: 14px; }
            -->
        </style>
        <script type="text/javascript">
             function doaddprodpostDriver(id) {
              var rmCode = document.getElementById("rmCode").value;
              var product = document.getElementById("product").value;
             window.location.href = 'do?MOD=BOK&ACT=doaddproduct&custId=' + id + '&rmCode=' + rmCode+ '&product=' + product ;
            }

        </script>

    </head>
    <div class="header">Add Product   </div>
    <br/>   
    <form id="form1" name="form1" method="post"  action="do?MOD=BOK&ACT=doaddproduct">
        <table width="95%" align="center"  style="border:#013567 solid 2px;padding:10px;" border="0">
            <tr width="400">
                        <td    style="color:gray; font-style:italic;">Customer ID</td>
                        <td  style="color: gray; font-style:italic;">
                            <input type="text" readonly="readonly"  id="custId" name="custId" value="<%=custId%>"  >
                        <input type="hidden" name="rmCode" id="rmCode" value="<%= rmCode%>"></td>
                    </tr>  
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Customer Name</td>
                        <td  style="color: gray; font-style:italic;">
                            <input type="text" readonly="readonly"  id="custName" name="custName" value="<%=custName%>"  > </td>
                    </tr>  
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Product</td>
                        <td  style="color: gray; font-style:italic;">
                            <select name="product" id="product" >
                                <%
                                    ArrayList prodlst = Customer.getOtherCustProducts(custId); 
                                    for (Object prood : prodlst) {

                                %> 
                                <option value ="<%=prood%>" > <%=prood%></option>  
                                <%
                                    }

                                %>
                            </select>
                        
                        </td>
                    </tr>  
        </table>
                            <center><input type="submit" onclick="returnn doaddprodpostDriver(id);" name="update" value="Add Product" width="100%" id="update"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 20em;  height: 2.5em; border-radius: 12px;"></center>
    </form>
</html>