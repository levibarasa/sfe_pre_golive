package com.orig.gls.dao.report;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GenRpt {

    public void formatPurchaseOrder(String jrxmurl, String reportdir, String rname, Map parameters, Collection col) {
        ClassLoader classLoader = getClass().getClassLoader();
        String reportDestination = reportdir + System.getProperty("file.separator") + rname + ".pdf";
        System.out.println("report dest " + reportDestination);
        File f = new File(reportDestination);
        if (f.exists()) {
            f.delete();
        }
        try {
            InputStream url = classLoader.getResourceAsStream(jrxmurl);
            JasperReport jasperReport = JasperCompileManager.compileReport(url);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(col));
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportDestination);
        } catch (Exception asd) {
            System.out.println(asd.getMessage());
        }
    }
}
