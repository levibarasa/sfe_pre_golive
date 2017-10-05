<%@page import="com.sfe.dao.customer.Customer"%>
<%@page import="java.util.ArrayList"%>

<%
   // session.setAttribute("content_page", "customerInfo.jsp");
%>
<html>
    <head>
        <title>Customer Information</title>
        <script type="text/javascript">
            var l = document.getElementById('list');
            l.setAttribute('size', l.childElementCount + l.length);
        </script>

        <style type="text/css">
            #apDiv1 {
                position:absolute;
                width:496px;
                height:294px;
                z-index:1;
                left: 308px;
                top: 275px;
                background-color: #CCC;
            }
        </style>
    </head>
    <body>
        <fieldset>
            <div style="width:100%;background-color:#24315e;color:#FFF;font-size:17px;text-align:center;">
                <strong>
                    Detailed Customer View
                </strong>
            </div>
            </br>
            <div width="100%">
                <%
                    String custId  = "";// request.getParameter("custId");
                    System.out.println("Customer ID is : " + custId);
                    if(custId =="" || custId.equalsIgnoreCase("")){
                   custId = "10418";
                    }
                    ArrayList ar = Customer.getCustomerInfo(custId);
                    System.out.println("Customer: "+ar);
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


            </div>
        </fieldset>
    </body>

