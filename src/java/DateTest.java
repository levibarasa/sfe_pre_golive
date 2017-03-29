/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Levi
 */
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateTest {

    public DateTest() {
        // expected input format in YYYY-MM-DD???
        String startDate = "27-MAR-2017";
        SimpleDateFormat in = new SimpleDateFormat("dd-MMM-yyyy");

          
        try {
         Date date = in.parse(startDate);
            // output format
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(fmt.format(date));
            
        } catch (ParseException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] argv) {
        new DateTest();
    }
}


