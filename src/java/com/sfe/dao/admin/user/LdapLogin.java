/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.sfe.dao.admin.user;
 
import java.util.Hashtable;
import java.util.Properties;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
 
/**
*
* @author Levi
*/
public class LdapLogin {
 
//    public static void main(String[] args) {
//        LdapLogin ldapLogin = new LdapLogin();
//        System.out.println(ldapLogin.runApp("nicholast",""));
//////NOTE: replace theUserName below with the Active Directory/LDAP user whose attribites you want printed.
////        //ldapExaminer.printUserBasicAttributes("nicholas.kiprotich", ldapExaminer.getLdapContext());
//       //System.out.println(ldapExaminer.authUser("soumya.rao", "Password", ldapExaminer.getLdapContext())); 
//    }
    public String runApp(String username, String pass){
        LdapContext ctx =getLdapContext();
       // printUserBasicAttributes("nicholast",ctx);
    return    authUser(username,pass,ctx);
    }
    public LdapContext getLdapContext() {
        LdapContext ctx = null;
        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.SECURITY_AUTHENTICATION, "Simple");
 
//NOTE: replace user@domain.com with a User that is present in your Active Directory/LDAP
            env.put(Context.SECURITY_PRINCIPAL, "inm\\SVC_SFE"); ///
//NOTE: replace userpass with passwd of this user.
            env.put(Context.SECURITY_CREDENTIALS, "EffectiveSal3s");
//NOTE: replace ADorLDAPHost with your Active Directory/LDAP Hostname or IP.
            env.put(Context.PROVIDER_URL, "ldap://192.168.221.23:389");
 
            System.out.println("Attempting to Connect...");
 
            ctx = new InitialLdapContext(env, null); //<<<<<<------------------------FAILS HERE--------------->>>>
            System.out.println("Connection Successful.");
        } catch (NamingException nex) {
            System.out.println("LDAP Connection: FAILED");
            nex.printStackTrace();
        }
        return ctx;
    }
 
    private void printUserBasicAttributes(String username, LdapContext ctx) {
        try {
 
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
//NOTE: The attributes mentioned in array below are the ones that will be retrieved, you can add more.
            String[] attrIDs = {"distinguishedName",
                "sn",
                "givenname",
                "mail",
                "telephonenumber", "canonicalName", "userAccountControl", "accountExpires"};
            constraints.setReturningAttributes(attrIDs);
 
//NOTE: replace DC=domain,DC=com below with your domain info. It is essentially the Base Node for Search.
            NamingEnumeration answer = ctx.search("ou=IMBANK-KENYA,DC=inm,DC=corp", "sAMAccountName="
                    + username, constraints);
 
            if (answer.hasMore()) {
                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
                System.out.println(attrs.get("distinguishedName"));
                System.out.println(attrs.get("givenname"));
                System.out.println(attrs.get("sn"));
                System.out.println(attrs.get("mail"));
                System.out.println(attrs.get("telephonenumber"));
                System.out.println(attrs.get("canonicalName"));
                System.out.println(attrs.get("userAccountControl"));
                System.out.println(attrs.get("accountExpires"));
            } else {
                throw new Exception("Invalid User");
            }
 
        } catch (Exception ex) {
            //  ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
 
    public String authUser(String username, String password, LdapContext ctx) {
        SearchControls ctrls = new SearchControls();
        ctrls.setReturningAttributes(new String[]{"givenName", "sn", "memberOf"});
        ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String loggedinUser = "";
        try {
            NamingEnumeration<javax.naming.directory.SearchResult> answers = ctx.search("ou=IMBANK-KENYA,DC=inm,DC=corp", "sAMAccountName="
                    + username, ctrls);
            javax.naming.directory.SearchResult result = answers.nextElement();
 
            String user = result.getNameInNamespace();
 
            System.out.println("them  returned usr: " + user);
 
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            props.put(Context.PROVIDER_URL, "ldap://192.168.221.23:389");
            props.put(Context.SECURITY_PRINCIPAL, user);
            props.put(Context.SECURITY_CREDENTIALS, password);
 
            ctx = new InitialLdapContext(props, null);
 
            System.out.println("Logged successfully");
            loggedinUser = username;
        } catch (Exception e) {
            //set the authentication failure
            if (e.getMessage().contains("52e")) {
                loggedinUser = "";
                System.out.println("Invalid Credentials");
            }
            e.printStackTrace();
        }
        return loggedinUser;
    }
}