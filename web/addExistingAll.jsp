<%@page import="java.util.ArrayList"%>
<%@page import="com.sfe.dao.customer.Customer"%>
<html>
    <header>


        <script  type="text/javascript">
            function doapostDriver(id) {
                var rmCode = document.getElementById("rmCode").value;
                window.location.href = 'do?MOD=BOK&ACT=doadexistcustomer&custId=' + id + '&rmCode=' + rmCode;
            }

        </script>  


    </header>

    <body>
        <%
    
  String rmCode = request.getParameter("rmCode");
  String custId = request.getParameter("custId"); 
ArrayList ar = Customer.populateCustomerListAllById(custId);
int size = ar.size();
        %>

        <form id="form1" name="form1" method="post" onSubmit="return doapostDriver('<%= custId %>');" action="do?MOD=BOK&ACT=doadexistcustomer" >
            <center><strong>Add Customer To Today's List
                </strong>

                <br/><br/>
                <%
                           
                                           String Customer_ID, Name, Permanent_phonenumber,
                                                   Customer_Type, email_ID, Occupation ; 
                                           for (int i = 0; i < size; i++) {
                                               ArrayList one = (ArrayList) ar.get(i);

                                               Customer_ID = (String) one.get(0);

                                               Name = (String) one.get(1);
                                               Permanent_phonenumber = (String) one.get(2);
                                               Customer_Type = (String) one.get(3);
                                               email_ID = (String) one.get(4);
                                               Occupation = (String) one.get(5); 
                        

                %>  
                <input type="hidden" name="rmCode" id="rmCode" value="<%= rmCode %>">
                <table width="400">
                    <tr width="400">
                        <td    style="color: black; font-style:italic;">&nbsp; </td>
                        <td   style="color: black; font-style:italic;">&nbsp; </td>
                    </tr><tr width="400">
                        <td    style="color:gray; font-style:italic;">Customer ID</td>
                        <td  style="color: gray; font-style:italic;">
                            <input type="text"   id="custId" name="custId" value="<%=Customer_ID%>"  > </td>
                    </tr>  
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Customer Name

                        </td>
                        <td   style="color: gray; font-style:italic;"> 

                            <input type="text" id="custName" value="<%=Name%>"   name="custName"  >
                        </td>
                    </tr> 
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Phone Number</td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text"   id="phone" name="phone" value="<%=Permanent_phonenumber%>"  >
                        </td>
                    </tr>
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Customer Type</td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text"   id="customerType" name="customerType" value="<%=Customer_Type%>"  >
                        </td>
                    </tr>
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Email ID</td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text"   id="emailID" name="emailID" value="<%=email_ID%>"  >
                        </td>
                    </tr>
                    <tr width="400">
                        <td    style="color:gray; font-style:italic;">Occupation </td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text"   id="occupation" name="occupation" value="<%=Occupation%>"  >
                        </td>
                    </tr>

                    <%
                    }
                    %>
                </table>
                <br/><br/>
                <center><input type="submit"  name="update" value="Add to Todays List" width="100%" id="update"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 20em;  height: 2.5em; border-radius: 12px;"></center>

        </form>
    </center>
    <p>&nbsp;</p>
</body>
</html>