<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%
        String path = "";
        String rfomart = (String) session.getAttribute("rformat");
        if(rfomart.equalsIgnoreCase("") || rfomart == null){
        rfomart = "PDF";
        }
       System.out.println("Report format " + rfomart);
        switch (rfomart) {
            case "PDF":
                path = request.getContextPath() + "/Reports/" + (String) session.getAttribute("rname") + ".pdf";
                System.out.println("Report path " + path);
                break;
            default:
                path = request.getContextPath() + "/Reports/" + (String) session.getAttribute("rname") + ".xlsx";
                System.out.println("Report path " + path);
                break;
        }


    %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    <meta http-equiv="cache-control" content="max-age=0" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <link rel="icon" href="images/favicon.ico" type="image/x-icon" /> 
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="pragma" content="no-cache" />
    <script language="javaScript" type="text/javascript" src="include/sim.js"></script>
    <object  type="application/x-pdf" title="otherreportspdf" align="left">
        <embed src="<%=path%>" width="900" height="550" quality="High"/>
    </object>
</html>