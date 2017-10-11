<%@page import="com.sfe.dao.customer.Customer"%>
<%
    String designation = Customer.getRMDesignation(user_code);
    %>
    <input type="hidden" name="designation" id="designation" value="<%= designation %>">
<input type="hidden" name="rmCode" id="rmCode" value="<%= user_code %>">
    <table width="100%">
        <tr width="100%">
            <td width="20%"><br/>
                <p> <input type="button" name="home" value="Home" onClick="window.location = 'index.jsp'" width="100%" id="home"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="weeklycalllist" onClick=" return dopostDriver();window.location = 'weeklycalllist.jsp'" value="Daily Call List" width="100%" id="weeklycalllist"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="completelist" onClick=" return completeListRmCode(); window.location = 'completelist.jsp'" value="Complete List" width="100%" id="completelist"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="reports" onClick="return dopostRptDriver(); window.location = 'crptHome.jsp'" value="Reports" width="100%" id="reports"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="updaterevenue" value="Update Revenue/Income" width="100%" id="updaterevenue"   style="opacity: 0.6; cursor: not-allowed;color:#ffffff;background-color:#24315e"></p>


            </td>
            <td width="60%"><br/>
                <p style="color: #24315e; font-size: 14px"><Strong><u>Sales Force Effectiveness Tool </u> </Strong></p>
                <p style="font-style: italic; font-size: 13px"> The SFE Customer Masterlist tool enables a relationship officer to: </p>
                <p style="font-style: italic; font-size: 13px">1) Access up-to-date view of customers' product holding on a weekly basis </p>
                <p style="font-style: italic; font-size: 13px">2) Identify potential cross sell and next-best-products-to-sell opportunities in their portfolios </p>
                <p style="font-style: italic; font-size: 13px">3) View Performance in terms of Sales Pipeline, SFE Sustainability and the performance against budgets </p>

            </td>
            <td width="20%">
                <br/><br/><br/>
                <img src="images/alarm.png" name="alarm" width="50" height="50"   border="0"/>
                <p> <input type="button" onclick="return getReminders()" name="remindersfortoday" value="Reminders For Today" width="100%" id="remindersfortoday"   style="color:#ffffff;background-color:#24315e"></p>
                <br/><br/><br/><br/><br/> 
                <Strong>  
                    <p style="color: #24315e; font-size: 10px">In case of any clarifications or help, please contact:</p>
                    <p style="color: #24315e; font-size: 10px">Nicholas Talam Kiprotich -<br/> Nicholas.Kiprotich@imbank.co.ke</p>
                    <p style="color: #24315e; font-size: 10px">Abdul Muzahir Bhaijee -<br/> ABhaijee@imbank.co.ke</p>
                </strong>
            </td>
        </tr>
    </table>