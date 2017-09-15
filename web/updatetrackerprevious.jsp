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
            window.location.href = 'do?MOD=BOK&ACT=doapcustomer&custId=' + id+'&rmCode=' + rmCode+'&clientcontacted=' + clientcontactedj+'&salescommitment=' + salescommitmentj+'&docsubmitted=' + docsubmittedj+'&closed=' + closedj+'&comments=' + commentsj+'&scheduledcalldate='+ scheduledcalldatej;
             }
            
            </script>  

   
    </header>
    
    <body>
        <%
    
  String rmCode = request.getParameter("rmCode");
  String custId = request.getParameter("custId");
  String clientcontacted = request.getParameter("clientcontacted");
  String salescommitment = request.getParameter("salescommitment");
  String docsubmitted = request.getParameter("docsubmitted");
  String closed = request.getParameter("closed");
  String comments = request.getParameter("comments");
  String scheduledcalldate = request.getParameter("scheduledcalldate");
String custName = Customer.getCustName(custId); 
System.out.println(rmCode+" "+custId+" "+clientcontacted+" "+salescommitment+" "+docsubmitted+" "+closed+" "+comments+" "+scheduledcalldate);
    %>
<form id="form1" name="form1" method="post" onSubmit="javascript:doapostDriver('<%= custId %>');" action="do?MOD=BOK&ACT=doapcustomer" >
<center>
<strong>
Update Customer Tracker Details
</strong>

<br/><br/>

<input type="hidden" name="rmCode" id="rmCode" value="<%= rmCode %>">
<table width="400">
    <tr width="400">
        <td    style="color: black; font-style:italic;">&nbsp; </td>
        <td   style="color: black; font-style:italic;">&nbsp; </td>
    </tr><tr width="400">
        <td    style="color:gray; font-style:italic;">Customer ID</td>
        <td  style="color: gray; font-style:italic;">
        <input type="text"   id="custId" name="custId" value="<%=custId%>"  > </td>
    </tr>
    <tr width="400">
        <td    style="color:gray; font-style:italic;">Customer Name
            
    </td>
        <td   style="color: gray; font-style:italic;"> 
            
            <input type="text" id="custName" value="<%=custName%>"   name="custName"  >
        </td>
    </tr> 
    <tr width="400">
        <td    style="color:gray; font-style:italic;">Client Contacted
          
</td>
        <td   style="color: gray; font-style:italic;"> 
            <select width="20px" id="clientcontacted" name="clientcontacted">
                           <option value="No">No</option>
                             <option value="Yes">Yes</option>  
                                </select>
        </td>
    </tr>
    <tr width="400">
        <td    style="color:gray; font-style:italic;">Sales Commitment
            
  </td>
        <td   style="color: gray; font-style:italic;"> 
             <select width="20px" id="salescommitment" name="salescommitment">
                 <!-- <option ><%= // salescommitment %></option> -->
                              <option value="No">No</option>
                             <option value="Yes">Yes</option>
                   
                                </select>
        </td>
    </tr>
    <tr width="400">
        <td    style="color:gray; font-style:italic;">Docs Submited</td>
        <td   style="color: gray; font-style:italic;">  
<select width="20px" id="docsubmitted" name="docsubmitted"> 
                           <!--     <option  ><%= //docsubmitted %></option> -->
                             <option value="No">No</option>
                             <option value="Yes">Yes</option>
                                
          </select>
        </td>
    </tr>
    <tr width="400">
        <td    style="color:gray; font-style:italic;">Closed</td>
        <td   style="color: gray; font-style:italic;"> 
<select width="20px" id="closed" name="closed">
                        <!--     <option>  <%= //closed %></option> -->
                              <option value="No">No</option>
                             <option value="Yes">Yes</option>
          </select>
        </td>
    </tr>
    <tr width="400">
        <td    style="color:gray; font-style:italic;">Comments</td>
        <td   style="color: gray; font-style:italic;"> 
            <input type="text" id="comments"  value="" name="comments" size="30" >
        </td>
    </tr> 
    <tr width="400">
        <td    style="color:gray; font-style:italic;">Scheduled Date</td>
        <td   style="color: gray; font-style:italic;"> 
            <input type="text" readonly="readonly"  value="<%= scheduledcalldate %>" name="scheduledcalldate" id="scheduledcalldate">
        </td>
    </tr>
    
</table>
<br/><br/>
<center><input type="submit"  name="update" value="Save" width="100%" id="update"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 20em;  height: 2.5em; border-radius: 12px;"></center>

  </form>
        </center>
</body>
    </html>