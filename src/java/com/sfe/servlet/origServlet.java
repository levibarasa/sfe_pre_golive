package com.sfe.servlet;
 
import com.sfe.web.customer.Customerw; 
import com.sfe.web.user.Accessw; 
import com.sfe.web.user.Userw;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class origServlet extends HttpServlet {

    private static final Log log = LogFactory.getLog("origlogger");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mod = request.getParameter("MOD");
        String act = request.getParameter("ACT");
        switch (mod) {
            case "BOK":
                switch (act) {
                    case "doLog":
                        Accessw.handleLogin(request, response);
                        break;
                    case "Home":
                        Accessw.handleHome(request, response);
                        break;
                    case "Login":
                        Accessw.handleLoginNav(request, response);
                        break; 
                    case "domuser":
                        Userw.handleMaintainUser(request, response);
                        break;
                    case "domoduser":
                        Userw.handleModifyUser(request, response);
                        break;
                    case "dochC":
                        Userw.changePwd(request, response);
                        break;
                    case "chC":
                        Userw.navChangePss(request, response);
                        break;
                    case "Logout":
                        Accessw.handleLogout(request, response);
                        break;  
                  case "dogenerate":
                        Customerw.handleGenerateList(request, response);
                        break;
                   case "dosearch":
                        Customerw.handleDoSearch(request, response);
                        break;
                   case "doadcustomer":
                        Customerw.handleAddNewCustomer(request, response);
                        break;
                    case "doadexistcustomer":
                        Customerw.handleAddExistCustomer(request, response);
                        break;
                    case "doacustomer":
                        Customerw.handleUpdateCustomer(request, response);
                        break;
                    case "doapcustomer":
                        Customerw.handleUpdatePrevCustomer(request, response);
                        break;
                    case "dosac":
                        Accessw.handleSac(request, response);
                        break;
                    case "gosac":
                        Accessw.goSac(request, response);
                        break; 
                    case "domodcustomer":
                        Customerw.handleMaintainCustomer(request, response);
                        break;
                    case "domcustomer":
                        Customerw.handleModifyCustomer(request, response);
                        break;    
   
  
//                    case "gotorpt":
//                        Reports.handleGoReport(request, response);
//                        break;
//                    case "gorpt":
//                        Reports.goReport(request, response);
//                        break; 

                }
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
