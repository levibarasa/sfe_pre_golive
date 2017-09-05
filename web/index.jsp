<html>
    <head>
        <title>Logged In Administrator</title>
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
    <script src="include/jquery.textillate.js"></script>
    <link href="include/animate.css" rel="stylesheet" type="text/css">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="STYLESHEET" type="text/css" href="include/calendar.css">
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
        
        if (${adminloggedin == 'true'}) {
            alert("You have successfully as Admin");
        }
    </script>
    <style type="text/css">
        <!--
        html,body{
            text-align: center;
            background-image: url(images/plain_white_background.jpg);
        }
        input[type=submit] {
            width: 20em;  height: 2em;
        }
        input[type=button] {
            width: 20em;  height: 2em;
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
        -->
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

    <table width="100%">
        <tr width="100%">
            <td width="20%"><br/>
                <p> <input type="button" name="home" value="Home" onClick="window.location = 'index.jsp'" width="100%" id="home"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="weeklycalllist" onClick="window.location = 'weeklycalllist.jsp'" value="Daily Call List" width="100%" id="weeklycalllist"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="completelist" onClick="window.location = 'completelist.jsp'" value="Complete List" width="100%" id="completelist"   style="color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="reports" value="Reports" width="100%" id="reports"   style="opacity: 0.6; cursor: not-allowed;color:#ffffff;background-color:#24315e"></p>
                <p> <input type="button" name="updaterevenue" value="Update Revenue/Income" width="100%" id="updaterevenue"   style="opacity: 0.6; cursor: not-allowed;color:#ffffff;background-color:#24315e"></p>


            </td>
            <td width="60%"><br/>
                <p style="color: #24315e; font-size: 14px"><Strong><u>Sales Force Effectiveness Tool </u> </Strong></p>
                <p style="font-style: italic; font-size: 13px"> The SFE Customer Masterlist tool enables a relationship officer to: </p>
                <p style="font-style: italic; font-size: 13px">1) Access up-to-date view of customers' product holding on a weekly basis </p>
                <p style="font-style: italic; font-size: 13px">2) Identify potential cross sell and next-best-products-to-sell opportunities in their portfolios </p>
                <p style="font-style: italic; font-size: 13px">3) View Performance in terms of Sales Pipeline, SFE Sustainability and the performance against budgets </p>

            </td>
            <td width="20%">
                <br/><br/><br/>
                <img src="images/alarm.png" name="alarm" width="50" height="50"   border="0"/>
                <p> <input type="button" name="remindersfortoday" value="Reminders For Today" width="100%" id="remindersfortoday"   style="color:#ffffff;background-color:#24315e"></p>
                <br/><br/><br/><br/><br/> 
                <Strong>  
                    <p style="color: #24315e; font-size: 14px">In case of any clarifications or help, please contact:</p>
                    <p style="color: #24315e; font-size: 14px">Nicholas Talam Kiprotich - Nicholas.Kiprotich@imbank.co.ke</p>
                    <p style="color: #24315e; font-size: 14px">Abdul Muzahir Bhaijee - ABhaijee@imbank.co.ke</p>
                </strong>
            </td>
        </tr>
    </table>



    <%@ include file="include/footer_one.jsp" %>

