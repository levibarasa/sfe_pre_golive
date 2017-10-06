<%@page import="com.sfe.dao.customer.Customer"%>
<html>
    <%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
             java.util.*" errorPage="" %>
    <%
        ArrayList all = Customer.getRegion();
        int size = all.size();
    %>	
    <head>
        <style type="text/css">
            <!--
            .style10 {color: #013567; font-weight: bold; font-size: 14px; }
            -->
        </style>
        <script type="text/javascript">
            function getAccountValue(reg) {
                if (window.opener !== null && !window.opener.closed) {
                    var region = window.opener.document.getElementById("region"); 
                    region.value = reg; //for innerhtml 
                    window.close();
                }

            }

        </script>

    </head>
    <div class="header">All Regions </div>
    <br/>
    <form id="popup">
        <table width="95%" align="center"  style="border:#013567 solid 2px;padding:10px;" border="0">
            <tr>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Regions </span></th> 
            </tr>
            <%
                for (int i = 0; i < size; i++) {
                    ArrayList one = (ArrayList) all.get(i);
            %>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getAccountValue('<%=(String) one.get(0)%>')" id="region"><%=(String) one.get(0)%></a></div></td>
                  </tr>
            <% }%>
        </table>
    </form>
</html>