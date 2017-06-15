<link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.dao.customer.Customer" errorPage="" %>
<%
    Customer frd = new Customer();
    ArrayList all = frd.getUnMappedAccounts();
    int size = all.size();
%>
<style type="text/css">
    <!--
    .style10 {color: #000000; font-weight: bold; font-size: 12px; }
    .style11 {color: #E49C7C}
    .button {color: #FF9E4D}
    -->
</style>
<script type="text/javascript">
    if (${mapsuc == 'true'}) {
        alert("Customer mapping successful");
    }
      
    if (${mapErr == 'true'}) {
        alert("Customer mapping failed or \n You have reached maximum number of group members per subgroup ");
    }
    if (${subErr == 'true'}) {
        alert("Customer Mapping error. Check input values");
    }
</script>
<script type="text/javascript">
    var popup;
    function getSubGroup(id) {
        popup = window.open("customer/subGroups.jsp?d=" + id, "Functions", "width=500,height=400, resizable=no");
        popup.focus();
        return false;
    }
</script>
<script>
    function deleteDriver(id) {
        if (confirm('Want to map this customer?')) {
            var subgrou = document.getElementById(id + "subgroup").value;
            var actType = document.getElementById(id + "custType").value;
            window.location.href = 'do?MOD=BOK&ACT=doacustomer&did=' + id + '&subgroup=' + subgrou + '&custType=' + actType;
        }
    }
</script>
<div class="header">All Unmapped Customers</div>
<br/>
<br/>
<form method="post">
    <div class="div">
        <table width="95%" align="center"  style="border:#019ADD solid 2px;padding:10px;" border="1">
            <tr>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Customer Number </span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Customer Name </span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Customer Sol Id</span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Sub Group Code</span></th>
                <th></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Credit Rating</span></th>
                <th bgcolor="#019ADD" scope="col"><span class="style10">Action</span></th>
            </tr>
            <%            for (int i = 0; i < size; i++) {
                    ArrayList one = (ArrayList) all.get(i);
            %>l
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><%=(String) one.get(0)%></div></td>
                <td><div align="center"><%=(String) one.get(1)%></div></td>
                <td><div align="center"><%=(String) one.get(2)%></div></td>
                <td>
                    <label>
                        <input name="<%= (String) one.get(0) + "subgroup"%>" type="text" readonly="true"  id="<%= (String) one.get(0) + "subgroup"%>" onkeyup="caps(this)" class="textboxes" size="10"/>
                        <input name="<%= (String) one.get(0) + "subgroupName"%>" type="hidden" readonly="true"  id="<%= (String) one.get(0) + "subgroupName"%>" onkeyup="caps(this)" class="textboxes"/>
                    </label>
                </td> <td><a href="" onclick="return getSubGroup('<%=(String) one.get(0)%>')"><img src="images/search.png"></a></td>
                <td>
                    <label>
                        <input type="text" name="<%= (String) one.get(0) + "custType"%>" onkeyup="this.value = this.value.toUpperCase();" value="1" id="<%= (String) one.get(0) + "custType"%>" readonly="true" />
                    </label>
                </td>
                <td>
                    <input value="Add" type="button" class="redButton" onclick="javascript:deleteDriver('<%= (String) one.get(0)%>')" size="6"/>
                </td>
            </tr>
            <% }%>
        </table>
    </div>
</form>