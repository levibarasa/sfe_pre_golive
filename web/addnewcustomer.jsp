<html>
    <header>
        
        <script type="text/javascript">
        if (${custadd == 'true'}) {
            alert("Customer Saved Successfully.");
        }
         
    </script>
    </header>
    
    <body>
<form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=doadcustomer">
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