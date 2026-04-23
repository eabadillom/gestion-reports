package com.ferbo.gestion.reports.jasper;

import com.ferbo.gestion.tools.GestionException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReporteOcupacionCamaraJR extends AbstractJR 
{
    private static Logger log = LogManager.getLogger(ReporteOcupacionCamaraJR.class);
    
    public static final String reportNameJASPER = "/jasper/almacen/OcupacionCamara.jrxml";
    
    public ReporteOcupacionCamaraJR(Connection conn) {
        super(conn);
    }
    
    public ReporteOcupacionCamaraJR(Connection conn, String logoAbsolutePath) {
        super(conn, logoAbsolutePath);
    }
    
    public byte[] getPDFReporteOcupacionCamara(List<Integer> listCliente, Integer idCliente, Integer idPlanta) throws GestionException 
    {
        byte[]              bytes = null;
        Date                fecha = new Date();
        InputStream         jrxml = null;
        Map<String, Object> jrParams = null;
        JasperBL jasperBO = new JasperBL();
        try {
            log.info("Ruta logo: " + this.logoPath);
            jrxml = fsTools.getResourceStream(reportNameJASPER);
            
            jrParams = new HashMap<String, Object>();
            jrParams.put("REPORT_CONNECTION", conn);
            jrParams.put("idCliente", listCliente);
            jrParams.put("idPlanta", idPlanta);
            jrParams.put("fecha", fecha);
            jrParams.put("imagen", this.logoPath);
            jrParams.put("REPORT_LOCALE", new Locale("es", "MX"));
            
            bytes = jasperBO.createPDF(jrParams, jrxml);
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
        
        return bytes;
    }
    
    public byte[] getXLSReporteOcupacionCamara(List<Integer> listCliente, Integer idCliente, Integer idPlanta) throws GestionException 
    {
        byte[]              bytes = null;
        Date                fecha = new Date();
        InputStream         jrxml = null;
        Map<String, Object> jrParams = null;
        
        JasperBL jasperBO = new JasperBL();
        
        try {
            log.info("Ruta logo: " + this.logoPath);
            jrxml = fsTools.getResourceStream(reportNameJASPER);
            
            jrParams = new HashMap<String, Object>();
            jrParams.put("REPORT_CONNECTION", conn);
            jrParams.put("idCliente", listCliente);
            jrParams.put("idPlanta", idPlanta);
            jrParams.put("fecha", fecha);
            jrParams.put("imagen", this.logoPath);
            jrParams.put("REPORT_LOCALE", new Locale("es", "MX"));
            
            bytes = jasperBO.createXLSX(jrParams, jrxml);
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
        
        return bytes;
    }
    
}
