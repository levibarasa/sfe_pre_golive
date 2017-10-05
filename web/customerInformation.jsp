<%@page import="com.sfe.dao.customer.Customer"%>
<%@page import="java.util.ArrayList"%>

<%
%>
<html>
    <head>
        <title>Customer Information</title>
        <style type="text/css">
            #apDiv1 {
                position:absolute;
                width:848px;
                height:263px;
                z-index:1;
                left: 321px;
                top: 318px;
                background-color: #CCC;
            }
        </style>
    </head>
    <body> 
        <div style="width:100%;background-color:#24315e;color:#FFF;font-size:17px;text-align:center;">
            <strong>
                Detailed Customer View
            </strong>
        </div>
        </br>
        <div width="100%">
            <%
                String custId = request.getParameter("custId");
                System.out.println("Customer ID is : " + custId);
                if (custId == "" || custId.equalsIgnoreCase("")) {
                    custId = "10418";
                }
                ArrayList ar = Customer.getCustomerInfo(custId);
                System.out.println("Customer: " + ar);
                for (int i = 0; i < ar.size(); i++) {
                    ArrayList one = (ArrayList) ar.get(i);
                    String custID, name, phone, custType, occupation, emailId, age, Marital_status, address, yrs, city;
                    custID = (String) one.get(0);
                    name = (String) one.get(1);
                    phone = (String) one.get(2);
                    age = (String) one.get(3);
                    Marital_status = (String) one.get(4);
                    city = (String) one.get(5);
                    custType = (String) one.get(6);
                    address = (String) one.get(7);
                    occupation = (String) one.get(8);
                    yrs = (String) one.get(9);
                    emailId = (String) one.get(10);

            %>
            <div width="70%" style="background-color:#CCC; border:1px;">
                <table  width="70%" align="left" style="background-color:#CCC;">
                    <tr width="100%" style="background-color:#CCC;">
                        <td width="30%" color ="#24315e"> Customer Name</td>
                        <td width="3%">:</td>
                        <td width="40%"><%=name%></td>
                        <td width="15%" color ="#24315e">Age</td>
                        <td width="2%">:</td>
                        <td width="10%"><%=age%></td>
                    </tr>
                    <tr width="100%">
                        <td width="30%" color ="#24315e"> Customer ID</td>
                        <td width="3%">:</td>
                        <td width="40%"><%=custID%></td>
                        <td width="15%" color ="#24315e">Phone Number</td>
                        <td width="2%">:</td>
                        <td width="10%"><%=phone%></td>
                    </tr>
                    <tr width="100%">
                        <td width="30%" color ="#24315e"> Address</td>
                        <td width="3%">:</td>
                        <td width="40%"><%=address%></td>
                        <td width="15%" color ="#24315e">City</td>
                        <td width="2%">:</td>
                        <td width="10%"><%=city%></td>
                    </tr>
                    <tr width="100%">
                        <td width="30%" color ="#24315e"> Customer Type</td>
                        <td width="3%">:</td>
                        <td width="40%"><%=custType%></td>
                        <td width="15%" color ="#24315e">Years With Bank</td>
                        <td width="2%">:</td>
                        <td width="10%"><%=yrs%></td>
                    </tr>
                    <tr width="100%">
                        <td width="30%" color ="#24315e"> Email</td>
                        <td width="3%">:</td>
                        <td width="40%"><%=emailId%></td>
                        <td width="15%" color ="#24315e">Occupation</td>
                        <td width="2%">:</td>
                        <td width="10%"><%=occupation%></td>
                    </tr>
                    <tr width="100%">
                        <td width="30%" color ="#24315e"> Signatories</td>
                        <td width="3%">:</td>
                        <td width="40%"> </td>
                        <td width="15%" color ="#24315e"> </td>
                        <td width="2%"> </td>
                        <td width="10%"> </td>
                    </tr>
                    <%
                        }
                    %>  
                </table>

            </div>

            <div width="30%" align="right" style="border:solid; border:1px;">
                <Strong style="font-style:italic;color:#24315e;font-size:13px;">Comments from Previous Contact</Strong> <br/>
                <select  width="230" name='currentproducts' multiple="multiple" size=6 style="margin-top:10px; height:100px;-moz-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);
                         -webkit-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);        
                         box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);">

                    <% 
                        ArrayList arr = Customer.getPreviousContact(custId);
                        System.out.println("Previous Contact Date: " + arr);
                        int siz = arr.size();
                        if(siz > 0){
                        for (int i = 0; i < arr.size(); i++) {
                            ArrayList one = (ArrayList) arr.get(i);
                            String preDate = (String) one.get(0);
                            preDate = preDate.substring(0, 10);
                    %>
                    <option ><%=preDate%></option> 
                    <%
                        }

                   }
                        else{
                    %>

                    <option >2017-07-23 </option>   
                    <%
                       }
                    %>

                </select>
            </div>

        </div>
        <div width="100%" style="margin-top:100px;">
            <Strong style="font-style:italic;color:#24315e;font-size:14px;">Current Product Holdings</Strong> 
            <div width ="100px" align="left" style="border:solid;border:thin;"><br/>
                <select width="230" name='currentproducts' multiple="multiple" size=6 style="height:100px;-moz-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);
                        -webkit-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);        
                        box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);">   

                    <%
                        ArrayList arra = Customer.getCurrentProducts(custId);
                        System.out.println("Current products: " + arra);  
                            for(Object produ : arra){
                    %>

                    <option >  <%=produ%></option> 
                    <%
                        }  
                    %>

                </select> 
                <div id="apDiv1" style=" border-style:solid; border:thin;">
                    <Strong style="font-style:italic;color:#24315e;font-size:14px;">Next Best Product to Sell</Strong> <i style="font-size:8px;">double click to mark so</i>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <Strong style="font-style:italic;color:#24315e;font-size:14px;">Other Products:</Strong> <br/>
                    &nbsp;&nbsp;&nbsp; 
                    <select name='currentproducts' multiple="multiple" size=6 style="height:100px;-moz-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);
                            -webkit-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);        
                            box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);"> 

                        <%
                             
                            ArrayList array = Customer.getNextBestProduct(custId);
                            int sizes =array.size(); 
                                for (int i = 0; i < sizes; i++) {
                                    ArrayList one = (ArrayList) array.get(i);
                                    String produc = (String) one.get(0);
                        %> 
                        <option > <%=produc%></option> 
                        <%
                            }
                       
                        %>
                    </select>                     
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                   
                    <select name='currentproducts2' multiple="multiple" size=6 style="height:100px;-moz-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);
                            -webkit-box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);        
                            box-shadow: inset 0px 0px 7px  rgba(0, 0, 0, 0.33);">
                        <%
                            ArrayList list = Customer.getOtherCustProducts(custId);
                            System.out.println("Other products: " + list);
                            for(Object product: list){
        
                        %> 
                        <option > <%=product%></option>  
                        <%
                            }
                        %>
                    </select>
                    <br/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  

                    <img src="images/arrowdown.png" name="alarm" width="39" height="30"   border="0"/>

                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;               
                    <img src="images/sold.png" name="alarm" width="60" height="30"   border="0"/>            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  

                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    <img src="images/arrowdown.png" name="alarm" width="30" height="30"   border="0"/>

                    <table width="400" height="77" border="1" >
                        <tr style="color:#FFF;" bgcolor="#333366">
                            <th  scope="col">Product Sold</th>
                            <th scope="col">Date</th> 
                        </tr>
                        <tr style="border:none;">
                            <td style="border:none;">&nbsp;</td>
                            <td style="border:none;">&nbsp;</td> 
                        </tr>
                        <tr style="border:none;">
                            <td style="border:none;">&nbsp;</td>
                            <td style="border:none;">&nbsp;</td> 
                        </tr>
                    </table>
                    <form name="form1" method="post" action="">

                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="submit" name="save" id="save" value="Save" align="right">
                    </form>
                </div>
                <br/>
                <Strong style="font-style:italic;color:#24315e;font-size:14px;">Turnover Details: </Strong> <br/>
                <table width="237" style="border:1px solid #000;">
                    <tr style="border:none;">
                        <td width="94" style="border:none;"> </td>
                        <td width="60" style="border:none;"><Strong style="font-style:italic;color:#24315e;font-size:14px;">Credit</Strong></td>
                        <td width="67" style="border:none;"><Strong style="font-style:italic;color:#24315e;font-size:14px;">Debit</Strong></td>
                    </tr>
                    <tr style="border:none;">
                        <td style="border:none;"> 
                            <Strong style="font-style:italic;color:#24315e;font-size:14px;">2014 Turnover:</Strong>
                        </td>
                        <td style="border:none;"><input type="text" size="10" name="" id="" value=""></td>
                        <td style="border:none;"><input type="text" size="10" name="" id="" value=""></td>
                    </tr>
                    <tr>
                        <td style="border:none;"><Strong style="font-style:italic;color:#24315e;font-size:14px;">2014 - 2015:</Strong></td>
                        <td style="border:none;"><input type="text" size="10" name="" id="" value=""></td>
                        <td style="border:none;"><input type="text" size="10" name="" id="" value=""></td>
                    </tr>
                    <tr>
                        <td style="border:none;"><Strong style="font-style:italic;color:#24315e;font-size:14px;">2015 - 2016:</Strong></td>
                        <td style="border:none;"><input type="text" size="10" name="" id="" value=""></td>
                        <td style="border:none;"><input type="text" size="10" name="" id="" value=""></td>
                    </tr>
                    <tr style="border:none;">
                        <td style="border:none;"><Strong style="font-style:italic;color:#24315e;font-size:14px;">2016 - 2017:</Strong></td>
                        <td style="border:none;"><input type="text" size="10" name="" id="" value=""></td>
                        <td style="border:none;"><input type="text" size="10" name="" id="" value=""></td>
                    </tr>
                </table>   
            </div>    
            <div>
            </div>           
        </div> 
    </body>

