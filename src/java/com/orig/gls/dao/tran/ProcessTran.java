/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.dao.tran;

import com.orig.gls.iso.Transfers;
import com.orig.gls.prop.GlsProp;
import java.io.InputStream;
import org.jpos.iso.ISOException;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.GenericPackager;

/**
 *
 * @author HENRY
 */
public class ProcessTran {

    public String postTran(String fromAcnt, String toAcnt, String amt, String parts) {
        GlsProp bi = new GlsProp();
        System.out.println("Doing transaction posting");
        String host = bi.getDBProperty().getProperty("com.trans.host");
        String port = bi.getDBProperty().getProperty("com.trans.port").replaceAll("[^0-9]", "");
        int ports = Integer.parseInt(port);
        System.out.println("Port is " + ports);
        InputStream url = getClass().getClassLoader().getResourceAsStream("basic.xml");
        try {
            GenericPackager packager = new GenericPackager(url);
            Transfers c = new Transfers();
            System.out.println("Transaction Amount is : " + amt);
            c.amt = amt;
            c.currency = "KES";
            c.anct = fromAcnt;
            c.recipient_acct = toAcnt;
            c.particulars = parts;
            c.channel = new ASCIIChannel(host, ports, packager);
            //s  c.channel.connect();
            return c.run();
        } catch (ISOException e) {
            System.out.println("Exception " + e.getMessage());
        }
        return "Something Wrong";
    }
}
