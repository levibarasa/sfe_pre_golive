<%@page import="java.util.ArrayList"%>
<%@page import="com.sfe.dao.customer.Customer"%>
<html>
    <head>
        <title>Previous Weekly Call List</title>
        <link href="include/admin.css" rel="stylesheet" type="text/css">
        <link href="include/menu.css" rel="stylesheet" type="text/css">
        <link href="include/main.css" rel="stylesheet" type="text/css">

        <link rel="stylesheet" type="text/css" href="include/menu.css">

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
            max-height: 6000px;
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
 
    <table width="100%">
        <tr width="100%">
            <td width="20%"><br/>
            </td>
            <td width="60%">
                <div class="zui-wrapper">
                    <div class="zui-scroller">
                        <table class="zui-table">
                            <thead>  
                            <th >Customer ID </th>
                            <th >Name</th>
                            <th>Phone Number</th>
                            <th>Age</th>
                            <th>Marital Status</th>
                            <th>City</th>
                            <th> Customer Type</th>
                            <th>Permanent Address </th>
                            <th>Occupation </th>
                            <th>Years With Bank </th>
                            <th> Email ID</th> 
                            </tr>
                            </thead>
                            <tbody>
                                <%
                                    String user_code = (String) session.getAttribute("code");
                                    String days = "14"; // = request.getParameter("days"); 
                                    String rmCode = user_code; 
                                     rmCode = "242";
                                     days = "14";
                                   
                                    System.out.println("Days selected:" + days);
                                    System.out.println("RM Code:" + rmCode);
                                    int noOfdays = Integer.parseInt(days);
                                    ArrayList ar = Customer.getPreviousWeeklyCallList(rmCode, 1, noOfdays);
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
                                <tr style="font-size: 9px">
                                    <td style="font-size: 12px"> <%=custID%> </td>
                                    <td   style="font-size: 12px"><%=name%> </td>
                                    <td   style="font-size: 12px"><%=phone%></td>
                                    <td    style="font-size: 12px"><%=age%></td>
                                    <td  style="font-size: 12px"><%=Marital_status%></td>
                                    <td  style="font-size: 12px"><%=city%></td>
                                    <td  style="font-size: 12px"> <%=custType%></td>
                                    <td style="font-size: 12px"> <%=address%></td>
                                    <td style="font-size: 12px"><%=occupation%> </td>
                                    <td style="font-size: 12px"><%=yrs%> </td>
                                    <td style="font-size: 12px"> <%=emailId%></td> 
                                </tr>
                                <%
                                    }
                                %>  

                            </tbody>
                        </table>
                    </div>
                </div>
                <input type="submit" name="Save" value="Save" width="100%" id="save"   style="color:#ffffff;background-color:#24315e;align-self: center;">

            </td>
        </tr>
    </table>

</div>



