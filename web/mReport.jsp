<%@ include file="include/header_one.jsp" %> 
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
<table width="100%" height="350px">
    <tr width="100%">
        <td width="20%"><br/>
            <p> <input type="button" name="home" value="Home" onClick="window.location = 'index.jsp'" width="100%" id="home"   style="color:#ffffff;background-color:#24315e"></p>
            <p> <input type="button" name="weeklycalllist" onClick=" return dopostDriver();window.location = 'weeklycalllist.jsp'" value="Daily Call List" width="100%" id="weeklycalllist"   style="color:#ffffff;background-color:#24315e"></p>
            <p> <input type="button" name="completelist" onClick=" return completeListRmCode(); window.location = 'completelist.jsp'" value="Complete List" width="100%" id="completelist"   style="color:#ffffff;background-color:#24315e"></p>
            <p> <input type="button" name="reports" onClick="return dopostRptDriver(); window.location = 'rptHome.jsp'" value="Reports" width="100%" id="reports"   style="color:#ffffff;background-color:#24315e"></p>
            <p> <input type="button" name="updaterevenue" value="Update Revenue/Income" width="100%" id="updaterevenue"   style="opacity: 0.6; cursor: not-allowed;color:#ffffff;background-color:#24315e"></p>


        </td>
        <td width="80%">
    <center>

        <object  type="application/x-pdf" title="otherreportspdf" align="left">
            <embed src="<%=path%>" width="900" height="550" quality="High"/>
        </object>
    </center>
</td> 
</tr>
</table>
<%@ include file="include/footer_one.jsp" %>

