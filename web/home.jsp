<%--
Document : index
Created on : Nov 5, 2012, 6:06:23 PM
Author	 : mano
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.nts.service.UserService"%>
<%@page import="com.nts.model.User"%>
<%@page import="java.util.List"%> 
<%@page import="java.util.Date"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css"/>
        <title>Home </title>	
    </head>
    <body>
    <center>
        <div id="mystyle">
            <h1>VRS</h1>
            <p><a href=" "></a><br/>
                <b>VRS</b><br/>
                <%=new Date()%></br>
                <%
                    User user = (User) session.getAttribute("user");
                %>		
                <b>Welcome <%= user.getName()%></b>		
                <br/>
                <a href="logout.jsp">Logout</a>
            </p>

            <table>
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>User Name</th>
                        <th>Full Names</th> 
                        <th>User Role</th>					
                    </tr>
                </thead>
                <tbody>
                    <%

                        ArrayList list = UserService.getAllUserDetails();
                         for (int k = 0; k < list.size(); k++) {
                         ArrayList listitem = (ArrayList) list.get(k);   
                        String userId = (String) listitem.get(0);
                        String userName = (String) listitem.get(1);
                        String passWord = (String) listitem.get(2);
                        String names = (String) listitem.get(3);
                        String role = (String) listitem.get(4);
                    %>
                    <tr>
                        <td><%=userId %></td>
                        <td><%=userName%></td>
                        <td><%=names%></td> 
                        <td><%=role%></td>
                    </tr>
                    <%}%>
                <tbody>
            </table>		
            <br/>
        </div>

    </center>		
</body>
</html>