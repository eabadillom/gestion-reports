package com.ferbo.gestion.reports.jasper;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.gestion.tools.GestionException;

public class KardexJR extends AbstractJR {
	
	private static final Logger log = LogManager.getLogger(KardexJR.class);
	public static final String reportNameJASPER = "/jasper/almacen/Kardex.jrxml";

	public KardexJR(Connection conn) {
		super(conn);
	}
	
	public KardexJR(Connection conn, String logoAbsolutePath) {
		super(conn, logoAbsolutePath);
	}
	
	public byte[] getPDF(String folioCliente)
	throws GestionException {
		byte[] bytes = null;
		
		InputStream         jrxml = null;
        Map<String, Object> jrParams = null;
        
        JasperBL jasperBO = new JasperBL();
        
        try {
        	log.debug("Ruta logo: " + this.logoPath);
        	jrxml = fsTools.getResourceStream(reportNameJASPER);
        	
            jrParams = new HashMap<String, Object>();
            jrParams.put("REPORT_CONNECTION", conn);
            jrParams.put("REPORT_LOCALE", new Locale("es", "MX"));
            jrParams.put("folioCliente", folioCliente);
            jrParams.put("imagen", logoPath);
            
            bytes = jasperBO.createPDF(jrParams, jrxml);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
		
		return bytes;
	}
	
	public byte[] getXLSX(String folioCliente)
	throws GestionException {
		byte[] bytes = null;
		
		InputStream         jrxml = null;
        Map<String, Object> jrParams = null;
        
        JasperBL jasperBO = new JasperBL();
        
        try {
        	log.debug("Ruta logo: " + this.logoPath);
        	jrxml = fsTools.getResourceStream(reportNameJASPER);
        	
            jrParams = new HashMap<String, Object>();
            jrParams.put("REPORT_CONNECTION", conn);
            jrParams.put("REPORT_LOCALE", new Locale("es", "MX"));
            jrParams.put("folioCliente", folioCliente);
            jrParams.put("imagen", logoPath);
            
            bytes = jasperBO.createXLSX(jrParams, jrxml);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
		
		return bytes;
	}

}
