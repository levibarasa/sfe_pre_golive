<html>
    <%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
             java.util.*" errorPage="" %>
    <head>
        <style type="text/css">
            <!--
            .style10 {color: #013567; font-weight: bold; font-size: 14px; }
            -->
        </style>
        <script type="text/javascript">
            function getFunctionValue(ths) {
                if (window.opener !== null && !window.opener.closed) {
                    var func = window.opener.document.getElementById("rFunction");
                    func.value = ths.innerHTML; //for innerhtml
                }
                window.close();
            }
        </script>

    </head>
    <div class="header">User Management</div>
    <br/>
    <form id="popup">
        <table width="95%" align="center"  style="border:#013567 solid 2px;padding:10px;" border="0">
            <tr>   
                <th bgcolor="#24315e" scope="col"><span class="style10"> 
                Function
                </span></th>
             </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onClick="getFunctionValue(this)" id="cname">
             AddNewRM   
                </a></div></td>
                
            </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onClick="getFunctionValue(this)" id="cname">EditRMDetails
                
                </a></div></td>
                
            </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onClick="getFunctionValue(this)" id="cname">DeleteRM
                
                </a></div></td>
                
            </tr>

        </table>
    </form>
</html>