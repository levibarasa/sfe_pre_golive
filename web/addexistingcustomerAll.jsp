<html>
    <header>

        <script type="text/javascript">

            var request;

            function sendInfo()
            {
                var name = document.searchname.cust_name.value;
                var custid = document.searchname.cust_Id.value;
                var url = "do?MOD=BOK&ACT=dosearch&val=" + name;
                var url1 = "do?MOD=BOK&ACT=dosearch&val1=" + custid;
                if (window.XMLHttpRequest) {
                    request = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    request = new ActiveXObject("Microsoft.XMLHTTP");
                }

                try {
                    request.onreadystatechange = getInfo;
                    request.open("GET", url, true);
                    request.send();
                } catch (e) {
                    alert("Unable to connect to server");
                }
                try {
                    request.onreadystatechange = getInfo;
                    request.open("GET", url1, true);
                    request.send();
                } catch (e) {
                    alert("Unable to connect to server");
                }

            }

            function getInfo() {
                if (request.readyState == 4) {
                    var val = request.responseText;
                    document.getElementById('cus_name').innerHTML = val;
                }
            }

        </script>
        <script type="text/javascript">
            function getCustExistingValue() {
                var id = document.getElementById("custId").value;// 
                var custName = document.getElementById("custName").value;
                var rmCode = document.getElementById("rmCode").value;
                popup = window.open("displayexistingcustomerAll.jsp?custId=" + id + "&custName=" + custName + "&rmCode=" + rmCode, "Existing Customer Information", "width=600,height=600");
                popup.focus();
                return false
            }
        </script>
    </header>

    <body>
        <%
           String rmCode = request.getParameter("rmCode");
        %>
    <center>
        <form id="searchname" name="searchname"   method="post" action="">
            <table width="400"> 
                <tr width="400">
                <input type="hidden" name="rmCode" id="rmCode" value="<%= rmCode %>">
                <td    style="color: black; font-style:italic;">Customer Name   Or</td>
                <td   style="color: black; font-style:italic;"> Customer ID</td>
                </tr>
                <tr width="400">
                    <td    style="color:gray; font-style:italic;">
                        <input type="text"   id="custName" name="custName" >
                    </td>
                    <td   style="color: gray; font-style:italic;"> 
                        <input type="text" id="custId"   name="custId" >
                    </td>
                </tr>
            </table>
        </form>

        <center><input type="button" onclick="return getCustExistingValue()" name="search" value="Search" width="100%" id="search"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 15em;  height: 2em; border-radius: 12px;"></center>

    </center>
</body>

</html>