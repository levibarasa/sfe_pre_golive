<%@page import="com.sfe.dao.customer.Customer"%>
<%@page import="java.util.ArrayList"%>

<html>
    <header>
<script type="text/javascript">
        var popup;
        function getRmCodeValue() {
            popup = window.open("rpt/rmCodeList.jsp", "Functions", "width=500,height=400, resizable=false");
            popup.focus();
            return false;
        }
    </script>

        <script  type="text/javascript">
            function doapostDriver() {
                var rmCode = document.getElementById("rmCode").value;
                window.location.href = 'do?MOD=BOK&ACT=doadexistcustomer&rmCode=' + rmCode;
            }

        </script>  


    </header>
 
       

        <form id="form1" name="form1" method="post" onSubmit="return doapostDriver();" action="do?MOD=BOK&ACT=doadexistcustomer" >
      <center><strong>Create/Update Rm Details</strong><br/><br/>
                <table width="600">
                    <tr width="600">
                        <td    style="color:gray; font-style:italic;">RM Code :</td>
                     <td> </td>
                        <td  style="color: gray; font-style:italic;">
                            <input type="text"   id="rmCode" name="rmCode" value=""  >
                            <a href="" onClick="return getRmCodeValue()"><img src="images/search.png"></a>
                            RM Name:</td>
                             <td> 
                               <input type="text"   id="rmName" name="rmName" value=""  >
                             </td>
                    </tr>  
                    <tr width="600">
                        <td    style="color:gray; font-style:italic;">Access Level</td>
                         <td> </td>
                        <td   style="color: gray; font-style:italic;">  <%
                                ArrayList arr = Customer. getAcessLevel() ;
 
                                int arrsize = arr.size(); 
                            %> 
                            <select name="todayproduct" id="todayproduct">
                                <%
                                    for (int k = 0; k < arrsize; k++) {
                                        ArrayList two = (ArrayList) arr.get(k);
                                        String al = (String) two.get(0);
                                %> 
                                <option value ="<%=al%>" > <%=al%></option>  
                                <%
                                    }


                                %>
                            </select> 
                        </td>
                         <td> </td>
                    </tr> 
                    <tr width="600">
                        <td    style="color:gray; font-style:italic;">Windows UserName</td>
                        <td> </td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text"   id="winUsername" name="winUsername" value=""  >
                           </td>
                         <td> </td>
                    </tr>
                    <tr width="600">
                        <td    style="color:gray; font-style:italic;">Designation</td>
                         <td> </td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text"   id="designation" name="designation" value=""  >
                        </td>
                         <td> 
                          
                      </td>
                    </tr>
                    <tr width="600">
                        <td    style="color:gray; font-style:italic;">Branch Code</td>
                         <td> </td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text"   id="branchcode" name="branchcode" value=""  >
                           <a href="" onClick="return getBranchValue()"><img src="images/search.png"></a> Branch Name
                        </td>
                         <td> 
                         <input type="text"   id="branchname" name="branchname" value=""  >
                      </td>
                    </tr>
                     <tr width="600">
                        <td    style="color:gray; font-style:italic;">Region </td>
                         <td> </td>
                        <td   style="color: gray; font-style:italic;"> 
                          <input type="text"   id="region" name="region" value=""  >
                           <a href="" onClick="return getRegionValue()"><img src="images/search.png"></a></td>
                         <td>&nbsp;</td>
    </tr>
                    <tr width="600">
                        <td    style="color:gray; font-style:italic;">Branch Manager Code</td>
                         <td> </td>
                        <td   style="color: gray; font-style:italic;"> 
                            <input type="text"   id="regioncode" name="regioncode" value=""  >
                           <a href="" onClick="return getBMValue()"><img src="images/search.png"></a> BM Name
                   </td>
                         <td> 
                         <input type="text"   id="regionname" name="regionname" value=""  >
                      </td>
                    </tr>

                    <%
                    }
                    %>
                </table>
                  <br/><br/>
                <center><input type="button"  name="save" value="Save" width="100%" id="save"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 10em;  height: 2em; border-radius: 12px;">
                
                &nbsp;&nbsp;&nbsp;
                <input type="button"  name="update" value="Update" width="100%" id="update"   style="color:#ffffff;background-color:#24315e;align-self: center;width: 10em;  height: 2em; border-radius: 12px;">
          </center>

        </form>
    </center> 

<%@ include file="include/footer_one.jsp" %>