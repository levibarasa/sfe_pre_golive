<%-- 
    Document   : logout
    Created on : Mar 2, 2017, 12:23:55 PM
    Author     : Levi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>logout Page</title>
    </head>
    <body>
        <%		
                session.removeAttribute("userName");
                session.removeAttribute("password");
                session.invalidate();
        %>
    <center>
        <h1>You have successfully logged out</h1>
        To login again <a href="login.jsp">click here</a>.
    </center>
</body>
</html>