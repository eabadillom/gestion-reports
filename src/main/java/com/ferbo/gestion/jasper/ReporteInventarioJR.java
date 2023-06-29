package com.ferbo.gestion.jasper;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.gestion.tools.GestionException;

public class ReporteInventarioJR extends AbstractJR {
	
	public static final String reportNameJASPER = "/jasper/almacen/InventarioAlmacen.jrxml";
	
	public ReporteInventarioJR(Connection conn) {
		super(conn);
	}
	
	public ReporteInventarioJR(Connection conn, String logoAbsolutePath) {
		super(conn, logoAbsolutePath);
	}
	
	private static Logger log = LogManager.getLogger(ReporteInventarioJR.class);
	
	public byte[] getPDFReporteInventario(Integer idCliente, Integer idPlanta) throws GestionException {
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
            jrParams.put("imagen", logoPath);
            jrParams.put("idCliente", idCliente);
            jrParams.put("Fecha", fecha);
            jrParams.put("Camara", null);
            jrParams.put("Planta", idPlanta);
            jrParams.put("REPORT_LOCALE", new Locale("es", "MX"));
            
            bytes = jasperBO.createPDF(jrParams, jrxml);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
        
        return bytes;
	}
	
	public byte[] getXLSReporteInventario(Integer idCliente, Integer idPlanta) throws GestionException {
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
            jrParams.put("imagen", logoPath);
            jrParams.put("idCliente", idCliente);
            jrParams.put("Fecha", fecha);
            jrParams.put("Camara", null);
            jrParams.put("Planta", idPlanta);
            jrParams.put("REPORT_LOCALE", new Locale("es", "MX"));
            
            bytes = jasperBO.createXLSX(jrParams, jrxml);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
        
        return bytes;
	}

}
