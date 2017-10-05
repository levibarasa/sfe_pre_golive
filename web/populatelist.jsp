<form id="form1" name="form1" method="post" >
    <input type="hidden" id ="rmCode" value="<%=rmCode%>" name="rmCode" >
    <td width="60%">
        <div class="zui-wrapper">
            <div class="zui-scroller">
                <table class="zui-table">
                    <thead>
                    <th>Update</th>
                    <th >Customer ID </th>
                    <th >Name</th>
                    <th>Phone Number</th>
                    <th>Customer Type</th>
                    <th>Email ID</th>
                    <th>Occupation</th>
                    <th>Current Product Holdings</th>
                    <th>Client Contacted</th>
                    <th>Sales Commitment</th>
                    <th>Docs Submitted</th>
                    <th>Closed</th>
                    <th>Comments</th>
                    <th>Scheduled Call Date</th>

                    </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList list = new ArrayList();
                            String Customer_ID, Name, Permanent_phonenumber,
                                    Customer_Type, email_ID, Occupation, Client_Contacted,
                                    Sales_Commitment, Docs_Submitted, Closed, Comments, Filled_Week;
                            for (int i = 0; i < size; i++) {
                                ArrayList one = (ArrayList) ar.get(i);

                                Customer_ID = (String) one.get(0);

                                Name = (String) one.get(1);
                                Permanent_phonenumber = (String) one.get(2);
                                Customer_Type = (String) one.get(3);
                                email_ID = (String) one.get(4);
                                Occupation = (String) one.get(5);
                                Client_Contacted = (String) one.get(6);
                                Sales_Commitment = (String) one.get(7);
                                Docs_Submitted = (String) one.get(8);
                                Closed = (String) one.get(9);
                                Comments = (String) one.get(10);
                                Filled_Week = (String) one.get(11);


                        %>   

                        <tr style="font-size: 9px">
                            <td  style="font-size: 9px;" width="80px">
                                <a href ="" onclick="return getCustUpdateValue('<%= Customer_ID%>')">
                                    <img src="images/update.jpg" name="alarm" width="30" height="30"   border="0"/> 
                                </a>
                            </td> 
                            <td style="font-size: 9px;width: 50px;overflow: hidden; text-overflow: ellipsis;" width="80px"> 
                                <a  href="" onclick="return getCustInfoValue('<%= Customer_ID%>')" style="font-size:9px;color: #666666; text-decoration: none;" >
                                    <input readonly="readonly" style="border: none;background-color: transparent;" type="text" value="<%=Customer_ID%>" name ="custId" id="custId">
                                </a>
                            </td>
                            <td   style="font-size: 9px">
                                <a href="" onclick="return getCustInfoValue('<%= Customer_ID%>')" style="font-size:9px;color: #666666; text-decoration: none;word-wrap: break-word;">  
                                    <span style="display:block;width:200px;word-wrap:break-word;">
                                        <input readonly="readonly" style="border: none;background-color: transparent;" type="text" value="<%=Name%>" name ="name" id="name"> 


                                    </span> 

                                </a>
                            </td>
                            <td   width="80px" style="font-size:9px;color: #666666;"><%=Permanent_phonenumber%></td>
                            <td    width="80px" style="font-size:9px;color: #666666;"><%=Customer_Type%></td>
                            <td   width="80px" style="font-size:9px;color: #666666;"><%=email_ID%></td>
                            <td    width="80px" style="font-size:9px;color: #666666;"><%=Occupation%></td>
                            <%

                                int rating = Customer.getCurrentProducts(Customer_ID).size();
                            %> 

                            <td   width="80px"   <%
                                if (rating < 1) {
                                  %> 
                                  style="background-color:red;font-size: 9px;"
                                  <%
                                  } else
                                  %> 
                                  <% if (rating == 2) {
                                  %> 
                                  style="font-size: 9px;background-color:yellow;"
                                  <%
                                  } else
                                  %> 
                                  <% if (rating == 3) {
                                  %> 
                                  style="font-size: 9px;background-color:yellow;"
                                  <%
                                  } else
                                  %> 
                                  <% if (rating == 4) {
                                  %> 
                                  style="font-size: 9px;background-color:yellow;"
                                  <%
                                  } else
                                  %> 

                                  <% if (rating > 5) {
                                  %> 
                                  style="font-size: 9px;background-color:green;"
                                  <%
                                      }
                                  %> 
                                  >
                                <%=rating%>
                            </td>


                            <td style="font-size: 9px;width: 50px;overflow: hidden; text-overflow: ellipsis;" width="80px"> 
                                <input readonly="readonly" style="border: none;background-color: transparent;" type="text" value="<%=Client_Contacted%>" name ="clientcontacted" id="clientcontacted">
                            </td>

                            <td style="font-size: 9px;width: 50px;overflow: hidden; text-overflow: ellipsis;" width="80px"> 
                                <input readonly="readonly" style="border: none;background-color: transparent;" type="text" value="<%=Sales_Commitment%>" name ="salescommitment" id="salescommitment">
                            </td>

                            <td style="font-size: 9px;width: 50px;overflow: hidden; text-overflow: ellipsis;" width="80px"> 
                                <input readonly="readonly" style="border: none;background-color: transparent;" type="text" value="<%=Docs_Submitted%>" name ="docsubmitted" id="docsubmitted">
                            </td>

                            <td style="font-size: 9px;width: 50px;overflow: hidden; text-overflow: ellipsis;" width="80px"> 
                                <input readonly="readonly" style="border: none;background-color: transparent;" type="text" value="<%=Closed%>" name ="closed" id="closed">
                            </td>

                            <td  style="font-size: 9px;" width="80px">
                                <input readonly="readonly"  type="text" id ="comments" value="<%=Comments%>" id="comments"  name="comments" size="30">
                            </td>
                            <td  style="font-size: 9px;" width="80px">
                                <input readonly="readonly"  type="text" value="<%=Filled_Week%>" name="scheduledcalldate" id="scheduledcalldate">
                            </td> 

                        </tr>

                        <%
                            }
 
                        %> 

                    </tbody>
                </table>
            </div>
        </div>



</form> 