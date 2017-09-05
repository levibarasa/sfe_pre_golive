package com.sfe.dao.report;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public class GenRpt {

    public void formatPurchaseOrder(String jrxmurl, String reportdir, String rname, Map parameters, Collection col, String format) {
        ClassLoader classLoader = getClass().getClassLoader();
        String reportDestination = reportdir + System.getProperty("file.separator") + rname + format;
        System.out.println("report dest " + reportDestination);
        File f = new File(reportDestination);
        if (f.exists()) {
            f.delete();
        }
        try {
            switch (format) {
                case ".pdf":
                    InputStream url = classLoader.getResourceAsStream(jrxmurl);
                    JasperReport jasperReport = JasperCompileManager.compileReport(url);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(col));
                    JasperExportManager.exportReportToPdfFile(jasperPrint, reportDestination);
                    break;
                default:
                    JRXlsxExporter exporter = new JRXlsxExporter();
                    url = classLoader.getResourceAsStream(jrxmurl);
                    jasperReport = JasperCompileManager.compileReport(url);
                    jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(col));
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File(reportDestination));
                    exporter.exportReport();

                    break;
            }

        } catch (Exception asd) {
            System.out.println(asd.getMessage());
        }
    }
}
