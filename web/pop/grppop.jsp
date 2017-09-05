<%@page import="com.orig.gls.dao.group.Group"%>
<%@page import="com.orig.gls.dao.branch.Branch"%>
<html>
    <%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
             java.util.*" errorPage="" %>
    <%
       ArrayList all = Group.getAllGroups();
        int size = all.size();
    %>	
    <head>
        <style type="text/css">
            <!--
            .style10 {color: #013567; font-weight: bold; font-size: 14px; }
            -->
        </style>
        <script type="text/javascript">
            function getBranchValue(ths, sGroupName) {
                if (window.opener !== null && !window.opener.closed) {
                    var func = window.opener.document.getElementById("groupcode");
                    var grp = window.opener.document.getElementById("groupName");
                    func.value = ths.innerHTML; //for innerhtml
                    grp.value = sGroupName;
                    window.close();
                }

            }
        </script>

    </head>
    <div class="header">Available Branches</div>
    <br/>
    <form id="popup">
        <table width="95%" align="center"  style="border:#013567 solid 2px;padding:10px;" border="0">
            <tr>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Group Code</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Group Id</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Group Name</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Created Date</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Created By</span></th>
            </tr>
            <%
                for (int i = 0; i < size; i++) {
                    ArrayList one = (ArrayList) all.get(i);
            %>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getSubGroupCodeValue(this, '<%=(String) one.get(0)%>')" id="cname"><%=(String) one.get(2)%></a></div></td>
                <td><div align="center"><%=(String) one.get(1)%></div></td>
                <td><div align="center"><%=(String) one.get(2)%></div></td>
                <td><div align="center"><%=(String) one.get(3)%></div></td>
                <td><div align="center"><%=(String) one.get(4)%></div></td>
            </tr>
            <% }%>
        </table>
    </form>
</html>