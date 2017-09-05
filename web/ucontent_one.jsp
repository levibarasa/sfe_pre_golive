<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*" errorPage="" %>
<%
    //ArrayList arr = Product.featuredFour();
%>
<style type="text/css">
    .image {
        overflow: hidden;
        transition-duration: 0.8s;
        transition-property: transform;
    }
    .image:hover {
        transform: rotate(360deg);
        -webkit-transform: rotate(360deg);
    }
    #container {
        width: 960px;
        margin: 0 auto;
    }

    #primary {
        float: left;
        width: 240px;
    }

    #content {
        float: left;
        width: 480px;
    }

    #secondary {
        float: left;
        width: 240px;
    }

    #footer {
        clear: both;
    }
</style>
<script type="text/javascript">
    if (${nonexistencemenu == 'true'}) {
        alert("ERROR\nMenu Option Does Not Exist");
    }
    if (${rolemenunone == 'true'}) {
        alert("ERROR\nAccess is denied");
    }
</script>

<div id="container">


    <div id="primary">


    </div>

    <div id="content">
        <br/><br/> 
        <!--img src="images/sfe.png" name="sfe" width="450" height="200"   border="0"/-->
    </div>

    <div id="secondary">
        <br/><br/><br/> 
        <!--table width="600">
            <tr><td width="200"></td>
                <td>
        <form>
          
            <center style="font: Calibri; font-size: 14px; color:#03f; font-weight:bolder; font-style: italic ">Relationship Officer Code</center>
 
        </td></tr><tr><td width="200"></td>
        <td>
            <center> <input type="text" name ="username" id="username" size="30"></center><br/>
            </td></tr><tr><td width="200"></td>
            <td>
            <center style="font: Calibri; font-size: 14px; color:#03f; font-weight:bolder; font-style: italic ">Password</center>
      
            </td></tr><tr><td width="200"></td>
            <td>
            <center><input type="password" name ="pass" id="pass" size="30"></center><br/><br/>
            </td></tr><tr><td width="200"></td>
            <td width="100">
            <center><input type="submit" value="    Login    " width="100" bgcolor="#24315e" style="background-color: #24315e; color: #eee;" size="30"></center>
        </form>
                </td></tr>
        <tr><td width="200"></td>
            <td><center>
                <a href="" style="font-style:italic; color:#03f;font-size: 9 px;">
                    Forgot or Change Password
                </a>  
        </center>
            </td>
        </tr>
        <tr><td width="200"></td>
            <td>
                 <center>
                 <input type="submit" value=" Reset/Change Password  " width="50" bgcolor="#000000" style="background-color:#C8C8C8; color: #eee;" size="30">
                 </center>
            </td>
        </tr>
          </table-->
    </div>


</div>






