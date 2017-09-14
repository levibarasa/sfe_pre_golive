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
                var cust_name = document.getElementById("cust_name");
                var emailId = document.getElementById("emailId");
                var phoneNo = document.getElementById("phoneNo");
                
                var cust_nameval = document.getElementById("cust_name").value;
                var emailIdval = document.getElementById("emailId").value;
                var phoneNoval = document.getElementById("phoneNo").value;
                var valid = true;
                if(cust_name.value.length<=0 || emailId.value.length<=0 || phoneNo.value.length<=0)
                    {
                        alert("Don't leave the field empty!");
                        valid = false;
                    }
                    else{
                        if(!isNaN(cust_nameval) || !isNaN(emailIdval)){
                            alert("Enter Correct Values for the fields");
                    valid = false;}
                }
                return valid;
            };
      </script>
      
      <script type="text/javascript">
        //
        var popup;
        function postNewUser() {  
            //phoneNo,emailId,cust_name,rmCode
                var rmCode = document.getElementById("rmCode").value;
                var cust_nameval = document.getElementById("cust_name").value;
                var emailIdval = document.getElementById("emailId").value;
                var phoneNoval = document.getElementById("phoneNo").value;
                
             window.location.href = "do?MOD=BOK&ACT=doadcustomer&rmCode="+rmCode+"&cust_name="+cust_nameval+"&emailId="+emailIdval+"&phoneNo="+phoneNoval;
        }
    </script>
    </header>
    
    <body>
        <%
              String rmCode = request.getParameter("rmCode");
            %>
<form id="form1" name="form1"  onsubmit="return validate(); return postNewUser();" method="post" action="do?MOD=BOK&ACT=doadcustomer">
<table width="400">
    <tr width="400">
        <td    style="color: black; font-style:italic;">Mandatory Fields</td>
        <td   style="color: black; font-style:italic;"> Optional Fields</td>
    </tr><tr width="400">
        <td    style="color:gray; font-style:italic;">Customer Name</td>
        <td  style="color: gray; font-style:italic;"> Customer Email ID</td>
    </tr><tr width="400">
        <td    style="color:gray; font-style:italic;">
            <input type="text"   id="cust_name" name="cust_name"  >
        </td>
        <td   style="color: gray; font-style:italic;"> 
            <input type="text" id="emailId"   name="emailId"  >
        </td>
    </tr><tr width="400">
        <td   style="color:gray; font-style:italic;"> 
        </td>
        <td   style="color: gray; font-style:italic;"> 
            Customer Phone Number
        </td>
    </tr><tr width="400">
        <td   style="color:gray; font-style:italic;"> 
            <input type="hidden" id ="rmCode" value="<%=rmCode%>" name="rmCode" >
        </td>
        <td   style="color: gray; font-style:italic;"> 
            <input type="text" id="phoneNo"   name="phoneNo"  >
        </td>
    </tr>
</table>

<center><input type="submit" name="Save" value="Save" width="100%" id="save"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 20em;  height: 2.5em; border-radius: 12px;"></center>

  </form>
    </body>
    </html>