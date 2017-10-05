<html>
    <header>

        <script type="text/javascript">
            if (${custadd == 'true'}) {
                alert("Customer Saved Successfully.");
            }

        </script>
        <script type="text/javascript">
            function validate()
            {
                var Password = document.getElementById("Password");
                var Password1 = document.getElementById("Password1");
                var uname = document.getElementById("uname");

                var Password = document.getElementById("Password").value;
                var Password1 = document.getElementById("Password1").value;
                var uname = document.getElementById("uname").value;
                var valid = true;
                if (uname.value.length <= 0 || emailId.value.length <= 0 || phoneNo.value.length <= 0)
                {
                    alert("Don't leave the field empty!");
                    valid = false;
                } else {
                    if (isNaN(uname)) {
                        alert("Enter Correct Values for the fields");
                        valid = false;
                    }
                }
                return valid;
            }
            ;
        </script>

        <script type="text/javascript">
            //
            var popup;
            function postCNewUser() {
                var Password = document.getElementById("Password").value;
                var Password1 = document.getElementById("Password1").value;
                var uname = document.getElementById("uname").value;

                window.location.href = "do?MOD=BOK&ACT=dochC&uname=" + uname + "&Password1=" + Password1 + "&Password=" + Password;
            }
        </script>
    </header>

    <body>
        <%
              String rmCode = request.getParameter("rmCode");
        %>

    <center>  <Strong> Change Password</Strong>
        <form id="form1" name="form1"  onsubmit="return validate(); return postCNewUser();" method="post" action="do?MOD=BOK&ACT=dochC">
            <table width="400">
                <tr width="400">
                    <td    style="color: black; font-style:italic;">Relationship Officer Code:</td>
                    <td   style="color: black; font-style:italic;"> <input type="text" id="uname"   name="uname"  > </td> 
                </tr> 
                <tr width="400">
                    <td    style="color: black; font-style:italic;">&nbsp; </td>
                    <td   style="color: black; font-style:italic;">&nbsp;   </td>
                </tr> 
                <tr width="400">
                    <td    style="color: black; font-style:italic;">New Password:</td>
                    <td   style="color: black; font-style:italic;"> <input type="text" id="Password"   name="Password"  > </td>
                </tr> 
                <tr width="400">
                    <td    style="color: black; font-style:italic;">&nbsp; </td>
                    <td   style="color: black; font-style:italic;">&nbsp;   </td>
                </tr>
                <tr width="400">
                    <td    style="color: black; font-style:italic;">New Password Again:</td>
                    <td   style="color: black; font-style:italic;"> <input type="text" id="Password1"   name="Password1"  > </td>
                </tr> 
                <tr width="400">
                    <td    style="color: black; font-style:italic;">&nbsp; </td>
                    <td   style="color: black; font-style:italic;">&nbsp;   </td>
                </tr>

            </table>

            <center><input type="submit" name="Save" value="Change Password" width="100%" id="save"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 20em;  height: 2.5em; border-radius: 12px;"></center>

        </form>

    </center>
</body>
</html>