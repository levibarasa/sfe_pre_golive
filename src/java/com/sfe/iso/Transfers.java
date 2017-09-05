package com.sfe.iso;

import java.io.IOException;
import java.util.Random;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

public class Transfers {

    public ISOChannel channel;
    public String amt, particulars, currency, recipient_acct, anct;
    DateUtils ut = new DateUtils();
    String cur_time = ut.getCurrentDate();
    String datetime = ut.getCurrentDateAndTime();

    public double totalamt;
    public int totalrecs;

    public String run() {
        try {
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(1000000);
            String formatedInt;
            formatedInt = String.format("%06d", randomInt);
            PrepareFields fp = new PrepareFields();
            String amt1 = amt.replaceAll("[^\\d.]", "");
            ISOMsg m = new ISOMsg();
            m.setMTI("1200");
            m.set(2, fp.formatField2(recipient_acct.trim()).trim());
            m.set(3, fp.formatField3("24").trim());
            m.set(4, fp.formatField4(amt1).trim());
            m.set(11, fp.formatField11(formatedInt).trim());
            m.set(12, datetime);
            m.set(17, cur_time);
            m.set(24, fp.formatField24("200").trim());
            m.set(32, fp.formatField32("54").trim());
            m.set(41, "00800535282910  ");
            m.set(42, "00800535282910 ");
            m.set(43, fp.formatField43(particulars).trim());
            m.set(49, fp.formatField49("KES").trim());
            m.set(102, fp.formatField102(anct.trim(), "54", "001"));
            m.set(103, fp.formatField103(recipient_acct.trim(), "  57", "001"));
            m.set(123, fp.formatField123("CMN").trim());
            if (!channel.isConnected()) {
                channel.connect();
            }
            channel.send(m);
            m.dump(System.out, " ");
            ISOMsg msg = channel.receive();
            if (msg != null) {
                responseReceived(msg, m);
                msg.dump(System.out, " ");
                return msg.getString(39);
            } else {
                System.out.println("No Response issued by Finacle Server");
            }
            channel.disconnect();
        } catch (IOException ex) {
            System.out.println("Io  Exception Is : " + ex.getMessage());
        } catch (ISOException ex) {
            System.out.println("Iso  Exception Is : " + ex.getMessage());
        }
        return "000000";
    }

    public void responseReceived(ISOMsg m, Object o) {
        System.out.println("Response Code Is : " + m.getString(39));
    }

    public String FinSql(String where) {
        if ("111".equals(where)) {
            return "Invalid Scheme Type";
        } else if ("114".equals(where)) {
            return "Invalid Account Number";
        } else if ("115".equals(where)) {
            return "Requested Function not Supported";
        } else if ("121".equals(where)) {
            return "Withdrawal Amount Exceeded";
        } else if ("913".equals(where)) {
            return "Duplicate Transmission";
        } else if ("904".equals(where)) {
            return "Format Error";
        } else if ("902".equals(where)) {
            return "Invalid Transaction";
        } else if ("119".equals(where)) {
            return "Transaction Not Permitted";
        } else if ("116".equals(where)) {
            return "Insufficient Funds";
        } else if ("188".equals(where)) {
            return "Invalid Rate / Currency Combination";
        } else if ("185".equals(where)) {
            return "Invalid Currency / Transaction Amount";
        } else if ("184".equals(where)) {
            return "Account is Closed/Frozen";
        } else if ("180".equals(where)) {
            return "Transfer Limit Exceeded";
        } else if ("907".equals(where)) {
            return "cbc services down";
        } else if ("909".equals(where)) {
            return "System Malfunction";
        } else if ("911".equals(where)) {
            return "Card Issuer/ cbc service timed out";
        } else {
            return "The Response Code Is : " + where;
        }
    }
}
