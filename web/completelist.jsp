<%@page import="com.sfe.dao.customer.Customer"%>
<html>
    <head>
        <title>Complete List</title>
        <link href="include/admin.css" rel="stylesheet" type="text/css">
        <link href="include/menu.css" rel="stylesheet" type="text/css">
        <link href="include/main.css" rel="stylesheet" type="text/css">

        <link rel="stylesheet" type="text/css" href="include/menu.css">
        <%

            String rmCode = request.getParameter("rmCode");
            System.out.println("Rm Code: " + rmCode);
            ArrayList ar = Customer.getCompleteList(rmCode);
            int size = ar.size();
        %>
    </style>
    <script type="text/javascript" src="include/jquery-1.4.2.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link href="include/admin.css" rel="stylesheet" type="text/css">
    <link href="include/main.css" rel="stylesheet" type="text/css">
    <script src="include/jquery-1.11.3.min.js"></script>
    <script src="include/jquery.lettering.js"></script>
    <script src="include/code.jquery.comjquery-1.12.4.js"></script>
    <script src="include/jquery.dataTables.min.js"></script>
    <script src="include/jquery.textillate.js"></script>
    <link href="include/animate.css" rel="stylesheet" type="text/css">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="STYLESHEET" type="text/css" href="include/calendar.css">
    <link href="include/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" type="text/javascript" src="include/simplecalendar.js"></script>
    <link href="css/cssLayout.css" rel="stylesheet" type="text/css">
    <link href="include/menu.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
    <meta http-equiv="cache-control" content="max-age=0" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="pragma" content="no-cache" />
    <script language="javaScript" type="text/javascript" src="include/sim.js"></script>
    <script type="text/javascript">
        function MM_findObj(n, d) { //v4.01
            var p, i, x;
            if (!d)
                d = document;
            if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
                d = parent.frames[n.substring(p + 1)].document;
                n = n.substring(0, p);
            }
            if (!(x = d[n]) && d.all)
                x = d.all[n];
            for (i = 0; !x && i < d.forms.length; i++)
                x = d.forms[i][n];
            for (i = 0; !x && d.layers && i < d.layers.length; i++)
                x = MM_findObj(n, d.layers[i].document);
            if (!x && d.getElementById)
                x = d.getElementById(n);
            return x;
        }

        function MM_validateForm() { //v4.0
            var i, p, q, nm, test, num, min, max, errors = '', args = MM_validateForm.arguments;
            for (i = 0; i < (args.length - 2); i += 3) {
                test = args[i + 2];
                val = MM_findObj(args[i]);
                if (val) {
                    nm = val.name;
                    if ((val = val.value) !== "") {
                        if (test.indexOf('isEmail') !== -1) {
                            p = val.indexOf('@');
                            if (p < 1 || p === (val.length - 1))
                                errors += '- ' + nm + ' must contain an e-mail address.\n';
                        } else if (test !== 'R') {
                            num = parseFloat(val);
                            if (isNaN(val))
                                errors += '- ' + nm + ' must contain a number.\n';
                            if (test.indexOf('inRange') !== -1) {
                                p = test.indexOf(':');
                                min = test.substring(8, p);
                                max = test.substring(p + 1);
                                if (num < min || max < num)
                                    errors += '- ' + nm + ' must contain a number between ' + min + ' and ' + max + '.\n';
                            }
                        }
                    } else if (test.charAt(0) === 'R')
                        errors += '- ' + nm + ' is required.\n';
                }
            }
            if (errors)
                alert('The following error(s) occurred:\n' + errors);
            document.MM_returnValue = (errors === '');
        }
    </script>
    <script type="text/javascript">
        //
        var popup;
        function getCustInfoValue() {
            popup = window.open("customerInfo.jsp", "Customer Information", "width=600,height=800");
            popup.focus();
            return false
        }
        function getPrevWklyListCrtValue() {
            popup = window.open("callistperiod.jsp", "Previous Weekly List", "width=300,height=300");
            popup.focus();
            return false
        }

        function addNewCustomerValue() {
            popup = window.open("addnewcustomer.jsp", "Add New Customer", "width=400,height=400");
            popup.focus();
            return false
        }

    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#example').DataTable({
                "scrollY": 200,
                "scrollX": true
            });
        });
    </script>
    <style type="text/css">
        div.dataTables_wrapper {
            width: 800px;
            margin: 0 auto;
        }

        html,body{
            text-align: center;
            background-image: url(images/plain_white_background.jpg);
        }
        input[type=submit] {
            width: 15em;  height: 2em; border-radius: 12px;
        }
        input[type=button] {
            width: 15em;  height: 2em; border-radius: 12px;
        }

        .style1 {
            font-size: 16px;
            font-weight: bold;
            color: #FFFFFF;
        }
        .white{
            color:#000099;
            width:100%;
            height:5%;
            position:fixed;
            float:left;
            bottom:0px;
            background-color: #24315e;
        }
        .style2 {color: #FFFFFF}
        .bg {background-color: #24315e}
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
        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            border: 1px solid #ddd;
        }

        th, td {
            border: none;
            text-align: left;
            padding: 8px;
        }

        .fix {
            position: absolute;
            *position: relative; /*ie7*/
            margin-left: -100px;
            width: 100px;
        }
        .outer {
            position: relative;
        }
        .inner {
            overflow-x: scroll;
            overflow-y: visible;
            width: 400px; 
            margin-left: 100px;
        }

        tr:nth-child(even){background-color: #f2f2f2}

        .zui-table {
            border: none;
            border-right: solid 1px #DDEFEF;
            border-collapse: separate;
            border-spacing: 0;
            font: normal 10px Arial, sans-serif;
            width: 100%
        }
        .zui-table thead th {
            background-color: #DDEFEF;
            border: none;
            color: #336B6B;
            padding: 10px;
            text-align: left;
            text-shadow: 1px 1px 1px #fff;
            white-space: nowrap;
        }
        .zui-table tbody td {
            border-bottom: solid 1px #DDEFEF;
            color: #333;
            padding: 10px;
            text-shadow: 1px 1px 1px #fff;
            white-space: nowrap;
        }
        .zui-wrapper {
            position: relative;
        }
        .zui-scroller {
            margin-left: 0 px;
            overflow-x: auto;
            overflow-y: scroll;
            padding-bottom: 5px;
            max-width: 1000px; 
            max-height: 350px;
        }
        .zui-table .zui-sticky-col {
            border-left: solid 1px #DDEFEF;
            border-right: solid 1px #DDEFEF;
            left: 0;
            position: absolute;
            top: auto;
            width: 80px;
        }
    </style>
    <script>
        $(function () {
            $('.style2').textillate({in: {
                    effect: 'fadeInLeftBig'
                },
                out: {
                    effect: 'fadeOutLeftBig'
                },
                loop: true
            });
        });
    </script>
</head>
<body>
    <%@ include file="include/header_one.jsp" %>
    <Strong style="font-size:16px; align-self center;"><u>
            Complete List
        </u></Strong>
    <table width="100%">  
        <tr width="100%">
            <td width="20%"><br/>
                <p> <input type="button" name="home" onClick="window.location = 'index.jsp'" value="Home" width="100%" id="home"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="weeklycalllist" onClick="window.location = 'weeklycalllist.jsp'" value="Daily Call List" width="100%" id="weeklycalllist"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="completelist" onClick="window.location = 'completelist.jsp'" value="Complete List" width="100%" id="completelist"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="reports" value="Reports" width="100%" id="reports"   style="opacity: 0.6; cursor: not-allowed;color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="updaterevenue" value="Update Revenue/Income" width="100%" id="updaterevenue"   style="opacity: 0.6; cursor: not-allowed;color:#ffffff;background-color:#24315e"></p>
            </td>

        <form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=doacustomer">
            <td width="60%">
                <div class="zui-wrapper">
                    <div class="zui-scroller">
                        <table class="zui-table">
                            <thead>
                            <th>Customer ID </th> 
                            <th>Name</th>
                            <th>Phone Number</th>
                            <th>Age</th>
                            <th>Marital Status</th>
                            <th>City</th>
                            <th>Customer Type</th>
                            <th>Permanent Address</th>
                            <th>Occupation</th>
                            <th>Years with Bank</th>
                            <th>Email ID</th>
                            <th>Total Products</th>
                            <th>Agency Banking</th>
                            <th>Business Transaction</th>
                            <th>Call Deposit</th>
                            <th>Collection Scheme</th>
                            <th>Fcy Transaction</th>
                            <th>Flexi Deposit</th>
                            <th>Housing Loan</th>
                            <th>Hp Loan</th> <th>IMS</th> <th>Insurance Premium</th> <th>Locker Security</th>
                            <th>NGO</th> <th>Online Savers</th> <th>Overdraft</th> <th>Personal Transaction</th>
                            <th>Term Deposit</th> <th>Term Loan</th> <th>Trade Finance</th> <th>Young Savers</th>
                            <th>Bank Assurance</th> <th>Credit Cards</th> <th>Prepaid Cards</th> <th>Internet Banking</th>
                            </tr>
                            </thead>
                            <tbody>

                                <%                                    for (int i = 0; i < size; i++) {
                                        ArrayList one = (ArrayList) ar.get(i);
                                        int totalProducts = 0;
                                        String Customer_ID, Name, Permanent_phonenumber, Age, Marital_Status, City, Customer_Type, Permanent_Address, Occupation, Years_with_Bank, email_ID,
                                                Agency_Banking,
                                                Business_Transaction, Call_Deposit, Collection_Scheme, Fcy_Transaction, Flexi_Deposit, Housing_Loan, Hp_Loan,
                                                Ims, Insurance_Premium, Locker_Security, Ngo, Online_Savers, Overdraft, Personal_Transaction, Term_Deposit,
                                                Term_Loan, Trade_Finance, Young_Savers, Bank_Assurance, Credit_Cards, Prepaid_Cards, Internet_Banking;
                                        Customer_ID = (String) one.get(0);
                                        Name = (String) one.get(1);
                                        Permanent_phonenumber = (String) one.get(2);
                                        Age = (String) one.get(3);
                                        Marital_Status = (String) one.get(4);
                                        City = (String) one.get(5);
                                        Customer_Type = (String) one.get(6);
                                        Permanent_Address = (String) one.get(7);
                                        Occupation = (String) one.get(8);
                                        Years_with_Bank = (String) one.get(9);
                                        email_ID = (String) one.get(10);
                                        email_ID = (String) one.get(10);
                                        totalProducts = Customer.getCurrentProducts(Customer_ID).size();

                                        Agency_Banking = (String) one.get(11);
                                        Business_Transaction = (String) one.get(12);
                                        Call_Deposit = (String) one.get(13);
                                        Collection_Scheme = (String) one.get(14);
                                        Fcy_Transaction = (String) one.get(15);
                                        Flexi_Deposit = (String) one.get(16);
                                        Housing_Loan = (String) one.get(17);
                                        Hp_Loan = (String) one.get(18);
                                        Ims = (String) one.get(19);
                                        Insurance_Premium = (String) one.get(20);
                                        Locker_Security = (String) one.get(21);
                                        Ngo = (String) one.get(22);
                                        Online_Savers = (String) one.get(23);
                                        Overdraft = (String) one.get(24);
                                        Personal_Transaction = (String) one.get(25);
                                        Term_Deposit = (String) one.get(26);
                                        Term_Loan = (String) one.get(27);
                                        Trade_Finance = (String) one.get(28);
                                        Young_Savers = (String) one.get(29);
                                        Bank_Assurance = (String) one.get(30);
                                        Credit_Cards = (String) one.get(31);
                                        Prepaid_Cards = (String) one.get(32);
                                        Internet_Banking = (String) one.get(33);


                                %>   
                                <tr style="font-size: 9px">
                                    <td style="font-size: 9px;width: 50px;overflow: hidden; text-overflow: ellipsis;" width="80px"> 
                                        <%=Customer_ID%>
                                    </td>
                                    <td   style="font-size: 9px">
                                        <span style="display:block;width:200px;word-wrap:break-word;">
                                            <%=Name%>
                                        </span> 

                                    </td>
                                    <td   width="80px" style="font-size:9px;color: #666666;"><%=Permanent_phonenumber%></td>
                                    <td    width="80px" style="font-size:9px;color: #666666;"><%=Age%></td>
                                    <td   width="80px" style="font-size:9px;color: #666666;"><%=Marital_Status%></td>
                                    <td    width="80px" style="font-size:9px;color: #666666;"><%=City%></td>
                                    <td    width="80px" style="font-size:9px;color: #666666;"><%=Customer_Type%></td>
                                    <td    width="80px" style="font-size:9px;color: #666666;"><%=Permanent_Address%></td>
                                    <td    width="80px" style="font-size:9px;color: #666666;"><%=Occupation%></td>
                                    <td    width="80px" style="font-size:9px;color: #666666;"><%=Years_with_Bank%></td>
                                    <td    width="80px" style="font-size:9px;color: #666666;"><%=email_ID%></td>
                                    <td    width="80px" style="font-size:9px;color: #666666;"><%=totalProducts%></td>                                     
                                    <%
                                        int agency_Banking = Integer.parseInt(Agency_Banking);
                                        int business_Transaction = Integer.parseInt(Business_Transaction);
                                        int call_Deposit = Integer.parseInt(Call_Deposit);
                                        int collection_Scheme = Integer.parseInt(Collection_Scheme);
                                        int fcy_Transaction = Integer.parseInt(Fcy_Transaction);
                                        int flexi_Deposit = Integer.parseInt(Flexi_Deposit);
                                        int housing_Loan = Integer.parseInt(Housing_Loan);
                                        int hp_Loan = Integer.parseInt(Hp_Loan);
                                        int ims = Integer.parseInt(Ims);
                                        int insurance_Premium = Integer.parseInt(Insurance_Premium);
                                        int locker_Security = Integer.parseInt(Locker_Security);
                                        int ngo = Integer.parseInt(Ngo);
                                        int online_Savers = Integer.parseInt(Online_Savers);
                                        int overdraft = Integer.parseInt(Overdraft);
                                        int personal_Transaction = Integer.parseInt(Personal_Transaction);
                                        int term_Deposit = Integer.parseInt(Term_Deposit);
                                        int term_Loan = Integer.parseInt(Term_Loan);
                                        int trade_Finance = Integer.parseInt(Trade_Finance);
                                        int young_Savers = Integer.parseInt(Young_Savers);
                                        int bank_Assurance = Integer.parseInt(Bank_Assurance);
                                        int credit_Cards = Integer.parseInt(Credit_Cards);
                                        int prepaid_Cards = Integer.parseInt(Prepaid_Cards);
                                        int internet_Banking = Integer.parseInt(Internet_Banking);


                                    %> 

                                    <td   width="80px"   <%                                        if (agency_Banking < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (agency_Banking == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (agency_Banking == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (agency_Banking == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (agency_Banking > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=agency_Banking%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (business_Transaction < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (business_Transaction == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (business_Transaction == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (business_Transaction == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (business_Transaction > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=business_Transaction%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (call_Deposit < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (call_Deposit == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (call_Deposit == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (call_Deposit == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (call_Deposit > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=call_Deposit%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (collection_Scheme < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (collection_Scheme == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (collection_Scheme == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (collection_Scheme == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (collection_Scheme > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=collection_Scheme%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (fcy_Transaction < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (fcy_Transaction == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (fcy_Transaction == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (fcy_Transaction == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (fcy_Transaction > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=fcy_Transaction%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (flexi_Deposit < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (flexi_Deposit == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (flexi_Deposit == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (flexi_Deposit == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (flexi_Deposit > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=flexi_Deposit%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (housing_Loan < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (housing_Loan == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (housing_Loan == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (housing_Loan == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (housing_Loan > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=housing_Loan%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (hp_Loan < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (hp_Loan == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (hp_Loan == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (hp_Loan == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (hp_Loan > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=hp_Loan%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (ims < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (ims == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (ims == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (ims == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (ims > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=ims%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (insurance_Premium < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (insurance_Premium == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (insurance_Premium == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (insurance_Premium == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (insurance_Premium > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=insurance_Premium%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (locker_Security < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (locker_Security == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (locker_Security == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (locker_Security == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (locker_Security > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=locker_Security%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (ngo < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (ngo == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (ngo == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (ngo == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (ngo > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=ngo%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (online_Savers < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (online_Savers == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (online_Savers == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (online_Savers == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (online_Savers > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=online_Savers%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (overdraft < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (overdraft == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (overdraft == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (overdraft == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (overdraft > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=overdraft%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (personal_Transaction < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (personal_Transaction == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (personal_Transaction == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (personal_Transaction == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (personal_Transaction > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=personal_Transaction%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (term_Deposit < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (term_Deposit == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (term_Deposit == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (term_Deposit == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (term_Deposit > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=term_Deposit%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (term_Loan < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (term_Loan == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (term_Loan == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (term_Loan == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (term_Loan > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=term_Loan%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (trade_Finance < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (trade_Finance == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (trade_Finance == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (trade_Finance == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (trade_Finance > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=trade_Finance%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (young_Savers < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (young_Savers == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (young_Savers == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (young_Savers == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (young_Savers > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=young_Savers%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (bank_Assurance < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (bank_Assurance == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (bank_Assurance == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (bank_Assurance == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (bank_Assurance > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=bank_Assurance%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (credit_Cards < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (credit_Cards == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (credit_Cards == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (credit_Cards == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (credit_Cards > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=credit_Cards%>
                                    </td>
                                    <td   width="80px"   <%

                                        if (prepaid_Cards < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (prepaid_Cards == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (prepaid_Cards == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (prepaid_Cards == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (prepaid_Cards > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=prepaid_Cards%>
                                    </td>

                                    <td   width="80px"   <%

                                        if (internet_Banking < 1) {
                                          %> 
                                          style="background-color:red;font-size: 9px;"
                                          <%
                                          } else
                                          %> 
                                          <% if (internet_Banking == 2) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (internet_Banking == 3) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 
                                          <% if (internet_Banking == 4) {
                                          %> 
                                          style="font-size: 9px;background-color:yellow;"
                                          <%
                                          } else
                                          %> 

                                          <% if (internet_Banking > 5) {
                                          %> 
                                          style="font-size: 9px;background-color:green;"
                                          <%
                                              }
                                          %> 
                                          >
                                        <%=internet_Banking%>
                                    </td>
                                </tr>


                                <%

                                    }
                                %> 
                            </tbody>
                        </table>
                    </div>
                </div>

        </form>
    </td>
</tr>
</table>

</div>

<%@ include file="include/footer_one.jsp" %>

